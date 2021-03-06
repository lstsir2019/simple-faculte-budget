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

    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetReferenceProjetAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String referenceProjet, String referenceSousProjet, int annee);

    public BudgetCompteBudgitaire findByCompteBudgitaireCodeAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String code, String referenceSousProjet, int annee);

    public BudgetCompteBudgitaire findByCompteBudgitaireCode(String code);

    public List<BudgetCompteBudgitaire> findDistinctByBudgetSousProjetBudgetProjetBudgetFaculteAnnee(int annee);

    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetReferenceProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);

    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetProjetBudgetFaculteAnneeLessThan(Integer anneeMin, Integer anneeMax);

    public BudgetCompteBudgitaire findByReference(String reference);

    @Query("SELECT new BudgetCompteBudgitaire(bcb.compteBudgitaire,SUM(bcb.detaillesBudget.creditOuvertReel), SUM( bcb.detaillesBudget.creditOuvertEstimatif),SUM(bcb.detaillesBudget.engagePaye),SUM(bcb.detaillesBudget.engageNonPaye)) From BudgetCompteBudgitaire bcb Where bcb.budgetSousProjet.budgetProjet.budgetFaculte.annee=?1 GROUP BY bcb.compteBudgitaire.code")
    public List<BudgetCompteBudgitaire> findDetaillesBudgetByAnne(int annee);

    @Query("SELECT new BudgetCompteBudgitaire(bcb.compteBudgitaire,SUM(bcb.detaillesBudget.creditOuvertReel), SUM( bcb.detaillesBudget.creditOuvertEstimatif),SUM(bcb.detaillesBudget.engagePaye),SUM(bcb.detaillesBudget.engageNonPaye)) From BudgetCompteBudgitaire bcb Where bcb.budgetSousProjet.budgetProjet.id=?1 GROUP BY bcb.compteBudgitaire.code")
    public List<BudgetCompteBudgitaire> findDetaillesBudgetByProjet(Long id);

    // @Query("SELECT new com.faculte.simplefacultebudget.domain.bean.DetaillesBudget(SUM(bcb.detaillesBudget.creditOuvertEstimatif), SUM(bcb.detaillesBudget.creditOuvertReel), SUM(bcb.detaillesBudget.reliquatEstimatif), SUM(bcb.detaillesBudget.reliquatReel), SUM(bcb.detaillesBudget.engageNonPaye), SUM( bcb.detaillesBudget.engagePaye), SUM( bcb.detaillesBudget.reliquatPayeEstimatif), SUM( bcb.detaillesBudget.reliquatPayereel), SUM( bcb.detaillesBudget.reliquatNonPayeEstimatif), SUM(bcb.detaillesBudget.reliquatNonPayReel)) From BudgetCompteBudgitaire bcb Where bcb.compteBudgitaire.code=?1 and budgetSousProjet.budgetProjet.budgetFaculte.annee=?2")
    //   @Query("SELECT new DetaillesBudget(SUM(bcb.detaillesBudget.creditOuvertEstimatif), SUM(bcb.detaillesBudget.creditOuvertReel), SUM(bcb.detaillesBudget.reliquatEstimatif), SUM(bcb.detaillesBudget.reliquatReel), SUM(bcb.detaillesBudget.engageNonPaye), SUM( bcb.detaillesBudget.engagePaye), SUM( bcb.detaillesBudget.reliquatPayeEstimatif), SUM( bcb.detaillesBudget.reliquatPayereel), SUM( bcb.detaillesBudget.reliquatNonPayeEstimatif), SUM(bcb.detaillesBudget.reliquatNonPayReel)) From BudgetCompteBudgitaire bcb Where bcb.compteBudgitaire.code=?1 and budgetSousProjet.budgetProjet.budgetFaculte.annee=?2")
    //  public List<BudgetCompteBudgitaire> findDetaillesByProjet(String code, int annee);
    public Long countByCompteBudgitaireCode(String code);
//    public BudgetCompteBudgitaire findByCompteBudgitaireCodeAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String codeCompteBudgitaire,String referenceSousProjet, int annee);

}
