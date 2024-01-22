package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleUserProfile extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int FEMALE = 0;
    public static final int IMPERIAL = 1;
    public static final int ITEM_LENGTH = 11;
    public static final int MALE = 1;
    public static final int METRIC = 0;
    private int mAge;
    private int mGender;
    private float mHeight;
    private int mUnit;
    private float mWeight;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleUserProfile() {
        this(0, 0, 0, 0.0f, 0.0f, 31, null);
    }

    public /* synthetic */ BleUserProfile(int i, int i2, int i3, float f, float f2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) == 0 ? i2 : 0, (i4 & 4) != 0 ? 20 : i3, (i4 & 8) != 0 ? 170.0f : f, (i4 & 16) != 0 ? 60.0f : f2);
    }

    public static /* synthetic */ BleUserProfile copy$default(BleUserProfile bleUserProfile, int i, int i2, int i3, float f, float f2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = bleUserProfile.mUnit;
        }
        if ((i4 & 2) != 0) {
            i2 = bleUserProfile.mGender;
        }
        int i5 = i2;
        if ((i4 & 4) != 0) {
            i3 = bleUserProfile.mAge;
        }
        int i6 = i3;
        if ((i4 & 8) != 0) {
            f = bleUserProfile.mHeight;
        }
        float f3 = f;
        if ((i4 & 16) != 0) {
            f2 = bleUserProfile.mWeight;
        }
        return bleUserProfile.copy(i, i5, i6, f3, f2);
    }

    public final int component1() {
        return this.mUnit;
    }

    public final int component2() {
        return this.mGender;
    }

    public final int component3() {
        return this.mAge;
    }

    public final float component4() {
        return this.mHeight;
    }

    public final float component5() {
        return this.mWeight;
    }

    @NotNull
    public final BleUserProfile copy(int i, int i2, int i3, float f, float f2) {
        return new BleUserProfile(i, i2, i3, f, f2);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mUnit = readUInt8();
        this.mGender = readUInt8();
        this.mAge = readUInt8();
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mHeight = readFloat(LITTLE_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mWeight = readFloat(LITTLE_ENDIAN);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mUnit);
        writeInt8(this.mGender);
        writeInt8(this.mAge);
        float f = this.mHeight;
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeFloat(f, LITTLE_ENDIAN);
        float f2 = this.mWeight;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeFloat(f2, LITTLE_ENDIAN);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleUserProfile) {
            BleUserProfile bleUserProfile = (BleUserProfile) obj;
            return this.mUnit == bleUserProfile.mUnit && this.mGender == bleUserProfile.mGender && this.mAge == bleUserProfile.mAge && Float.compare(this.mHeight, bleUserProfile.mHeight) == 0 && Float.compare(this.mWeight, bleUserProfile.mWeight) == 0;
        }
        return false;
    }

    public final int getMAge() {
        return this.mAge;
    }

    public final int getMGender() {
        return this.mGender;
    }

    public final float getMHeight() {
        return this.mHeight;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 11;
    }

    public final int getMUnit() {
        return this.mUnit;
    }

    public final float getMWeight() {
        return this.mWeight;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.mUnit) * 31) + Integer.hashCode(this.mGender)) * 31) + Integer.hashCode(this.mAge)) * 31) + Float.hashCode(this.mHeight)) * 31) + Float.hashCode(this.mWeight);
    }

    public final void setMAge(int i) {
        this.mAge = i;
    }

    public final void setMGender(int i) {
        this.mGender = i;
    }

    public final void setMHeight(float f) {
        this.mHeight = f;
    }

    public final void setMUnit(int i) {
        this.mUnit = i;
    }

    public final void setMWeight(float f) {
        this.mWeight = f;
    }

    @NotNull
    public String toString() {
        return "BleUserProfile(mUnit=" + this.mUnit + ", mGender=" + this.mGender + ", mAge=" + this.mAge + ", mHeight=" + this.mHeight + ", mWeight=" + this.mWeight + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleUserProfile(int i, int i2, int i3, float f, float f2) {
        this.mUnit = i;
        this.mGender = i2;
        this.mAge = i3;
        this.mHeight = f;
        this.mWeight = f2;
    }
}
