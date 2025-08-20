package start.querydsl_start.query.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.jpa.PlayerRepository;
import start.querydsl_start.jpa.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JpqlSampleTest {


    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void initData() {
        // 테스트 클래스 실행 전에 딱 한 번 실행됨
        System.out.println("before all");
        Player p1 = new Player("손흥민",33,"축구",213421382L,"LA Galuxy Football");
        Player p2 = new Player("오나티 쇼헤이",24,"야구",8237438334L,"LA 다저스");
        Player p3 = new Player("스테판 커리",30,"농구",94837221L,"LA 레이커스");
        Player p4 = new Player("막스 페르스타펀",28,"F1 레이싱",2500000L,"F1 레드불 레이싱");


        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.flush();
        em.clear();
    }


    @Test
    @DisplayName("JPQL-[기본문법] 테스트")
    @Transactional
    @Rollback(false)
    void testJpa_V1()
    {
        String sql = "select m from Player m where m.type=:type";

        List<Player> players = em.createQuery(sql,Player.class)
                .setParameter("type","야구")
                .getResultList();
        players.forEach(p -> log.info("{}",p));
    }

    @Test
    @DisplayName("스프링데이터JPA - [메서드명 조회] 테스트")
    @Transactional
    @Rollback(false)
    void testJpa_V12()
    {

    }

}
