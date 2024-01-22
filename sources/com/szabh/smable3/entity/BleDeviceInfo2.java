package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.Locale;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleDeviceInfo2 extends BleReadable {
    @NotNull
    private String mBleAddress;
    @NotNull
    private String mBleName;
    @NotNull
    private String mClassicAddress;
    @NotNull
    private String mFirmwareFlag;
    @NotNull
    private String mFirmwareVersion;
    @NotNull
    private String mFullVersion;
    private int mLanguageCode;
    @NotNull
    private String mLanguageVersion;
    @NotNull
    private String mPlatform;
    @NotNull
    private String mPrototype;
    @NotNull
    private String mUiVersion;

    public BleDeviceInfo2() {
        this(null, null, null, null, null, 0, null, null, null, null, null, 2047, null);
    }

    public /* synthetic */ BleDeviceInfo2(String str, String str2, String str3, String str4, String str5, int i, String str6, String str7, String str8, String str9, String str10, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "0.0.0" : str3, (i2 & 8) != 0 ? "0.0.0" : str4, (i2 & 16) == 0 ? str5 : "0.0.0", (i2 & 32) != 0 ? 1 : i, (i2 & 64) != 0 ? "" : str6, (i2 & 128) != 0 ? "" : str7, (i2 & 256) != 0 ? "" : str8, (i2 & 512) != 0 ? "" : str9, (i2 & 1024) == 0 ? str10 : "");
    }

    private final String toVersion(byte[] bArr, CharSequence charSequence) {
        return ArraysKt___ArraysKt.joinToString$default(bArr, charSequence, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: com.szabh.smable3.entity.BleDeviceInfo2$toVersion$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                return invoke(b.byteValue());
            }

            @NotNull
            public final CharSequence invoke(byte b) {
                int i = b & 255;
                return i > 9 ? BleConst.GetDeviceTime : String.valueOf(i);
            }
        }, 30, (Object) null);
    }

    @NotNull
    public final String component1() {
        return this.mBleAddress;
    }

    @NotNull
    public final String component10() {
        return this.mFirmwareFlag;
    }

    @NotNull
    public final String component11() {
        return this.mFullVersion;
    }

    @NotNull
    public final String component2() {
        return this.mClassicAddress;
    }

    @NotNull
    public final String component3() {
        return this.mFirmwareVersion;
    }

    @NotNull
    public final String component4() {
        return this.mUiVersion;
    }

    @NotNull
    public final String component5() {
        return this.mLanguageVersion;
    }

    public final int component6() {
        return this.mLanguageCode;
    }

    @NotNull
    public final String component7() {
        return this.mBleName;
    }

    @NotNull
    public final String component8() {
        return this.mPlatform;
    }

    @NotNull
    public final String component9() {
        return this.mPrototype;
    }

    @NotNull
    public final BleDeviceInfo2 copy(@NotNull String mBleAddress, @NotNull String mClassicAddress, @NotNull String mFirmwareVersion, @NotNull String mUiVersion, @NotNull String mLanguageVersion, int i, @NotNull String mBleName, @NotNull String mPlatform, @NotNull String mPrototype, @NotNull String mFirmwareFlag, @NotNull String mFullVersion) {
        Intrinsics.checkNotNullParameter(mBleAddress, "mBleAddress");
        Intrinsics.checkNotNullParameter(mClassicAddress, "mClassicAddress");
        Intrinsics.checkNotNullParameter(mFirmwareVersion, "mFirmwareVersion");
        Intrinsics.checkNotNullParameter(mUiVersion, "mUiVersion");
        Intrinsics.checkNotNullParameter(mLanguageVersion, "mLanguageVersion");
        Intrinsics.checkNotNullParameter(mBleName, "mBleName");
        Intrinsics.checkNotNullParameter(mPlatform, "mPlatform");
        Intrinsics.checkNotNullParameter(mPrototype, "mPrototype");
        Intrinsics.checkNotNullParameter(mFirmwareFlag, "mFirmwareFlag");
        Intrinsics.checkNotNullParameter(mFullVersion, "mFullVersion");
        return new BleDeviceInfo2(mBleAddress, mClassicAddress, mFirmwareVersion, mUiVersion, mLanguageVersion, i, mBleName, mPlatform, mPrototype, mFirmwareFlag, mFullVersion);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        String readStringUtil$default = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String upperCase = readStringUtil$default.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        this.mBleAddress = upperCase;
        String readStringUtil$default2 = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        Locale locale2 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
        String upperCase2 = readStringUtil$default2.toUpperCase(locale2);
        Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(locale)");
        this.mClassicAddress = upperCase2;
        this.mFirmwareVersion = toVersion(readBytes(3), ".");
        this.mUiVersion = toVersion(readBytes(3), ".");
        this.mLanguageVersion = toVersion(readBytes(3), ".");
        this.mLanguageCode = readUInt8();
        this.mBleName = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        this.mPlatform = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        this.mPrototype = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        this.mFirmwareFlag = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
        this.mFullVersion = BleReadable.readStringUtil$default(this, (byte) 0, null, 2, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleDeviceInfo2) {
            BleDeviceInfo2 bleDeviceInfo2 = (BleDeviceInfo2) obj;
            return Intrinsics.areEqual(this.mBleAddress, bleDeviceInfo2.mBleAddress) && Intrinsics.areEqual(this.mClassicAddress, bleDeviceInfo2.mClassicAddress) && Intrinsics.areEqual(this.mFirmwareVersion, bleDeviceInfo2.mFirmwareVersion) && Intrinsics.areEqual(this.mUiVersion, bleDeviceInfo2.mUiVersion) && Intrinsics.areEqual(this.mLanguageVersion, bleDeviceInfo2.mLanguageVersion) && this.mLanguageCode == bleDeviceInfo2.mLanguageCode && Intrinsics.areEqual(this.mBleName, bleDeviceInfo2.mBleName) && Intrinsics.areEqual(this.mPlatform, bleDeviceInfo2.mPlatform) && Intrinsics.areEqual(this.mPrototype, bleDeviceInfo2.mPrototype) && Intrinsics.areEqual(this.mFirmwareFlag, bleDeviceInfo2.mFirmwareFlag) && Intrinsics.areEqual(this.mFullVersion, bleDeviceInfo2.mFullVersion);
        }
        return false;
    }

    @NotNull
    public final String getMBleAddress() {
        return this.mBleAddress;
    }

    @NotNull
    public final String getMBleName() {
        return this.mBleName;
    }

    @NotNull
    public final String getMClassicAddress() {
        return this.mClassicAddress;
    }

    @NotNull
    public final String getMFirmwareFlag() {
        return this.mFirmwareFlag;
    }

    @NotNull
    public final String getMFirmwareVersion() {
        return this.mFirmwareVersion;
    }

    @NotNull
    public final String getMFullVersion() {
        return this.mFullVersion;
    }

    public final int getMLanguageCode() {
        return this.mLanguageCode;
    }

    @NotNull
    public final String getMLanguageVersion() {
        return this.mLanguageVersion;
    }

    @NotNull
    public final String getMPlatform() {
        return this.mPlatform;
    }

    @NotNull
    public final String getMPrototype() {
        return this.mPrototype;
    }

    @NotNull
    public final String getMUiVersion() {
        return this.mUiVersion;
    }

    public int hashCode() {
        return (((((((((((((((((((this.mBleAddress.hashCode() * 31) + this.mClassicAddress.hashCode()) * 31) + this.mFirmwareVersion.hashCode()) * 31) + this.mUiVersion.hashCode()) * 31) + this.mLanguageVersion.hashCode()) * 31) + Integer.hashCode(this.mLanguageCode)) * 31) + this.mBleName.hashCode()) * 31) + this.mPlatform.hashCode()) * 31) + this.mPrototype.hashCode()) * 31) + this.mFirmwareFlag.hashCode()) * 31) + this.mFullVersion.hashCode();
    }

    public final void setMBleAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mBleAddress = str;
    }

    public final void setMBleName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mBleName = str;
    }

    public final void setMClassicAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mClassicAddress = str;
    }

    public final void setMFirmwareFlag(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mFirmwareFlag = str;
    }

    public final void setMFirmwareVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mFirmwareVersion = str;
    }

    public final void setMFullVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mFullVersion = str;
    }

    public final void setMLanguageCode(int i) {
        this.mLanguageCode = i;
    }

    public final void setMLanguageVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mLanguageVersion = str;
    }

    public final void setMPlatform(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPlatform = str;
    }

    public final void setMPrototype(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPrototype = str;
    }

    public final void setMUiVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mUiVersion = str;
    }

    @NotNull
    public String toString() {
        return "BleDeviceInfo2(mBleName='" + this.mBleName + "', mBleAddress='" + this.mBleAddress + "', mClassicAddress='" + this.mClassicAddress + "', mFirmwareVersion='" + this.mFirmwareVersion + "', mUiVersion='" + this.mUiVersion + "', mLanguageVersion='" + this.mLanguageVersion + "', mLanguageCode='" + this.mLanguageCode + "', mPlatform='" + this.mPlatform + "', mPrototype='" + this.mPrototype + "', mFirmwareFlag='" + this.mFirmwareFlag + "', mFullVersion='" + this.mFullVersion + "')";
    }

    public BleDeviceInfo2(@NotNull String mBleAddress, @NotNull String mClassicAddress, @NotNull String mFirmwareVersion, @NotNull String mUiVersion, @NotNull String mLanguageVersion, int i, @NotNull String mBleName, @NotNull String mPlatform, @NotNull String mPrototype, @NotNull String mFirmwareFlag, @NotNull String mFullVersion) {
        Intrinsics.checkNotNullParameter(mBleAddress, "mBleAddress");
        Intrinsics.checkNotNullParameter(mClassicAddress, "mClassicAddress");
        Intrinsics.checkNotNullParameter(mFirmwareVersion, "mFirmwareVersion");
        Intrinsics.checkNotNullParameter(mUiVersion, "mUiVersion");
        Intrinsics.checkNotNullParameter(mLanguageVersion, "mLanguageVersion");
        Intrinsics.checkNotNullParameter(mBleName, "mBleName");
        Intrinsics.checkNotNullParameter(mPlatform, "mPlatform");
        Intrinsics.checkNotNullParameter(mPrototype, "mPrototype");
        Intrinsics.checkNotNullParameter(mFirmwareFlag, "mFirmwareFlag");
        Intrinsics.checkNotNullParameter(mFullVersion, "mFullVersion");
        this.mBleAddress = mBleAddress;
        this.mClassicAddress = mClassicAddress;
        this.mFirmwareVersion = mFirmwareVersion;
        this.mUiVersion = mUiVersion;
        this.mLanguageVersion = mLanguageVersion;
        this.mLanguageCode = i;
        this.mBleName = mBleName;
        this.mPlatform = mPlatform;
        this.mPrototype = mPrototype;
        this.mFirmwareFlag = mFirmwareFlag;
        this.mFullVersion = mFullVersion;
    }
}
