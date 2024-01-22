package com.polidea.rxandroidble2.internal;

import android.os.DeadObjectException;
import androidx.annotation.NonNull;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.internal.operations.Operation;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
/* loaded from: classes9.dex */
public abstract class QueueOperation<T> implements Operation<T> {

    /* loaded from: classes9.dex */
    public class a implements ObservableOnSubscribe<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ QueueReleaseInterface f13390a;

        public a(QueueReleaseInterface queueReleaseInterface) {
            this.f13390a = queueReleaseInterface;
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(ObservableEmitter<T> observableEmitter) {
            try {
                QueueOperation.this.protectedRun(observableEmitter, this.f13390a);
            } catch (DeadObjectException e) {
                observableEmitter.tryOnError(QueueOperation.this.provideException(e));
                RxBleLog.e(e, "QueueOperation terminated with a DeadObjectException", new Object[0]);
            } catch (Throwable th) {
                observableEmitter.tryOnError(th);
                RxBleLog.e(th, "QueueOperation terminated with an unexpected exception", new Object[0]);
            }
        }
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(@NonNull Operation<?> operation) {
        return compareTo2((Operation) operation);
    }

    @Override // com.polidea.rxandroidble2.internal.operations.Operation
    public Priority definedPriority() {
        return Priority.NORMAL;
    }

    public abstract void protectedRun(ObservableEmitter<T> observableEmitter, QueueReleaseInterface queueReleaseInterface) throws Throwable;

    public abstract BleException provideException(DeadObjectException deadObjectException);

    @Override // com.polidea.rxandroidble2.internal.operations.Operation
    public final Observable<T> run(QueueReleaseInterface queueReleaseInterface) {
        return Observable.create(new a(queueReleaseInterface));
    }

    /* renamed from: compareTo  reason: avoid collision after fix types in other method */
    public int compareTo2(@NonNull Operation operation) {
        return operation.definedPriority().f13389a - definedPriority().f13389a;
    }
}
