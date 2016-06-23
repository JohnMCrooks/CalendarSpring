/*
 * Copyright (c) 2016.
 */

package com.crooks.controllers;

import com.crooks.entities.Event;
import com.crooks.entities.User;
import com.crooks.services.EventRepository;
import com.crooks.services.UserRepository;
import com.crooks.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Created by johncrooks on 6/23/16.
 */
@Controller
public class CalendarSpringController {

    @Autowired
    UserRepository userrepo;
    @Autowired
    EventRepository eventrepo;


    @RequestMapping(path="/", method= RequestMethod.GET)
    public String home(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");

        model.addAttribute("username", username);
        model.addAttribute("events", eventrepo.findAll());
        model.addAttribute("now", LocalDateTime.now());
        return "home";

    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String password) throws Exception {
        User user = userrepo.findByName(username);
        if (user == null){
            user = new User(username, PasswordStorage.createHash(password));
            userrepo.save(user);
        } else if(!PasswordStorage.verifyPassword(password, user.getPassword())) {
            throw new Exception("Wrong Password!");
        }

        session.setAttribute("username", username);

        return "redirect:/";
    }
    @RequestMapping(path="/create-event", method = RequestMethod.POST)
    public String createEvent(HttpSession session, String description, String time){
        String username = (String) session.getAttribute("username");
        User user = userrepo.findByName(username);
        Event event = new Event(description, LocalDateTime.parse(time), user);
        eventrepo.save(event);
        return "redirect:/";

    }

    @RequestMapping(path="/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
