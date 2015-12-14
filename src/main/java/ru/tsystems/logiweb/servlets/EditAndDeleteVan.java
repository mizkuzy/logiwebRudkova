package ru.tsystems.logiweb.servlets;

import ru.tsystems.logiweb.service.API.VanService;
import ru.tsystems.logiweb.service.IMPL.VanServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditAndDeleteVan extends HttpServlet {

    VanService vanService = new VanServiceImpl();

    //TODO не знаю как передать какую именно Фуру я хочу редактировать, т.к. кнопка на всех строчках одна и та же висит
    //TODO наверное фуру лучше устанавливать в сессию и сразу перенаправлять в .*jsp
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("editVan.jsp");
        /*Van van = (Van) req.getSession().getAttribute("currentVan");

        vanService.update(van);
       req.getSession().setAttribute("currentVan", van);*/
        requestDispatcher.forward(req,resp);
    }

    //TODO не знаю как передать какую именно Фуру я хочу удалить, т.к. кнопка на всех строчках одна и та же висит
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcherManager = req.getRequestDispatcher("/main_manager.jsp");
        /*Van van = (Van) req.getSession().getAttribute("currentVan");
        vanService.delete(van.getIdVan());
        req.getSession().setAttribute("currentVan", van);*/
        requestDispatcherManager.forward(req,resp);

    }
}
