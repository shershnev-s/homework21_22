package by.tut.shershnev_s.service;

import by.tut.shershnev_s.repository.model.User;

public interface UserService {

    User loadUserByUsername(String username);
}
