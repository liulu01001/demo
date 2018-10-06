package com.liulu.springbootmail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

    @Resource
    private SendMailService sendMailService;

    @Test
    public void test1(){

        sendMailService.sendMail("beijing01005@163.com","主题1","这是一个简单的邮件！");

    }

    @Test
    public void test2(){

        String context="<html>\n" +
                "<body>\n" +
                "<h3>这是html页面</h3>"+"</body>\n" +
                "</html>";

        sendMailService.htmlMail("beijing01005@163.com","主题1",context);

    }

    @Test
    public void test3(){

        String filePath="C:\\Users\\HASEE\\Desktop\\资料\\小项目\\spring-boot-mail.zip";
        String context="<html>\n" +
                "<body>\n" +
                "<h3>这是html附件页面</h3>"+"</body>\n" +
                "</html>";

        sendMailService.fileMail("beijing01005@163.com","主题1",context,filePath);

    }

    @Test
    public void test4(){

        String id="1111";
        String filePath="C:\\Users\\Public\\Pictures\\Sample Pictures\\魔方.jpg";
        String context="<html><body>" +
                "<img src=\'cid:"+id
                +"\'></img>"+
                "</body></html>";

        sendMailService.imgMail("beijing01005@163.com","主题1",context,filePath,id);

    }
}
