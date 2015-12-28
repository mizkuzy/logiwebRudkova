package ru.tsystems.logiweb.servlets;

//todo delete
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditAndDeleteDriver extends HttpServlet {

    /**
     * Edits driver.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("editDriver.jsp");
        // либо здесь либо на страничке drivers.jsp, нужно вычленить какого именно водителя будем редактировать
        requestDispatcher.forward(req, resp);
    }

    /**
     * Deletes driver.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
