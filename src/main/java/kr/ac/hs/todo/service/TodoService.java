package kr.ac.hs.todo.service;

import kr.ac.hs.todo.dto.TodoRequest;
import kr.ac.hs.todo.dto.TodoResponse;
import kr.ac.hs.todo.entity.Todo;
import kr.ac.hs.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public TodoResponse create(TodoRequest request) {
        // 투두 생성
        Todo instance = new Todo(request.getTitle(), request.getContent());
        Todo todo = todoRepository.save(instance); // 생성된 todo를 반환 (id값을 얻기 위해 반환 값 이용)

        return new TodoResponse(todo);
    }

    @Transactional
    public TodoResponse update(Long id, TodoRequest request) {
        // id값으로 조회가 되지 않으면 에러를 내보냄
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));

        Todo updatedTodo = todo.update(request.getTitle(), request.getContent());

        return new TodoResponse(updatedTodo);
    }

    @Transactional
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public TodoResponse get(Long id) {
        // id값으로 조회가 되지 않으면 에러를 내보냄
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));

        return new TodoResponse(todo);
    }

    @Transactional(readOnly = true)
    public Page<TodoResponse> getAll(Pageable pageable) {
        // pageable로 todo 조회
        Page<Todo> todo = todoRepository.findAll(pageable);

        // entity를 그대로 반환하기보다는 한번 감쌈
        Page<TodoResponse> todoResponses = todo.map(TodoResponse::new);

        return todoResponses;
    }
}