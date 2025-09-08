package start.querydsl_start.query.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import start.querydsl_start.query.entity.Trader;

import java.util.List;

public interface TraderRepository extends JpaRepository<Trader,Long> ,CustomTraderRepository{
    List<Trader> findByUsername(String username);
}
