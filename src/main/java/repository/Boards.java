package repository;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "repository")
public class Boards {
    @Id
    @Column(name = "decemalNumber")
    String decimalNumber;

    @Column(name = "name")
    String name;

    public String getDecimalNumber() {
        return decimalNumber;
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
