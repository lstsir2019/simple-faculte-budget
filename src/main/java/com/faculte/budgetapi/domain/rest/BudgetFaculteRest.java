/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.rest;


import com.faculte.budgetapi.domain.bean.BudgetFaculte;
import com.faculte.budgetapi.domain.model.service.BudgetFaculteService;
import com.faculte.budgetapi.domain.res.converter.BudgetFaculteConverter;
import com.faculte.budgetapi.domain.rest.vo.BudgetFaculteVo;
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
 *@CrossOrigin(origins = "http://localhost:4200")

 * @author AMINE
 */
@RequestMapping("/budget_api/budget_api_facultes")
@CrossOrigin(origins = "http://localhost:4200")
@RestController()
public class BudgetFaculteRest {
    @Autowired 
    private BudgetFaculteService budgetFaculteService;

    public BudgetFaculteService getBudgetFaculteService() {
        return budgetFaculteService;
    }

    public void setBudgetFaculteService(BudgetFaculteService budgetFaculteService) {
        this.budgetFaculteService = budgetFaculteService;
    }
    @GetMapping("/annee/{annee}")
    public BudgetFaculte findByAnnee(@PathVariable int annee) {
        return budgetFaculteService.findByAnnee(annee);
    }
    @PostMapping("/")
    public int creerBudgetFaculte(@RequestBody BudgetFaculteVo budgetFaculteVo) {
        BudgetFaculteConverter budgetFaculteConverter = new BudgetFaculteConverter();
        BudgetFaculte myBudgetFaculte = budgetFaculteConverter.toItem(budgetFaculteVo);
        BudgetFaculteVo budgetFaculte = budgetFaculteConverter.toVo(myBudgetFaculte);
        return budgetFaculteService.creerBudgetFaculte(myBudgetFaculte);

    }
   
    
    
}
