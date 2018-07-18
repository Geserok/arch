package repository;


import javax.persistence.*;

@Entity(name = "supplyModule")
@Table
public class SupplyModule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SupplyModule_id")
    int id;

    @Column(name = "SupplyModuleName")
    String name;

    @Column(name = "decimalNumber", unique = true, nullable = false)
    String decimalNumber;

    public void setName(String name) {
        this.name = name;
    }

    public void setDecimalNumber(String decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDecimalNumber() {
        return decimalNumber;
    }
}
