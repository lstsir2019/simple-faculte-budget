/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.model.dao.BudgetSousProjetDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.faculte.simplefacultebudget.domain.model.service.BudgetProjetService;

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
    private BudgetProjetService budgetProjetService;

    @Autowired
    private BudgetProjetService budgetEntiteAdministratifService;

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
//            budgetFaculteService.payerBudgetFaculte(bsp.getBudgetFaculte().getAnnee(), prix);

            return 1;
        }
    }

    @Override
    public boolean isEqual(BudgetSousProjet bsp, BudgetSousProjet sousProjet) {
        return bsp.getDetaillesBudget().getCreditOuvertEstimatif() == sousProjet.getDetaillesBudget().getCreditOuvertEstimatif()
                && bsp.getDetaillesBudget().getCreditOuvertReel() == sousProjet.getDetaillesBudget().getCreditOuvertReel()
                && bsp.getDetaillesBudget().getEngagePaye() == sousProjet.getDetaillesBudget().getEngagePaye()
                && bsp.getDetaillesBudget().getEngageNonPaye() == sousProjet.getDetaillesBudget().getEngageNonPaye();
    }

    @Override
    public int createBudgetSousProjet(BudgetProjet budgetProjet, List<BudgetSousProjet> budgetSousProjets) {
        if (budgetProjet == null || budgetSousProjets == null) {
            return -1;
        } else {
            BudgetProjet bp = budgetProjetService.findByReferenceProjetAndBudgetFaculteAnnee(budgetProjet.getReferenceProjet(), budgetProjet.getBudgetFaculte().getAnnee());
            if (bp == null) {
                return -2;
            } else {
                for (BudgetSousProjet budgetSousProjet : budgetSousProjets) {
                    budgetSousProjet.setBudgetProjet(bp);
                    budgetSousProjetDao.save(budgetSousProjet);
                }
                return 1;
            }
        }
    }

    private void validateBudgetSouSprojet(BudgetProjet budgetProjet, List<BudgetSousProjet> budgetSousProjets) {
       /* for (int i = 0; i < budgetSousProjets.size(); i++) {
            budgetProjet.setDetaillesBudget(budgetProjet.getDetaillesBudget());
            BudgetSousProjet sousProjet = budgetSousProjets.get(i);
            BudgetSousProjet bsp = findByReferenceSousProjetAndBudgetFaculteAnnee(sousProjet.getReferenceSousProjet(), budgetProjet.getBudgetFaculte().getAnnee());

            double restEstimatif = budgetProjet.getDetaillesBudget().getReliquatEstimatif() - sousProjet.getDetaillesBudget().getCreditOuvertEstimatif();
            double restReel = budgetProjet.getDetaillesBudget().getReliquatReel() - sousProjet.getDetaillesBudget().getCreditOuvertReel();
            if (bsp != null) {
                double nvReliquatReelBudgetFaculte = bsp.getDetaillesBudget().getCreditOuvertReel() + budgetProjet.getDetaillesBudget().getReliquatReel();
                double nvReliquatEstimatifBudgetFaculte = bsp.getDetaillesBudget().getCreditOuvertEstimatif() + budgetProjet.getDetaillesBudget().getReliquatEstimatif();
                if (!isEqual(bsp, sousProjet) && updateBudgetSouSprojet(bsp, sousProjet, nvReliquatReelBudgetFaculte, nvReliquatEstimatifBudgetFaculte) == 1) {
                    budgetProjet.getDetaillesBudget().setReliquatEstimatif(nvReliquatEstimatifBudgetFaculte - sousProjet.getDetaillesBudget().getCreditOuvertEstimatif());
                    budgetProjet.getDetaillesBudget().setReliquatReel(nvReliquatReelBudgetFaculte - sousProjet.getDetaillesBudget().getCreditOuvertReel());
                    budgetFaculteService.save(budgetProjet);
                }
                budgetEntiteAdministratifService.createBudgetEntiteAdministratif(bsp, sousProjet.getBudgetEntiteAdmins());
            } else if (restEstimatif < 0 || restReel < 0) {
                break;
            } else {
                bsp = new BudgetSousProjet();
                bsp.setDetaillesBudget(sousProjet.getDetaillesBudget());
                bsp.getDetaillesBudget().setAntecedent(getAnticident(sousProjet.getReferenceSousProjet(), budgetProjet.getBudgetFaculte().getAnnee()));
                bsp.setReferenceSousProjet(sousProjet.getReferenceSousProjet());
                bsp.getDetaillesBudget().setReliquatEstimatif(sousProjet.getDetaillesBudget().getCreditOuvertEstimatif());
                bsp.getDetaillesBudget().setCreditOuvertEstimatif(sousProjet.getDetaillesBudget().getCreditOuvertEstimatif());
                bsp.getDetaillesBudget().setReliquatReel(sousProjet.getDetaillesBudget().getCreditOuvertReel() + bsp.getDetaillesBudget().getAntecedent());
                bsp.getDetaillesBudget().setCreditOuvertReel(sousProjet.getDetaillesBudget().getCreditOuvertReel() + bsp.getDetaillesBudget().getAntecedent());
                bsp.getDetaillesBudget().setEngagePaye(sousProjet.getDetaillesBudget().getEngagePaye());
                bsp.getDetaillesBudget().setEngageNonPaye(sousProjet.getDetaillesBudget().getEngageNonPaye());
                bsp.setBudgetProjet(budgetProjet);
                budgetProjet.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
                budgetProjet.getDetaillesBudget().setReliquatReel(restReel);
                // budgetFaculteService.save(budgetProjet);
                budgetSousProjetDao.save(bsp);
//                    budgetEntiteAdministratifService.createBudgetEntiteAdministratif(bsp, sousProjet.());
            }
        }*/
    }

    @Override
    public void save(BudgetSousProjet budgetSousProjet) {
        budgetSousProjetDao.save(budgetSousProjet);
    }

    @Override
    public int updateBudgetSouSprojet(BudgetSousProjet bspOld, BudgetSousProjet sousProjet, double nvReliquatReelBudgetFaculte, double nvReliquatEstimatifBudgetFaculte) {
        double ReelConsomer = bspOld.getDetaillesBudget().getCreditOuvertReel() - bspOld.getDetaillesBudget().getReliquatReel();
        double EstimatifConsomer = bspOld.getDetaillesBudget().getCreditOuvertEstimatif() - bspOld.getDetaillesBudget().getReliquatEstimatif();
        if (nvReliquatReelBudgetFaculte < sousProjet.getDetaillesBudget().getCreditOuvertReel() || nvReliquatEstimatifBudgetFaculte < sousProjet.getDetaillesBudget().getCreditOuvertEstimatif()) {
            return -1;
        } else if (sousProjet.getDetaillesBudget().getCreditOuvertReel() < ReelConsomer
                || sousProjet.getDetaillesBudget().getCreditOuvertEstimatif() < EstimatifConsomer) {
            return -2;
        } else {
            bspOld.getDetaillesBudget().setCreditOuvertEstimatif(sousProjet.getDetaillesBudget().getCreditOuvertEstimatif());
            bspOld.getDetaillesBudget().setCreditOuvertReel(sousProjet.getDetaillesBudget().getCreditOuvertReel());
            bspOld.getDetaillesBudget().setEngagePaye(sousProjet.getDetaillesBudget().getEngagePaye());
            bspOld.getDetaillesBudget().setEngageNonPaye(sousProjet.getDetaillesBudget().getEngageNonPaye());
            bspOld.getDetaillesBudget().setReliquatReel(sousProjet.getDetaillesBudget().getCreditOuvertReel() - EstimatifConsomer);
            bspOld.getDetaillesBudget().setReliquatEstimatif(sousProjet.getDetaillesBudget().getCreditOuvertEstimatif() - ReelConsomer);
            budgetSousProjetDao.save(bspOld);
            return 1;
        }
    }

    @Override
    public void removeBsp(int annee, String referenceSousProjet) {
//        double reliquatEstimatif = 0;
//        double reliquatReel = 0;
//        BudgetFaculte bf = budgetFaculteService.findByAnnee(annee);
//        BudgetSousProjet bsp = findByReferenceSousProjetAndBudgetFaculteAnnee(referenceSousProjet, annee);
//        for (BudgetEntiteAdministratif bea : bsp.getBudgetEntiteAdmins()) {
//            reliquatEstimatif += bsp.getDetaillesBudget().getReliquatEstimatif();
//            reliquatReel += bsp.getDetaillesBudget().getReliquatReel();
//            for (BudgetCompteBudgitaire bcb : bea.getBudgeCompteBudgitaires()) {
//                reliquatEstimatif += bcb.getDetaillesBudget().getCreditOuvertEstimatif();
//                reliquatReel += bcb.getDetaillesBudget().getCreditOuvertReel();
//            }
//        }
        BudgetFaculte bf = budgetFaculteService.findByAnnee(annee);
        BudgetSousProjet bsp = findByReferenceSousProjetAndbudgetProjetBudgetFaculteAnnee(referenceSousProjet, annee);
        bf.setDetaillesBudget(bf.getDetaillesBudget());
        bsp.setDetaillesBudget(bsp.getDetaillesBudget());
        bf.getDetaillesBudget().setReliquatEstimatif(bf.getDetaillesBudget().getReliquatEstimatif() + bsp.getDetaillesBudget().getCreditOuvertEstimatif());
        bf.getDetaillesBudget().setReliquatReel(bf.getDetaillesBudget().getReliquatReel() + bsp.getDetaillesBudget().getCreditOuvertEstimatif());
        budgetFaculteService.save(bf);
        budgetSousProjetDao.delete(bsp);
    }

    @Override
    public double getAnticident(String reference, int annee) {
        BudgetSousProjet bspOld = findByReferenceSousProjetAndbudgetProjetBudgetFaculteAnnee(reference, annee - 1);
        if (bspOld != null) {
            return bspOld.getDetaillesBudget().getReliquatReel();
        } else {
            return 0D;
        }
    }

    @Override
    public BudgetSousProjet findByReferenceSousProjetAndbudgetProjetBudgetFaculteAnnee(String referenceSousProjet, int annee) {
        return budgetSousProjetDao.findByReferenceSousProjetAndbudgetProjetBudgetFaculteAnnee(referenceSousProjet, annee);
    }

    @Override
    public List<BudgetSousProjet> findBybudgetProjetBudgetFaculteAnnee(int annee) {
        return budgetSousProjetDao.findBybudgetProjetBudgetFaculteAnnee(annee);
    }

    @Override
    public List<BudgetSousProjet> findByBudgetFaculteAnneeOrBudgetFaculteAnnee(Integer anneeMin, Integer anneeMax) {
        return budgetSousProjetDao.findByBudgetFaculteAnneeOrBudgetFaculteAnnee(anneeMin, anneeMax);
    }

    public BudgetProjetService getBudgetEntiteAdministratifService() {
        return budgetEntiteAdministratifService;
    }

    public void setBudgetEntiteAdministratifService(BudgetProjetService budgetEntiteAdministratifService) {
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
