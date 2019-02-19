/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.budgetapi.domain.rest.vo;

/**
 *
 * @author AMINE
 */
public class DetaillesBudgetVo {
     private Long id;
    private String antecedent;
    private String creditOuvertEstimatif;
    private String creditOuvertReel;
    private String reliquatEstimatif;
    private String reliquatReel;
    private String engageNonPaye;
    private String engagePaye;
    private String reliquatPayeEstimatif;
    private String reliquatPayereel;
    private String reliquatNonPayeEstimatif;
    private String reliquatNonPayReel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAntecedent() {
        return antecedent;
    }

    public void setAntecedent(String antecedent) {
        this.antecedent = antecedent;
    }

    public String getCreditOuvertEstimatif() {
        return creditOuvertEstimatif;
    }

    public void setCreditOuvertEstimatif(String creditOuvertEstimatif) {
        this.creditOuvertEstimatif = creditOuvertEstimatif;
    }

    public String getCreditOuvertReel() {
        return creditOuvertReel;
    }

    public void setCreditOuvertReel(String creditOuvertReel) {
        this.creditOuvertReel = creditOuvertReel;
    }

    public String getReliquatEstimatif() {
        return reliquatEstimatif;
    }

    public void setReliquatEstimatif(String reliquatEstimatif) {
        this.reliquatEstimatif = reliquatEstimatif;
    }

    public String getReliquatReel() {
        return reliquatReel;
    }

    public void setReliquatReel(String reliquatReel) {
        this.reliquatReel = reliquatReel;
    }

    public String getEngageNonPaye() {
        return engageNonPaye;
    }

    public void setEngageNonPaye(String engageNonPaye) {
        this.engageNonPaye = engageNonPaye;
    }

    public String getEngagePaye() {
        return engagePaye;
    }

    public void setEngagePaye(String engagePaye) {
        this.engagePaye = engagePaye;
    }

    public String getReliquatPayeEstimatif() {
        return reliquatPayeEstimatif;
    }

    public void setReliquatPayeEstimatif(String reliquatPayeEstimatif) {
        this.reliquatPayeEstimatif = reliquatPayeEstimatif;
    }

    public String getReliquatPayereel() {
        return reliquatPayereel;
    }

    public void setReliquatPayereel(String reliquatPayereel) {
        this.reliquatPayereel = reliquatPayereel;
    }

    public String getReliquatNonPayeEstimatif() {
        return reliquatNonPayeEstimatif;
    }

    public void setReliquatNonPayeEstimatif(String reliquatNonPayeEstimatif) {
        this.reliquatNonPayeEstimatif = reliquatNonPayeEstimatif;
    }

    public String getReliquatNonPayReel() {
        return reliquatNonPayReel;
    }

    public void setReliquatNonPayReel(String reliquatNonPayReel) {
        this.reliquatNonPayReel = reliquatNonPayReel;
    }
    
    
}
