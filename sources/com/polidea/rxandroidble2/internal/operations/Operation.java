package com.polidea.rxandroidble2.internal.operations;

import androidx.annotation.RestrictTo;
import com.polidea.rxandroidble2.internal.Priority;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import io.reactivex.Observable;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public interface Operation<T> extends Comparable<Operation<?>> {
    Priority definedPriority();

    Observable<T> run(QueueReleaseInterface queueReleaseInterface);
}
