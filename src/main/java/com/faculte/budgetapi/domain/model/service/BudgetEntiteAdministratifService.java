/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.model.service;

import com.faculte.budgetapi.domain.bean.BudgetEntiteAdministratif;
import com.faculte.budgetapi.domain.bean.BudgetSousProjet;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface BudgetEntiteAdministratifService {

    public BudgetEntiteAdministratif findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public List<BudgetEntiteAdministratif> findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);

    // public BudgetEntiteAdministratif findByByReferenceEntiteAdministratifBudgetSousProjetReferenceSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif ,String referenceSousProjet , int annee);
    public int creerBudgetEntiteAdministratif(BudgetEntiteAdministratif budgetEntiteAdministratif);

    public void updateReliquatBea(BudgetEntiteAdministratif budgetEntiteAdministratif);

    public void deleteBudgetSousProjet(String referenceSousProjet, int annee);

    public void deleteBudgetEntiteAdministratif(BudgetEntiteAdministratif budgetEntiteAdministratif);

    public int createBudgetEntiteAdministratif(BudgetSousProjet budgetSousProjet, List<BudgetEntiteAdministratif> budgetEntiteAdministratifs);

    public int payerBudgetEA(BudgetEntiteAdministratif budgetEntiteAdministratif, double montant);

    public List<BudgetEntiteAdministratif> findByBudgetSousProjetBudgetFaculteAnnee(int annee);
}
