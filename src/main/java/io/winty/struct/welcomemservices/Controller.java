package io.winty.struct.welcomemservices;

import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Stream;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log
@RestController
@RequestMapping("/")
public class Controller {
    
    private final MessageRepository repository;
    
    public Controller(MessageRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping
    public Mono<Stream<MessageDTO>> list(){
        log.log(Level.INFO, "LIST");
        return Mono.fromSupplier(()-> repository.findAll().stream().map(MessageDTO::new)).log();   
    }   
    
    @GetMapping("/{id}") 
    public Mono<MessageDTO> get( @PathVariable("id") String guildId ){
        log.log(Level.INFO, "GET: {0}", guildId);
        return Mono.fromSupplier(()-> Optional.ofNullable(repository.findFirstByGuild(guildId)).orElseThrow().toDto()).log();
    }
    
    @PostMapping
    @Transactional
    public Mono<ResponseEntity<MessageDTO>> post(@RequestBody MessageDTO dto , UriComponentsBuilder uriBuilder){
        log.log(Level.INFO, "POST: {0}", dto);
        return Mono.fromSupplier(() -> {
            Message message = new Message(dto);
            repository.save(message);
            var uri = uriBuilder.path("/welcome-messages/{id}").buildAndExpand(message.getId()).toUri();
            return ResponseEntity.created(uri).body(new MessageDTO(message));
        }).log();
    }
    
    @PutMapping("/{id}")
    @Transactional
    public Mono<ResponseEntity<MessageDTO>> putMethodName(@PathVariable("id") String guildId, @RequestBody MessageDTO dto) {
        return Mono.fromSupplier(()->{
            Message message = Optional.ofNullable(repository.findFirstByGuild(guildId)).orElseThrow();
            
            message.update(dto);
            repository.save(message);
            return ResponseEntity.status(HttpStatusCode.valueOf(202)).body(message.toDto());
        }).log();
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public Mono<ResponseEntity<Object>> delete(@PathVariable("id") String guildId) {
        log.log(Level.INFO, "DELETING ID: {0}",guildId);
        return Mono.fromSupplier(()->{
             Message message = repository.findFirstByGuild(guildId);
             repository.delete(message);
             return ResponseEntity.noContent().build();
        }).log();
    }
    
}
