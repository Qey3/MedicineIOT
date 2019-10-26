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

    private static Logger log = Logger.getLogger("SugarController");

    @Autowired
    SugarService productCatalogService;


    @RequestMapping("/sugar-page/{page}")
    public String showCatalog(@PathVariable int page, Model model) {
        List<SugarTests> tests = new ArrayList<>();
        tests = productCatalogService.getTests(page);


        log.info("items row = " + tests.size());

        model.addAttribute("tests", tests);
        return "sugarTests";
    }

}
