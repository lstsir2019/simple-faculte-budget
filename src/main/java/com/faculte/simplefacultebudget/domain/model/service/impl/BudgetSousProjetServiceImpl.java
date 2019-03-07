/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.bean.BudgetEntiteAdministratif;
import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.model.dao.BudgetSousProjetDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetEntiteAdministratifService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class BudgetSousProjetServiceImpl implements BudgetSousProjetService {

    @Autowired
    private BudgetSousProjetService budgetSousProjetService;

    @Autowired
    private BudgetSousProjetDao budgetSousProjetDao;

    @Autowired
    private BudgetFaculteService budgetFaculteService;

    @Autowired
    private BudgetEntiteAdministratifService budgetEntiteAdministratifService;

    @Override
    public List<BudgetSousProjet> findByBudgetFaculteAnnee(int annee) {
        return budgetSousProjetDao.findByBudgetFaculteAnnee(annee);
    }

    @Override
    public int payerSousProjet(BudgetSousProjet budgetSousProjet, double prix) {
        BudgetSousProjet bsp = budgetSousProjetDao.getOne(budgetSousProjet.getId());
        if (bsp == null) {
            return -1;
        } else {
            double nvnonpaye = bsp.getDetaillesBudget().getEngageNonPaye() - prix;
            double nvpaye = bsp.getDetaillesBudget().getEngagePaye() + prix;

            double nvRelPayEst = bsp.getDetaillesBudget().getCreditOuvertEstimatif() - nvpaye;
            double nvRelPayRel = bsp.getDetaillesBudget().getCreditOuvertReel() - nvpaye;
            double nvRelNPyEst = bsp.getDetaillesBudget().getCreditOuvertEstimatif() - nvnonpaye;
            double nvRelNPyRel = bsp.getDetaillesBudget().getCreditOuvertReel() - nvnonpaye;

            bsp.getDetaillesBudget().setEngagePaye(nvpaye);
            bsp.getDetaillesBudget().setEngageNonPaye(nvnonpaye);
            bsp.getDetaillesBudget().setReliquatPayeEstimatif(nvRelPayEst);
            bsp.getDetaillesBudget().setReliquatPayereel(nvRelPayRel);
            bsp.getDetaillesBudget().setReliquatNonPayeEstimatif(nvRelNPyEst);
            bsp.getDetaillesBudget().setReliquatNonPayReel(nvRelNPyRel);

            budgetSousProjetDao.save(bsp);
            budgetFaculteService.payerBudgetFaculte(bsp.getBudgetFaculte().getAnnee(), prix);

            return 1;
        }
    }

    @Override
    public int createBudgetSousProjet(BudgetFaculte budgetFacultet, List<BudgetSousProjet> budgetSousProjets) {
        if (budgetSousProjets == null || budgetSousProjets.isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i < budgetSousProjets.size(); i++) {
                BudgetSousProjet sousProjet = budgetSousProjets.get(i);
                System.out.println(budgetFacultet.getDetaillesBudget().getCreditOuvertEstimatif());
                budgetFacultet.setDetaillesBudget(budgetFacultet.getDetaillesBudget());
                double restEstimatif = budgetFacultet.getDetaillesBudget().getReliquatEstimatif() - sousProjet.getDetaillesBudget().getCreditOuvertEstimatif();
                double restReel = budgetFacultet.getDetaillesBudget().getReliquatReel() - sousProjet.getDetaillesBudget().getCreditOuvertReel();
                if (restEstimatif < 0 || restReel < 0) {
                    break;
                } else {
                    BudgetSousProjet bsp = findByReferenceSousProjetAndBudgetFaculteAnnee(budgetSousProjets.get(i).getReferenceSousProjet(), budgetFacultet.getAnnee());
                    if (bsp != null) {
                        budgetFacultet.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
                        budgetFacultet.getDetaillesBudget().setReliquatReel(restReel);
                        budgetFaculteService.updateReliquatBf(budgetFacultet);
                        budgetEntiteAdministratifService.createBudgetEntiteAdministratif(bsp, sousProjet.getBudgetEntiteAdmins());

                    } else {
                        bsp = new BudgetSousProjet();
                        bsp.setDetaillesBudget(sousProjet.getDetaillesBudget());
                        bsp.setReferenceSousProjet(sousProjet.getReferenceSousProjet());
                        bsp.getDetaillesBudget().setReliquatEstimatif(sousProjet.getDetaillesBudget().getCreditOuvertEstimatif());
                        bsp.getDetaillesBudget().setCreditOuvertEstimatif(sousProjet.getDetaillesBudget().getCreditOuvertEstimatif());
                        bsp.getDetaillesBudget().setReliquatReel(sousProjet.getDetaillesBudget().getCreditOuvertReel());
                        bsp.getDetaillesBudget().setCreditOuvertReel(sousProjet.getDetaillesBudget().getCreditOuvertReel());
                        bsp.getDetaillesBudget().setEngagePaye(sousProjet.getDetaillesBudget().getEngagePaye());
                        bsp.getDetaillesBudget().setEngageNonPaye(sousProjet.getDetaillesBudget().getEngageNonPaye());
                        bsp.setBudgetFaculte(budgetFacultet);
                        budgetFacultet.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
                        budgetFacultet.getDetaillesBudget().setReliquatReel(restReel);
                        budgetFaculteService.updateReliquatBf(budgetFacultet);
                        budgetSousProjetDao.save(bsp);
                        budgetEntiteAdministratifService.createBudgetEntiteAdministratif(bsp, sousProjet.getBudgetEntiteAdmins());
                    }
                }
            }
            return 1;
        }
    }

    @Override
    public void updateReliquatBsp(BudgetSousProjet budgetSousProjet) {
        BudgetSousProjet bsp = budgetSousProjetDao.getOne(budgetSousProjet.getId());
        budgetSousProjetDao.save(bsp);
    }

    @Override

    public BudgetSousProjet findByReferenceSousProjetAndBudgetFaculteAnnee(String referenceSousProjet, int annee) {
        return budgetSousProjetDao.findByReferenceSousProjetAndBudgetFaculteAnnee(referenceSousProjet, annee);
    }

    @Override
    public void removeBsp(int annee, String referenceSousProjet) {
        double reliquatEstimatif = 0;
        double reliquatReel = 0;
        BudgetFaculte bf = budgetFaculteService.findByAnnee(annee);
        BudgetSousProjet bsp = findByReferenceSousProjetAndBudgetFaculteAnnee(referenceSousProjet, annee);
        for (BudgetEntiteAdministratif bea : bsp.getBudgetEntiteAdmins()) {
            reliquatEstimatif += bsp.getDetaillesBudget().getReliquatEstimatif();
            reliquatReel += bsp.getDetaillesBudget().getReliquatReel();
            for (BudgetCompteBudgitaire bcb : bea.getBudgeCompteBudgitaires()) {
                reliquatEstimatif += bcb.getDetaillesBudget().getCreditOuvertEstimatif();
                reliquatReel += bcb.getDetaillesBudget().getCreditOuvertReel();
            }
        }
        bf.setDetaillesBudget(bf.getDetaillesBudget());
        bsp.setDetaillesBudget(bsp.getDetaillesBudget());
        bf.getDetaillesBudget().setReliquatEstimatif(bf.getDetaillesBudget().getReliquatEstimatif() + bsp.getDetaillesBudget().getCreditOuvertEstimatif() + reliquatEstimatif);
        bf.getDetaillesBudget().setReliquatReel(bf.getDetaillesBudget().getReliquatReel() + bsp.getDetaillesBudget().getCreditOuvertReel() + reliquatReel);
        budgetFaculteService.updateReliquatBf(bf);
        budgetSousProjetDao.delete(bsp);
    }

    public BudgetEntiteAdministratifService getBudgetEntiteAdministratifService() {
        return budgetEntiteAdministratifService;
    }

    public void setBudgetEntiteAdministratifService(BudgetEntiteAdministratifService budgetEntiteAdministratifService) {
        this.budgetEntiteAdministratifService = budgetEntiteAdministratifService;
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

    public BudgetSousProjetDao getBudgetSousProjetDao() {
        return budgetSousProjetDao;
    }

    public void setBudgetSousProjetDao(BudgetSousProjetDao budgetSousProjetDao) {
        this.budgetSousProjetDao = budgetSousProjetDao;
    }
}
