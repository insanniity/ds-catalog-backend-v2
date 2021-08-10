package com.insannity.dscatalog.repositories;

import com.insannity.dscatalog.entities.Role;
import com.insannity.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}