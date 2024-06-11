package org.example.customerservice.repository;

import org.example.customerservice.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestStatusesRepository extends JpaRepository<RequestStatus, Integer> {

}
