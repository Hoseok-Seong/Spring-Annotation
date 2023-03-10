package ex01;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MyRefApp {
    public static void main(String[] args) throws Exception {
        // Scanner sc = new Scanner(System.in);
        // String path = sc.nextLine(); // 디버그컨솔에서는 사용안됨
        String path = "/login";

        // path = "/login" -> uc.login()
        // path = "/join" -> uc.join()
        UserController uc = new UserController();

        // 계속 추가해주어야 해서 유지보수, ocp에 안좋다.
        // if (path.equals("/login")) {
        // uc.login();
        // } else if (path.equals("/join")) {
        // uc.join();
        // } else if (path.equals("/joinForm")) {
        // uc.joinForm();
        // }

        Method[] methods = uc.getClass().getDeclaredMethods(); // 어노테이션을 찾는다.
        // System.out.println(methods.length);
        for (int i = 0; i < methods.length; i++) {
            Method mt = methods[i];
            // System.out.println(mt.getName());

            Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class); // 모든 어노테이션의 조상
            RequestMapping rm = (RequestMapping) anno;
            // System.out.println(rm.uri());
            // if (anno == null) {
            // System.out.println("못찾음");
            // }

            if (rm.uri().equals(path)) {
                mt.invoke(uc);
            }
        }

    }
}