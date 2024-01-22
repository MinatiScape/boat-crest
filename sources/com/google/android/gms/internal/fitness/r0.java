package com.google.android.gms.internal.fitness;

import android.util.Log;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.fitness.result.DataReadResult;
/* loaded from: classes8.dex */
public final class r0 extends zzbf {

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder<DataReadResult> f8847a;
    public int b;
    public DataReadResult c;

    public r0(BaseImplementation.ResultHolder<DataReadResult> resultHolder) {
        this.b = 0;
        this.f8847a = resultHolder;
    }

    @Override // com.google.android.gms.internal.fitness.zzbc
    public final void zza(DataReadResult dataReadResult) {
        synchronized (this) {
            if (Log.isLoggable("Fitness", 2)) {
                int i = this.b;
                StringBuilder sb = new StringBuilder(33);
                sb.append("Received batch result ");
                sb.append(i);
                Log.v("Fitness", sb.toString());
            }
            DataReadResult dataReadResult2 = this.c;
            if (dataReadResult2 == null) {
                this.c = dataReadResult;
            } else {
                dataReadResult2.zzb(dataReadResult);
            }
            int i2 = this.b + 1;
            this.b = i2;
            if (i2 == this.c.zzab()) {
                this.f8847a.setResult(this.c);
            }
        }
    }

    public /* synthetic */ r0(BaseImplementation.ResultHolder resultHolder, k0 k0Var) {
        this(resultHolder);
    }
}
