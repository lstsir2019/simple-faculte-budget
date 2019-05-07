/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.CompteBudgitaire;
import com.faculte.simplefacultebudget.domain.model.dao.CompteBudgitaireDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import com.faculte.simplefacultebudget.domain.model.service.CompteBudgitaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class CompteBudgitaireServiceImpl implements CompteBudgitaireService {

    @Autowired
    private CompteBudgitaireDao compteBudgitaireDao;

    @Autowired
    private BudgetFaculteService budgetFaculteService;

    @Autowired
    BudgetCompteBudgitaireService budgetCompteBudgitaireService;

    @Override
    public CompteBudgitaire findByCodeAndAnnee(String code, int anneee) {
        return compteBudgitaireDao.findByCodeAndAnnee(code, anneee);
    }

    @Override
    public int compteBudgitaireSave(CompteBudgitaire compteBudgitaire) {
        if (null == compteBudgitaire) {
            return -1;
        } else {
            CompteBudgitaire cb = findByCodeAndAnnee(compteBudgitaire.getCode(), compteBudgitaire.getAnnee());
            if (cb != null) {
                return -2;
            } else {
                compteBudgitaireDao.save(compteBudgitaire);
                return 1;
            }
        }
    }

    @Override
    public int payerCB(String code, int annee) {
        CompteBudgitaire cb = findByCodeAndAnnee(code, annee);
        if (cb == null) {
            return -1;
        } else {
            budgetCompteBudgitaireService.payerBCB(code);
            return 1;
        }
    }

    public BudgetCompteBudgitaireService getBudgetCompteBudgitaireService() {
        return budgetCompteBudgitaireService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
    }

    public void setCompteBudgitaireDao(CompteBudgitaireDao compteBudgitaireDao) {
        this.compteBudgitaireDao = compteBudgitaireDao;
    }

}
