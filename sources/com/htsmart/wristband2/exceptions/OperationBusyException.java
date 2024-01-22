package com.htsmart.wristband2.exceptions;

import com.htsmart.wristband2.a.a.b;
import java.util.Locale;
/* loaded from: classes11.dex */
public class OperationBusyException extends WristbandException {

    /* renamed from: a  reason: collision with root package name */
    private b f12020a;

    public OperationBusyException(b bVar) {
        this.f12020a = bVar;
    }

    public b getAppOperation() {
        return this.f12020a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return String.format(Locale.getDefault(), "Operation %s(%d) add failed!!!", this.f12020a.getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this.f12020a)));
    }

    public void setAppOperation(b bVar) {
        this.f12020a = bVar;
    }
}
