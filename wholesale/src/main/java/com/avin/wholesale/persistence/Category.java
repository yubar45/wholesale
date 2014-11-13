/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.avin.wholesale.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Yubar
 */

@Entity(name = "Categories")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"categoryName", "parentCategory"})})
public class Category implements Serializable{
    private int id;
    private String categoryName;
    private Category parentCategory;
    private String categoryImage;
    private int rank;
    private Date creationDate;
    
    public Category(int id){
        this.id=id;
    }
    
    public Category() {
        this.creationDate=new Date();
    }

    public Category(String categoryName, Category parentCategory) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.creationDate=new Date();
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @ManyToOne
    @JoinColumn(name = "parentCategory")
    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }
    
    

    @Column(nullable = false)
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.categoryName);
        hash = 13 * hash + Objects.hashCode(this.parentCategory);
        hash = 13 * hash + this.rank;
        hash = 13 * hash + Objects.hashCode(this.creationDate);
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
        final Category other = (Category) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
