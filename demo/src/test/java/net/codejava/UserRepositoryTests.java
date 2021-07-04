package net.codejava;
 
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests 
{
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository repo;
     
    @Test
    public void testCreateUser() throws IOException 
    {
		File file=new File("C:\\Users\\Pratik Ganjare\\Documents\\bib.jpg");
        User user = new User();
        user.setEmail("ravikumar@gmail.com");
        user.setPassword("ravi2020");
        user.setFirstName("Ravi");
        user.setLastName("Kumar");
        byte[] bytes=Files.readAllBytes(file.toPath());
		user.setFileImg(bytes);
        User savedUser = repo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
         
    }
    
}