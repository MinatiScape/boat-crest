package kotlinx.coroutines.debug.internal;

import java.util.Comparator;
import kotlin.comparisons.f;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
/* renamed from: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesInfoImpl$lambda-12$$inlined$sortedBy$1  reason: invalid class name */
/* loaded from: classes12.dex */
public final class DebugProbesImpl$dumpCoroutinesInfoImpl$lambda12$$inlined$sortedBy$1<T> implements Comparator {
    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        return f.compareValues(Long.valueOf(((DebugProbesImpl.a) t).i.sequenceNumber), Long.valueOf(((DebugProbesImpl.a) t2).i.sequenceNumber));
    }
}
