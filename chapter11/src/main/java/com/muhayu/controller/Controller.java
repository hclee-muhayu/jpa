package com.muhayu.controller;

import com.muhayu.domain.Member;
import com.muhayu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyecheon on 2017. 6. 13..
 */
@RestController
@RequestMapping("/")
public class Controller {
    @Autowired
    MemberService memberService;

    @RequestMapping("/")
    public String test() {
        final Member member = new Member();
        member.setName("kim");
        memberService.join(member);
        System.out.println(memberService.findByName("kim"));
        return String.valueOf(memberService.findMembers());
    }
}
