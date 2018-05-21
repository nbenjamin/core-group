package com.provider.restaurantservice.domain;

import javax.persistence.Enumerated;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private boolean isVeggie;
    private Float price;
    @Enumerated
    private CuisineType cuisineType;
    private boolean isAvaliableAtBreakfast;
    private boolean isAvaliableAtLunch;
    private boolean isAvaliableAtSnack;
    private boolean isAvaliableAtDinner;
//    @Enumerated
//    private Set<DayOfWeek> dayOfWeeks =new HashSet();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVeggie() {
        return isVeggie;
    }

    public void setVeggie(boolean veggie) {
        isVeggie = veggie;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public boolean isAvaliableAtBreakfast() {
        return isAvaliableAtBreakfast;
    }

    public void setAvaliableAtBreakfast(boolean avaliableAtBreakfast) {
        isAvaliableAtBreakfast = avaliableAtBreakfast;
    }

    public boolean isAvaliableAtLunch() {
        return isAvaliableAtLunch;
    }

    public void setAvaliableAtLunch(boolean avaliableAtLunch) {
        isAvaliableAtLunch = avaliableAtLunch;
    }

    public boolean isAvaliableAtSnack() {
        return isAvaliableAtSnack;
    }

    public void setAvaliableAtSnack(boolean avaliableAtSnack) {
        isAvaliableAtSnack = avaliableAtSnack;
    }

    public boolean isAvaliableAtDinner() {
        return isAvaliableAtDinner;
    }

    public void setAvaliableAtDinner(boolean avaliableAtDinner) {
        isAvaliableAtDinner = avaliableAtDinner;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, true);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
