package com.example.cryptography_app;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class CryptographyApp {
    private JFrame frame;
    private JTextField messageField;
    private JTextField keyField;
    private JTextArea resultArea;
    private JTextField encryptedMessageField;
    private JTextField decryptionKeyField;
    private JTextArea decryptedResultArea;
    private JTextField emailField;

    public CryptographyApp() {
        // Frame setup
        frame = new JFrame("Cryptography App");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Encryption Section
        JLabel encryptLabel = new JLabel("Encryption");
        encryptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        encryptLabel.setBounds(50, 20, 200, 30);
        frame.add(encryptLabel);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setBounds(50, 60, 100, 25);
        frame.add(messageLabel);

        messageField = new JTextField();
        messageField.setBounds(150, 60, 300, 30);
        frame.add(messageField);

        JLabel keyLabel = new JLabel("Key (Integer):");
        keyLabel.setBounds(50, 100, 100, 25);
        frame.add(keyLabel);

        keyField = new JTextField();
        keyField.setBounds(150, 100, 300, 30);
        frame.add(keyField);

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(150, 140, 120, 30);
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptMessage();
            }
        });
        frame.add(encryptButton);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(50, 180, 100, 25);
        frame.add(resultLabel);

        resultArea = new JTextArea();
        resultArea.setBounds(150, 180, 300, 100);
        resultArea.setLineWrap(true);
        resultArea.setEditable(false);
        frame.add(resultArea);

        // Decryption Section
        JLabel decryptLabel = new JLabel("Decryption");
        decryptLabel.setFont(new Font("Arial", Font.BOLD, 18));
        decryptLabel.setBounds(600, 20, 200, 30);
        frame.add(decryptLabel);

        JLabel encryptedMessageLabel = new JLabel("Encrypted Message:");
        encryptedMessageLabel.setBounds(600, 60, 150, 25);
        frame.add(encryptedMessageLabel);

        encryptedMessageField = new JTextField();
        encryptedMessageField.setBounds(750, 60, 300, 30);
        frame.add(encryptedMessageField);

        JLabel decryptionKeyLabel = new JLabel("Key (Integer):");
        decryptionKeyLabel.setBounds(600, 100, 150, 25);
        frame.add(decryptionKeyLabel);

        decryptionKeyField = new JTextField();
        decryptionKeyField.setBounds(750, 100, 300, 30);
        frame.add(decryptionKeyField);

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.setBounds(750, 140, 120, 30);
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptMessage();
            }
        });
        frame.add(decryptButton);

        JLabel decryptedResultLabel = new JLabel("Result:");
        decryptedResultLabel.setBounds(600, 180, 100, 25);
        frame.add(decryptedResultLabel);

        decryptedResultArea = new JTextArea();
        decryptedResultArea.setBounds(750, 180, 300, 100);
        decryptedResultArea.setLineWrap(true);
        decryptedResultArea.setEditable(false);
        frame.add(decryptedResultArea);

        // Email Section
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 300, 100, 25);
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 300, 300, 30);
        frame.add(emailField);

        JButton sendEmailButton = new JButton("Send Email");
        sendEmailButton.setBounds(150, 340, 120, 30);
        sendEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendEmail();
            }
        });
        frame.add(sendEmailButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(300, 340, 120, 30);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        frame.add(resetButton);

        frame.setVisible(true);
    }

    private void encryptMessage() {
        try {
            String message = messageField.getText();
            String key = keyField.getText();
            resultArea.setText(encode(key, message));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error encrypting message: " + e.getMessage());
        }
    }

    private void decryptMessage() {
        try {
            String encryptedMessage = encryptedMessageField.getText();
            String key = decryptionKeyField.getText();
            decryptedResultArea.setText(decode(key, encryptedMessage));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error decrypting message: " + e.getMessage());
        }
    }

    private void sendEmail() {
        String recipient = emailField.getText();
        String body = resultArea.getText();
        String sender = "your-email@mail.com";
        String password = "your-password";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Encrypted Message");
            message.setText(body);

            Transport.send(message);
            JOptionPane.showMessageDialog(frame, "Email sent successfully!");
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(frame, "Error sending email: " + e.getMessage());
        }
    }
    private void resetFields() {
        messageField.setText("");
        keyField.setText("");
        resultArea.setText("");
        encryptedMessageField.setText("");
        decryptionKeyField.setText("");
        decryptedResultArea.setText("");
        emailField.setText("");
    }

    private String encode(String key, String msg) {
        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char keyChar = key.charAt(i % key.length());
            char encChar = (char) ((msg.charAt(i) + keyChar) % 256);
            enc.append(encChar);
        }
        return Base64.getEncoder().encodeToString(enc.toString().getBytes());
    }

    private String decode(String key, String enc) {
        String decoded = new String(Base64.getDecoder().decode(enc));
        StringBuilder dec = new StringBuilder();
        for (int i = 0; i < decoded.length(); i++) {
            char keyChar = key.charAt(i % key.length());
            char decChar = (char) ((256 + decoded.charAt(i) - keyChar) % 256);
            dec.append(decChar);
        }
        return dec.toString();
    }
}
