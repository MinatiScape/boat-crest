package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SafeParcelable.Class(creator = "ModuleInstallStatusUpdateCreator")
/* loaded from: classes6.dex */
public class ModuleInstallStatusUpdate extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ModuleInstallStatusUpdate> CREATOR = new zae();
    @SafeParcelable.Field(getter = "getSessionId", id = 1)
    public final int h;
    @InstallState
    @SafeParcelable.Field(getter = "getInstallState", id = 2)
    public final int i;
    @Nullable
    @SafeParcelable.Field(getter = "getBytesDownloaded", id = 3)
    public final Long j;
    @Nullable
    @SafeParcelable.Field(getter = "getTotalBytesToDownload", id = 4)
    public final Long k;
    @SafeParcelable.Field(getter = "getErrorCode", id = 5)
    public final int l;
    @Nullable
    public final ProgressInfo m;

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes6.dex */
    public @interface InstallState {
        public static final int STATE_CANCELED = 3;
        public static final int STATE_COMPLETED = 4;
        public static final int STATE_DOWNLOADING = 2;
        public static final int STATE_DOWNLOAD_PAUSED = 7;
        public static final int STATE_FAILED = 5;
        public static final int STATE_INSTALLING = 6;
        public static final int STATE_PENDING = 1;
        public static final int STATE_UNKNOWN = 0;
    }

    /* loaded from: classes6.dex */
    public static class ProgressInfo {

        /* renamed from: a  reason: collision with root package name */
        public final long f8354a;
        public final long b;

        public ProgressInfo(long j, long j2) {
            Preconditions.checkNotZero(j2);
            this.f8354a = j;
            this.b = j2;
        }

        public long getBytesDownloaded() {
            return this.f8354a;
        }

        public long getTotalBytesToDownload() {
            return this.b;
        }
    }

    @SafeParcelable.Constructor
    @KeepForSdk
    public ModuleInstallStatusUpdate(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) @InstallState int i2, @Nullable @SafeParcelable.Param(id = 3) Long l, @Nullable @SafeParcelable.Param(id = 4) Long l2, @SafeParcelable.Param(id = 5) int i3) {
        this.h = i;
        this.i = i2;
        this.j = l;
        this.k = l2;
        this.l = i3;
        this.m = (l == null || l2 == null || l2.longValue() == 0) ? null : new ProgressInfo(l.longValue(), l2.longValue());
    }

    public int getErrorCode() {
        return this.l;
    }

    @InstallState
    public int getInstallState() {
        return this.i;
    }

    @Nullable
    public ProgressInfo getProgressInfo() {
        return this.m;
    }

    public int getSessionId() {
        return this.h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getSessionId());
        SafeParcelWriter.writeInt(parcel, 2, getInstallState());
        SafeParcelWriter.writeLongObject(parcel, 3, this.j, false);
        SafeParcelWriter.writeLongObject(parcel, 4, this.k, false);
        SafeParcelWriter.writeInt(parcel, 5, getErrorCode());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
