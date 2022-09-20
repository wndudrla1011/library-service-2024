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

    @Query("select m from Member m where m.id <> :id and m.loginId = :memberId")
    Member checkDuplicatedLoginId(@Param("id") Long id,
                                  @Param("memberId") String loginId);

    @Query("select m from Member m where m.email = :memberEmail")
    Member findByEmail(@Param("memberEmail") String email);

    @Query("select m from Member m where m.loginId = :memberId and m.password = :pw")
    Member certify(@Param("memberId") String loginId,
                   @Param("pw") String password);


}
