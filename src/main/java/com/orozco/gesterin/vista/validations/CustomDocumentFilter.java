package com.orozco.gesterin.vista.validations;

import com.orozco.gesterin.exception.ControllerExceptionHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 18 sep. 2024
 * @description Sistema GESTERIN
 */
public class CustomDocumentFilter extends DocumentFilter {

    private final int maxLength;
    private final Pattern pattern;

    public CustomDocumentFilter(int maxLength, Pattern pattern) {
        this.maxLength = maxLength;
        this.pattern = pattern;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) {
        try {
            String newString = getNewString(fb, offset, string);
            if (isValid(newString)) {
                super.insertString(fb, offset, string, attr);
            }
        } catch (BadLocationException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al aplicar insertString");
            Logger.getLogger(CustomDocumentFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) {

        try {
            String newString = getNewString(fb, offset, text);
            if (isValid(newString)) {
                super.replace(fb, offset, length, text, attrs);
            }
        } catch (BadLocationException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al aplicar replace");
            Logger.getLogger(CustomDocumentFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) {
        try {
            super.remove(fb, offset, length);
        } catch (BadLocationException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al aplicar remove");
            Logger.getLogger(CustomDocumentFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getNewString(FilterBypass fb, int offset, String string) {
        try {
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
            return newText;
        } catch (BadLocationException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al aplicar getNewString");
            Logger.getLogger(CustomDocumentFilter.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    private boolean isValid(String text) {
        return text.length() <= maxLength && pattern.matcher(text).matches();
//        boolean valid = text.length() <= maxLength && pattern.matcher(text).matches();
//        System.out.println("Text: \"" + text + "\" Valid: " + valid);
//        return valid;
    }
}
