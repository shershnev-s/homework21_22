package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.UserRepository;
import by.tut.shershnev_s.repository.model.User;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryImpl extends GenericDaoImpl<Long, User> implements UserRepository {

}
