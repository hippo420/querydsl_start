package start.querydsl_start.query.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import start.querydsl_start.query.entity.Trader;
import start.querydsl_start.query.entity.dto.TraderDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CustomTraderRepository {
    List<Trader> findByCond(Integer condAge, BigDecimal condSalary);
    Page<TraderDTO> findPaging1(Trader trader, Pageable pageable);
    Page<TraderDTO> findPaging2(Trader trader, Pageable pageable);
    Page<TraderDTO> searchPagingUtil(Trader trader, Pageable pageable);
}
