package com.instinctools.egor.mentoring.JPA.Service;

import com.instinctools.egor.mentoring.JPA.Entity.Account;

public interface AccountService {
    public void createAccount(Account account);
    public void deleteAccount(Account account);
    public void updateAccount(Account account);
}
