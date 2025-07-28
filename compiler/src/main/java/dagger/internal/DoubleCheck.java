package dagger.internal;

import javax.inject.Provider;

/**
 * Compatibility shim for Firebase Performance This class bridges the gap between Google Dagger
 * (expected by Firebase) and the custom Dagger fork used by this project
 *
 * <p>Provides double-checked locking for singletons
 */
public final class DoubleCheck<T> implements Provider<T> {
    private static final Object UNINITIALIZED = new Object();

    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private DoubleCheck(Provider<T> provider) {
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
                        throw new IllegalStateException("Already initialized");
                    }
                    instance = result = prov.get();
                    provider = null; // Release reference to avoid memory leaks
                }
            }
        }
        return (T) result;
    }

    /** Creates a DoubleCheck provider that wraps the given provider. */
    public static <T> Provider<T> provider(Provider<T> provider) {
        Preconditions.checkNotNull(provider);
        if (provider instanceof DoubleCheck) {
            return provider;
        }
        return new DoubleCheck<T>(provider);
    }
}
