package iot.medicine.copyDatabaseTest;

import iot.medicine.WebAppTestConfiguration;
import iot.medicine.copydatabase.CopySugarService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppTestConfiguration.class)
public class copyDatabaseTest {


    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    CopySugarService copySugarService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
//    @Sql({"/deviceTypeTest.sql", "/devicesDetailsTest.sql", "/usersTest.sql", "/deviceTest.sql", "/sugarControllerTests.sql", "/usersRole.sql"})
    public void executeCopyTest() throws Exception {

        copySugarService.executeCopy();
    }
}
