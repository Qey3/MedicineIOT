package iot.medicine.sugar;

import iot.medicine.copydatabase.CopySugarRepo;
import iot.medicine.copydatabase.CopySugarService;
import my.entity.mvc.Chart;
import my.entity.mvc.SugarTestsMVC;
import my.entity.sugarMS.SugarTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));

        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        if (hasUserRole) {
            model.addAttribute("tests", productCatalogService.getTests(page, userName, "0"));
        }else if(hasAdminRole){
            model.addAttribute("tests", productCatalogService.getAdminTests(page));
        }

//        model.addAttribute("tests", productCatalogService.getTests(page, userName, "0"));
        model.addAttribute("page", page);
        return "sugarTests";
    }
    @RequestMapping("/pieChart")
    public String showChart(Model model, Principal principal){

        String userName = principal.getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));

        List<Chart> charts = productCatalogService.getChartsForUser(userName);



        model.addAttribute("pieDataList", charts);

        return "chart";
    }

}
