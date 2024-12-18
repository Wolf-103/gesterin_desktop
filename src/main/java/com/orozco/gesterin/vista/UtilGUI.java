package com.orozco.gesterin.vista;

import com.orozco.gesterin.utils.AppConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public abstract class UtilGUI {

    //Ancho máximo
    public static int MAX_WIDTH = 800;
    //Alto máximo
    public static int MAX_HEIGHT = 800;

    public static Color colorForegroundCamposActivos = Color.BLACK;
    public static Color colorForegroundCamposInactivo = Color.WHITE;

    public static Color colorBackgroundActivo = Color.WHITE;
    public static Color colorBackgroundInactivo = Color.GRAY;

    private static final String dniNumeroRepetido = "(\\d)\\1{" + AppConstants.DNI_MIN + "," + AppConstants.DNI_MAX + "}";
    private static final String cuitCuilRepetido = "(\\d)\\1{" + AppConstants.CUIT_CUIL_MIN + "," + AppConstants.CUIT_CUIL_MAX + "}";
    private static final String numeroTelefonicoRepetido = "(\\d)\\1{" + AppConstants.TELEPHONE_MIN + "," + AppConstants.TELEPHONE_MAX + "}";

    /**
     * Coloca los componentes en según su estado predefinido para el sistema
     * editando la posibilidad de ser o no editados (estado) y sus propiedades
     * de Background y Foreground según se predefinió en el sistema
     *
     * @param panel objeto de tipo Jpanel que contiene los objetos a evaluar
     * @param estado
     * <ul>
     * <li>true: estado del componente activo</li>
     * <li>false: estado del componente inactivo</li>
     * </ul>
     */
    public static void deshabilitarHabilitarComponentes(JPanel panel, boolean estado) {
        Color background = estado ? colorBackgroundActivo : colorBackgroundInactivo;
        Color foreground = estado ? colorForegroundCamposActivos : colorForegroundCamposInactivo;

        /**
         * Utilice consumer porque es una interfaz que admite solo un argumento
         * que seran en este caso los compnonetes dentro del panel, se genera un
         * map como clave el tipo de componente y como valor el consumer que
         * guarda al componente en sí Cada componente que se define dentro de la
         * clase, antes de guardarse se redefine con las nuevas características
         * según el estado
         *
         */
        Map<Class<? extends Component>, Consumer<Component>> componentActions = new HashMap<>();
        componentActions.put(JTextField.class, comp -> {
            JTextField textField = (JTextField) comp;
            textField.setEnabled(estado);
            textField.setBackground(background);
            textField.setForeground(foreground);
            textField.setDisabledTextColor(foreground);
        });
        componentActions.put(JTextArea.class, comp -> {
            JTextArea textArea = (JTextArea) comp;
            textArea.setEnabled(estado);
            textArea.setBackground(background);
            textArea.setForeground(foreground);
            textArea.setDisabledTextColor(foreground);
        });
        componentActions.put(JFormattedTextField.class, comp -> {
            JFormattedTextField formattedTextField = (JFormattedTextField) comp;
            formattedTextField.setEnabled(estado);
            formattedTextField.setBackground(background);
            formattedTextField.setForeground(foreground);
        });
        componentActions.put(JComboBox.class, comp -> {
            JComboBox<?> comboBox = (JComboBox<?>) comp;
            comboBox.setEnabled(estado);
            comboBox.setBackground(background);
            comboBox.setForeground(foreground);
        });
        componentActions.put(JScrollPane.class, comp -> {
            JScrollPane scrollPane = (JScrollPane) comp;
            for (Component c : scrollPane.getViewport().getComponents()) {
                if (c instanceof JTextArea textArea) {
                    textArea.setEnabled(estado);
                    textArea.setBackground(background);
                    textArea.setForeground(foreground);
                    textArea.setDisabledTextColor(foreground);
                }
            }
        });
        componentActions.put(JButton.class, comp -> {
            JButton button = (JButton) comp;
            button.setEnabled(estado);
        });
        componentActions.put(JPasswordField.class, comp -> {
            JPasswordField jPasswordField = (JPasswordField) comp;
            jPasswordField.setEnabled(estado);
            jPasswordField.setBackground(background);
            jPasswordField.setForeground(foreground);
        });
        componentActions.put(JCheckBox.class, comp -> {
            JCheckBox checkBox = (JCheckBox) comp;
            checkBox.setEnabled(estado);
            checkBox.setBackground(background);
            checkBox.setForeground(foreground);
        });
        componentActions.put(JRadioButton.class, comp -> {
            JRadioButton jRadioBtn = (JRadioButton) comp;
            jRadioBtn.setEnabled(estado);
            jRadioBtn.setBackground(background);
            jRadioBtn.setForeground(foreground);
        });
        componentActions.put(JToggleButton.class, comp -> {
            JToggleButton toggleButton = (JToggleButton) comp;
            toggleButton.setEnabled(estado);
        });
        componentActions.put(JSpinner.class, comp -> {
            JSpinner spinner = (JSpinner) comp;
            spinner.setEnabled(estado);
        });

        /**
         * Aplicar acciones a cada componente, si no se encuentra coincidencia
         * en la key (la clase del componente) entonces devuelve el componente
         * sin modificar
         *
         */
        for (Component componente : panel.getComponents()) {
            componentActions.getOrDefault(componente.getClass(), c -> {
            }).accept(componente);
        }
//        Component[] componentes = panel.getComponents();
//        for (Component componente : componentes) {
//            if (componente instanceof JTextField jTextField) {
//                jTextField.setEnabled(estado);
//                jTextField.setBackground(background);
//                jTextField.setForeground(foreground);
//                jTextField.setDisabledTextColor(foreground);
//            } else {
//                if (componente instanceof JTextArea jTextArea) {
//                    jTextArea.setEnabled(estado);
//                    jTextArea.setBackground(background);
//                    jTextArea.setForeground(foreground);
//                    //Por si tiene disabletextColor
//                    jTextArea.setDisabledTextColor(foreground);
//                } else {
//                    if (componente instanceof JFormattedTextField jFormattedTextField) {
//                        jFormattedTextField.setEnabled(estado);
//                        jFormattedTextField.setBackground(background);
//                        jFormattedTextField.setForeground(foreground);
//                    } else {
//                        if (componente instanceof JComboBox jComboBox) {
//                            jComboBox.setEnabled(estado);
//                            jComboBox.setBackground(background);
//                            jComboBox.setForeground(foreground);
//                        } else {
//                            if (componente instanceof JScrollPane jScrllP) {
//                                for (int j = 0; j < jScrllP.getViewport().getComponents().length; j++) {
//                                    if (jScrllP.getViewport().getComponent(j) instanceof JTextArea jTextArea) {
//                                        jTextArea.setEnabled(estado);
//                                        jTextArea.setBackground(background);
//                                        jTextArea.setForeground(foreground);
//                                        //Por si tiene disabletextColor
//                                        jTextArea.setDisabledTextColor(foreground);
//                                    }
//                                }
//                            } else {
//                                if (componente instanceof JButton jButton) {
//                                    jButton.setEnabled(estado);
//                                } else {
//                                    if (componente instanceof JCheckBox checkBox) {
//                                        checkBox.setEnabled(estado);
//                                        ((JCheckBox) componente).setBackground(background);
//                                        ((JCheckBox) componente).setForeground(foreground);
//                                    } else {
//                                        if (componente instanceof JToggleButton btn) {
//                                            btn.setEnabled(estado);
//                                        } else {
//                                            if (componente instanceof JSpinner spin) {
//                                                spin.setEnabled(estado);
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

    public static void borrarCamposDeComponentes(JPanel panel) {
        JTextField caja;
        JFormattedTextField cajaF;
        JTextArea cajaArea;
        JComboBox combo;
        Component[] componentes = panel.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JTextField) {
                caja = (JTextField) componentes[i];
                caja.setText("");
            } else if (componentes[i] instanceof JTextArea) {
                cajaArea = (JTextArea) componentes[i];
                cajaArea.setText("");
            } else if (componentes[i] instanceof JFormattedTextField) {
                cajaF = (JFormattedTextField) componentes[i];
                cajaF.setText("");
            } else if (componentes[i] instanceof JComboBox) {
                combo = (JComboBox) componentes[i];
                combo.setSelectedIndex(0);
            } else if (componentes[i] instanceof JScrollPane) {
                JScrollPane jScrllP = (JScrollPane) componentes[i];
                for (int j = 0; j < jScrllP.getViewport().getComponents().length; j++) {
                    if (jScrllP.getViewport().getComponent(j) instanceof JTextArea) {
                        cajaArea = ((JTextArea) jScrllP.getViewport().getComponent(j));
                        cajaArea.setText("");
                    }
                }
            } else if (componentes[i] instanceof JCheckBox) {
                JCheckBox checkBox = ((JCheckBox) componentes[i]);
                checkBox.setSelected(false);
            } else if (componentes[i] instanceof JSpinner) {
                JSpinner spin = ((JSpinner) componentes[i]);
                spin.setValue(0);
            }
        }
    }

    /**
     * Antes de crear y adjuntar una ventana interna a mi desktop verifico si ya
     * existe si no existe la configuro y la agrego a mi desktop la centro y la
     * hago visible
     *
     * @param desktop
     * @param frame
     */
    public static void openInternalFrame(JDesktopPane desktop, JInternalFrame frame) {
        if (!isFrameOpen(desktop, frame)) {
            configureFrame(frame);
            desktop.add(frame);
            centerFrame(desktop, frame);
            frame.setVisible(true);
            addResizeListener(desktop, frame);
        } else {
            bringToFront(desktop, frame);
        }
    }

    /**
     * Verifica si dentro de los internal frame de mi desktop existe un iFrame
     * dado
     *
     * @param desktop
     * @param frame
     * @return true si encuentra false sino
     */
    private static boolean isFrameOpen(JDesktopPane desktop, JInternalFrame frame) {
        JInternalFrame[] frames = desktop.getAllFrames();
        for (JInternalFrame openFrame : frames) {
            if (openFrame.getClass().equals(frame.getClass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Nuestros internal frame no podran redimensionarse ni maximizar
     *
     * @param frame
     */
    private static void configureFrame(JInternalFrame frame) {
        frame.setClosable(true);
        frame.setResizable(false);
        frame.setIconifiable(false);
        frame.setMaximizable(false);
    }

    /**
     * Cada vez que redimensionemos nuestra pantalla principal, la o las
     * pantallas dependientes del escritorio se moveran a su centro
     *
     * @param desktop
     * @param frame
     */
    private static void centerFrame(JDesktopPane desktop, JInternalFrame frame) {
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = frame.getSize();
        int x = Math.max(0, (desktopSize.width - frameSize.width) / 2);
        int y = Math.max(0, (desktopSize.height - frameSize.height) / 2);
        frame.setLocation(x, y);
    }

    /**
     * Si intentamos abrir una ventana interna que ya existe dentro del
     * escritorio lo movemos al frente
     *
     * @param desktop
     * @param frame
     */
    private static void bringToFront(JDesktopPane desktop, JInternalFrame frame) {
        JInternalFrame[] frames = desktop.getAllFrames();
        for (JInternalFrame openFrame : frames) {
            if (openFrame.getClass().equals(frame.getClass())) {
                try {
                    openFrame.setSelected(true);
                } catch (PropertyVetoException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * Este médodo escucha cuando se redimenciona la pantalla principal y coloca
     * a los frame internos en el medio de éste
     *
     * @param desktop
     * @param frame
     */
    private static void addResizeListener(JDesktopPane desktop, JInternalFrame frame) {
        desktop.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centerFrame(desktop, frame);
            }
        });
    }

    /**
     * Comprueba que no se repitan numeros
     *
     * @param numero es numero a verificar
     * @param tipo tipo de patron a evaluar (dni,cuitCuil, telefono)
     * @return
     */
    public static boolean validarNumerosRepetidos(String numero, String tipo) {
        // Cargamos el patron de expresion a comprara .    
        String patron = "";
        if (tipo.equals("dni")) {
            patron = dniNumeroRepetido;
        } else {
            if (tipo.equals("telefono")) {
                patron = numeroTelefonicoRepetido;
            } else {
                if (tipo.equals("cuitCuil")) {
                    patron = cuitCuilRepetido;
                }
            }
        }
        Pattern pattern1 = Pattern.compile(patron, Pattern.MULTILINE);
        //verificar si pertence al patron
        Matcher matcher1 = pattern1.matcher(numero);
        return matcher1.matches();
    }

    /**
     * Comprueba si cumple con el patron de la construciòn de un correo
     * electronico
     *
     * @param email es el correo completo a verificar
     * @return
     * <ul>
     * <li>TRUE: si cumple con las condiciones</li>
     * <li>FALSE: si incumple las condiciones</li>
     * </ul>
     */
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(AppConstants.PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @SuppressWarnings("unchecked")
    public static void CargarCombo(List miLista, JComboBox miCombo) {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        if (miLista != null) {
            combo.addElement("[Seleccionar]");
            for (Object o : miLista) {
                combo.addElement(o);
            }

        } else {
            combo.addElement("[Sin elementos]");
        }
        miCombo.setEditable(false);
        miCombo.setModel(combo);

    }

    /**
     * Vuelve mayúsculas todas las primeras letras
     *
     * @param field: palabra a capitalizar
     * @return String
     */
    public static String capitalizeAll(String field) {
        //Genera array de palabras tomando como parpametro de separacion los espacios
        String[] words = field.split("\\s+");
        //Se utiliza para facilitar la reconstrucción con el detalle que se elijaS
        StringBuilder capitalizedString = new StringBuilder();
        //Recorremos las palabras y apliamos los cambios en cada una
        for (String word : words) {
            if (word.length() > 0) {
                capitalizedString.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        //Convertimos a StringBuilder en String. Trim elimina espacios adicionales.
        return capitalizedString.toString().trim();
    }

    /**
     * Capitalizar primera letra en texto y despues de un punto
     *
     * @param field. Texto a capitalizar
     * @return String
     */
    public static String capitalizeFirstLetters(String field) {
        //Se utiliza para facilitar la reconstrucción con el detalle que se elijaS
        StringBuilder capitalizedString = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : field.toCharArray()) {
            //Verificamos que no sea una letra sola y no sea un numero
            if (capitalizeNext && Character.isLetter(c)) {
                capitalizedString.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                capitalizedString.append(c);
            }

            if (c == '.') {
                capitalizeNext = true;
            }
        }

        return capitalizedString.toString();
    }
}
