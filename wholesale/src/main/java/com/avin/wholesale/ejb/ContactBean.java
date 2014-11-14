/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avin.wholesale.ejb;

import com.avin.wholesale.persistence.Contact;
import com.avin.wholesale.persistence.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yubar
 */
@Stateless
public class ContactBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
    public void addNewContact(Contact contact){
        if (contact.getOwner()==null)
            throw new RuntimeException("NullContactOwnerException");
        contact.setOwner(em.find(User.class, contact.getOwner().getUserName()));
        if (contact.getOwner()==null)
            throw new RuntimeException("UserNotExistsException");
        try {
            em.persist(contact);
        } catch (Exception ex){
            throw new RuntimeException("PersistExecption");
        }
    }
    
    public void editContact(Contact contact){
        Contact oldContact=em.find(Contact.class, contact.getId());
        if (oldContact==null)
            throw new RuntimeException("ContactNotExistsException");
        if (contact.getContactName()!=null && !contact.getContactName().equals(""))
            oldContact.setContactName(contact.getContactName());
        if (contact.getContactNumber()!=null && !contact.getContactNumber().equals(""))
            oldContact.setContactNumber(contact.getContactNumber());
        if (contact.getOwner()!=null){
            contact.setOwner(em.find(User.class, contact.getOwner().getUserName()));
            if (contact.getOwner()==null)
                throw new RuntimeException("UserNotExistsException");
            oldContact.setOwner(contact.getOwner());
        }
    }
    
    public void deleteContact(Contact contact){
        if ((contact=em.find(Contact.class, contact.getId()))==null)
            throw new RuntimeException("ContactNotExistsException");
        try {
            em.remove(contact);
        } catch (Exception ex){
            throw new RuntimeException("DeleteException");
        }
    }
}
