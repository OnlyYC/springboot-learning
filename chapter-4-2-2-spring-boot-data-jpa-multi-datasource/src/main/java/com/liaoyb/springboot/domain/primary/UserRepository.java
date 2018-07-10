package com.liaoyb.springboot.domain.primary;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liaoyb
 * @date 2018-07-07 18:30
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
