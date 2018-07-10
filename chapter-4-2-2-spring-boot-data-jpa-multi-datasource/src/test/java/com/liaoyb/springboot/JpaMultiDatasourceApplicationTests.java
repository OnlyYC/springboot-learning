package com.liaoyb.springboot;

import com.liaoyb.springboot.domain.primary.User;
import com.liaoyb.springboot.domain.primary.UserRepository;
import com.liaoyb.springboot.domain.secondary.Book;
import com.liaoyb.springboot.domain.secondary.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaMultiDatasourceApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

	@Test
	public void test() {
	    userRepository.deleteAll();
        bookRepository.deleteAll();

		userRepository.save(new User("逃避", 23));
        Assert.assertEquals(1, userRepository.count());

        bookRepository.save(new Book("java编程", "unknown"));
        Assert.assertEquals(1, bookRepository.count());
	}

}
