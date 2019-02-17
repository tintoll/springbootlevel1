package io.tintoll.springbootlevel1.service;


import io.tintoll.springbootlevel1.domain.Member;
import io.tintoll.springbootlevel1.helper.CommonPageInfo;
import io.tintoll.springbootlevel1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired private MemberRepository repository;


    public CommonPageInfo<Member> findAll(Pageable pageable) {
        Page<Member> all = repository.findAll(pageable);
        return new CommonPageInfo<Member>(all);//return repository.findAll(pageable);
    }

    public Member save(@Valid Member member) {
        return repository.save(member);
    }

    public void createDummy() {
        for(int i=0; i< 200; i++) {
            Member member = new Member();
            member.setUsername("user_"+i+"@naver.com");
            member.setPassword("1234");
            repository.save(member);
        }
    }
}
