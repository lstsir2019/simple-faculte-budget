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
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.faculte.simplefacultebudget.domain.model.service.BudgetProjetService;
import java.util.ArrayList;

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
    private BudgetCompteBudgitaireService budgetCompteBudgitaireService;

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
    public int createBudgetSousProjet(BudgetProjet budgetProjet, List<BudgetSousProjet> budgetSousProjets) {
        if (budgetProjet == null || budgetSousProjets == null) {
            return -1;
        } else {
            findAndRemoveItems(budgetSousProjets, budgetProjet);
            validateBudgetSouSprojet(budgetProjet, budgetSousProjets);
            for (BudgetSousProjet budgetSousProjet : budgetSousProjets) {
                budgetSousProjet.setBudgetProjet(budgetProjet);
                budgetCompteBudgitaireService.createBudgetCompteBudgitaire(budgetSousProjet, budgetSousProjet.getBudgetCompteBudgitaires());
            }

            return 1;
        }
    }

    private List<BudgetSousProjet> findAndRemoveItems(List<BudgetSousProjet> budgetSousProjets, BudgetProjet budgetProjet) {
        List<BudgetSousProjet> list = findByBudgetProjetReferenceProjetAndBudgetProjetBudgetFaculteAnnee(budgetProjet.getReferenceProjet(), budgetProjet.getBudgetFaculte().getAnnee());
        List<BudgetSousProjet> bpsToRemove = new ArrayList<>();

        for (BudgetSousProjet budgetSousProjet : list) {
            if (!budgetSousProjets.contains(budgetSousProjet)) {
                bpsToRemove.add(budgetSousProjet);
            }
        }
        budgetSousProjetDao.deleteAll(bpsToRemove);
        return bpsToRemove;
    }

    @Override
    public List<BudgetSousProjet> findByBudgetProjetReferenceProjetAndBudgetProjetBudgetFaculteAnnee(String referenceProjet, int annee) {
        return budgetSousProjetDao.findByBudgetProjetReferenceProjetAndBudgetProjetBudgetFaculteAnnee(referenceProjet, annee);
    }

    public int validateBudgetSouSprojet(BudgetProjet budgetProjet, List<BudgetSousProjet> budgetSousProjets) {
        if (budgetProjet == null || budgetSousProjets == null) {
            return -1;
        } else {
            double reliquatEstimatif = 0D;
            double reliquatReel = 0D;
            for (BudgetSousProjet budgetSousProjet : budgetSousProjets) {
                budgetSousProjet.setBudgetProjet(budgetProjet);
                reliquatEstimatif += budgetSousProjet.getDetaillesBudget().getCreditOuvertEstimatif();
                reliquatReel += budgetSousProjet.getDetaillesBudget().getCreditOuvertReel();
            }
            budgetProjet.getDetaillesBudget().setReliquatReel(reliquatReel);
            budgetProjet.getDetaillesBudget().setReliquatEstimatif(reliquatEstimatif);
        }
        return 1;
    }

    @Override
    public void save(BudgetSousProjet budgetSousProjet
    ) {
        budgetSousProjetDao.save(budgetSousProjet);
    }

    @Override
    public int updateBudgetSouSprojet(BudgetSousProjet bspOld, BudgetSousProjet sousProjet,
            double nvReliquatReelBudgetFaculte, double nvReliquatEstimatifBudgetFaculte
    ) {
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
    public boolean isEqual(BudgetSousProjet bsp, BudgetSousProjet sousProjet) {
        return bsp.getDetaillesBudget().getCreditOuvertEstimatif() == sousProjet.getDetaillesBudget().getCreditOuvertEstimatif()
                && bsp.getDetaillesBudget().getCreditOuvertReel() == sousProjet.getDetaillesBudget().getCreditOuvertReel()
                && bsp.getDetaillesBudget().getEngagePaye() == sousProjet.getDetaillesBudget().getEngagePaye()
                && bsp.getDetaillesBudget().getEngageNonPaye() == sousProjet.getDetaillesBudget().getEngageNonPaye();
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
        BudgetSousProjet bsp = findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(referenceSousProjet, annee);
        bf.setDetaillesBudget(bf.getDetaillesBudget());
        bsp.setDetaillesBudget(bsp.getDetaillesBudget());
        bf.getDetaillesBudget().setReliquatEstimatif(bf.getDetaillesBudget().getReliquatEstimatif() + bsp.getDetaillesBudget().getCreditOuvertEstimatif());
        bf.getDetaillesBudget().setReliquatReel(bf.getDetaillesBudget().getReliquatReel() + bsp.getDetaillesBudget().getCreditOuvertEstimatif());
        budgetFaculteService.save(bf);
        budgetSousProjetDao.delete(bsp);
    }

    @Override
    public double getAnticident(String reference, int annee
    ) {
        BudgetSousProjet bspOld = findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(reference, annee - 1);
        if (bspOld != null) {
            return bspOld.getDetaillesBudget().getReliquatReel();
        } else {
            return 0D;
        }
    }

    @Override
    public BudgetSousProjet findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(String referenceSousProjet, int annee
    ) {
        return budgetSousProjetDao.findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(referenceSousProjet, annee);
    }

    @Override
    public List<BudgetSousProjet> findBybudgetProjetBudgetFaculteAnnee(int annee
    ) {
        return budgetSousProjetDao.findBybudgetProjetBudgetFaculteAnnee(annee);
    }

    @Override
    public List<BudgetSousProjet> findByBudgetFaculteAnneeOrBudgetFaculteAnnee(Integer anneeMin, Integer anneeMax
    ) {
        return budgetSousProjetDao.findByBudgetFaculteAnneeOrBudgetFaculteAnnee(anneeMin, anneeMax);
    }

    public BudgetProjetService getBudgetProjetService() {
        return budgetProjetService;
    }

    public void setBudgetProjetService(BudgetProjetService budgetProjetService) {
        this.budgetProjetService = budgetProjetService;
    }

    public BudgetCompteBudgitaireService getBudgetCompteBudgitaireService() {
        return budgetCompteBudgitaireService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
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
