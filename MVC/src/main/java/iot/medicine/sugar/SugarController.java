package iot.medicine.sugar;

import iot.medicine.pojo.SugarTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SugarController {

    @Autowired
    SugarService productCatalogService;


    @RequestMapping("/sugar-page/{lastId}")
    public String showCatalog(@PathVariable Long lastId, Model model) {
        List<SugarTests> tests;
        tests = productCatalogService.getTests(lastId);

        model.addAttribute("items", tests);
        return "sugarTests";
    }

}
