package com.example.tob.repository;

import com.example.tob.entity.RoleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IAccountRoleRepository extends JpaRepository<RoleAccount, Long> {

    @Query("""
            SELECT mr.code
            FROM RoleAccount ra
            JOIN MRole mr ON ra.roleId = mr.id
            and mr.systemDeleteFlag = false
            WHERE ra.systemId = :systemId
            and ra.systemDeleteFlag = false
            """)
    Set<String> findBySystemId(Long systemId);

}
