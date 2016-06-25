package de.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class PeriodList {

    private List<Period> container = new ArrayList<>();

    public boolean add(final Period neu) {
        Predicate<Period> isOverlapped = old ->
                (neu.getStart() > old.getStart() && neu.getStart() < old.getEnd())
                        || (neu.getEnd() > old.getStart() && neu.getEnd() < old.getEnd());
        final List<Period> overlapped = container.stream().filter(isOverlapped).collect(Collectors.toList());
        overlapped.forEach(period -> container.remove(period));
        overlapped.add(neu);
        final List<Integer> collectedStartsAndEnds =
                overlapped.stream().flatMap(period -> Arrays.asList(period.getStart(), period.getEnd()).stream()).collect(Collectors.toList());

        final Optional<Integer> start = collectedStartsAndEnds.stream().min(Integer::compareTo);
        final Optional<Integer> end = collectedStartsAndEnds.stream().max(Integer::compareTo);
        if (start.isPresent() && end.isPresent()) {
            container.add(new Period(start.get(), end.get()));
            return true;
        }
        return false;
    }

    public List<Period> get() {
        return container;
    }

    public PeriodList combain(final PeriodList periodList) {
        return periodList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PeriodList that = (PeriodList) o;

        if (container.size() != that.container.size()) {
            return false;
        }
        int index = 0;
        for (Period period : container) {
            if (!period.equals(that.container.get(index))) {
                return false;
            }
            index++;
        }

        return container != null ? container.equals(that.container) : that.container == null;

    }

    @Override
    public int hashCode() {
        return container != null ? container.hashCode() : 0;
    }
}
