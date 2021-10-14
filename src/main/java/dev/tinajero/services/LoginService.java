package dev.tinajero.services;


import dev.tinajero.models.Users;
import dev.tinajero.repos.UserRepo;

public class LoginService {
    UserRepo userRepo = new UserRepo();

    public boolean loginBenco(String user, String pass){//login method for the benco, only returns true if it is indeed the benefactor coordinator
                                                        //same thing happens in the other login methods

        Users u = userRepo.getByUsername(user);
        if(u != null){
            if(user.equals(u.getUserName()) && pass.equals(u.getPassword()) && u.isBenCo() == true) {
                return true;
            }
        }

        return false;
    }

    public boolean loginDH(String username, String password) {
        Users u = userRepo.getByUsername(username);
        if(u != null){
            if(username.equals(u.getUserName()) && password.equals(u.getPassword()) && u.isDepartH() == true) {
                return true;
            }
        }
        return false;
    }

    public boolean loginDS(String username, String password) {
        Users u = userRepo.getByUsername(username);

        if(u != null){
            if(username.equals(u.getUserName()) && password.equals(u.getPassword()) && u.isDirectS() == true) {
                return true;
            }
        }
        return false;
    }

    public boolean login(String username, String password) {

        Users u = userRepo.getByUsername(username);

        if(u != null){
            if(username.equals(u.getUserName()) && password.equals(u.getPassword()) && u.isEmployee() == true) {
                return true;
            }
        }
        return false;
    }

    public Users login(String username){
        Users u = userRepo.getByUsername(username);

        return u;
    }
}
