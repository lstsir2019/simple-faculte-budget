/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.CompteBudgitaire;
import com.faculte.simplefacultebudget.domain.model.dao.CompteBudgitaireDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
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
    BudgetCompteBudgitaireService budgetCompteBudgitaireService;

    @Override
    public CompteBudgitaire findByCode(String code) {
        return compteBudgitaireDao.findByCode(code);
    }

    @Override
    public void creerCompteBudgitaire(CompteBudgitaire compteBudgitaire) {
        compteBudgitaireDao.save(compteBudgitaire);
    }

    @Override
    public int payerCB(String code) {
        CompteBudgitaire cb = findByCode(code);
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
