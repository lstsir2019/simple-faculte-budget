/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.res.converter;

import com.faculte.budgetapi.domain.bean.BudgetEntiteAdministratif;
import com.faculte.budgetapi.domain.bean.BudgetSousProjet;
import com.faculte.budgetapi.domain.rest.vo.BudgetEntiteAdministratifVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class BudgetEntiteAdministratifConverter extends AbstractConverter<BudgetEntiteAdministratif, BudgetEntiteAdministratifVo> {

    @Override
    public BudgetEntiteAdministratif toItem(BudgetEntiteAdministratifVo vo) {
        if (vo == null) {
            return null;
        } else {
            BudgetEntiteAdministratif item = new BudgetEntiteAdministratif();
            item.setId(vo.getId());
            item.setReferenceEntiteAdministratif(vo.getReferenceEntiteAdministratif());
            item.setBudgeCompteBudgitaires(new BudgetCompteBudgitaireConverter().toItem(vo.getBudgetCompteBudgitaireVo()));
            item.setDetaillesBudget(new DetaillesBudgetConverter().toItem(vo.getDetaillesBudgetVo()));
            item.setBudgetSousProjet(new BudgetSousProjetConverter().toItem(vo.getBudgetSousProjetVo()));
            return item;
        }
    }

    @Override
    public BudgetEntiteAdministratifVo toVo(BudgetEntiteAdministratif item) {
        if (item == null) {
            return null;
        } else {
            BudgetEntiteAdministratifVo vo = new BudgetEntiteAdministratifVo();
            vo.setId(vo.getId());
            vo.setReferenceEntiteAdministratif(vo.getReferenceEntiteAdministratif());
            vo.setBudgetCompteBudgitaireVo(new BudgetCompteBudgitaireConverter().toVo(item.getBudgeCompteBudgitaires()));
            vo.setDetaillesBudgetVo(new DetaillesBudgetConverter().toVo(item.getDetaillesBudget()));
            vo.setBudgetSousProjetVo(new BudgetSousProjetConverter().toVo(item.getBudgetSousProjet()));

            return vo;
        }
    }

}
