package com.muhayu.service;

import com.muhayu.domain.Member;
import com.muhayu.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by hyecheon on 2017. 6. 13..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void join() throws Exception {
        final Member member = new Member();
        member.setName("kim");
        Long saveId = memberService.join(member);
        assertEquals(member.getName(), memberRepository.findOne(saveId).getName());
    }

    @Test(expected = IllegalStateException.class)
    public void duplicationMember() throws Exception {
        final Member member1 = new Member();
        final String kim = "kim";
        member1.setName(kim);
        final Member member2 = new Member();
        member2.setName(kim);
        memberService.join(member1);
        memberService.join(member2);
        fail("예외가 발생해야 됩니다.");
    }

    @Test
    public void findMembers() {
    }

    @Test
    public void findOne() {
    }
}
