package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

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

    public void addAccount(Account account){
        if (!this.accounts.contains(account))
            this.accounts.add(account);
    }

    public ArrayList<Account> getAccounts(){
        return new ArrayList<>(accounts);
    }

    public Account getMainAccount(){
        return accounts.get(0);
    }

/*
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
    //quizas public TripDTO
    public void beginTrip(Scooter scooter){
        this.userController.beginTrip(scooter);
    }

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
*/

}
