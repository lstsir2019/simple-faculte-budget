/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author AMINE
 */
public interface BudgetSousProjetService {

    public void save(BudgetSousProjet budgetSousProjet);

    public int createBudgetSousProjet(BudgetProjet budgetProjet, List<BudgetSousProjet> BudgetSousProjets);

    public List<BudgetSousProjet> findByBudgetProjetReferenceProjetAndBudgetProjetBudgetFaculteAnnee(String referenceProjet, int annee);

    public int payerSousProjet(BudgetSousProjet budgetSousProjet, double prix);

    public void removeBsp(int annee, String referenceSousProjet);

    public double getAnticident(String reference, int annee);

    public int updateBudgetSouSprojet(BudgetSousProjet bspOld, BudgetSousProjet sousProjet, double reliquatReelBudgetFaculte, double reliquatEstimatifBudgetFaculte);

    public boolean isEqual(BudgetSousProjet bsp, BudgetSousProjet sousProjet);

    public BudgetSousProjet findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);

    public List<BudgetSousProjet> findBybudgetProjetBudgetFaculteAnnee(int annee);

    List<BudgetSousProjet> findByBudgetFaculteAnneeOrBudgetFaculteAnnee(Integer anneeMin, Integer anneeMax);
}
