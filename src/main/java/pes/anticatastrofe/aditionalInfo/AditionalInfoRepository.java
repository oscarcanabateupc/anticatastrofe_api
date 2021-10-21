package pes.anticatastrofe.aditionalInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AditionalInfoRepository extends JpaRepository<AditionalInfo,String> {
}
