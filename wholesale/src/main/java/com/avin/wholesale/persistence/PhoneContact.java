/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avin.wholesale.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author Yubar
 */
@Entity(name = "PhoneContacts")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cityCode" , "phoneNumber"}))
public class PhoneContact extends Contact{
    private String cityCode;
    private String phoneNumber;

    public PhoneContact() {
    }

    public PhoneContact(String contactName, String cityCode, String phoneNumber) {
        this.contactName = contactName;
        this.cityCode = cityCode;
        this.phoneNumber = phoneNumber;
    }
    
    public PhoneContact(int id){
        this.id=id;
    }

    @Column(nullable = false, length = 4)
    @Size(min = 2, max = 3)
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Column(nullable = false, length = 8)
    @Size(min=7, max = 8)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    @Transient
    public String getContactNumber() {
        return this.cityCode.concat(this.phoneNumber);
    }
    
    @Override
    public void setContactNumber(String contactNumber) {
        this.cityCode=contactNumber.substring(0, 3);
        this.phoneNumber=contactNumber.substring(3);
    }

}
