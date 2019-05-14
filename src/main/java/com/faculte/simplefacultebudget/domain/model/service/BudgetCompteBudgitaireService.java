/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface BudgetCompteBudgitaireService {

    public int createBudgetCompteBudgitaire(BudgetSousProjet budgetSousProjet, List<BudgetCompteBudgitaire> budgetCompteBudgitaires);

    public int payerBCB(String code);

    public void removeBcb(String referenceCompteBudgitaire);

    public double getAnticident(String code, String refEa, String refBsp, int annee);

    public int updateBudgetCompteBudgitaire(BudgetCompteBudgitaire bcb, BudgetCompteBudgitaire budgetCompteBudgitaire, double nvReliquatReelBudgetEntiteAdministratif, double nvReliquatEstimatifBudgetEntiteAdministratif);

    public boolean isEqual(BudgetCompteBudgitaire bcb, BudgetCompteBudgitaire compteBudgitaire);

    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetReferenceProjetAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String referenceProjet, String referenceSousProjet, int annee);

    public BudgetCompteBudgitaire findByCompteBudgitaireCodeAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String code, String referenceSousProjet, int annee);

    public BudgetCompteBudgitaire findByCompteBudgitaireCode(String code);

    public List<BudgetCompteBudgitaire> findDistinctByBudgetSousProjetBudgetProjetBudgetFaculteAnnee(int annee);

    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetReferenceProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);

    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetProjetBudgetFaculteAnneeLessThan(Integer anneeMin, Integer anneeMax);

    public BudgetCompteBudgitaire removebudgetCompteBudgitaire(String codeCompteBudgitaire, String referenceSousProjet, int annee);

    public BudgetCompteBudgitaire findByReference(String reference);

    public void calculeDetaillesbudgetCompteBudgitaire(BudgetSousProjet bsp);
}
