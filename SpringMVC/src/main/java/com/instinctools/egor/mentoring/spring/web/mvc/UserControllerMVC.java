package com.instinctools.egor.mentoring.spring.web.mvc;

import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.services.UserService;
import com.instinctools.egor.mentoring.spring.web.dto.BookDTO;
import com.instinctools.egor.mentoring.spring.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserControllerMVC {
    private final UserService service;

    @Autowired
    public UserControllerMVC(@Qualifier("userService") final UserService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ModelAndView createUser(@ModelAttribute("userDTO") final UserDTO userDTO) {
        final User user = userDTO.toModel();
        service.createUser(user);
        return new ModelAndView("redirect:/users");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @GetMapping("/create/form")
    public ModelAndView getCreateForm() {
        ModelAndView modelAndView = new ModelAndView("userCreate");
        modelAndView.addObject(new UserDTO("Input name", new Date(System.currentTimeMillis())));
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAllUsers() {
        final ModelAndView modelAndView = new ModelAndView("userList");
        final List<User> users = service.getAllUsers();
        modelAndView.addObject("userList", users);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteUser(@RequestParam("userId") final String userId) {
        final User user = service.getUserById(userId);
        service.deleteUser(user);
        return new ModelAndView("redirect:/users");
    }

    @PutMapping("/update")
    public ModelAndView updateUser(@RequestParam("userId") final String userId,
                                   @RequestParam("user") final UserDTO userDTO) {
        final User user = service.getUserById(userId);
        user.setUserName(userDTO.getName());
        user.setDateOfBirth(userDTO.getBirthDate());
        service.updateUser(user);
        return new ModelAndView();
    }
}
