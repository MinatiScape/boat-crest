package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class CollectionsKt__IterablesKt$Iterable$1 implements Iterable<Object>, KMappedMarker {
    public final /* synthetic */ Function0<Iterator<Object>> h;

    /* JADX WARN: Multi-variable type inference failed */
    public CollectionsKt__IterablesKt$Iterable$1(Function0<? extends Iterator<Object>> function0) {
        this.h = function0;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Object> iterator() {
        return this.h.invoke();
    }
}
