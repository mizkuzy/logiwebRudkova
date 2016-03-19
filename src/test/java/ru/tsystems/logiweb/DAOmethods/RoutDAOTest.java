package ru.tsystems.logiweb.DAOmethods;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.dao.API.RoutGenericDAO;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Transactional
@SuppressWarnings("deprecation")
@ContextConfiguration(locations = "/spring.xml")
public class RoutDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RoutGenericDAO routDAO;

    @Test
    public void getCitiesListForCityTest(){
        String CITY =  "Cherepovec";
        List<String> cities = routDAO.getCorrespondingCitiesForCityFrom(CITY);
        assertNotNull(cities);

    }

}
