package repository;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Boards")
public class Boards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    int id;

    @Column(name = "decimalNumber", unique = true, nullable = false)
    String decimalNumber;

    @Column(name = "name")
    String name;

    @Column(name = "designatedElements")
    String designatedElements;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "SupplyModules_Boards",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "SupplyModule_id"))
    private Set<SupplyModule> supplyModuleSet = new HashSet<>();

    public String getDesignatedElements() {
        return designatedElements;
    }

    public String getDecimalNumber() {
        return decimalNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setDesignatedElements(String designatedElements) {
        this.designatedElements = designatedElements;
    }

    public void setDecimalNumber(String decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SupplyModule> getSupplyModuleSet() {
        return supplyModuleSet;
    }

    public void addSupplyModule(SupplyModule supplyModule) {
        supplyModuleSet.add(supplyModule);
    }

    @Override
    public String toString() {
        return "Boards{" +
                "decimalNumber='" + decimalNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
