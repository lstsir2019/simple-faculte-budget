/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.rest.proxy;

import com.faculte.simplefacultebudget.domain.rest.vo.exchange.SousProjetVo;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author hp
 */
@FeignClient(name = "microservice3-mandatPersonnel")
public interface SousProjetService {

    @GetMapping("/mandat/mandats/all/sousprojet")
    public List<SousProjetVo> findAllSousProjet();
}