package dagger.internal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Provider;

/**
 * Compatibility shim for Firebase Performance This class bridges the gap between Google Dagger
 * (expected by Firebase) and the custom Dagger fork used by this project
 *
 * <p>Provides factory for creating sets in multibinding
 */
public final class SetFactory<T> implements Factory<Set<T>> {
    @SuppressWarnings("rawtypes")
    private static final SetFactory<Object> EMPTY_FACTORY =
            new SetFactory<Object>(Collections.<Provider<Object>>emptyList());

    private final Iterable<Provider<T>> contributingProviders;

    private SetFactory(Iterable<Provider<T>> contributingProviders) {
        this.contributingProviders = contributingProviders;
    }

    @Override
    public Set<T> get() {
        Set<T> result = new HashSet<T>();
        for (Provider<T> provider : contributingProviders) {
            result.add(provider.get());
        }
        return Collections.unmodifiableSet(result);
    }

    /** Creates a factory for an empty set. */
    @SuppressWarnings("unchecked")
    public static <T> Factory<Set<T>> empty() {
        return (Factory<Set<T>>) (Factory) EMPTY_FACTORY;
    }

    /** Creates a factory for a set with the given providers. */
    public static <T> Factory<Set<T>> create(Iterable<Provider<T>> providers) {
        return new SetFactory<T>(providers);
    }
}
