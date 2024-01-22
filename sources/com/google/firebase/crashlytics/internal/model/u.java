package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class u extends CrashlyticsReport.Session.User {

    /* renamed from: a  reason: collision with root package name */
    public final String f11238a;

    /* loaded from: classes10.dex */
    public static final class b extends CrashlyticsReport.Session.User.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f11239a;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User.Builder
        public CrashlyticsReport.Session.User build() {
            String str = "";
            if (this.f11239a == null) {
                str = " identifier";
            }
            if (str.isEmpty()) {
                return new u(this.f11239a);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User.Builder
        public CrashlyticsReport.Session.User.Builder setIdentifier(String str) {
            Objects.requireNonNull(str, "Null identifier");
            this.f11239a = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.Session.User) {
            return this.f11238a.equals(((CrashlyticsReport.Session.User) obj).getIdentifier());
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.User
    @NonNull
    public String getIdentifier() {
        return this.f11238a;
    }

    public int hashCode() {
        return this.f11238a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "User{identifier=" + this.f11238a + "}";
    }

    public u(String str) {
        this.f11238a = str;
    }
}
