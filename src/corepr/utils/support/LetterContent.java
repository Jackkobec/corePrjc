package corepr.utils.support;

import corepr.utils.email.model_letter.Letter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LetterContent extends JFrame {
    private Letter letter;
    private ISupportController controller;
    JTextArea mailContent = new JTextArea();

    public LetterContent(Letter letter, ISupportController controller){
        this.controller = controller;
        setTitle("Mail content");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.letter = letter;
        init(letter);
        setVisible(true);
    }

    private void init(Letter letter){
        mailContent.setText(letter.getMessage());
        JButton reply = new JButton("Reply");
        reply.addActionListener(new ReplyActionListener());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(mailContent);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(reply, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private class ReplyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.sendEmail(letter.getFromName(), mailContent.getText());
            dispose();
        }
    }
}
