package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class a extends CrashlyticsReportWithSessionId {

    /* renamed from: a  reason: collision with root package name */
    public final CrashlyticsReport f11144a;
    public final String b;

    public a(CrashlyticsReport crashlyticsReport, String str) {
        Objects.requireNonNull(crashlyticsReport, "Null report");
        this.f11144a = crashlyticsReport;
        Objects.requireNonNull(str, "Null sessionId");
        this.b = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReportWithSessionId) {
            CrashlyticsReportWithSessionId crashlyticsReportWithSessionId = (CrashlyticsReportWithSessionId) obj;
            return this.f11144a.equals(crashlyticsReportWithSessionId.getReport()) && this.b.equals(crashlyticsReportWithSessionId.getSessionId());
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId
    public CrashlyticsReport getReport() {
        return this.f11144a;
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId
    public String getSessionId() {
        return this.b;
    }

    public int hashCode() {
        return ((this.f11144a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "CrashlyticsReportWithSessionId{report=" + this.f11144a + ", sessionId=" + this.b + "}";
    }
}
