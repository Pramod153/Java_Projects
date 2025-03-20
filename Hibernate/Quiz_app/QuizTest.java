package com.hibernate.quizapp;

import java.util.List;
import java.util.Scanner;

public class QuizTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserUtil userUtil = new UserUtil();
        QuestionUtil questionUtil = new QuestionUtil();

        System.out.println("Welcome to the Quiz!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        int choice = scanner.nextInt();
        scanner.nextLine();

        User currentUser = null;
        if (choice == 1) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            User user = new User();
            user.setUser_name(username);
            user.setPassword(password);
            userUtil.regUser(user);
            System.out.println("Registration Successful! Please Login.");
        }

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        currentUser = userUtil.loginUser(username, password);
        if (currentUser == null) {
            System.out.println("Invalid Credentials. Exiting...");
            scanner.close();
            return;
        }
        System.out.println("Login Successful!");

        List<Question> questions = questionUtil.getAllQuestions();
        int score = 0;

        for (Question question : questions) {
            System.out.println("\n" + question.getQuestionText());
            List<Answer> answers = question.getAnswers();
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ". " + answers.get(i).getAnswerText());
            }

            System.out.print("Enter your choice (1-4): ");
            int userAnswer = scanner.nextInt();

            if (userAnswer == question.getCrrctOpt()) {
                score++;
                System.out.println("Correct Answer!");
            } else {
                System.out.println("Wrong Answer!");
            }
        }

        System.out.println("Your Final Score: " + score);
        scanner.close();
    }
}
