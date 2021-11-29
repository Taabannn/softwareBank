package ir.maktab58.softwareBank.models;

import lombok.*;

/**
 * @author Taban Soleymani
 */
@AllArgsConstructor
@EqualsAndHashCode
public class Date implements Comparable<Date> {
    private @Getter @Setter int day;
    private @Getter @Setter int month;
    private @Getter @Setter int year;

    public static boolean validateDate(int year, int month, int day) {
        if (month <= 0 || month > 12)
            throw new IllegalArgumentException("Wrong value for month, it must be from 1 to 12");
        if (day <= 0 || day > 32)
            throw new IllegalArgumentException("Wrong value for day, it must be from 1 to 31");
        if (year < 1111 || year > 9999)
            throw new IllegalArgumentException("Wrong value for Year");
        if (month >= 7 && month < 12 && day == 31)
            throw new IllegalArgumentException("Wrong value for day. in second half of year, months have less than 31 days");
        if (month == 12 && day == 30)
            throw new IllegalArgumentException("Wrong value for day. it is supposed that 12th month just has 29 days");
        return true;
    }

    @Override
    public int compareTo(Date o) {
        return ((year - o.year) * 365 +
                calcSpentDaysTillMonth(month) - calcSpentDaysTillMonth(o.month) +
                day - o.day);
    }

    private int calcSpentDaysTillMonth(int month) {
        switch (month) {
            case 1 -> {return 0;}
            case 2 -> {return 31;}
            case 3 -> {return 2 * 31;}
            case 4 -> {return 3 * 31;}
            case 5 -> {return 4 * 31;}
            case 6 -> {return 5 * 31;}
            case 7 -> {return 6 * 31;}
            case 8 -> {return (6 * 31 + 30);}
            case 9 -> {return (6 * 31 + 2 * 30);}
            case 10 -> {return (6 * 31 + 3 * 30);}
            case 11 -> {return (6 * 31 + 4 * 30);}
            case 12 -> {return (6 * 31 + 5 * 30);}
            default -> throw new IllegalArgumentException("Wrong value for month, it must be from 1 to 12");
        }
    }

    private String getNameOfMonth(int month) {
        switch (month) {
            case 1 -> {return "Farvardin";}
            case 2 -> {return "Ordibehesht";}
            case 3 -> {return "Khordad";}
            case 4 -> {return "Tir";}
            case 5 -> {return "Mordad";}
            case 6 -> {return "Shahrivar";}
            case 7 -> {return "Mehr";}
            case 8 -> {return "Aaban";}
            case 9 -> {return "Aazar";}
            case 10 -> {return "Dey";}
            case 11 -> {return "Bahman";}
            case 12 -> {return "Esfand";}
            default -> throw new IllegalArgumentException("Wrong value for month, it must be from 1 to 12");
        }
    }

    @Override
    public String toString() {
        return "Date{" + day + " " + getNameOfMonth(month) + " " + year + '}';
    }
}
