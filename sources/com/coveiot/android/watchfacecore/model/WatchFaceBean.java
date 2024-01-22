package com.coveiot.android.watchfacecore.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class WatchFaceBean {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f6112a;
    @NotNull
    public final String b;
    @Nullable
    public final String c;
    @Nullable
    public final String d;
    @Nullable
    public String e;
    @Nullable
    public final String f;
    @Nullable
    public final String g;
    @Nullable
    public String h;
    @Nullable
    public final List<String> i;
    @Nullable
    public final Integer j;
    @Nullable
    public final Integer k;
    @Nullable
    public final Integer l;
    @Nullable
    public final String m;
    public final boolean n;
    @Nullable
    public String o;

    public WatchFaceBean(@NotNull String uid, @NotNull String faceId, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable List<String> list, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str7, boolean z, @Nullable String str8) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(faceId, "faceId");
        this.f6112a = uid;
        this.b = faceId;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = list;
        this.j = num;
        this.k = num2;
        this.l = num3;
        this.m = str7;
        this.n = z;
        this.o = str8;
    }

    @NotNull
    public final String component1() {
        return this.f6112a;
    }

    @Nullable
    public final Integer component10() {
        return this.j;
    }

    @Nullable
    public final Integer component11() {
        return this.k;
    }

    @Nullable
    public final Integer component12() {
        return this.l;
    }

    @Nullable
    public final String component13() {
        return this.m;
    }

    public final boolean component14() {
        return this.n;
    }

    @Nullable
    public final String component15() {
        return this.o;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    @Nullable
    public final String component5() {
        return this.e;
    }

    @Nullable
    public final String component6() {
        return this.f;
    }

    @Nullable
    public final String component7() {
        return this.g;
    }

    @Nullable
    public final String component8() {
        return this.h;
    }

    @Nullable
    public final List<String> component9() {
        return this.i;
    }

    @NotNull
    public final WatchFaceBean copy(@NotNull String uid, @NotNull String faceId, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable List<String> list, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str7, boolean z, @Nullable String str8) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(faceId, "faceId");
        return new WatchFaceBean(uid, faceId, str, str2, str3, str4, str5, str6, list, num, num2, num3, str7, z, str8);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WatchFaceBean) {
            WatchFaceBean watchFaceBean = (WatchFaceBean) obj;
            return Intrinsics.areEqual(this.f6112a, watchFaceBean.f6112a) && Intrinsics.areEqual(this.b, watchFaceBean.b) && Intrinsics.areEqual(this.c, watchFaceBean.c) && Intrinsics.areEqual(this.d, watchFaceBean.d) && Intrinsics.areEqual(this.e, watchFaceBean.e) && Intrinsics.areEqual(this.f, watchFaceBean.f) && Intrinsics.areEqual(this.g, watchFaceBean.g) && Intrinsics.areEqual(this.h, watchFaceBean.h) && Intrinsics.areEqual(this.i, watchFaceBean.i) && Intrinsics.areEqual(this.j, watchFaceBean.j) && Intrinsics.areEqual(this.k, watchFaceBean.k) && Intrinsics.areEqual(this.l, watchFaceBean.l) && Intrinsics.areEqual(this.m, watchFaceBean.m) && this.n == watchFaceBean.n && Intrinsics.areEqual(this.o, watchFaceBean.o);
        }
        return false;
    }

    @Nullable
    public final String getDownloadUrl() {
        return this.e;
    }

    @Nullable
    public final String getEditUrl() {
        return this.o;
    }

    @NotNull
    public final String getFaceId() {
        return this.b;
    }

    @Nullable
    public final Integer getFacePosition() {
        return this.l;
    }

    @Nullable
    public final Integer getFaceResId() {
        return this.k;
    }

    @Nullable
    public final String getFaceType() {
        return this.c;
    }

    @Nullable
    public final String getFileMd5Hash() {
        return this.g;
    }

    @Nullable
    public final String getFileType() {
        return this.f;
    }

    public final boolean getNewWatchFace() {
        return this.n;
    }

    @Nullable
    public final String getPreviewImageUrl() {
        return this.h;
    }

    @Nullable
    public final String getPreviewImageUrlFromLocal() {
        return this.m;
    }

    @Nullable
    public final Integer getPreviewResId() {
        return this.j;
    }

    @Nullable
    public final List<String> getTags() {
        return this.i;
    }

    @Nullable
    public final String getTitle() {
        return this.d;
    }

    @NotNull
    public final String getUid() {
        return this.f6112a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((this.f6112a.hashCode() * 31) + this.b.hashCode()) * 31;
        String str = this.c;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.d;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.e;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.f;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.g;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.h;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        List<String> list = this.i;
        int hashCode8 = (hashCode7 + (list == null ? 0 : list.hashCode())) * 31;
        Integer num = this.j;
        int hashCode9 = (hashCode8 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.k;
        int hashCode10 = (hashCode9 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.l;
        int hashCode11 = (hashCode10 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str7 = this.m;
        int hashCode12 = (hashCode11 + (str7 == null ? 0 : str7.hashCode())) * 31;
        boolean z = this.n;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode12 + i) * 31;
        String str8 = this.o;
        return i2 + (str8 != null ? str8.hashCode() : 0);
    }

    public final void setDownloadUrl(@Nullable String str) {
        this.e = str;
    }

    public final void setEditUrl(@Nullable String str) {
        this.o = str;
    }

    public final void setPreviewImageUrl(@Nullable String str) {
        this.h = str;
    }

    @NotNull
    public String toString() {
        return "WatchFaceBean(uid=" + this.f6112a + ", faceId=" + this.b + ", faceType=" + this.c + ", title=" + this.d + ", downloadUrl=" + this.e + ", fileType=" + this.f + ", fileMd5Hash=" + this.g + ", previewImageUrl=" + this.h + ", tags=" + this.i + ", previewResId=" + this.j + ", faceResId=" + this.k + ", facePosition=" + this.l + ", previewImageUrlFromLocal=" + this.m + ", newWatchFace=" + this.n + ", editUrl=" + this.o + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WatchFaceBean(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, List list, Integer num, Integer num2, Integer num3, String str9, boolean z, String str10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7, (i & 128) != 0 ? null : str8, (i & 256) != 0 ? null : list, (i & 512) != 0 ? null : num, (i & 1024) != 0 ? null : num2, (i & 2048) != 0 ? null : num3, (i & 4096) != 0 ? null : str9, (i & 8192) != 0 ? false : z, (i & 16384) != 0 ? null : str10);
    }
}
