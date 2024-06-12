package org.example.customerservice.repository;

import org.example.customerservice.entity.CurrentRequestStatus;
import org.example.customerservice.entity.id.CurrentRequestStatusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrentRequestStatusesRepository extends JpaRepository<CurrentRequestStatus, CurrentRequestStatusId> {

    @Query(value = "select * from customers.customers.current_request_status c " +
            "where c.request_id=?1 " +
            "order by c.change_datetime desc " +
            "limit 1", nativeQuery = true)
    Optional<CurrentRequestStatus> findLatestStatusByRequestId(Long requestId);

    @Query(value = "select * from customers.customers.current_request_status c " +
            "where c.request_id=?1 " +
            "order by c.change_datetime", nativeQuery = true)
    List<CurrentRequestStatus> findAllStatuses(Long requestId);
}
