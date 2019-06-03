/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.CompteBudgitaire;
import com.faculte.simplefacultebudget.domain.model.dao.CompteBudgitaireDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import com.faculte.simplefacultebudget.domain.model.service.CompteBudgitaireService;
import java.util.List;
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
    public CompteBudgitaire findByCode(String code) {
        return compteBudgitaireDao.findByCode(code);
    }

    @Override
    public CompteBudgitaire compteBudgitaireSave(CompteBudgitaire compteBudgitaire) {
        if (null == compteBudgitaire) {
            return null;
        } else {
            CompteBudgitaire cb = findByCode(compteBudgitaire.getCode());
            if (cb != null) {
                return cb;
            } else {
                cb = compteBudgitaireDao.save(compteBudgitaire);
                return cb;
            }
        }
    }

    @Override
    public List<CompteBudgitaire> findAll() {
        return compteBudgitaireDao.findAll();
    }

    @Override
    public int removeByCode(String code) {
        CompteBudgitaire compteBudgitaire = findByCode(code);
        if (compteBudgitaire == null) {
            return -1;
        } else {
            Long number = budgetCompteBudgitaireService.countByCompteBudgitaireCode(code);
            if (number != null && number > 0) {
                return -2;
            } else {
                compteBudgitaireDao.delete(compteBudgitaire);
                return 1;
            }
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
