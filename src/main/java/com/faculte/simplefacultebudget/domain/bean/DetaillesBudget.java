/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author AMINE
 */
@Entity
public class DetaillesBudget implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double antecedent;
    private double creditOuvertEstimatif;
    private double creditOuvertReel;
    private double reliquatEstimatif;
    private double reliquatReel;
    private double engageNonPaye;
    private double engagePaye;
    private double reliquatPayeEstimatif;
    private double reliquatPayereel;
    private double reliquatNonPayeEstimatif;
    private double reliquatNonPayReel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAntecedent() {
        return antecedent;
    }

    public void setAntecedent(double antecedent) {
        this.antecedent = antecedent;
    }

    public double getCreditOuvertEstimatif() {
        return creditOuvertEstimatif;
    }

    public void setCreditOuvertEstimatif(double creditOuvertEstimatif) {
        this.creditOuvertEstimatif = creditOuvertEstimatif;
    }

    public double getCreditOuvertReel() {
        return creditOuvertReel;
    }

    public void setCreditOuvertReel(double creditOuvertReel) {
        this.creditOuvertReel = creditOuvertReel;
    }

    public double getReliquatEstimatif() {
        return reliquatEstimatif;
    }

    public void setReliquatEstimatif(double reliquatEstimatif) {
        this.reliquatEstimatif = reliquatEstimatif;
    }

    public double getReliquatReel() {
        return reliquatReel;
    }

    public void setReliquatReel(double reliquatReel) {
        this.reliquatReel = reliquatReel;
    }

    public double getEngageNonPaye() {
        return engageNonPaye;
    }

    public void setEngageNonPaye(double engageNonPaye) {
        this.engageNonPaye = engageNonPaye;
    }

    public double getEngagePaye() {
        return engagePaye;
    }

    public void setEngagePaye(double engagePaye) {
        this.engagePaye = engagePaye;
    }

    public double getReliquatPayeEstimatif() {
        return reliquatPayeEstimatif;
    }

    public void setReliquatPayeEstimatif(double reliquatPayeEstimatif) {
        this.reliquatPayeEstimatif = reliquatPayeEstimatif;
    }

    public double getReliquatPayereel() {
        return reliquatPayereel;
    }

    public void setReliquatPayereel(double reliquatPayereel) {
        this.reliquatPayereel = reliquatPayereel;
    }

    public double getReliquatNonPayeEstimatif() {
        return reliquatNonPayeEstimatif;
    }

    public void setReliquatNonPayeEstimatif(double reliquatNonPayeEstimatif) {
        this.reliquatNonPayeEstimatif = reliquatNonPayeEstimatif;
    }

    public double getReliquatNonPayReel() {
        return reliquatNonPayReel;
    }

    public void setReliquatNonPayReel(double reliquatNonPayReel) {
        this.reliquatNonPayReel = reliquatNonPayReel;
    }

    
}
