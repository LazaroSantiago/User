package entity;

import dto.ScooterDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
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

    @ManyToOne
    private Account currentAccount;
    @OneToOne
    private Trip currentTrip;
    @OneToOne
    private Scooter currentScooter;

    public void addAccount(Account account){
        if (!this.accounts.contains(account))
            this.accounts.add(account);
    }

    public ArrayList<Account> getAccounts(){
        return new ArrayList<>(accounts);
    }

    public void switchAccount(int i){
        if (i<=1)
            this.currentAccount = accounts.get(0);
        else if (i>= accounts.size())
            this.currentAccount = accounts.get(accounts.size()-1);
        else
            this.currentAccount = accounts.get(i-1);
    }

//    TODO:
//    Como usuario quiero un listado de los monopatines cercanos a mi zona,
//    para poder encontrar un monopatín cerca de mi ubicación
    public ArrayList<ScooterDto> getScootersNearby(){
        //la entidad usuario llama a Scooter pasando this.ubicacion
        //y ahi ocurre la logica
        return null;
    }

    //desactivar scooter()
    //pausar()

    //activar scooter()
    public void beginTrip(Scooter scooter) throws Exception {
        if (!currentAccount.verifyAccount() || !scooter.verifyScooter())
            return;
        //crear viaje, le paso this.usuario
        //seteo el viaje actual al usuario
        //monopatin en uso.

    }

    public void endTrip(ScooterStop stop){
        if (currentScooter.getUbicacion() != stop.getUbicacion())
            return;
        //setear en el viaje hora finalizada
        this.currentTrip.endTrip();
        //crear factura
        long tripPrice = stop.calculateTransaction(currentTrip);
        this.currentAccount.payTrip(tripPrice);
        this.currentScooter.setStatus('F');
        this.setCurrentTrip(null);
    }


}
