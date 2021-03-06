package corepr.view.console;


import corepr.controller.interfaces.*;
import corepr.exceptions.LogException;
import corepr.exceptions.AppException;

import corepr.exceptions.ValidationException;
import corepr.model.common.Address;
import corepr.model.common.Passport;
import corepr.model.common.Product;
import corepr.model.common.Size;

import corepr.model.office.Client;
import corepr.model.office.PostTicket;
import corepr.utils.logging.LogContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Menu {
    protected static IClientController clientController;
    protected static IManagerController managerController;
    protected static IEmployeeManagement employeeManagement;
    protected static IMoneyController moneyController;
    protected static IPostController postController;

    protected static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
Menu menu  = new Menu();

        menu.start(clientController, managerController, moneyController, employeeManagement, postController);
    }
    public void start(IClientController controller, IManagerController managerController,
                      IMoneyController moneyController, IEmployeeManagement employeeManagement, IPostController postController)
                                {
        clientController = controller;
        this.managerController = managerController;
        this.moneyController = moneyController;
        this.employeeManagement = employeeManagement;
        this.postController = postController;
        scanner.useDelimiter("\\n");

        chooseUser();
     }


    protected void chooseUser() {
        System.out.printf("For clients choose: 1\nFor manager choose: 2\nFor director choose 3\n");
        int user = scanner.nextInt();
        switch (user) {
            case 1:
                MenuClient menuClient = new MenuClient();
                clientEnter();
                menuClient.clientMenuRun();
                break;
            case 2:
               while (true) {
                    System.out.println("Log in:");
                    String managerLog = scanner.next();
                    // Log in validation
                   MenuManager menuManager = new MenuManager();
                    menuManager.managerMenuRun();
                   break;
               }
            case 3:
                System.out.println("Log in:");
                String directorLog = scanner.next();
                // Log in validation
               MenuDirector menuDirector = new MenuDirector();
                menuDirector.directorMenuRun();
                break;
        }
    }

    protected void showTakeProductMenu()  {
        System.out.println("Input ticket ID");
        String ticketId;
        ticketId = scanner.next();
        Product product = null;
        try {
            product = clientController.takeProduct(Integer.parseInt(ticketId));
            System.out.println(product.toString());
        } catch (AppException e) {
            e.printStackTrace();
        }

    }

    protected void showGetClientMenu() {
        System.out.println("Input clients phone");
        String phone;
        phone = scanner.next();
        try {
            Client client = managerController.getClient(phone);
            if (client != null) {
                System.out.println(client.toString());
            } else {
                System.out.println("Client was not found according to inputted phone number.");
            }
        } catch (AppException ex){
            System.out.println(ex.getMessage());
        }
    }

    protected  void showTicketByClientPhoneMenu() {
        System.out.println("Input clients phone");
        String phone;
        phone = scanner.next();
        PostTicket postTicket = null;
        try {
            List<PostTicket> postTicketList= managerController.showTicketByClientPhone(phone);
            for(PostTicket p : postTicketList) {
                System.out.println(postTicket.toString());
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    protected void showAddClientMenu() {

            String fullName = fullNameInput();
            String passportNum = passportInput();
            String phone = phoneInput();

            Passport passport = new Passport(fullName, passportNum);
        try {
            Client client = managerController.addClient(passport, phone);
            System.out.println("Client added");
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    protected void showAllLogs()  {
        System.out.println("Show all logs:\n");
        try {
            LogContainer.showAllLogs();
        } catch (LogException ex){
            System.out.println(ex.getMessage());
        }
    }


    protected void showCancelTicketMenu() {

        System.out.println("Cancel: input product Id to cancel");
        String productId;
        productId = scanner.next();
        int prodId = Integer.parseInt(productId);

        try{
            if (clientController.cancelTicket(prodId)) {
                System.out.println("Your order is canceled");
            } else {
                System.out.println("There is no order according to inputted Id.");
            }
        } catch (NumberFormatException ex){
            System.out.println("Inputted order Id is not numeric.");
        } catch (AppException ex){
            System.out.println(ex.getMessage());
        }
    }

    protected void showAddTicketMenu() {
        System.out.println("Create a client:");
        String clientPhone = phoneInput();
        String clientFullName = fullNameInput();
        String clientPassportNumber = passportInput();
        String clientEmail = emailInput();

        Client client = new Client(clientPhone, new Passport(clientFullName, clientPassportNumber),clientEmail);

        System.out.println("Address destination creation: input city ");
        String addressToCity;
        addressToCity = scanner.next();
        System.out.println("Address destination creation: input street ");
        String addressToStreet;
        addressToStreet = scanner.next();
        String addressToHouseNum;
        while (true) {
            System.out.println("Address destination creation: input house number ");
            addressToHouseNum = scanner.next();
            int num;
            try{
                num = Integer.parseInt(addressToHouseNum);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Inputted value is not numeric.");
            }
        }

        Address addrTo = new Address(addressToCity, addressToStreet, addressToHouseNum);

        System.out.println("Product creation: input product name(from 2 to 20 symbols) ");
        String productName;
        productName = scanner.next();
        System.out.println("Product creation: input product length ");
        String productLength;
        productLength = scanner.next();
        System.out.println("Product creation: input product width ");
        String productWidth;
        productWidth = scanner.next();
        System.out.println("Product creation: input product height ");
        String productHeight;
        productHeight = scanner.next();
        System.out.println("Product creation: input product weight ");
        String productWeight;
        productWeight = scanner.next();
        System.out.println("Product creation: input product price ");
        String productPrice;
        productPrice = scanner.next();

        Product product1 = new Product(productName,
                new Size(Integer.parseInt(productLength),
                        Integer.parseInt(productWidth),
                        Integer.parseInt(productHeight),
                        Integer.parseInt(productWeight)),
                Integer.parseInt(productPrice),
                client);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);

        try {
            PostTicket postTicket = clientController.makeOrder(client, addrTo, productList);
            System.out.println("Post ticket id is " + postTicket.getId());
        } catch (AppException e) {
            System.out.println(e.getMessage());
        }
    }


    protected void clientEnter() {
        while (true) {
//            System.out.println("Input: 1.I am already have account in Art Post ");
//            System.out.println("Input: 2. I am a new user "); //for receivers
//            int userAnswer = scanner.nextInt();
//            if (userAnswer != 1 && userAnswer != 2) System.out.println("Incorrect input");
//            if (userAnswer == 1) {
            System.out.println("Enter your login");
            String userLog = scanner.next();
            System.out.println("Enter your password");
            String userPass = scanner.next();
            break;
            //validation
            // if wrong System.out.println("Wrong login or password")

        }
    }

    protected void showAllPostOffices(){
        postController.showOfficesOnMap();
    }

    protected String fullNameInput() {
        String fullName;
        while (true) {
            System.out.println("Input first name and second name ");
            fullName = scanner.next();
            if (fullName.isEmpty()) {
                System.out.println("Incorrect data: inputted value is empty.");
            } else {
                break;
            }
        }
        return fullName;
    }

    protected String passportInput() {
        String passportNumber;
        while (true) {
            System.out.println("Input  passport number in format DF908754(without spaces) ");
            //passportNumber = String.valueOf(scanner.useDelimiter("\\n"));
            scanner.useDelimiter("\\n");
            passportNumber = scanner.next();
            if (passportNumber.isEmpty() || (passportNumber.length() != 8) || passportNumber.contains(" ")) {
                System.out.println("Incorrect data: either passport number is empty or length is greater than " +
                        "8 characters or contains spaces..");
            } else {
                break;
            }
        }
        return passportNumber;
    }

    protected String phoneInput() {
        String phone;
        while (true) {
            System.out.println("Input phone in format: +380935075645 (without spaces)");
            phone = scanner.next();
            if ((phone.length() > 13) || (!(phone.contains("+380")))) {
                System.out.println("Incorrect data: either number length is greater than " +
                        "13 characters or number does not contain \"+380\".");
            } else {
                break;
            }
        }
        return phone;
    }
    private String emailInput() {
        String email;
        while(true){
            System.out.println("Input client email: ");
            email = scanner.next();
            if(!email.contains("@")){
                System.out.println("Incorrect email address ");
            }else{
                break;
            }
        }
        return email;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public static IClientController getClientController() {
        return clientController;
    }

    public static IManagerController getManagerController() {
        return managerController;
    }

    public static IEmployeeManagement getEmployeeManagement() {
        return employeeManagement;
    }

    public static IMoneyController getMoneyController() {
        return moneyController;
    }

    public static IPostController getPostController() {
        return postController;
    }
}
