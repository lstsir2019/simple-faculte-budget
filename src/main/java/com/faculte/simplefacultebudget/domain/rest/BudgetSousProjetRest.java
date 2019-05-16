/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest;

import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import com.faculte.simplefacultebudget.domain.res.converter.AbstractConverter;
import com.faculte.simplefacultebudget.domain.rest.proxy.SousProjetService;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetSousProjetVo;
import com.faculte.simplefacultebudget.domain.rest.vo.exchange.SousProjetVo;
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
@RequestMapping("/budget-api/budget-sous-projets")
@CrossOrigin(origins = "http://localhost:4200")

@RestController()
public class BudgetSousProjetRest {

    @Autowired
    private BudgetSousProjetService budgetSousProjetService;

    @Autowired
    @Qualifier("budgetSousProjetConverter")
    private AbstractConverter<BudgetSousProjet, BudgetSousProjetVo> budgetSousProjetConverter;

    @Autowired
    private SousProjetService sousProjetService;

    @GetMapping("/reference/{reference}/annee/{annee}")
    public BudgetSousProjetVo findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(@PathVariable("reference") String referenceSousProjet, @PathVariable("annee") int annee) {
        BudgetSousProjet myBsp = budgetSousProjetService.findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(referenceSousProjet, annee);
        return budgetSousProjetConverter.toVo(myBsp);
    }

    @GetMapping("/annee/{annee}")
    public List<BudgetSousProjetVo> findBybudgetProjetBudgetFaculteAnnee(@PathVariable int annee) {
        List<BudgetSousProjet> bsps = budgetSousProjetService.findBybudgetProjetBudgetFaculteAnnee(annee);
        return budgetSousProjetConverter.toVo(bsps);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        budgetSousProjetService.deleteById(id);
    }

    @GetMapping("/referenceprojet/{referenceSousProjet}/annee/{annee}")
    public List<BudgetSousProjetVo> findByBudgetProjetReferenceProjetAndBudgetProjetBudgetFaculteAnnee(@PathVariable("referenceSousProjet") String referenceProjet, @PathVariable("annee") int annee) {
        return budgetSousProjetConverter.toVo(budgetSousProjetService.findByBudgetProjetReferenceProjetAndBudgetProjetBudgetFaculteAnnee(referenceProjet, annee));
    }

    @GetMapping("/all/sousprojet")
    public List<SousProjetVo> findAllSousProjet() {
        return sousProjetService.findAllSousProjet();
    }

    @GetMapping("/anneeMin/{anneeMin}/anneeMax/{anneeMax}")
    public List<BudgetSousProjetVo> findByBudgetFaculteAnneeOrBudgetFaculteAnnee(@PathVariable("anneeMin") Integer anneeMin, @PathVariable("anneeMax") Integer anneeMax) {
        return budgetSousProjetConverter.toVo(budgetSousProjetService.findByBudgetFaculteAnneeOrBudgetFaculteAnnee(anneeMin, anneeMax));
    }

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
}
