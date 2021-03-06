package app;

import config.*;
import data.TSVReader;
import logic.*;
import model.DataSource;
import data.InMemoryRepo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Config: a container for config flags + some configuration related actions
 */
public class Config {
    private DataSourceType dataSourceTypeConfig;
    private StrategyType strategyTypeConfig;
    private String preset_input;

    public Config(){}

    // TODO make this useful, maybe 3 params, 4?
    public Config(String repoType, String dataSource, String solve){
        this.preset_input = dataSource;
        this.dataSourceTypeConfig = DataSourceType.valueOf(repoType);
        this.strategyTypeConfig = StrategyType.valueOf(solve);
    }

    public void GetConfig() {
        try {
            InputStream inputStream;
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream
                    (propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException(propFileName+ " not found.");
            }

            dataSourceTypeConfig = DataSourceType.valueOf(prop.getProperty
                    ("REPO_TYPE"));
            strategyTypeConfig = StrategyType.valueOf(prop.getProperty
                    ("STRATEGY"));
            preset_input = prop.getProperty("PRESET_INPUT");
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataSource GetData(){
        switch (dataSourceTypeConfig) {
            case Memory:
                return getMemoryData(preset_input);
            case DBConnect:
            case RestHttp:
            default:
                throw new UnsupportedOperationException("Not implemented");
        }
    }

    public Solver GetSolver() {
        switch (strategyTypeConfig) {
            case BruteForce:
                return getBruteForce();
            case SimulatedAnnealing:
                return getSimulatedAnnealing();
            case GeneticAlgorithm:
                CullConfig cullcfg =new CullConfig(200, 0.02);
                Mutagen mutagen = new Virus();
                CombineConfig combinecfg = new CombineConfig(
                        CombineConfig.NOBLE,
                        CombineConfig.CROSSOVER,
                        mutagen);
                EvolutionConfig ecfg = new EvolutionConfig(100, 3000, cullcfg,
                        combinecfg);
                return getGeneticAlgorithm(ecfg);
            default:
                throw new UnsupportedOperationException("Not implemented");
        }
    }

    // nullable
    // TODO get this safe!
    private DataSource getMemoryData(String source){
        TSVReader reader = new TSVReader(source);
        reader.Parse();
        InMemoryRepo repo = new InMemoryRepo(reader.Content());
        if (repo.Ready()){
            return repo;
        }
        return null;
    }

    private Solver getBruteForce() {
        return new BruteForce();
    }
    private Solver getSimulatedAnnealing() { return new SimulatedAnnealing(); }
    private Solver getGeneticAlgorithm(EvolutionConfig ecfg) {
        GeneticAlgorithm rst = new GeneticAlgorithm(ecfg);
        rst.InjectData(GetData());
        return rst;
    }
}
