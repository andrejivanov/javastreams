package de.ai;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPeriodList {

    @Test
    public void periodListSchouldBeEqualIfAllPeriodsEqual() throws Exception {

        List<Period> first =
                Arrays.asList(
                        new Period(1, 2),
                        new Period(3, 7),
                        new Period(8, 9)
                );
        List<Period> second =
                Arrays.asList(
                        new Period(1, 2),
                        new Period(3, 7),
                        new Period(8, 9)
                );
        assertTrue(first.equals(second));
    }

    @Test
    public void periodListSchouldBeNotEqualIfSomePeriodNotEquals() throws Exception {

        List<Period> first =
                Arrays.asList(
                        new Period(1, 2),
                        new Period(3, 7),
                        new Period(8, 9)
                );
        List<Period> second =
                Arrays.asList(
                        new Period(1, 2),
                        new Period(3, 7),
                        new Period(8, 10)
                );
        assertFalse(first.equals(second));
    }


    @Test
    public void periodListAddMethodShouldAccumulateCorrectly() throws Exception {

        final List<Period> testList =
                Arrays.asList(
                        new Period(1, 2),
                        new Period(3, 7),
                        new Period(3, 4),
                        new Period(5, 6),
                        new Period(4, 7),
                        new Period(8, 9));
        final List<Period> resultList =
                Arrays.asList(
                        new Period(1, 2),
                        new Period(3, 7),
                        new Period(8, 9)
                );

        final List<Period> concatenated = testList.stream().collect(PeriodList::new, PeriodList::add, PeriodList::combain).get();

        assertTrue(concatenated.equals(resultList));
    }
}
