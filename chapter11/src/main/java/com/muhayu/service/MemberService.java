package com.muhayu.service;

import com.muhayu.domain.Member;
import com.muhayu.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by hyecheon on 2017. 6. 12..
 */
@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        final List<Member> byName = memberRepository.findByName(member.getName());
        /*if (!byName.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }*/
        Optional.of(!byName.isEmpty())
                .orElseThrow(() -> new IllegalStateException("이미 존재하는 회원입니다."));
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public List<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
