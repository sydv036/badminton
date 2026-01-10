package com.example.tob.repository;

import com.example.tob.dtos.responses.auth.MemberInfoResponse;
import com.example.tob.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    @Query(value = """
                SELECT 
                    ac.systemId as systemId,
                    ac.userName as email,
                    me.phoneNumber as phoneNumber
                FROM Account ac 
                JOIN Member me on ac.systemId = me.systemId
                and me.systemDeleteFlag = false 
                where ac.userName = :userName
                and ac.systemDeleteFlag = false
            """)
    MemberInfoResponse findByUserName(@Param("userName") String userName);

}
