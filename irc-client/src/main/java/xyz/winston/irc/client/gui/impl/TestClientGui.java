package xyz.winston.irc.client.gui.impl;

import java.awt.event.*;
import javax.swing.event.*;
import lombok.Getter;
import lombok.NonNull;
import xyz.winston.irc.client.gui.GUIHandler;

import java.awt.*;
import javax.swing.*;
import xyz.winston.irc.client.gui.type.*;

/**
 * @author winston
 */
@SuppressWarnings("Convert2MethodRef")
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

    private void messageInputKeyTyped(KeyEvent e) {
        if (e.getKeyCode() != KeyEvent.VK_ENTER) return;
        handler.handleEnterKey();
    }

    private void usingPasswordAction(ChangeEvent e) {
        passwordInput.setEnabled(usingPassword.isSelected());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        username = new JHintTextField("User Name (3-16 characters)");
        connectButton = new JButton();
        messageInput = new JHintTextField("Enter message");
        sendButton = new JButton();
        addressInput = new JHintTextField("IP Address (or domain name)");
        portInput = new JHintTextField("Port (default: 1337)");
        disconnectButton = new JButton();
        usingPassword = new JCheckBox();
        clearButton = new JButton();
        passwordInput = new JHintTextField("Password");
        passwordInput.setEnabled(false);
        scrollPane1 = new JScrollPane();
        chatLog = new JTextArea();
        $vseparator = new JSeparator();
        $serverInfoLabel1 = new JLabel();
        scrollPane2 = new JScrollPane();
        serverInformation = new JTextArea();
        $serverInfoLabel2 = new JLabel();

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

        //---- messageInput ----
        messageInput.setFont(new Font("Arial", Font.PLAIN, 18));
        messageInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                messageInputKeyTyped(e);
            }
        });
        add(messageInput);
        messageInput.setBounds(10, 480, 390, 40);

        //---- sendButton ----
        sendButton.setText("S");
        sendButton.addActionListener(e -> sendAction(e));
        add(sendButton);
        sendButton.setBounds(405, 480, 45, 40);

        //---- addressInput ----
        addressInput.setToolTipText("address");
        addressInput.setFont(new Font("Arial", addressInput.getFont().getStyle(), 16));
        add(addressInput);
        addressInput.setBounds(10, 45, 245, 20);

        //---- portInput ----
        portInput.setFont(new Font("Arial", portInput.getFont().getStyle(), 16));
        add(portInput);
        portInput.setBounds(265, 45, 95, 20);

        //---- disconnectButton ----
        disconnectButton.setText("Disconnect");
        disconnectButton.addActionListener(e -> disconnectAction(e));
        add(disconnectButton);
        disconnectButton.setBounds(370, 40, 130, 25);

        //---- usingPassword ----
        usingPassword.setText("Using password");
        usingPassword.addChangeListener(e -> usingPasswordAction(e));
        add(usingPassword);
        usingPassword.setBounds(370, 70, 125, usingPassword.getPreferredSize().height);

        //---- clearButton ----
        clearButton.setText("C");
        clearButton.addActionListener(e -> clearHandler(e));
        add(clearButton);
        clearButton.setBounds(455, 480, 45, 40);
        add(passwordInput);
        passwordInput.setBounds(10, 70, 350, 25);

        //======== scrollPane1 ========
        {
            scrollPane1.setAutoscrolls(true);

            //---- chatLog ----
            chatLog.setEditable(false);
            scrollPane1.setViewportView(chatLog);
        }
        add(scrollPane1);
        scrollPane1.setBounds(10, 105, 490, 370);

        //---- $vseparator ----
        $vseparator.setOrientation(SwingConstants.VERTICAL);
        add($vseparator);
        $vseparator.setBounds(505, -5, 10, 540);

        //---- $serverInfoLabel1 ----
        $serverInfoLabel1.setText("Server information");
        add($serverInfoLabel1);
        $serverInfoLabel1.setBounds(new Rectangle(new Point(530, 0), $serverInfoLabel1.getPreferredSize()));

        //======== scrollPane2 ========
        {

            //---- serverInformation ----
            serverInformation.setEditable(false);
            serverInformation.setText("awaiting connection ...");
            scrollPane2.setViewportView(serverInformation);
        }
        add(scrollPane2);
        scrollPane2.setBounds(515, 40, 145, 480);

        //---- $serverInfoLabel2 ----
        $serverInfoLabel2.setText("(Real time update)");
        add($serverInfoLabel2);
        $serverInfoLabel2.setBounds(new Rectangle(new Point(530, 15), $serverInfoLabel2.getPreferredSize()));

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
    private JHintTextField messageInput;
    private JButton sendButton;
    private JHintTextField addressInput;
    private JHintTextField portInput;
    private JButton disconnectButton;
    private JCheckBox usingPassword;
    private JButton clearButton;
    private JHintTextField passwordInput;
    private JScrollPane scrollPane1;
    private JTextArea chatLog;
    private JSeparator $vseparator;
    private JLabel $serverInfoLabel1;
    private JScrollPane scrollPane2;
    private JTextArea serverInformation;
    private JLabel $serverInfoLabel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
