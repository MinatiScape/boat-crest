package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class d extends CrashlyticsReport.FilesPayload {

    /* renamed from: a  reason: collision with root package name */
    public final ImmutableList<CrashlyticsReport.FilesPayload.File> f11197a;
    public final String b;

    /* loaded from: classes10.dex */
    public static final class b extends CrashlyticsReport.FilesPayload.Builder {

        /* renamed from: a  reason: collision with root package name */
        public ImmutableList<CrashlyticsReport.FilesPayload.File> f11198a;
        public String b;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.Builder
        public CrashlyticsReport.FilesPayload build() {
            String str = "";
            if (this.f11198a == null) {
                str = " files";
            }
            if (str.isEmpty()) {
                return new d(this.f11198a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.Builder
        public CrashlyticsReport.FilesPayload.Builder setFiles(ImmutableList<CrashlyticsReport.FilesPayload.File> immutableList) {
            Objects.requireNonNull(immutableList, "Null files");
            this.f11198a = immutableList;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.Builder
        public CrashlyticsReport.FilesPayload.Builder setOrgId(String str) {
            this.b = str;
            return this;
        }

        public b() {
        }

        public b(CrashlyticsReport.FilesPayload filesPayload) {
            this.f11198a = filesPayload.getFiles();
            this.b = filesPayload.getOrgId();
        }
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload
    public CrashlyticsReport.FilesPayload.Builder a() {
        return new b(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.FilesPayload) {
            CrashlyticsReport.FilesPayload filesPayload = (CrashlyticsReport.FilesPayload) obj;
            if (this.f11197a.equals(filesPayload.getFiles())) {
                String str = this.b;
                if (str == null) {
                    if (filesPayload.getOrgId() == null) {
                        return true;
                    }
                } else if (str.equals(filesPayload.getOrgId())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload
    @NonNull
    public ImmutableList<CrashlyticsReport.FilesPayload.File> getFiles() {
        return this.f11197a;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload
    @Nullable
    public String getOrgId() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = (this.f11197a.hashCode() ^ 1000003) * 1000003;
        String str = this.b;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "FilesPayload{files=" + this.f11197a + ", orgId=" + this.b + "}";
    }

    public d(ImmutableList<CrashlyticsReport.FilesPayload.File> immutableList, @Nullable String str) {
        this.f11197a = immutableList;
        this.b = str;
    }
}
