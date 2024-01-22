package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class h extends CrashlyticsReport.Session.Application.Organization {

    /* renamed from: a  reason: collision with root package name */
    public final String f11205a;

    /* loaded from: classes10.dex */
    public static final class b extends CrashlyticsReport.Session.Application.Organization.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f11206a;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Organization.Builder
        public CrashlyticsReport.Session.Application.Organization build() {
            String str = "";
            if (this.f11206a == null) {
                str = " clsId";
            }
            if (str.isEmpty()) {
                return new h(this.f11206a);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Organization.Builder
        public CrashlyticsReport.Session.Application.Organization.Builder setClsId(String str) {
            Objects.requireNonNull(str, "Null clsId");
            this.f11206a = str;
            return this;
        }

        public b() {
        }

        public b(CrashlyticsReport.Session.Application.Organization organization) {
            this.f11206a = organization.getClsId();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.Session.Application.Organization) {
            return this.f11205a.equals(((CrashlyticsReport.Session.Application.Organization) obj).getClsId());
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Organization
    @NonNull
    public String getClsId() {
        return this.f11205a;
    }

    public int hashCode() {
        return this.f11205a.hashCode() ^ 1000003;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Organization
    public CrashlyticsReport.Session.Application.Organization.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "Organization{clsId=" + this.f11205a + "}";
    }

    public h(String str) {
        this.f11205a = str;
    }
}
