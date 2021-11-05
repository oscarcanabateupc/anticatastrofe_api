package pes.anticatastrofe.aditionalInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AditionalInfoService {
    private final AditionalInfoRepository aditionalInfoRepository;

    @Autowired
    public AditionalInfoService(AditionalInfoRepository aditionalInfoRepository) {this.aditionalInfoRepository = aditionalInfoRepository;
    }

    public List<AditionalInfo> getAditionalInfos() {
        return aditionalInfoRepository.findAll();
    }

    public AditionalInfo addNewAditionalInfo(AditionalInfo aditionalInfo) {
        return aditionalInfoRepository.save(aditionalInfo);
    }

    public void deleteAditionalInfo(String email) {
        aditionalInfoRepository.deleteById(email);
    }

    public Optional<AditionalInfo> findByID(String email) {
        return aditionalInfoRepository.findById(email);
    }
}
