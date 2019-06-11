package nl.ordina.recruitmentexperience.common;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<I, O> {

    O map(final I input);

    default List<O> map(final Collection<I> input){
        return input.stream().map(this::map).collect(Collectors.toList());
    }

    default O mapNullSafe(final I input) {
        return input == null ? null : map(input);
    }
}
