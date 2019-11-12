package iot.medicine.sugar;

import iot.medicine.copydatabase.CopySugarRepo;
import iot.medicine.copydatabase.CopySugarService;
import my.entity.mvc.SugarTestsMVC;
import my.entity.sugarMS.SugarTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class SugarController {

    private static Logger log = Logger.getLogger("SugarController");

    @Autowired
    SugarService productCatalogService;

//    @Autowired
//    CopySugarRepo copySugarRepo;

    @RequestMapping("/sugar-page/{page}")
    public String showCatalog(@PathVariable int page, Model model, Principal principal) {

        String userName = principal.getName();

        List<SugarTestsMVC> tests = new ArrayList<>();
        tests = productCatalogService.getTests(page, userName);

        log.info("items row = " + tests.size());
//        log.info("LAS ID = " + copySugarRepo.getNewSugarTests(0L).size());

        model.addAttribute("tests", tests);
        model.addAttribute("page", page);
        return "sugarTests";
    }


}
