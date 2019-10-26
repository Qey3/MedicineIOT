package iot.medicine;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
@RequestMapping("/homePage")
public class HomeController {

    private static Logger log = Logger.getLogger("HomeController");

    @RequestMapping(method = RequestMethod.GET)
    public String showHomepage() {
        log.info("Call showHomePage()");

        return "homePage";
    }
}
