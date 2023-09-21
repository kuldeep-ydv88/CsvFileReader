package com.example.Emailsender.service;

import com.example.Emailsender.Entity.User;
import com.example.Emailsender.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BirthdayEmailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 10 16 * * *")    public String sendBirthdayEmails() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();
        List<User> usersWithBirthday = userRepository.findByBirthDate(currentDate.getMonthValue(), currentDate.getDayOfMonth());

        for (User user : usersWithBirthday) {

            LocalDate userBirthDate = user.getBirthDate();
            if(userBirthDate.getMonthValue() == currentMonth && userBirthDate.getDayOfMonth() == currentDay){
               String to = user.getEmail();
               String[] cc = new String[]{"kuldeep@gmail.com"};
                String subject = "Happy Birthday!";
                String body = "Dear " + user.getName() + ",\n\nWishing you a fantastic birthday!";
                emailService.sendMail(to,cc,subject, body);
            }

        }
        return "Birthday emails sent.";
    }


}