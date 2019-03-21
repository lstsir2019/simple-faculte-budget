/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.bean.BudgetEntiteAdministratif;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface BudgetCompteBudgitaireService {

    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public BudgetCompteBudgitaire findByCompteBudgitaireCode(String code);

    public BudgetCompteBudgitaire findByCompteBudgitaireCodeAndBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String code, String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public int createBudgetCompteBudgitaire(BudgetEntiteAdministratif budgetEntiteAdministratif, List<BudgetCompteBudgitaire> budgetCompteBudgitaires);

    public int payerBCB(String code);

    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(int annee);

    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);

    public void removeBcb(String referenceCompteBudgitaire);

    public double getAnticident(String code, String refEa, String refBsp, int annee);

    public void updateBudgetCompteBudgitaire(BudgetCompteBudgitaire bcb, BudgetCompteBudgitaire budgetCompteBudgitaire);

    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnneeLessThan(Integer anneeMin, Integer anneeMax);

    public boolean isEqual(BudgetCompteBudgitaire bcb, BudgetCompteBudgitaire compteBudgitaire);
    
    public BudgetCompteBudgitaire findByReferenceCompteBudgitaire(String reference);
}
