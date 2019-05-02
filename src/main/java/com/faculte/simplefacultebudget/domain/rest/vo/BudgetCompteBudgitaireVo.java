/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest.vo;

/**
 *
 * @author AMINE
 */
public class BudgetCompteBudgitaireVo {

    private Long id;
    private String reference;
    private DetaillesBudgetVo detaillesBudgetVo;
    private CompteBudgitaireVo compteBudgitaireVo;
    private BudgetSousProjetVo budgetSousProjetVo;

    public BudgetCompteBudgitaireVo() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public DetaillesBudgetVo getDetaillesBudgetVo() {
        return detaillesBudgetVo;
    }

    public void setDetaillesBudgetVo(DetaillesBudgetVo detaillesBudgetVo) {
        this.detaillesBudgetVo = detaillesBudgetVo;
    }

    public CompteBudgitaireVo getCompteBudgitaireVo() {
        return compteBudgitaireVo;
    }

    public void setCompteBudgitaireVo(CompteBudgitaireVo compteBudgitaireVo) {
        this.compteBudgitaireVo = compteBudgitaireVo;
    }

    public BudgetSousProjetVo getBudgetSousProjetVo() {
        return budgetSousProjetVo;
    }

    public void setBudgetSousProjetVo(BudgetSousProjetVo budgetSousProjetVo) {
        this.budgetSousProjetVo = budgetSousProjetVo;
    }


}
