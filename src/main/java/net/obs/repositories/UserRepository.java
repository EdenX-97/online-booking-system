package net.obs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.obs.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findTopByOrderByIdDesc();

    User findTopByOrderByIdAsc();

    User findByEmail(String email);

    User findByAccount(String account);
}
