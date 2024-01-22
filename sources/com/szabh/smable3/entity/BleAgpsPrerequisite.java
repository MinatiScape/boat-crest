package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleAgpsPrerequisite extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 32;
    private int mAltitude;
    private float mLatitude;
    private float mLongitude;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleAgpsPrerequisite() {
        this(0.0f, 0.0f, 0, 7, null);
    }

    public /* synthetic */ BleAgpsPrerequisite(float f, float f2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0.0f : f, (i2 & 2) != 0 ? 0.0f : f2, (i2 & 4) != 0 ? 0 : i);
    }

    public static /* synthetic */ BleAgpsPrerequisite copy$default(BleAgpsPrerequisite bleAgpsPrerequisite, float f, float f2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            f = bleAgpsPrerequisite.mLongitude;
        }
        if ((i2 & 2) != 0) {
            f2 = bleAgpsPrerequisite.mLatitude;
        }
        if ((i2 & 4) != 0) {
            i = bleAgpsPrerequisite.mAltitude;
        }
        return bleAgpsPrerequisite.copy(f, f2, i);
    }

    public final float component1() {
        return this.mLongitude;
    }

    public final float component2() {
        return this.mLatitude;
    }

    public final int component3() {
        return this.mAltitude;
    }

    @NotNull
    public final BleAgpsPrerequisite copy(float f, float f2, int i) {
        return new BleAgpsPrerequisite(f, f2, i);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        float f = this.mLongitude;
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeFloat(f, LITTLE_ENDIAN);
        float f2 = this.mLatitude;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeFloat(f2, LITTLE_ENDIAN);
        int i = this.mAltitude;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeInt16(i, LITTLE_ENDIAN);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleAgpsPrerequisite) {
            BleAgpsPrerequisite bleAgpsPrerequisite = (BleAgpsPrerequisite) obj;
            return Float.compare(this.mLongitude, bleAgpsPrerequisite.mLongitude) == 0 && Float.compare(this.mLatitude, bleAgpsPrerequisite.mLatitude) == 0 && this.mAltitude == bleAgpsPrerequisite.mAltitude;
        }
        return false;
    }

    public final int getMAltitude() {
        return this.mAltitude;
    }

    public final float getMLatitude() {
        return this.mLatitude;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 32;
    }

    public final float getMLongitude() {
        return this.mLongitude;
    }

    public int hashCode() {
        return (((Float.hashCode(this.mLongitude) * 31) + Float.hashCode(this.mLatitude)) * 31) + Integer.hashCode(this.mAltitude);
    }

    public final void setMAltitude(int i) {
        this.mAltitude = i;
    }

    public final void setMLatitude(float f) {
        this.mLatitude = f;
    }

    public final void setMLongitude(float f) {
        this.mLongitude = f;
    }

    @NotNull
    public String toString() {
        return "BleAgpsPrerequisite(mLongitude=" + this.mLongitude + ", mLatitude=" + this.mLatitude + ", mAltitude=" + this.mAltitude + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleAgpsPrerequisite(float f, float f2, int i) {
        this.mLongitude = f;
        this.mLatitude = f2;
        this.mAltitude = i;
    }
}
