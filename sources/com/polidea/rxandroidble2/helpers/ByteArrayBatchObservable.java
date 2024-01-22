package com.polidea.rxandroidble2.helpers;

import androidx.annotation.NonNull;
import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import java.nio.ByteBuffer;
import org.reactivestreams.Subscriber;
/* loaded from: classes9.dex */
public class ByteArrayBatchObservable extends Flowable<byte[]> {
    @NonNull
    public final ByteBuffer i;
    public final int j;

    /* loaded from: classes9.dex */
    public class a implements Consumer<Emitter<byte[]>> {
        public a() {
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Emitter<byte[]> emitter) {
            int min = Math.min(ByteArrayBatchObservable.this.i.remaining(), ByteArrayBatchObservable.this.j);
            if (min == 0) {
                emitter.onComplete();
                return;
            }
            byte[] bArr = new byte[min];
            ByteArrayBatchObservable.this.i.get(bArr);
            emitter.onNext(bArr);
        }
    }

    public ByteArrayBatchObservable(@NonNull byte[] bArr, int i) {
        if (i > 0) {
            this.i = ByteBuffer.wrap(bArr);
            this.j = i;
            return;
        }
        throw new IllegalArgumentException("maxBatchSize must be > 0 but found: " + i);
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super byte[]> subscriber) {
        Flowable.generate(new a()).subscribe(subscriber);
    }
}
