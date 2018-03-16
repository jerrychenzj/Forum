package com.jin.utils;

import com.jin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by JINS on 2018/3/6.
 */
@Component
public class TemplateSendEmail {
    private static Configuration freemarkerConfiguration = null;

    public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
        this.freemarkerConfiguration = freemarkerConfiguration;
    }


    //通过模板构造邮件内容，参数username将替换模板文件中的${username}标签。
        private String getMailText(String name,String erroTime){
            String htmlText="";
            try {
                //通过指定模板名获取FreeMarker模板实例
                Template tpl=freemarkerConfiguration.getTemplate("mail.ftl");
                //FreeMarker通过Map传递动态数据
                Map map=new HashMap();
                map.put("userName",name);
                map.put("sendTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                map.put("erroTime",erroTime);
                //解析模板并替换动态数据，最终username将替换模板文件中的${username}标签。
                htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return htmlText;
        }


    public  boolean sendMail(String email, String erroTime) {

        String from = "q453088644@163.com";
        String to = email;
        final String username = "q453088644@163.com";
        final String password = "321asdfghjklzxcv";



        Properties props = System.getProperties();


        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(props);

        session.setDebug(true);
        try {

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setSubject("您的帐号登录错误（来自：Forum）");

            String htmlText = getMailText(email,erroTime);

            message.setContent(htmlText,"text/html;charset=utf-8");

            Transport transport=session.getTransport();

            transport.connect("smtp.163.com",25, username, password);

            transport.sendMessage(message,new Address[]{new InternetAddress(to)});
            transport.close();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    }


