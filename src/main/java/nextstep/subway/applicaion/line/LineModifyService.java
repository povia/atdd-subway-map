package nextstep.subway.applicaion.line;

import nextstep.subway.applicaion.dto.LineRequest;
import nextstep.subway.applicaion.dto.SectionRequest;
import nextstep.subway.domain.Line;
import nextstep.subway.domain.LineRepository;
import nextstep.subway.domain.Section;
import nextstep.subway.exception.DuplicateCreationException;
import nextstep.subway.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LineModifyService {
    private final LineRepository lineRepository;

    public LineModifyService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    public Line saveLine(String name, String color) {
        lineRepository
                .findByName(name)
                .ifPresent(
                        line -> {
                            throw new DuplicateCreationException();
                        });
        return lineRepository.save(new Line(name, color));
    }

    public void updateLine(Long id, LineRequest lineRequest) {
        Line line = lineRepository.findById(id).orElseThrow(NotFoundException::new);
        line.changeLineInformation(lineRequest.getName(), lineRequest.getColor());
    }

    public void addSection(Long id, Section section){
        Line line = lineRepository.findById(id).orElseThrow(NotFoundException::new);
        // TODO 이게 맞는건지 모르겠습니다. 차라리 생성자 주입으로 해서 넣는게 맞는건지...
        section.updateLine(line);
        line.addSection(section);
    }

    public void deleteLine(Long id) {
        lineRepository.deleteById(id);
    }
}
