package start.querydsl_start.entity.advanceMapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
public class EntityInheritTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("엔티티 상속매핑 - [조인전략] 테스트")
    @Transactional
    @Rollback(false)
    void testInheritanceByJoin()
    {
        Movie movie = new Movie("봉준호");
        movie.setName("기생충");
        movie.setPrice("15,000");
        
        Book book = new Book("김영하");
        book.setName("단 한번의 삶");
        book.setPrice("12,000");

        em.persist(movie);
        em.persist(book);

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
    @DisplayName("엔티티 상속매핑 - [단일테이블전략] 테스트")
    @Transactional
    @Rollback(false)
    void testInheritanceBySingleTable()
    {
        IPhone iPhone = new IPhone("사용가능");
        iPhone.setName("IPhone16 pro");
        iPhone.setPrice("1,600,000");

        Galaxy galaxy = new Galaxy("사용가능");
        galaxy.setName("갤러시 25 울트라");
        galaxy.setPrice("2,100,000");

        em.persist(iPhone);
        em.persist(galaxy);

        em.flush();
        em.clear();

        //조회
        List<IPhone> res1 = em.createQuery("select m from IPhone m", IPhone.class)
                .getResultList();
        //조회
        List<Galaxy> res2 = em.createQuery("select m from Galaxy m", Galaxy.class)
                .getResultList();

        //조회, DTYPE이 설정된 경우, JPA가 각 행에 저장된 클래스 타입을 구분하는 컬럼을 자동 생성
        List<Phone> res3 = em.createQuery("select m from Phone m", Phone.class)
                .getResultList();

        res1.stream().forEach(r1 -> log.info("Result1: {}", r1));

        res2.stream().forEach(r2 -> log.info("Result2: {}", r2));

        res3.stream().forEach(r3 -> log.info("Result3: {}", r3));
    }

    @Test
    @DisplayName("엔티티 상속매핑 - [클래스구현전략] 테스트")
    @Transactional
    @Rollback(false)
    void testInheritanceByConcreate()
    {
        Kia car1 = new Kia("K5","3000천만원");
        Porsche car2 = new Porsche("porsche911","2억","F1 팀 있음");
        em.persist(car1);
        em.persist(car2);
        em.flush();
        em.clear();

        //조회
        List<Kia> res1 = em.createQuery("select m from Kia m", Kia.class)
                .getResultList();
        List<Porsche> res2 = em.createQuery("select m from Porsche m", Porsche.class)
                .getResultList();

        res1.stream().forEach(r1 -> log.info("Result1: {}", r1));
        res2.stream().forEach(r2 -> log.info("Result2: {}", r2));
    }

    @Test
    @DisplayName("엔티티 상속매핑 - [MappingSuperclass] 테스트")
    @Transactional
    @Rollback(false)
    void testInheritanceByMappingSuperclass()
    {
        Buyer buyer = new Buyer();
        buyer.setName("젠슨황");
        buyer.setCompany("엔디비아");
        buyer.setAccount(BigDecimal.valueOf(20310389327819L));
        Seller seller = new Seller();

        seller.setName("판매자");
        seller.setCompany("삼성전자");
        seller.setCertification("QA테스트완료");
        em.persist(buyer);
        em.persist(seller);
        em.flush();
        em.clear();

        //조회
        List<Buyer> res1 = em.createQuery("select m from Buyer m", Buyer.class)
                .getResultList();
        List<Seller> res2 = em.createQuery("select m from Seller m", Seller.class)
                .getResultList();

        res1.stream().forEach(r1 -> log.info("Result1: {}", r1));
        res2.stream().forEach(r2 -> log.info("Result2: {}", r2));
    }
}
