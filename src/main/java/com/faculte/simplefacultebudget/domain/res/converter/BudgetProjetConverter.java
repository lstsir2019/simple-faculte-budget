/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.res.converter;

import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import com.faculte.simplefacultebudget.domain.bean.BudgetSousProjet;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetFaculteVo;
import com.faculte.simplefacultebudget.domain.rest.vo.BudgetProjetVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class BudgetProjetConverter extends AbstractConverter<BudgetProjet, BudgetProjetVo> {
    
    @Override
    public BudgetProjet toItem(BudgetProjetVo vo) {
        if (vo == null) {
            return null;
        } else {
            BudgetProjet item = new BudgetProjet();
            item.setId(vo.getId());
            item.setReferenceProjet(vo.getReferenceProjet());
            item.setLibelle(vo.getLibelle());
            item.setBudgetSousProjets(new BudgetSousProjetConverter().toItem(vo.getBudgetSousProjetVos()));
            item.setDetaillesBudget(new DetaillesBudgetConverter().toItem(vo.getDetaillesBudgetVo()));
            //item.setBudgetSousProjet(new BudgetSousProjetConverter().toItem(vo.getBudgetSousProjetVo()));
            return item;
        }
    }
    
    @Override
    public BudgetProjetVo toVo(BudgetProjet item) {
        if (item == null) {
            return null;
        } else {
            BudgetProjetVo vo = new BudgetProjetVo();
            vo.setId(item.getId());
            vo.setReferenceProjet(item.getReferenceProjet());
            vo.setDetaillesBudgetVo(new DetaillesBudgetConverter().toVo(item.getDetaillesBudget()));
            vo.setBudgetFaculteVo(new BudgetFaculteConverter().toVo(item.getBudgetFaculte()));
            //  vo.setBudgetSousProjetVos(new BudgetSousProjetConverter().toVo(item.getBudgetSousProjets()));
            return vo;
        }
    }
    
}
