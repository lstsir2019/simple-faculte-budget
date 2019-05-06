/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author AMINE
 */
@Entity
public class BudgetFaculte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int annee;
    @OneToOne(cascade = {CascadeType.ALL})
    private DetaillesBudget detaillesBudget;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "budgetFaculte", fetch = FetchType.LAZY)
    private List<BudgetProjet> budgetProjets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public DetaillesBudget getDetaillesBudget() {
        return detaillesBudget;
    }

    public void setDetaillesBudget(DetaillesBudget detaillesBudget) {
        this.detaillesBudget = detaillesBudget;
    }

    public List<BudgetProjet> getBudgetProjets() {
        return budgetProjets;
    }

    public void setBudgetProjets(List<BudgetProjet> budgetProjets) {
        this.budgetProjets = budgetProjets;
    }

}
