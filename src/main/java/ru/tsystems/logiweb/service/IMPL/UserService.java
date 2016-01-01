package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tsystems.logiweb.dao.API.EmployeeGenericDAO;
import ru.tsystems.logiweb.entities.Employee;
import ru.tsystems.logiweb.entities.statusesAndStates.POSITION;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private EmployeeGenericDAO employeeDAO;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        logger.info("Requested email - " + email);
        //TODO здесь обработать исключение
        Employee employee = employeeDAO.getEmployeeByEmail(email);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (employee.getPosition().equals(POSITION.DRIVER)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_DRIVER"));
            logger.info("The authorities of the user - ROLE_DRIVER");
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
            logger.info("The authorities of the user - ROLE_MANAGER");
        }

        return new User(email, employee.getPassword(), authorities);
    }
}


































