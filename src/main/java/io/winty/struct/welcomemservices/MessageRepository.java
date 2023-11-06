package io.winty.struct.welcomemservices;

import org.springframework.data.jpa.repository.JpaRepository;

interface MessageRepository extends JpaRepository<Message, Long> {
    
    Message findFirstByGuild(String guild); 
}
