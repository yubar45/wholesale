/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avin.wholesale.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Yubar
 */
@Entity(name = "MobileContacts")
public class MobileContact extends Contact{
    
    private String mobileNumber;
    
    public MobileContact() {
    }
    
    public MobileContact(String contactName, String mobileNumber){
        this.mobileNumber=mobileNumber;
        this.contactName=contactName;
    }
    
    public MobileContact(int id){
        this.id=id;
    }

    @Size(max = 11, min = 10)
    @Column(nullable = false, unique = true, length = 11)
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    @Transient
    public String getContactNumber() {
        return this.mobileNumber;
    }

    @Override
    public void setContactNumber(String contactNumber) {
        setMobileNumber(contactNumber);
    }
    
}
