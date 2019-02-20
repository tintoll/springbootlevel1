package io.tintoll.springbootlevel1.service;


import io.tintoll.springbootlevel1.domain.Member;
import io.tintoll.springbootlevel1.domain.MemberAuthority;
import io.tintoll.springbootlevel1.helper.CommonPageInfo;
import io.tintoll.springbootlevel1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService implements UserDetailsService {

    @Autowired private MemberRepository repository;
    @Autowired private PasswordEncoder encoder;

    public CommonPageInfo<Member> findAll(Pageable pageable) {
        Page<Member> all = repository.findAll(pageable);
        return new CommonPageInfo<>(all);//return repository.findAll(pageable);
    }

    public Member save(@Valid Member member) {
        HashSet<MemberAuthority> authorities = new HashSet<>();
        authorities.add(MemberAuthority.USER);

        if(member.getUsername().equals("blue@tigrison.com")) authorities.add(MemberAuthority.ADMIN);

        member.setAuthorities(authorities);

        member.setPassword(encoder.encode(member.getPassword()));
        return repository.save(member);
    }

    public void createDummy() {
        for(int i=0; i< 200; i++) {
            Member member = new Member();
            member.setUsername("user_"+i+"@naver.com");
            member.setPassword("1234");
            save(member);
        }
    }

    static class UserDetailImpl extends User {
        public UserDetailImpl(Member m ) {
            super(m.getUsername(), m.getPassword(), m.getAuthorities());
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       return repository.findByUsername(username)
                .map(m -> new UserDetailImpl(m))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
