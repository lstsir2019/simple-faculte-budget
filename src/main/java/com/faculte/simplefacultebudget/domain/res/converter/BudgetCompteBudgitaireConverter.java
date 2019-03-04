/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.res.converter;

import com.faculte.simplefacultebudget.domain.bean.BudgetCompteBudgitaire;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetCompteBudgitaireVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class BudgetCompteBudgitaireConverter extends AbstractConverter<BudgetCompteBudgitaire, BudgetCompteBudgitaireVo> {

    @Override
    public BudgetCompteBudgitaire toItem(BudgetCompteBudgitaireVo vo) {
        if (vo == null) {
            return null;
        } else {
            BudgetCompteBudgitaire item = new BudgetCompteBudgitaire();
            item.setId(vo.getId());
            item.setCompteBudgitaire(new CompteBudgitaireConverter().toItem(vo.getCompteBudgitaireVo()));
            item.setDetaillesBudget(new DetaillesBudgetConverter().toItem(vo.getDetaillesBudgetVo()));
            //item.setBudgetEntiteAdministratif(new BudgetEntiteAdministratifConverter().toItem(vo.getBudgetEntiteAdministratifVo()));
            return item;
        }
    }

    @Override
    public BudgetCompteBudgitaireVo toVo(BudgetCompteBudgitaire item) {
        if (item == null) {
            return null;
        } else {
            BudgetCompteBudgitaireVo vo = new BudgetCompteBudgitaireVo();
            vo.setId(item.getId());
            vo.setCompteBudgitaireVo(new CompteBudgitaireConverter().toVo(item.getCompteBudgitaire()));
            vo.setDetaillesBudgetVo(new DetaillesBudgetConverter().toVo(item.getDetaillesBudget()));
            vo.setBudgetEntiteAdministratifVo(new BudgetEntiteAdministratifConverter().toVo(item.getBudgetEntiteAdministratif()));
            return vo;
        }
    }

}
