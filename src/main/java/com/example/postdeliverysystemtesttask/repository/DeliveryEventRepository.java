package com.example.postdeliverysystemtesttask.repository;

import com.example.postdeliverysystemtesttask.domain.model.DeliveryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryEventRepository extends JpaRepository<DeliveryEvent, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM DELIVERY_EVENT WHERE postal_delivery_id = :delivery_id")
    List<DeliveryEvent> getDeliveryEventsByDeliveryId(@Param("delivery_id") Long deliveryId);
}
