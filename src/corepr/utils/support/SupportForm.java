package corepr.utils.support;

import corepr.utils.email.model_letter.Letter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;


public class SupportForm extends JFrame {

    private ISupportController controller;
    private DefaultListModel letterListModel = new DefaultListModel();
    private JList mailList = new JList(letterListModel);

    public SupportForm(ISupportController supportController) {

        controller = supportController;

        setTitle("Support");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    void init() {
        initEmailList();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(mailList);

        JLabel mailsLabel = new JLabel("e-mail list");

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initEmailList();
            }
        });

        JPanel mailListPanel = new JPanel(new BorderLayout());
        mailListPanel.add(scrollPane, BorderLayout.CENTER);
        mailListPanel.add(refresh, BorderLayout.SOUTH);

        JPanel main = new JPanel(new BorderLayout());
        main.add(mailsLabel, BorderLayout.NORTH);
        main.add(mailListPanel, BorderLayout.CENTER);

        getContentPane().add(main);
    }

    public void initEmailList() {
        List<Letter> emailList = null;
        try {
            emailList = controller.getNewEmails();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mailList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    new LetterContent((Letter) mailList.getSelectedValue(), controller);
                }
            }
        });

        for (Letter letter : emailList) {
            letterListModel.addElement(letter);
        }
    }
}
