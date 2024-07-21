package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество ДатаРождения НомерТелефона Пол) через пробел");

        String input = scanner.nextLine();
        String[] userData = input.split(" ");

        try {
            validateInputLength(userData);
            String surname = userData[0];
            String name = userData[1];
            String patronymic = userData[2];
            String birthDate = userData[3];
            String phoneNumber = userData[4];
            String gender = userData[5];

            validateBirthDate(birthDate);
            validatePhoneNumber(phoneNumber);
            validateGender(gender);

            String fileName = surname + ".txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
                writer.write(String.join(" ", surname, name, patronymic, birthDate, phoneNumber, gender));
                writer.newLine();
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
        scanner.close();
    }

    public static void validateInputLength(String[] data){
        if (data.length < 6) {
            throw new IllegalArgumentException("Введено меньше данных, чем требуется.");
        } else if (data.length > 6) {
            throw new IllegalArgumentException("Введено больше данных, чем требуетсяю");
        }
    }

    public static void validateBirthDate(String birthDate){
        if (birthDate.matches("\\\\d{2}\\\\.\\\\d{2}\\\\.\\\\d{4}")){
            throw new IllegalArgumentException("Неверный формат даты рождения. Ожидается dd/mm/yyyy");
        }
    }

    public static void validatePhoneNumber(String phoneNumber){
        if (!phoneNumber.matches("\\d+")){
            throw new IllegalArgumentException("Номер телефона должен быть целым числом без форматирования.");
        }
    }

    public static void validateGender(String gender){
        if (!gender.matches("[fm]")){
            throw new IllegalArgumentException("Пол должен быть символом 'f' или 'm'");
        }
    }
}