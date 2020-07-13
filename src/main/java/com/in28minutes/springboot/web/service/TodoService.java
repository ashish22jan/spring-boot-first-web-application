package com.in28minutes.springboot.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.in28minutes.springboot.web.model.Todo;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoCount = 3;

	static {
		todos.add(new Todo(1, "Ashish", "Morgan Stanley", new Date(), false));
		todos.add(new Todo(2, "Ashish", "Goldman Sachs", new Date(), false));
		todos.add(new Todo(3, "Ashish", "Manhattan Associates", new Date(), false));
		todos.add(new Todo(4, "Ashish", "State Street", new Date(), false));
		todos.add(new Todo(5, "Ashish", "Walmart Labs", new Date(), false));
		todos.add(new Todo(6, "Ashish", "Paypal", new Date(), false));
		todos.add(new Todo(7, "Ashish", "Societe Generale", new Date(), false));
		todos.add(new Todo(8, "Ashish", "CGI", new Date(), false));
		todos.add(new Todo(9, "Ashish", "AppDynamics", new Date(), false));
		todos.add(new Todo(10, "Ashish", "J.P Morgan Chase & Co.", new Date(), false));
		todos.add(new Todo(11, "Ashish", "Majid Al Futtaim", new Date(), false));
		todos.add(new Todo(12, "Ashish", "American Express", new Date(), false));
		todos.add(new Todo(13, "Ashish", "Informatica", new Date(), false));
		todos.add(new Todo(14, "Ashish", "All State", new Date(), false));
		todos.add(new Todo(15, "Ashish", "Disney+ Hotstar", new Date(), false));

	}

	public List<Todo> retrieveTodos(String user) {
		List<Todo> filteredTodos = new ArrayList<Todo>();
		for (Todo todo : todos) {
			if (todo.getUser().equals(user)) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}

	public Todo retrieveTodos(int id) {
		// List<Todo> filteredTodos = new ArrayList<Todo>();
		for (Todo todo : todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}

	public void updateTodo(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
	}

	public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
		todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
	}

	public void deleteTodo(int id) {
		Iterator<Todo> iterator = todos.iterator();
		while (iterator.hasNext()) {
			Todo todo = iterator.next();
			if (todo.getId() == id) {
				iterator.remove();
			}
		}
	}
}