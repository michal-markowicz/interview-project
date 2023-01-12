package pl.mmarkowicz.interview.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mmarkowicz.interview.exception.InvalidQueryParameters;
import pl.mmarkowicz.interview.model.Region;
import pl.mmarkowicz.interview.service.RealEstateStatsService;
import pl.mmarkowicz.interview.web.query.FindRealEstateStatsQuery;
import pl.mmarkowicz.interview.web.response.RealEstateAveragePriceStatsResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/real-estates-stats")
@RequiredArgsConstructor
@Slf4j
public class RealEstateStatsController {

    private final RealEstateStatsService realEstateStatsService;

    @GetMapping("/{regionId}")
    public ResponseEntity getAveragePrice(@PathVariable String regionId, @ModelAttribute FindRealEstateStatsQuery query) {
        log.info("getAveragePrice({},{})", regionId, query);
        validateInput(regionId, query);
        return ResponseEntity.ok(new RealEstateAveragePriceStatsResponse(realEstateStatsService.countAveragePrice(regionId, query)));
    }

    private void validateInput(String regionId, FindRealEstateStatsQuery query) {
        List<String> errors = new ArrayList<>();
        if (Arrays.stream(Region.values()).map(Region::name).noneMatch(name -> name.equals(regionId))) {
            errors.add("UNKNOWN_REGION");
        }
        if (query.getSize() == null || !Set.of("S", "M", "L").contains(query.getSize())) {
            errors.add("UNKNOWN_SIZE");
        }
        if (query.getTypes() == null || query.getTypes().split(",").length == 0) {
            errors.add("MISSING_TYPES");
        }
        if (query.getDateSince() == null || !query.getDateSince().matches("\\d{8}")) {
            errors.add("INVALID_DATE_SINCE");
        }
        if (query.getDateUntil() == null || !query.getDateUntil().matches("\\d{8}")) {
            errors.add("INVALID_DATE_UNTIL");
        }
        if (!errors.isEmpty()) {
            throw new InvalidQueryParameters(errors);
        }
    }
}
