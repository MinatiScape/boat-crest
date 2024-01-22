package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Result;
/* loaded from: classes6.dex */
public class Response<T extends Result> {
    public Result h;

    public Response() {
    }

    public Response(@NonNull T t) {
        this.h = t;
    }

    @NonNull
    public T getResult() {
        return (T) this.h;
    }

    public void setResult(@NonNull T t) {
        this.h = t;
    }
}
