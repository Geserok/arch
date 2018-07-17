package repository;


import javax.persistence.*;

@Entity(name = "boards")
@Table
public class Boards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    int id;
    @Column(name = "decimalNumber", unique = true, nullable = false)
    String decimalNumber;

    @Column(name = "name")
    String name;

    public String getDecimalNumber() {
        return decimalNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setDecimalNumber(String decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Boards{" +
                "decimalNumber='" + decimalNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
