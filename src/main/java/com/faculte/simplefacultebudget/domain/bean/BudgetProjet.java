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
public class BudgetProjet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String referenceEntiteAdministratif;
    @ManyToOne(cascade = {CascadeType.ALL})
    private DetaillesBudget detaillesBudget;
    
    @OneToMany(mappedBy = "budgetProjet")
    private List<BudgetSousProjet> budgetSousProjets;

    
    public BudgetProjet() {
    }

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
