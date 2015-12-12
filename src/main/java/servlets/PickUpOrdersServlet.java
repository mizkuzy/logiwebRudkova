package servlets;

import entities.Request;
import service.API.RequestService;
import service.IMPL.RequestServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PickUpOrdersServlet extends HttpServlet {

    private RequestService requestService = new RequestServiceImpl();

    //TODO работает

    /**
     * Counts amount of Special Routs (by Route Labels) in Requests. Picks ID of these requests
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pick_up_orders.jsp");

        List<Request> requestsWithYellowRout = requestService.findRequestsWithSpecialRout("yellow");
        List<Request> requestsWithGreenRout = requestService.findRequestsWithSpecialRout("green");
        List<Request> requestsWithPurpleRout = requestService.findRequestsWithSpecialRout("purple");
        List<Request> requestsWithBlueRout = requestService.findRequestsWithSpecialRout("blue");

        request.getSession().setAttribute("yellowRoutRequests", requestsWithYellowRout);
        request.getSession().setAttribute("greenRoutRequests", requestsWithGreenRout);
        request.getSession().setAttribute("purpleRoutRequests", requestsWithPurpleRout);
        request.getSession().setAttribute("blueRoutRequests", requestsWithBlueRout);

        requestDispatcher.forward(request, response);
    }

}
