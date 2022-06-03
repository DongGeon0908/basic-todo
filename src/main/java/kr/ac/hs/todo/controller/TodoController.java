package kr.ac.hs.todo.controller;

import kr.ac.hs.todo.dto.TodoRequest;
import kr.ac.hs.todo.dto.TodoResponse;
import kr.ac.hs.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // 생성
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TodoRequest request) {
        TodoResponse response = todoService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 수정
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody TodoRequest request
    ) {
        TodoResponse response = todoService.update(id, request);

        return ResponseEntity.ok(response);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        todoService.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    // 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        TodoResponse response = todoService.get(id);

        return ResponseEntity.ok(response);
    }

    // 전체 조회 (페이지 조회)
    @GetMapping
    public ResponseEntity<?> getAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<TodoResponse> response = todoService.getAll(pageable);

        return ResponseEntity.ok(response);
    }
}
