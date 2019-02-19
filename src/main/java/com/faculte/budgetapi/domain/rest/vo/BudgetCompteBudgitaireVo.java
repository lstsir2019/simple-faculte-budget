/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.rest.vo;



/**
 *
 * @author AMINE
 */
public class BudgetCompteBudgitaireVo {
     private Long id;
    private DetaillesBudgetVo detaillesBudgetVo;
    private BudgetEntiteAdministratifVo budgetEntiteAdministratifVo;
    private CompteBudgitaireVo compteBudgitaireVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetaillesBudgetVo getDetaillesBudgetVo() {
        return detaillesBudgetVo;
    }

    public void setDetaillesBudgetVo(DetaillesBudgetVo detaillesBudgetVo) {
        this.detaillesBudgetVo = detaillesBudgetVo;
    }

    public BudgetEntiteAdministratifVo getBudgetEntiteAdministratifVo() {
        return budgetEntiteAdministratifVo;
    }

    public void setBudgetEntiteAdministratifVo(BudgetEntiteAdministratifVo budgetEntiteAdministratifVo) {
        this.budgetEntiteAdministratifVo = budgetEntiteAdministratifVo;
    }

    public CompteBudgitaireVo getCompteBudgitaireVo() {
        return compteBudgitaireVo;
    }

    public void setCompteBudgitaireVo(CompteBudgitaireVo compteBudgitaireVo) {
        this.compteBudgitaireVo = compteBudgitaireVo;
    }
    
    
}
