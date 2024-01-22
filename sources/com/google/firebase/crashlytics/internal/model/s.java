package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class s extends CrashlyticsReport.Session.Event.Log {

    /* renamed from: a  reason: collision with root package name */
    public final String f11227a;

    /* loaded from: classes10.dex */
    public static final class b extends CrashlyticsReport.Session.Event.Log.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f11228a;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log.Builder
        public CrashlyticsReport.Session.Event.Log build() {
            String str = "";
            if (this.f11228a == null) {
                str = " content";
            }
            if (str.isEmpty()) {
                return new s(this.f11228a);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log.Builder
        public CrashlyticsReport.Session.Event.Log.Builder setContent(String str) {
            Objects.requireNonNull(str, "Null content");
            this.f11228a = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.Session.Event.Log) {
            return this.f11227a.equals(((CrashlyticsReport.Session.Event.Log) obj).getContent());
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log
    @NonNull
    public String getContent() {
        return this.f11227a;
    }

    public int hashCode() {
        return this.f11227a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "Log{content=" + this.f11227a + "}";
    }

    public s(String str) {
        this.f11227a = str;
    }
}
