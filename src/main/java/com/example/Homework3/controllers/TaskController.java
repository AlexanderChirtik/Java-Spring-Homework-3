package com.example.Homework3.controllers;


import com.example.Homework3.domain.User;
import com.example.Homework3.services.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class TaskController {

    @Autowired
    private DataProcessingService service;

    /**
     * Метод срабатывает при запросе на http://localhost:8080
     * @return возвращает список задач
     */
    @GetMapping
    public List<String> getAllTasks()
    {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return  tasks;
    }

    /**
     * Метод срабатывает при запросе на http://localhost:8080/sort и сортирует пользователей из БД по возрасту
     * @return Возвращает список уже отсортированных пользователей
     */
    @GetMapping("/sort")
    public List<User> sortUsersByAge()
    {
        return service.sortUsersByAge(service.getRepository().findAll());
    }


    /**
     * Метод срабатывает при запросе на http://localhost:8080/filtet/{возраст} и фильтрует пользователей
     * из базы данных с учетом переданного в запросе возраста
     * @param age Возрастное ограничение
     * @return Возвращает список пользователей, превосходящих возрастное ограничение
     */
    @GetMapping("/filter/{age}")
    public List<User> filterUsersByAge(@PathVariable int age) {
        return service.filterUsersByAge(service.getRepository().findAll(), age);
    }

    /**
     * Метод срабатывает при запросе на http://localhost:8080/calc и считает средний арифметический возраст
     * всех пользователей, находящихся в базе данных
     * @return Возвращает число, означающее средний возраст в базе данных
     */
    @GetMapping("/calc")
    public double calculateAverageAge() {
        return service.calculateAverageAge(service.getRepository().findAll());
    }
}
