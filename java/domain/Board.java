package kr.or.dcca.dcca.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @Builder
    public Board(Long id, String title, String content, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
