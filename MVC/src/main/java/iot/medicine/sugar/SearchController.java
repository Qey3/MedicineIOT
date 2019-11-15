package iot.medicine.sugar;

import com.mysql.jdbc.log.Log;
import my.entity.mvc.SugarTestsMVC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("search")
public class SearchController {

    private static final Logger log = Logger.getLogger("SearchController");

    @Autowired
    SugarService sugarService;


    @GetMapping
    public String search(@RequestParam String searchStr, Model model, Principal principal){

        String userName = principal.getName();

        int page = 1;

        model.addAttribute("tests", sugarService.getTests(page, userName, searchStr));
        model.addAttribute("page", page);
        return "sugarTests";
    }
}
