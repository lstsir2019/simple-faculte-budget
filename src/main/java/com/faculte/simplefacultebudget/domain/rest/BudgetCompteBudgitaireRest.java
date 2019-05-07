/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.res.converter.AbstractConverter;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetCompteBudgitaireVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AMINE
 */
@RequestMapping("/budget_api/budget_compte_budgitaires")
@CrossOrigin(origins = "http://localhost:4200")
@RestController()
public class BudgetCompteBudgitaireRest {

    @Autowired
    private BudgetCompteBudgitaireService budgetCompteBudgitaireService;

    @Autowired
    @Qualifier("budgetCompteBudgitaireConverter")
    private AbstractConverter<BudgetCompteBudgitaire, BudgetCompteBudgitaireVo> budgetCompteBudgitaireConverter;

   
    @GetMapping("/annee/{annee}")
    public List<BudgetCompteBudgitaireVo> findDistinctByBudgetSousProjetBudgetProjetBudgetFaculteAnnee(@PathVariable("annee") int annee) {
        List<BudgetCompteBudgitaire> bcbs = budgetCompteBudgitaireService.findDistinctByBudgetSousProjetBudgetProjetBudgetFaculteAnnee(annee);
        return budgetCompteBudgitaireConverter.toVo(bcbs);
    }


    @DeleteMapping("/referenceCompteBudgitaire/{referenceCompteBudgitaire}")
    public void removeBcb(@PathVariable("referenceCompteBudgitaire") String referenceCompteBudgitaire) {
        budgetCompteBudgitaireService.removeBcb(referenceCompteBudgitaire);
    }

    @GetMapping("/anneeMin/{anneeMin}/anneeMax/{anneeMax}")
    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetProjetBudgetFaculteAnneeLessThan(@PathVariable("anneeMin") Integer anneeMin, @PathVariable("anneeMax") Integer anneeMax) {
        return budgetCompteBudgitaireService.findByBudgetSousProjetBudgetProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetProjetBudgetFaculteAnneeLessThan(anneeMin, anneeMax);
    }

    @GetMapping("/referenceCompteBudgitaire/{referenceCompteBudgitaire}")
    public BudgetCompteBudgitaire findByReferenceCompteBudgitaire(@PathVariable("referenceCompteBudgitaire") String reference) {
        return budgetCompteBudgitaireService.findByReference(reference);
    }

    public BudgetCompteBudgitaireService getBudgetCompteBudgitaireService() {
        return budgetCompteBudgitaireService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
    }
}
