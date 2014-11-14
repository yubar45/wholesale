/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avin.wholesale.ejb;

import com.avin.wholesale.persistence.Ads;
import com.avin.wholesale.persistence.Category;
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
public class CategoryBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
    public void addNewCategory(Category category){
        if (category.getCategoryName()==null || category.getCategoryName().equals(""))
            throw new RuntimeException("NullCategoryNameException");
        if (category.getParentCategory()!=null){
            category.setParentCategory(em.find(Category.class, category.getParentCategory().getId()));
            if (category.getParentCategory()==null)
                throw new RuntimeException("CategoryNotExistsException");
        }
        try {
            em.persist(category);
        } catch (Exception ex){
            throw new RuntimeException("PersistException");
        }
    }
    
    public void editCategory(Category category){
        Category oldCategory=em.find(Category.class, category.getId());
        if (oldCategory==null)
            throw new RuntimeException("CategoryNotExistsException");
        if (category.getCategoryName()!=null && !category.getCategoryName().equals(""))
            oldCategory.setCategoryName(category.getCategoryName());
        if (category.getParentCategory()!=null){
            category.setParentCategory(em.find(Category.class, category.getParentCategory().getId()));
            if (category.getParentCategory()==null)
                throw new RuntimeException("CategoryNotExistsException");
        }
        if (category.getRank()>0)
            oldCategory.setRank(category.getRank());
    }
    
    public void deleteCategory(Category category){
        if ((category=em.find(Category.class, category.getId()))==null)
            throw new RuntimeException("CategoryNotExistsException");
        try {
            em.remove(category);
        } catch (Exception ex){
            throw new RuntimeException("DeleteException");
        }
    }
    
    public List<Category> getSubCategories(Category category){
        if (category != null && (category=em.find(Category.class, category.getId()))==null)
            throw new RuntimeException("CategoryNotExistsException");
        Query query = em.createNamedQuery("com.avin.wholesale.persistence.category.categoriesByParentCategory");
        query.setParameter("parentCategory", category);
        return query.getResultList();
    }
    
    public List<Ads> getCategoryAds(Category category){
        if ((category=em.find(Category.class, category.getId()))==null)
            throw new RuntimeException("CategoryNotExistsException");
        Query query = em.createNamedQuery("com.avin.wholesale.persistence.ads.AdsByCategory");
        query.setParameter("category", category);
        return query.getResultList();
    }
}
