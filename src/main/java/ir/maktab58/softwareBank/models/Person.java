package ir.maktab58.softwareBank.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Taban Soleymani
 */
public class Person {
    private @Getter @Setter String name;
    private @Getter @Setter List<Borrow> listOfBorrowed = new ArrayList<>();
    private @Getter @Setter int numOfLateDays = 0;

    public Person(String name) {
        this.name = name;
    }

    public int getLateDays() {
        return numOfLateDays;
    }

    public void borrow(Disc disc, Date borrowingDate) {
        Borrow borrowed = new Borrow(disc, borrowingDate);
        if (!listOfBorrowed.contains(borrowed))
            listOfBorrowed.add(borrowed);
    }

    public void deliver(Disc disc, Date deliveryDate) {
        int latency = 0;
        for (Borrow borrow : listOfBorrowed) {
            if (borrow.getDisc().equals(disc) && borrow.getDeliveryDate() == null) {
                if (borrow.isLate(deliveryDate)) {
                    latency = deliveryDate.compareTo(borrow.getBorrowingDate());
                }
            }
        }
        Predicate<Borrow> borrowPredicate = borrow -> borrow.getDisc().equals(disc);
        listOfBorrowed.forEach(borrowed -> {
            listOfBorrowed.removeIf(borrowPredicate);
        });
        if (latency > 7) {
            numOfLateDays += latency - 7;
        }
    }
}
