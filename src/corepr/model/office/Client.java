package corepr.model.office;

import corepr.model.common.Passport;


public class Client extends User {

    public Client( String phone, Passport passport) {
        super(phone);
        this.passport = passport;
        userType = UserType.CLIENT;
    }

    public Client(String phone, Passport passport, String mail) {
        super(phone);
        this.passport = passport;
        this.mail = mail;
        userType = UserType.CLIENT;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {

        return mail;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }


    @Override
    public String toString() {
        return "Client{" +
                "passport=" + passport +
                ", mail='" + mail + '\'' +
                "phone_login=" + getLogin() +
                ", pass='" + getPassword() + '\'' +
                '}';
    }
}
