package ir.maktab58.softwareBank.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author Taban Soleymani
 */
@ToString
public class Disc {
    private @Getter @Setter String name;
    private @Getter @Setter boolean isBorrowed;

    public Disc(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disc disc = (Disc) o;
        return name.equals(disc.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
