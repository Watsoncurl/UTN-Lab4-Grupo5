package Filters;

import javax.swing.text.*;

public class NumbersOnlyFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null && string.matches("[0-9]+")) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text != null && text.matches("[0-9]+")) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
