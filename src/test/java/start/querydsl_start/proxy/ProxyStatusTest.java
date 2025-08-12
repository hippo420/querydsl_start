package start.querydsl_start.proxy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.proxy.eager.Driver;
import start.querydsl_start.proxy.eager.License;
import start.querydsl_start.proxy.lazy.Invoice;
import start.querydsl_start.proxy.lazy.InvoiceLine;

@Slf4j
@SpringBootTest
public class ProxyStatusTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("JPA 프록시 초기화 테스트")
    @Transactional
    @Rollback(false)
    void testProxy()
    {
        InvoiceLine invoiceLine = new InvoiceLine();
        invoiceLine.setName("노트북");
        invoiceLine.setQty(300L);
        em.persist(invoiceLine);

        Invoice invoice = new Invoice();
        invoice.setName("1234-구매청구서");
        invoice.setInvoiceLine(invoiceLine);

        em.persist(invoice);
        em.flush();
        em.clear();

        //조회
        Invoice res1 = em.find(Invoice.class,1L);

        boolean isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(res1);
        log.info("{}-엔티티 프록시로드 : {}",res1.getClass().getSimpleName(),isLoad);

        log.info("#-----Invoice 내역-----#");
        log.info("# 순번 : {}",res1.getId());
        log.info("# 제목 : {}",res1.getName());
        log.info("#-----Invoice 내역 END-----#");

        Long ivId = res1.getInvoiceLine().getId();
        isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(res1.getInvoiceLine());
        log.info("InvocieLine.getId()-엔티티 프록시로드 : {}",isLoad);

        String ivName = res1.getInvoiceLine().getName();
        isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(res1.getInvoiceLine());
        log.info("InvocieLine.getName()-엔티티 프록시로드 : {}",isLoad);


        InvoiceLine iv = em.getReference(InvoiceLine.class,ivId);
        isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(iv);
        log.info("InvoiceLine-엔티티 프록시로드 : {}",isLoad);


        log.info("#-----Invoice 상세내역 -----#");
        log.info("# 물품순번 : {}번",res1.getInvoiceLine().getId());
        log.info("# 물품명 : {}",res1.getInvoiceLine().getName());
        log.info("# 물품수량 : {}EA",res1.getInvoiceLine().getQty());

    }


    @Test
    @DisplayName("JPA 프록시 강제초기화 테스트")
    @Transactional
    @Rollback(false)
    void testProxyForce()
    {

        InvoiceLine invoiceLine = new InvoiceLine();
        invoiceLine.setName("노트북");
        invoiceLine.setQty(300L);
        em.persist(invoiceLine);

        Invoice invoice = new Invoice();
        invoice.setName("1234-구매청구서");
        invoice.setInvoiceLine(invoiceLine);

        em.persist(invoice);
        em.flush();
        em.clear();

        //조회
        Invoice res1 = em.find(Invoice.class,1L);

        boolean isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(res1);
        log.info("{}-엔티티 프록시로드 : {}",res1.getClass().getSimpleName(),isLoad);

        log.info("#-----Invoice 내역-----#");
        log.info("# 순번 : {}",res1.getId());
        log.info("# 제목 : {}",res1.getName());
        log.info("#-----Invoice 내역 END-----#");

        Long ivId = res1.getInvoiceLine().getId();
        isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(res1.getInvoiceLine());
        log.info("InvocieLine.getId()-엔티티 프록시로드 : {}",isLoad);

        org.hibernate.Hibernate.initialize(res1.getInvoiceLine());
        isLoad = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(res1.getInvoiceLine());
        log.info("res1.getInvoiceLine() - 강제 프록시초기화 : {}",isLoad);
    }
}
