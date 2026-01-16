package com.minzzzun.bookdart_be.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
public class DefaultController {

    @RequestMapping({"", "/"}) // @RequestMapping 의 주소값은 스트링 한개도 가능, 스트링 배열도 가능!
    public String empty(){
        return "redirect:/index";
    }

    @RequestMapping("/{page}")
    public String page(@PathVariable String page){
        return page;
    }


}
