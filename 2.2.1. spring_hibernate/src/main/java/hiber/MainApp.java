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

      Car car1 = new Car("BMW",1);
      Car car2 = new Car("Audi",2);
      Car car3 = new Car("Mercedes",3);
      Car car4 = new Car("Porsche", 4);

      User user1 = new User("Zaur", "Tregulov", "tregulov@mail.ru");
      User user2 = new User("Nail", "Alishev", "alishev@mail.ru");
      User user3 = new User("Petr", "Ivanov", "ivanov@mail.ru");
      User user4 = new User("Ivan", "Petrov", "petrov@mail.ru");


      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      List<User> usersCar = userService.getUserByCar("BMW", 1);
      for (User user : usersCar) {
         System.out.println(user);
      }

      context.close();

   }
}
