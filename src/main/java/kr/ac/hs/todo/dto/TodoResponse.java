package kr.ac.hs.todo.dto;

import kr.ac.hs.todo.entity.Todo;
import lombok.Data;

@Data
public class TodoResponse {
    private Long id;
    private String title;
    private String content;

    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
    }
}