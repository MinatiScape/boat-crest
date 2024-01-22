package com.coveiot.android.leonardo.sensai.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RequiresApi;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SensAICompareListItem implements Parcelable {
    @NotNull
    public static final CREATOR CREATOR = new CREATOR(null);
    @Nullable
    private Integer image;
    @Nullable
    private String name;
    @Nullable
    private Integer value1;
    @Nullable
    private Integer value2;

    /* loaded from: classes5.dex */
    public static final class CREATOR implements Parcelable.Creator<SensAICompareListItem> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public SensAICompareListItem createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SensAICompareListItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public SensAICompareListItem[] newArray(int i) {
            return new SensAICompareListItem[i];
        }
    }

    public SensAICompareListItem(@Nullable String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        this.name = str;
        this.image = num;
        this.value1 = num2;
        this.value2 = num3;
    }

    public static /* synthetic */ SensAICompareListItem copy$default(SensAICompareListItem sensAICompareListItem, String str, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sensAICompareListItem.name;
        }
        if ((i & 2) != 0) {
            num = sensAICompareListItem.image;
        }
        if ((i & 4) != 0) {
            num2 = sensAICompareListItem.value1;
        }
        if ((i & 8) != 0) {
            num3 = sensAICompareListItem.value2;
        }
        return sensAICompareListItem.copy(str, num, num2, num3);
    }

    @Nullable
    public final String component1() {
        return this.name;
    }

    @Nullable
    public final Integer component2() {
        return this.image;
    }

    @Nullable
    public final Integer component3() {
        return this.value1;
    }

    @Nullable
    public final Integer component4() {
        return this.value2;
    }

    @NotNull
    public final SensAICompareListItem copy(@Nullable String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        return new SensAICompareListItem(str, num, num2, num3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SensAICompareListItem) {
            SensAICompareListItem sensAICompareListItem = (SensAICompareListItem) obj;
            return Intrinsics.areEqual(this.name, sensAICompareListItem.name) && Intrinsics.areEqual(this.image, sensAICompareListItem.image) && Intrinsics.areEqual(this.value1, sensAICompareListItem.value1) && Intrinsics.areEqual(this.value2, sensAICompareListItem.value2);
        }
        return false;
    }

    @Nullable
    public final Integer getImage() {
        return this.image;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    @Nullable
    public final Integer getValue1() {
        return this.value1;
    }

    @Nullable
    public final Integer getValue2() {
        return this.value2;
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.image;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.value1;
        int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.value2;
        return hashCode3 + (num3 != null ? num3.hashCode() : 0);
    }

    public final void setImage(@Nullable Integer num) {
        this.image = num;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setValue1(@Nullable Integer num) {
        this.value1 = num;
    }

    public final void setValue2(@Nullable Integer num) {
        this.value2 = num;
    }

    @NotNull
    public String toString() {
        return "SensAICompareListItem(name=" + this.name + ", image=" + this.image + ", value1=" + this.value1 + ", value2=" + this.value2 + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // android.os.Parcelable
    @RequiresApi(29)
    public void writeToParcel(@NotNull Parcel dest, int i) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.name);
        Integer num = this.image;
        Intrinsics.checkNotNull(num);
        dest.writeInt(num.intValue());
        Integer num2 = this.value1;
        Intrinsics.checkNotNull(num2);
        dest.writeInt(num2.intValue());
        Integer num3 = this.value2;
        Intrinsics.checkNotNull(num3);
        dest.writeInt(num3.intValue());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SensAICompareListItem(@NotNull Parcel parcel) {
        this(parcel.readString(), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()));
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }
}
