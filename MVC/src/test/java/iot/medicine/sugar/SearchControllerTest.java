package iot.medicine.sugar;

import iot.medicine.WebAppTestConfiguration;
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
public class SearchControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Sql(value ={"/deviceTypeTest.sql", "/devicesDetailsTest.sql", "/usersTest.sql", "/deviceTest.sql", "/sugarControllerTests.sql", "/usersRole.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/dropAll.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void sugarControllerTest() throws Exception {

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "user";
            }
        };

        String viewName;
        ModelAndView modelAndView =
                mockMvc.perform(get("/search").principal(principal)
                        .param("searchStr", "5"))
                        .andReturn()
                        .getModelAndView();

        viewName = modelAndView.getViewName();
        List items = (List)modelAndView.getModel().get("tests");

        assertEquals("sugarTests", viewName);
        assertNotNull(items);
        assertEquals(1, items.size());
    }

}
