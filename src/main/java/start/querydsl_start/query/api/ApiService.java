package start.querydsl_start.query.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import start.querydsl_start.query.entity.Trader;

import java.util.List;

@Service
@AllArgsConstructor
public class ApiService {

    private final ApiJpaRepository repository;

    public Trader findTrader(String username) {
        return repository.findTraderByUserName(username);
    }

    public List<Trader> findAll() {
        return repository.findAll();
    }

    public List<Trader> findByCond1(Trader trader) {
        return repository.findByCond1(trader);
    }

    public List<Trader> findByCond2(Trader trader) {
        return repository.findByCond2(trader);
    }

    public List<Trader> findByCond3(Trader trader) {
        return repository.findByCond3(trader);
    }
}
