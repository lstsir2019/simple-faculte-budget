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
public class BudgetProjet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String referenceProjet;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private DetaillesBudget detaillesBudget;

    @OneToMany(mappedBy = "budgetProjet", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<BudgetSousProjet> budgetSousProjets;

    @ManyToOne
    private BudgetFaculte budgetFaculte;

    public BudgetProjet() {
    }

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

    public BudgetFaculte getBudgetFaculte() {
        return budgetFaculte;
    }

    public void setBudgetFaculte(BudgetFaculte budgetFaculte) {
        this.budgetFaculte = budgetFaculte;
    }

    public DetaillesBudget getDetaillesBudget() {
        return detaillesBudget;
    }

    public void setDetaillesBudget(DetaillesBudget detaillesBudget) {
        this.detaillesBudget = detaillesBudget;
    }

    public List<BudgetSousProjet> getBudgetSousProjets() {
        return budgetSousProjets;
    }

    public void setBudgetSousProjets(List<BudgetSousProjet> budgetSousProjets) {
        this.budgetSousProjets = budgetSousProjets;
    }

}
