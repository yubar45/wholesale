/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avin.wholesale.ejb;

import com.avin.wholesale.persistence.City;
import com.avin.wholesale.persistence.State;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yubar
 */
@Stateless
public class CityBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
    public void addNewCity(City city){
        if (city.getCityName()==null || city.getCityName().equals(""))
            throw new RuntimeException("NullCityNameException");
        if (city.getCityState()==null)
            throw new RuntimeException("NullCityStateException");
        city.setCityState(em.find(State.class, city.getCityState().getId()));
        if (city.getCityState()==null)
            throw new RuntimeException("StateNotExistsException");
        try {
            em.persist(city);
        } catch (Exception ex){
            throw new RuntimeException("PersistException");
        }
    }
    
    public void editCity(City city){
        City oldCity=em.find(City.class, city.getId());
        if (oldCity == null)
            throw new RuntimeException("CityNotExistsException");
        if (city.getCityName()!=null && !city.getCityName().equals(""))
            oldCity.setCityName(city.getCityName());
        if (city.getCityState()!=null){
            city.setCityState(em.find(State.class, city.getCityState()));
            if (city.getCityState()!=null)
                oldCity.setCityState(city.getCityState());
        }
    }
    
    public void deleteCity(City city){
        if ((city=em.find(City.class, city.getId()))==null)
            throw new RuntimeException("CityNotExistsException");
        try {
            em.remove(city);
        } catch (Exception ex){
            throw new RuntimeException("DeleteException");
        }
    }
}
