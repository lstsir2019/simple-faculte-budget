/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetEntiteAdministratif;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.model.dao.BudgetEntiteAdministratifDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetEntiteAdministratifService;
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

    public BudgetSousProjetService getBudgetSousProjetService() {
        return budgetSousProjetService;
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
    public List<BudgetEntiteAdministratif> findByBudgetSousProjetBudgetFaculteAnnee(int annee) {
        return budgetEntiteAdministratifDao.findByBudgetSousProjetBudgetFaculteAnnee(annee);
    }

    @Override
    public void save(BudgetEntiteAdministratif budgetEntiteAdministratif) {
        budgetEntiteAdministratifDao.save(budgetEntiteAdministratif);
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
    public int updateBudgetEntiteAdministratif(BudgetEntiteAdministratif beaOld, BudgetEntiteAdministratif entiteAdministratif, double nvReliquatReelBudgetSousProjet, double nvReliquatEstimatifBudgetSousProjet) {
        double ReelConsomer = beaOld.getDetaillesBudget().getCreditOuvertReel() - beaOld.getDetaillesBudget().getReliquatReel();
        double EstimatifConsomer = beaOld.getDetaillesBudget().getCreditOuvertEstimatif() - beaOld.getDetaillesBudget().getReliquatEstimatif();
        if (nvReliquatReelBudgetSousProjet < entiteAdministratif.getDetaillesBudget().getCreditOuvertReel() || nvReliquatEstimatifBudgetSousProjet < entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif()) {
            return -1;
        } else if (entiteAdministratif.getDetaillesBudget().getCreditOuvertReel() < ReelConsomer
                || entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif() < EstimatifConsomer) {
            return -1;
        } else {
            beaOld.getDetaillesBudget().setReliquatEstimatif(entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
            beaOld.getDetaillesBudget().setCreditOuvertEstimatif(entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
            beaOld.getDetaillesBudget().setReliquatReel(entiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
            beaOld.getDetaillesBudget().setCreditOuvertReel(entiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
            beaOld.getDetaillesBudget().setEngagePaye(entiteAdministratif.getDetaillesBudget().getEngagePaye());
            beaOld.getDetaillesBudget().setEngageNonPaye(entiteAdministratif.getDetaillesBudget().getEngageNonPaye());
            budgetEntiteAdministratifDao.save(beaOld);
            return 1;
        }
    }

    @Override
    public boolean isEqual(BudgetEntiteAdministratif bea, BudgetEntiteAdministratif entiteAdministratif) {
        return bea.getDetaillesBudget().getCreditOuvertEstimatif() == entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif()
                && bea.getDetaillesBudget().getCreditOuvertReel() == entiteAdministratif.getDetaillesBudget().getCreditOuvertReel()
                && bea.getDetaillesBudget().getEngagePaye() == entiteAdministratif.getDetaillesBudget().getEngagePaye()
                && bea.getDetaillesBudget().getEngageNonPaye() == entiteAdministratif.getDetaillesBudget().getEngageNonPaye();
    }

    @Override
    public int createBudgetEntiteAdministratif(BudgetSousProjet budgetSousProjet, List<BudgetEntiteAdministratif> budgetEntiteAdministratifs) {
        if (budgetEntiteAdministratifs == null || budgetEntiteAdministratifs.isEmpty()) {
            return -1;
        } else {
            for (int j = 0; j < budgetEntiteAdministratifs.size(); j++) {
                BudgetEntiteAdministratif entiteAdministratif = budgetEntiteAdministratifs.get(j);
                budgetSousProjet.setDetaillesBudget(budgetSousProjet.getDetaillesBudget());
                double restEstimatif = budgetSousProjet.getDetaillesBudget().getReliquatEstimatif() - entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif();
                double restReel = budgetSousProjet.getDetaillesBudget().getReliquatReel() - entiteAdministratif.getDetaillesBudget().getCreditOuvertReel();
                BudgetEntiteAdministratif beaOld = findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(entiteAdministratif.getReferenceEntiteAdministratif(), budgetSousProjet.getReferenceSousProjet(), budgetSousProjet.getBudgetFaculte().getAnnee());
                if (beaOld != null) {
                    double nvReliquatReelBudgetSousProjet = beaOld.getDetaillesBudget().getCreditOuvertReel() + budgetSousProjet.getDetaillesBudget().getReliquatReel();
                    double nvReliquatEstimatifBudgetSousProjet = beaOld.getDetaillesBudget().getCreditOuvertEstimatif() + budgetSousProjet.getDetaillesBudget().getReliquatEstimatif();
                    if (!isEqual(beaOld, entiteAdministratif) && updateBudgetEntiteAdministratif(beaOld, entiteAdministratif, nvReliquatReelBudgetSousProjet, nvReliquatEstimatifBudgetSousProjet) == 1) {
                        budgetSousProjet.getDetaillesBudget().setReliquatEstimatif(nvReliquatEstimatifBudgetSousProjet - entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
                        budgetSousProjet.getDetaillesBudget().setReliquatReel(nvReliquatReelBudgetSousProjet - entiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
                        budgetSousProjetService.save(budgetSousProjet);
                    }
                    budgetCompteBudgitaireService.createBudgetCompteBudgitaire(beaOld, entiteAdministratif.getBudgeCompteBudgitaires());
                } else if (restEstimatif < 0 || restReel < 0) {
                    break;
                } else {
                    beaOld = new BudgetEntiteAdministratif();
                    beaOld.setDetaillesBudget(entiteAdministratif.getDetaillesBudget());
                    beaOld.getDetaillesBudget().setAntecedent(getAnticident(entiteAdministratif.getReferenceEntiteAdministratif(), budgetSousProjet.getReferenceSousProjet(), budgetSousProjet.getBudgetFaculte().getAnnee()));
                    beaOld.setReferenceEntiteAdministratif(entiteAdministratif.getReferenceEntiteAdministratif());
                    beaOld.getDetaillesBudget().setReliquatEstimatif(entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
                    beaOld.getDetaillesBudget().setCreditOuvertEstimatif(entiteAdministratif.getDetaillesBudget().getCreditOuvertEstimatif());
                    beaOld.getDetaillesBudget().setReliquatReel(entiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
                    beaOld.getDetaillesBudget().setCreditOuvertReel(entiteAdministratif.getDetaillesBudget().getCreditOuvertReel());
                    beaOld.getDetaillesBudget().setEngagePaye(entiteAdministratif.getDetaillesBudget().getEngagePaye());
                    beaOld.getDetaillesBudget().setEngageNonPaye(entiteAdministratif.getDetaillesBudget().getEngageNonPaye());
                    beaOld.setBudgetSousProjet(budgetSousProjet);
                    budgetSousProjet.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
                    budgetSousProjet.getDetaillesBudget().setReliquatReel(restReel);
                    budgetSousProjetService.save(budgetSousProjet);
                    budgetEntiteAdministratifDao.save(beaOld);
                    budgetCompteBudgitaireService.createBudgetCompteBudgitaire(beaOld, entiteAdministratif.getBudgeCompteBudgitaires());
                }
            }
            return 1;
        }
    }

    @Override
    public void removeBea(String referenceEntiteAdministratif, String referenceSousProjet, int annee) {
        BudgetSousProjet bsp = budgetSousProjetService.findByReferenceSousProjetAndBudgetFaculteAnnee(referenceSousProjet, annee);
        BudgetEntiteAdministratif bea = findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
        bsp.setDetaillesBudget(bsp.getDetaillesBudget());
        bea.setDetaillesBudget(bea.getDetaillesBudget());
        bsp.getDetaillesBudget().setReliquatEstimatif(bsp.getDetaillesBudget().getReliquatEstimatif() + bea.getDetaillesBudget().getCreditOuvertEstimatif());
        bsp.getDetaillesBudget().setReliquatReel(bsp.getDetaillesBudget().getReliquatReel() + bea.getDetaillesBudget().getCreditOuvertReel());
        budgetSousProjetService.save(bsp);
        budgetEntiteAdministratifDao.delete(bea);
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
    public double getAnticident(String refEa, String refSp, int annee) {
        BudgetEntiteAdministratif beaOld = findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(refEa, refSp, annee - 1);
        if (beaOld != null) {
            return beaOld.getDetaillesBudget().getReliquatReel();
        } else {
            return 0D;
        }
    }

    @Override
    public List<BudgetEntiteAdministratif> findByBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetFaculteAnneeLessThan(Integer anneeMin, Integer anneeMax) {
        return budgetEntiteAdministratifDao.findByBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetFaculteAnneeLessThan(anneeMin, anneeMax);
    }

}
