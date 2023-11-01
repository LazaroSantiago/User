package util;

import controller.AccountController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import controller.UserController;

@Component
public class Test {
    private final AccountController accountController;
    private final UserController userController;

    @Autowired
    public Test(AccountController accountController, UserController userController) {
        this.accountController = accountController;
        this.userController = userController;
    }

    public void test() {
//        User a = new User();
//        userController.save(a);
//        accountController.deleteById(a.getId());
    }
}
