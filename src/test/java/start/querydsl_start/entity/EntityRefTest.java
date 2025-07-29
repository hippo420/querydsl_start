package start.querydsl_start.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.constant.StockType;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class EntityRefTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("엔티티 기타 매핑")
    @Transactional
    @Rollback(false)
    void EntityRefTest()
    {
        Fund fund1 = new Fund("10001","펀드1");
        Fund fund2 = new Fund("10002","펀드2");
        em.persist(fund1);
        em.persist(fund2);

        Stock stock1 = new Stock("KR111","삼성전자", BigDecimal.valueOf(60000) ,202301293L, StockType.KOSPI,null);
        Stock stock2 = new Stock("KR112","네이버", BigDecimal.valueOf(250000) ,1235123L, StockType.KOSPI,null);
        Stock stock3 = new Stock("KR211","HLB", BigDecimal.valueOf(120000),92729L, StockType.KOSDAQ,null);

        em.persist(stock1);
        em.persist(stock2);
        em.persist(stock3);

        em.flush();
        em.clear();



        //조회
        List<Stock> stockList = em.createQuery("select m from Stock m", Stock.class)
                .getResultList();


        stockList.stream().forEach(t -> System.out.println(t));
    }

}
