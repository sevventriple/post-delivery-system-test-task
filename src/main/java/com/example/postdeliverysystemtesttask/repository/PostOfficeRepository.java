package com.example.postdeliverysystemtesttask.repository;

import com.example.postdeliverysystemtesttask.domain.model.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Integer> {
}
