/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest;

import com.faculte.simplefacultebudget.domain.bean.CompteBudgitaire;
import com.faculte.simplefacultebudget.domain.model.service.CompteBudgitaireService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/budget/compte_budgitaires")
@RestController()
public class CompteBudgitaireRest {

    @Autowired
    private CompteBudgitaireService compteBudgitaireService;

    @GetMapping("/code/{code}")
    public CompteBudgitaire findByCode(@PathVariable String code) {
        return compteBudgitaireService.findByCode(code);
    }

    @PostMapping("/")
    public void creerCompteBudgitaire(@RequestBody CompteBudgitaire compteBudgitaire) {
        compteBudgitaireService.compteBudgitaireSave(compteBudgitaire);
    }

    public CompteBudgitaireService getCompteBudgitaireService() {
        return compteBudgitaireService;
    }

    public void setCompteBudgitaireService(CompteBudgitaireService compteBudgitaireService) {
        this.compteBudgitaireService = compteBudgitaireService;
    }

}
