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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author AMINE
 */
@Entity
public class BudgetSousProjet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String referenceSousProjet;

    @OneToOne(cascade = {CascadeType.ALL})
    private DetaillesBudget detaillesBudget;

    @OneToMany(mappedBy = "budgetSousProjet", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<BudgetCompteBudgitaire> budgetCompteBudgitaires;

    @ManyToOne
    private BudgetProjet budgetProjet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceSousProjet() {
        return referenceSousProjet;
    }

    public void setReferenceSousProjet(String referenceSousProjet) {
        this.referenceSousProjet = referenceSousProjet;
    }


    public DetaillesBudget getDetaillesBudget() {
        return detaillesBudget;
    }

    public void setDetaillesBudget(DetaillesBudget detaillesBudget) {
        this.detaillesBudget = detaillesBudget;
    }

    public List<BudgetCompteBudgitaire> getBudgetCompteBudgitaires() {
        return budgetCompteBudgitaires;
    }

    public void setBudgetCompteBudgitaires(List<BudgetCompteBudgitaire> budgetCompteBudgitaires) {
        this.budgetCompteBudgitaires = budgetCompteBudgitaires;
    }

    public BudgetProjet getBudgetProjet() {
        return budgetProjet;
    }

    public void setBudgetProjet(BudgetProjet budgetProjet) {
        this.budgetProjet = budgetProjet;
    }

}
