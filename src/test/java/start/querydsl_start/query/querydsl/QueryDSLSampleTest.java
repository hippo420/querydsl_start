package start.querydsl_start.query.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.entity.QMember;
import start.querydsl_start.jpa.entity.Player;
import start.querydsl_start.jpa.entity.QPlayer;
import start.querydsl_start.query.entity.Firm;
import start.querydsl_start.query.entity.Trader;
import start.querydsl_start.query.entity.dto.TraderDTO;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueryDSLSampleTest {


    @PersistenceContext
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void initData() {
        // 테스트 클래스 실행 전에 딱 한 번 실행됨
        System.out.println("before all");
        Player p1 = new Player("손흥민",33,"축구",10000L,"LA Galuxy Football");
        Player p2 = new Player("오나티 쇼헤이",24,"야구",30000L,"LA 다저스");
        Player p21 = new Player("김혜성",25,"야구",2000L,"LA 다저스");
        Player p3 = new Player("스테판 커리",30,"농구",5000L,"LA 레이커스");
        Player p4 = new Player("막스 페르스타펀",28,"F1 레이싱",2000L,"F1 레드불 레이싱");

        Firm f1 = new Firm("Goldman Sachs");
        Firm f2 = new Firm("Morgan Stanley");
        Firm f3 = new Firm("JP Morgan");
        em.persist(f1);
        em.persist(f2);
        em.persist(f3);

        List<Trader> traders = List.of(
                new Trader("alice", 25, f1, BigDecimal.valueOf(5000)),
                new Trader("bob", 30, f2, BigDecimal.valueOf(7000)),
                new Trader("charlie", 28, f3, BigDecimal.valueOf(6500)),
                new Trader("david", 35, f1, BigDecimal.valueOf(9000)),
                new Trader("eva", 22, f2, BigDecimal.valueOf(4000)),
                new Trader("frank", 40, f3, BigDecimal.valueOf(12000)),
                new Trader("grace", 27, f1, BigDecimal.valueOf(5800)),
                new Trader("henry", 32, f2, BigDecimal.valueOf(7500)),
                new Trader("irene", 29, f3, BigDecimal.valueOf(6800)),
                new Trader("jack", 31, f1, BigDecimal.valueOf(8100))
        );

        traders.forEach(em::persist);


        em.persist(p1);
        em.persist(p2);
        em.persist(p21);
        em.persist(p3);
        em.persist(p4);
        em.flush();
        em.clear();

        jpaQueryFactory = new JPAQueryFactory(em);
    }


    @Test
    @DisplayName("QueryDSL-[SELECT-where] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_SELECT()
    {
        //WHERE
        QPlayer p = new QPlayer("customplayer");
        //QPlayer p = QPlayer.player);

        List<Player> players = jpaQueryFactory
                .select(p)
                .from(p)
                .where(p.name.eq("손흥민"))
                .fetch();

        players.forEach(player -> log.info("{}",player));

    }

    @Test
    @DisplayName("QueryDSL-[SELECT-where] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_SELECT_WHERE()
    {
        //WHERE
        QPlayer p = QPlayer.player;

        List<Player> players = jpaQueryFactory
                .select(p)
                .from(p)
                .where(p.type.eq("야구").and(p.age.gt(24)))
                .fetch();

        players.forEach(player -> log.info("{}",player));

        List<Player> players2 = jpaQueryFactory
                .select(p)
                .from(p)
                .where(p.type.eq("야구"), p.age.gt(24))
                .fetch();
        players2.forEach(player -> log.info("{}",player));

        List<Player> players3= jpaQueryFactory
                .select(p)
                .from(p)
                .where(p.type.eq("야구").or(p.type.eq("축구")))
                .fetch();

        players3.forEach(player -> log.info("{}",player));
    }

    @Test
    @DisplayName("QueryDSL-[SELECT-조건: Equal, Not Equal] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_SELECT_COND1()
    {
        //WHERE
        QPlayer p = QPlayer.player;

        //equal, not equal
        List<Player> players = jpaQueryFactory
                .select(p)
                .from(p)
                .where(p.type.eq("야구").and(p.age.gt(24)))
                .fetch();

        players.forEach(player -> log.info("{}",player));

    }

    @Test
    @DisplayName("QueryDSL-[SELECT-조건: In, Not in] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_SELECT_COND2() {
        //WHERE
        QPlayer p = QPlayer.player;
        //in, not in
        List<Player> players = jpaQueryFactory
                .select(p)
                .from(p)
                .where(p.type.eq("야구"), p.age.gt(24))
                .fetch();
        players.forEach(player -> log.info("{}",player));
    }

    @Test
    @DisplayName("QueryDSL-[SELECT-조건: Between] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_SELECT_COND3() {
        //WHERE
        QPlayer p = QPlayer.player;

        //between
        List<Player> players3= jpaQueryFactory
                .select(p)
                .from(p)
                .where(p.type.eq("야구").or(p.type.eq("축구")))
                .fetch();

        players3.forEach(player -> log.info("{}",player));
    }

    @Test
    @DisplayName("QueryDSL-[SELECT-조건: GT,LT] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_SELECT_COND4() {
        //WHERE
        QPlayer p = QPlayer.player;

        //between
        List<Player> players= jpaQueryFactory
                .select(p)
                .from(p)
                .where(p.salary.gt(2000L),p.salary.loe(10000L))
                .fetch();

        players.forEach(player -> log.info("{}",player));
    }

    @Test
    @DisplayName("QueryDSL-[SELECT-조건: LIKE] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_SELECT_COND5() {
        //WHERE
        QPlayer p = QPlayer.player;

        //between
        List<Player> players= jpaQueryFactory
                .select(p)
                .from(p)
                .where(p.team.like("LA%"))
                .fetch();

        players.forEach(player -> log.info("{}",player));
    }


}
