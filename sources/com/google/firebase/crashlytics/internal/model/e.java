package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class e extends CrashlyticsReport.FilesPayload.File {

    /* renamed from: a  reason: collision with root package name */
    public final String f11199a;
    public final byte[] b;

    /* loaded from: classes10.dex */
    public static final class b extends CrashlyticsReport.FilesPayload.File.Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f11200a;
        public byte[] b;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File.Builder
        public CrashlyticsReport.FilesPayload.File build() {
            String str = "";
            if (this.f11200a == null) {
                str = " filename";
            }
            if (this.b == null) {
                str = str + " contents";
            }
            if (str.isEmpty()) {
                return new e(this.f11200a, this.b);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File.Builder
        public CrashlyticsReport.FilesPayload.File.Builder setContents(byte[] bArr) {
            Objects.requireNonNull(bArr, "Null contents");
            this.b = bArr;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File.Builder
        public CrashlyticsReport.FilesPayload.File.Builder setFilename(String str) {
            Objects.requireNonNull(str, "Null filename");
            this.f11200a = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.FilesPayload.File) {
            CrashlyticsReport.FilesPayload.File file = (CrashlyticsReport.FilesPayload.File) obj;
            if (this.f11199a.equals(file.getFilename())) {
                if (Arrays.equals(this.b, file instanceof e ? ((e) file).b : file.getContents())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File
    @NonNull
    public byte[] getContents() {
        return this.b;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.FilesPayload.File
    @NonNull
    public String getFilename() {
        return this.f11199a;
    }

    public int hashCode() {
        return ((this.f11199a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.b);
    }

    public String toString() {
        return "File{filename=" + this.f11199a + ", contents=" + Arrays.toString(this.b) + "}";
    }

    public e(String str, byte[] bArr) {
        this.f11199a = str;
        this.b = bArr;
    }
}
