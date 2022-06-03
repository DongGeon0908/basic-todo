package kr.ac.hs.todo.dto;

import lombok.Data;

@Data
public class TodoRequest {
    private String title;
    private String content;
}