package start.querydsl_start.realationMapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.realation.onetomany.Attachment;
import start.querydsl_start.realation.onetomany.AttachmentV1;
import start.querydsl_start.realation.onetomany.Post;
import start.querydsl_start.realation.onetomany.PostV1;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@SpringBootTest
public class OneToManyTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("다대일 - 단방향매핑 테스트")
    @Transactional
    @Rollback(false)
    void testManyToOne()
    {
        Attachment a1 = new Attachment();
        Attachment a2 = new Attachment();
        Attachment a3 = new Attachment();
        a1.setName("법인증명서");
        a2.setName("계약서");
        a3.setName("통장사본");
        List<Attachment> attachments = new ArrayList<Attachment>();
        attachments.add(a1);
        attachments.add(a2);
        attachments.add(a3);

        em.persist(a1);
        em.persist(a2);
        em.persist(a3);

        Post p1 = new Post();
        p1.setName("물품 계약건");
        p1.setAttachments(attachments);
        em.persist(p1);

        em.flush();
        em.clear();

        //조회
        Post post = em.find(Post.class,1L);

        log.info("게시물 : {}", post);
        post.getAttachments().forEach(a -> {log.info("첨부 : {}",a);});

        //log.info("첨부파일에서 게시물조회 불가 : {}", post.getAttachments().get(0).getPost());
    }


    @Test
    @DisplayName("다대일 - 양방향매핑 테스트")
    @Transactional
    @Rollback(false)
    void testManyToOne_OneToMany()
    {
        AttachmentV1 att1 = new AttachmentV1();
        att1.setName("파일1");
        em.persist(att1); //cascase시 불필요

        AttachmentV1 att2 = new AttachmentV1();
        att2.setName("파일2");
        em.persist(att2); //cascase시 불필요

        // 저장
        PostV1 post = new PostV1();
        post.setName("게시글1");
        post.getAttachments().add(att1);
        post.getAttachments().add(att2);

        em.persist(post); //cascase시 일괄 persist

        em.flush();
        em.clear();

        //조회
        PostV1 foundPost = em.find(PostV1.class, post.getId());
        log.info("게시물에서 첨부파일 조회 :{}",foundPost.getAttachments());

        AttachmentV1 foundAttachment = em.find(AttachmentV1.class, att1.getId());
        log.info("첨부파일에서 게시물 조회 :{}",foundAttachment.getPost());

    }

}
