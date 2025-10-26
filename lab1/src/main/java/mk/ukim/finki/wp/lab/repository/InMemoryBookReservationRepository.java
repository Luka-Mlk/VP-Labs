package mk.ukim.finki.wp.lab.repository;

import org.springframework.stereotype.Repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookReservation;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository{
   public BookReservation save(BookReservation reservation) {
       DataHolder.reservations.removeIf(c -> c.getBookTitle().equals(reservation.getBookTitle()));
       DataHolder.reservations.add(reservation);
       return null;
   };
}
