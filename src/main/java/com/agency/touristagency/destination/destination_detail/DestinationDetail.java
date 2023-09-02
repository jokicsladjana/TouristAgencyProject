package com.agency.touristagency.destination.destination_detail;

import com.agency.touristagency.destination.Destination;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "destination_details",catalog = "agency")
public class DestinationDetail implements Serializable {
    @Id
    @JoinColumn(name = "id_destination", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Destination destination;
    @Basic(optional = false)
    private int numberOfSeats;

    @Basic(optional = false)
    private String roadGuide;

    @Basic(optional = false)
    @Column(name = "about_the_trip")
    private String aboutTheTrip;

    public DestinationDetail() {
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getRoadGuide() {
        return roadGuide;
    }

    public void setRoadGuide(String roadGuide) {
        this.roadGuide = roadGuide;
    }

    public String getAboutTheTrip() {
        return aboutTheTrip;
    }

    public void setAboutTheTrip(String aboutTheTrip) {
        this.aboutTheTrip = aboutTheTrip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestinationDetail destinationDetail = (DestinationDetail) o;
        return Objects.equals(destination, destinationDetail.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination);
    }

    @Override
    public String toString() {
        return "Destinacija: "+destination.getNameOfDestination()+"-"+destination.getCity()+",      Cijena: "+ destination.getPrice()+",     Datum polaska: "+ destination.getDepartureDate()+",     Datum povratka: "+ destination.getReturnDate();
    }
}



