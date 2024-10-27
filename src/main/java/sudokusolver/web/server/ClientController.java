package sudokusolver.web.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ClientController
{
    @GetMapping("/")
    public String homePage()
    {
        return "forward:/home.html";
    }

}
