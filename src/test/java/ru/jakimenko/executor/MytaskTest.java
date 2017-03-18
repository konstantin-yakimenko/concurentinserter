package ru.jakimenko.executor;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 * Created by konst on 18.03.17.
 */
// @RunWith(EasyMockRunner.class)
public class MytaskTest {

    @Rule
    public TestRule mocks = new EasyMockRule(this);

    @Mock
    Total total;

    @Test
    public void call() throws Exception {
//        replay(total);

        System.out.println("total.getSum() = " + total.getSum());

//        Total total = new Total();
//        assertEquals(0, total.getSum());
    }

}