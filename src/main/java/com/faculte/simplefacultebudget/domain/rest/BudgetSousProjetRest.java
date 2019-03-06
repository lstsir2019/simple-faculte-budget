/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest;

import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import com.faculte.simplefacultebudget.domain.res.converter.AbstractConverter;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetSousProjetVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("budgetSousProjetConverter")
    private AbstractConverter<BudgetSousProjet, BudgetSousProjetVo> budgetSousProjetConverter;

    public BudgetSousProjetService getBudgetSousProjetService() {
        return budgetSousProjetService;
    }

    public void setBudgetSousProjetService(BudgetSousProjetService budgetSousProjetService) {
        this.budgetSousProjetService = budgetSousProjetService;
    }

    public AbstractConverter<BudgetSousProjet, BudgetSousProjetVo> getBudgetSousProjetConverter() {
        return budgetSousProjetConverter;
    }

    public void setBudgetSousProjetConverter(AbstractConverter<BudgetSousProjet, BudgetSousProjetVo> budgetSousProjetConverter) {
        this.budgetSousProjetConverter = budgetSousProjetConverter;
    }

    @GetMapping("/reference/{reference}/annee/{annee}")
    public BudgetSousProjetVo findByReferenceSousProjetAndBudgetFaculteAnnee(@PathVariable("reference") String referenceSousProjet, @PathVariable("annee") int annee) {
        BudgetSousProjet myBsp = budgetSousProjetService.findByReferenceSousProjetAndBudgetFaculteAnnee(referenceSousProjet, annee);
        return budgetSousProjetConverter.toVo(myBsp);
    }

    @GetMapping("/annee/{annee}")
    public List<BudgetSousProjetVo> findByBudgetFaculteAnnee(@PathVariable int annee) {
        List<BudgetSousProjet> bsps = budgetSousProjetService.findByBudgetFaculteAnnee(annee);
        return budgetSousProjetConverter.toVo(bsps);
    }

    @PostMapping("/")
    public int creerBudgetSousProjet(@RequestBody BudgetSousProjetVo budgeSousProjetVo) {
        BudgetSousProjet budgetSousProjet = budgetSousProjetConverter.toItem(budgeSousProjetVo);
        budgetSousProjetConverter.toVo(budgetSousProjet);
        return budgetSousProjetService.creerBudgetSousProjet(budgetSousProjet);
    }

    @DeleteMapping("/referenceSousProjet/{referenceSousProjet}/annee/{annee}")
    public void removeBsp(@PathVariable int annee, @PathVariable String referenceSousProjet) {
        budgetSousProjetService.removeBsp(annee, referenceSousProjet);
    }

}
