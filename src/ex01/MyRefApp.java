package ex01;

import java.util.Scanner;

public class MyRefApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();

        // path = "/login" -> uc.login()
        // path = "/join" -> uc.join()
        UserController uc = new UserController();
    }
}