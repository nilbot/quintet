package logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCull {
    CullConfig cullConfig;
    GAData mock;
    Cull cull;

    public void setup() {
        cullConfig = new CullConfig();
        mock = new GADataTestMock();
        cull = new Cull();
    }

    // logic tests
    @Test
    public void TestCullMethod() throws Exception {
        setup();
        mock.initPopulation(100);
        cullConfig.setCullAmount(10);
        GAData result = cull.cull(cullConfig, mock);

        assertEquals(90, result.getPopulationSize());
    }

    // smoke testing
    @Test
    public void TestCullSmoke() {
        GAData mock = null;

        try {
            GAData result = cull.cull(cullConfig, mock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}