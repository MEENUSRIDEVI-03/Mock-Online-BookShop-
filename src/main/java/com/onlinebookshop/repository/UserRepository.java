
package com.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
