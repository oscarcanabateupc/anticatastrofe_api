package pes.anticatastrofe.messageWithCoordinates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pes.anticatastrofe.messageWithCoordinates.MessageWithCoordinates;
import pes.anticatastrofe.messageWithCoordinates.MessageWithCoordinatesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageWithCoordinatesService {
    private final MessageWithCoordinatesRepository messageWithCoordinatesRepository;

    @Autowired
    public MessageWithCoordinatesService(MessageWithCoordinatesRepository messageWithCoordinatesRepository) {
        this.messageWithCoordinatesRepository = messageWithCoordinatesRepository;
    }

    public List<MessageWithCoordinates> getMessages() {
        return messageWithCoordinatesRepository.findAll();
    }

    public Optional<MessageWithCoordinates> getMessageById(int id) {
        return messageWithCoordinatesRepository.findById(id);
    }

    public MessageWithCoordinates addNewMessage(MessageWithCoordinates message) {
        return messageWithCoordinatesRepository.save(message);
    }

    public void deleteMessage(int id) {
        messageWithCoordinatesRepository.deleteById(id);
    }

    public Optional<MessageWithCoordinates> findByID(int id) {
        return messageWithCoordinatesRepository.findById(id);
    }
}
