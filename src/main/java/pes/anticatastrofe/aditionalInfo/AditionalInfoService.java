package pes.anticatastrofe.aditionalInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AditionalInfoService {
    private final AditionalInfoRepository aditionalInfoRepository;

    @Autowired
    public AditionalInfoService(AditionalInfoRepository aditionalInfoRepository) {this.aditionalInfoRepository = aditionalInfoRepository;
    }

    public List<AditionalInfo> getAditionalInfos() {
        return aditionalInfoRepository.findAll();
    }

    public void addNewAditionalInfo(AditionalInfo aditionalInfo) {
        aditionalInfoRepository.save(aditionalInfo);
    }

    public void deleteAditionalInfo(String email) {
        aditionalInfoRepository.deleteById(email);
    }
}
