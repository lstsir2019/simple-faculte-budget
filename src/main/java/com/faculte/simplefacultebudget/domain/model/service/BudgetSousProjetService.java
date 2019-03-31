/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface BudgetSousProjetService {

    public BudgetSousProjet findByReferenceSousProjetAndBudgetFaculteAnnee(String referenceSousProjet, int annee);

    public List<BudgetSousProjet> findByBudgetFaculteAnnee(int annee);

    public void save(BudgetSousProjet budgetSousProjet);

    public int createBudgetSousProjet(BudgetFaculte budgetFacultet, List<BudgetSousProjet> BudgetSousProjets);

    public int payerSousProjet(BudgetSousProjet budgetSousProjet, double prix);

    public void removeBsp(int annee, String referenceSousProjet);
    
    public double getAnticident(String reference, int annee);
    
    public int updateBudgetSouSprojet(BudgetSousProjet bspOld, BudgetSousProjet sousProjet, double reliquatReelBudgetFaculte, double reliquatEstimatifBudgetFaculte);
    
    List<BudgetSousProjet> findByBudgetFaculteAnneeOrBudgetFaculteAnnee(Integer anneeMin, Integer anneeMax);
    
    public boolean isEqual(BudgetSousProjet bsp, BudgetSousProjet sousProjet);
}
