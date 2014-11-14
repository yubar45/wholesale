/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avin.wholesale.ejb;

import com.avin.wholesale.persistence.City;
import com.avin.wholesale.persistence.Role;
import com.avin.wholesale.persistence.State;
import com.avin.wholesale.persistence.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yubar
 */
@Startup
@Singleton
public class StartupBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    private void init(){
        try {
            registerAdmin();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StartupBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void registerAdmin() throws NoSuchAlgorithmException{
        if (em.find(User.class, "yubar")!=null)
            return ;
        User user=new User("حسن", "موسوی", "yubar");
        user.setEmail("yubar45@gmail.com");
        user.setPassword(new BigInteger(1, MessageDigest.getInstance("SHA-256").digest("yubar".getBytes())).toString(16));
        user.setRegistrationDate(new Date());
        user.setUserCity(new City("کرج", new State("البرز")));
        user.setUserRoles(new ArrayList<Role>());
        user.getUserRoles().add(Role.admin);
        user.getUserRoles().add(Role.manager);
        user.getUserRoles().add(Role.confirmeduser);
        em.persist(user.getUserCity().getCityState());
        em.persist(user.getUserCity());
        em.persist(user);
    }
}
