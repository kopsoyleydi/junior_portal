package com.example.junior_portal.data;


import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepoInter profileRepoInter;
}
