/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.faculte.simplefacultebudget.domain.model.service.BudgetProjetService;
import com.faculte.simplefacultebudget.domain.model.dao.BudgetProjetDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import java.util.ArrayList;

/**
 *
 * @author AMINE
 */
@Service
public class BudgetProjetServiceImpl implements BudgetProjetService {

    @Autowired
    private BudgetProjetService budgetProjetService;

    @Autowired
    private BudgetProjetDao budgetProjetDao;

    @Autowired
    private BudgetFaculteService budgetFaculteService;

    @Autowired
    private BudgetSousProjetService budgetSousProjetService;

    @Override
    public BudgetProjet findByReferenceProjetAndBudgetFaculteAnnee(String referenceProjet, int annee) {
        return budgetProjetDao.findByReferenceProjetAndBudgetFaculteAnnee(referenceProjet, annee);
    }

    @Override
    public List<BudgetProjet> findByBudgetFaculteAnnee(int annee) {
        return budgetProjetDao.findByBudgetFaculteAnnee(annee);
    }

    @Override
    public void save(BudgetProjet budgetProjet) {
        budgetProjetDao.save(budgetProjet);
    }

    @Override
    public int createBudgetProjet(BudgetFaculte budgetFacultet, List<BudgetProjet> budgetProjets) {
        if (budgetProjets == null) {
            return -1;
        } else {
            for (BudgetProjet budgetProjet : budgetProjets) {
                budgetProjet.setBudgetFaculte(budgetFacultet);
                budgetSousProjetService.createBudgetSousProjet(budgetProjet, budgetProjet.getBudgetSousProjets());

            }
            return 1;
        }
    }

    private List<BudgetProjet> findAndRemoveItems(List<BudgetProjet> budgetProjets, BudgetFaculte budgetFaculte) {
        List<BudgetProjet> list = findByBudgetFaculteAnnee(budgetFaculte.getAnnee());
        List<BudgetProjet> bpsToRemove = new ArrayList<>();

        for (BudgetProjet budgetProjet : list) {
            if (!budgetProjets.contains(budgetProjet)) {
                bpsToRemove.add(budgetProjet);
            }
        }
        budgetProjetDao.deleteAll(bpsToRemove);
        return bpsToRemove;
    }

    @Override
    public void deleteById(Long id) {
        budgetProjetDao.deleteById(id);
    }

    @Override
    public void calculeDetaillesBudgetProjet(BudgetFaculte budgetFaculte) {
        List<BudgetProjet> budgetProjets = findByBudgetFaculteAnnee(budgetFaculte.getAnnee());
        budgetProjets.forEach(bp -> {
            budgetSousProjetService.calculeDetaillesBudgetSousProjet(bp);
        });
        double reliquatEstimatif = 0D;
        double reliquatReel = 0D;
        double engageNonPaye = 0D;
        double engagePaye = 0D;
        for (BudgetProjet budgetProjet : budgetProjets) {
            reliquatEstimatif += budgetProjet.getDetaillesBudget().getCreditOuvertEstimatif();
            reliquatReel += budgetProjet.getDetaillesBudget().getCreditOuvertReel();
            engageNonPaye += budgetProjet.getDetaillesBudget().getEngageNonPaye();
            engagePaye += budgetProjet.getDetaillesBudget().getEngagePaye();
        }
        budgetFaculte.setBudgetProjets(budgetProjets);
        budgetFaculte.getDetaillesBudget().setReliquatReel(reliquatReel);
        budgetFaculte.getDetaillesBudget().setReliquatEstimatif(reliquatEstimatif);
        budgetFaculte.getDetaillesBudget().setEngageNonPaye(engageNonPaye);
        budgetFaculte.getDetaillesBudget().setEngagePaye(engagePaye);
    }

    @Override
    public BudgetProjet findById(Long id) {
        return budgetProjetDao.getOne(id);
    }

    public BudgetProjetService getBudgetProjetService() {
        return budgetProjetService;
    }

    public void setBudgetProjetService(BudgetProjetService budgetProjetService) {
        this.budgetProjetService = budgetProjetService;
    }

    public BudgetProjetDao getBudgetProjetDao() {
        return budgetProjetDao;
    }

    public void setBudgetProjetDao(BudgetProjetDao budgetProjetDao) {
        this.budgetProjetDao = budgetProjetDao;
    }

    public BudgetFaculteService getBudgetFaculteService() {
        return budgetFaculteService;
    }

    public void setBudgetFaculteService(BudgetFaculteService budgetFaculteService) {
        this.budgetFaculteService = budgetFaculteService;
    }

    public BudgetSousProjetService getBudgetSousProjetService() {
        return budgetSousProjetService;
    }

    public void setBudgetSousProjetService(BudgetSousProjetService budgetSousProjetService) {
        this.budgetSousProjetService = budgetSousProjetService;
    }

}
