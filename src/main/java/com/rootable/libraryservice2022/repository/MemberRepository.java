package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m order by m.id desc")
    List<Member> findMembers();

    @Query("select m from Member m where m.loginId = :memberId")
    Member findByLoginId(@Param("memberId") String loginId);



}
