package start.querydsl_start.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.realation.OneMember;
import start.querydsl_start.realation.OneTeam;

import java.util.List;
@Slf4j
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
        memberList.stream().forEach(m -> log.info("MEMBER -> {}",m));
        teamList.stream().forEach(t -> log.info("TEAM -> {}",t));
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

    @Test
    @DisplayName("연관관계 편의 메소드")
    @Transactional
    @Rollback(false)
    void testRefMethod(){
        Team be_dev = new Team("백엔드개발팀");
        Team fe_dev = new Team("프론트엔드개발팀");
        em.persist(be_dev);
        em.persist(fe_dev);

        Member member1 = new Member("개발자1",27,be_dev);
        Member member2 = new Member("개발자2",33,be_dev);
        Member member3 = new Member("개발자3",40,be_dev);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        member3.setTeam(fe_dev);

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


    @Test
    @DisplayName("다대다 [N:N]매핑-단방향 테스트")
    @Transactional
    @Rollback(false)
    void testManyToManyRelation1(){
        Product p1 = new Product("제품1");
        em.persist(p1);

        Member m1 = new Member();
        m1.setName("멤버1");
        m1.getProducts().add(p1);
        em.persist(m1);


        em.flush();
        em.clear();

        //조회
        List<Product> products = em.createQuery("select t from Product t", Product.class)
                .getResultList();

        List<Member> members = em.createQuery("select t from Member t", Member.class)
                .getResultList();

        products.stream().forEach(m -> log.info("Product -> {}", m));
        members.stream().forEach(t -> log.info("Member -> {}", t));
    }


    @Test
    @DisplayName("다대다 [N:N]매핑-식별자")
    @Transactional
    @Rollback(false)
    void testManyToManyRelation2(){
        Member m1 = new Member();
        m1.setName("멤버1");
        em.persist(m1);

        Product p1 = new Product();
        p1.setName("상품1");
        em.persist(p1);

        MemberProduct mp1 = new MemberProduct();
        mp1.setMember(m1);
        mp1.setProduct(p1);
        mp1.setOrderDate("2025-07-31");
        em.persist(mp1);

        em.flush();
        em.clear();

        MemberProduct mp = em.find(MemberProduct.class,mp1);
        Member m = mp.getMember();
        Product p = mp.getProduct();
        String od = mp.getOrderDate();

        log.info("MEMBER-> {}",m);
        log.info("PRODUCT-> {}",p);
        log.info("ORDER_DATE-> {}",od);
    }

}
