package kotlin.collections;

import java.util.Map;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface o<K, V> extends Map<K, V>, KMappedMarker {
    V c(K k);

    @NotNull
    Map<K, V> getMap();
}
