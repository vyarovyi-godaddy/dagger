package dagger.internal;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;

/**
 * Compatibility shim for Firebase Performance This class bridges the gap between Google Dagger
 * (expected by Firebase) and the custom Dagger fork used by this project
 *
 * <p>Provides factory for creating maps of providers in multibinding
 */
public final class MapProviderFactory<K, V> implements Factory<Map<K, Provider<V>>> {
    @SuppressWarnings("rawtypes")
    private static final MapProviderFactory<Object, Object> EMPTY_FACTORY =
            new MapProviderFactory<Object, Object>(
                    Collections.<Object, Provider<Object>>emptyMap()
            );

    private final Map<K, Provider<V>> contributingMap;

    private MapProviderFactory(Map<K, Provider<V>> contributingMap) {
        this.contributingMap = Collections.unmodifiableMap(contributingMap);
    }

    @Override
    public Map<K, Provider<V>> get() {
        return contributingMap;
    }

    /** Creates a factory for an empty map. */
    @SuppressWarnings("unchecked")
    public static <K, V> Factory<Map<K, Provider<V>>> empty() {
        return (Factory<Map<K, Provider<V>>>) (Factory) EMPTY_FACTORY;
    }

    /** Creates a factory for a map with the given key-provider pairs. */
    public static <K, V> Factory<Map<K, Provider<V>>> create(Map<K, Provider<V>> map) {
        return new MapProviderFactory<K, V>(new LinkedHashMap<K, Provider<V>>(map));
    }
}
