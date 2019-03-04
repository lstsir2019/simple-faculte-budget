/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service;

import com.faculte.simplefacultebudget.domain.bean.CompteBudgitaire;

/**
 *
 * @author AMINE
 */
public interface CompteBudgitaireService {

    public CompteBudgitaire findByCode(String code);

    public void creerCompteBudgitaire(CompteBudgitaire compteBudgitaire);

    public void deleteBudgetCompteBudgitaire(String code);

    public int payerCB(String code);

}
