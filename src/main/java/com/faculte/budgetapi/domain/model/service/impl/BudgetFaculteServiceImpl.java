/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.model.service.impl;


import com.faculte.budgetapi.domain.bean.BudgetFaculte;
import com.faculte.budgetapi.domain.model.dao.BudgetFaculteDao;
import com.faculte.budgetapi.domain.model.service.BudgetFaculteService;
import com.faculte.budgetapi.domain.model.service.BudgetSousProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class BudgetFaculteServiceImpl implements BudgetFaculteService {

    @Autowired
    private BudgetFaculteDao budgetFaculteDao;
    @Autowired
    private BudgetSousProjetService budgetSousProjetService;

    public BudgetFaculteDao getBudgetFaculteDao() {
        return budgetFaculteDao;
    }

    public BudgetSousProjetService getBudgetSousProjetService() {
        return budgetSousProjetService;
    }

    public void setBudgetSousProjetService(BudgetSousProjetService budgetSousProjetService) {
        this.budgetSousProjetService = budgetSousProjetService;
    }

    public void setBudgetFaculteDao(BudgetFaculteDao budgetFaculteDao) {
        this.budgetFaculteDao = budgetFaculteDao;
    }

    @Override
    public BudgetFaculte findByAnnee(int annee) {
        return budgetFaculteDao.findByAnnee(annee);
    }


    @Override
    public int creerBudgetFaculte(BudgetFaculte budgetFaculte) {
        BudgetFaculte bf = findByAnnee(budgetFaculte.getAnnee());
        if (bf != null) {
            return -1;
        } else {
            bf = new BudgetFaculte();
            bf.setDetaillesBudget(budgetFaculte.getDetaillesBudget());
            bf.setAnnee(budgetFaculte.getAnnee());
            bf.getDetaillesBudget().setReliquatEstimatif(budgetFaculte.getDetaillesBudget().getCreditOuvertEstimatif());
            bf.getDetaillesBudget().setCreditOuvertEstimatif(budgetFaculte.getDetaillesBudget().getCreditOuvertEstimatif());
            bf.getDetaillesBudget().setReliquatReel(budgetFaculte.getDetaillesBudget().getCreditOuvertReel());
            bf.getDetaillesBudget().setCreditOuvertReel(budgetFaculte.getDetaillesBudget().getCreditOuvertReel());
            bf.getDetaillesBudget().setEngagePaye(budgetFaculte.getDetaillesBudget().getEngagePaye());
            bf.getDetaillesBudget().setEngageNonPaye(budgetFaculte.getDetaillesBudget().getEngageNonPaye());
            budgetFaculteDao.save(bf);
            return 1;
        }
    }

    @Override
    public void updateReliquatBf(BudgetFaculte budgetFaculte) {
        budgetFaculteDao.save(budgetFaculte);
    }

    @Override
    public BudgetFaculte findById(Long id) {
        return budgetFaculteDao.getOne(id);
    }

    @Override
    public void deleteBudgetFaculte(BudgetFaculte budgetFaculte) {
        budgetFaculteDao.delete(budgetFaculte);
    }

}
