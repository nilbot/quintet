package app;

import config.*;
import model.*;
import logic.*;
import presentation.*;

/**
 * ConsoleApp:
 */
public class ConsoleApp {
    public static void main(String[] argv){
        // Get Config from config resource file
        Config config = new Config();
        config.GetConfig();

        // init data store
        DataSource repo = config.GetData();

        Solver solver = config.GetSolver();
        if (repo.Ready()) {
            solver.InjectData(repo);
        } else {
            System.err.println("Repo is not ready");
            return;
        }
        System.out.println(repo.getMeta().toJson());

        Result res = solver.Solve();

        System.out.println(res.toJson());
    }
}
