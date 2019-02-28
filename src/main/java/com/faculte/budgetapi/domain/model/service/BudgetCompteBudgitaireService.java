/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.model.service;

import com.faculte.budgetapi.domain.bean.BudgetCompteBudgitaire;
import com.faculte.budgetapi.domain.bean.BudgetEntiteAdministratif;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface BudgetCompteBudgitaireService {

    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public BudgetCompteBudgitaire findByCompteBudgitaireCode(String code);

    public int creerBudgetCompteBudgitaire(BudgetCompteBudgitaire budgetCompteBudgitaire);

    public BudgetCompteBudgitaire findByCompteBudgitaireCodeAndBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String code, String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public void deleteBudgetEntiteAdministratif(String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public void deleteBudgetCompteBudgitaire(BudgetCompteBudgitaire budgetCompteBudgitaire);

    public int createBudgetCompteBudgitaire(BudgetEntiteAdministratif budgetEntiteAdministratif, List<BudgetCompteBudgitaire> budgetCompteBudgitaires);

    public int payerBCB(String code);

    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(int annee);

    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);
}
