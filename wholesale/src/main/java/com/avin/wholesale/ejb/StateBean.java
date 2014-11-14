/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avin.wholesale.ejb;

import com.avin.wholesale.persistence.City;
import com.avin.wholesale.persistence.State;
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
public class StateBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
    public void addNewState(State state){
        if (state.getStateName()==null || state.getStateName().equals(""))
            throw new RuntimeException("NullStateNameException");
        try {
            em.persist(state);
        } catch (Exception ex){
            throw new RuntimeException("PersistException");
        }
    }
    
    public void editState(State state){
        State oldState;
        if ((oldState=em.find(State.class, state.getId()))==null)
            throw new RuntimeException("StateNotExistsException");
        if (state.getStateName()==null || state.getStateName().equals(""))
            throw new RuntimeException("NullStateNameException");
        oldState.setStateName(state.getStateName());
    }
    
    public void deleteState(State state){
        if ((state=em.find(State.class, state.getId()))==null)
            throw new RuntimeException("StateNotExistsException");
        try {
            em.remove(state);
        } catch (Exception ex){
            throw new RuntimeException("DeleteException");
        }
    }
    
    public List<State> getAllStates(){
        Query query = em.createNamedQuery("com.avin.wholesale.persistence.state.AllStates");
        return query.getResultList();
    }
    
    public List<City> getStateCities(State state){
        Query query = em.createNamedQuery("com.avin.wholesale.persistence.city.citiesByState");
        query.setParameter("state", state);
        return query.getResultList();
    }
}
