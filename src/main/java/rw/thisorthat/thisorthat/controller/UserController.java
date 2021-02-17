package rw.thisorthat.thisorthat.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("/id")
    public String show(){
        return "show";
    }

    @PostMapping("")
    public String store(){
        return "store";
    }

    @PutMapping("/id")
    public String update(){
        return "update";
    }

    @DeleteMapping("/id")
    public String delete(){
        return "delete";
    }
}
