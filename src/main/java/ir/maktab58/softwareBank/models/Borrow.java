package ir.maktab58.softwareBank.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class Borrow {
    private @Getter @Setter Disc disc;
    private @Getter @Setter Date borrowingDate;
    private @Getter @Setter Date deliveryDate = null;

    public Borrow(Disc disc, Date borrowingDate) {
        this.disc = disc;
        this.disc.setBorrowed(true);
        this.borrowingDate = borrowingDate;
    }

    public boolean isLate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
        if (deliveryDate.compareTo(borrowingDate) > 7) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return disc.equals(borrow.disc) && borrowingDate.equals(borrow.borrowingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disc, borrowingDate);
    }
}
