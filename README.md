# java streams collect method
How to use streams collect method to accumulate periods concatenating if overlapps.
```java
List<Period> periodList = ...
List<Period> concatenatedPeriods = 
      periodList.stream()
        .collect(PeriodList::new, PeriodList::add, PeriodList::combain)
        .get();        
```
Input:
```
Period{start=1, end=2}
Period{start=3, end=7}
Period{start=3, end=4}
Period{start=5, end=6}
Period{start=4, end=7}
Period{start=8, end=9}
```

Output:
```
Period{start=1, end=2}
Period{start=3, end=7}
Period{start=8, end=9}
```
