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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author AMINE
 */
@Entity
public class BudgetCompteBudgitaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
   
    @ManyToOne(cascade = {CascadeType.ALL})
    private DetaillesBudget detaillesBudget;

    @ManyToOne
    private CompteBudgitaire compteBudgitaire;
   
    @ManyToOne
    private BudgetSousProjet budgetSousProjet;

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

   

    public DetaillesBudget getDetaillesBudget() {
        return detaillesBudget;
    }

    public void setDetaillesBudget(DetaillesBudget detaillesBudget) {
        this.detaillesBudget = detaillesBudget;
    }

    public CompteBudgitaire getCompteBudgitaire() {
        return compteBudgitaire;
    }

    public void setCompteBudgitaire(CompteBudgitaire compteBudgitaire) {
        this.compteBudgitaire = compteBudgitaire;
    }

    public BudgetSousProjet getBudgetSousProjet() {
        return budgetSousProjet;
    }

    public void setBudgetSousProjet(BudgetSousProjet budgetSousProjet) {
        this.budgetSousProjet = budgetSousProjet;
    }




}
