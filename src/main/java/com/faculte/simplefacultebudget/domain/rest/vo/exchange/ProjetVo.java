/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest.vo.exchange;

import java.util.List;

/**
 *
 * @author abdou
 */
public class ProjetVo {
    
     private Long id;
    private String libelleP;
    private List<SousProjetVo> sousProjetsVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleP() {
        return libelleP;
    }

    public void setLibelleP(String libelleP) {
        this.libelleP = libelleP;
    }

    public List<SousProjetVo> getSousProjetsVo() {
        return sousProjetsVo;
    }

    public void setSousProjetsVo(List<SousProjetVo> sousProjetsVo) {
        this.sousProjetsVo = sousProjetsVo;
    }
    
    
    
}
