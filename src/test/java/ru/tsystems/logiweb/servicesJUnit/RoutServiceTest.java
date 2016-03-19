package ru.tsystems.logiweb.servicesJUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.entities.Van;
import ru.tsystems.logiweb.service.API.RoutService;
import ru.tsystems.logiweb.service.API.VanService;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@Transactional
@SuppressWarnings("deprecation")
@ContextConfiguration(locations = "/spring.xml")
public class RoutServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RoutService routService;

    @Test
    public void getAllCitiesTest(){
        List<String> cities = routService.getCities();
        assertNotNull(cities);
    }
}
