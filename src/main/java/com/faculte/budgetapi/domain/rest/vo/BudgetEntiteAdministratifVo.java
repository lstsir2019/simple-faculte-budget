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
public class BudgetEntiteAdministratifVo {

    private Long id;
    private String referenceEntiteAdministratif;
    private DetaillesBudgetVo detaillesBudgetVo;
    private BudgetSousProjetVo budgetSousProjetVo;
    private List<BudgetCompteBudgitaireVo> budgetCompteBudgitaireVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceEntiteAdministratif() {
        return referenceEntiteAdministratif;
    }

    public void setReferenceEntiteAdministratif(String referenceEntiteAdministratif) {
        this.referenceEntiteAdministratif = referenceEntiteAdministratif;
    }

    public DetaillesBudgetVo getDetaillesBudgetVo() {
        return detaillesBudgetVo;
    }

    public void setDetaillesBudgetVo(DetaillesBudgetVo detaillesBudgetVo) {
        this.detaillesBudgetVo = detaillesBudgetVo;
    }

    public BudgetSousProjetVo getBudgetSousProjetVo() {
        return budgetSousProjetVo;
    }

    public void setBudgetSousProjetVo(BudgetSousProjetVo budgetSousProjetVo) {
        this.budgetSousProjetVo = budgetSousProjetVo;
    }

    public List<BudgetCompteBudgitaireVo> getBudgetCompteBudgitaireVo() {
        return budgetCompteBudgitaireVo;
    }

    public void setBudgetCompteBudgitaireVo(List<BudgetCompteBudgitaireVo> budgetCompteBudgitaireVo) {
        this.budgetCompteBudgitaireVo = budgetCompteBudgitaireVo;
    }

}
