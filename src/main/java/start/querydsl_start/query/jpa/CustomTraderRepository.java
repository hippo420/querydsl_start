package start.querydsl_start.query.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import start.querydsl_start.query.entity.Trader;
import start.querydsl_start.query.entity.dto.TraderDTO;

import java.util.List;

public interface CustomTraderRepository {
    List<Trader> findByCond(Trader trader);
    Page<TraderDTO> searchPageSimple(Trader trader, Pageable pageable);
    Page<TraderDTO> searchPageComplex(Trader trader, Pageable pageable);
    Page<TraderDTO> searchPagingUtil(Trader trader, Pageable pageable);
}
