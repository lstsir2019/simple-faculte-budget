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
public class SousProjetVo {

    private Long id;
    private String referenceSousProjet;
    private List<EntiteAdministratifVo> entiteAdministratifsVo;

    private ProjetVo projetVo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceSousProjet() {
        return referenceSousProjet;
    }

    public void setReferenceSousProjet(String referenceSousProjet) {
        this.referenceSousProjet = referenceSousProjet;
    }

   
    public List<EntiteAdministratifVo> getEntiteAdministratifsVo() {
        return entiteAdministratifsVo;
    }

    public void setEntiteAdministratifsVo(List<EntiteAdministratifVo> entiteAdministratifsVo) {
        this.entiteAdministratifsVo = entiteAdministratifsVo;
    }

    public ProjetVo getProjetVo() {
        return projetVo;
    }

    public void setProjetVo(ProjetVo projetVo) {
        this.projetVo = projetVo;
    }

}
