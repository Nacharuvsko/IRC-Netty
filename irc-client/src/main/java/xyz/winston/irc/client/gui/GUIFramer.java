package xyz.winston.irc.client.gui;

import lombok.Getter;
import lombok.NonNull;
import xyz.winston.irc.client.gui.impl.TestClientGui;

import javax.swing.*;

/**
 * heheh xyeta)))))))))))))
 * @author winston
 */
public final class GUIFramer extends JFrame {

    @Getter
    private final TestClientGui testClientGui;

    public GUIFramer(final @NonNull TestClientGui clientGui) {
        this.testClientGui = clientGui;
        add(testClientGui);
    }

}
