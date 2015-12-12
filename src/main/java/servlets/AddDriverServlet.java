package servlets;

import entities.Driver;
import entities.Employee;
import entities.statusesAndStates.POSITION;
import service.API.DriverService;
import service.API.EmployeeService;
import service.IMPL.DriverServiceImpl;
import service.IMPL.EmployeeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDriverServlet extends HttpServlet {

    private DriverService driverService = new DriverServiceImpl();
    private EmployeeService employeeService = new EmployeeServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcherManager = req.getRequestDispatcher("/main_manager.jsp");

        String name = req.getParameter("driverName");
        String surname = req.getParameter("driverSurname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //TODO ОШИБКА при создании водителя HTTP Status 500 - org.hibernate.TransientPropertyValueException:
       // object references an unsaved transient instance - save the transient instance before flushing :
      //  logiweb.entities.Employee.driverNumberFK -> logiweb.entities.Driver

        Employee employee = new Employee(email, password, POSITION.DRIVER);
        Driver driver = new Driver(name, surname);
       employee.setDriverFK(driver);

       driverService.create(driver);
        employeeService.create(employee);

        requestDispatcherManager.forward(req, resp);
    }
}
