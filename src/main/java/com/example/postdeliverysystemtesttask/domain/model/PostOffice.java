package com.example.postdeliverysystemtesttask.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "POST_OFFICE")
@NoArgsConstructor
@Setter
@Getter
public class PostOffice {
    @Id
    @SequenceGenerator(name = "POST_OFFICE_SEQ", sequenceName = "POST_OFFICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_OFFICE_SEQ")
    @Column(name = "POST_INDEX")
    private Integer postIndex;

    @Column(name = "NAME")
    String name;

    @OneToOne
    @JoinColumn(name = "POSTAL_OFFICE_ADDRESS_ID")
    Address postalOfficeAddress;
}
