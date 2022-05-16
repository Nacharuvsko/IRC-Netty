package xyz.winston.irc.client.gui.impl;

import java.awt.event.*;
import lombok.Getter;
import lombok.NonNull;
import xyz.winston.irc.client.gui.GUIHandler;

import java.awt.*;
import javax.swing.*;
import xyz.winston.irc.client.gui.type.*;

/**
 * @author winston
 */
@Getter
public final class TestClientGui extends JPanel {

    private final GUIHandler handler;

    public TestClientGui() {
        initComponents();
        handler = new GUIHandler(this);
    }

    private void connectAction(ActionEvent e) {
        handler.handleConnectButton(
                username.getText(), addressInput.getText(), portInput.getText(),
                passwordInput.getText(), usingPassword.isSelected()
        );
    }

    private void disconnectAction(ActionEvent e) {
        handler.handleDisconnectButton();
    }

    private void sendAction(ActionEvent e) {
        handler.handleSendButton(messageInput.getText());
    }

    private void clearHandler(ActionEvent e) {
        handler.handleClear();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        username = new JHintTextField("User Name (3-16 characters)");
        connectButton = new JButton();
        chatLog = new JTextArea();
        messageInput = new JTextField();
        sendButton = new JButton();
        addressInput = new JHintTextField("IP Address (or domain name)");
        $separator = new JSeparator();
        $statusLabel = new JLabel();
        statusLabel = new JLabel();
        portInput = new JHintTextField("Port (default: 1337)");
        passwordInput = new JHintTextField("Password");
        disconnectButton = new JButton();
        usingPassword = new JCheckBox();
        clearButton = new JButton();

        //======== this ========
        setLayout(null);

        //---- username ----
        username.setToolTipText("user name");
        username.setFont(new Font("Arial", username.getFont().getStyle(), 16));
        add(username);
        username.setBounds(10, 15, 350, 20);

        //---- connectButton ----
        connectButton.setText("Connect");
        connectButton.addActionListener(e -> connectAction(e));
        add(connectButton);
        connectButton.setBounds(370, 10, 130, 25);

        //---- chatLog ----
        chatLog.setEditable(false);
        chatLog.setFont(chatLog.getFont().deriveFont(chatLog.getFont().getSize() + 4f));
        add(chatLog);
        chatLog.setBounds(10, 105, 490, 365);
        add(messageInput);
        messageInput.setBounds(10, 480, 405, 35);

        //---- sendButton ----
        sendButton.setText("Send");
        sendButton.addActionListener(e -> sendAction(e));
        add(sendButton);
        sendButton.setBounds(420, 480, 80, 40);

        //---- addressInput ----
        addressInput.setToolTipText("address");
        addressInput.setFont(new Font("Arial", addressInput.getFont().getStyle(), 16));
        add(addressInput);
        addressInput.setBounds(10, 45, 245, 20);
        add($separator);
        $separator.setBounds(-5, 525, 540, 3);

        //---- $statusLabel ----
        $statusLabel.setText("Status:");
        $statusLabel.setFont($statusLabel.getFont().deriveFont(17f));
        add($statusLabel);
        $statusLabel.setBounds(new Rectangle(new Point(15, 535), $statusLabel.getPreferredSize()));

        //---- statusLabel ----
        statusLabel.setText("IDLE");
        statusLabel.setFont(statusLabel.getFont().deriveFont(17f));
        add(statusLabel);
        statusLabel.setBounds(new Rectangle(new Point(75, 535), statusLabel.getPreferredSize()));

        //---- portInput ----
        portInput.setFont(new Font("Arial", portInput.getFont().getStyle(), 16));
        add(portInput);
        portInput.setBounds(265, 45, 95, 20);
        add(passwordInput);
        passwordInput.setBounds(10, 75, 350, passwordInput.getPreferredSize().height);

        //---- disconnectButton ----
        disconnectButton.setText("Disconnect");
        disconnectButton.addActionListener(e -> disconnectAction(e));
        add(disconnectButton);
        disconnectButton.setBounds(370, 40, 130, 25);

        //---- usingPassword ----
        usingPassword.setText("Using password");
        add(usingPassword);
        usingPassword.setBounds(370, 75, 125, usingPassword.getPreferredSize().height);

        //---- clearButton ----
        clearButton.setText("Clear");
        clearButton.addActionListener(e -> clearHandler(e));
        add(clearButton);
        clearButton.setBounds(420, 535, 79, 30);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JHintTextField username;
    private JButton connectButton;
    private JTextArea chatLog;
    private JTextField messageInput;
    private JButton sendButton;
    private JHintTextField addressInput;
    private JSeparator $separator;
    private JLabel $statusLabel;
    private JLabel statusLabel;
    private JHintTextField portInput;
    private JHintTextField passwordInput;
    private JButton disconnectButton;
    private JCheckBox usingPassword;
    private JButton clearButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
