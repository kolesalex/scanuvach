package com.czi.scanuvach.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.czi.scanuvach.service.HostService;


@Controller
@RequestMapping("/")
public class PortController {

    @Autowired
    private HostService hostService;

    @GetMapping("/nmapPortsJob/results")
    public String showAll(Model model) {
        model.addAttribute("hosts", hostService.getHostPorts());
        return "ports";
    }
}
