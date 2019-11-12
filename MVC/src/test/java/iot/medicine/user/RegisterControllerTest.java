package iot.medicine.user;

import iot.medicine.WebAppTestConfiguration;
import my.entity.mvc.user.Users;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebAppTestConfiguration.class)
public class RegisterControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    UserService userService;

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
    public void showRegisterPageTest() throws Exception {

        String viewName;
        ModelAndView modelAndView =
                mockMvc.perform(get("/register"))
                        .andReturn()
                        .getModelAndView();

        viewName = modelAndView.getViewName();
        List items = (List) modelAndView.getModel().get("roles");

        assertEquals("registerUser", viewName);
        assertNotNull(items);
        assertEquals(2, items.size());
    }

    @Test
//    @Sql({"/deviceTypeTest.sql", "/devicesDetailsTest.sql", "/usersTest.sql", "/deviceTest.sql", "/sugarControllerTests.sql", "/usersRole.sql"})
    public void registerFormTest() throws Exception {

        Users user = new Users(2L, "user2", "user2", "$10$JaW3vN/HMAMRrHrq8YCCBeDucJbtjubTziwVMnyeH.90wJUFV1UCa", "user2", "user2", new ArrayList<>(), new HashSet<>());
        String viewName;
        ModelAndView modelAndView =
                mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .param("role", "USER")
                        .requestAttr("item", user))
                        .andReturn()
                        .getModelAndView();

        viewName = modelAndView.getViewName();
        List items = (List) modelAndView.getModel().get("roles");

//        assertEquals(userService.findUserByLogin("user2"), user);
        assertEquals("registerUser", viewName);
        assertNotNull(items);
        assertEquals(2, items.size());
    }
}
