/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface BudgetProjetService {

    public BudgetProjet findByReferenceProjetAndBudgetFaculteAnnee(String referenceProjet, int annee);

    public List<BudgetProjet> findByBudgetFaculteAnnee(int annee);

    public void deleteById(Long id);
    
    public BudgetProjet findById(Long id);

    public void save(BudgetProjet budgetProjet);

    public int createBudgetProjet(BudgetFaculte budgetFacultet, List<BudgetProjet> budgetProjets);

    public void calculeDetaillesBudgetProjet(BudgetFaculte budgetFaculte);

}
