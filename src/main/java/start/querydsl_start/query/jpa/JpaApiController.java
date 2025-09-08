package start.querydsl_start.query.jpa;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import start.querydsl_start.query.api.ApiService;
import start.querydsl_start.query.entity.Trader;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/jpaApi")
public class JpaApiController {
    private final TraderRepository repository;


    @GetMapping("/findByUsername")
    public List<Trader> find(@RequestParam String username) {

        return  repository.findByUsername(username);
    }

    @GetMapping("/findByCond1View")
    public String findByCond1View() {

        return "findByCond";
    }

    @PostMapping("/findByCond")
    public List<Trader> findByCond1(@ModelAttribute Trader trader) {

        return repository.findByCond(trader);

    }

}
