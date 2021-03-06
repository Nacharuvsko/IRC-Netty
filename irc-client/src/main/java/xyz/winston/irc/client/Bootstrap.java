package xyz.winston.irc.client;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.client.connection.ConnectionManager;
import xyz.winston.irc.client.gui.GUIFramer;
import xyz.winston.irc.client.gui.impl.TestClientGui;

import javax.swing.*;

/**
 * Client owo uwu >w<
 * @author winston
 */
public final class Bootstrap {

    public static Bootstrap INSTANCE;

    @Getter
    private final GUIFramer framer;

    public Bootstrap() {
        INSTANCE = this;

        ConnectionManager.IMP.initialize();

        framer = new GUIFramer(new TestClientGui());
        framer.setSize(670, 555);
        framer.setTitle("IRC Client");
        framer.setLocation(500, 500);
        framer.setResizable(false);
        framer.setVisible(true);
        framer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /** Entry Point */
    public static void main(final String @NotNull [] args) {
        new Bootstrap();
    }

}
