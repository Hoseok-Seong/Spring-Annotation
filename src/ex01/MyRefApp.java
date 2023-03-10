package ex01;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyRefApp {
    public static void main(String[] args) throws Exception {
        // Scanner sc = new Scanner(System.in);
        // String path = sc.nextLine(); // 디버그컨솔에서는 사용안됨
        String path = "/login";

        // 1. 컴포넌트 스캔, 패키지 스캔

        String packageName = "ex01";

        List<Class<?>> classes = new ArrayList<>();
        List<Object> beans = new ArrayList<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resources = classLoader.getResource(packageName);
        System.out.println(resources);

        String fileName = resources.getFile();
        System.out.println(fileName);

        File dir = new File(fileName);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.getName().endsWith(".class")) {
                    String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                    System.out.println(file.getName());
                    System.out.println(className);
                    classes.add(Class.forName(className));
                }
            }
        }

        for (Class<?> cls : classes) {
            if (cls.isAnnotationPresent(Controller.class)) {
                Object bean = cls.getDeclaredConstructor().newInstance();
                beans.add(bean);
                Method[] methods = cls.getDeclaredMethods();
                for (int i = 0; i < methods.length; i++) {
                    Method mt = methods[i];
                    Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class); // 모든 어노테이션의 조상
                    if (anno == null) {
                        System.out.println("못찾음");
                    }
                    RequestMapping rm = (RequestMapping) anno;

                    if (rm.uri().equals(path)) {
                        mt.invoke(bean); // Dispatcher Mapping 발동
                    }
                }
            }
        }

        // String path = "C:\workspace\new_spring_lab\refapp\bin\ex01";

        // 2. Dispatcher Mapping 발동

        // UserController uc = new UserController();

        // Method[] methods = uc.getClass().getDeclaredMethods(); // 어노테이션을 찾는다.
        // for (int i = 0; i < methods.length; i++) {
        // Method mt = methods[i];

        // Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class); // 모든
        // 어노테이션의 조상
        // RequestMapping rm = (RequestMapping) anno;

        // if (rm.uri().equals(path)) {
        // mt.invoke(uc); // Dispatcher Mapping 발동
        // }
        // }

    }
}