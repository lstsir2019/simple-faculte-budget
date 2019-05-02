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
    private DetaillesBudgetVo detaillesBudgetVo;
    private String referenceSousProjet;
    private String libelle;
    private List<BudgetCompteBudgitaireVo> budgetCompteBudgitaireVos;
    private BudgetProjetVo budgetProjetVo;

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

    public String getReferenceSousProjet() {
        return referenceSousProjet;
    }

    public void setReferenceSousProjet(String referenceSousProjet) {
        this.referenceSousProjet = referenceSousProjet;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<BudgetCompteBudgitaireVo> getBudgetCompteBudgitaireVos() {
        return budgetCompteBudgitaireVos;
    }

    public void setBudgetCompteBudgitaireVos(List<BudgetCompteBudgitaireVo> budgetCompteBudgitaireVos) {
        this.budgetCompteBudgitaireVos = budgetCompteBudgitaireVos;
    }

    public BudgetProjetVo getBudgetProjetVo() {
        return budgetProjetVo;
    }

    public void setBudgetProjetVo(BudgetProjetVo budgetProjetVo) {
        this.budgetProjetVo = budgetProjetVo;
    }

}
