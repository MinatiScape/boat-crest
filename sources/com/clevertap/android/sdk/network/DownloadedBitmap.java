package com.clevertap.android.sdk.network;

import android.graphics.Bitmap;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DownloadedBitmap {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Bitmap f2649a;
    @NotNull
    public Status b;
    public long c;

    /* loaded from: classes2.dex */
    public enum Status {
        NO_IMAGE("NO_IMAGE"),
        SUCCESS("SUCCESS"),
        DOWNLOAD_FAILED("DOWNLOAD_FAILED"),
        NO_NETWORK("NO_NETWORK"),
        INIT_ERROR("INIT_ERROR"),
        SIZE_LIMIT_EXCEEDED("SIZE_LIMIT_EXCEEDED");
        
        @NotNull
        private final String statusValue;

        Status(String str) {
            this.statusValue = str;
        }

        @NotNull
        public final String getStatusValue() {
            return this.statusValue;
        }
    }

    public DownloadedBitmap(@Nullable Bitmap bitmap, @NotNull Status status, long j) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.f2649a = bitmap;
        this.b = status;
        this.c = j;
    }

    public static /* synthetic */ DownloadedBitmap copy$default(DownloadedBitmap downloadedBitmap, Bitmap bitmap, Status status, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            bitmap = downloadedBitmap.f2649a;
        }
        if ((i & 2) != 0) {
            status = downloadedBitmap.b;
        }
        if ((i & 4) != 0) {
            j = downloadedBitmap.c;
        }
        return downloadedBitmap.copy(bitmap, status, j);
    }

    @Nullable
    public final Bitmap component1() {
        return this.f2649a;
    }

    @NotNull
    public final Status component2() {
        return this.b;
    }

    public final long component3() {
        return this.c;
    }

    @NotNull
    public final DownloadedBitmap copy(@Nullable Bitmap bitmap, @NotNull Status status, long j) {
        Intrinsics.checkNotNullParameter(status, "status");
        return new DownloadedBitmap(bitmap, status, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DownloadedBitmap) {
            DownloadedBitmap downloadedBitmap = (DownloadedBitmap) obj;
            return Intrinsics.areEqual(this.f2649a, downloadedBitmap.f2649a) && this.b == downloadedBitmap.b && this.c == downloadedBitmap.c;
        }
        return false;
    }

    @Nullable
    public final Bitmap getBitmap() {
        return this.f2649a;
    }

    public final long getDownloadTime() {
        return this.c;
    }

    @NotNull
    public final Status getStatus() {
        return this.b;
    }

    public int hashCode() {
        Bitmap bitmap = this.f2649a;
        return ((((bitmap == null ? 0 : bitmap.hashCode()) * 31) + this.b.hashCode()) * 31) + Long.hashCode(this.c);
    }

    public final void setBitmap(@Nullable Bitmap bitmap) {
        this.f2649a = bitmap;
    }

    public final void setDownloadTime(long j) {
        this.c = j;
    }

    public final void setStatus(@NotNull Status status) {
        Intrinsics.checkNotNullParameter(status, "<set-?>");
        this.b = status;
    }

    @NotNull
    public String toString() {
        return "DownloadedBitmap(bitmap=" + this.f2649a + ", status=" + this.b + ", downloadTime=" + this.c + HexStringBuilder.COMMENT_END_CHAR;
    }
}
