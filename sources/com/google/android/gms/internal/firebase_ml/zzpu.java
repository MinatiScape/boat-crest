package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.internal.firebase_ml.zzpy;
import com.google.firebase.ml.common.FirebaseMLException;
/* loaded from: classes7.dex */
public interface zzpu<T, S extends zzpy> {
    @WorkerThread
    T zza(S s) throws FirebaseMLException;

    @Nullable
    zzqp zzoc();
}
