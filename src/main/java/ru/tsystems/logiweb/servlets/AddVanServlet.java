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

public class AddVanServlet extends HttpServlet {

    private VanService vanService = new VanServiceImpl();

    //TODO вообще не работает

    /**
     * Picks up dates from form "addVan.jsp" and create Entity Van.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcherManager = req.getRequestDispatcher("/main_manager.jsp");

        //TODO сделать проверки на входящие данныею Метод vanService.create(van) отдельно работает, а тут вообще игнор
        String vanNumber = req.getParameter("VanNumber");
        int driversAmount = 0;
        int capacity = 0;

        try {
            driversAmount = Integer.valueOf(req.getParameter("DriversAmount"));
            capacity = Integer.valueOf(req.getParameter("Capacity"));
        } catch (NumberFormatException e) {
            System.err.println(e);
        }

        Van van = new Van(vanNumber, driversAmount, capacity);
        vanService.create(van);
        /*Van van1 = vanService.getEntityByID(van.getIdVan());
        van1.setVanNumber(vanNumber);
        vanService.update(van1);*/

        requestDispatcherManager.forward(req, resp);
    }
}
