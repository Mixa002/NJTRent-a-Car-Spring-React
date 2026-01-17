package com.rentacar.backend.repository;

import com.rentacar.backend.model.RadnikEkspo;
import com.rentacar.backend.model.RadnikEkspoKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RadnikEkspoRepository extends JpaRepository<RadnikEkspo, RadnikEkspoKey> {
    // Spring će ovo prevesti u: WHERE id.radnikID = ?
    List<RadnikEkspo> findById_RadnikID(Integer radnikID);

    // Isto tako možeš i za ekspozituru ako ti zatreba
    List<RadnikEkspo> findById_EkspozituraID(Integer ekspozituraID);

}
