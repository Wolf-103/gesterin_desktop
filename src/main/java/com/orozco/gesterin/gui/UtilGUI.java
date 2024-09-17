/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orozco.gesterin.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
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
        Color background;
        Color foreground;
        if (estado) {
            background = colorBackgroundActivo;
            foreground = colorForegroundCamposActivos;
        } else {
            background = colorBackgroundInactivo;
            foreground = colorForegroundCamposInactivo;
        }
        //Utilice instanceof cuando necesite confirmar el tipo de un objeto en tiempo de ejecución
        Component[] componentes = panel.getComponents();
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JTextField) {
                ((JTextField) componentes[i]).setEnabled(estado);
                ((JTextField) componentes[i]).setBackground(background);
                ((JTextField) componentes[i]).setForeground(foreground);
                ((JTextField) componentes[i]).setDisabledTextColor(foreground);
            } else {
                if (componentes[i] instanceof JTextArea) {
                    ((JTextArea) componentes[i]).setEnabled(estado);
                    ((JTextArea) componentes[i]).setBackground(background);
                    ((JTextArea) componentes[i]).setForeground(foreground);
                    //Por si tiene disabletextColor
                    ((JTextArea) componentes[i]).setDisabledTextColor(foreground);
                } else {
                    if (componentes[i] instanceof JFormattedTextField) {
                        ((JFormattedTextField) componentes[i]).setEnabled(estado);
                        ((JFormattedTextField) componentes[i]).setBackground(background);
                        ((JFormattedTextField) componentes[i]).setForeground(foreground);

                    } else {
                        if (componentes[i] instanceof JComboBox) {
                            ((JComboBox) componentes[i]).setEnabled(estado);
                            ((JComboBox) componentes[i]).setBackground(background);
                            ((JComboBox) componentes[i]).setForeground(foreground);
                        } else {
                            if (componentes[i] instanceof JScrollPane) {
                                JScrollPane jScrllP = (JScrollPane) componentes[i];
                                for (int j = 0; j < jScrllP.getViewport().getComponents().length; j++) {
                                    if (jScrllP.getViewport().getComponent(j) instanceof JTextArea) {
                                        ((JTextArea) jScrllP.getViewport().getComponent(j)).setEnabled(estado);
                                        ((JTextArea) jScrllP.getViewport().getComponent(j)).setBackground(background);
                                        ((JTextArea) jScrllP.getViewport().getComponent(j)).setForeground(foreground);
                                        //Por si tiene disabletextColor
                                        ((JTextArea) jScrllP.getViewport().getComponent(j)).setDisabledTextColor(foreground);
                                    }
                                }
                            } else {
                                if (componentes[i] instanceof JButton) {
                                    ((JButton) componentes[i]).setEnabled(estado);
                                } else {
                                    if (componentes[i] instanceof JCheckBox) {
                                        JCheckBox checkBox = ((JCheckBox) componentes[i]);
                                        checkBox.setEnabled(estado);
                                        ((JCheckBox) componentes[i]).setBackground(background);
                                        ((JCheckBox) componentes[i]).setForeground(foreground);
                                    } else {
                                        if (componentes[i] instanceof JToggleButton) {
                                            JToggleButton btn = ((JToggleButton) componentes[i]);
                                            btn.setEnabled(estado);
                                        } else {
                                            if (componentes[i] instanceof JSpinner) {
                                                JSpinner spin = ((JSpinner) componentes[i]);
                                                spin.setEnabled(estado);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
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
            } else {
                if (componentes[i] instanceof JTextArea) {
                    cajaArea = (JTextArea) componentes[i];
                    cajaArea.setText("");
                } else {
                    if (componentes[i] instanceof JFormattedTextField) {
                        cajaF = (JFormattedTextField) componentes[i];
                        cajaF.setText("");
                    } else {
                        if (componentes[i] instanceof JComboBox) {
                            combo = (JComboBox) componentes[i];
                            combo.setSelectedIndex(0);
                        } else {
                            if (componentes[i] instanceof JScrollPane) {
                                JScrollPane jScrllP = (JScrollPane) componentes[i];
                                for (int j = 0; j < jScrllP.getViewport().getComponents().length; j++) {
                                    if (jScrllP.getViewport().getComponent(j) instanceof JTextArea) {
                                        cajaArea = ((JTextArea) jScrllP.getViewport().getComponent(j));
                                        cajaArea.setText("");
                                    }
                                }
                            } else {
                                if (componentes[i] instanceof JCheckBox) {
                                    JCheckBox checkBox = ((JCheckBox) componentes[i]);
                                    checkBox.setSelected(false);
                                } else {
                                    if (componentes[i] instanceof JSpinner) {
                                        JSpinner spin = ((JSpinner) componentes[i]);
                                        spin.setValue(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

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

    private static boolean isFrameOpen(JDesktopPane desktop, JInternalFrame frame) {
        JInternalFrame[] frames = desktop.getAllFrames();
        for (JInternalFrame openFrame : frames) {
            if (openFrame.getClass().equals(frame.getClass())) {
                return true;
            }
        }
        return false;
    }

    private static void configureFrame(JInternalFrame frame) {
        frame.setClosable(true);
        frame.setResizable(false);
    }

    private static void centerFrame(JDesktopPane desktop, JInternalFrame frame) {
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = frame.getSize();
        int x = Math.max(0, (desktopSize.width - frameSize.width) / 2);
        int y = Math.max(0, (desktopSize.height - frameSize.height) / 2);
        frame.setLocation(x, y);
    }

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

    private static void addResizeListener(JDesktopPane desktop, JInternalFrame frame) {
        desktop.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                centerFrame(desktop, frame);
            }
        });
    }

}
