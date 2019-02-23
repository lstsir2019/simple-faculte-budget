/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.rest.vo;

import java.util.List;



/**
 *
 * @author AMINE
 */
public class BudgetFaculteVo {
    private Long id;
    private List<BudgetSousProjetVo> budgetSousProjetVo;
    private String annee;
    private DetaillesBudgetVo detaillesBudgetVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BudgetSousProjetVo> getBudgetSousProjetVo() {
        return budgetSousProjetVo;
    }

    public void setBudgetSousProjetVo(List<BudgetSousProjetVo> budgetSousProjetVo) {
        this.budgetSousProjetVo = budgetSousProjetVo;
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
