package logic;


import model.DataSource;
import presentation.GAResult;
import presentation.Result;

import java.util.ArrayList;
import java.util.List;

public class GATemplate implements Solver {
    private Cullable cullable;
    private Combinable combinable;
    private GAData gaData;

    public GATemplate(Combinable co, Cullable cu) {
        prep(co, cu);
    }

    private void prep(Combinable co, Cullable cu) {
        this.combinable = co;
        this.cullable = cu;
        gaData = new GAData();

    }

    @Override
    public void InjectData(DataSource repo) {
        gaData.InjectData(repo);
    }

    @Override
    public Result Solve() {
        for (int i = 0; i< 5; i++) {
//            this.initDataStorage();
            gaData.createInitialPopulation();
            combinable.combine();
            cullable.cull(gaData);
        }

        return new GAResult(gaData.getPopulation());
    }
}