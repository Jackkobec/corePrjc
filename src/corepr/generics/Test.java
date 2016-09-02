package corepr.generics;

import corepr.model.office.Client;
import corepr.model.office.User;

import java.util.ArrayList;
import java.util.List;


public class Test {


    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>();

        test(clients);

    }

    public static void test(List<? extends User> list){
        User client = (Client) list.get(0);
        //list.add(new User(null,null));
        //list.add(new Client("sdf",new Passport("sdf","sdf")));
    }

}


