package com.polidea.rxandroidble2.internal.operations;

import android.os.DeadObjectException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.internal.QueueOperation;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Cancellable;
/* loaded from: classes12.dex */
public abstract class ScanOperation<SCAN_RESULT_TYPE, SCAN_CALLBACK_TYPE> extends QueueOperation<SCAN_RESULT_TYPE> {
    public final RxBleAdapterWrapper h;

    /* loaded from: classes12.dex */
    public class a implements Cancellable {
        public final /* synthetic */ Object h;

        public a(Object obj) {
            this.h = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.functions.Cancellable
        public void cancel() {
            RxBleLog.i("Scan operation is requested to stop.", new Object[0]);
            ScanOperation scanOperation = ScanOperation.this;
            scanOperation.c(scanOperation.h, this.h);
        }
    }

    public ScanOperation(RxBleAdapterWrapper rxBleAdapterWrapper) {
        this.h = rxBleAdapterWrapper;
    }

    public abstract SCAN_CALLBACK_TYPE a(ObservableEmitter<SCAN_RESULT_TYPE> observableEmitter);

    public abstract boolean b(RxBleAdapterWrapper rxBleAdapterWrapper, SCAN_CALLBACK_TYPE scan_callback_type);

    public abstract void c(RxBleAdapterWrapper rxBleAdapterWrapper, SCAN_CALLBACK_TYPE scan_callback_type);

    @Override // com.polidea.rxandroidble2.internal.QueueOperation
    public final void protectedRun(ObservableEmitter<SCAN_RESULT_TYPE> observableEmitter, QueueReleaseInterface queueReleaseInterface) {
        SCAN_CALLBACK_TYPE a2 = a(observableEmitter);
        try {
            observableEmitter.setCancellable(new a(a2));
            RxBleLog.i("Scan operation is requested to start.", new Object[0]);
            if (!b(this.h, a2)) {
                observableEmitter.tryOnError(new BleScanException(0));
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // com.polidea.rxandroidble2.internal.QueueOperation
    public BleException provideException(DeadObjectException deadObjectException) {
        return new BleScanException(1, deadObjectException);
    }
}
