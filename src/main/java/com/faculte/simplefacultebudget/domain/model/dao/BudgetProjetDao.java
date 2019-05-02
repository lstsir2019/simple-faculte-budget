/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.dao;

import com.faculte.simplefacultebudget.domain.bean.BudgetProjet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AMINE
 */
@Repository
public interface BudgetProjetDao extends JpaRepository<BudgetProjet, Long> {

    public BudgetProjet findByReferenceEntiteAdministratif(String referenceEntiteAdministratif);

    public List<BudgetProjet> findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceSousProjet, int annee);

    public BudgetProjet findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif, String referenceSousProjet, int annee);

    public List<BudgetProjet> findByBudgetSousProjetBudgetFaculteAnnee(int annee);

    public List<BudgetProjet> findByBudgetSousProjetBudgetFaculteAnneeGreaterThanOrBudgetSousProjetBudgetFaculteAnneeLessThan(Integer anneeMin, Integer anneeMax);
}
