package start.querydsl_start.query.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
        Player p21 = new Player("김혜성",24,"야구",8237438334L,"LA 다저스");
        Player p3 = new Player("스테판 커리",30,"농구",94837221L,"LA 레이커스");
        Player p4 = new Player("막스 페르스타펀",28,"F1 레이싱",2500000L,"F1 레드불 레이싱");


        em.persist(p1);
        em.persist(p2);
        em.persist(p21);
        em.persist(p3);
        em.persist(p4);
        em.flush();
        em.clear();
    }


    @Test
    @DisplayName("JPQL-[SELECT-where] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_V1()
    {
        //WHERE
        String sql = "select m from Player m where m.type=:type";

        List<Player> players = em.createQuery(sql,Player.class)
                .setParameter("type","야구")
                .getResultList();
        players.forEach(p -> log.info("{}",p));

    }

    @Test
    @DisplayName("JPQL-[SELECT-where] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_V1_1()
    {
        //GROUPBY
        String sql = "select m from Player m where m.type=:type";

        List<Player> players = em.createQuery(sql,Player.class)
                .setParameter("type","야구")
                .getResultList();
        players.forEach(p -> log.info("{}",p));

    }

    @Test
    @DisplayName("JPQL-[SELECT-where] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_V1_2()
    {
        //HAVING
        String sql = "select m from Player m where m.type=:type";

        List<Player> players = em.createQuery(sql,Player.class)
                .setParameter("type","야구")
                .getResultList();
        players.forEach(p -> log.info("{}",p));

    }

    @Test
    @DisplayName("JPQL-[SELECT-TypeQuery] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_V1_Type()
    {
        //HAVING
        String sql = "select m from Player m where m.type=:type";

        TypedQuery<Player> query = em.createQuery(sql,Player.class)
                .setParameter("type","야구");

        List<Player> players = query.getResultList();
        players.forEach(p -> log.info("{}",p));

    }

    @Test
    @DisplayName("JPQL-[SELECT-Query] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_V1_NoType()
    {
        String sql = "select m from Player m where m.type=:type";

        Query query = em.createQuery(sql).setParameter("type","야구");
        List results = query.getResultList();
        results.forEach(p -> log.info("{}",p));
    }


    @Test
    @DisplayName("JPQL-[SELECT-where] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_V1_3()
    {
        //ORDER BY
        String sql = "select m from Player m  order by m.age ASC , m.id DESC";

        List<Player> players = em.createQuery(sql,Player.class)
                .getResultList();
        players.forEach(p -> log.info("{}",p));

    }

    @Test
    @DisplayName("JPQL-[UPDATE] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_V2()
    {

        String sql = "update Player m set m.name = '이름없음' where m.name is not null";
        String sql1 = "select m from Player m";

        List<Player> players =em.createQuery(sql1,Player.class).getResultList();
        System.out.println("update 전");
        players.forEach(System.out::println);

        int cnt = em.createQuery(sql).executeUpdate();

        em.clear();

        List<Player> players1 =em.createQuery(sql1,Player.class).getResultList();
        System.out.println("update -"+cnt+"건");
        players1.forEach(System.out::println);
    }

    @Test
    @DisplayName("JPQL-[DELETE] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_V3()
    {
        String sql = "delete Player m where m.type = :type";
        String sql1 = "select m from Player m";

        List<Player> players = em.createQuery(sql1,Player.class).getResultList();
        System.out.println("delete 전");
        players.forEach(System.out::println);

        int cnt = em.createQuery(sql).setParameter("type","야구").executeUpdate();

        em.clear();

        List<Player> players1 =em.createQuery(sql1,Player.class).getResultList();
        System.out.println("delete "+cnt+"건");
        players1.forEach(System.out::println);
    }
}
