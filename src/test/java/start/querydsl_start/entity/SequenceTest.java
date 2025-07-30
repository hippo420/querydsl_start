package start.querydsl_start.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
@Slf4j
@SpringBootTest
public class SequenceTest {

    @PersistenceContext
    EntityManager em;


    @Test
    @DisplayName("TABLE 시퀀스 테스트")
    @Transactional
    @Rollback(false)
    void testTableSeqGenerator(){
        Team be_dev = new Team("백엔드개발팀");
        em.persist(be_dev);

        Member member1 = new Member("개발자1",27,be_dev);
        Member member2 = new Member("개발자2",33,be_dev);
        Member member3 = new Member("개발자2",40,be_dev);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        Fund f1 = new Fund("01001","펀드1");
        Fund f2 = new Fund("01002","펀드2");
        em.persist(f1);
        em.persist(f2);

        em.flush();
        em.clear();
    }
}
