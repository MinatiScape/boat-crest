package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class f extends CrashlyticsReport.Session {

    /* renamed from: a  reason: collision with root package name */
    public final String f11201a;
    public final String b;
    public final long c;
    public final Long d;
    public final boolean e;
    public final CrashlyticsReport.Session.Application f;
    public final CrashlyticsReport.Session.User g;
    public final CrashlyticsReport.Session.OperatingSystem h;
    public final CrashlyticsReport.Session.Device i;
    public final ImmutableList<CrashlyticsReport.Session.Event> j;
    public final int k;

    /* loaded from: classes10.dex */
    public static final class b extends CrashlyticsReport.Session.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f11202a;
        public String b;
        public Long c;
        public Long d;
        public Boolean e;
        public CrashlyticsReport.Session.Application f;
        public CrashlyticsReport.Session.User g;
        public CrashlyticsReport.Session.OperatingSystem h;
        public CrashlyticsReport.Session.Device i;
        public ImmutableList<CrashlyticsReport.Session.Event> j;
        public Integer k;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session build() {
            String str = "";
            if (this.f11202a == null) {
                str = " generator";
            }
            if (this.b == null) {
                str = str + " identifier";
            }
            if (this.c == null) {
                str = str + " startedAt";
            }
            if (this.e == null) {
                str = str + " crashed";
            }
            if (this.f == null) {
                str = str + " app";
            }
            if (this.k == null) {
                str = str + " generatorType";
            }
            if (str.isEmpty()) {
                return new f(this.f11202a, this.b, this.c.longValue(), this.d, this.e.booleanValue(), this.f, this.g, this.h, this.i, this.j, this.k.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setApp(CrashlyticsReport.Session.Application application) {
            Objects.requireNonNull(application, "Null app");
            this.f = application;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setCrashed(boolean z) {
            this.e = Boolean.valueOf(z);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setDevice(CrashlyticsReport.Session.Device device) {
            this.i = device;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setEndedAt(Long l) {
            this.d = l;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setEvents(ImmutableList<CrashlyticsReport.Session.Event> immutableList) {
            this.j = immutableList;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setGenerator(String str) {
            Objects.requireNonNull(str, "Null generator");
            this.f11202a = str;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setGeneratorType(int i) {
            this.k = Integer.valueOf(i);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setIdentifier(String str) {
            Objects.requireNonNull(str, "Null identifier");
            this.b = str;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setOs(CrashlyticsReport.Session.OperatingSystem operatingSystem) {
            this.h = operatingSystem;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setStartedAt(long j) {
            this.c = Long.valueOf(j);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setUser(CrashlyticsReport.Session.User user) {
            this.g = user;
            return this;
        }

        public b() {
        }

        public b(CrashlyticsReport.Session session) {
            this.f11202a = session.getGenerator();
            this.b = session.getIdentifier();
            this.c = Long.valueOf(session.getStartedAt());
            this.d = session.getEndedAt();
            this.e = Boolean.valueOf(session.isCrashed());
            this.f = session.getApp();
            this.g = session.getUser();
            this.h = session.getOs();
            this.i = session.getDevice();
            this.j = session.getEvents();
            this.k = Integer.valueOf(session.getGeneratorType());
        }
    }

    public boolean equals(Object obj) {
        Long l;
        CrashlyticsReport.Session.User user;
        CrashlyticsReport.Session.OperatingSystem operatingSystem;
        CrashlyticsReport.Session.Device device;
        ImmutableList<CrashlyticsReport.Session.Event> immutableList;
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.Session) {
            CrashlyticsReport.Session session = (CrashlyticsReport.Session) obj;
            return this.f11201a.equals(session.getGenerator()) && this.b.equals(session.getIdentifier()) && this.c == session.getStartedAt() && ((l = this.d) != null ? l.equals(session.getEndedAt()) : session.getEndedAt() == null) && this.e == session.isCrashed() && this.f.equals(session.getApp()) && ((user = this.g) != null ? user.equals(session.getUser()) : session.getUser() == null) && ((operatingSystem = this.h) != null ? operatingSystem.equals(session.getOs()) : session.getOs() == null) && ((device = this.i) != null ? device.equals(session.getDevice()) : session.getDevice() == null) && ((immutableList = this.j) != null ? immutableList.equals(session.getEvents()) : session.getEvents() == null) && this.k == session.getGeneratorType();
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    @NonNull
    public CrashlyticsReport.Session.Application getApp() {
        return this.f;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    @Nullable
    public CrashlyticsReport.Session.Device getDevice() {
        return this.i;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    @Nullable
    public Long getEndedAt() {
        return this.d;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    @Nullable
    public ImmutableList<CrashlyticsReport.Session.Event> getEvents() {
        return this.j;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    @NonNull
    public String getGenerator() {
        return this.f11201a;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public int getGeneratorType() {
        return this.k;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    @NonNull
    @Encodable.Ignore
    public String getIdentifier() {
        return this.b;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    @Nullable
    public CrashlyticsReport.Session.OperatingSystem getOs() {
        return this.h;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public long getStartedAt() {
        return this.c;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    @Nullable
    public CrashlyticsReport.Session.User getUser() {
        return this.g;
    }

    public int hashCode() {
        long j = this.c;
        int hashCode = (((((this.f11201a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        Long l = this.d;
        int hashCode2 = (((((hashCode ^ (l == null ? 0 : l.hashCode())) * 1000003) ^ (this.e ? 1231 : 1237)) * 1000003) ^ this.f.hashCode()) * 1000003;
        CrashlyticsReport.Session.User user = this.g;
        int hashCode3 = (hashCode2 ^ (user == null ? 0 : user.hashCode())) * 1000003;
        CrashlyticsReport.Session.OperatingSystem operatingSystem = this.h;
        int hashCode4 = (hashCode3 ^ (operatingSystem == null ? 0 : operatingSystem.hashCode())) * 1000003;
        CrashlyticsReport.Session.Device device = this.i;
        int hashCode5 = (hashCode4 ^ (device == null ? 0 : device.hashCode())) * 1000003;
        ImmutableList<CrashlyticsReport.Session.Event> immutableList = this.j;
        return ((hashCode5 ^ (immutableList != null ? immutableList.hashCode() : 0)) * 1000003) ^ this.k;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public boolean isCrashed() {
        return this.e;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "Session{generator=" + this.f11201a + ", identifier=" + this.b + ", startedAt=" + this.c + ", endedAt=" + this.d + ", crashed=" + this.e + ", app=" + this.f + ", user=" + this.g + ", os=" + this.h + ", device=" + this.i + ", events=" + this.j + ", generatorType=" + this.k + "}";
    }

    public f(String str, String str2, long j, @Nullable Long l, boolean z, CrashlyticsReport.Session.Application application, @Nullable CrashlyticsReport.Session.User user, @Nullable CrashlyticsReport.Session.OperatingSystem operatingSystem, @Nullable CrashlyticsReport.Session.Device device, @Nullable ImmutableList<CrashlyticsReport.Session.Event> immutableList, int i) {
        this.f11201a = str;
        this.b = str2;
        this.c = j;
        this.d = l;
        this.e = z;
        this.f = application;
        this.g = user;
        this.h = operatingSystem;
        this.i = device;
        this.j = immutableList;
        this.k = i;
    }
}
