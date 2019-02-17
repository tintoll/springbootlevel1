package io.tintoll.springbootlevel1.repository;

import io.tintoll.springbootlevel1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
