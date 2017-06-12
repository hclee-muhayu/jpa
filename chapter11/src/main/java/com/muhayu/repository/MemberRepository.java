package com.muhayu.repository;

import com.muhayu.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by hyecheon on 2017. 6. 12..
 */
@Repository
public class MemberRepository {

    @PersistenceContext
    private
    EntityManager entityManager;

    public void save(Member member) {
        entityManager.persist(member);
    }

    public Member findOne(Long id) {
        return entityManager.find(Member.class, id);
    }

    public List<Member> findAll() {
        return entityManager.createQuery("SELECT m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return entityManager
                .createQuery("SELECT m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }
}
