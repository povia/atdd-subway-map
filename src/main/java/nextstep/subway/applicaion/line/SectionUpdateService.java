package nextstep.subway.applicaion.line;

import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.applicaion.line.LineModifyService;
import nextstep.subway.applicaion.station.StationReadService;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Station;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SectionUpdateService {
  private final LineModifyService lineModifyService;
  private final StationReadService stationReadService;

  public SectionUpdateService(LineModifyService lineModifyService, StationReadService stationReadService) {
    this.lineModifyService = lineModifyService;
    this.stationReadService = stationReadService;
  }

  public void addSection(Long id, SectionRequest sectionRequest){
    Objects.nonNull(sectionRequest.getUpStationId());
    Objects.nonNull(sectionRequest.getDownStationId());
    Objects.nonNull(sectionRequest.getDistance());

    Station upStation = stationReadService.findSpecificStation(sectionRequest.getUpStationId());
    Station downStation = stationReadService.findSpecificStation(sectionRequest.getDownStationId());
    Section section = new Section(upStation, downStation, sectionRequest.getDistance());
    lineModifyService.addSection(id, section);
  }

  public void deleteSection(Long id, Long stationId){
    Station downStation = stationReadService.findSpecificStation(stationId);
    lineModifyService.deleteSection(id, stationId);
  }
}
