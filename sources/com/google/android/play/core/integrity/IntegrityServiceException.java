package com.google.android.play.core.integrity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import java.util.Locale;
/* loaded from: classes10.dex */
public class IntegrityServiceException extends ApiException {

    /* renamed from: a  reason: collision with root package name */
    private final Throwable f10449a;

    public IntegrityServiceException(int i, Throwable th) {
        super(new Status(i, String.format(Locale.ROOT, "Integrity API error (%d): %s.", Integer.valueOf(i), com.google.android.play.core.integrity.model.a.a(i))));
        if (i != 0) {
            this.f10449a = th;
            return;
        }
        throw new IllegalArgumentException("ErrorCode should not be 0.");
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable getCause() {
        return this.f10449a;
    }

    public int getErrorCode() {
        return super.getStatusCode();
    }
}
