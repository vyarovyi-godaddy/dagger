package dagger.internal;

/**
 * Compatibility shim for Firebase Performance This interface bridges the gap between Google Dagger
 * (expected by Firebase) and the custom Dagger fork used by this project
 */
public interface MembersInjector<T> {
    /** Injects dependencies into the given instance. */
    void injectMembers(T instance);
}
