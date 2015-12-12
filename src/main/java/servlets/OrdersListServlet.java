package servlets;

import service.API.OrderService;
import service.IMPL.OrderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrdersListServlet extends HttpServlet {

    OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("orders_list.jsp");

        List ordersPROCESS = orderService.getOrdersProcess();
        List ordersDONE = orderService.getOrdersDone();

        System.err.println(ordersPROCESS.size());
        System.err.println(ordersDONE.size());

        req.getSession().setAttribute("ordersPROCESS", ordersPROCESS);
        req.getSession().setAttribute("ordersDONE", ordersDONE);

        //TODO    Должна быть кнопка посмотреть выполненные заказы (ordersDONE) в orders_list.jsp

        requestDispatcher.forward(req, resp);
    }
}
