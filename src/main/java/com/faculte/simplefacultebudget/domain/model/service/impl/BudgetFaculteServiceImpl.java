/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.model.dao.BudgetFaculteDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AMINE
 */
@Service
public class BudgetFaculteServiceImpl implements BudgetFaculteService {

    @Autowired
    private BudgetFaculteDao budgetFaculteDao;

    @Autowired
    private BudgetSousProjetService budgetSousProjetService;

    org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    public BudgetFaculteDao getBudgetFaculteDao() {
        return budgetFaculteDao;
    }

    @Override
    public BudgetFaculte findByAnnee(int annee) {
        log.info("l'objet budget faculte");
        return budgetFaculteDao.findByAnnee(annee);
    }

    @Override
    public int payerBudgetFaculte(int annee, double prix) {
        BudgetFaculte bf = findByAnnee(annee);
        if (bf == null) {
            return -1;
        } else {
            double nvnonpaye = bf.getDetaillesBudget().getEngageNonPaye() - prix;
            double nvpaye = bf.getDetaillesBudget().getEngagePaye() + prix;

            double nvRelPayEst = bf.getDetaillesBudget().getCreditOuvertEstimatif() - nvpaye;
            double nvRelPayRel = bf.getDetaillesBudget().getCreditOuvertReel() - nvpaye;
            double nvRelNPyEst = bf.getDetaillesBudget().getCreditOuvertEstimatif() - nvnonpaye;
            double nvRelNPyRel = bf.getDetaillesBudget().getCreditOuvertReel() - nvnonpaye;

            bf.getDetaillesBudget().setEngagePaye(nvpaye);
            bf.getDetaillesBudget().setEngageNonPaye(nvnonpaye);
            bf.getDetaillesBudget().setReliquatPayeEstimatif(nvRelPayEst);
            bf.getDetaillesBudget().setReliquatPayereel(nvRelPayRel);
            bf.getDetaillesBudget().setReliquatNonPayeEstimatif(nvRelNPyEst);
            bf.getDetaillesBudget().setReliquatNonPayReel(nvRelNPyRel);

            budgetFaculteDao.save(bf);

            return 1;
        }
    }

    @Override
    public int updateBudgetFaculte(BudgetFaculte bfFound, BudgetFaculte budgetFaculte) {
        double ReelConsomer = bfFound.getDetaillesBudget().getCreditOuvertReel() - bfFound.getDetaillesBudget().getReliquatReel();
        double EstimatifConsomer = bfFound.getDetaillesBudget().getCreditOuvertEstimatif() - bfFound.getDetaillesBudget().getReliquatEstimatif();
        if (budgetFaculte.getDetaillesBudget().getCreditOuvertReel() < ReelConsomer
                || budgetFaculte.getDetaillesBudget().getCreditOuvertEstimatif() < EstimatifConsomer) {

            return -1;
        } else {
            bfFound.getDetaillesBudget().setCreditOuvertEstimatif(budgetFaculte.getDetaillesBudget().getCreditOuvertEstimatif());
            bfFound.getDetaillesBudget().setCreditOuvertReel(budgetFaculte.getDetaillesBudget().getCreditOuvertReel());
            bfFound.getDetaillesBudget().setEngagePaye(budgetFaculte.getDetaillesBudget().getEngagePaye());
            bfFound.getDetaillesBudget().setEngageNonPaye(budgetFaculte.getDetaillesBudget().getEngageNonPaye());
            bfFound.getDetaillesBudget().setReliquatReel(budgetFaculte.getDetaillesBudget().getCreditOuvertReel() - EstimatifConsomer);
            bfFound.getDetaillesBudget().setReliquatEstimatif(budgetFaculte.getDetaillesBudget().getCreditOuvertEstimatif() - ReelConsomer);
            budgetFaculteDao.save(bfFound);
            return 1;
        }
    }

    @Override
    public int creerBudgetFaculte(BudgetFaculte budgetFaculte) {
        BudgetFaculte bf=findByAnnee(budgetFaculte.getAnnee());
        if (bf != null) {
            if (!isEqual(bf, budgetFaculte)) {
                updateBudgetFaculte(bf, budgetFaculte);
            }
                budgetSousProjetService.createBudgetSousProjet(bf, budgetFaculte.getBudgetSousProjets());
            return 1;
        } else {
            bf = new BudgetFaculte();
            bf.setDetaillesBudget(budgetFaculte.getDetaillesBudget());
            bf.setAnnee(budgetFaculte.getAnnee());
            bf.getDetaillesBudget().setAntecedent(getAnticident(budgetFaculte));
            bf.getDetaillesBudget().setReliquatEstimatif(budgetFaculte.getDetaillesBudget().getCreditOuvertEstimatif());
            bf.getDetaillesBudget().setCreditOuvertEstimatif(budgetFaculte.getDetaillesBudget().getCreditOuvertEstimatif());
            bf.getDetaillesBudget().setReliquatReel(budgetFaculte.getDetaillesBudget().getCreditOuvertReel() + bf.getDetaillesBudget().getAntecedent());
            bf.getDetaillesBudget().setCreditOuvertReel(budgetFaculte.getDetaillesBudget().getCreditOuvertReel() + bf.getDetaillesBudget().getAntecedent());
            bf.getDetaillesBudget().setEngagePaye(budgetFaculte.getDetaillesBudget().getEngagePaye());
            bf.getDetaillesBudget().setEngageNonPaye(budgetFaculte.getDetaillesBudget().getEngageNonPaye());
            budgetFaculteDao.save(bf);
            budgetSousProjetService.createBudgetSousProjet(bf, budgetFaculte.getBudgetSousProjets());
            return 2;
        }
    }

    @Override
    public void save(BudgetFaculte budgetFaculte) {
        budgetFaculteDao.save(budgetFaculte);
    }

    @Override
    public BudgetFaculte findById(Long id) {
        return budgetFaculteDao.getOne(id);
    }

    @Override
    public void removeBf(int annee) {
        BudgetFaculte bf = findByAnnee(annee);
        budgetFaculteDao.delete(bf);
    }

    public BudgetSousProjetService getBudgetSousProjetService() {
        return budgetSousProjetService;
    }

    public void setBudgetSousProjetService(BudgetSousProjetService budgetSousProjetService) {
        this.budgetSousProjetService = budgetSousProjetService;
    }

    public void setBudgetFaculteDao(BudgetFaculteDao budgetFaculteDao) {
        this.budgetFaculteDao = budgetFaculteDao;
    }

    @Override
    public double getAnticident(BudgetFaculte budgetFaculte) {
        BudgetFaculte bfOld = findByAnnee(budgetFaculte.getAnnee() - 1);
        if (bfOld != null) {
            return bfOld.getDetaillesBudget().getReliquatReel();
        } else {
            return 0D;
        }
    }

    @Override
    public List<BudgetFaculte> findByAnneeGreaterThanEqualOrAnneeLessThanEqual(int anneeMin, int anneeMax) {
        return budgetFaculteDao.findByAnneeGreaterThanEqualOrAnneeLessThanEqual(anneeMin, anneeMax);
    }

    @Override
    public boolean isEqual(BudgetFaculte bf, BudgetFaculte budgetFaculte) {
        return bf.getDetaillesBudget().getCreditOuvertEstimatif() == budgetFaculte.getDetaillesBudget().getCreditOuvertEstimatif()
                && bf.getDetaillesBudget().getCreditOuvertReel() == budgetFaculte.getDetaillesBudget().getCreditOuvertReel()
                && bf.getDetaillesBudget().getEngagePaye() == budgetFaculte.getDetaillesBudget().getEngagePaye()
                && bf.getDetaillesBudget().getEngageNonPaye() == budgetFaculte.getDetaillesBudget().getEngageNonPaye();
    }

}
