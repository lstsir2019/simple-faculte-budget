/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.common.pdf.GeneratePdf;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import com.faculte.simplefacultebudget.domain.res.converter.AbstractConverter;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetSousProjetVo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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
    private BudgetCompteBudgitaireService budgetCompteBudgitaireService;

    @Autowired
    @Qualifier("budgetSousProjetConverter")
    private AbstractConverter<BudgetSousProjet, BudgetSousProjetVo> budgetSousProjetConverter;

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

    @GetMapping("/anneeMin/{anneeMin}/anneeMax/{anneeMax}")
    public List<BudgetSousProjetVo> findByBudgetFaculteAnneeOrBudgetFaculteAnnee(@PathVariable("anneeMin") Integer anneeMin, @PathVariable("anneeMax") Integer anneeMax) {
        return budgetSousProjetConverter.toVo(budgetSousProjetService.findByBudgetFaculteAnneeOrBudgetFaculteAnnee(anneeMin, anneeMax));
    }

    @GetMapping("/pdf/sous-projet/referenceProjet/{referenceProjet}/referenceSousProjet/{referenceSousProjet}/annee/{annee}")
    public ResponseEntity<Object> reportFaculte(@PathVariable String referenceProjet, @PathVariable String referenceSousProjet, @PathVariable int annee) throws JRException, IOException {
        Map<String, Object> params = new HashMap<>();

        BudgetSousProjet budgetSousProjet = budgetSousProjetService.findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(referenceSousProjet, annee);

        List<BudgetCompteBudgitaire> bcbs = budgetCompteBudgitaireService.findByBudgetSousProjetBudgetProjetReferenceProjetAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(referenceProjet, referenceSousProjet, annee);

        params.put("annee", budgetSousProjet.getBudgetProjet().getBudgetFaculte().getAnnee());
        params.put("coe", budgetSousProjet.getDetaillesBudget().getReliquatEstimatif());
        params.put("cor", budgetSousProjet.getDetaillesBudget().getReliquatReel());
        params.put("ep", budgetSousProjet.getDetaillesBudget().getEngagePaye());
        params.put("enp", budgetSousProjet.getDetaillesBudget().getEngageNonPaye());
        params.put("name", budgetSousProjet.getReferenceSousProjet());

        return GeneratePdf.generate("raport", params, bcbs, "/rapport/rapport.jasper");
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

    public BudgetCompteBudgitaireService getBudgetCompteBudgitaireService() {
        return budgetCompteBudgitaireService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
    }

}
