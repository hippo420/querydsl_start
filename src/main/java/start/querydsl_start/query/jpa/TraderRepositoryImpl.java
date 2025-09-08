package start.querydsl_start.query.jpa;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import start.querydsl_start.query.entity.QFirm;
import start.querydsl_start.query.entity.QTrader;
import start.querydsl_start.query.entity.Trader;
import start.querydsl_start.query.entity.dto.QTraderDTO;
import start.querydsl_start.query.entity.dto.TraderDTO;

import java.math.BigDecimal;
import java.util.List;

import static start.querydsl_start.query.entity.QFirm.*;
import static start.querydsl_start.query.entity.QTrader.*;
import static start.querydsl_start.query.entity.QTrader.trader;
@Slf4j
public class TraderRepositoryImpl implements CustomTraderRepository{

    private final JPAQueryFactory queryFactory;
    public TraderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Trader> findByCond(Integer condAge, BigDecimal condSalary) {
        QTrader t = trader;
        QFirm f = firm;
        log.info("condAge: {}, condSalary: {}",condAge, condSalary);
        return queryFactory
                .select(t)
                .from(t)
                .join(t.firm, f)
                .where(gtAge(condAge), ltSalary(condSalary))
                .fetch();
    }

    @Override
    public Page<TraderDTO> findPaging1(Trader cond, Pageable pageable) {

        QueryResults<TraderDTO>  results = queryFactory
                                    .select(new QTraderDTO(trader.username, trader.age))
                                    .from(trader)
                                    .join(trader.firm, firm)
                                    .where(gtAge(cond.getAge()), ltSalary(cond.getSalary()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<TraderDTO> content = results.getResults();
        long count = results.getTotal();
        return new PageImpl<>(content,pageable,count);
    }

    @Override
    public Page<TraderDTO> findPaging2(Trader cond, Pageable pageable) {
        //데이터 조회 쿼리
        List<TraderDTO>  content = queryFactory
                .select(new QTraderDTO(trader.username, trader.age))
                .from(trader)
                .join(trader.firm, firm)
                .where(gtAge(cond.getAge()), ltSalary(cond.getSalary()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //카운트 조회 쿼리
        long count = queryFactory
                .select(trader)
                .from(trader)
                .where(gtAge(cond.getAge()), ltSalary(cond.getSalary()))
                .fetchCount();

        return new PageImpl<>(content,pageable,count);
    }

    //카운트가 생략가능한 경우 생략해버림
    //pageSize : 100인대 실제 데이터가 100미만인 경우
    //마지막 페이지의 데이터개수가 pageSize보다 작은 경우
    @Override
    public Page<TraderDTO> searchPagingUtil(Trader cond, Pageable pageable) {
        List<TraderDTO>  content = queryFactory
                .select(new QTraderDTO(trader.username, trader.age))
                .from(trader)
                .join(trader.firm, firm)
                .where(gtAge(cond.getAge()), ltSalary(cond.getSalary()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Trader> countQuery = queryFactory
                .select(trader)
                .from(trader)
                .where(gtAge(cond.getAge()), ltSalary(cond.getSalary()))
                ;

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }


    private BooleanExpression gtAge(Integer age) {
        return age != null ? trader.age.gt(age) : null;
    }

    private BooleanExpression ltSalary(BigDecimal salary) {
        return salary != null ? trader.salary.lt(salary) : null;
    }
}
