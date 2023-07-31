package ezequiel.trevorassignment13.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    private Long account_id;
    private String accountName;
    private List<User> users = new ArrayList<>(); //ManyToMany


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    @Column(length = 50)
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.PERSIST)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }



}
