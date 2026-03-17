package com.funaki.filaProducao.repository;

import com.funaki.filaProducao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
