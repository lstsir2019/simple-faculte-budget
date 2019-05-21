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
import org.springframework.data.domain.Sort;
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
    public int creerBudgetFaculte(BudgetFaculte budgetFaculte) {
        if (Integer.toString(budgetFaculte.getAnnee()).length() <= 3) {
            return -1;
        }
        BudgetFaculte bf = findByAnnee(budgetFaculte.getAnnee());
        if (bf != null) {
            budgetFaculte.setId(bf.getId());
            budgetFaculte.getDetaillesBudget().setId(bf.getDetaillesBudget().getId());
        }
        budgetProjetService.createBudgetProjet(budgetFaculte, budgetFaculte.getBudgetProjets());
        budgetFaculteDao.save(budgetFaculte);
        calculeDetaillesBudgetFaculte(budgetFaculte);
        budgetFaculteDao.save(budgetFaculte);
        return 1;
    }

    public int calculeDetaillesBudgetFaculte(BudgetFaculte budgetFaculte) {
        if (budgetFaculte == null) {
            return -1;
        } else {
            this.budgetProjetService.calculeDetaillesBudgetProjet(budgetFaculte);
            return 1;
        }
    }

    @Override
    public List<BudgetFaculte> findAll() {
        return budgetFaculteDao.findAll(new Sort(Sort.Direction.DESC, "annee"));
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
    public void remove(int annee) {
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

}
