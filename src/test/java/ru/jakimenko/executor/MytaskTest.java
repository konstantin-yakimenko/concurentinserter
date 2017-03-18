package ru.jakimenko.executor;

import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.easymock.annotation.RegularMock;
import org.unitils.easymock.util.Calls;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 * Created by konst on 18.03.17.
 */
//@RunWith(UnitilsJUnit4TestClassRunner.class)
@RunWith(EasyMockRunner.class)
public class MytaskTest {

    // http://j4sq.blogspot.ru/2012/01/easymock.html

//    @Rule
//    public TestRule mocks = new EasyMockRule(this);

    @TestSubject
    private Mytask task = new Mytask();

    @Mock(type = MockType.NICE, name = "mock", fieldName = "total")
    Total total;

//    @RegularMock
//    Total total;

    @Test
    public void call() throws Exception {
        expect(total.calc(20)).andReturn(30);
        expect(total.calc(anyInt())).andReturn(15).times(2);
        replay(total);
        assertEquals(30, task.calc(18));
        assertEquals(30, task.calc(18));
        assertEquals(60, task.calc(20));
    }

}