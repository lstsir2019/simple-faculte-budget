/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest;

import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.res.converter.AbstractConverter;
import com.faculte.simplefacultebudget.domain.rest.proxy.EntiteAdministratifService;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetProjetVo;
import com.faculte.simplefacultebudget.domain.rest.vo.exchange.EntiteAdministratifVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.faculte.simplefacultebudget.domain.model.service.BudgetProjetService;

/**
 *
 * @author AMINE
 */
@RequestMapping("/budget_api/budget_entite_admin")
@CrossOrigin(origins = "http://localhost:4200")

@RestController()
public class BudgetProjetRest {

    @Autowired
    private BudgetProjetService budgetProjetService;

    @Autowired
    @Qualifier("budgetProjetConverter")
    private AbstractConverter<BudgetProjet, BudgetProjetVo> budgetProjetConverter;

   

    @GetMapping("/referenceprojet/{referenceProjet}/annee/{annee}")
     public BudgetProjet findByReferenceProjetAndBudgetFaculteAnnee(@PathVariable String referenceProjet,@PathVariable int annee) {
        return budgetProjetService.findByReferenceProjetAndBudgetFaculteAnnee(referenceProjet, annee);
    }

    @GetMapping("/annee/{annee}")
    public List<BudgetProjet> findByBudgetFaculteAnnee(int annee) {
        return budgetProjetService.findByBudgetFaculteAnnee(annee);
    }

    public AbstractConverter<BudgetProjet, BudgetProjetVo> getBudgetProjetConverter() {
        return budgetProjetConverter;
    }

    public void setBudgetProjetConverter(AbstractConverter<BudgetProjet, BudgetProjetVo> budgetProjetConverter) {
        this.budgetProjetConverter = budgetProjetConverter;
    }


   

    public BudgetProjetService getBudgetProjetService() {
        return budgetProjetService;
    }

    public void setBudgetProjetService(BudgetProjetService budgetProjetService) {
        this.budgetProjetService = budgetProjetService;
    }

}
