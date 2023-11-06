package io.winty.struct.welcomemservices;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "welcome_messages")
@Entity(name="Message")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guild; 
    private String content;
    private LocalDateTime data;
    
    public MessageDTO toDto(){
        return new MessageDTO(guild, content);
    }
    
    public void update(MessageDTO dto){
        content = dto.content();
        data = LocalDateTime.now();
    }

    public Message(MessageDTO dto) {
        guild = dto.guild();
        content = dto.content();
        data = LocalDateTime.now();
    }
}
