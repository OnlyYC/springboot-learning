package com.liaoyb.springboot.domain.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liaoyb
 * @date 2018-07-07 22:18
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}
