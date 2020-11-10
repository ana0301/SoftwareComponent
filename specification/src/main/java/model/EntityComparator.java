package model;

import java.util.Comparator;
import java.util.function.Function;

public class EntityComparator implements Comparator<Entity> {
    @Override
    public int compare(Entity entity, Entity t1) {
        return 0;
    }

    @Override
    public Comparator<Entity> reversed() {
        return null;
    }

    @Override
    public Comparator<Entity> thenComparing(Comparator<? super Entity> other) {
        return null;
    }

    @Override
    public <U> Comparator<Entity> thenComparing(Function<? super Entity, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Entity> thenComparing(Function<? super Entity, ? extends U> keyExtractor) {
        return null;
    }
}
