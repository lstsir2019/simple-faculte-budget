/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
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
    @ManyToOne(cascade = {CascadeType.ALL})
    private DetaillesBudget detaillesBudget;
    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "budgetFaculte",fetch=FetchType.LAZY)
    private List<BudgetSousProjet> budgetSousProjets;

    @JsonIgnore
    public List<BudgetSousProjet> getBudgetSousProjets() {
        return budgetSousProjets;
    }

    @JsonSetter
    public void setBudgetSousProjets(List<BudgetSousProjet> budgetSousProjets) {
        this.budgetSousProjets = budgetSousProjets;
    }

    public DetaillesBudget getDetaillesBudget() {
        return detaillesBudget;
    }

    public void setDetaillesBudget(DetaillesBudget detaillesBudget) {
        this.detaillesBudget = detaillesBudget;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BudgetFaculte)) {
            return false;
        }
        BudgetFaculte other = (BudgetFaculte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.faculte.budget.bean.BudgetFaculte[ id=" + id + " ]";
    }

}
