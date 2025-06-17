package Filters;

import javax.swing.text.*;

public class LettersOnlyFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null && string.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text != null && text.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
