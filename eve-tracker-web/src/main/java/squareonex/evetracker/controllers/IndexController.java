package squareonex.evetracker.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController implements ErrorController {
    @RequestMapping({"", "index"})
    public String index(){
        return "index";
    }

    @RequestMapping("error")
    public String handleError(HttpServletRequest request) {
        return "error";
    }
}
