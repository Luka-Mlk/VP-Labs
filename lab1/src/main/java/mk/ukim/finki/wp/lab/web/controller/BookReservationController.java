package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {

    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping
    public String placeReservation(
            @RequestParam String bookId,
            @RequestParam Integer numCopies,
            @RequestParam String readerName,
            @RequestParam String readerAddress,
            HttpServletRequest request,
            Model model
    ) {
        String userIp = request.getRemoteAddr();
        try {
            BookReservation reservation = bookReservationService.placeReservation(
                    bookId,
                    readerName,
                    readerAddress,
                    numCopies
            );
            model.addAttribute("reservation", reservation);
            model.addAttribute("userIp", userIp);
            return "reservationConfirmation";

        } catch (IllegalArgumentException e) {
            return "redirect:/books?error=InvalidReservationData";
        }
    }
}
