package org.designpatterns._02_structural_patterns._06_adapter._02_after;

import org.designpatterns._02_structural_patterns._06_adapter._02_after.security.LoginHandler;
import org.designpatterns._02_structural_patterns._06_adapter._02_after.security.UserDetailsService;

public class App {

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        UserDetailsService userDetailsService = new AccountUserDetailsService(accountService);
        LoginHandler loginHandler = new LoginHandler(userDetailsService);
        String login = loginHandler.login("keesun", "keesun");
        System.out.println(login);
    }
}
