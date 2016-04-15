package logic;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.builders.NullBuilder;

import static org.junit.Assert.*;

public class TestCull {
    CullConfig cullConfig;
    GAData mock;
    Cull cull;

    @Before
    public void setup() {
        cullConfig = new CullConfig();
        mock = new GADataTestMock();
        cull = new Cull();
    }

    // logic tests
    @Test
    public void TestCullMethod() throws Exception {
        mock.initPopulation(100);
        cullConfig.setCullAmount(10);
        GAData result = cull.cull(cullConfig, mock);

        assertEquals(90, result.getPopulationSize());
    }

    // smoke testing
    @Test(expected = IllegalArgumentException.class)
    public void TestCullSmoke() {
        GAData dMock = null;
        cull.cull(cullConfig, dMock);
    }
}