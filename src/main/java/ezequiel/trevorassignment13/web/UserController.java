package ezequiel.trevorassignment13.web;

import ezequiel.trevorassignment13.domain.Account;
import ezequiel.trevorassignment13.domain.Address;
import ezequiel.trevorassignment13.domain.User;
import ezequiel.trevorassignment13.service.AccountService;
import ezequiel.trevorassignment13.service.AddressService;
import ezequiel.trevorassignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    AccountService accountService;

    @GetMapping("/register")
    public String registerUser(ModelMap model) {
        model.put("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String createUser(User user) {
        userService.saveUser(user); // added the accountName parameter so save the account with the desire name
        return "redirect:/register";
    }

    @GetMapping("/users")
    public String getUsers(ModelMap model) {
        Set<User> users = userService.getAllUsers();
        model.put("users", users);
        if (users.size() == 1) {
            model.put("user", users.iterator().next());
        }
        return "/users";
    }

    @GetMapping("/users/{user_id}")// REMEMBER YOU COPY THIS FROM THE SOLUTION. YOU WERE DOING THIS ON THE SAVE METHOD. CHECK THAT
    public String getUser(@PathVariable Long user_id, ModelMap model) {
        User user = userService.getUserById(user_id);
        if (user.getAddress()==null){
            Address address = new Address();
            address.setUser(user);
            address.setUser_id(user_id);
            user.setAddress(address);
        }
        model.put("users",Arrays.asList(user));
        model.put("user",user);
//        model.put("address",user.getAddress());
        return "/users";
    }

    @PostMapping("/users/{user_id}")
    public String updateUser(@PathVariable Long user_id, User user) {
        Address address = user.getAddress();
        address.setUser(user);
        System.out.println(user_id.getClass());// to check the type of the user_id
        address.setUser_id(user_id);
        System.out.println(address.getUser_id().getClass());// to check the type of the user_id
        user.setAddress(address);
        userService.save(user);
        return "redirect:/users/" + user.getUser_id();
    }

    @PostMapping("/users/{user_id}/delete")
    public String deleteUser(@PathVariable Long user_id) {
        userService.deleteById(user_id);
        return "redirect:/users";
    }
}

