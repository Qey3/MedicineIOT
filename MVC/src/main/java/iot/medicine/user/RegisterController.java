package iot.medicine.user;

import my.entity.mvc.user.RoleName;
import my.entity.mvc.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private static Logger log = Logger.getLogger("RegisterController");

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @GetMapping
    public String showRegisterPage(Model model) {

        model.addAttribute("roles", userService.getAllRolesName());
        return "registerUser";
    }

    @PostMapping
    public String registerForm(@ModelAttribute("item") Users user,
                               @RequestParam("role") RoleName role,
                               BindingResult result,
                               Model model) {

        userValidator.validate(user,result);

        if (result.hasErrors()) {
            model.addAttribute("roles", userService.getAllRolesName());
            model.addAttribute("errors", result.getAllErrors());
            return "registerUser";
        }

        log.info("Request param role = " + role);

        if (!userService.saveNewUser(user, role)) return "error";

        return "redirect:/homePage";
    }
}
