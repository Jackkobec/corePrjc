package corepr.view.frame;
//JOptionpane - form to post

import corepr.controller.interfaces.IClientController;
import corepr.model.office.Client;
import corepr.model.office.PostTicket;
import corepr.model.office.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 */
public class ClientView extends JFrame {

    private IClientController clientController;
    private User user;

    private JButton addTicketButton;
    private JButton showInfo;
    private JButton cancelOrder;
    private JButton takeProductButton;
    private JButton showOffices;
    private JTextField clientsInfo;

    private DefaultListModel ticketListModel = new DefaultListModel();
    private JList ticketList = new JList(ticketListModel);

    public ClientView(IClientController clientController, User user) throws HeadlessException {
        this.user = user;
        this.clientController = clientController;

        setTitle("Client Menu");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        setVisible(true);

    }

    void init() {

        setLayout(new GridLayout(6, 2));
        addTicketButton = new JButton("Add Ticket");
        showInfo = new JButton("Show Info");
        cancelOrder = new JButton("Cancel Order");
        takeProductButton = new JButton("Take Product");
        showOffices = new JButton("Show All Offices");
        clientsInfo = new JTextField(clientController.toString());

        List<PostTicket> postTicketList = clientController.showAllClientTickets(
                new Client(user.getPhone(), user.getPassport()));

        for (PostTicket postTicket : postTicketList) {
            ticketListModel.addElement(postTicket);
        }
        getContentPane().add(ticketList);

        JButton addTicket = new JButton("Add Ticket");
        addTicket.setMnemonic('O');
        addTicket.setSize(2, 3);
        addTicket.setLayout(new GridLayout(1, 2));
        addTicket.setToolTipText("pres to show info");
        addTicket.addActionListener(new MyActionListener());
        getContentPane().add(addTicket);


        JButton showInfo = new JButton("showInfo");
        showInfo.setMnemonic('O');
        showInfo.setSize(2, 3);
        showInfo.setLayout(new GridLayout(1, 2));
        showInfo.setToolTipText("showInfo");
        showInfo.addActionListener(new MyActionListener());
        getContentPane().add(showInfo);


        JButton cancelOrder = new JButton("cancelOrder");
        cancelOrder.setMnemonic('O');
        cancelOrder.setSize(2, 3);
        cancelOrder.setLayout(new GridLayout(1, 2));
        cancelOrder.setToolTipText("pres to show info");
        cancelOrder.addActionListener(new MyActionListener());
        getContentPane().add(cancelOrder);


        JButton takeProduct = new JButton("takeProductButton");
        takeProduct.setMnemonic('O');
        takeProduct.setSize(2, 3);
        takeProduct.setLayout(new GridLayout(1, 2));
        takeProduct.setToolTipText("pres to show info");
        takeProduct.addActionListener(new MyActionListener());
        getContentPane().add(takeProduct);


        JButton clientsInfo = new JButton("clientsInfo");
        clientsInfo.setMnemonic('O');
        clientsInfo.setSize(2, 3);
        clientsInfo.setLayout(new GridLayout(1, 2));
        clientsInfo.setToolTipText("pres to show info");
        clientsInfo.addActionListener(new MyActionListener());
        getContentPane().add(clientsInfo);


    }


    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
