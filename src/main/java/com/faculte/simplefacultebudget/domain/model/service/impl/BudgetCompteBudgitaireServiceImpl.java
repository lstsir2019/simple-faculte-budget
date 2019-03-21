/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.bean.BudgetEntiteAdministratif;
import com.faculte.simplefacultebudget.domain.bean.CompteBudgitaire;
import com.faculte.simplefacultebudget.domain.model.dao.BudgetCompteBudgitaireDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetEntiteAdministratifService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import com.faculte.simplefacultebudget.domain.model.service.CompteBudgitaireService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private BudgetEntiteAdministratifService budgetEntiteAdministratifService;

    @Autowired
    private BudgetFaculteService budgetFaculteService;

    @Autowired
    private BudgetSousProjetService budgetSousProjetService;

    @Override
    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif, String referenceSousProjet, int annee) {
        return budgetCompteBudgitaireDao.findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
    }

    @Override
    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(int annee) {
        return budgetCompteBudgitaireDao.findDistinctByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(annee);
    }

    @Override
    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String referenceSousProjet, int annee) {
        return budgetCompteBudgitaireDao.findByBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(referenceSousProjet, annee);
    }

    @Override
    public BudgetCompteBudgitaire findByCompteBudgitaireCodeAndBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String code, String referenceEntiteAdministratif, String referenceSousProjet, int annee) {
        return budgetCompteBudgitaireDao.findByCompteBudgitaireCodeAndBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(code, referenceEntiteAdministratif, referenceSousProjet, annee);
    }

    @Override
    public BudgetCompteBudgitaire findByCompteBudgitaireCode(String code) {
        return budgetCompteBudgitaireDao.findByCompteBudgitaireCode(code);
    }

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
            budgetEntiteAdministratifService.payerBudgetEA(bcb.getBudgetEntiteAdministratif(), nvpaye);
            return 1;
        }
    }

    @Override
    public void updateBudgetCompteBudgitaire(BudgetCompteBudgitaire bcb, BudgetCompteBudgitaire budgetCompteBudgitaire) {
        bcb.getDetaillesBudget().setCreditOuvertEstimatif(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif());
        bcb.getDetaillesBudget().setReliquatEstimatif(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif());
        bcb.getDetaillesBudget().setCreditOuvertReel(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel());
        bcb.getDetaillesBudget().setReliquatReel(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel());
        budgetCompteBudgitaireDao.save(bcb);
    }

    @Override
    public boolean isEqual(BudgetCompteBudgitaire bcb, BudgetCompteBudgitaire compteBudgitaire) {
        if (bcb.getDetaillesBudget().getCreditOuvertEstimatif() == compteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif()
                && bcb.getDetaillesBudget().getCreditOuvertReel() == compteBudgitaire.getDetaillesBudget().getCreditOuvertReel()
                && bcb.getDetaillesBudget().getEngagePaye() == compteBudgitaire.getDetaillesBudget().getEngagePaye()
                && bcb.getDetaillesBudget().getEngageNonPaye() == compteBudgitaire.getDetaillesBudget().getEngageNonPaye()
                && bcb.getCompteBudgitaire().getCode().equals(compteBudgitaire.getCompteBudgitaire().getCode())
                && bcb.getCompteBudgitaire().getLibelle().equals(compteBudgitaire.getCompteBudgitaire().getLibelle())) {
            return true;
        } else {
            return false;
        }
    }

    int i = 1;

    @Override
    public int createBudgetCompteBudgitaire(BudgetEntiteAdministratif budgetEntiteAdministratif, List<BudgetCompteBudgitaire> budgetCompteBudgitaires) {
        if (budgetCompteBudgitaires == null || budgetCompteBudgitaires.isEmpty()) {
            return -1;
        } else {
            for (int k = 0; k < budgetCompteBudgitaires.size(); k++) {
                BudgetCompteBudgitaire budgetCompteBudgitaire = budgetCompteBudgitaires.get(k);
                budgetEntiteAdministratif.setDetaillesBudget(budgetEntiteAdministratif.getDetaillesBudget());
                double restEstimatif = budgetEntiteAdministratif.getDetaillesBudget().getReliquatEstimatif() - budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif();
                double restReel = budgetEntiteAdministratif.getDetaillesBudget().getReliquatReel() - budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel();
                if (restEstimatif < 0 || restReel < 0) {
                    return -2;
                } else {
                    BudgetCompteBudgitaire bcb = findByCompteBudgitaireCodeAndBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(budgetCompteBudgitaire.getCompteBudgitaire().getCode(), budgetEntiteAdministratif.getReferenceEntiteAdministratif(), budgetEntiteAdministratif.getBudgetSousProjet().getReferenceSousProjet(), budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
                    if (bcb != null) {
                        if (!isEqual(bcb, budgetCompteBudgitaire)) {
                            budgetEntiteAdministratif.getDetaillesBudget().setReliquatEstimatif(restEstimatif + bcb.getDetaillesBudget().getCreditOuvertEstimatif());
                            budgetEntiteAdministratif.getDetaillesBudget().setReliquatReel(restReel + bcb.getDetaillesBudget().getCreditOuvertReel());
                            budgetEntiteAdministratifService.save(budgetEntiteAdministratif);
                            updateBudgetCompteBudgitaire(bcb, budgetCompteBudgitaire);
                        }
                    } else {
                        CompteBudgitaire cb = new CompteBudgitaire();
                        bcb = new BudgetCompteBudgitaire();
                        bcb.setDetaillesBudget(budgetCompteBudgitaire.getDetaillesBudget());
                        cb.setCode(budgetCompteBudgitaire.getCompteBudgitaire().getCode());
                        cb.setLibelle(budgetCompteBudgitaire.getCompteBudgitaire().getLibelle());
                        compteBudgitaireService.creerCompteBudgitaire(cb);

                        bcb.getDetaillesBudget().setAntecedent(getAnticident(budgetCompteBudgitaire.getCompteBudgitaire().getCode(), budgetEntiteAdministratif.getReferenceEntiteAdministratif(), budgetEntiteAdministratif.getBudgetSousProjet().getReferenceSousProjet(), budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee() - 1));
                        bcb.setBudgetEntiteAdministratif(budgetEntiteAdministratif);
                        bcb.getDetaillesBudget().setCreditOuvertEstimatif(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif());
                        bcb.getDetaillesBudget().setReliquatEstimatif(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif());
                        bcb.getDetaillesBudget().setCreditOuvertReel(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel());
                        bcb.getDetaillesBudget().setReliquatReel(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel());
                        bcb.setCompteBudgitaire(cb);

                        budgetEntiteAdministratif.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
                        budgetEntiteAdministratif.getDetaillesBudget().setReliquatReel(restReel);
                        budgetEntiteAdministratifService.save(budgetEntiteAdministratif);

                        bcb.setReferenceCompteBudgitaire(bcb.generateCode(i));
                        budgetCompteBudgitaireDao.save(bcb);
                        i++;
                    }
                }
            }
            return 1;
        }
    }

    @Override
    public void removeBcb(String referenceCompteBudgitaire) {
        BudgetCompteBudgitaire bcb = findByReferenceCompteBudgitaire(referenceCompteBudgitaire);
        BudgetEntiteAdministratif bea = budgetEntiteAdministratifService.findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(bcb.getBudgetEntiteAdministratif().getReferenceEntiteAdministratif(), bcb.getBudgetEntiteAdministratif().getBudgetSousProjet().getReferenceSousProjet(), bcb.getBudgetEntiteAdministratif().getBudgetSousProjet().getBudgetFaculte().getAnnee());
        bcb.setDetaillesBudget(bcb.getDetaillesBudget());
        bea.setDetaillesBudget(bea.getDetaillesBudget());
        bea.getDetaillesBudget().setReliquatEstimatif(bea.getDetaillesBudget().getReliquatEstimatif() + bcb.getDetaillesBudget().getCreditOuvertEstimatif());
        bea.getDetaillesBudget().setReliquatReel(bea.getDetaillesBudget().getReliquatReel() + bcb.getDetaillesBudget().getCreditOuvertReel());
        budgetEntiteAdministratifService.save(bea);
        budgetCompteBudgitaireDao.delete(bcb);
    }

    @Override
    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnneeLessThan(Integer anneeMin, Integer anneeMax) {
        return budgetCompteBudgitaireDao.findByBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnneeLessThan(anneeMin, anneeMax);
    }

    @Override
    public BudgetCompteBudgitaire findByReferenceCompteBudgitaire(String reference) {
        return budgetCompteBudgitaireDao.findByReferenceCompteBudgitaire(reference);
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

    public BudgetEntiteAdministratifService getBudgetEntiteAdministratifService() {
        return budgetEntiteAdministratifService;
    }

    public void setBudgetEntiteAdministratifService(BudgetEntiteAdministratifService budgetEntiteAdministratifService) {
        this.budgetEntiteAdministratifService = budgetEntiteAdministratifService;
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
        BudgetCompteBudgitaire bcb = findByCompteBudgitaireCodeAndBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(code, refEa, refBsp, annee);
        if (bcb != null) {
            return bcb.getDetaillesBudget().getReliquatReel();
        } else {
            return 0D;
        }
    }

}
