/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
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
public class CompteBudgitaire implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String libelle;
    
    @OneToMany(mappedBy = "compteBudgitaire")
    private List<BudgetCompteBudgitaire> budgetCompteBudgitaires;
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<BudgetCompteBudgitaire> getBudgetCompteBudgitaires() {
        return budgetCompteBudgitaires;
    }

    public void setBudgetCompteBudgitaires(List<BudgetCompteBudgitaire> budgetCompteBudgitaires) {
        this.budgetCompteBudgitaires = budgetCompteBudgitaires;
    }

    @Override
    public String toString() {
        return "CompteBudgitaire{" + "id=" + id + ", code=" + code + ", libelle=" + libelle + ", budgetCompteBudgitaires=" + budgetCompteBudgitaires + '}';
    }

}
