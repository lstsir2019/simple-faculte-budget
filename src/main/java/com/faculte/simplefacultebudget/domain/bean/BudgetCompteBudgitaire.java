/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.bean;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    private String referenceCompteBudgitaire;
    @ManyToOne(cascade = {CascadeType.ALL})
    private DetaillesBudget detaillesBudget;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private BudgetEntiteAdministratif budgetEntiteAdministratif;
    @OneToOne(cascade = {CascadeType.ALL})
    private CompteBudgitaire compteBudgitaire;

    public String generateCode(long index) {
        String gerenerateCode = "";
        String referenceEA = this.budgetEntiteAdministratif.getReferenceEntiteAdministratif();
        String referenceSP = this.budgetEntiteAdministratif.getBudgetSousProjet().getReferenceSousProjet();
        int annee = (this.budgetEntiteAdministratif.getBudgetSousProjet().getBudgetFaculte().getAnnee());
        gerenerateCode += annee + "-" + referenceSP.substring(0, 4) + "-" + referenceEA.substring(0, 4) + "-" + index;
        return gerenerateCode;
    }

    public String getReferenceCompteBudgitaire() {
        return referenceCompteBudgitaire;
    }

    public void setReferenceCompteBudgitaire(String referenceCompteBudgitaire) {
        this.referenceCompteBudgitaire = referenceCompteBudgitaire;
    }

    public CompteBudgitaire getCompteBudgitaire() {
        return compteBudgitaire;
    }

    public void setCompteBudgitaire(CompteBudgitaire compteBudgitaire) {
        this.compteBudgitaire = compteBudgitaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetaillesBudget getDetaillesBudget() {
        return detaillesBudget;
    }

    public void setDetaillesBudget(DetaillesBudget detaillesBudget) {
        this.detaillesBudget = detaillesBudget;
    }

    public BudgetEntiteAdministratif getBudgetEntiteAdministratif() {
        return budgetEntiteAdministratif;
    }

    public void setBudgetEntiteAdministratif(BudgetEntiteAdministratif budgetEntiteAdministratif) {
        this.budgetEntiteAdministratif = budgetEntiteAdministratif;
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
        if (!(object instanceof BudgetCompteBudgitaire)) {
            return false;
        }
        BudgetCompteBudgitaire other = (BudgetCompteBudgitaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.faculte.budget.bean.BudgeCompteBudgitaire[ id=" + id + " ]";
    }

}
