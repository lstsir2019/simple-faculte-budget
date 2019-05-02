/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest;

import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.model.service.BudgetEntiteAdministratifService;
import com.faculte.simplefacultebudget.domain.res.converter.AbstractConverter;
import com.faculte.simplefacultebudget.domain.rest.proxy.EntiteAdministratifService;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetEntiteAdministratifVo;
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

    @Autowired
    @Qualifier("budgetEntiteAdministratifConverter")
    private AbstractConverter<BudgetProjet, BudgetEntiteAdministratifVo> budgetEntiteAdministratifConverter;

    @Autowired
    private EntiteAdministratifService entiteAdministratifService;

    @GetMapping("/referenceEntiteAdmin/{referenceEntiteAdmin}/refSousProjet/{referenceSousProjet}/annee/{annee}")
    public BudgetEntiteAdministratifVo findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(@PathVariable("referenceEntiteAdmin") String referenceEntiteAdministratif, @PathVariable("referenceSousProjet") String referenceSousProjet, @PathVariable("annee") int annee) {
        BudgetProjet myBea = budgetEntiteAdministratifService.findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
        return budgetEntiteAdministratifConverter.toVo(myBea);
    }

    @GetMapping("/refSousProjet/{referenceSousProjet}/annee/{annee}")
    public List<BudgetEntiteAdministratifVo> findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(@PathVariable String referenceSousProjet, @PathVariable int annee) {
        List<BudgetProjet> myBeas = budgetEntiteAdministratifService.findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceSousProjet, annee);
        return budgetEntiteAdministratifConverter.toVo(myBeas);
    }

    @GetMapping("/annee/{annee}")
    public List<BudgetEntiteAdministratifVo> findByBudgetSousProjetBudgetFaculteAnnee(@PathVariable("annee") int annee) {
        List<BudgetProjet> myBeas = budgetEntiteAdministratifService.findByBudgetSousProjetBudgetFaculteAnnee(annee);
        return budgetEntiteAdministratifConverter.toVo(myBeas);
    }

    @DeleteMapping("/referenceEntiteAdmin/{referenceEntiteAdministratif}/referenceSousProjet/{referenceSousProjet}/annee/{annee}")
    public void removeBea(@PathVariable String referenceEntiteAdministratif, @PathVariable String referenceSousProjet, @PathVariable int annee) {
        budgetEntiteAdministratifService.removeBea(referenceEntiteAdministratif, referenceSousProjet, annee);
    }

    public AbstractConverter<BudgetProjet, BudgetEntiteAdministratifVo> getBudgetEntiteAdministratifConverter() {
        return budgetEntiteAdministratifConverter;
    }

    public void setBudgetEntiteAdministratifConverter(AbstractConverter<BudgetProjet, BudgetEntiteAdministratifVo> budgetEntiteAdministratifConverter) {
        this.budgetEntiteAdministratifConverter = budgetEntiteAdministratifConverter;
    }

    public BudgetEntiteAdministratifService getBudgetEntiteAdministratifService() {
        return budgetEntiteAdministratifService;
    }

    public void setBudgetEntiteAdministratifService(BudgetEntiteAdministratifService budgetEntiteAdministratifService) {
        this.budgetEntiteAdministratifService = budgetEntiteAdministratifService;
    }

    @GetMapping("/all/entiteadministratif")
    public List<EntiteAdministratifVo> findAllEntiteAdministratif() {
        return entiteAdministratifService.findAllEntiteAdministratif();
    }

    @GetMapping("/anneeMin/{anneeMin}/anneeMax/{anneeMax}")
    public List<BudgetEntiteAdministratifVo> findByBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetFaculteAnneeLessThan(@PathVariable("anneeMin") Integer anneeMin, @PathVariable("anneeMax") Integer anneeMax) {
        return budgetEntiteAdministratifConverter.toVo(budgetEntiteAdministratifService.findByBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetFaculteAnneeLessThan(anneeMin, anneeMax));
    }

}
