package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class k extends CrashlyticsReport.Session.Event.Application {

    /* renamed from: a  reason: collision with root package name */
    public final CrashlyticsReport.Session.Event.Application.Execution f11211a;
    public final ImmutableList<CrashlyticsReport.CustomAttribute> b;
    public final ImmutableList<CrashlyticsReport.CustomAttribute> c;
    public final Boolean d;
    public final int e;

    /* loaded from: classes10.dex */
    public static final class b extends CrashlyticsReport.Session.Event.Application.Builder {

        /* renamed from: a  reason: collision with root package name */
        public CrashlyticsReport.Session.Event.Application.Execution f11212a;
        public ImmutableList<CrashlyticsReport.CustomAttribute> b;
        public ImmutableList<CrashlyticsReport.CustomAttribute> c;
        public Boolean d;
        public Integer e;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application build() {
            String str = "";
            if (this.f11212a == null) {
                str = " execution";
            }
            if (this.e == null) {
                str = str + " uiOrientation";
            }
            if (str.isEmpty()) {
                return new k(this.f11212a, this.b, this.c, this.d, this.e.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setBackground(@Nullable Boolean bool) {
            this.d = bool;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setCustomAttributes(ImmutableList<CrashlyticsReport.CustomAttribute> immutableList) {
            this.b = immutableList;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setExecution(CrashlyticsReport.Session.Event.Application.Execution execution) {
            Objects.requireNonNull(execution, "Null execution");
            this.f11212a = execution;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setInternalKeys(ImmutableList<CrashlyticsReport.CustomAttribute> immutableList) {
            this.c = immutableList;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application.Builder
        public CrashlyticsReport.Session.Event.Application.Builder setUiOrientation(int i) {
            this.e = Integer.valueOf(i);
            return this;
        }

        public b() {
        }

        public b(CrashlyticsReport.Session.Event.Application application) {
            this.f11212a = application.getExecution();
            this.b = application.getCustomAttributes();
            this.c = application.getInternalKeys();
            this.d = application.getBackground();
            this.e = Integer.valueOf(application.getUiOrientation());
        }
    }

    public boolean equals(Object obj) {
        ImmutableList<CrashlyticsReport.CustomAttribute> immutableList;
        ImmutableList<CrashlyticsReport.CustomAttribute> immutableList2;
        Boolean bool;
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.Session.Event.Application) {
            CrashlyticsReport.Session.Event.Application application = (CrashlyticsReport.Session.Event.Application) obj;
            return this.f11211a.equals(application.getExecution()) && ((immutableList = this.b) != null ? immutableList.equals(application.getCustomAttributes()) : application.getCustomAttributes() == null) && ((immutableList2 = this.c) != null ? immutableList2.equals(application.getInternalKeys()) : application.getInternalKeys() == null) && ((bool = this.d) != null ? bool.equals(application.getBackground()) : application.getBackground() == null) && this.e == application.getUiOrientation();
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    @Nullable
    public Boolean getBackground() {
        return this.d;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    @Nullable
    public ImmutableList<CrashlyticsReport.CustomAttribute> getCustomAttributes() {
        return this.b;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    @NonNull
    public CrashlyticsReport.Session.Event.Application.Execution getExecution() {
        return this.f11211a;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    @Nullable
    public ImmutableList<CrashlyticsReport.CustomAttribute> getInternalKeys() {
        return this.c;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    public int getUiOrientation() {
        return this.e;
    }

    public int hashCode() {
        int hashCode = (this.f11211a.hashCode() ^ 1000003) * 1000003;
        ImmutableList<CrashlyticsReport.CustomAttribute> immutableList = this.b;
        int hashCode2 = (hashCode ^ (immutableList == null ? 0 : immutableList.hashCode())) * 1000003;
        ImmutableList<CrashlyticsReport.CustomAttribute> immutableList2 = this.c;
        int hashCode3 = (hashCode2 ^ (immutableList2 == null ? 0 : immutableList2.hashCode())) * 1000003;
        Boolean bool = this.d;
        return ((hashCode3 ^ (bool != null ? bool.hashCode() : 0)) * 1000003) ^ this.e;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Application
    public CrashlyticsReport.Session.Event.Application.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "Application{execution=" + this.f11211a + ", customAttributes=" + this.b + ", internalKeys=" + this.c + ", background=" + this.d + ", uiOrientation=" + this.e + "}";
    }

    public k(CrashlyticsReport.Session.Event.Application.Execution execution, @Nullable ImmutableList<CrashlyticsReport.CustomAttribute> immutableList, @Nullable ImmutableList<CrashlyticsReport.CustomAttribute> immutableList2, @Nullable Boolean bool, int i) {
        this.f11211a = execution;
        this.b = immutableList;
        this.c = immutableList2;
        this.d = bool;
        this.e = i;
    }
}
