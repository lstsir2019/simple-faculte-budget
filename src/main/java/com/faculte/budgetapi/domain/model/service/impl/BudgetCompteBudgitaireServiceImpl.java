/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.model.service.impl;

import com.faculte.budgetapi.domain.bean.BudgetCompteBudgitaire;
import com.faculte.budgetapi.domain.bean.BudgetEntiteAdministratif;
import com.faculte.budgetapi.domain.bean.BudgetFaculte;
import com.faculte.budgetapi.domain.bean.BudgetSousProjet;
import com.faculte.budgetapi.domain.bean.CompteBudgitaire;
import com.faculte.budgetapi.domain.model.dao.BudgetCompteBudgitaireDao;
import com.faculte.budgetapi.domain.model.service.BudgetCompteBudgitaireService;
import com.faculte.budgetapi.domain.model.service.BudgetEntiteAdministratifService;
import com.faculte.budgetapi.domain.model.service.BudgetFaculteService;
import com.faculte.budgetapi.domain.model.service.BudgetSousProjetService;
import com.faculte.budgetapi.domain.model.service.CompteBudgitaireService;
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
    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif, String referenceSousProjet, int annee) {
        return budgetCompteBudgitaireDao.findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
    }
//    @Override
//    public int creerBudgetCompteBudgitaire(BudgetCompteBudgitaire budgetCompteBudgitaire) {
//        BudgetFaculte bf = budgetFaculteService.findByAnnee(budgetCompteBudgitaire.getBudgetEntiteAdministratif().getBudgetSousProjet().getBudgetFaculte().getAnnee());
//        if (bf == null) {
//            return -6;
//        }
//        int res = 0;
//        List<BudgetSousProjet> budgetSousProjets = budgetSousProjetService.findByBudgetFaculteAnnee(budgetCompteBudgitaire.getBudgetEntiteAdministratif().getBudgetSousProjet().getBudgetFaculte().getAnnee());
//        
//        List<BudgetEntiteAdministratif> administratifs = budgetEntiteAdministratifService.findByBudgetSousProjetReferenceSousProjet(budgetCompteBudgitaire.getBudgetEntiteAdministratif().getReferenceEntiteAdministratif());
//        List<BudgetCompteBudgitaire> budgetCompteBudgitaires = findByBudgetEntiteAdministratifReferenceEntiteAdministratif(budgetCompteBudgitaire.getBudgetEntiteAdministratif().getReferenceEntiteAdministratif());
//        for (BudgetSousProjet bsp : budgetSousProjets) {
//            if (bsp.getReferenceSousProjet().equals(budgetCompteBudgitaire.getBudgetEntiteAdministratif().getBudgetSousProjet().getReferenceSousProjet())) {
//                for (BudgetEntiteAdministratif bea : bsp.getBudgetEntiteAdmins()) {
//                    if (bea.getReferenceEntiteAdministratif().equals(budgetCompteBudgitaire.getBudgetEntiteAdministratif().getReferenceEntiteAdministratif())) {
//                        for (BudgetCompteBudgitaire bcb : bea.getBudgeCompteBudgitaires()) {
//                           // bcb = findByCompteBudgitaireCode(budgetCompteBudgitaire.getCompteBudgitaire().getCode());
//                           // bcb.setCompteBudgitaire(budgetCompteBudgitaire.getCompteBudgitaire());
//                            if (bcb.getCompteBudgitaire() == budgetCompteBudgitaire.getCompteBudgitaire()) {
//                                return  -2;
//                            }
//                        }
//                        bea.setDetaillesBudget(bea.getDetaillesBudget());
//                        double restEstimatif = bea.getDetaillesBudget().getReliquatEstimatif() - budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif();
//                        double restReel = bea.getDetaillesBudget().getReliquatReel() - budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel();
//                        if (restEstimatif < 0) {
//                            res= -3;
//                        } else if (restReel < 0) {
//                            res = -4;
//                        } else {
//                            CompteBudgitaire cb = new CompteBudgitaire();
//                            BudgetCompteBudgitaire bcb = new BudgetCompteBudgitaire();
//                            bcb.setDetaillesBudget(budgetCompteBudgitaire.getDetaillesBudget());
//                            cb.setCode(budgetCompteBudgitaire.getCompteBudgitaire().getCode());
//                            cb.setLibelle(budgetCompteBudgitaire.getCompteBudgitaire().getLibelle());
//                            compteBudgitaireService.creerCompteBudgitaire(cb);
//                            bcb.getDetaillesBudget().setCreditOuvertEstimatif(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif());
//                            bcb.getDetaillesBudget().setReliquatEstimatif(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif());
//                            bcb.getDetaillesBudget().setCreditOuvertReel(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel());
//                            bcb.getDetaillesBudget().setReliquatReel(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel());
//                            bcb.setCompteBudgitaire(cb);
//                            bcb.setBudgetEntiteAdministratif(bea);
//                            bea.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
//                            bea.getDetaillesBudget().setReliquatReel(restReel);
//                            budgetEntiteAdministratifService.updateReliquatBea(bea);
//                            budgetCompteBudgitaireDao.save(bcb);
//                            res = 1;
//                        }
//
//                    }
//                }
//            }
//        }return res;
//    }

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
    public int creerBudgetCompteBudgitaire(BudgetCompteBudgitaire budgetCompteBudgitaire) {
        BudgetFaculte bf = budgetFaculteService.findByAnnee(budgetCompteBudgitaire.getBudgetEntiteAdministratif().getBudgetSousProjet().getBudgetFaculte().getAnnee());
        BudgetSousProjet bsp = budgetSousProjetService.findByReferenceSousProjetAndBudgetFaculteAnnee(budgetCompteBudgitaire.getBudgetEntiteAdministratif().getBudgetSousProjet().getReferenceSousProjet(), budgetCompteBudgitaire.getBudgetEntiteAdministratif().getBudgetSousProjet().getBudgetFaculte().getAnnee());
        BudgetEntiteAdministratif bea = budgetEntiteAdministratifService.findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(budgetCompteBudgitaire.getBudgetEntiteAdministratif().getReferenceEntiteAdministratif(), budgetCompteBudgitaire.getBudgetEntiteAdministratif().getBudgetSousProjet().getReferenceSousProjet(), budgetCompteBudgitaire.getBudgetEntiteAdministratif().getBudgetSousProjet().getBudgetFaculte().getAnnee());
        BudgetCompteBudgitaire bcb = findByCompteBudgitaireCodeAndBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(budgetCompteBudgitaire.getCompteBudgitaire().getCode(), budgetCompteBudgitaire.getBudgetEntiteAdministratif().getReferenceEntiteAdministratif(), budgetCompteBudgitaire.getBudgetEntiteAdministratif().getBudgetSousProjet().getReferenceSousProjet(), budgetCompteBudgitaire.getBudgetEntiteAdministratif().getBudgetSousProjet().getBudgetFaculte().getAnnee());
        CompteBudgitaire cb = compteBudgitaireService.findByCode(budgetCompteBudgitaire.getCompteBudgitaire().getCode());
        if (bf == null) {
            return -1;
        } else if (bsp == null) {
            return -2;
        } else if (bea == null) {
            return -3;
        } else if (bcb != null) {
            return -4;
        } else if (cb != null) {
            return -5;
        }

        bea.setDetaillesBudget(bea.getDetaillesBudget());
        double restEstimatif = bea.getDetaillesBudget().getReliquatEstimatif() - budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif();
        double restReel = bea.getDetaillesBudget().getReliquatReel() - budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel();
        if (restEstimatif < 0) {
            return -6;
        } else if (restReel < 0) {
            return -7;
        } else {
            cb = new CompteBudgitaire();
            bcb = new BudgetCompteBudgitaire();
            bcb.setDetaillesBudget(budgetCompteBudgitaire.getDetaillesBudget());
            cb.setCode(budgetCompteBudgitaire.getCompteBudgitaire().getCode());
            cb.setLibelle(budgetCompteBudgitaire.getCompteBudgitaire().getLibelle());
            compteBudgitaireService.creerCompteBudgitaire(cb);
            bcb.setCodeBcb(budgetCompteBudgitaire.generateCode());
            bcb.getDetaillesBudget().setCreditOuvertEstimatif(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif());
            bcb.getDetaillesBudget().setReliquatEstimatif(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif());
            bcb.getDetaillesBudget().setCreditOuvertReel(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel());
            bcb.getDetaillesBudget().setReliquatReel(budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel());
            bcb.setCompteBudgitaire(cb);
            bcb.setBudgetEntiteAdministratif(bea);
            bea.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
            bea.getDetaillesBudget().setReliquatReel(restReel);
            budgetEntiteAdministratifService.updateReliquatBea(bea);
            budgetCompteBudgitaireDao.save(bcb);
            return 1;
        }

    }

    @Override
    public BudgetCompteBudgitaire findByCompteBudgitaireCodeAndBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(String code, String referenceEntiteAdministratif, String referenceSousProjet, int annee) {
        return budgetCompteBudgitaireDao.findByCompteBudgitaireCodeAndBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(code, referenceEntiteAdministratif, referenceSousProjet, annee);
    }

    @Override
    public void deleteBudgetEntiteAdministratif(String referenceEntiteAdministratif, String referenceSousProjet, int annee) {
        List<BudgetCompteBudgitaire> budgetCompteBudgitaires = findByBudgetEntiteAdministratifReferenceEntiteAdministratifAndBudgetEntiteAdministratifBudgetSousProjetReferenceSousProjetAndBudgetEntiteAdministratifBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
        for (BudgetCompteBudgitaire budgetCompteBudgitaire : budgetCompteBudgitaires) {
            compteBudgitaireService.deleteBudgetCompteBudgitaire(budgetCompteBudgitaire.getCompteBudgitaire().getCode());

        }
        BudgetEntiteAdministratif budgetEntiteAdministratif = budgetEntiteAdministratifService.findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(referenceEntiteAdministratif, referenceSousProjet, annee);
        budgetEntiteAdministratifService.deleteBudgetEntiteAdministratif(budgetEntiteAdministratif);
    }

    @Override
    public void deleteBudgetCompteBudgitaire(BudgetCompteBudgitaire budgetCompteBudgitaire
    ) {
        budgetCompteBudgitaireDao.delete(budgetCompteBudgitaire);
    }
//    }
//    @Override
//    public List<BudgetCompteBudgitaire> findByBudgetEntiteAdministratifReferenceEntiteAdministratif(String referenceEntiteAdministratif) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public BudgetCompteBudgitaire findByCompteBudgitaireCode(String code) {
        return budgetCompteBudgitaireDao.findByCompteBudgitaireCode(code);
    }

    @Override
    public int createBudgetCompteBudgitaire(BudgetEntiteAdministratif budgetEntiteAdministratif, List<BudgetCompteBudgitaire> budgetCompteBudgitaires) {
        int res = 0;
        for (int k = 0; k < budgetEntiteAdministratif.getBudgeCompteBudgitaires().size(); k++) {
            BudgetCompteBudgitaire bcb = budgetEntiteAdministratif.getBudgeCompteBudgitaires().get(k);
            CompteBudgitaire cb = compteBudgitaireService.findByCode(bcb.getCompteBudgitaire().getCode());
            if (budgetCompteBudgitaires == null || budgetCompteBudgitaires.isEmpty()) {
                break;
            } else {
                budgetEntiteAdministratif.setDetaillesBudget(budgetEntiteAdministratif.getDetaillesBudget());
                double restEstimatif = budgetEntiteAdministratif.getDetaillesBudget().getReliquatEstimatif() - bcb.getDetaillesBudget().getCreditOuvertEstimatif();
                double restReel = budgetEntiteAdministratif.getDetaillesBudget().getReliquatReel() - bcb.getDetaillesBudget().getCreditOuvertReel();
                if (restEstimatif < 0 || restReel < 0) {
                    break;
                } else {
                    bcb.setDetaillesBudget(bcb.getDetaillesBudget());
                    cb.setCode(bcb.getCompteBudgitaire().getCode());
                    cb.setLibelle(bcb.getCompteBudgitaire().getLibelle());
                    compteBudgitaireService.creerCompteBudgitaire(cb);
                    //bcb.setCodeBcb(bcb.generateCode());
                    bcb.setBudgetEntiteAdministratif(budgetEntiteAdministratif);
                    bcb.getDetaillesBudget().setCreditOuvertEstimatif(bcb.getDetaillesBudget().getCreditOuvertEstimatif());
                    bcb.getDetaillesBudget().setReliquatEstimatif(bcb.getDetaillesBudget().getCreditOuvertEstimatif());
                    bcb.getDetaillesBudget().setCreditOuvertReel(bcb.getDetaillesBudget().getCreditOuvertReel());
                    bcb.getDetaillesBudget().setReliquatReel(bcb.getDetaillesBudget().getCreditOuvertReel());
                    bcb.setCompteBudgitaire(cb);
                    budgetEntiteAdministratif.getDetaillesBudget().setReliquatEstimatif(restEstimatif);
                    budgetEntiteAdministratif.getDetaillesBudget().setReliquatReel(restReel);
                    budgetEntiteAdministratifService.updateReliquatBea(budgetEntiteAdministratif);
                    budgetCompteBudgitaireDao.save(bcb);
                    res = 3;
                }
            }
        }
        return res;
    }
}
