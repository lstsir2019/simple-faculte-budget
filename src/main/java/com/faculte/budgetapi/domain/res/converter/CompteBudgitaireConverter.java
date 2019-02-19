/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.res.converter;

import com.faculte.budgetapi.domain.bean.BudgetEntiteAdministratif;
import com.faculte.budgetapi.domain.bean.CompteBudgitaire;
import com.faculte.budgetapi.domain.rest.vo.CompteBudgitaireVo;
import org.springframework.stereotype.Component;

/**
 *
 * @author AMINE
 */
@Component
public class CompteBudgitaireConverter extends AbstractConverter<CompteBudgitaire, CompteBudgitaireVo> {

    @Override
    public CompteBudgitaire toItem(CompteBudgitaireVo vo) {

        if (vo == null) {
            return null;
        } else {
            CompteBudgitaire item = new CompteBudgitaire();
            item.setId(vo.getId());
            item.setCode(vo.getCode());
            item.setLibelle(vo.getLibelle());
            return item;
        }
    }

    @Override
    public CompteBudgitaireVo toVo(CompteBudgitaire item) {
        if (item == null) {
            return null;
        } else {
            CompteBudgitaireVo vo = new CompteBudgitaireVo();
            vo.setId(item.getId());
            vo.setCode(item.getCode());
            vo.setLibelle(item.getLibelle());
            return vo;
        }
    }

}
