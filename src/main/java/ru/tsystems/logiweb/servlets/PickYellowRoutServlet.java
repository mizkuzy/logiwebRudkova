package ru.tsystems.logiweb.servlets;

import ru.tsystems.logiweb.entities.Good;
import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.entities.Request;
import ru.tsystems.logiweb.entities.Van;
import ru.tsystems.logiweb.entities.statusesAndStates.OrderStatus;
import ru.tsystems.logiweb.entities.statusesAndStates.RequestStatus;
import ru.tsystems.logiweb.service.API.OrderService;
import ru.tsystems.logiweb.service.API.RequestService;
import ru.tsystems.logiweb.service.API.VanService;
import ru.tsystems.logiweb.service.IMPL.OrderServiceImpl;
import ru.tsystems.logiweb.service.IMPL.RequestServiceImpl;
import ru.tsystems.logiweb.service.IMPL.VanServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PickYellowRoutServlet extends HttpServlet {

    OrderService orderService = new OrderServiceImpl();
    VanService vanService = new VanServiceImpl();
    RequestService requestService = new RequestServiceImpl();

    //TODO метод не доделан
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("create_order.jsp");
        /*
        1. Создать новый заказ
        2. Установить статус "В работе"
         */
        Order order = new Order();
        //order.setNumber();//TODO создать механизм присваивания номеров
        req.getSession().setAttribute("orderNumber", order.getNumber());
        order.setStatus(OrderStatus.PROCESS);

        List<Request> requestsWithYellowRout = (List<Request>) req.getSession().getAttribute("yellowRoutRequests");

        order.addRequest(requestsWithYellowRout.get(0));

        List<Good> goods = new ArrayList<Good>();
        List<String> cities1 = new ArrayList<String>();
        List<String> cities2 = new ArrayList<String>();
        for (Request request : requestsWithYellowRout) {
            goods.add(request.getGoodForRequest());
            cities1.add(request.getRoutForRequest().getCity1());
            cities2.add(request.getRoutForRequest().getCity2());
        }

        Good good = goods.get(0);
        req.getSession().setAttribute("good", good.getName());
        String city1 = cities1.get(0);
        req.getSession().setAttribute("city1", city1);
        String city2 = cities2.get(0);
        req.getSession().setAttribute("city2", city2);

        int mass = good.getMass();
        req.getSession().setAttribute("mass", mass);

        /*for (Good g : goods) {
            mass += g.getMass();
        }*/

        orderService.create(order);

        List ordersPROCESS = orderService.getOrdersProcess();

        req.getSession().setAttribute("ordersPROCESS", ordersPROCESS);
        req.getSession().setAttribute("orderNumber", order.getNumber());
        req.getSession().setAttribute("orderStatus", order.getStatus());

        String routLabelType = "yellow"; //TODO сделать параметром
        List appropriateVans = vanService.getAppropriateVans(routLabelType);

        req.getSession().setAttribute("appropriateVans", appropriateVans);
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("main_manager.jsp");
        //достать заказ "orderNumber"
        Order order = orderService.getByNumber((Integer) req.getSession().getAttribute("orderNumber"));
        //достать фуру "idVan" из странички//TODO не знаю КАК!!!!!
        //String[] vansFromCheckBox = req.getParameterValues("vansFromCheckBox");
        String[] appropriateVans = req.getParameterValues("selectedVan"); //
        System.err.println(appropriateVans.length);
        String idVan = appropriateVans[0];
        System.out.println("OK");

        List<Van> vansList = (List<Van>) req.getSession().getAttribute("appropriateVans");
        Van van = vansList.get(Integer.valueOf(idVan) - 1);//получили фуру
        //положить фуру в order
        order.setVan(van);
        orderService.update(order);

        //TODO если пользователь передумает делать заказ, то добавить кнопку отменить заказ с методом orderService.deleteByNumber((Integer) или Rollback

        //поменять статус у request
        List<Request> requestsWithYellowRout = (List<Request>) req.getSession().getAttribute("yellowRoutRequests");
        requestsWithYellowRout.get(0).setStatusRequest(RequestStatus.FINISHED);
        requestService.update(requestsWithYellowRout.get(0));

        requestDispatcher.forward(req, resp);
    }
}
