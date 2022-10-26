package net.obs.repositories;

import net.obs.enums.AgencyTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import net.obs.models.Agency;

import java.util.List;

public interface AgencyRepository extends JpaRepository<Agency, Integer> {
    Agency findTopByOrderByIdDesc();
    Agency findTopByOrderByIdAsc();
    Agency findByType(AgencyTypeEnum type);
    List<Agency> findAllByType(AgencyTypeEnum type);
    Agency findByAgencyName(String name);

}

