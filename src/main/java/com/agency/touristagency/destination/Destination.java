package com.agency.touristagency.destination;

import com.agency.touristagency.destination.destination_detail.DestinationDetail;
import com.agency.touristagency.specialOffer.SpecialOffer;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name="destination",catalog = "agency")
@NamedQueries({
        @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d")
})
public class Destination implements Serializable {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        private Long id;
        @Basic(optional = false)
        private String nameOfDestination;
        @Basic(optional = false)
        private String city;
        @Basic(optional = false)
        private double price;
        @Basic(optional = false)
        private LocalDate departureDate;
        @Basic(optional = false)
        private LocalDate returnDate;
        @OneToMany(mappedBy = "destination")
        private List<DestinationDetail> destinationDetailList;
        @OneToMany(mappedBy = "destination")
        private List <SpecialOffer> specialOfferList;

        public Destination() {
        }

        public List<DestinationDetail> getDestinationDetailList() {
            return destinationDetailList;
        }

        public void setDestinationDetailList(List<DestinationDetail> destinationDetailList) {
            this.destinationDetailList = destinationDetailList;
        }

    public List<SpecialOffer> getSpecialOfferList() {
        return specialOfferList;
    }

    public void setSpecialOfferList(List<SpecialOffer> specialOfferList) {
        this.specialOfferList = specialOfferList;
    }

    public String getNameOfDestination() {
            return nameOfDestination;
        }

        public void setNameOfDestination (String nameOfDestination) {
            this.nameOfDestination = nameOfDestination;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public LocalDate getDepartureDate() {
            return departureDate;
        }

        public void setDepartureDate(LocalDate departureDate) {
            this.departureDate = departureDate;
        }
        public LocalDate getReturnDate() {
        return returnDate;
        }

        public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Destination destination = (Destination) o;
            return Objects.equals(id, destination.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return nameOfDestination+", "+city;
        }

    }


