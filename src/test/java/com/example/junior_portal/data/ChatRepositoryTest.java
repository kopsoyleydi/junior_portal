package com.example.junior_portal.data;


import com.example.junior_portal.data.impl.inter.ChatMessageRepoInter;
import com.example.junior_portal.data.impl.inter.ChatRoomRepoInter;
import com.example.junior_portal.data.repository.ChatRoomRepository;
import com.example.junior_portal.model.chat.ChatRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
public class ChatRepositoryTest {

    @Autowired private ChatRoomRepoInter chatRoomRepoInter;

    @Autowired private ChatRoomRepository chatRoomRepository;

    @Autowired private ChatMessageRepoInter chatMessageRepoInter;


    @Test
    void createRoomRepositoryTest(){
        Long id = 1L;
        Long senderId = 2L;
        Long recipientId = 3L;


        ChatRoom chatRoom = ChatRoom.builder()
                .chatId(id).senderId(senderId).recipientId(recipientId)
                .build();


        chatRoomRepository.save(chatRoom);

        Optional<ChatRoom> getChatRoom = chatRoomRepository.findById(1L);

        assertThat(getChatRoom).isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c).isEqualTo(chatRoom);
                });
    }

    @Test
    void findBySenderIdAndRecipientIdTest(){
        Long id = 1L;
        Long senderId = 2L;
        Long recipientId = 3L;

        ChatRoom chatRoom = ChatRoom.builder()
                .recipientId(recipientId).senderId(senderId)
                .chatId(id).build();

        chatRoomRepository.save(chatRoom);

        ChatRoom getChatRoom = chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId);


        assertThat(getChatRoom).isEqualTo(chatRoom);
    }
}
