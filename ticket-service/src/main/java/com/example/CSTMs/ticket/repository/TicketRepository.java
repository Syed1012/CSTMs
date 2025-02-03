package com.example.CSTMs.ticket.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.CSTMs.ticket.model.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
    // Find tickets by title and created date range
    List<Ticket> findByTitleAndCreatedAtBetween(String title, LocalDateTime startDate, LocalDateTime endDate);

    // Find tickets by priority
    Page<Ticket> findByPriority(String priority, Pageable pageable);

    //Find tickets by status
    Page<Ticket> findByStatus(String status, Pageable pageable);
}
