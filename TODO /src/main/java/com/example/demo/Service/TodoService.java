package com.example.demo.Service;

import com.example.demo.Model.MyUser;
import com.example.demo.Model.Todo;
import com.example.demo.Repository.AuthRepository;
import com.example.demo.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final AuthRepository authRepository;

    public List<Todo> getTodos(Integer id) {
        return todoRepository.findTodosByMyUser(authRepository.findMyUserById(id));

    }

    public void addTodo(Integer userId, Todo todo) {
        todo.setMyUser(authRepository.findMyUserById(userId));
        todoRepository.save(todo);
    }

    public void updateTodo(Integer userId, Todo todo, Integer idTodo) {
        Todo oldTodo = todoRepository.findTodoById(idTodo);
        if (oldTodo == null) {
            return;
        }
        if (oldTodo.getMyUser().getId().equals(userId)) {
            return;
        }
        oldTodo.setMessage(todo.getMessage());
        todoRepository.save(oldTodo);
    }

    public void deleteTodo(Integer userId, Integer idTodo) {
        Todo todo = todoRepository.findTodoById(idTodo);
        if (todo == null) {
            return;
        }
        if (todo.getMyUser().getId().equals(userId)) {
            return;
        }
        todoRepository.delete(todo);

    }

}
