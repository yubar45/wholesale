/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avin.wholesale.ejb;

import com.avin.wholesale.persistence.Ads;
import com.avin.wholesale.persistence.Category;
import com.avin.wholesale.persistence.Contact;
import com.avin.wholesale.persistence.User;
import java.util.ArrayList;
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
public class AdsBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
    public void addNewAds(Ads ads){
        if (ads.getCategory()==null)
            throw new RuntimeException("NullAdsCategoryException");
        ads.setCategory(em.find(Category.class, ads.getCategory().getId()));
        if (ads.getCategory()==null)
            throw new RuntimeException("CategoryNotExistsException");
        if (ads.getOwner()==null)
            throw new RuntimeException("NullAdsOwnerException");
        ads.setOwner(em.find(User.class, ads.getOwner().getUserName()));
        if (ads.getOwner()==null)
            throw new RuntimeException("UserNotExistsException");
        List<Contact> adsContact=new ArrayList();
        for (Contact c:ads.getContacts()){
            Contact temp=em.find(Contact.class, c.getId());
            if (temp!=null)
                adsContact.add(temp);
        }
        if (adsContact.isEmpty())
            throw new RuntimeException("EmptyAdsContactsException");
        ads.setContacts(adsContact);
        try {
            em.persist(ads);
        } catch (Exception ex){
            throw new RuntimeException("PersistException");
        }
    }
    
    public void editAdsCategory(Ads ads){
        Ads oldAds=em.find(Ads.class, ads.getId());
        if (oldAds==null)
            throw new RuntimeException("AdsNotExistsException");
        if (ads.getCategory()==null)
            throw new RuntimeException("NullAdsCategoryException");
        ads.setCategory(em.find(Category.class, ads.getCategory().getId()));
        if (ads.getCategory()==null)
            throw new RuntimeException("CategoryNotExistsException");
        oldAds.setCategory(ads.getCategory());
    }
    
    public void editAdsContacts(Ads ads){
        Ads oldAds=em.find(Ads.class, ads.getId());
        if (oldAds==null)
            throw new RuntimeException("AdsNotExistsException");
        if (ads.getContacts()==null || ads.getContacts().isEmpty())
            throw new RuntimeException("EmptyAdsContactsException");
        oldAds.getContacts().clear();
        for (Contact c:ads.getContacts()){
            Contact temp=em.find(Contact.class, c.getId());
            oldAds.getContacts().add(temp);
        }
        if (oldAds.getContacts().isEmpty())
            throw new RuntimeException("EmptyAdsContactsException");
    }
    
    public void editAdsActivityStates(Ads ads){
        Ads oldAds=em.find(Ads.class, ads.getId());
        if (oldAds==null)
            throw new RuntimeException("AdsNotExistsException");
        oldAds.getContacts().clear();
        for (Contact c:ads.getContacts()){
            Contact temp=em.find(Contact.class, c.getId());
            oldAds.getContacts().add(temp);
        }
    }
    
    public void editAdsDescription(Ads ads){
        Ads oldAds=em.find(Ads.class, ads.getId());
        if (oldAds==null)
            throw new RuntimeException("AdsNotExistsException");
        oldAds.setDescription(ads.getDescription());
    }
    
    public void editAdsAddress(Ads ads){
        Ads oldAds=em.find(Ads.class, ads.getId());
        if (oldAds==null)
            throw new RuntimeException("AdsNotExistsException");
        oldAds.setAddress(ads.getAddress());
    }
    
    public void deleteAds(Ads ads){
        if ((ads=em.find(Ads.class, ads.getId()))==null)
            throw new RuntimeException("AdsNotExistsException");
        try {
            em.remove(ads);
        } catch (Exception ex){
            throw new RuntimeException("DeleteException");
        }
    }
    
    public List<Ads> getAllAds(){
        Query query = em.createNamedQuery("com.avin.wholesale.persistence.ads.AllAds");
        return query.getResultList();
    }
    
    public void fillAdsContacts(Ads ads){
        Ads oldAds=em.find(Ads.class, ads.getId());
        if (oldAds==null)
            throw new RuntimeException("AdsNotExistsException");
        ads.setContacts(oldAds.getContacts());
    }
    
    public void fillAdsActivityStates(Ads ads){
        Ads oldAds=em.find(Ads.class, ads.getId());
        if (oldAds==null)
            throw new RuntimeException("AdsNotExistsException");
        ads.setActivityStates(oldAds.getActivityStates());
    }
}
