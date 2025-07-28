package dagger.internal;

import javax.inject.Provider;

/**
 * Compatibility shim for Firebase Performance This class bridges the gap between Google Dagger
 * (expected by Firebase) and the custom Dagger fork used by this project
 *
 * <p>Provides scoped provider functionality
 */
public final class ScopedProvider<T> implements Provider<T> {
    private static final Object UNINITIALIZED = new Object();

    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private ScopedProvider(Provider<T> provider) {
        this.provider = provider;
    }

    @SuppressWarnings("unchecked") // Cast safe because of the instance check
    @Override
    public T get() {
        Object result = instance;
        if (result == UNINITIALIZED) {
            synchronized (this) {
                result = instance;
                if (result == UNINITIALIZED) {
                    Provider<T> prov = provider;
                    if (prov == null) {
                        throw new IllegalStateException("Already scoped");
                    }
                    instance = result = prov.get();
                    provider = null; // Release reference to avoid memory leaks
                }
            }
        }
        return (T) result;
    }

    /** Creates a scoped provider that wraps the given provider. */
    public static <T> Provider<T> create(Provider<T> provider) {
        Preconditions.checkNotNull(provider);
        if (provider instanceof ScopedProvider) {
            return provider;
        }
        return new ScopedProvider<T>(provider);
    }
}
