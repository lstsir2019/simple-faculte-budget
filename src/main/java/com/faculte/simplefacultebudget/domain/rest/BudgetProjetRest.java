/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.common.pdf.GeneratePdf;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.res.converter.AbstractConverter;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetProjetVo;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author AMINE
 */
@RequestMapping("/budget-api/budget-projets")
@CrossOrigin(origins = "http://localhost:4200")

@RestController()
public class BudgetProjetRest {

    @Autowired
    private BudgetProjetService budgetProjetService;
    @Autowired
    private BudgetCompteBudgitaireService budgetCompteBudgitaireService;

    @Autowired
    @Qualifier("budgetProjetConverter")
    private AbstractConverter<BudgetProjet, BudgetProjetVo> budgetProjetConverter;

    @GetMapping("/referenceprojet/{referenceProjet}/annee/{annee}")
    public BudgetProjetVo findByReferenceProjetAndBudgetFaculteAnnee(@PathVariable String referenceProjet, @PathVariable int annee) {
        return budgetProjetConverter.toVo(budgetProjetService.findByReferenceProjetAndBudgetFaculteAnnee(referenceProjet, annee));
    }

    @GetMapping("/annee/{annee}")
    public List<BudgetProjetVo> findByBudgetFaculteAnnee(@PathVariable int annee) {
        return budgetProjetConverter.toVo(budgetProjetService.findByBudgetFaculteAnnee(annee));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        budgetProjetService.deleteById(id);
    }

    @GetMapping("/pdf/projet/id/{id}")
    public ResponseEntity<Object> reportFaculte(@PathVariable Long id) throws JRException, IOException {
        Map<String, Object> params = new HashMap<>();

        BudgetProjet budgetProjet = budgetProjetService.findById(id);

        List<BudgetCompteBudgitaire> bcbs = budgetCompteBudgitaireService.findDetaillesBudgetByProjet(id);

        params.put("annee", budgetProjet.getBudgetFaculte().getAnnee());
        params.put("coe", budgetProjet.getDetaillesBudget().getReliquatEstimatif());

        params.put("cor", budgetProjet.getDetaillesBudget().getReliquatReel());
        params.put("ep", budgetProjet.getDetaillesBudget().getEngagePaye());
        params.put("enp", budgetProjet.getDetaillesBudget().getEngageNonPaye());
      
        params.put("name", budgetProjet.getReferenceProjet());

        return GeneratePdf.generate("raport", params, bcbs, "/rapport/rapport.jasper");
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

    public BudgetCompteBudgitaireService getBudgetCompteBudgitaireService() {
        return budgetCompteBudgitaireService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
    }

}
