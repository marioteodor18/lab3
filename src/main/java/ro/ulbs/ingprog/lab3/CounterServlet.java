package ro.ulbs.ingprog.lab3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CounterServlet", urlPatterns = {"/CounterServlet"})
public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        updateCounter(request, "CounterGetName", "WarningGetName");
        status(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        updateCounter(request, "CounterPostName", "WarningPostName");
        status(request, response);
    }

    private int parseValue(String value) {
        if (value == null) {
            return 0;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }


    private void updateCounter(HttpServletRequest request, String counterName, String warningName) {
        HttpSession session = request.getSession();

        Integer counter = (Integer) session.getAttribute(counterName);
        if (counter == null) {
            counter = 0;
        }

        counter += parseValue(request.getParameter("inc"));
        counter -= parseValue(request.getParameter("dec"));

        session.setAttribute(counterName, counter);

        if (counter == 0) {
            session.setAttribute(warningName, true);
        } else {
            session.removeAttribute(warningName);
        }
    }

    private void status(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h2>Welcome to Counter Servlet</h2>");

        Integer counterGet = (Integer) session.getAttribute("CounterGetName");
        printWriter.println("<br> Counter for GET: " + counterGet);
        if (session.getAttribute("WarningGetName") != null) {
            printWriter.println("<br><b style=\"color:red\">WARNING GET</b>");
        }

        Integer counterPost = (Integer) session.getAttribute("CounterPostName");
        printWriter.println("<br> Counter for POST: " + counterPost);
        if (session.getAttribute("WarningPostName") != null) {
            printWriter.println("<br><b style=\"color:red\">WARNING POST</b>");
        }

        printWriter.println("<br><br><a href=\"index.jsp\">Back</a>");
    }
}