package kr.ac.hs.todo.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자는 무조건 필요함! 이게 규칙
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    public Todo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Todo update(String title, String content) {
        this.title = title;
        this.content = content;

        return this;
    }
}