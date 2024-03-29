package com.autoviolation.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Модель данных - правонарушения.
 * -@OneToMany() - потому-что в одном Ассидент будет много Rule
 *- @OneToMany(cascade = CascadeType.ALL - в случае удаления/добавления каскадом
 * , mappedBy = "accident")
 * - т.е эту связь ищи в поле Rule class private Accident accident -> @ManyToOne связанный с этим
 */
@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE}, mappedBy = "accident")
    private AccidentType type;

    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE}, mappedBy = "accident",
    fetch = FetchType.EAGER)
    private Set<Rule> rules = new HashSet<>();

    public Accident() {
    }

    public Accident(String name, String text, String address) {
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public static Accident of(int id, String name, String text, String address) {
        Accident accident = new Accident();
        accident.id = id;
        accident.name = name;
        accident.text = text;
        accident.address = address;
        return accident;
    }

    public void addRuleToAccident(Rule rule) {
        rules.add(rule);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Accident: id=%s, name=%s, text=%s, address=%s, AccidentType=%s, rules=%s",
                id, name, text, address, type, rules);
    }
}
