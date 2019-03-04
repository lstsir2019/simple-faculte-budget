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
public class BudgetSousProjetVo {

    private Long id;
    private List<BudgetEntiteAdministratifVo> budgetEntiteAdministratifVo;
    private DetaillesBudgetVo detaillesBudgetVo;
    private BudgetFaculteVo budgetFaculteVo;
    private String referenceSousProjet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BudgetEntiteAdministratifVo> getBudgetEntiteAdministratifVo() {
        return budgetEntiteAdministratifVo;
    }

    public void setBudgetEntiteAdministratifVo(List<BudgetEntiteAdministratifVo> budgetEntiteAdministratifVo) {
        this.budgetEntiteAdministratifVo = budgetEntiteAdministratifVo;
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

    public String getReferenceSousProjet() {
        return referenceSousProjet;
    }

    public void setReferenceSousProjet(String referenceSousProjet) {
        this.referenceSousProjet = referenceSousProjet;
    }

}
