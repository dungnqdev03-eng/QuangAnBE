package com.company.quangan.repository;

import com.company.quangan.payload.Enum.ERole;
import com.company.quangan.payload.entity.BookMark;
import com.company.quangan.payload.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(ERole roleName);

}
