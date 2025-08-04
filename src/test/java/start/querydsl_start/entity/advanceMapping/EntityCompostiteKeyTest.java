package start.querydsl_start.entity.advanceMapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.entity.advancedMapping.composite.noidentify.idclass.User;
import start.querydsl_start.entity.advancedMapping.composite.noidentify.idclass.UserAddress;
import start.querydsl_start.entity.advancedMapping.composite.noidentify.idclass.UserId;
import start.querydsl_start.entity.advancedMapping.inherit.concreate.Kia;
import start.querydsl_start.entity.advancedMapping.inherit.concreate.Porsche;
import start.querydsl_start.entity.advancedMapping.inherit.join.Book;
import start.querydsl_start.entity.advancedMapping.inherit.join.Item;
import start.querydsl_start.entity.advancedMapping.inherit.join.Movie;
import start.querydsl_start.entity.advancedMapping.inherit.single.Galaxy;
import start.querydsl_start.entity.advancedMapping.inherit.single.IPhone;
import start.querydsl_start.entity.advancedMapping.inherit.single.Phone;
import start.querydsl_start.entity.advancedMapping.inherit.superclass.Buyer;
import start.querydsl_start.entity.advancedMapping.inherit.superclass.Seller;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
public class EntityCompostiteKeyTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("엔티티 비식별관계 - [IdClass] 테스트")
    @Transactional
    @Rollback(false)
    void testNonIdentify_IdClass()
    {
        User user = new User();
        user.setId1(1L);
        user.setId2(2L);
        user.setName("사용자1");
        em.persist(user);

        // 2. 사용자 주소(UserAddress) 생성
        UserAddress address = new UserAddress();
        address.setUser(user);
        em.persist(address);


        em.flush();
        em.clear();

        //조회
        UserAddress foundAddress = em.find(UserAddress.class, address.getId());
        System.out.println("조회된 주소: " + foundAddress);
        System.out.println("주소에 연결된 사용자: " + foundAddress.getUser());
    }

    @Test
    @DisplayName("엔티티 비식별관계 - [Embedded] 테스트")
    @Transactional
    @Rollback(false)
    void testNonIdentify_Embedded()
    {


        em.persist(null);
        em.persist(null);

        em.flush();
        em.clear();

        //조회
        List<Movie> res1 = em.createQuery("select m from Movie m", Movie.class)
                .getResultList();
        //조회
        List<Book> res2 = em.createQuery("select m from Book m", Book.class)
                .getResultList();
        //조회, DTYPE이 설정된 경우, JPA가 각 행에 저장된 클래스 타입을 구분하는 컬럼을 자동 생성
        List<Item> res3 = em.createQuery("select m from Item m", Item.class)
                .getResultList();

        res1.stream().forEach(r1 -> log.info("Result1: {}", r1));
        res2.stream().forEach(r2 -> log.info("Result2: {}", r2));
        res3.stream().forEach(r3 -> log.info("Result3: {}", r3));
    }

    @Test
    @DisplayName("엔티티 식별관계 - [IdClass] 테스트")
    @Transactional
    @Rollback(false)
    void testIdentify_IdClass()
    {


        em.persist(null);
        em.persist(null);

        em.flush();
        em.clear();

        //조회
        List<Movie> res1 = em.createQuery("select m from Movie m", Movie.class)
                .getResultList();
        //조회
        List<Book> res2 = em.createQuery("select m from Book m", Book.class)
                .getResultList();
        //조회, DTYPE이 설정된 경우, JPA가 각 행에 저장된 클래스 타입을 구분하는 컬럼을 자동 생성
        List<Item> res3 = em.createQuery("select m from Item m", Item.class)
                .getResultList();

        res1.stream().forEach(r1 -> log.info("Result1: {}", r1));
        res2.stream().forEach(r2 -> log.info("Result2: {}", r2));
        res3.stream().forEach(r3 -> log.info("Result3: {}", r3));
    }

    @Test
    @DisplayName("엔티티 식별관계 - [Embedded] 테스트")
    @Transactional
    @Rollback(false)
    void testIdentify_Embedded()
    {


        em.persist(null);
        em.persist(null);

        em.flush();
        em.clear();

        //조회
        List<Movie> res1 = em.createQuery("select m from Movie m", Movie.class)
                .getResultList();
        //조회
        List<Book> res2 = em.createQuery("select m from Book m", Book.class)
                .getResultList();
        //조회, DTYPE이 설정된 경우, JPA가 각 행에 저장된 클래스 타입을 구분하는 컬럼을 자동 생성
        List<Item> res3 = em.createQuery("select m from Item m", Item.class)
                .getResultList();

        res1.stream().forEach(r1 -> log.info("Result1: {}", r1));
        res2.stream().forEach(r2 -> log.info("Result2: {}", r2));
        res3.stream().forEach(r3 -> log.info("Result3: {}", r3));
    }


}
