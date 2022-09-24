package org.designpatterns._02_structural_patterns._06_adapter._02_after;

import org.designpatterns._02_structural_patterns._06_adapter._02_after.security.UserDetails;
import org.designpatterns._02_structural_patterns._06_adapter._02_after.security.UserDetailsService;

public class AccountServiceAdapter implements UserDetailsService {

    public AccountAdapter findAccountByUsername(String username) {
        AccountAdapter account = new AccountAdapter();
        account.setName(username);
        account.setPassword(username);
        account.setEmail(username);
        return account;
    }

    public void createNewAccount(AccountAdapter account) {

    }

    public void updateAccount(AccountAdapter account) {

    }

    @Override
    public UserDetails loadUser(String username) {
        return (UserDetails)findAccountByUsername(username);
    }
}
