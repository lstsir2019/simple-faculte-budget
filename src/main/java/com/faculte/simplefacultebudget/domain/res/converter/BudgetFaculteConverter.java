/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.res.converter;

import com.faculte.simplefacultebudget.domain.bean.BudgetFaculte;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetFaculteVo;
import com.faculte.simplefacultebudget.domain.common.util.NumberUtil;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class BudgetFaculteConverter extends AbstractConverter<BudgetFaculte, BudgetFaculteVo> {

    @Override
    public BudgetFaculte toItem(BudgetFaculteVo vo) {
        if (vo == null) {
            return null;
        } else {
            BudgetFaculte item = new BudgetFaculte();
            item.setAnnee(NumberUtil.toInteger(vo.getAnnee()));
            item.setId(vo.getId());
            item.setBudgetSousProjets(new BudgetSousProjetConverter().toItem(vo.getBudgetSousProjetVo()));
            item.setDetaillesBudget(new DetaillesBudgetConverter().toItem(vo.getDetaillesBudgetVo()));

            return item;
        }
    }
    @Override
    public BudgetFaculteVo toVo(BudgetFaculte item) {
        if (item == null) {
            return null;
        } else {
            BudgetFaculteVo vo = new BudgetFaculteVo();
            vo.setAnnee((item.getAnnee()+""));
            vo.setId(item.getId());
            vo.setDetaillesBudgetVo(new DetaillesBudgetConverter().toVo(item.getDetaillesBudget()));
            return vo;
        }
    }
}
