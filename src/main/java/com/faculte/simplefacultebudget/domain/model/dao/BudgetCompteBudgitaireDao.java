/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.dao;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.bean.DetaillesBudget;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    
    @Query("SELEC new com.faculte.simplefacultebudget.domain.bean.DetaillesBudget(SUM(bcb.detaillesBudget.creditOuvertEstimatif), SUM(bcb.detaillesBudget.creditOuvertReel), SUM(bcb.detaillesBudget.reliquatEstimatif), SUM(bcb.detaillesBudget.reliquatReel), SUM(bcb.detaillesBudget.engageNonPaye), SUM( bcb.detaillesBudget.engagePaye), SUM( bcb.detaillesBudget.reliquatPayeEstimatif), SUM( bcb.detaillesBudget.reliquatPayereel), SUM( bcb.detaillesBudget.reliquatNonPayeEstimatif), SUM(bcb.detaillesBudget.reliquatNonPayReel) From BudgetCompteBudgitaire bcb Where bcb.compteBudgitaire.code=?1 and budgetSousProjet.budgetProjet.budgetFaculte.annee=2?")
    public DetaillesBudget findDetaillesBudgetByCompteBudgitaireAndAnne(String code,int annee);

    public BudgetCompteBudgitaire findByCompteBudgitaireCodeAndCompteBudgitaireAnneeAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String codeCompteBudgitaire, int annee, String referenceSousProjet, int annee0);

}
