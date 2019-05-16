/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
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
    public int createBudgetSousProjet(BudgetProjet budgetProjet, List<BudgetSousProjet> budgetSousProjets) {
        if (budgetProjet == null || budgetSousProjets == null) {
            return -1;
        } else {
            //    findAndRemoveItems(budgetSousProjets, budgetProjet);
            //    validateBudgetSousProjet(budgetProjet, budgetSousProjets);
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
    public List<BudgetSousProjet> removeBudgetSousProjets(List<BudgetSousProjet> budgetSousProjets) {

        budgetSousProjetDao.deleteAll(budgetSousProjets);
        return budgetSousProjets;
    }

    @Override
    public List<BudgetSousProjet> findByBudgetProjetReferenceProjetAndBudgetProjetBudgetFaculteAnnee(String referenceProjet, int annee) {
        return budgetSousProjetDao.findByBudgetProjetReferenceProjetAndBudgetProjetBudgetFaculteAnnee(referenceProjet, annee);
    }

    public int validateBudgetSousProjet(BudgetProjet budgetProjet, List<BudgetSousProjet> budgetSousProjets) {
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

//    @Override
//    public int calculeDetaillesBudgetSousProjet(BudgetSousProjet budgetSousProjet) {
//        if (budgetSousProjet == null) {
//            return -1;
//        } else {
//            List<BudgetCompteBudgitaire> budgetCompteBudgitaires = budgetCompteBudgitaireService.findByBudgetSousProjetBudgetProjetReferenceProjetAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetProjetBudgetFaculteAnnee(budgetSousProjet.getBudgetProjet().getReferenceProjet(), budgetSousProjet.getReferenceSousProjet(), budgetSousProjet.getBudgetProjet().getBudgetFaculte().getAnnee());
//            if (budgetCompteBudgitaires == null) {
//                return -2;
//            }
//            double reliquatEstimatif = 0D;
//            double reliquatReel = 0D;
//            for (BudgetCompteBudgitaire budgetCompteBudgitaire : budgetCompteBudgitaires) {
//                reliquatEstimatif += budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertEstimatif();
//                reliquatReel += budgetCompteBudgitaire.getDetaillesBudget().getCreditOuvertReel();
//            }
//            budgetSousProjet.getDetaillesBudget().setReliquatReel(reliquatReel);
//            budgetSousProjet.getDetaillesBudget().setReliquatEstimatif(reliquatEstimatif);
//            budgetSousProjetDao.save(budgetSousProjet);
//            return 1;
//        }
//    }
    @Override
    public void calculeDetaillesBudgetSousProjet(BudgetProjet budgetProjet) {
        List<BudgetSousProjet> budgetSousProjets = findByBudgetProjetReferenceProjetAndBudgetProjetBudgetFaculteAnnee(budgetProjet.getReferenceProjet(), budgetProjet.getBudgetFaculte().getAnnee());
        budgetSousProjets.forEach(bsp -> {
            budgetCompteBudgitaireService.calculeDetaillesbudgetCompteBudgitaire(bsp);
        });
        double reliquatEstimatif = 0D;
        double reliquatReel = 0D;
        for (BudgetSousProjet budgetSousProjet : budgetSousProjets) {
            reliquatEstimatif += budgetSousProjet.getDetaillesBudget().getCreditOuvertEstimatif();
            reliquatReel += budgetSousProjet.getDetaillesBudget().getCreditOuvertReel();
        }
        budgetProjet.setBudgetSousProjets(budgetSousProjets);
        budgetProjet.getDetaillesBudget().setReliquatReel(reliquatReel);
        budgetProjet.getDetaillesBudget().setReliquatEstimatif(reliquatEstimatif);

    }

    @Override
    public void save(BudgetSousProjet budgetSousProjet) {
        budgetSousProjetDao.save(budgetSousProjet);
    }

    @Override
    public void deleteById(Long id) {
        budgetSousProjetDao.deleteById(id);
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
