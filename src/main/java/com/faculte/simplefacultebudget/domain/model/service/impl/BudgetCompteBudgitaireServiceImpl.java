/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.bean.CompteBudgitaire;
import com.faculte.simplefacultebudget.domain.model.dao.BudgetCompteBudgitaireDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import com.faculte.simplefacultebudget.domain.model.service.CompteBudgitaireService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.faculte.simplefacultebudget.domain.model.service.BudgetProjetService;

/**
 *
 * @author AMINE
 */
@Service
public class BudgetCompteBudgitaireServiceImpl implements BudgetCompteBudgitaireService {

    @Autowired
    private BudgetCompteBudgitaireService budgetCompteBudgitaireService;

    @Autowired
    private BudgetCompteBudgitaireDao budgetCompteBudgitaireDao;

    @Autowired
    private CompteBudgitaireService compteBudgitaireService;

    @Autowired
    private BudgetProjetService budgetProjetService;

    @Autowired
    private BudgetFaculteService budgetFaculteService;

    @Autowired
    private BudgetSousProjetService budgetSousProjetService;

    private static final Logger log = LoggerFactory.getLogger(BudgetCompteBudgitaireServiceImpl.class);

    @Override
    public int payerBCB(String code) {
        BudgetCompteBudgitaire bcb = findByCompteBudgitaireCode(code);
        if (bcb == null) {
            return -1;
        } else if (bcb.getDetaillesBudget().getEngageNonPaye() == 0) {
            return -2;
        } else {
            double nvpaye = bcb.getDetaillesBudget().getEngageNonPaye();
            double nvnonpaye = bcb.getDetaillesBudget().getEngagePaye();

            double nvRelPayEst = bcb.getDetaillesBudget().getCreditOuvertEstimatif() - nvpaye;
            double nvRelPayRel = bcb.getDetaillesBudget().getCreditOuvertReel() - nvpaye;
            double nvRelNPyEst = bcb.getDetaillesBudget().getCreditOuvertEstimatif() - nvnonpaye;
            double nvRelNPyRel = bcb.getDetaillesBudget().getCreditOuvertReel() - nvnonpaye;

            bcb.getDetaillesBudget().setEngagePaye(nvpaye);
            bcb.getDetaillesBudget().setEngageNonPaye(nvnonpaye);
            bcb.getDetaillesBudget().setReliquatPayeEstimatif(nvRelPayEst);
            bcb.getDetaillesBudget().setReliquatPayereel(nvRelPayRel);
            bcb.getDetaillesBudget().setReliquatNonPayeEstimatif(nvRelNPyEst);
            bcb.getDetaillesBudget().setReliquatNonPayReel(nvRelNPyRel);

            budgetCompteBudgitaireDao.save(bcb);
            //    budgetSousProjetService.payerBudgetEA(bcb.getBudgetSousProjet(), nvpaye);
            return 1;
        }
    }

    @Override
    public int updateBudgetCompteBudgitaire(BudgetCompteBudgitaire budgetCompteBudgitaireOld, BudgetCompteBudgitaire budgetCompteBudgitaireNew, double nvReliquatReelBudgetEntiteAdministratif, double nvReliquatEstimatifBudgetEntiteAdministratif) {
        double ReelConsomer = budgetCompteBudgitaireOld.getDetaillesBudget().getCreditOuvertReel() - budgetCompteBudgitaireOld.getDetaillesBudget().getReliquatReel();
        double EstimatifConsomer = budgetCompteBudgitaireOld.getDetaillesBudget().getCreditOuvertEstimatif() - budgetCompteBudgitaireOld.getDetaillesBudget().getReliquatEstimatif();
        if (nvReliquatReelBudgetEntiteAdministratif < budgetCompteBudgitaireNew.getDetaillesBudget().getCreditOuvertReel() || nvReliquatEstimatifBudgetEntiteAdministratif < budgetCompteBudgitaireNew.getDetaillesBudget().getCreditOuvertEstimatif()) {
            return -1;
        } else if (budgetCompteBudgitaireNew.getDetaillesBudget().getCreditOuvertReel() < ReelConsomer
                || budgetCompteBudgitaireNew.getDetaillesBudget().getCreditOuvertEstimatif() < EstimatifConsomer) {
            return -2;
        } else {
            budgetCompteBudgitaireOld.getDetaillesBudget().setCreditOuvertEstimatif(budgetCompteBudgitaireNew.getDetaillesBudget().getCreditOuvertEstimatif());
            budgetCompteBudgitaireOld.getDetaillesBudget().setReliquatEstimatif(budgetCompteBudgitaireNew.getDetaillesBudget().getCreditOuvertEstimatif());
            budgetCompteBudgitaireOld.getDetaillesBudget().setCreditOuvertReel(budgetCompteBudgitaireNew.getDetaillesBudget().getCreditOuvertReel());
            budgetCompteBudgitaireOld.getDetaillesBudget().setReliquatReel(budgetCompteBudgitaireNew.getDetaillesBudget().getCreditOuvertReel());
            budgetCompteBudgitaireOld.getDetaillesBudget().setEngagePaye(budgetCompteBudgitaireNew.getDetaillesBudget().getEngagePaye());
            budgetCompteBudgitaireOld.getDetaillesBudget().setEngageNonPaye(budgetCompteBudgitaireNew.getDetaillesBudget().getEngageNonPaye());
            budgetCompteBudgitaireDao.save(budgetCompteBudgitaireOld);
            return 1;
        }
    }

    @Override
    public boolean isEqual(BudgetCompteBudgitaire bcb, BudgetCompteBudgitaire compteBudgitaire) {
        return bcb.getDetaillesBudget().getCreditOuvertEstimatif() == compteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif()
                && bcb.getDetaillesBudget().getCreditOuvertReel() == compteBudgitaire.getDetaillesBudget().getCreditOuvertReel()
                && bcb.getDetaillesBudget().getEngagePaye() == compteBudgitaire.getDetaillesBudget().getEngagePaye()
                && bcb.getDetaillesBudget().getEngageNonPaye() == compteBudgitaire.getDetaillesBudget().getEngageNonPaye();
    }

    @Override
    public int createBudgetCompteBudgitaire(BudgetSousProjet budgetSousProjet, List<BudgetCompteBudgitaire> budgetCompteBudgitaires) {
        if (budgetCompteBudgitaires == null || budgetCompteBudgitaires.isEmpty()) {
            return -1;
        } else {
            if (!valideBudgetCompteBudgitaire(budgetSousProjet, budgetCompteBudgitaires)) {
                return -2;
            }
            return 1;
        }
    }

    private boolean valideBudgetCompteBudgitaire(BudgetSousProjet budgetSousProjet, List<BudgetCompteBudgitaire> budgetCompteBudgitaires) {
        int res = 0;
        for (BudgetCompteBudgitaire budgetCompteBudgitaire : budgetCompteBudgitaires) {
            res += validteBudgetCompteBudgitaire(budgetCompteBudgitaire);
        }
        return res == budgetCompteBudgitaires.size();
    }

    public int validteBudgetCompteBudgitaire(BudgetCompteBudgitaire budgetCompteBudgitaire) {
        if (budgetCompteBudgitaire == null) {
            return -1;
        } else {
            CompteBudgitaire cb = compteBudgitaireService.findByCodeAndAnnee(budgetCompteBudgitaire.getCompteBudgitaire().getCode(), budgetCompteBudgitaire.getCompteBudgitaire().getAnnee());
            BudgetSousProjet bsp = budgetSousProjetService.findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(budgetCompteBudgitaire.getBudgetSousProjet().getReferenceSousProjet(), budgetCompteBudgitaire.getBudgetSousProjet().getBudgetProjet().getBudgetFaculte().getAnnee());
            if (cb == null || bsp == null) {
                return -2;
            } else {
                double restEstimatif = cb.getDetaillesBudget().getCreditOuvertEstimatif() - cb.getDetaillesBudget().getReliquatEstimatif() - budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif();
                double restReel = cb.getDetaillesBudget().getCreditOuvertReel() - cb.getDetaillesBudget().getReliquatReel() - budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel();
                if (restEstimatif < 0) {
                    return -3;
                } else if (restReel < 0) {
                    return -4;
                } else {
                    budgetCompteBudgitaire.getDetaillesBudget().setCreditOuvertEstimatif(restEstimatif);
                    budgetCompteBudgitaire.getDetaillesBudget().setCreditOuvertReel(restReel);
                    //  budgetCompteBudgitaire.getBudgetSousProjet().getDetaillesBudget().setCreditOuvertEstimatif(budgetCompteBudgitaire.getBudgetSousProjet().getDetaillesBudget().getAntecedent() + restEstimatif);
                    //  budgetCompteBudgitaire.getBudgetSousProjet().getDetaillesBudget().setCreditOuvertReel(budgetCompteBudgitaire.getBudgetSousProjet().getDetaillesBudget().getReliquatReel()+ restReel);

                    return 1;
                }
            }
        }
    }

    public void removebudgetCompteBudgitaire(String codeCompteBudgitaire, String referenceSousProjet, int annee) {
        CompteBudgitaire cb = compteBudgitaireService.findByCodeAndAnnee(codeCompteBudgitaire, annee);
        BudgetSousProjet bsp = budgetSousProjetService.findByReferenceSousProjetAndBudgetProjetBudgetFaculteAnnee(referenceSousProjet,annee);
      BudgetCompteBudgitaire bcb= budgetCompteBudgitaireDao.findByCompteBudgitaireCodeAndCompteBudgitaireAnneeAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(codeCompteBudgitaire, annee,referenceSousProjet,annee);
    }

    @Override
    public void removeBcb(String referenceCompteBudgitaire) {
        /*   BudgetCompteBudgitaire bcb = findByReference(referenceCompteBudgitaire);
        BudgetProjet bea = budgetSousProjetService.findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(bcb.getBudgetEntiteAdministratif().getReferenceEntiteAdministratif(), bcb.getBudgetEntiteAdministratif().getBudgetSousProjet().getReferenceSousProjet(), bcb.getBudgetEntiteAdministratif().getBudgetSousProjet().getBudgetFaculte().getAnnee());
        bcb.setDetaillesBudget(bcb.getDetaillesBudget());
        bea.setDetaillesBudget(bea.getDetaillesBudget());
        bea.getDetaillesBudget().setReliquatEstimatif(bea.getDetaillesBudget().getReliquatEstimatif() + bcb.getDetaillesBudget().getCreditOuvertEstimatif());
        bea.getDetaillesBudget().setReliquatReel(bea.getDetaillesBudget().getReliquatReel() + bcb.getDetaillesBudget().getCreditOuvertReel());
//        budgetSousProjetService.save(bea);
        budgetCompteBudgitaireDao.delete(bcb);*/
    }

    @Override
    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetReferenceProjetAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String referenceProjet, String referenceSousProjet, int annee) {
        return budgetCompteBudgitaireDao.findByBudgetSousProjetBudgetProjetReferenceProjetAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(referenceProjet, referenceSousProjet, annee);
    }

    @Override
    public BudgetCompteBudgitaire findByCompteBudgitaireCodeAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String code, String referenceSousProjet, int annee) {
        return budgetCompteBudgitaireDao.findByCompteBudgitaireCodeAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(code, referenceSousProjet, annee);
    }

    @Override
    public BudgetCompteBudgitaire findByCompteBudgitaireCode(String code) {
        return budgetCompteBudgitaireDao.findByCompteBudgitaireCode(code);
    }

    @Override
    public List<BudgetCompteBudgitaire> findDistinctByBudgetSousProjetBudgetProjetBudgetFaculteAnnee(int annee) {
        return budgetCompteBudgitaireDao.findDistinctByBudgetSousProjetBudgetProjetBudgetFaculteAnnee(annee);
    }

    @Override
    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetReferenceProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(String referenceSousProjet, int annee) {
        return budgetCompteBudgitaireDao.findByBudgetSousProjetBudgetProjetReferenceProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(referenceSousProjet, annee);
    }

    @Override
    public List<BudgetCompteBudgitaire> findByBudgetSousProjetBudgetProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetProjetBudgetFaculteAnneeLessThan(Integer anneeMin, Integer anneeMax) {
        return budgetCompteBudgitaireDao.findByBudgetSousProjetBudgetProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetProjetBudgetFaculteAnneeLessThan(anneeMin, anneeMax);
    }

    @Override
    public BudgetCompteBudgitaire findByReference(String reference) {
        return budgetCompteBudgitaireDao.findByReference(reference);
    }

    public BudgetSousProjetService getBudgetSousProjetService() {
        return budgetSousProjetService;
    }

    public void setBudgetSousProjetService(BudgetSousProjetService budgetSousProjetService) {
        this.budgetSousProjetService = budgetSousProjetService;
    }

    public BudgetFaculteService getBudgetFaculteService() {
        return budgetFaculteService;
    }

    public void setBudgetFaculteService(BudgetFaculteService budgetFaculteService) {
        this.budgetFaculteService = budgetFaculteService;
    }

    public BudgetProjetService getBudgetProjetService() {
        return budgetProjetService;
    }

    public void setBudgetProjetService(BudgetProjetService budgetProjetService) {
        this.budgetProjetService = budgetProjetService;
    }

    public CompteBudgitaireService getCompteBudgitaireService() {
        return compteBudgitaireService;
    }

    public void setCompteBudgitaireService(CompteBudgitaireService compteBudgitaireService) {
        this.compteBudgitaireService = compteBudgitaireService;
    }

    public BudgetCompteBudgitaireService getBudgetCompteBudgitaireService() {
        return budgetCompteBudgitaireService;
    }

    public void setBudgetCompteBudgitaireService(BudgetCompteBudgitaireService budgetCompteBudgitaireService) {
        this.budgetCompteBudgitaireService = budgetCompteBudgitaireService;
    }

    public BudgetCompteBudgitaireDao getBudgetCompteBudgitaireDao() {
        return budgetCompteBudgitaireDao;
    }

    public void setBudgetCompteBudgitaireDao(BudgetCompteBudgitaireDao budgetCompteBudgitaireDao) {
        this.budgetCompteBudgitaireDao = budgetCompteBudgitaireDao;
    }

    @Override
    public double getAnticident(String code, String refEa, String refBsp, int annee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
