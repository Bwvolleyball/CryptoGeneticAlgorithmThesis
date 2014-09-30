package regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.business;

import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.service.IPopulation;
import regishonorsthesis.brandonward.cryptogeneticalgorithmthesis.service.IPopulationImpl;

/**
 * Created by BrandonWard on 9/25/2014.
 */
public class PopulationManager {

    private IPopulation populationSvc;

    public PopulationManager() {
        populationSvc = new IPopulationImpl();
    }

}
