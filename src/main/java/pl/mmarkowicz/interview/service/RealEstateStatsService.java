package pl.mmarkowicz.interview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mmarkowicz.interview.model.Region;
import pl.mmarkowicz.interview.repository.RealEstateEntityRepository;
import pl.mmarkowicz.interview.web.query.FindRealEstateStatsQuery;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RealEstateStatsService {

    private final RealEstateEntityRepository realEstateEntityRepository;

    public BigDecimal countAveragePrice(String regionId, FindRealEstateStatsQuery query) {
        return realEstateEntityRepository.countAveragePriceForCriteria(query.getSizeFrom(), query.getSizeTo(), query.getTypes().split(","), query.getDateFrom(), query.getDateTo(), Region.valueOf(regionId));
    }
}
