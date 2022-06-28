package com.project.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {



	public User findByUsername(String username);

	@Query( "select u from User u JOIN FETCH u.userroles ur join fetch ur.role r where r.name =:roleName" )
	 public List<User> findBySpecificRoles(@Param("roleName") String roleName);
	
//	@Query("select u from User u where u.username =:username")
//	public User findByUsername(@Param("username") String username);

}
