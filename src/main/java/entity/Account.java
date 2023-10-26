package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Account {
    @Id
    private Long id;
    @Column
    private Date creationDate;
    @Column
    private long idMercadoPago;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> users;

    private boolean isBanned;
    private long funds;

    public void addUser(User users){
        this.users.add(users);
    }

    public Set<User> getUsers(){
        return new HashSet<>(users);
    }

    public boolean verifyAccount() throws Exception {
        if (this.isBanned()){
            throw new Exception("Cuenta deshabilitada.");
        } else if (this.getFunds()==0){
            throw new Exception("No hay fondos.");
        } else {
            return true;
        }
    }

    public void payTrip(long tripPrice){
        if (tripPrice > funds)
            this.setFunds(0);
        else
            this.setFunds(funds - tripPrice);
    }
}