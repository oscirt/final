package org.example.customerservice.repository;

import org.example.customerservice.entity.CurrentRequestStatus;
import org.example.customerservice.entity.id.CurrentRequestStatusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrentRequestStatusesRepository extends JpaRepository<CurrentRequestStatus, CurrentRequestStatusId> {

    Optional<CurrentRequestStatus> findByRequestIdOrderByChangeDatetimeDesc(Long requestId);
    List<CurrentRequestStatus> findAllByRequestId(Long requestId);
}
