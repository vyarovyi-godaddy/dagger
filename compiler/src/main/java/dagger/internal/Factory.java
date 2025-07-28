package dagger.internal;

import javax.inject.Provider;

/**
 * Compatibility shim for Firebase Performance This interface bridges the gap between Google Dagger
 * (expected by Firebase) and the custom Dagger fork used by this project
 */
public interface Factory<T> extends Provider<T> {
    /**
     * Creates an instance of type T. This method is inherited from Provider but redeclared for
     * clarity.
     */
    @Override
    T get();
}
