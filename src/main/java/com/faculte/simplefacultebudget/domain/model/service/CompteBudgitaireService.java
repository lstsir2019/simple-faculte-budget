/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.service;

import com.faculte.simplefacultebudget.domain.bean.CompteBudgitaire;
import java.util.List;

/**
 *
 * @author AMINE
 */
public interface CompteBudgitaireService {

    public CompteBudgitaire findByCode(String code);

    public CompteBudgitaire compteBudgitaireSave(CompteBudgitaire compteBudgitaire);

    public int removeByCode(String code);

    public List<CompteBudgitaire> findAll();

}
