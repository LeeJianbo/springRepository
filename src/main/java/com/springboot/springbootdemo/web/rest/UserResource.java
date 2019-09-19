package com.springboot.springbootdemo.web.rest;

import com.alibaba.fastjson.JSON;
import com.springboot.springbootdemo.dto.UserDTO;
import com.springboot.springbootdemo.dto.base.ResponseDTO;
import com.springboot.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class UserResource extends BaseResource {

    @Autowired
    public UserService userService;

    @RequestMapping("addUser")
    public ResponseDTO addUser(@Validated @RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return success();
    }

    @RequestMapping("findUser")
    public ResponseDTO findUser(@RequestBody UserDTO userDTO) {
        return success(userService.findUserByName(userDTO));
    }

    @RequestMapping("pageList")
    public ResponseDTO pageList(Pageable page) {

        return success(userService.pageList(page));
    }

}
