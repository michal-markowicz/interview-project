package pl.mmarkowicz.interview.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mmarkowicz.interview.schedule.model.RealEstateImportJob;

import java.time.LocalDate;
import java.util.List;

public interface RealEstateJobRepository extends JpaRepository<RealEstateImportJob, Integer> {
    List<RealEstateImportJob> findByDate(LocalDate date);
}
