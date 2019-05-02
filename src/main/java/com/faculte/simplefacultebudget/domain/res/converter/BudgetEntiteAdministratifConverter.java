/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.res.converter;

import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetEntiteAdministratifVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class BudgetEntiteAdministratifConverter extends AbstractConverter<BudgetProjet, BudgetEntiteAdministratifVo> {

    @Override
    public BudgetProjet toItem(BudgetEntiteAdministratifVo vo) {
        if (vo == null) {
            return null;
        } else {
            BudgetProjet item = new BudgetProjet();
            item.setId(vo.getId());
            item.setReferenceEntiteAdministratif(vo.getReferenceEntiteAdministratif());
            item.setBudgeCompteBudgitaires(new BudgetCompteBudgitaireConverter().toItem(vo.getBudgetCompteBudgitaireVo()));
            item.setDetaillesBudget(new DetaillesBudgetConverter().toItem(vo.getDetaillesBudgetVo()));
            //item.setBudgetSousProjet(new BudgetSousProjetConverter().toItem(vo.getBudgetSousProjetVo()));
            return item;
        }
    }

    @Override
    public BudgetEntiteAdministratifVo toVo(BudgetProjet item) {
        if (item == null) {
            return null;
        } else {
            BudgetEntiteAdministratifVo vo = new BudgetEntiteAdministratifVo();
            vo.setId(item.getId());
            vo.setReferenceEntiteAdministratif(item.getReferenceEntiteAdministratif());
            vo.setDetaillesBudgetVo(new DetaillesBudgetConverter().toVo(item.getDetaillesBudget()));
            vo.setBudgetSousProjetVo(new BudgetSousProjetConverter().toVo(item.getBudgetSousProjet()));
            return vo;
        }
    }

}
