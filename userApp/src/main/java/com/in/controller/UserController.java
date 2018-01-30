package com.in.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.in.service.UsersService;
import com.in.user.Users;

@Controller
@RequestMapping("/users/")
/*@Configuration
@ComponentScan("com.in.service")*/
public class UserController {
	
	 @Autowired
	 UsersService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView getPage() {
		System.out.println("INSIDE users/page REQUEST");
		ModelAndView view = new ModelAndView("users");
		return view;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getAll(Users users) {
        Map<String, Object> map = new HashMap<String, Object>();
        	System.out.println("user list :");
        List<Users> list = userService.list();
        if (list != null) {
            map.put("status", "200");
            map.put("message", "Data found");
            map.put("data", list);
            System.out.println("data  ::: "+list.get(0).getEmail());
        } else {
            map.put("status", "404");
            map.put("message", "Data not found");
 
        }
System.out.println("Final Map"+map.toString());
        return map;
    } 
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getSaved(Users users) {
        Map<String, Object> map = new HashMap<String, Object>();
 
        if (userService.saveOrUpdate(users)) {
            map.put("status", "200");
            map.put("message", "Your record have been saved successfully");
        }
 
        return map;
    }

}
