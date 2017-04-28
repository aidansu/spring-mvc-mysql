package com.aidansu.demo.controller;

import com.aidansu.demo.common.utils.EncryptUtil;
import com.aidansu.demo.common.utils.ErrorResponseUtil;
import com.aidansu.demo.common.utils.SessionUtil;
import com.aidansu.demo.common.utils.StringUtil;
import com.aidansu.demo.model.User;
import com.aidansu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * Created by aidan on 17-4-26.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 主页面
     */
    @RequestMapping(value="/" ,method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    /**
     * 跳转到用户登录页面
     */
    @RequestMapping(value="/login" ,method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 跳转到用户注册页面
     */
    @RequestMapping(value="/register" ,method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    /**
     * 用户注册
     *
     * @param modelMap
     * @param request
     */
    @RequestMapping(value="/registering" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String registering(HttpServletRequest request,ModelMap modelMap){
        // 获取传递过来的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String telephone = request.getParameter("telephone");

        if(StringUtil.isEmpty(username)) {
            return ErrorResponseUtil.setResponse("400", "Username can not be null");
        }
        if(StringUtil.isEmpty(password)) {
            return ErrorResponseUtil.setResponse("400", "Password can not be null");
        }
        if(StringUtil.isEmpty(password2)) {
            return ErrorResponseUtil.setResponse("400", "Confirm password can not be null");
        }
        if(!password.equals(password2)){
            return ErrorResponseUtil.setResponse("400", "Two passwords are inconsistent");
        }

        // 验证用户名是否已经注册过
        User user ;
        try {
            user = userService.findByUsername(username);
        }catch(Exception e){
            user = null;
        }
        if(user != null){
            return ErrorResponseUtil.setResponse("400", "The username is registered");
        }
        String shaPwd;
        try {
            shaPwd = EncryptUtil.encryptSHA(password) ;
        } catch (NoSuchAlgorithmException e) {
            return ErrorResponseUtil.setResponse("400", "The password can not be encrypted");
        }
        Date date = new Date();
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(shaPwd);
        if(StringUtil.isNotEmpty(telephone)){
            newUser.setTelephone(telephone);
        }
        newUser.setCreateTime(date);
        newUser.setLastLoginTime(date);
        newUser.setUpdateTime(date);

        try {
            userService.insert(newUser);
        }catch(Exception e){
            return ErrorResponseUtil.setResponse("400", e.getMessage());
        }
        return ErrorResponseUtil.setResponse("200", "registration success");

    }

    /**
     * 用户登录
     */
    @RequestMapping(value="/logining" ,method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String logining(HttpServletRequest request,ModelMap modelMap){
        // 获取传递过来的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(StringUtil.isNotEmpty(username) && StringUtil.isNotEmpty(password) ){
            User user;
            try {
                user = userService.findByUsername(username);
            }catch (Exception e){
                user = null;
            }
            if(user != null){
                String shaPwd ;
                try {
                    shaPwd = EncryptUtil.encryptSHA(password) ;
                } catch (NoSuchAlgorithmException e) {
                    shaPwd = "" ;
                }
                if(StringUtil.isNotEmpty(user.getPassword()) && shaPwd.equals(user.getPassword())){
                    SessionUtil.login(user, request);
                    user.setLastLoginTime(user.getUpdateTime());
                    user.setUpdateTime(new Date());
                    try {
                        userService.update(user);
                    }catch(Exception e){
                        return ErrorResponseUtil.setResponse("400", e.getMessage());
                    }
                    return ErrorResponseUtil.setResponse("200", "login successful");
                }else{
                    return ErrorResponseUtil.setResponse("400", "Password error");
                }
            }else{
                return ErrorResponseUtil.setResponse("400", "The user can not be found");
            }
        }else{
            return ErrorResponseUtil.setResponse("400", "param is null");
        }
    }

    /**
     * 跳转到用户列表
     */
    @RequestMapping(value="/user-list" ,method = RequestMethod.GET)
    public String userList(HttpServletRequest request, ModelMap modelMap){
        // 获取当前用户信息
        User user = SessionUtil.getUserFromSession(request);
        modelMap.put("user",user);

        List<User> userList = userService.findAll();
        modelMap.put("users",userList);

        return "user-list";
    }

    /**
     * 根据id删除用户
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String goDelete(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        userService.delete(id);
        return "redirect:/user-list";
    }

    /**
     * 登出
     */
    @RequestMapping(value="/logout" ,method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        SessionUtil.logout(request);
        return "redirect:login";
    }
}
