package ru.tsystems.logiweb.servlets;

//TODO DELETE THIS CLASS
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.service.API.DriverService;
import ru.tsystems.logiweb.service.IMPL.DriverServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class DriverServlet extends HttpServlet {

    DriverService driverService = new DriverServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("drivers.jsp");

        ArrayList<Driver> drivers = (ArrayList<Driver>) driverService.getAll();

        req.getSession().setAttribute("drivers", drivers);

        requestDispatcher.forward(req, resp);

    }
}
