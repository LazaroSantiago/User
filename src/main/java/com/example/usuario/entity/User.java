package com.example.usuario.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    int phoneNumber;
    @Column(unique = true)
    String email;
    @ManyToMany(fetch = FetchType.LAZY)
    private ArrayList<Account> accounts;

    public void addAccount(Account account) {
        if (!this.accounts.contains(account))
            this.accounts.add(account);
    }

    public ArrayList<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

}
