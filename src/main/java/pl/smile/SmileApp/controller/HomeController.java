package pl.smile.SmileApp.controller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class HomeController {

    @GetMapping("")
    public String homePage() {
        return "homepage";
    }




}





