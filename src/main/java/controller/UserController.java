package controller;

import dto.TripDto;
import entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //CRUD
//    @GetMapping("")
//
//    void deleteById(ID id);
//
//    boolean existsById(ID id);
//
//    List<T> findAll();
//
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"No se encuentra al usuario\"}");
        }
    }
//
//    T save(T persisted);

    public TripDto beginTrip(Long userId, Long scooterId) throws Exception {
        //esto va en el service...
        ResponseEntity<?> re = this.findById(userId);
        User user = null;
        try {
            if (re.getStatusCode().is2xxSuccessful())
                user = (User) re.getBody();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        assert user != null;
        Long accountId = user.getMainAccount().getId();

        RestTemplate rt = new RestTemplate();
        String url1 = "http://localhost:8001/account/verify_account/{accountId}";
        ResponseEntity<Boolean> accountCheck = rt.getForEntity(url1, Boolean.class);
        if (!accountCheck.getStatusCode().is2xxSuccessful())
            return null;
        //supongamos que esto es crear un trip
        String url2 = "http://localhost:8002/trip/user/" + userId + "/scooter/" + scooterId;
        ResponseEntity<TripDto> tripDto = rt.getForEntity(url2, TripDto.class);

        return tripDto.getBody();
        //chequear cuenta, llamando al endpoint de accountcontroller
        //llamar a trip controller
    }

    public void endTrip(TripDto tripId) {
        RestTemplate rt = new RestTemplate();

        //get viaje
        String url1 = "http://localhost:8002/trip/" + tripId + "/";

        //checkeo ubicacion similar
        if (stop.getUbicacion() != scooter.getUbicacion())
            return;

        //agrego info viaje finalizado
        //put
        String url4 = "http://localhost:8002/trip/" + tripId + "/";

        //genero recibo
        String url3 = "http://localhost:8002/receipt/fare/" + tripId + "/";
        ResponseEntity<Long> fareRt = rt.getForEntity(url3, Long.class);
        Long fare = fareRt.getBody();
        //pago
        String url2 = "http://localhost:8001/pay/{fare}/account/{id}";
    }
}
