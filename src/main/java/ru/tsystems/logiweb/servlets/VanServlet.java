package ru.tsystems.logiweb.servlets;

import ru.tsystems.logiweb.entities.Van;
import ru.tsystems.logiweb.service.API.VanService;
import ru.tsystems.logiweb.service.IMPL.VanServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class VanServlet extends HttpServlet {

    private VanService vanService = new VanServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("vans.jsp");

        //TODO NulEx может быть если нет фур
        ArrayList<Van> vansList = (ArrayList<Van>) vanService.getAll();

        req.getSession().setAttribute("vansList", vansList);
        requestDispatcher.forward(req, resp);
    }
}
