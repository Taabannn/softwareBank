package ir.maktab58.softwareBank.view;

import ir.maktab58.softwareBank.models.Admin;
import ir.maktab58.softwareBank.models.Disc;
import ir.maktab58.softwareBank.service.BankService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class SoftwareBankSys {
    Admin admin = new Admin();
    private @Getter @Setter BankService bankService;

    public void login() {
        System.out.println("********** Welcome **********");
        System.out.println("Please enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine().trim();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine().trim();
        if (admin.isUserAdmin(username, password)) {
            System.out.println("You've logged in ");
            showAdminMenu();
        } else {
            System.out.println("Sorry! you might have entered wrong username or password.\n" +
                               "Please try again.");
        }
    }

    public void showAdminMenu() {
        System.out.println("Please enter num of events and amount of fine per day then each event: ");
        Scanner scanner = new Scanner(System.in);
        initializeNumOfEventsAndPenalty(scanner);
        getEvents(bankService.getNumOfEvents(), scanner);
        boolean exit = false;
        while (!exit) {
            System.out.println("********** Admin Menu **********\n" +
                    "1) show fines\n" +
                    "2) show borrowed discs\n" +
                    "3) exit\n");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> showFines();
                case "2" -> showBorrowedDiscs();
                case "3" -> exit = true;
                default -> System.out.println("Invalid input! your choice must be an integer between 1 to 3.");
            }
        }
    }

    private void showBorrowedDiscs() {
        System.out.println("Borrowed Discs : ");
        bankService.getDiscs()
                    .stream()
                    .filter(Disc::isBorrowed)
                    .forEach(System.out::println);
    }

    private void showFines() {
        bankService.calculateFines();
        System.out.println("Fines : ");
        bankService.getMembers().forEach(System.out::println);
    }

    public void initializeNumOfEventsAndPenalty(Scanner scanner) {
        String firstLine = scanner.nextLine().trim();
        String[] firstLineOfInput = firstLine.split(" ");
        int numOfEvents =  Integer.parseInt(firstLineOfInput[0]);
        long penalty =  Long.parseLong(firstLineOfInput[1]);
        bankService = new BankService(numOfEvents, penalty);
    }

    public void getEvents(int numOfEvents, Scanner scanner) {
        List<String[]> eventsStr = new ArrayList<>();
        for (int i = 0; i < numOfEvents; i++) {
            String nextLine = scanner.nextLine();
            String[] tokens = nextLine.split(" ");
            eventsStr.add(tokens);
        }
        bankService.extractEvents(eventsStr);
    }
}
