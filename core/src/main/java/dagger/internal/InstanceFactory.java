package dagger.internal;

/**
 * Compatibility shim for Firebase Performance This class bridges the gap between Google Dagger
 * (expected by Firebase) and the custom Dagger fork used by this project
 *
 * <p>Provides factory for pre-existing instances
 */
public final class InstanceFactory<T> implements Factory<T> {
    private final T instance;

    private InstanceFactory(T instance) {
        this.instance = instance;
    }

    @Override
    public T get() {
        return instance;
    }

    /** Creates a factory that returns the given instance. */
    @SuppressWarnings("unchecked")
    public static <T> Factory<T> create(T instance) {
        return new InstanceFactory<T>((T) Preconditions.checkNotNull(instance, "instance"));
    }
}
