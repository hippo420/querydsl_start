package start.querydsl_start.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
public class EntityTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("엔티티 입력 및 조회테스트")
    void EntityTest()
    {
        Team be_dev = new Team("백엔드개발팀");
        Team fe_dev = new Team("프론트엔드개발팀");
        Team db = new Team("DB팀");
        Team sys = new Team("시스템팀");
        em.persist(be_dev);
        em.persist(fe_dev);
        em.persist(db);
        em.persist(sys);

        //연관관계주인 값 설정함
        Member member1 = new Member("개발자1",27,be_dev);
        Member member2 = new Member("개발자2",33,be_dev);
        Member member3 = new Member("개발자2",40,fe_dev);
        Member member4 = new Member("개발자2",30,fe_dev);
        Member member5 = new Member("개발자2",50,sys);
        Member member6 = new Member("개발자2",18,db);
        Member member7 = new Member("개발자2",43,be_dev);



        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        em.persist(member5);
        em.persist(member6);
        em.persist(member7);

        em.flush();
        em.clear();



        //조회
        List<Member> memberList = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        List<Team> teamList = em.createQuery("select t from Team t", Team.class)
                .getResultList();
        memberList.stream().forEach(m -> System.out.println(m));
        teamList.stream().forEach(t -> System.out.println(t));
    }

}
