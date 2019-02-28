/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.rest;

import com.faculte.budgetapi.domain.bean.BudgetEntiteAdministratif;
import com.faculte.budgetapi.domain.bean.BudgetSousProjet;
import com.faculte.budgetapi.domain.model.service.BudgetEntiteAdministratifService;
import com.faculte.budgetapi.domain.res.converter.BudgetCompteBudgitaireConverter;
import com.faculte.budgetapi.domain.res.converter.BudgetEntiteAdministratifConverter;
import com.faculte.budgetapi.domain.rest.vo.BudgetEntiteAdministratifVo;
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
@RequestMapping("/budget_api/budget_entite_admin")
@CrossOrigin(origins = "http://localhost:4200")

@RestController()
public class BudgetEntiteAdministratifRest {

    @Autowired
    private BudgetEntiteAdministratifService budgetEntiteAdministratifService;

    @GetMapping("/referenceEntiteAdmin/{referenceEntiteAdmin}/refSousProjet/{referenceSousProjet}/annee/{annee}")
    public BudgetEntiteAdministratif findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(@PathVariable("referenceEntiteAdmin") String referenceEntiteAdministratif, @PathVariable("referenceSousProjet") String referenceSousProjet, @PathVariable("annee") int annee) {
        return budgetEntiteAdministratifService.findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
    }

    @GetMapping("/refSousProjet/{referenceSousProjet}/annee/{annee}")
    public List<BudgetEntiteAdministratif> findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(@PathVariable String referenceSousProjet, @PathVariable int annee) {
        return budgetEntiteAdministratifService.findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceSousProjet, annee);
    }

    @PostMapping("/")
    public int creerBudgetEntiteAdministratif(@RequestBody BudgetEntiteAdministratifVo budgetEntiteAdministratifVo) {
        BudgetEntiteAdministratifConverter budgetEntiteAdministratifConverter = new BudgetEntiteAdministratifConverter();
        BudgetEntiteAdministratif budgetEntiteAdministratif = budgetEntiteAdministratifConverter.toItem(budgetEntiteAdministratifVo);
        budgetEntiteAdministratifConverter.toVo(budgetEntiteAdministratif);
        return budgetEntiteAdministratifService.creerBudgetEntiteAdministratif(budgetEntiteAdministratif);
    }

    public BudgetEntiteAdministratifService getBudgetEntiteAdministratifService() {
        return budgetEntiteAdministratifService;
    }

    public void setBudgetEntiteAdministratifService(BudgetEntiteAdministratifService budgetEntiteAdministratifService) {
        this.budgetEntiteAdministratifService = budgetEntiteAdministratifService;
    }

    @DeleteMapping("/refSousProjet/{budgetSousProjet}/annee/{annee}")
    public void deleteBudgetSousProjet(@PathVariable String referenceSousProjet, @PathVariable int annee) {
        budgetEntiteAdministratifService.deleteBudgetSousProjet(referenceSousProjet, annee);
    }

    @GetMapping("/annee/{annee}")
    public List<BudgetEntiteAdministratif> findByBudgetSousProjetBudgetFaculteAnnee(@PathVariable("annee") int annee) {
        return budgetEntiteAdministratifService.findByBudgetSousProjetBudgetFaculteAnnee(annee);
    }

}
