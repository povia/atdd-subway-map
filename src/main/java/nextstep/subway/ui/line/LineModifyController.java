package nextstep.subway.ui.line;

import java.net.URI;
import nextstep.subway.applicaion.dto.LineCreateResponse;
import nextstep.subway.applicaion.dto.LineRequest;
import nextstep.subway.applicaion.line.LineCreationService;
import nextstep.subway.applicaion.line.LineModifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lines")
public class LineModifyController {

    private final LineCreationService lineCreationService;

    private final LineModifyService lineModifyService;

    public LineModifyController(
            LineCreationService lineCreationService, LineModifyService lineModifyService) {
        this.lineCreationService = lineCreationService;
        this.lineModifyService = lineModifyService;
    }

    @PostMapping
    public ResponseEntity<LineCreateResponse> createLine(@RequestBody LineRequest lineRequest) {
        LineCreateResponse line = lineCreationService.saveLine(lineRequest);
        return ResponseEntity.created(URI.create("/lines/" + line.getId())).body(line);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateLine(
            @PathVariable Long id, @RequestBody LineRequest lineRequest) {
        lineModifyService.updateLine(id, lineRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLine(@PathVariable Long id) {
        lineModifyService.deleteLine(id);
        return ResponseEntity.noContent().build();
    }
}
