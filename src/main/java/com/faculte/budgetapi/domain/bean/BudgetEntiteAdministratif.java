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
public class BudgetEntiteAdministratif implements Serializable {

    @OneToMany(cascade = {CascadeType.ALL})
    private List<BudgetCompteBudgitaire> budgeCompteBudgitaires;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String referenceEntiteAdministratif ;
    @ManyToOne(cascade = {CascadeType.ALL})
    private DetaillesBudget detaillesBudget;
    @ManyToOne(cascade = {CascadeType.ALL})
    private BudgetSousProjet budgetSousProjet ;
    @JsonIgnore
    public List<BudgetCompteBudgitaire> getBudgeCompteBudgitaires() {
        return budgeCompteBudgitaires;
    }
     @JsonSetter
    public void setBudgeCompteBudgitaires(List<BudgetCompteBudgitaire> budgeCompteBudgitaires) {
        this.budgeCompteBudgitaires = budgeCompteBudgitaires;
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

    public BudgetSousProjet getBudgetSousProjet() {
        return budgetSousProjet;
    }

    public void setBudgetSousProjet(BudgetSousProjet budgetSousProjet) {
        this.budgetSousProjet = budgetSousProjet;
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
        if (!(object instanceof BudgetEntiteAdministratif)) {
            return false;
        }
        BudgetEntiteAdministratif other = (BudgetEntiteAdministratif) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.faculte.budget.bean.BudgetEntiteAdmin[ id=" + id + " ]";
    }
    
}
