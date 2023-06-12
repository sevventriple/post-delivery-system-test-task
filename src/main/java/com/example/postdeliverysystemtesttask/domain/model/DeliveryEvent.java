package com.example.postdeliverysystemtesttask.domain.model;

import com.example.postdeliverysystemtesttask.domain.enumeration.DeliveryEventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "DELIVERY_EVENT")
@NoArgsConstructor
@Setter
@Getter
public class DeliveryEvent {
    @Id
    @SequenceGenerator(name = "DELIVERY_EVENT_SEQ", sequenceName = "DELIVERY_EVENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DELIVERY_EVENT_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "DELIVERY_EVENT_TYPE")
    @Enumerated(value = EnumType.STRING)
    private DeliveryEventType deliveryEventType;

    @Column(name = "TIME")
    private ZonedDateTime time;

    @ManyToOne
    @JoinColumn(name = "POST_OFFICE_ID")
    private PostOffice postOffice;

    @ManyToOne
    @JoinColumn(name = "POSTAL_DELIVERY_ID")
    private PostalDelivery postalDelivery;
}
