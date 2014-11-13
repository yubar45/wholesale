/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avin.wholesale.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Yubar
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"owner", "category"})})
public class Ads implements Serializable {
    private boolean confirmed=false;
    private int id;
    private Category category;
    private String description;
    private Date creationDate;
    private String address;
    private String image;
    private User owner;
    private List<State> activityStates;
    private List<Contact> contacts;

    public Ads() {
        this.creationDate=new Date();
    }

    public Ads(int id) {
        this.id=id;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "category")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(length = 3000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @ManyToMany
    @JoinTable(name = "Ads_ActivityStates", joinColumns = @JoinColumn(name = "ads"), inverseJoinColumns = @JoinColumn(name = "activityState"))
    public List<State> getActivityStates() {
        return activityStates;
    }

    public void setActivityStates(List<State> activityStates) {
        this.activityStates = activityStates;
    }

    @ManyToMany
    @JoinTable(name = "Ads_Contacts", joinColumns = @JoinColumn(name = "ads"), inverseJoinColumns = @JoinColumn(name = "contact"))
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    @Column(nullable = false)
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.confirmed ? 1 : 0);
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.category);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.creationDate);
        hash = 29 * hash + Objects.hashCode(this.address);
        hash = 29 * hash + Objects.hashCode(this.owner);
        hash = 29 * hash + Objects.hashCode(this.activityStates);
        hash = 29 * hash + Objects.hashCode(this.contacts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ads other = (Ads) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
