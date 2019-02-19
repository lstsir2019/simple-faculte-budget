/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.model.dao;

import com.faculte.budgetapi.domain.bean.DetaillesBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AMINE
 */
@Repository
public interface DetaillesBudgetDao extends JpaRepository<DetaillesBudget , Long>{
    
}
