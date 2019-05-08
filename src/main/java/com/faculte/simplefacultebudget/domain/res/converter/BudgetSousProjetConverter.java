/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.res.converter;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetProjetVo;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetSousProjetVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class BudgetSousProjetConverter extends AbstractConverter<BudgetSousProjet, BudgetSousProjetVo> {

    @Override
    public BudgetSousProjet toItem(BudgetSousProjetVo vo) {
        if (vo == null) {
            return null;
        } else {
            BudgetSousProjet item = new BudgetSousProjet();
            item.setId(vo.getId());
            item.setReferenceSousProjet(vo.getReferenceSousProjet());
            item.setBudgetProjet(new BudgetProjetConverter().toItem(vo.getBudgetProjetVo()));
            item.setDetaillesBudget(new DetaillesBudgetConverter().toItem(vo.getDetaillesBudgetVo()));
            item.setBudgetCompteBudgitaires(new BudgetCompteBudgitaireConverter().toItem(vo.getBudgetCompteBudgitaireVos()));
            //item.setBudgetFaculte(new BudgetFaculteConverter().toItem(vo.getBudgetFaculteVo()));
            return item;
        }
    }

    @Override
    public BudgetSousProjetVo toVo(BudgetSousProjet item) {
        if (item == null) {
            return null;
        } else {
            BudgetSousProjetVo vo = new BudgetSousProjetVo();
            vo.setId(item.getId());
            vo.setReferenceSousProjet(item.getReferenceSousProjet());
            vo.setDetaillesBudgetVo(new DetaillesBudgetConverter().toVo(item.getDetaillesBudget()));
            vo.setBudgetProjetVo(new BudgetProjetConverter().toVo(item.getBudgetProjet()));
            return vo;
        }
    }

}
