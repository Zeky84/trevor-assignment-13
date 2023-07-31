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


import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String registerUser(ModelMap model) {
        model.put("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/register";
    }

    @GetMapping("/users")
    public String getUsers(ModelMap model) {
        Set<User> users = userService.findAllUsersWithAccountsAndAddress();
        model.put("users", users);
        if (users.size() == 1) {
            model.put("user", users.iterator().next());
        }
        return "/users";
    }

    @GetMapping("/users/{user_id}")
    public String getUser(@PathVariable Long user_id, ModelMap model) {
        User user = userService.findByIdWithAddressAndAccount(user_id);
        model.put("users", Collections.singletonList(user));//Arrays.asList(user)
        model.put("user", user);
        if (model.size() > 1) {
            return "/users";
        }
        return "users/{user_id}";
    }

    @PostMapping("/users/{user_id}")
    public String updateUser(@PathVariable Long user_id, User user) {
        User alreadyUser = userService.findByIdWithAddressAndAccount(user_id);
        user.setAccounts(alreadyUser.getAccounts());//Populating user accounts
        userService.saveUser(user);
        return "redirect:/users/" + user.getUser_id();
    }

    @PostMapping("/users/{user_id}/delete")
    public String deleteUser(@PathVariable Long user_id) {
        userService.deleteById(user_id);
        return "redirect:/users";
    }

    @PostMapping("/users/{user_id}/accounts")
    public String createAccount(@PathVariable Long user_id) {
        User user = userService.findByIdWithAddressAndAccount(user_id);
        Account account = new Account();
        account.setAccountName("Account #" + (user.getAccounts().size()+1));
        account.getUsers().add(user);
        user.getAccounts().add(account);
        accountService.saveAccount(account);
        return "redirect:/users/{user_id}/accounts/"+account.getAccount_id();
    }
    @GetMapping("/users/{user_id}/accounts/{account_id}")
    public String getAccount(ModelMap model, @PathVariable Long account_id, @PathVariable Long user_id){
        User user = userService.findByIdWithAddressAndAccount(user_id);
        Account account = accountService.findById(account_id);
        model.put("account",account);
        model.put("user",user);
        return "account";
    }
    @PostMapping("/users/{user_id}/accounts/{account_id}")
    public String saveAccount(@PathVariable Long account_id, Account account){
        accountService.saveAccount(account);

        return "redirect:/users/{user_id}/accounts/{account_id}";
    }
}

