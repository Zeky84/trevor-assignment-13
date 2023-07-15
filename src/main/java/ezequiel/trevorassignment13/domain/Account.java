package ezequiel.trevorassignment13.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
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
    @ManyToMany(mappedBy = "accounts")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account_id == null) ? 0: account_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if( o == null || getClass() != o.getClass())
            return false;
        Account other = (Account) o;
        if (account_id == null){
        if (other.account_id != null)
            return false;
        }else if (!account_id.equals(other.account_id))
            return false;
        return true;
    }
}
