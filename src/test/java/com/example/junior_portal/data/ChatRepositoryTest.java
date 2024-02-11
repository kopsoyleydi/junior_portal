package com.example.junior_portal.data;


import com.example.junior_portal.data.impl.inter.ChatMessageRepoInter;
import com.example.junior_portal.data.impl.inter.ChatRoomRepoInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
public class ChatRepositoryTest {

    @Autowired
    private ChatRoomRepoInter chatRoomRepoInter;

    @Autowired private ChatMessageRepoInter chatMessageRepoInter;
}
