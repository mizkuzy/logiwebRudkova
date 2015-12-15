package ru.tsystems.logiweb.servlets;

//TODO проверка всех форм на пустоту

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.tsystems.logiweb.entities.Employee;
import ru.tsystems.logiweb.entities.statusesAndStates.POSITION;
import ru.tsystems.logiweb.service.API.EmployeeService;
import ru.tsystems.logiweb.service.IMPL.EmployeeServiceImpl;

import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//TODO может ли кнопка Домой висеть на всех страницах и её не нужно было бы прописывать вручную на каждой jsp странице
public class LoginServlet extends HttpServlet {

    @Autowired
    private EmployeeService employeeService;
    ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    //TODO убрать сохранение сессии, если закрывается страница браузера
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
//TODO запретить гетЗапросы там где нельзя
    // TODO добавить zапрет на незалогинившихся
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean validationIsOK = false;
        String email = request.getParameter("user");
        String password = request.getParameter("pswd");

        RequestDispatcher requestDispatcherManager = request.getRequestDispatcher("/main_manager.jsp");
        RequestDispatcher requestDispatcherDriver = request.getRequestDispatcher("/main_driver.jsp");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");

        try {
            validationIsOK = employeeService.checkEmailAndPassword(email, password);
        } catch (NoResultException ex) {
            requestDispatcher.forward(request, response);
        }

        if (validationIsOK) {
            Employee employee = employeeService.getEntityByEmail(email);
            request.setAttribute("employee", employee);
            if (employee.getPosition().equals(POSITION.DRIVER)) {
                Integer personalDriverNumber = employeeService.getPersonalNumber(email);
                request.getSession().setAttribute("personalNumber", personalDriverNumber);
                setSession(request, employee.getEmployeeId());
                requestDispatcherDriver.forward(request, response);
            } else {
                setSession(request, employee.getEmployeeId());
                requestDispatcherManager.forward(request, response);
            }
        } else {
            setErrorValidationToSession(request);
            requestDispatcher.forward(request, response);
        }
    }

    public void setSession(HttpServletRequest request, Integer userID) {
        request.getSession().setAttribute("userID", userID);
    }

    public void setErrorValidationToSession(HttpServletRequest request) {
        request.getSession().setAttribute("errorValidation", "Invalid pair login+password. Try again or ask the Administrator");//TODO это сообщение нужно скриптом написать, чтобы оно выводилось под формой для входа
    }
}
