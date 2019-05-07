/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.dao;

import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AMINE
 */
@Repository
public interface BudgetSousProjetDao extends JpaRepository<BudgetSousProjet, Long> {

    public BudgetSousProjet findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);

    public List<BudgetSousProjet> findBybudgetProjetBudgetFaculteAnnee(int annee);

    @Query("SELECT bsp FROM BudgetSousProjet bsp WHERE bsp.budgetProjet.budgetFaculte.annee >= ?1 or bsp.budgetProjet.budgetFaculte.annee <= ?2")
    List<BudgetSousProjet> findByBudgetFaculteAnneeOrBudgetFaculteAnnee(Integer anneeMin, Integer anneeMax);

}
