package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

         User user1 = new User("User1", "Lastname1", "user1@mail.ru");
         Car car = new Car("Kalina", 2);
         user1.setCar(car);
         User user2 = new User("User2", "Lastname2", "user2@mail.ru");
         user2.setCar(new Car("Priora", 1));
         userService.add(user1);
         userService.add(user2);
         List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(user.getCar().toString());
         System.out.println();
      }

         User user3 = userService.getUserByCar("Kalina", 2);
         User user4 = userService.getUserByCar("Priora", 1);
         System.out.println("------------------------------------------------------------------------------");
         System.out.println(user3.toString());
         System.out.println(user4.toString());
         System.out.println("------------------------------------------------------------------------------");

         context.close();
   }
}
