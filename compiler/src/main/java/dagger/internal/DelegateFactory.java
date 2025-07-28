package dagger.internal;

import javax.inject.Provider;

/**
 * Compatibility shim for Firebase Performance This class bridges the gap between Google Dagger
 * (expected by Firebase) and the custom Dagger fork used by this project
 *
 * <p>Provides delegation factory for handling circular dependencies
 */
public final class DelegateFactory<T> implements Factory<T> {
    private Provider<T> delegate;

    @Override
    public T get() {
        Provider<T> d = delegate;
        if (d == null) {
            throw new IllegalStateException("Delegate factory not set");
        }
        return d.get();
    }

    /** Sets the delegate provider. */
    public void setDelegatedProvider(Provider<T> delegate) {
        if (this.delegate != null) {
            throw new IllegalStateException("Delegate factory already set");
        }
        this.delegate = delegate;
    }

    /** Creates a new delegate factory. */
    public static <T> DelegateFactory<T> create() {
        return new DelegateFactory<T>();
    }
}
