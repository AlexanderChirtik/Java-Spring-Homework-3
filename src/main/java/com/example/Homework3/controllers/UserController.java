package com.example.Homework3.controllers;

import com.example.Homework3.domain.User;
import com.example.Homework3.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RegistrationService service;

    /**
     * При запросе на http://localhost:8080/user по умолчанию вызывается метод userList()
     * со списком всех юзеров, находящихся в базу данных
     * @return список с юзерами из базы данных
     */
    @GetMapping
    public List<User> userList() { return service.getDataProcessingService().getRepository().findAll(); }


    /**
     * Метод срабатывает при отправлении данных на http://localhost:8080/user/body и добавляет нового пользователя в БД
     * @param user Принимает нового юзера, которого нужно добавить в базу
     * @return Возвращает строку с отчетом о добавлении нового пользователя
     */
    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user)
    {
        service.getDataProcessingService().getRepository().save(user);
        return "User added from body!";
    }

    /**
     * Метод срабатывает при отправлении данных на http://localhost:8080/user/param и добавляет нового пользователя в БД
     * @param name Имя пользователя из параметров запроса
     * @param age Возраст пользователя из параметров запроса
     * @param email Электронная почта пользователя из параметров запроса
     * @return Возвращает строку с отчетом о добавлении нового пользователя
     */
    @PostMapping("/param")
    public String userAddFromParam(@RequestParam("name") String name, @RequestParam("age") int age,
                                   @RequestParam("email") String email) {
        service.processRegistration(name, age, email);
        return "User added from param!";
    }


    /**
     * Метод срабатывает при отправлении данных на http://localhost:8080/user/delete/{id}
     * и удаляет пользователя из БД по его id
     * @param id Номер пользователя в базу данных
     * @return Возвращает строку с отчетом об удалении пользователя
     */
    @PostMapping("/delete/{id}")
    public String userDelete(@PathVariable int id)
    {
        service.getDataProcessingService().getRepository().deleteById(id);
        return "User delete from database!";
    }

    /**
     * Метод срабатывает при отправлении данных на http://localhost:8080/user/body/update
     *  и меняет данные пользователя в БД по id переданного user
     * @param user Новый пользователь, который заменит пользователя с таким же id
     * @return Возвращает строку с отчетом об изменении данных пользователя на конкретной позиции
     */
    @PostMapping("/body/update")
    public String userUpdateFromBody(@RequestBody User user)
    {
        service.getDataProcessingService().getRepository().update(user);
        return "User update!";
    }
}