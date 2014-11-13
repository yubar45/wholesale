/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avin.wholesale.persistence;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Yubar
 */
@Entity(name = "Cities")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cityName","cityState"}))
public class City implements Serializable {
    private int id;
    private String cityName;
    private State cityState;

    public City() {
    }

    public City(int id) {
        this.id = id;
    }

    public City(String cityName, State cityState) {
        this.cityName = cityName;
        this.cityState = cityState;
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
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "cityState")
    public State getCityState() {
        return cityState;
    }

    public void setCityState(State cityState) {
        this.cityState = cityState;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.cityName);
        hash = 37 * hash + Objects.hashCode(this.cityState);
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
        final City other = (City) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
