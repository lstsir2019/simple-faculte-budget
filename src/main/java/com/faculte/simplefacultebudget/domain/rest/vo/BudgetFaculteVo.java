/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest.vo;

import java.util.List;

/**
 *
 * @author AMINE
 */
public class BudgetFaculteVo {

    private Long id;
    private List<BudgetProjetVo> budgetProjetVos;
    private String annee;
    private DetaillesBudgetVo detaillesBudgetVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BudgetProjetVo> getBudgetProjetVos() {
        return budgetProjetVos;
    }

    public void setBudgetProjetVos(List<BudgetProjetVo> budgetProjetVos) {
        this.budgetProjetVos = budgetProjetVos;
    }



    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public DetaillesBudgetVo getDetaillesBudgetVo() {
        return detaillesBudgetVo;
    }

    public void setDetaillesBudgetVo(DetaillesBudgetVo detaillesBudgetVo) {
        this.detaillesBudgetVo = detaillesBudgetVo;
    }

}
