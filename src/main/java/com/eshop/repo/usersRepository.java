package com.eshop.repo;

import com.eshop.model.users;
import org.springframework.data.repository.CrudRepository;

public interface usersRepository extends CrudRepository<users, Integer> {
    users findByEmail(String email);
}
