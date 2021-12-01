package ir.maktab58.softwareBank.service;

import ir.maktab58.softwareBank.models.events.SoftwareBankEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class SoftwareBank {
    private final @Getter int penalty;
    private final @Getter int numOfEvents;
    private @Getter List<SoftwareBankEvent> events = new ArrayList<>();

    public SoftwareBank() {
        String[] numOfEventsPlusPenalty = initializePenaltyAndNumOfEvents();
        this.numOfEvents =  Integer.parseInt(numOfEventsPlusPenalty[0]);
        this.penalty =  Integer.parseInt(numOfEventsPlusPenalty[1]);
        getEvents();
    }

    private String[] initializePenaltyAndNumOfEvents() {
        Scanner scanner = new Scanner(System.in);
        String firstLineOfInput = scanner.nextLine().trim();
        return firstLineOfInput.split(" ");
    }

    private void getEvents() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numOfEvents; i++) {
            String nextLine = scanner.nextLine();
            String[] tokens = nextLine.split(" ");

        }
    }
}
