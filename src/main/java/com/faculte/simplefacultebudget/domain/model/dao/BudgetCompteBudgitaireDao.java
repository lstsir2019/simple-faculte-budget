/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.dao;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AMINE
 */
@Repository
public interface BudgetCompteBudgitaireDao extends JpaRepository<BudgetCompteBudgitaire, Long> {

    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetreferenceProjetAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String referenceProjet, String referenceSousProjet, int annee);

    public BudgetCompteBudgitaire findByCompteBudgitaireCodeAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String code,String referenceSousProjet, int annee);

    public BudgetCompteBudgitaire findByCompteBudgitaireCode(String code);

    public List<BudgetCompteBudgitaire> findDistinctByBudgetSousProjetBudgetProjetBudgetFaculteAnnee(int annee);

    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetreferenceProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);

    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetProjetBudgetFaculteAnneeLessThan(Integer anneeMin, Integer anneeMax);

    public BudgetCompteBudgitaire findByReference(String reference);
}
