package net.obs.repositories;

import net.obs.enums.AgencyTypeEnum;
import net.obs.enums.StateEnum;
import net.obs.models.Agency;
import net.obs.models.Record;
import net.obs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    Record findTopByOrderByIdDesc();
    Record findTopByOrderByIdAsc();
    List<Record> findAllByState(StateEnum state);
    Record findByRecordDatetimeAndAgencyAndStateAndUser(Date time, Agency agency, StateEnum state, User user);


}
