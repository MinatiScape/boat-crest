package com.clevertap.android.sdk.validation;

import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public final class ValidationResult {

    /* renamed from: a  reason: collision with root package name */
    public int f2689a;
    public String b;
    public Object c;

    public ValidationResult(int i, String str) {
        this.f2689a = i;
        this.b = str;
    }

    public int getErrorCode() {
        return this.f2689a;
    }

    public String getErrorDesc() {
        return this.b;
    }

    public Object getObject() {
        return this.c;
    }

    public void setErrorCode(int i) {
        this.f2689a = i;
    }

    public void setErrorDesc(String str) {
        this.b = str;
    }

    public void setObject(Object obj) {
        this.c = obj;
    }

    public ValidationResult() {
        this.f2689a = 0;
    }
}
