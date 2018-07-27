package com.hrsb.cg.util;


import com.hrsb.cg.util.bean.MailSenderInfo;

public class SendMail {  
      
    public static void main(String[] args) {  
        SendMail.send_163();  
    }
    
    //smtp.163.com
    public static void send_163() {  
        // 这个类主要是设置邮件  
    	MailSenderInfo mailInfo = new MailSenderInfo();  
        mailInfo.setMailServerHost("smtp.163.com");  
        mailInfo.setMailServerPort("25");  
        mailInfo.setValidate(true);  
        mailInfo.setUserName("shaoyangkai@163.com"); // 实际发送者  
        mailInfo.setPassword("a986517");// 您的邮箱密码  
        mailInfo.setFromAddress("shaoyangkai@163.com"); // 设置发送人邮箱地址  
        mailInfo.setToAddress("740157491@qq.com"); // 设置接受者邮箱地址  
        mailInfo.setSubject("屌11丝");  
        mailInfo.setContent("设置邮箱内容<h1 style='colour:red'>h6</h1>");  
        // 这个类主要来发送邮件  
        SimpleMailSender sms = new SimpleMailSender();  
        //System.out.println(sms.sendTextMail(mailInfo)); // 发送文体格式  
       System.out.println( sms.sendHtmlMail(mailInfo)); // 发送html格式  
    }

    public static boolean send_email(String hostsmtp,String hosteamil,String hostpass,
    	String emailname,String title,String content) {  
        // 这个类主要是设置邮件  
    	MailSenderInfo mailInfo = new MailSenderInfo();  
        mailInfo.setMailServerHost(hostsmtp);  
        mailInfo.setMailServerPort("25");  
        mailInfo.setValidate(true);  
        mailInfo.setUserName(hosteamil); // 实际发送者  
        mailInfo.setPassword(hostpass);// 您的邮箱密码  
        mailInfo.setFromAddress(hosteamil); // 设置发送人邮箱地址  
        mailInfo.setToAddress(emailname); // 设置接受者邮箱地址  
        mailInfo.setSubject(title);  
        mailInfo.setContent(content);  
        // 这个类主要来发送邮件  
        SimpleMailSender sms = new SimpleMailSender();  
        //System.out.println(sms.sendTextMail(mailInfo)); // 发送文体格式  
       boolean bool= sms.sendHtmlMail(mailInfo); // 发送html格式  
       return bool;
    }
}