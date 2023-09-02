package com.agency.touristagency.specialOffer;

import com.agency.touristagency.destination.Destination;
import jakarta.persistence.*;

import java.io.Serializable;

import java.util.Objects;

    @Entity
    @Table(name = "special_offer",catalog = "agency")
    @NamedQueries(value = {
            @NamedQuery(name = "SpecialOffer.findByNameOfOffer",query = "SELECT s FROM SpecialOffer s  WHERE s.nameOfOffer = :specialOffer")
    })
    public class SpecialOffer implements Serializable {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        private Long id;
        @JoinColumn(name = "id_destination", referencedColumnName = "id")
        @ManyToOne(optional = false)
        private Destination destination;

        @Basic(optional = false)
        @Column(name = "name_of_offer")
        private String nameOfOffer;

        @Basic(optional = false)
        @Column(name = "price")
        private Double price;


        public SpecialOffer() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Destination getDestination() {
            return destination;
        }

        public void setDestination(Destination destination) {
            this.destination = destination;
        }

        public String getNameOfOffer() {
            return nameOfOffer;
        }

        public void setNameOfOffer(String nameOfOffer) {
            this.nameOfOffer = nameOfOffer;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpecialOffer specialOffer = (SpecialOffer) o;
            return Objects.equals(id, specialOffer.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Destinacija: "+destination.getNameOfDestination()+"-"+destination.getCity()+",      Cijena: "+ destination.getPrice()+",     Datum polaska: "+ destination.getDepartureDate()+",     Datum povratka: "+ destination.getReturnDate();        }
    }

