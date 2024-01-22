package kotlin.collections;

import java.util.Map;
import kotlin.jvm.internal.markers.KMutableMap;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public interface v<K, V> extends Map<K, V>, o<K, V>, KMutableMap {
    @Override // kotlin.collections.o
    @NotNull
    Map<K, V> getMap();
}
