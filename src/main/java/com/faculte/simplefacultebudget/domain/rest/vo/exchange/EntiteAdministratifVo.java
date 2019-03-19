/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest.vo.exchange;



/**
 *
 * @author abdou
 */
public class EntiteAdministratifVo {

    private Long id;
    private String referenceEntiteAdministratif;
    private SousProjetVo sousProjetVo;

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

    public SousProjetVo getSousProjetVo() {
        return sousProjetVo;
    }

    public void setSousProjetVo(SousProjetVo sousProjetVo) {
        this.sousProjetVo = sousProjetVo;
    }

    
    
    
}
