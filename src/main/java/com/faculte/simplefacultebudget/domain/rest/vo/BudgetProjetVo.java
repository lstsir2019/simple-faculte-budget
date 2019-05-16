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
public class BudgetProjetVo {

    private Long id;
    private String referenceProjet;
    private DetaillesBudgetVo detaillesBudgetVo;
    private BudgetFaculteVo budgetFaculteVo;
    private List<BudgetSousProjetVo> budgetSousProjetVos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceProjet() {
        return referenceProjet;
    }

    public void setReferenceProjet(String referenceProjet) {
        this.referenceProjet = referenceProjet;
    }

    public List<BudgetSousProjetVo> getBudgetSousProjetVos() {
        return budgetSousProjetVos;
    }

    public void setBudgetSousProjetVos(List<BudgetSousProjetVo> budgetSousProjetVos) {
        this.budgetSousProjetVos = budgetSousProjetVos;
    }

    public DetaillesBudgetVo getDetaillesBudgetVo() {
        return detaillesBudgetVo;
    }

    public void setDetaillesBudgetVo(DetaillesBudgetVo detaillesBudgetVo) {
        this.detaillesBudgetVo = detaillesBudgetVo;
    }

    public BudgetFaculteVo getBudgetFaculteVo() {
        return budgetFaculteVo;
    }

    public void setBudgetFaculteVo(BudgetFaculteVo budgetFaculteVo) {
        this.budgetFaculteVo = budgetFaculteVo;
    }

}
