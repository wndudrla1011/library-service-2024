package com.rootable.libraryservice2024.repository;

import com.rootable.libraryservice2024.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m order by m.id desc")
    List<Member> findMembers();

    @Query("select m from Member m where m.username = :memberId")
    Member findByLoginId(@Param("memberId") String username);

    @Query("select m from Member m where m.id <> :id and m.username = :memberId")
    Member checkDuplicatedLoginId(@Param("id") Long id,
                                  @Param("memberId") String username);

    @Query("select m from Member m where m.email = :memberEmail")
    Member findByEmail(@Param("memberEmail") String email);

    @Query("select m from Member m where m.username = :memberId and m.password = :pw")
    Member certify(@Param("memberId") String username,
                   @Param("pw") String password);


}
