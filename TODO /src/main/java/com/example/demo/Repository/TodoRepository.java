package com.example.demo.Repository;

import com.example.demo.Model.MyUser;
import com.example.demo.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    List<Todo> findTodosByMyUser(MyUser myUser);

    Todo findTodoById(Integer id);

}
