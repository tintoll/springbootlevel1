package io.tintoll.springbootlevel1.service;


import io.tintoll.springbootlevel1.domain.Member;
import io.tintoll.springbootlevel1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired private MemberRepository repository;


    public List<Member> findAll() {
        return repository.findAll();
    }

    public Member save(@Valid Member member) {
        return repository.save(member);
    }
}
