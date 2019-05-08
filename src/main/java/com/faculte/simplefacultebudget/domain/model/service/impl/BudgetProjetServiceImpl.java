/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
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
            double reliquatEstimative = 0;
            double reliquatReel = 0;

            for (BudgetProjet budgetProjet : budgetProjets) {

                budgetProjet.setBudgetFaculte(budgetFacultet);
                reliquatEstimative += budgetProjet.getDetaillesBudget().getCreditOuvertEstimatif();
                reliquatReel += budgetProjet.getDetaillesBudget().getCreditOuvertReel();
                budgetSousProjetService.createBudgetSousProjet(budgetProjet, budgetProjet.getBudgetSousProjets());

            }
            budgetFacultet.getDetaillesBudget().setReliquatEstimatif(reliquatEstimative);
            budgetFacultet.getDetaillesBudget().setReliquatReel(reliquatReel);

            return 1;
        }

    }

    private List<BudgetProjet> findItemsToRemove(List<BudgetProjet> budgetProjets, BudgetFaculte budgetFaculte) {
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
    public int payerSousProjet(BudgetProjet budgetProjet, double prix) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeBp(int annee, String referenceProjet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getAnticident(String reference, int annee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateBudgetProjet(BudgetProjet bpOld, BudgetProjet projet, double nvReliquatReelBudgetFaculte, double nvReliquatEstimatifBudgetFaculte) {

        double ReelConsomer = bpOld.getDetaillesBudget().getCreditOuvertReel() - bpOld.getDetaillesBudget().getReliquatReel();
        double EstimatifConsomer = bpOld.getDetaillesBudget().getCreditOuvertEstimatif() - bpOld.getDetaillesBudget().getReliquatEstimatif();
        if (nvReliquatReelBudgetFaculte < projet.getDetaillesBudget().getCreditOuvertReel() || nvReliquatEstimatifBudgetFaculte < projet.getDetaillesBudget().getCreditOuvertEstimatif()) {
            return -1;
        } else if (projet.getDetaillesBudget().getCreditOuvertReel() < ReelConsomer
                || projet.getDetaillesBudget().getCreditOuvertEstimatif() < EstimatifConsomer) {
            return -2;
        } else {
            bpOld.getDetaillesBudget().setCreditOuvertEstimatif(projet.getDetaillesBudget().getCreditOuvertEstimatif());
            bpOld.getDetaillesBudget().setCreditOuvertReel(projet.getDetaillesBudget().getCreditOuvertReel());
            bpOld.getDetaillesBudget().setEngagePaye(projet.getDetaillesBudget().getEngagePaye());
            bpOld.getDetaillesBudget().setEngageNonPaye(projet.getDetaillesBudget().getEngageNonPaye());
            bpOld.getDetaillesBudget().setReliquatReel(projet.getDetaillesBudget().getCreditOuvertReel() - EstimatifConsomer);
            bpOld.getDetaillesBudget().setReliquatEstimatif(projet.getDetaillesBudget().getCreditOuvertEstimatif() - ReelConsomer);
            budgetProjetDao.save(bpOld);
            return 1;
        }
    }

    @Override
    public boolean isEqual(BudgetProjet bp, BudgetProjet projet) {
        return bp.getDetaillesBudget().getCreditOuvertEstimatif() == projet.getDetaillesBudget().getCreditOuvertEstimatif()
                && bp.getDetaillesBudget().getCreditOuvertReel() == projet.getDetaillesBudget().getCreditOuvertReel()
                && bp.getDetaillesBudget().getEngagePaye() == projet.getDetaillesBudget().getEngagePaye()
                && bp.getDetaillesBudget().getEngageNonPaye() == projet.getDetaillesBudget().getEngageNonPaye();
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
