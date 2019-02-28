/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.bean;

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
public class BudgetSousProjet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String referenceSousProjet;
    @ManyToOne(cascade = {CascadeType.ALL})
    private DetaillesBudget detaillesBudget;
    @ManyToOne(cascade = {CascadeType.ALL})
    private BudgetFaculte budgetFaculte;
    @OneToMany(mappedBy = "budgetSousProjet", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<BudgetEntiteAdministratif> budgetEntiteAdmins;

    @JsonIgnore
    public List<BudgetEntiteAdministratif> getBudgetEntiteAdmins() {
        return budgetEntiteAdmins;
    }

    @JsonSetter
    public void setBudgetEntiteAdmins(List<BudgetEntiteAdministratif> budgetEntiteAdmins) {
        this.budgetEntiteAdmins = budgetEntiteAdmins;
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

    public BudgetFaculte getBudgetFaculte() {
        return budgetFaculte;
    }

    public void setBudgetFaculte(BudgetFaculte budgetFaculte) {
        this.budgetFaculte = budgetFaculte;
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
        if (!(object instanceof BudgetSousProjet)) {
            return false;
        }
        BudgetSousProjet other = (BudgetSousProjet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.faculte.budget.bean.BudgetSousProjet[ id=" + id + " ]";
    }

}
