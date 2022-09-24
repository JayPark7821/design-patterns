package org.designpatterns._02_structural_patterns._06_adapter._02_after;

import org.designpatterns._02_structural_patterns._06_adapter._02_after.security.UserDetails;
import org.designpatterns._02_structural_patterns._06_adapter._02_after.security.UserDetailsService;

public class AccountUserDetailsService implements UserDetailsService { // 우리가 맞춰야하는 target interface를 구현함.

    private AccountService accountService; // Adaptee의 클래스 사용

    public AccountUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUser(String username) {
        return new AccountUserDetails(accountService.findAccountByUsername(username));
    }
}
