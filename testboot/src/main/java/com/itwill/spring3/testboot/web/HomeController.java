	package com.itwill.spring3.testboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        log.info("home()");
        
        return "/main/index"; // View의 이름.
    }
    
    @GetMapping("/signup")
    public String sign(Model model) {
        log.info("home()");
        
        return "/member/signup"; // View의 이름.
    }

}
