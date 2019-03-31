/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service;

import com.faculte.simplefacultebudget.domain.bean.BudgetEntiteAdministratif;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface BudgetEntiteAdministratifService {

    public BudgetEntiteAdministratif findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public List<BudgetEntiteAdministratif> findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);

    public int createBudgetEntiteAdministratif(BudgetSousProjet budgetSousProjet, List<BudgetEntiteAdministratif> budgetEntiteAdministratifs);

    public int payerBudgetEA(BudgetEntiteAdministratif budgetEntiteAdministratif, double montant);

    public List<BudgetEntiteAdministratif> findByBudgetSousProjetBudgetFaculteAnnee(int annee);

    public void removeBea(String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public void save(BudgetEntiteAdministratif entiteAdministratif);

    public double getAnticident(String refEa, String refSp, int annee);

    public int updateBudgetEntiteAdministratif(BudgetEntiteAdministratif bea, BudgetEntiteAdministratif entiteAdministratif,double nvReliquatReelBudgetSousProjet,double nvReliquatEstimatifBudgetSousProjet);

    public List<BudgetEntiteAdministratif> findByBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetFaculteAnneeLessThan(Integer anneeMin, Integer anneeMax);

    public boolean isEqual(BudgetEntiteAdministratif bea, BudgetEntiteAdministratif entiteAdministratif);
}
