package com.orozco.gesterin.utils;

import com.orozco.gesterin.service.Implement.AuhtenticateServiceImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 16 oct. 2024
 * @description Sistema GESTERIN
 */
public class GenerateHash {
private static final List<String[]> users = new ArrayList<>();

    static {
        users.add(new String[]{"Wolf103", "Donna103"});
        users.add(new String[]{"facuFC", "Fcaniza"});
        users.add(new String[]{"silcapin", "Kilian63"});
        users.add(new String[]{"guillote", "muFasa33"});
        users.add(new String[]{"jormi", "daglioM44"});
        users.add(new String[]{"geraProf", "Gerardo901"});
        users.add(new String[]{"vaneProf", "Vanesa783"});
        users.add(new String[]{"SebasProf", "Sebastian362"});
        users.add(new String[]{"CarlosProf", "Carlos887"});
        users.add(new String[]{"HernanProf", "Hernan656"});
    }

    public static void main(String[] args) {
        AuhtenticateServiceImpl authService = new AuhtenticateServiceImpl();
        for (String[] user : users) {
            String username = user[0];
            String password = user[1];
            String hashedPassword = authService.hashPassword(password);

            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Hashed Password: " + hashedPassword);
            System.out.println("--------------");
        }
    }
}
