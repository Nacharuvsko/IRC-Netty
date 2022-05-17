package xyz.winston.irc.client.gui;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import xyz.winston.irc.client.connection.ConnectionManager;
import xyz.winston.irc.client.gui.impl.TestClientGui;
import xyz.winston.irc.client.gui.type.PasswordContainer;
import xyz.winston.irc.protocol.packet.C01PacketClientMessage;
import xyz.winston.nettytransporter.connection.Connection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * There are many duplicates, so fuck them, no time for refractoring
 * making bitches rn $$$$$$$$
 * @author winston
 */
@RequiredArgsConstructor
public final class GUIHandler {

    @Getter
    private final TestClientGui gui;

    private Future<?> pendingConnection;

    public void handleConnectButton(
            final @NonNull String userName,
            final @NonNull String address,
            final @NonNull String portStr,
            final @NonNull String password,
            final boolean usePassword
    ) {
        if (userName.length() > 16 || userName.length() < 3) {
            logMessage(Level.SYSTEM, "username length should be in bounds of 3-16");
            return;
        }

        if (address.isEmpty() || address.isBlank()) {
            logMessage(Level.SYSTEM, "Invalid address!");
            return;
        }

        int port;
        if (portStr.isEmpty() || portStr.isBlank()) {
            port = 1337;
        } else {
            try {
                port = Integer.parseInt(portStr);
            } catch (final @NonNull NumberFormatException e) {
                logMessage(Level.SYSTEM, "Invalid port");
                return;
            }
        }

        if (port < 1 || port > 65535) {
            logMessage(Level.SYSTEM, "Invalid port");
            return;
        }

        logMessage(Level.SYSTEM, "Connecting to: " + address + " | Port: " + port + " ...");

        pendingConnection = Executors.newFixedThreadPool(1).submit(() -> {
            gui.getConnectButton().setEnabled(false);
            gui.getAddressInput().setEnabled(false);
            gui.getUsername().setEnabled(false);
            gui.getPortInput().setEnabled(false);
            gui.getPasswordInput().setEnabled(false);
            gui.getUsingPassword().setEnabled(false);

            try {
                val conn = ConnectionManager.IMP.connect(address, port, userName,
                        new PasswordContainer(password, usePassword)).join();
                if (!conn.getLeft()) {
                    logMessage(Level.SYSTEM, "Can't connect to " + address + ":" + port + " - " + conn.getRight());

                    gui.getConnectButton().setEnabled(true);
                    gui.getAddressInput().setEnabled(true);
                    gui.getUsername().setEnabled(true);
                    gui.getPortInput().setEnabled(true);
                    gui.getPasswordInput().setEnabled(true);
                    gui.getUsingPassword().setEnabled(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logMessage(Level.SYSTEM, "Unexpected exception occurred. See console for logs");
            }
        });
    }

    public void handleEnterKey() {
        if (!gui.getMessageInput().hasFocus()) return;
        val messageInput = gui.getMessageInput().getText();
        handleSendButton(messageInput);
    }

    public void handleDisconnectButton() {
        if (pendingConnection != null && !pendingConnection.isCancelled() && !pendingConnection.isDone()) {
            logMessage(Level.SYSTEM, "Pending connection aborted");
            pendingConnection.cancel(true);
            gui.getConnectButton().setEnabled(true);
            gui.getAddressInput().setEnabled(true);
            gui.getUsername().setEnabled(true);
            gui.getPortInput().setEnabled(true);
            gui.getPasswordInput().setEnabled(true);
            gui.getUsingPassword().setEnabled(true);
            return;
        }

        gui.getConnectButton().setEnabled(true);
        gui.getAddressInput().setEnabled(true);
        gui.getUsername().setEnabled(true);
        gui.getPortInput().setEnabled(true);
        gui.getPasswordInput().setEnabled(true);
        gui.getUsingPassword().setEnabled(true);
        if (!ConnectionManager.IMP.isConnected()) return;
        ConnectionManager.IMP.dropConnection();
        logMessage(Level.SYSTEM, "Disconnected from the server");
    }

    public void handleSendButton(
            final @NonNull String messageRaw
    ) {
        if (!ConnectionManager.IMP.isConnected()) return;
        if (messageRaw.isEmpty() || messageRaw.isBlank()) return;
        gui.getMessageInput().setText("");
        Connection.sendPacket(new C01PacketClientMessage(
                ConnectionManager.IMP.getConnectorClient().getServerName(),
                messageRaw
        ));
    }

    public void handleClear() {
        gui.getChatLog().setText("");
    }

    // -----------------------------------------------------------

    public void updateServerInformation(
            final long serverEpoch,
            final int online
    ) {
        val epochStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(serverEpoch));
        gui.getServerInformation().setText(
                "Server time: \n" + epochStr + "\n-------\n" +
                "Online: " + online
        );
    }

    public void logMessage(final @NonNull String message) {
        logMessage(Level.ALL, message);
    }

    public void logMessage(
            final @NonNull Level level,
            final @NonNull String message
    ) {
        val date = new SimpleDateFormat("HH:mm:ss").format(new Date());
        gui.getChatLog().append(date + " " + level.prefix + message + "\n");
    }

    @RequiredArgsConstructor
    public enum Level {
        SYSTEM("System > "),
        ALL("");

        final @NonNull String prefix;
    }
}
