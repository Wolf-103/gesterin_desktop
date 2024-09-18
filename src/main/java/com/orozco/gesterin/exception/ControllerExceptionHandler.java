package com.orozco.gesterin.exception;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 18 sep. 2024
 * @description Sistema GESTERIN
 *
 * Clase con la cual controlo las excepciones y los mensajes generados por el
 * sistema almaceno los tipos de excepciones y en funcion a eso se generan las
 * acciones o mensajes
 */
public class ControllerExceptionHandler {

    private static final List<BiConsumer<Exception, Object>> handlers = new ArrayList<>();

    /**
     * Carga la excepción al manejador de errores Se usa bitconsumer porque se
     * adapta bien a lo que necesito y es una interfaz propia de java con ella
     * almaceno dos argumentos de tipo genérico, el primero lo uso para la
     * excepcion y el segundo para describir el contexto de la excepcion no
     * devuelve nada por lo que es ideal, solo necesito manejar los mensajes de
     * error puedo evaluar mas adelante si coloco o no un logger Tiene un único
     * método abstracto, que puedo implementarlo como lambda y con el devolver
     * el mensaje
     *
     * @param handler de tipo BiConsumer<Exception, Object>
     */
    public static void registerHandler(BiConsumer<Exception, Object> handler) {
        if (handler != null) {
            handlers.add(handler);
        } else {
            System.out.println("Error: El manejador de excepciones no puede ser nulo.");
        }
    }

    /**
     * Procesa la excepción con los manejadores registrados.
     *
     * @param e La excepción ocurrida.
     * @param context Contexto adicional sobre dónde ocurrió la excepción.
     */
    public static void handleError(Exception e, Object context) {
        if (handlers.isEmpty()) {
            System.out.println("No hay manejadores registrados.");
        } else {
            for (BiConsumer<Exception, Object> handler : handlers) {
                try {
                    handler.accept(e, context);
                } catch (Exception ex) {
                    System.out.println("Error al manejar la excepción: " + ex.getMessage());
                }
            }
        }
    }

    // Inicializador estático para registrar manejadores
    static {
        // Registrar manejador para RuntimeException
        registerHandler((ex, context) -> {
            if (ex instanceof RuntimeException) {
                System.out.println("RuntimeException Error: " + ex.getMessage() + "\n"
                        + "Detalle: " + context);
                getMessageError(context.toString(), """
                                                    Ocurrió un problema no controlado, por favor cierre el programa y intente nuevamente. 
                                                    Si el problema persiste contacte a tu técnico asignado..""", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Registrar manejador para NullPointerException
        registerHandler((ex, context) -> {
            if (ex instanceof NullPointerException) {
                System.out.println("NullPointerException manejada: " + ex.getMessage() + "\n"
                        + "Detalle: " + context);
                getMessageError(context.toString(), "Ocurrión un problema al verificar los datos, algunos o su totalidad se encuentran vacíos. "
                        + "Por favor verifique los datos enviados.", JOptionPane.WARNING_MESSAGE);
            }
        });

        registerHandler((ex, context) -> {
            if (ex instanceof SQLException) {
                System.out.println("SQLException error: " + ex.getMessage() + "\n"
                        + "Detalle: " + context);
                getMessageError(context.toString(), """
                                                    Error al consultar Base de Datos. 
                                                    Detalle: """+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        });

        registerHandler((ex, context) -> {
            if (ex instanceof BadLocationException) {
                System.out.println("BadLocationException error: " + ex.getMessage() + "\n"
                        + "Detalle: " + context);
                getMessageError(context.toString(), "Error al manitular documento", JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    /**
     * Generamos un error dentro del hilo de despacho de eventos (EDT) para
     * evitar que se bloquee la GUI
     *
     * @param title
     * @param message
     * @param typeError
     */
    private static void getMessageError(String title, String message, int typeError) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, message, title, typeError);
        });
    }
}
