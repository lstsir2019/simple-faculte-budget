/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.rest;

import com.faculte.budgetapi.domain.bean.BudgetSousProjet;
import com.faculte.budgetapi.domain.model.service.BudgetSousProjetService;
import com.faculte.budgetapi.domain.res.converter.BudgetSousProjetConverter;
import com.faculte.budgetapi.domain.rest.vo.BudgetSousProjetVo;
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
@RequestMapping("/budget_api/budget_sous_projet")
@CrossOrigin(origins = "http://localhost:4200")

@RestController()
public class BudgetSousProjetRest {

    @Autowired
    private BudgetSousProjetService budgetSousProjetService;

    public BudgetSousProjetService getBudgetSousProjetService() {
        return budgetSousProjetService;
    }

    public void setBudgetSousProjetService(BudgetSousProjetService budgetSousProjetService) {
        this.budgetSousProjetService = budgetSousProjetService;
    }

    public BudgetSousProjet findByReferenceSousProjetAndBudgetFaculteAnnee(@PathVariable String referenceSousProjet, @PathVariable int annee) {
        return budgetSousProjetService.findByReferenceSousProjetAndBudgetFaculteAnnee(referenceSousProjet, annee);
    }

    @GetMapping("/annee/{annee}")
    public List<BudgetSousProjet> findByBudgetFaculteAnnee(@PathVariable int annee) {
        return budgetSousProjetService.findByBudgetFaculteAnnee(annee);
    }

    @PostMapping("/")
    public int creerBudgetSousProjet(@RequestBody BudgetSousProjetVo budgeSousProjetVo) {
        BudgetSousProjetConverter budgetSousProjetConverter = new BudgetSousProjetConverter();
        BudgetSousProjet budgetSousProjet = budgetSousProjetConverter.toItem(budgeSousProjetVo);
        budgetSousProjetConverter.toVo(budgetSousProjet);
        return budgetSousProjetService.creerBudgetSousProjet(budgetSousProjet);
    }

    @DeleteMapping("/annee/{annee}")

    public void deleteBudgetFaculte(@PathVariable int annee) {
        budgetSousProjetService.deleteBudgetFaculte(annee);
    }

}
