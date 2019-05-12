/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service.impl;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.common.util.SearchUtil;
import com.faculte.simplefacultebudget.domain.model.dao.BudgetFaculteDao;
import com.faculte.simplefacultebudget.domain.model.service.BudgetFaculteService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetProjetService;
import com.faculte.simplefacultebudget.domain.model.service.BudgetSousProjetService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
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
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private BudgetProjetService budgetProjetService;

    private static final Logger log = LoggerFactory.getLogger(BudgetFaculteServiceImpl.class);

    public BudgetFaculteDao getBudgetFaculteDao() {
        return budgetFaculteDao;
    }

    @Override
    public BudgetFaculte findByAnnee(int annee) {
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
        BudgetFaculte bf = findByAnnee(budgetFaculte.getAnnee());
        if (bf != null) {
            budgetFaculte.setId(bf.getId());
            budgetFaculte.getDetaillesBudget().setId(bf.getDetaillesBudget().getId());
        }
        budgetProjetService.createBudgetProjet(budgetFaculte, budgetFaculte.getBudgetProjets());
        budgetFaculteDao.save(budgetFaculte);
        return 1;
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

    public BudgetProjetService getBudgetProjetService() {
        return budgetProjetService;
    }

    public void setBudgetSousProjetService(BudgetProjetService budgetProjetService) {
        this.budgetProjetService = budgetProjetService;
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
    public List<BudgetFaculte> findByAnneeMinAndAnneeMax(Integer anneeMin, Integer anneeMax) {
        return entityManager.createQuery(constructQuery(anneeMin, anneeMax)).getResultList();

    }

    private String constructQuery(Integer anneeMin, Integer anneeMax) {
        String query = "SELECT pf FROM BudgetFaculte pf WHERE 1=1";
        query += SearchUtil.addConstraintMinMax("pf", "annee", anneeMin, anneeMax);
        return query;
    }

    @Override
    public boolean isEqual(BudgetFaculte bf, BudgetFaculte budgetFaculte) {
        return bf.getDetaillesBudget().getCreditOuvertEstimatif() == budgetFaculte.getDetaillesBudget().getCreditOuvertEstimatif()
                && bf.getDetaillesBudget().getCreditOuvertReel() == budgetFaculte.getDetaillesBudget().getCreditOuvertReel()
                && bf.getDetaillesBudget().getEngagePaye() == budgetFaculte.getDetaillesBudget().getEngagePaye()
                && bf.getDetaillesBudget().getEngageNonPaye() == budgetFaculte.getDetaillesBudget().getEngageNonPaye();
    }
}
