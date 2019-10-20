package iot.medicine.sugar;

import iot.medicine.pojo.SugarTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class SugarController {

    private static Logger log = Logger.getLogger("HomeController");

    @Autowired
    SugarService productCatalogService;


    @RequestMapping("/sugar-page/{page}")
    public String showCatalog(@PathVariable int page, Model model) {
        List<SugarTests> tests = new ArrayList<>();
        page = 0;
        tests = productCatalogService.getTests(page);

        SugarTests tests1 = new SugarTests();
        tests1.setId(2L);
        tests1.setDevice_id(2L);
        tests1.setGlucose(2D);
        tests1.setTime(new Date());

        tests.add(tests1);

        log.info("items row = " + tests.size());

        model.addAttribute("tests", tests);
        return "sugarTests";
    }

}
