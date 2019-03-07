/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetEntiteAdministratif;
import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.model.dao.BudgetEntiteAdministratifDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
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
public class BudgetEntiteAdministratifServiceImpl implements BudgetEntiteAdministratifService {

    @Autowired
    private BudgetEntiteAdministratifDao budgetEntiteAdministratifDao;

    @Autowired
    private BudgetCompteBudgitaireService budgetCompteBudgitaireService;

    @Autowired
    private BudgetSousProjetService budgetSousProjetService;

    @Autowired
    private BudgetFaculteService budgetFaculteService;

    public BudgetSousProjetService getBudgetSousProjetService() {
        return budgetSousProjetService;
    }

    public void setBudgetSousProjetService(BudgetSousProjetService budgetSousProjetService) {
        this.budgetSousProjetService = budgetSousProjetService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
    }

    public void setBudgetEntiteAdministratifDao(BudgetEntiteAdministratifDao budgetEntiteAdministratifDao) {
        this.budgetEntiteAdministratifDao = budgetEntiteAdministratifDao;
    }

    @Override
    public BudgetEntiteAdministratif findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif, String referenceSousProjet, int annee) {
        return budgetEntiteAdministratifDao.findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
    }

    @Override
    public List<BudgetEntiteAdministratif> findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceSousProjet, int annee) {
        return budgetEntiteAdministratifDao.findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceSousProjet, annee);
    }

    @Override
    public void updateReliquatBea(BudgetEntiteAdministratif budgetEntiteAdministratif) {
        BudgetEntiteAdministratif bcb = findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(budgetEntiteAdministratif.getReferenceEntiteAdministratif(), budgetEntiteAdministratif.getBudgetSousProjet().getReferenceSousProjet(), budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
        budgetEntiteAdministratifDao.save(bcb);
    }

    @Override
    public List<BudgetEntiteAdministratif> findByBudgetSousProjetBudgetFaculteAnnee(int annee) {
        return budgetEntiteAdministratifDao.findByBudgetSousProjetBudgetFaculteAnnee(annee);
    }

    @Override
    public int payerBudgetEA(BudgetEntiteAdministratif budgetEntiteAdministratif, double montant) {
        BudgetEntiteAdministratif bea = budgetEntiteAdministratifDao.getOne(budgetEntiteAdministratif.getId());
        if (bea == null) {
            return -1;
        } else {
            double nvnonpaye = bea.getDetaillesBudget().getEngageNonPaye() - montant;
            double nvpaye = bea.getDetaillesBudget().getEngagePaye() + montant;

            double nvRelPayEst = bea.getDetaillesBudget().getCreditOuvertEstimatif() - nvpaye;
            double nvRelPayRel = bea.getDetaillesBudget().getCreditOuvertReel() - nvpaye;
            double nvRelNPyEst = bea.getDetaillesBudget().getCreditOuvertEstimatif() - nvnonpaye;
            double nvRelNPyRel = bea.getDetaillesBudget().getCreditOuvertReel() - nvnonpaye;

            bea.getDetaillesBudget().setEngagePaye(nvpaye);
            bea.getDetaillesBudget().setEngageNonPaye(nvnonpaye);
            bea.getDetaillesBudget().setReliquatPayeEstimatif(nvRelPayEst);
            bea.getDetaillesBudget().setReliquatPayereel(nvRelPayRel);
            bea.getDetaillesBudget().setReliquatNonPayeEstimatif(nvRelNPyEst);
            bea.getDetaillesBudget().setReliquatNonPayReel(nvRelNPyRel);

            budgetEntiteAdministratifDao.save(bea);
            budgetSousProjetService.payerSousProjet(bea.getBudgetSousProjet(), montant);

            return 1;
        }
    }

    @Override
    public int creerBudgetEntiteAdministratif(BudgetEntiteAdministratif budgetEntiteAdministratif) {
        BudgetFaculte bf = budgetFaculteService.findByAnnee(budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
        BudgetSousProjet bsp = budgetSousProjetService.findByReferenceSousProjetAndBudgetFaculteAnnee(budgetEntiteAdministratif.getBudgetSousProjet().getReferenceSousProjet(), budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
        BudgetEntiteAdministratif bea = findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(budgetEntiteAdministratif.getReferenceEntiteAdministratif(), budgetEntiteAdministratif.getBudgetSousProjet().getReferenceSousProjet(), budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
        if (bf == null) {
            return -1;
        } else if (bea == null) {
            return -2;
        } else if (bea != null) {
            return -3;
        } else {
            bea.setDetaillesBudget(bea.getDetaillesBudget());
            double restEstimatif = bea.getDetaillesBudget().getReliquatEstimatif() - budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif();
            double restReel = bea.getDetaillesBudget().getReliquatReel() - budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertReel();
            if (restEstimatif < 0) {
                return -4;
            } else if (restReel < 0) {
                return -5;
            } else {
                bea = new BudgetEntiteAdministratif();
                bea.setDetaillesBudget(budgetEntiteAdministratif.getDetaillesBudget());
                bea.setReferenceEntiteAdministratif(budgetEntiteAdministratif.getReferenceEntiteAdministratif());
                bea.getDetaillesBudget().setReliquatEstimatif(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
                bea.getDetaillesBudget().setCreditOuvertEstimatif(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
                bea.getDetaillesBudget().setReliquatReel(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
                bea.getDetaillesBudget().setCreditOuvertReel(budgetEntiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
                bea.getDetaillesBudget().setEngagePaye(budgetEntiteAdministratif.getDetaillesBudget().getEngagePaye());
                bea.getDetaillesBudget().setEngageNonPaye(budgetEntiteAdministratif.getDetaillesBudget().getEngageNonPaye());
                bea.setBudgetSousProjet(bsp);
                bea.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
                bea.getDetaillesBudget().setReliquatReel(restReel);
                budgetSousProjetService.updateReliquatBsp(bsp);
                budgetEntiteAdministratifDao.save(bea);
                return 1;
            }
        }
    }

    @Override
    public int createBudgetEntiteAdministratif(BudgetSousProjet budgetSousProjet, List<BudgetEntiteAdministratif> budgetEntiteAdministratifs) {
        if (budgetEntiteAdministratifs == null || budgetEntiteAdministratifs.isEmpty()) {
            return -1;
        } else {
            for (int j = 0; j < budgetEntiteAdministratifs.size(); j++) {
                BudgetEntiteAdministratif entiteAdministratif=budgetEntiteAdministratifs.get(j);
                budgetSousProjet.setDetaillesBudget(budgetSousProjet.getDetaillesBudget());
                double restEstimatif = budgetSousProjet.getDetaillesBudget().getReliquatEstimatif() - entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif();
                double restReel = budgetSousProjet.getDetaillesBudget().getReliquatReel() - entiteAdministratif.getDetaillesBudget().getCreditOuvertReel();
                if (restEstimatif < 0 || restReel < 0) {
                    break;
                } else {
                    BudgetEntiteAdministratif bea = findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(entiteAdministratif.getReferenceEntiteAdministratif(), budgetSousProjet.getReferenceSousProjet(), budgetSousProjet.getBudgetFaculte().getAnnee());
                    if (bea != null) {
                        budgetSousProjet.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
                        budgetSousProjet.getDetaillesBudget().setReliquatReel(restReel);
                        budgetSousProjetService.updateReliquatBsp(budgetSousProjet);
                        budgetCompteBudgitaireService.createBudgetCompteBudgitaire(bea, entiteAdministratif.getBudgeCompteBudgitaires());
                    } else {
                        bea=new BudgetEntiteAdministratif();
                        bea.setDetaillesBudget(entiteAdministratif.getDetaillesBudget());
                        bea.setReferenceEntiteAdministratif(entiteAdministratif.getReferenceEntiteAdministratif());
                        bea.getDetaillesBudget().setReliquatEstimatif(entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
                        bea.getDetaillesBudget().setCreditOuvertEstimatif(entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
                        bea.getDetaillesBudget().setReliquatReel(entiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
                        bea.getDetaillesBudget().setCreditOuvertReel(entiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
                        bea.getDetaillesBudget().setEngagePaye(entiteAdministratif.getDetaillesBudget().getEngagePaye());
                        bea.getDetaillesBudget().setEngageNonPaye(entiteAdministratif.getDetaillesBudget().getEngageNonPaye());
                        bea.setBudgetSousProjet(budgetSousProjet);
                        budgetSousProjet.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
                        budgetSousProjet.getDetaillesBudget().setReliquatReel(restReel);
                        budgetSousProjetService.updateReliquatBsp(budgetSousProjet);
                        budgetEntiteAdministratifDao.save(bea);
                        budgetCompteBudgitaireService.createBudgetCompteBudgitaire(bea, entiteAdministratif.getBudgeCompteBudgitaires());
                    }
                }
            }
            return 1;
        }
    }

    @Override
    public void removeBea(String referenceEntiteAdministratif, String referenceSousProjet, int annee) {
        BudgetEntiteAdministratif bea = findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
        BudgetSousProjet bsp =  budgetSousProjetService.findByReferenceSousProjetAndBudgetFaculteAnnee(referenceSousProjet, annee);
        bsp.setDetaillesBudget(bsp.getDetaillesBudget());
        bea.setDetaillesBudget(bea.getDetaillesBudget());
        bsp.getDetaillesBudget().setReliquatEstimatif(bsp.getDetaillesBudget().getReliquatEstimatif()+bea.getDetaillesBudget().getCreditOuvertEstimatif());
        bsp.getDetaillesBudget().setReliquatReel(bsp.getDetaillesBudget().getReliquatReel()+bea.getDetaillesBudget().getCreditOuvertReel());
        budgetSousProjetService.updateReliquatBsp(bsp);
        budgetEntiteAdministratifDao.delete(bea);
    }
}
