package start.querydsl_start.query.jpa;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import start.querydsl_start.query.entity.Trader;
import start.querydsl_start.query.entity.dto.TraderDTO;


import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/jpaApi")
public class JpaApiController {
    private final TraderRepository repository;


    @GetMapping("/findByUsername")
    public List<Trader> find(@RequestParam(name="username") String username) {
        log.info("param = {}", username);
        return  repository.findByUsername(username);
    }


    @PostMapping("/findByCond")
    public List<Trader> findByCond1(@ModelAttribute Trader trader) {
        log.info("trader = {}", trader);
        List<Trader> res = repository.findByCond(trader.getAge(),trader.getSalary());

        return res;
    }

    @PostMapping("/findPaging1")
    public Page<TraderDTO> findPaging1(@ModelAttribute Trader trader, Pageable pageable) {
        log.info("trader = {}", trader);

        return repository.findPaging1(trader,pageable);
    }

    @PostMapping("/findPaging2")
    public Page<TraderDTO> findPaging2(@ModelAttribute Trader trader, Pageable pageable) {
        log.info("trader = {}", trader);
        return repository.findPaging2(trader,pageable);
    }
    @PostMapping("/searchPagingUtil")
    public Page<TraderDTO> searchPagingUtil(@ModelAttribute Trader trader,Pageable pageable) {
        log.info("trader = {}", trader);
        return repository.searchPagingUtil(trader,pageable);
    }

}
