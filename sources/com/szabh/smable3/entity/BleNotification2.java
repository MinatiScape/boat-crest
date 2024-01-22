package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleNotification2 extends BleWritable {
    private int mCategory;
    @NotNull
    private String mContent;
    @NotNull
    private String mPackage;
    @NotNull
    private String mPhone;
    private long mTime;
    @NotNull
    private String mTitle;

    public BleNotification2() {
        this(0, 0L, null, null, null, null, 63, null);
    }

    public /* synthetic */ BleNotification2(int i, long j, String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? "" : str, (i2 & 8) != 0 ? "" : str2, (i2 & 16) != 0 ? "" : str3, (i2 & 32) != 0 ? "" : str4);
    }

    public static /* synthetic */ BleNotification2 copy$default(BleNotification2 bleNotification2, int i, long j, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = bleNotification2.mCategory;
        }
        if ((i2 & 2) != 0) {
            j = bleNotification2.mTime;
        }
        long j2 = j;
        if ((i2 & 4) != 0) {
            str = bleNotification2.mPackage;
        }
        String str5 = str;
        if ((i2 & 8) != 0) {
            str2 = bleNotification2.mTitle;
        }
        String str6 = str2;
        if ((i2 & 16) != 0) {
            str3 = bleNotification2.mPhone;
        }
        String str7 = str3;
        if ((i2 & 32) != 0) {
            str4 = bleNotification2.mContent;
        }
        return bleNotification2.copy(i, j2, str5, str6, str7, str4);
    }

    public final int component1() {
        return this.mCategory;
    }

    public final long component2() {
        return this.mTime;
    }

    @NotNull
    public final String component3() {
        return this.mPackage;
    }

    @NotNull
    public final String component4() {
        return this.mTitle;
    }

    @NotNull
    public final String component5() {
        return this.mPhone;
    }

    @NotNull
    public final String component6() {
        return this.mContent;
    }

    @NotNull
    public final BleNotification2 copy(int i, long j, @NotNull String mPackage, @NotNull String mTitle, @NotNull String mPhone, @NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mPackage, "mPackage");
        Intrinsics.checkNotNullParameter(mTitle, "mTitle");
        Intrinsics.checkNotNullParameter(mPhone, "mPhone");
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        return new BleNotification2(i, j, mPackage, mTitle, mPhone, mContent);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mCategory);
        writeObject(BleTime.Companion.ofLocal(this.mTime));
        BleWritable.writeStringWithFix$default(this, this.mPackage, 32, null, 4, null);
        BleWritable.writeStringWithFix$default(this, this.mTitle, 32, null, 4, null);
        BleWritable.writeStringWithFix$default(this, this.mPhone, 32, null, 4, null);
        BleWritable.writeStringWithLimit$default(this, this.mContent, 250, null, true, 4, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleNotification2) {
            BleNotification2 bleNotification2 = (BleNotification2) obj;
            return this.mCategory == bleNotification2.mCategory && this.mTime == bleNotification2.mTime && Intrinsics.areEqual(this.mPackage, bleNotification2.mPackage) && Intrinsics.areEqual(this.mTitle, bleNotification2.mTitle) && Intrinsics.areEqual(this.mPhone, bleNotification2.mPhone) && Intrinsics.areEqual(this.mContent, bleNotification2.mContent);
        }
        return false;
    }

    public final int getMCategory() {
        return this.mCategory;
    }

    @NotNull
    public final String getMContent() {
        return this.mContent;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        byte[] bytes = this.mContent.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return Math.min(bytes.length, 250) + 103;
    }

    @NotNull
    public final String getMPackage() {
        return this.mPackage;
    }

    @NotNull
    public final String getMPhone() {
        return this.mPhone;
    }

    public final long getMTime() {
        return this.mTime;
    }

    @NotNull
    public final String getMTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.mCategory) * 31) + Long.hashCode(this.mTime)) * 31) + this.mPackage.hashCode()) * 31) + this.mTitle.hashCode()) * 31) + this.mPhone.hashCode()) * 31) + this.mContent.hashCode();
    }

    public final void setMCategory(int i) {
        this.mCategory = i;
    }

    public final void setMContent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mContent = str;
    }

    public final void setMPackage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPackage = str;
    }

    public final void setMPhone(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPhone = str;
    }

    public final void setMTime(long j) {
        this.mTime = j;
    }

    public final void setMTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mTitle = str;
    }

    @NotNull
    public String toString() {
        return "BleNotification(mCategory=" + this.mCategory + ", mTime=" + this.mTime + ", mPackage='" + this.mPackage + "', mTitle='" + this.mTitle + "',mPhone='" + this.mPhone + "', mContent='" + this.mContent + "')";
    }

    public BleNotification2(int i, long j, @NotNull String mPackage, @NotNull String mTitle, @NotNull String mPhone, @NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mPackage, "mPackage");
        Intrinsics.checkNotNullParameter(mTitle, "mTitle");
        Intrinsics.checkNotNullParameter(mPhone, "mPhone");
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        this.mCategory = i;
        this.mTime = j;
        this.mPackage = mPackage;
        this.mTitle = mTitle;
        this.mPhone = mPhone;
        this.mContent = mContent;
    }
}
