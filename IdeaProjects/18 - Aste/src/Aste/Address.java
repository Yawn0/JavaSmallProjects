package Aste;

import java.io.Serial;
import java.io.Serializable;

public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public String street;
    public String country;
    public String prova;

    public Address(String street, String country, String prova) {
        this.street = street;
        this.country = country;
        this.prova = prova;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProva(String prova) {
        this.prova = prova;
    }

    public String toString(){
        return " Street : " +
                this.street +
                "Country : " +
                this.country;
    }

    public boolean equals(Object o){
        if(!(o instanceof Address)) return false;
        if(this == o) return true;
        Address a = (Address) o;
        return (a.street.equals(street) && a.country.equals(country) && a.prova.equals(prova));
    }
}



