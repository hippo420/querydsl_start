package start.querydsl_start.query.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import start.querydsl_start.query.api.ApiService;
import start.querydsl_start.query.entity.Trader;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/jpaApi")
public class JpaApiController {
    private final TraderRepository repository;


    @GetMapping("/findByUsername")
    public String find(@RequestParam String username, Model model) {


        List<Trader> traders= repository.findByUsername(username);
        model.addAttribute("traders", traders);
        return "findAll";
    }

    @GetMapping("/findByCond1View")
    public String findByCond1View() {

        return "findByCond";
    }

    @PostMapping("/findByCond")
    public String findByCond1(@ModelAttribute Trader trader, Model model) {

        List<Trader> traders= repository.findByCond(trader);
        model.addAttribute("traders", traders);

        return "findByCond";
    }

}
