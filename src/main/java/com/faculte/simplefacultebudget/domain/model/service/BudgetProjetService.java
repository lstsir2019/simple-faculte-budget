/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service;

import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface BudgetProjetService {

    public BudgetProjet findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public List<BudgetProjet> findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);

    public int createBudgetEntiteAdministratif(BudgetSousProjet budgetSousProjet, List<BudgetProjet> budgetEntiteAdministratifs);

    public int payerBudgetEA(BudgetProjet budgetEntiteAdministratif, double montant);

    public List<BudgetProjet> findByBudgetSousProjetBudgetFaculteAnnee(int annee);

    public void removeBea(String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public void save(BudgetProjet entiteAdministratif);

    public double getAnticident(String refEa, String refSp, int annee);

    public int updateBudgetEntiteAdministratif(BudgetProjet bea, BudgetProjet entiteAdministratif,double nvReliquatReelBudgetSousProjet,double nvReliquatEstimatifBudgetSousProjet);

    public List<BudgetProjet> findByBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetFaculteAnneeLessThan(Integer anneeMin, Integer anneeMax);

    public boolean isEqual(BudgetProjet bea, BudgetProjet entiteAdministratif);
}
