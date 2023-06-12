package com.example.postdeliverysystemtesttask.domain.model;

import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryStatus;
import com.example.postdeliverysystemtesttask.domain.enumeration.PostalItemType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "POSTAL_DELIVERY")
@NoArgsConstructor
@Setter
@Getter
public class PostalDelivery {
    @Id
    @SequenceGenerator(name = "POSTAL_DELIVERY_SEQ", sequenceName = "POSTAL_DELIVERY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTAL_DELIVERY_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "POSTAL_ITEM_TYPE")
    @Enumerated(EnumType.STRING)
    private PostalItemType postalItemType;

    @Column(name = "DELIVERY_STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Column(name = "ADDRESSEE_NAME")
    private String addresseeName;

    @OneToOne
    @JoinColumn(name = "ADDRESSEE_ADDRESS_ID")
    private Address addresseeAddress;

    @ManyToOne
    @JoinColumn(name = "TARGET_POST_OFFICE_ID")
    private PostOffice targetPostOffice;
}
