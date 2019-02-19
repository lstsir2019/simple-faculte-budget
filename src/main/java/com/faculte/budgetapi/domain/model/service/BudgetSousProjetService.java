/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.model.service;

import com.faculte.budgetapi.domain.bean.BudgetSousProjet;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface BudgetSousProjetService {

 
    public BudgetSousProjet findByReferenceSousProjetAndBudgetFaculteAnnee (String referenceSousProjet , int annee);

    public List<BudgetSousProjet> findByBudgetFaculteAnnee(int annee);

    public int creerBudgetSousProjet(BudgetSousProjet budgetSousProjet);
    public void updateReliquatBsp (BudgetSousProjet budgetSousProjet);
    public void deleteBudgetFaculte (int annee);
    public void deleteBudgetSousProjet (BudgetSousProjet budgetSousProjet);
}
