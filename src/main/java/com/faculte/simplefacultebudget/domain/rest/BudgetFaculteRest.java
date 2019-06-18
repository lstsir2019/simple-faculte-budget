/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.common.pdf.GeneratePdf;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import com.faculte.simplefacultebudget.domain.res.converter.AbstractConverter;
import com.faculte.simplefacultebudget.domain.res.converter.BudgetFaculteConverter;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetFaculteVo;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CrossOrigin(origins = "http://localhost:4200")
 *
 * @author AMINE
 */
@RequestMapping("/budget-api/budget-facultes")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BudgetFaculteRest {

    @Autowired
    private BudgetFaculteService budgetFaculteService;
    @Autowired
    private BudgetCompteBudgitaireService budgetCompteBudgitaireService;

    @Autowired
    @Qualifier("budgetFaculteConverter")
    private AbstractConverter<BudgetFaculte, BudgetFaculteVo> budgetFaculteConverter;

    public BudgetCompteBudgitaireService getBudgetCompteBudgitaireService() {
        return budgetCompteBudgitaireService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
    }

    public BudgetFaculteService getBudgetFaculteService() {
        return budgetFaculteService;
    }

    public void setBudgetFaculteService(BudgetFaculteService budgetFaculteService) {
        this.budgetFaculteService = budgetFaculteService;
    }

    @GetMapping("/")
    public List<BudgetFaculteVo> findAll() {
        return budgetFaculteConverter.toVo(budgetFaculteService.findAll());
    }

    @GetMapping("/annee/{annee}")
    public BudgetFaculteVo findByAnnee(@PathVariable int annee) {
        BudgetFaculte myBf = budgetFaculteService.findByAnnee(annee);
        return new BudgetFaculteConverter().toVo(myBf);
    }

    @PostMapping("/")
    public int creerBudgetFaculte(@RequestBody BudgetFaculteVo budgetFaculteVo) {
        BudgetFaculte myBudgetFaculte = budgetFaculteConverter.toItem(budgetFaculteVo);
        return budgetFaculteService.creerBudgetFaculte(myBudgetFaculte);
    }

    @DeleteMapping("/annee/{annee}")
    public void remove(@PathVariable int annee) {
        budgetFaculteService.remove(annee);
    }

//    @PutMapping("/annee/{annee}")
//    public int updateBudgetFaculte(@PathVariable int annee, @RequestBody BudgetFaculteVo budgetFaculte) {
//        return budgetFaculteService.updateBudgetFaculte(annee, budgetFaculteConverter.toItem(budgetFaculte));
//    }
    @PostMapping("/anneemin/anneemax/")
    public List<BudgetFaculteVo> findByAnneeMinAndAnneeMax(Integer anneeMin, Integer anneeMax) {
        return budgetFaculteConverter.toVo(budgetFaculteService.findByAnneeMinAndAnneeMax(anneeMin, anneeMax));
    }

    @GetMapping("/pdf/faculte/annee/{annee}")
    public ResponseEntity<Object> reportFaculte(@PathVariable int annee) throws JRException, IOException {
        Map<String, Object> params = new HashMap<>();

        BudgetFaculte bf = budgetFaculteService.findByAnnee(annee);
        List<BudgetCompteBudgitaire> bcbs = budgetCompteBudgitaireService.findDetaillesBudgetByAnne(annee);

        params.put("annee", bf.getAnnee());
        params.put("coe", bf.getDetaillesBudget().getReliquatEstimatif());
        params.put("cor", bf.getDetaillesBudget().getReliquatReel());
        params.put("ep", bf.getDetaillesBudget().getEngagePaye());
        params.put("enp", bf.getDetaillesBudget().getEngageNonPaye());

        params.put("name", "Situation Global");

        return GeneratePdf.generate("raport", params, bcbs, "/rapport/rapport.jasper");
    }

}
