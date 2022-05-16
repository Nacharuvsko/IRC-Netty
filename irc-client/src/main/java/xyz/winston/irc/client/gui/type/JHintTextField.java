package xyz.winston.irc.client.gui.type;

import lombok.NonNull;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public final class JHintTextField extends JTextField implements FocusListener {

    private String hint;
    private boolean showingHint = true;

    public JHintTextField() {
        this("n/a");
    }

    public JHintTextField(final @NonNull String hintText) {
        super(hintText);
        super.addFocusListener(this);
        this.hint = hintText;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");

            showingHint = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText(hint);
            showingHint = true;
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }

    public void setCustomHint(final @NonNull String customHint) {
        this.hint = customHint;
    }
}
