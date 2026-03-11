package service;

import model.User;

import java.util.ArrayList;

import dao.UserDao;

/*
 * Service layer contains business logic.
 * It acts as a bridge between the Main program and the DAO layer.
 * 
 * Main → UserService → UserDao → Database
 */

public class UserService {

    // Create DAO object
    private UserDao userDao = new UserDao();

    /*
     * Create new user
     * This method receives a User object from Main
     * and asks DAO to save it in the database
     */
    
    public void createUser(User user) {

        if(user.getEmail() == null || user.getEmail().isEmpty()) {
            System.out.println("Email cannot be empty");
            return;
        }
        if(user.getPassword().length() < 6){
            System.out.println("Password must be at least 6 characters");
            return;
        }
        
        System.out.println("Password validation enabled");

        userDao.insertUser(user);
    }

    /*
     * Get all users from database
     */
   
    public ArrayList<User> getAllUsers() {
        return userDao.fetchAllUsers();
    }

    /*
     * Find user by ID
     */
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    /*
     * Update user details
     */
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    /*
     * Delete user by ID
     */
    public boolean deleteUserById(int id) {
        return userDao.deleteUser(id);
    }
}