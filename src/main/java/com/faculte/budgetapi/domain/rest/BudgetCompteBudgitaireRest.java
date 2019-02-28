/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.rest;

import com.faculte.budgetapi.domain.bean.BudgetCompteBudgitaire;
import com.faculte.budgetapi.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.budgetapi.domain.res.converter.BudgetCompteBudgitaireConverter;
import com.faculte.budgetapi.domain.rest.vo.BudgetCompteBudgitaireVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/refEntite/{referenceEntiteAdministratif}/refsousProjet/{referenceSousProjet}/annee/{annee}")
    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(@PathVariable String referenceEntiteAdministratif, @PathVariable String referenceSousProjet, @PathVariable int annee) {
        return budgetCompteBudgitaireService.findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
    }

    @PostMapping("/")
    public int creerBudgetCompteBudgitaire(@RequestBody BudgetCompteBudgitaireVo budgetCompteBudgitaireVo) {
        BudgetCompteBudgitaireConverter budgetCompteBudgitaireConverter = new BudgetCompteBudgitaireConverter();
        BudgetCompteBudgitaire budgetCompteBudgitaire = budgetCompteBudgitaireConverter.toItem(budgetCompteBudgitaireVo);
        budgetCompteBudgitaireConverter.toVo(budgetCompteBudgitaire);
        return budgetCompteBudgitaireService.creerBudgetCompteBudgitaire(budgetCompteBudgitaire);
    }

    public BudgetCompteBudgitaireService getBudgetCompteBudgitaireService() {
        return budgetCompteBudgitaireService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
    }

    @DeleteMapping
    public void deleteBudgetEntiteAdministratif(String referenceEntiteAdministratif, String referenceSousProjet, int annee) {
        budgetCompteBudgitaireService.deleteBudgetEntiteAdministratif(referenceEntiteAdministratif, referenceSousProjet, annee);
    }

    @GetMapping("/annee/{annee}")
    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(@PathVariable("annee") int annee) {
        return budgetCompteBudgitaireService.findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(annee);
    }

    @GetMapping("/reference/{reference}/annee/{annee}")
    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(@PathVariable("reference") String referenceSousProjet, @PathVariable("annee") int annee) {
        return budgetCompteBudgitaireService.findByBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(referenceSousProjet, annee);
    }

}
