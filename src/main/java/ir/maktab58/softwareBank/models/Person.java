package ir.maktab58.softwareBank.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class Person {
    private @Getter @Setter String name;
    private @Getter @Setter List<Borrow> listOfBorrowed = new ArrayList<>();
    private @Getter @Setter int numOfLateDays = 0;
    private @Getter @Setter long fine;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int numOfLateDays) {
        this.name = name;
        this.numOfLateDays = numOfLateDays;
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
        if (latency > 7) {
            numOfLateDays += latency - 7;
        }
    }

    public void calculateFine(long finePerDay) {
        this.fine = finePerDay * numOfLateDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " : " + fine;
    }
}
