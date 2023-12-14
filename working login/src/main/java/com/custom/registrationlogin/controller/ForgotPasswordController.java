package com.custom.registrationlogin.controller;

import com.custom.registrationlogin.Utility;
import com.custom.registrationlogin.entity.User;
import com.custom.registrationlogin.service.UserDaoImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;


@Controller
public class ForgotPasswordController {


    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private JavaMailSender mailSender;


    @GetMapping("/forgot_password")
    public String ShowForgetPasswordForm(Model model)
    {
        model.addAttribute("user",new User("ROLE_USER"));
        return "forgotpasswordform";
    }

    @PostMapping("/forgot_password")
    public  String processForgetPsswordForm(HttpServletRequest request, Model model){

        String email=request.getParameter("email");
        String token = RandomStringUtils.randomAlphanumeric(45);

//        System.out.println("Email:"+email);
//        System.out.println("token: "+token);

        try {
            userDao.updateResetPasswordToken(token,email);

            //generate reset password link
            String resetPasswordLink= Utility.getSiteURL(request)+"/reset_password?token="+token;
            System.out.println(resetPasswordLink);

            //send email
            sendEmail(email,resetPasswordLink);

            //alert message to tell mail send
            model.addAttribute("message","We have send the mail please check ");



        }catch (UsernameNotFoundException e){
            model.addAttribute("error",e.getMessage());
        } catch (UnsupportedEncodingException  |MessagingException e) {
            model.addAttribute("error","Error While Sending email ");
        }



        return "forgotpasswordform";

    }

    private  void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message= mailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message);

        helper.setFrom("AjayGowlikar@gmail.com","Support");
        helper.setTo(email);

        String subject="Here Is the link to reset the password";
        String content="<p>Hello</p> +" +
                "<p>You have requested to reset your password</p>"+
                "<p>Click on below link to change your password : </p>" +
               "<p><b>" + resetPasswordLink + "</b></p>"+
                "<p>Ignore if not requested</p>";
        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);

    }


    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value="token") String token, Model model){
        User user= userDao.getByResetPasswordToken(token);
        if (user==null){
            model.addAttribute("title","Reset your password");
            model.addAttribute("message","invalid Token");
            return "message";

        }

        model.addAttribute("token",token);
        model.addAttribute("pageTitle","reset your password");

        return "reset_password_Form";

    }


    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userDao.getByResetPasswordToken(token);


        if (user == null) {
            model.addAttribute("title", "Reset your Password");
            model.addAttribute("message", "Invalid token");
            return "message";
        } else {
            userDao.updatePassword(user, password);
            model.addAttribute("title", "Reset password successful");
            model.addAttribute("message", "Password reset successful");
            return "message";
        }
    }




}
