package com.gilbert.test.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

/**
 * @author gilbertwang
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}
