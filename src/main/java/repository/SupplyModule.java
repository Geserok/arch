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
    private int id;

    @Column(name = "SupplyModuleName")
    private String name;

    @Column(name = "decimalNumber", unique = true, nullable = false)
    private String decimalNumber;

    @Column(name = "includedElements")
    private String includedElements;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Boards> boardsSet;

    SupplyModule() {
        this.boardsSet = new HashSet<>();
    }

    void setName(String name) {
        this.name = name;
    }

    void setDecimalNumber(String decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    void setIncludedElements(String includedElements) {
        this.includedElements = includedElements;
    }

    void setBoardsSet(Set<Boards> boardsSet) {
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
