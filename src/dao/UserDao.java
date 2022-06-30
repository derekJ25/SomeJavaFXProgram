package dao;

import java.sql.SQLException;

import model.User;

/**
 * A data access object (DAO) is a pattern that provides an abstract interface 
 * to a database or other persistence mechanism. 
 * the DAO maps application calls to the persistence layer and provides some specific data operations 
 * without exposing details of the database. 
 */
public interface UserDao {
	void setup() throws SQLException;
	User getUser(String name, String username, String password, byte[] profileByte) throws SQLException;
	User createUser(String name, String username, String password, byte[] profileByte) throws SQLException;
	boolean searchUserExist(String username);
}
