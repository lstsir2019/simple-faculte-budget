/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;

/**
 *
 * @author AMINE
 */
public interface BudgetFaculteService {

    public BudgetFaculte findByAnnee(int annee);

    public int creerBudgetFaculte(BudgetFaculte budgetFaculte);

    public void save(BudgetFaculte budgetFaculte);

    public BudgetFaculte findById(Long id);

    public int payerBudgetFaculte(int annee, double prix);

    public void removeBf(int annee);

    public void updateBudgetFaculte(BudgetFaculte bfFound, BudgetFaculte budgetFaculte);
    
    public double getAnticident(BudgetFaculte budgetFaculte);
}
