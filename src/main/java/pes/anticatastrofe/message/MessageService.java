package pes.anticatastrofe.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(int id) {
        return messageRepository.findById(id);
    }

    public Message addNewMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }

    public Optional<Message> findByID(int id) {
        return messageRepository.findById(id);
    }
}
