/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avin.wholesale.ejb;

import com.avin.wholesale.persistence.City;
import com.avin.wholesale.persistence.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Yubar
 */
@Stateless
public class UserBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
    public void addNewUser(User user){
        if (user.getFirstName()==null || user.getFirstName().equals(""))
            throw new RuntimeException("NullUserFirstNameException");
        if (user.getLastName()==null || user.getLastName().equals(""))
            throw new RuntimeException("NullUserLastNameException");
        if (user.getUserName()==null || user.getUserName().equals(""))
            throw new RuntimeException("NullUserNameException");
        if (user.getEmail()==null || user.getEmail().equals(""))
            throw new RuntimeException("NullUserEmailException");
        if (user.getPassword()==null || user.getPassword().equals(""))
            throw new RuntimeException("NullUserPasswordException");
        if (user.getUserCity()==null)
            throw new RuntimeException("NullUserCityException");
        user.setUserCity(em.find(City.class, user.getUserCity().getId()));
        if (user.getUserCity()==null)
            throw new RuntimeException("CityNotExistsException");
        try {
            em.persist(user);
        } catch (Exception ex){
            throw new RuntimeException("PersistException");
        }
    }
    
    public void editUser(User user){
        User oldUser=em.find(User.class, user.getUserName());
        if (oldUser==null)
            throw new RuntimeException("UserNotExistsException");
        if (user.getFirstName()!=null && !user.getFirstName().equals(""))
            oldUser.setFirstName(user.getFirstName());
        if (user.getLastName()!=null && !user.getLastName().equals(""))
            oldUser.setLastName(user.getLastName());
        if (user.getUserCity()!=null){
            user.setUserCity(em.find(City.class, user.getUserCity().getId()));
            if (user.getUserCity()!=null)
                oldUser.setUserCity(user.getUserCity());
        }
    }
    
    public void deleteUser(User user){
        if ((user=em.find(User.class, user.getUserName()))==null)
            throw new RuntimeException("UserNotExistsException");
        try {
            em.remove(user);
        } catch (Exception ex){
            throw new RuntimeException("DeleteException");
        }
    }
    
    public List<User> getAllUsers(){
        Query query = em.createNamedQuery("com.avin.wholesale.persistence.user.AllUsers");
        return query.getResultList();
    }
}
