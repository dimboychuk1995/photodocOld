package oe.roma.photodoc.controllers;


import oe.roma.photodoc.services.ContractService;
import oe.roma.photodoc.services.LoginService;
import oe.roma.photodoc.services.RemService;
import oe.roma.photodoc.services.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by us8610 on 19.06.14.
 */
@Controller
@RequestMapping("/login")
@SessionAttributes({"user"})
public class LoginController {

    @Resource(name="contractService")
    private ContractService contractService;

    @Resource(name="remService")
    private RemService remService;

    @Resource(name="loginService")
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model){
        model.addAttribute("rems",remService.getRems());
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/logout")
    public String logout(HttpSession session,ModelMap model){
        session.invalidate();
        if(model.containsAttribute("user")) model.remove("user");
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String checkLogin(@RequestParam String login, @RequestParam String password, ModelMap model){
        User user = loginService.getUser(login,password);

        if(user != null) {
            model.addAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/login";
    }
}
