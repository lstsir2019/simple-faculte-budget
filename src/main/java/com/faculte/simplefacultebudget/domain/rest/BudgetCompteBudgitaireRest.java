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

    @GetMapping("/refEntite/{referenceEntiteAdministratif}/refsousProjet/{referenceSousProjet}/annee/{annee}")
    public List<BudgetCompteBudgitaireVo> findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(@PathVariable String referenceEntiteAdministratif, @PathVariable String referenceSousProjet, @PathVariable int annee) {
        List<BudgetCompteBudgitaire> bcbs = budgetCompteBudgitaireService.findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
        return budgetCompteBudgitaireConverter.toVo(bcbs);
    }

    @GetMapping("/annee/{annee}")
    public List<BudgetCompteBudgitaireVo> findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(@PathVariable("annee") int annee) {
        List<BudgetCompteBudgitaire> bcbs = budgetCompteBudgitaireService.findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(annee);
        return budgetCompteBudgitaireConverter.toVo(bcbs);
    }

    @GetMapping("/reference/{reference}/annee/{annee}")
    public List<BudgetCompteBudgitaireVo> findByBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(@PathVariable("reference") String referenceSousProjet, @PathVariable("annee") int annee) {
        List<BudgetCompteBudgitaire> myBcbs = budgetCompteBudgitaireService.findByBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(referenceSousProjet, annee);
        return budgetCompteBudgitaireConverter.toVo(myBcbs);
    }

    @DeleteMapping("/suppression/code/{code}/referenceEntiteAdmin/{referenceEntiteAdministratif}/referenceSousProjet/{referenceSousProjet}/annee/{annee}")
    public void removeBcb(@PathVariable String code, @PathVariable String referenceEntiteAdministratif, @PathVariable String referenceSousProjet, @PathVariable int annee) {
        budgetCompteBudgitaireService.removeBcb(code, referenceEntiteAdministratif, referenceSousProjet, annee);
    }

    @GetMapping("/anneeMin/{anneeMin}/anneeMax/{anneeMax}")
    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnneeLessThan(@PathVariable("anneeMin") Integer anneeMin, @PathVariable("anneeMax") Integer anneeMax) {
        return budgetCompteBudgitaireService.findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnneeLessThan(anneeMin, anneeMax);
    }

    public BudgetCompteBudgitaireService getBudgetCompteBudgitaireService() {
        return budgetCompteBudgitaireService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
    }
}
