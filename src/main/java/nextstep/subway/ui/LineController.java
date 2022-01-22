package nextstep.subway.ui;

import java.net.URI;
import java.util.List;
import nextstep.subway.applicaion.LineService;
import nextstep.subway.applicaion.dto.LineRequest;
import nextstep.subway.applicaion.dto.LineResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lines")
public class LineController {
    private final LineService lineService;

    public LineController(LineService lineService) {
        this.lineService = lineService;
    }

    @PostMapping
    public ResponseEntity<LineResponse> createLine(@RequestBody LineRequest lineRequest) {
        LineResponse line = lineService.saveLine(lineRequest);
        return ResponseEntity.created(URI.create("/lines/" + line.getId())).body(line);
    }

    @GetMapping
    public ResponseEntity<List<LineResponse>> getAllLines() {
        List<LineResponse> lines = lineService.findAllLines();
        return ResponseEntity.ok().body(lines);
    }

    @GetMapping("{id}")
    public ResponseEntity<LineResponse> getLine(@PathVariable Long id) {
        LineResponse line = lineService.findSpecificLine(id);
        return ResponseEntity.ok().body(line);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateLine(@PathVariable Long id, @RequestBody LineRequest lineRequest){
        lineService.updateLine(id, lineRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLine(@PathVariable Long id){
        lineService.deleteLine(id);
        return ResponseEntity.noContent().build();
    }
}
