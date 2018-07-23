package repository;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SupplyModule")
public class SupplyModule {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "SupplyModule_id")
    int id;

    @Column(name = "SupplyModuleName")
    String name;

    @Column(name = "decimalNumber", unique = true, nullable = false)
    String decimalNumber;

    @Column(name = "includedElements")
    String includedElements;


    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Boards> boardsSet;

    public SupplyModule() {
        this.boardsSet = new HashSet<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDecimalNumber(String decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public void setIncludedElements(String includedElements) {
        this.includedElements = includedElements;
    }

    public void setBoardsSet(Set<Boards> boardsSet) {
        this.boardsSet = boardsSet;
    }

    public String getIncludedElements() {
        return includedElements;
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

    public Set<Boards> getBoardsSet() {
        return boardsSet;
    }

    public void addBoards(Boards boards) {
        boardsSet.add(boards);
    }

}
