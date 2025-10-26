package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import javax.swing.*;
import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookReservationService bookReservationService;

    public BookReservationServlet(SpringTemplateEngine springTemplateEngine, BookReservationService bookReservationService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookReservationService = bookReservationService;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, RuntimeException {
        String bookTitle = req.getParameter("title");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        String numCopies = req.getParameter("numCopies");
        String userIp = req.getRemoteAddr();
        try {
            bookReservationService.placeReservation(bookTitle, readerName, readerAddress, Integer.parseInt(numCopies));
        } catch (IllegalArgumentException e) {
            // resp.sendRedirect("/servlet/category?errorMessage=Invalid input for category");
            return;
        }

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        System.out.println(req.getParameter("title"));
        BookReservation reservation = new BookReservation(bookTitle, readerName, readerAddress, Long.parseLong(numCopies));
        context.setVariable("reservation", reservation);
        context.setVariable("userIp", userIp);

        springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }
}
