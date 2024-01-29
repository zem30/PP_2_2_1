package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        System.out.println("добавление и вывод пользователей БЕЗ машины");
        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        System.out.println("---------------------------");

        Car car1 = new Car("BMW",5);
        Car car2 = new Car("Mercedes-Benz",6);
        Car car3 = new Car("Lada",12);
        Car car4 = new Car("Honda",9);

        System.out.println("добавление и вывод пользователей с машиной");
        userService.add(new User("User5", "Lastname5", "user5@mail.ru", car1));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", car2));
        userService.add(new User("User7", "Lastname7", "user7@mail.ru", car3));
        userService.add(new User("User8", "Lastname8", "user8@mail.ru", car4));

        List<User> userWithCar = userService.listUsers();
        for (User user : userWithCar) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getUserCar());
            System.out.println();
        }
        System.out.println("---------------------------");
        System.out.println("вывод на экран пользователя с машиной по модели и серии");
        try {
            User user = userService.getUserByCarModelAndSeries("Mercedes-Benz",6);
            if(user!=null){
                System.out.println(user);
            }
        }catch (NoResultException e){
            System.out.println("хуйня, нет такого");
        }

        context.close();
    }

}
