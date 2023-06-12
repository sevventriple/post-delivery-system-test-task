package com.example.postdeliverysystemtesttask.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADDRESS")
@NoArgsConstructor
@Setter
@Getter
public class Address {
    @Id
    @SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "ADDRESS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "REGION")
    String region;

    @Column(name = "CITY")
    String city;

    @Column(name = "DISTRICT")
    String district;

    @Column(name = "STREET")
    String street;

    @Column(name = "HOME_NUMBER")
    Integer homeNumber;
}
