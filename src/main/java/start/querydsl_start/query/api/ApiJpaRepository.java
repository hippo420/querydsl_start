package start.querydsl_start.query.api;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import start.querydsl_start.query.entity.QTrader;
import start.querydsl_start.query.entity.Trader;

import java.math.BigDecimal;
import java.util.List;

import static start.querydsl_start.query.entity.QTrader.trader;

@Repository
public class ApiJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ApiJpaRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Trader findTraderByUserName(String username) {
        QTrader t = trader;
        return queryFactory
                .select(t)
                .from(t)
                .where(t.username.eq(username))
                .fetchOne();

    }

    public List<Trader> findAll() {
        QTrader t = trader;
        return queryFactory
                .select(t)
                .from(t)
                .fetch();
    }

    public List<Trader> findByCond1(Trader cond) {
        BooleanBuilder builder = new BooleanBuilder();

        if(!cond.getUsername().isEmpty())
        {
            builder.and(trader.username.eq(cond.getUsername()));
        }

        if(cond.getAge() != null)
        {
            builder.or(trader.age.eq(cond.getAge()));
        }

        return queryFactory
                .select(trader)
                .from(trader)
                .where(builder)
                .fetch();


    }

    public List<Trader> findByCond2(Trader cond) {
        return queryFactory
                .select(trader)
                .from(trader)
                .where(gtAge(cond.getAge()), ltSalary(cond.getSalary()))
                .fetch();
    }

    public List<Trader> findByCond3(Trader cond) {
        return queryFactory
                .select(trader)
                .from(trader)
                .where(eqNameOrAge(cond))
                .fetch();
    }

    private BooleanExpression eqUserName(String username) {

        return username != null ? trader.username.eq(username) : null;
    }

    private BooleanExpression gtAge(Integer age) {

        return age != null ? trader.age.gt(age) : null;
    }

    private BooleanExpression eqAge(Integer age) {

        return age != null ? trader.age.eq(age) : null;
    }

    private BooleanExpression ltSalary(BigDecimal salary) {

        return salary != null ? trader.salary.lt(salary) : null;
    }

    private BooleanExpression eqNameOrAge(Trader cond) {

        return eqUserName(cond.getUsername()).or(eqAge(cond.getAge()));
    }
}
