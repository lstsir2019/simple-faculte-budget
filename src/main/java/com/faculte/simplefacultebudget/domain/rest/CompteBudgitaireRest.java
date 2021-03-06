/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest;

import com.faculte.simplefacultebudget.domain.bean.CompteBudgitaire;
import com.faculte.simplefacultebudget.domain.model.service.CompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.res.converter.AbstractConverter;
import com.faculte.simplefacultebudget.domain.rest.vo.CompteBudgitaireVo;
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
@RequestMapping("/budget-api/compte-budgitaires")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CompteBudgitaireRest {

    @Autowired
    private CompteBudgitaireService compteBudgitaireService;
    @Autowired
    @Qualifier("compteBudgitaireConverter")
    private AbstractConverter<CompteBudgitaire, CompteBudgitaireVo> compteBudgitaireConverter;

    @GetMapping("/code/{code}")
    public CompteBudgitaire findByCode(@PathVariable String code) {
        return compteBudgitaireService.findByCode(code);
    }

    @PostMapping("/")
    public CompteBudgitaireVo creerCompteBudgitaire(@RequestBody CompteBudgitaireVo compteBudgitaireVo) {
        CompteBudgitaire compteBudgitaire = compteBudgitaireConverter.toItem(compteBudgitaireVo);
        compteBudgitaire = compteBudgitaireService.compteBudgitaireSave(compteBudgitaire);
        return compteBudgitaireConverter.toVo(compteBudgitaire);
    }

    @DeleteMapping("/code/{code}")
    public int removeByCode(@PathVariable String code) {
        return compteBudgitaireService.removeByCode(code);
    }

    @GetMapping("/")
    public List<CompteBudgitaireVo> findAll() {
        return compteBudgitaireConverter.toVo(compteBudgitaireService.findAll());
    }

    public CompteBudgitaireService getCompteBudgitaireService() {
        return compteBudgitaireService;
    }

    public void setCompteBudgitaireService(CompteBudgitaireService compteBudgitaireService) {
        this.compteBudgitaireService = compteBudgitaireService;
    }

    public AbstractConverter<CompteBudgitaire, CompteBudgitaireVo> getCompteBudgitaireConverter() {
        return compteBudgitaireConverter;
    }

    public void setCompteBudgitaireConverter(AbstractConverter<CompteBudgitaire, CompteBudgitaireVo> compteBudgitaireConverter) {
        this.compteBudgitaireConverter = compteBudgitaireConverter;
    }



}
