/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.model.dao;

import com.faculte.budgetapi.domain.bean.BudgetSousProjet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AMINE
 */
@Repository
public interface BudgetSousProjetDao extends JpaRepository<BudgetSousProjet, Long>{
    public BudgetSousProjet findByReferenceSousProjetAndBudgetFaculteAnnee (String referenceSousProjet , int annee);
    public List<BudgetSousProjet> findByBudgetFaculteAnnee(int annee);
}
