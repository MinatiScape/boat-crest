package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetDiagnosticFeatureTestResponse {

    /* renamed from: a  reason: collision with root package name */
    public int f3606a;
    public int b;
    public int c;

    public final int getDeploy() {
        return this.b;
    }

    public final int getFeature() {
        return this.c;
    }

    public final int getStatus() {
        return this.f3606a;
    }

    public final void setDeploy(int i) {
        this.b = i;
    }

    public final void setFeature(int i) {
        this.c = i;
    }

    public final void setStatus(int i) {
        this.f3606a = i;
    }

    @NotNull
    public String toString() {
        return "GetDiagnosticFeatureTestResponse(status=" + this.f3606a + ", deploy=" + this.b + ", feature=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
