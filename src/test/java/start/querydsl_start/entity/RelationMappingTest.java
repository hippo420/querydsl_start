package start.querydsl_start.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.realation.OneMember;
import start.querydsl_start.realation.OneTeam;

import java.util.List;

@SpringBootTest
public class RelationMappingTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("단방향 매핑 처리 테스트")
    @Transactional
    @Rollback(false)
    void testOneRelation(){
        OneTeam be_dev = new OneTeam("백엔드개발팀");
        em.persist(be_dev);

        OneMember member1 = new OneMember("개발자1",27,be_dev);
        OneMember member2 = new OneMember("개발자2",33,be_dev);
        OneMember member3 = new OneMember("개발자2",40,be_dev);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        Fund f1 = new Fund("01001","펀드1");
        Fund f2 = new Fund("01002","펀드2");
        em.persist(f1);
        em.persist(f2);

        em.flush();
        em.clear();

        //조회
        List<OneMember> memberList = em.createQuery("select m from OneMember m", OneMember.class)
                .getResultList();

        List<OneTeam> teamList = em.createQuery("select t from OneTeam t", OneTeam.class)
                .getResultList();
        memberList.stream().forEach(m -> System.out.println(m));
        teamList.stream().forEach(t -> System.out.println(t));
    }

    @Test
    @DisplayName("양방향 매핑 처리 테스트")
    @Transactional
    @Rollback(false)
    void testDualRelation(){
        Team be_dev = new Team("백엔드개발팀");
        em.persist(be_dev);

        Member member1 = new Member("개발자1",27);
        Member member2 = new Member("개발자2",33);
        Member member3 = new Member("개발자3",40);

        member1.setTeam(be_dev);
        member2.setTeam(be_dev);
        member3.setTeam(be_dev);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        //연관관계 주인값 누락함.
        Member member4 = new Member("개발자4",40);
        em.persist(member4);
        be_dev.getMember().add(member4);


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
