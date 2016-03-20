package ru.tsystems.logiweb.servicesJUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.entities.Van;
import ru.tsystems.logiweb.service.API.VanService;

import static org.junit.Assert.assertTrue;


@Transactional
@SuppressWarnings("deprecation")
@ContextConfiguration(locations = "/spring.xml")
public class VanServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private VanService vanService;

    @Test
    public void testCreateVan(){
        int sizeAllDriversBeforeTest = vanService.getAll().size();

        Van van = new Van();
        vanService.create(van);
        int sizeAllDriversAfterTest = vanService.getAll().size();
        assertTrue(sizeAllDriversAfterTest > sizeAllDriversBeforeTest);

        vanService.delete(van);
    }
}
