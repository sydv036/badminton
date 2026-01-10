package com.example.tob.repository;

import com.example.tob.entity.RoleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRoleRepository extends JpaRepository<RoleAccount, Long> {
}
