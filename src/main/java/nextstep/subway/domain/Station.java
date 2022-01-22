package nextstep.subway.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// 쿼리 캐싱이 안되는건 어쩔 수 없지만, 전체를 업데이트하다가 사이드 이펙트가 날 수 있기 때문에 미리 DynamicUpdate를 적용한다.
@DynamicUpdate
public class Station extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Station() {}

    public Station(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
