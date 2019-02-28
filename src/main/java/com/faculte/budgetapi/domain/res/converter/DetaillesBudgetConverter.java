/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.res.converter;

import com.faculte.budgetapi.domain.bean.BudgetEntiteAdministratif;
import com.faculte.budgetapi.domain.bean.DetaillesBudget;
import com.faculte.budgetapi.domain.rest.vo.DetaillesBudgetVo;
import com.fst.commandeapiv4.common.util.NumberUtil;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class DetaillesBudgetConverter extends AbstractConverter<DetaillesBudget, DetaillesBudgetVo> {

    @Override
    public DetaillesBudget toItem(DetaillesBudgetVo vo) {
        if (vo == null) {
            return null;
        } else {
            DetaillesBudget item = new DetaillesBudget();
            item.setId(vo.getId());
            item.setAntecedent(NumberUtil.toDouble(vo.getAntecedent()));
            item.setCreditOuvertEstimatif(NumberUtil.toDouble(vo.getCreditOuvertEstimatif()));
            item.setCreditOuvertReel(NumberUtil.toDouble(vo.getCreditOuvertReel()));
            item.setEngageNonPaye(NumberUtil.toDouble(vo.getEngageNonPaye()));
            item.setEngagePaye(NumberUtil.toDouble(vo.getEngagePaye()));
            item.setReliquatEstimatif(NumberUtil.toDouble(vo.getReliquatEstimatif()));
            item.setReliquatNonPayReel(NumberUtil.toDouble(vo.getReliquatNonPayReel()));
            item.setReliquatNonPayeEstimatif(NumberUtil.toDouble(vo.getReliquatNonPayeEstimatif()));
            item.setReliquatPayeEstimatif(NumberUtil.toDouble(vo.getReliquatPayeEstimatif()));
            item.setReliquatPayereel(NumberUtil.toDouble(vo.getReliquatPayereel()));
            item.setReliquatReel(NumberUtil.toDouble(vo.getReliquatReel()));
            return item;
        }
    }

    @Override
    public DetaillesBudgetVo toVo(DetaillesBudget item) {
        if (item == null) {
            return null;
        } else {
            DetaillesBudgetVo vo = new DetaillesBudgetVo();
            vo.setId(item.getId());
            vo.setAntecedent(NumberUtil.toString(item.getAntecedent()));
            vo.setCreditOuvertEstimatif(NumberUtil.toString(item.getCreditOuvertEstimatif()));
            vo.setCreditOuvertReel(NumberUtil.toString(item.getCreditOuvertReel()));
            vo.setEngageNonPaye(NumberUtil.toString(item.getEngageNonPaye()));
            vo.setEngagePaye(NumberUtil.toString(item.getEngagePaye()));
            vo.setReliquatEstimatif(NumberUtil.toString(item.getReliquatEstimatif()));
            vo.setReliquatNonPayReel(NumberUtil.toString(item.getReliquatNonPayReel()));
            vo.setReliquatNonPayeEstimatif(NumberUtil.toString(item.getReliquatNonPayeEstimatif()));
            vo.setReliquatPayeEstimatif(NumberUtil.toString(item.getReliquatPayeEstimatif()));
            vo.setReliquatPayereel(NumberUtil.toString(item.getReliquatPayereel()));
            vo.setReliquatReel(NumberUtil.toString(item.getReliquatReel()));
            return vo;
        }

    }

}
