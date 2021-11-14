//package pl.smile.SmileApp.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/appointment")
//public class HomeController {
//
//    @GetMapping("")
//    public String showHomePage(HttpSession session) {
//        if (session.getAttribute("patient") != null) {
//            return "homepage";
//        } else {
//            return "redirect:/login";
//        }
//    }
//}
