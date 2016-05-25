package oe.roma.photodoc.controllers;


import oe.roma.photodoc.domain.Customer;
import oe.roma.photodoc.services.ContractService;
import oe.roma.photodoc.services.ReportService;
import oe.roma.photodoc.services.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by us8610 on 19.06.14.
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Resource(name="reportService")
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public String downloadExcelAll(@RequestParam Integer rem_id, ModelMap model) {

        List<Customer> list = reportService.recordsList(rem_id);

        model.addAttribute("list", list);

        return "report";
    }
}
