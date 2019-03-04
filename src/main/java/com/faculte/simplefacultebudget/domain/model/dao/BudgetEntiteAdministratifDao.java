/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.model.dao;

import com.faculte.simplefacultebudget.domain.bean.BudgetEntiteAdministratif;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AMINE
 */
@Repository
public interface BudgetEntiteAdministratifDao extends JpaRepository<BudgetEntiteAdministratif , Long>{
    public BudgetEntiteAdministratif findByReferenceEntiteAdministratif (String referenceEntiteAdministratif);
    public List<BudgetEntiteAdministratif> findByBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceSousProjet , int annee);
    public BudgetEntiteAdministratif findByReferenceEntiteAdministratifAndBudgetSousProjetReferenceSousProjetAndBudgetSousProjetBudgetFaculteAnnee(String referenceEntiteAdministratif ,String referenceSousProjet , int annee);
    public List<BudgetEntiteAdministratif> findByBudgetSousProjetBudgetFaculteAnnee(int annee);
}
