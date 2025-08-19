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
import start.querydsl_start.jpa.named.Named;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class SpringDataJpaNamedQueryTest {


    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("스프링데이터JPA - [NamedQuery] 테스트")
    @Transactional
    @Rollback(false)
    void testJpa_V1() {

        Named n1 = new Named("개발자", 10);
        Named n2 = new Named("홍길동", 20);
        Named n3 = new Named("카카", 15);
        Named n4 = new Named("호동생", 40);
        Named n5 = new Named("홀리", 36);
        em.persist(n1);
        em.persist(n2);
        em.persist(n3);
        em.persist(n4);
        em.persist(n5);
        em.flush();
        em.clear();

        List<Named> names = em.createNamedQuery("Named.findByName", Named.class)
                .setParameter("name", "개발자")
                .getResultList();

        log.info("names = {}", names.getFirst());
    }

}
