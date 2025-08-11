package start.querydsl_start.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.jpa.entity.Player;
import start.querydsl_start.valuetype.collection.Call;
import start.querydsl_start.valuetype.collection.Collect;
import start.querydsl_start.valuetype.embeded.Festival;
import start.querydsl_start.valuetype.embeded.Period;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class SpringDataJpaTest {


    @Autowired
    PlayerRepository repository;

    @Test
    @DisplayName("스프링데이터JPA - [입력/조회] 테스트")
    @Transactional
    @Rollback(false)
    void testJpa_V1()
    {
        Player p1 = new Player("손흥민",33,"축구",213421382L,"LA Galuxy Football");
        Player p2 = new Player("오나티 쇼헤이",24,"야구",8237438334L,"LA 다저스");
        Player p3 = new Player("스테판 커리",30,"농구",94837221L,"LA 레이커스");
        Player p4 = new Player("막스 페르스타펀",28,"F1 레이싱",2500000L,"F1 레드불 레이싱");

        List<Player> ps = new ArrayList<>();
        ps.add(p3);
        ps.add(p4);

        repository.save(p1);
        repository.save(p2);
        repository.saveAll(ps);

        List<Player> players = repository.findAll();

        players.forEach(p -> log.info("{}",p));

    }

    @Test
    @DisplayName("스프링데이터JPA - [메서드명 조회] 테스트")
    @Transactional
    @Rollback(false)
    void testJpa_V12()
    {
        Player p1 = new Player("손흥민",33,"축구",213421382L,"LA Galuxy Football");
        Player p2 = new Player("오나티 쇼헤이",24,"야구",8237438334L,"LA 다저스");
        Player p3 = new Player("스테판 커리",30,"농구",94837221L,"LA 레이커스");
        Player p4 = new Player("막스 페르스타펀",28,"F1 레이싱",2500000L,"F1 레드불 레이싱");

        List<Player> ps = new ArrayList<>();
        ps.add(p3);
        ps.add(p4);

        repository.save(p1);
        repository.save(p2);
        repository.saveAll(ps);

        List<Player> players = repository.findByType("야구");

        players.forEach(p -> log.info("{}",p));

    }

}
