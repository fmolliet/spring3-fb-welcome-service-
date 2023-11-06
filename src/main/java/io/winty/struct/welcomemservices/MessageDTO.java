package io.winty.struct.welcomemservices;

public record MessageDTO( String guild, String content) {
    
    public MessageDTO(Message message){
        this(message.getGuild(), message.getContent());
    }
}
