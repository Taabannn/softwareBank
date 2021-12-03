package ir.maktab58.softwareBank.view;

import ir.maktab58.softwareBank.service.BankService;

/**
 * @author Taban Soleymani
 */
public class Main {
    public static void main(String[] args) {
        try {
            SoftwareBankSys softwareBankSys = new SoftwareBankSys();
            softwareBankSys.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
