package com.agency.touristagency.country.town.address;

import com.agency.touristagency.country.town.Town;
import com.agency.touristagency.passenger.Passenger;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "address",catalog = "agency")
@NamedQueries(value = {
        @NamedQuery(name = "Address.findByName",query = "SELECT a FROM Address a WHERE a.name = :name")
})
public class Address implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    private String name;
    @JoinColumn(name = "id_town", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Town town;
    @OneToMany(mappedBy = "address")
    private List<Passenger> passengerList;

    public Address() {
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name+","+town;
    }
}



