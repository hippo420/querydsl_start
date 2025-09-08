package start.querydsl_start.query.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import start.querydsl_start.query.entity.Trader;

import java.util.List;

@Controller
@AllArgsConstructor
public class ApiController {
    private final ApiService service;


    @GetMapping("/find")
    public String find(@RequestParam String username, Model model) {
        model.addAttribute("username", username);

        Trader trader = service.findTrader(username);
        model.addAttribute("trader", trader);
        return "find";
    }

    @GetMapping("/findAll")
    public String find(Model model) {


        List<Trader> traders= service.findAll();
        model.addAttribute("traders", traders);
        return "findAll";
    }

    @GetMapping("/findByCond1View")
    public String findByCond1View() {


        return "findByCond";
    }

    @PostMapping("/findByCond1")
    public String findByCond1(@ModelAttribute Trader trader, Model model) {

        List<Trader> traders= service.findByCond1(trader);
        model.addAttribute("traders", traders);

        return "findByCond";
    }

    @GetMapping("/findByCond2")
    public String findByCond2(@ModelAttribute Trader trader, Model model) {

        List<Trader> traders= service.findByCond2(trader);
        model.addAttribute("traders", traders);

        return "findByCond";
    }

    @GetMapping("/findByCond3")
    public String findByCond3(@ModelAttribute Trader trader, Model model) {

        List<Trader> traders= service.findByCond3(trader);
        model.addAttribute("traders", traders);

        return "findByCond";
    }
}
