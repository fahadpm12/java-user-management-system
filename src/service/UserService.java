package service;

import model.User;
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

        userDao.insertUser(user);
    }

    /*
     * Get all users from database
     */
    public void getAllUsers() {
        userDao.fetchAllUsers();
    }

    /*
     * Find user by ID
     */
    public void findUserById(int id) {
        userDao.findUserById(id);
    }

    /*
     * Update user details
     */
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    /*
     * Delete user by ID
     */
    public void deleteUserById(int id) {
        userDao.deleteUser(id);
    }
}