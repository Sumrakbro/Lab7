package Lab5.com.company;;


import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private String passportID; //Поле может быть null
    private long weight; //Значение поля должно быть больше 0

    public Person(long weight, String passportID) {
        if (!(weight < 0)) {
            this.weight = weight;
        } else throw new IllegalArgumentException("Значение weight должно быть >0");
        this.passportID = passportID;
    }

    public long getWeight() {
        return weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public Person(long weight) {
        if (!(weight < 0)) this.weight = weight;
        else throw new IllegalArgumentException("Значение height должно быть >0");
    }

    @Override
    public String toString() {
        return "Person{passportID: " + passportID +
                " weight: " + weight+"}";
    }
    public String PersonToSQL(){
        return "("+this.getPassportID()+","+this.getWeight()+")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return this.passportID.equals(person.passportID) ||
                this.weight == person.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportID, weight);
    }
}
