package com.example.CSTMs.ticket.service;

import com.example.CSTMs.ticket.model.Ticket;
import com.example.CSTMs.ticket.repository.TicketRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Create a new ticket
    public Ticket createTicket(Ticket ticket) {
        // Check if a ticket with the same title exists within the last 24 hours
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twentyFourHoursAgo = now.minus(24, ChronoUnit.HOURS);

        List<Ticket> existingTickets = ticketRepository.findByTitleAndCreatedAtBetween(ticket.getTitle(),
                twentyFourHoursAgo, now);
        if (!existingTickets.isEmpty()) {
            throw new IllegalArgumentException("A ticket with this title already exists within the last 24 hours.");
        }

        // Set the created time
        ticket.setCreatedAt(now.toString()); // Or you could use LocalDateTime.toString()

        return ticketRepository.save(ticket);
    }

    // Get all tickets
    public Page<Ticket> getAllTickets(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ticketRepository.findAll(pageable);
    }

    // Get a ticket by ID
    public Optional<Ticket> getTicketById(String id) {
        return ticketRepository.findById(id);
    }

    // Update a ticket
    public Ticket updateTicket(String id, Ticket ticket) {
        if (ticketRepository.existsById(id)) {
            ticket.setId(id);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    // Delete a ticket
    public void deleteTicket(String id) {
        ticketRepository.deleteById(id);
    }

    // Fetch tickets by priority
    public Page<Ticket> getTicketsByPriority(String priority, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ticketRepository.findByPriority(priority, pageable);
    }

    // Fetch tickets by status (resolved, pending, closed)
    public Page<Ticket> getTicketsByStatus(String status, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ticketRepository.findByStatus(status, pageable);
    }   
}
