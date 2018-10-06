package com.liulu.springbootmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SendMailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;
    /**
     * 简单的邮件文本发送
     */
    public void sendMail (String to,String subject,String content){

        SimpleMailMessage message =new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);
        mailSender.send(message);

    }

    /**
     * html 邮件发送
     */
    public void htmlMail(String to,String subject,String content){

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            helper.setFrom(from);
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 附件 邮件发送
     */
    public void fileMail(String to,String subject,String content,String filePath){

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            helper.setFrom(from);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String filename = file.getFilename();
            helper.addAttachment(filename,file);

            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 图片 邮件发送
     */
    public void imgMail(String to,String subject,String content,String imgPath,String id){

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            helper.setFrom(from);


            FileSystemResource file = new FileSystemResource(new File(imgPath));

            helper.addInline(id,file);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
