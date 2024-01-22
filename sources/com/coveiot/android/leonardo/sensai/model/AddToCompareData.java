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
public final class AddToCompareData implements Parcelable {
    @NotNull
    public static final CREATOR CREATOR = new CREATOR(null);
    @Nullable
    private Integer image1;
    @Nullable
    private Integer image2;
    @Nullable
    private Boolean isHighlight1;
    @Nullable
    private Boolean isHighlight2;
    @Nullable
    private String name1;
    @Nullable
    private String name2;
    @Nullable
    private String value1;
    @Nullable
    private String value2;

    /* loaded from: classes5.dex */
    public static final class CREATOR implements Parcelable.Creator<AddToCompareData> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public AddToCompareData createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new AddToCompareData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public AddToCompareData[] newArray(int i) {
            return new AddToCompareData[i];
        }
    }

    public AddToCompareData(@Nullable String str, @Nullable Integer num, @Nullable String str2, @Nullable Boolean bool, @Nullable String str3, @Nullable Integer num2, @Nullable String str4, @Nullable Boolean bool2) {
        this.name1 = str;
        this.image1 = num;
        this.value1 = str2;
        this.isHighlight1 = bool;
        this.name2 = str3;
        this.image2 = num2;
        this.value2 = str4;
        this.isHighlight2 = bool2;
    }

    @Nullable
    public final String component1() {
        return this.name1;
    }

    @Nullable
    public final Integer component2() {
        return this.image1;
    }

    @Nullable
    public final String component3() {
        return this.value1;
    }

    @Nullable
    public final Boolean component4() {
        return this.isHighlight1;
    }

    @Nullable
    public final String component5() {
        return this.name2;
    }

    @Nullable
    public final Integer component6() {
        return this.image2;
    }

    @Nullable
    public final String component7() {
        return this.value2;
    }

    @Nullable
    public final Boolean component8() {
        return this.isHighlight2;
    }

    @NotNull
    public final AddToCompareData copy(@Nullable String str, @Nullable Integer num, @Nullable String str2, @Nullable Boolean bool, @Nullable String str3, @Nullable Integer num2, @Nullable String str4, @Nullable Boolean bool2) {
        return new AddToCompareData(str, num, str2, bool, str3, num2, str4, bool2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddToCompareData) {
            AddToCompareData addToCompareData = (AddToCompareData) obj;
            return Intrinsics.areEqual(this.name1, addToCompareData.name1) && Intrinsics.areEqual(this.image1, addToCompareData.image1) && Intrinsics.areEqual(this.value1, addToCompareData.value1) && Intrinsics.areEqual(this.isHighlight1, addToCompareData.isHighlight1) && Intrinsics.areEqual(this.name2, addToCompareData.name2) && Intrinsics.areEqual(this.image2, addToCompareData.image2) && Intrinsics.areEqual(this.value2, addToCompareData.value2) && Intrinsics.areEqual(this.isHighlight2, addToCompareData.isHighlight2);
        }
        return false;
    }

    @Nullable
    public final Integer getImage1() {
        return this.image1;
    }

    @Nullable
    public final Integer getImage2() {
        return this.image2;
    }

    @Nullable
    public final String getName1() {
        return this.name1;
    }

    @Nullable
    public final String getName2() {
        return this.name2;
    }

    @Nullable
    public final String getValue1() {
        return this.value1;
    }

    @Nullable
    public final String getValue2() {
        return this.value2;
    }

    public int hashCode() {
        String str = this.name1;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.image1;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.value1;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.isHighlight1;
        int hashCode4 = (hashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str3 = this.name2;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num2 = this.image2;
        int hashCode6 = (hashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str4 = this.value2;
        int hashCode7 = (hashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Boolean bool2 = this.isHighlight2;
        return hashCode7 + (bool2 != null ? bool2.hashCode() : 0);
    }

    @Nullable
    public final Boolean isHighlight1() {
        return this.isHighlight1;
    }

    @Nullable
    public final Boolean isHighlight2() {
        return this.isHighlight2;
    }

    public final void setHighlight1(@Nullable Boolean bool) {
        this.isHighlight1 = bool;
    }

    public final void setHighlight2(@Nullable Boolean bool) {
        this.isHighlight2 = bool;
    }

    public final void setImage1(@Nullable Integer num) {
        this.image1 = num;
    }

    public final void setImage2(@Nullable Integer num) {
        this.image2 = num;
    }

    public final void setName1(@Nullable String str) {
        this.name1 = str;
    }

    public final void setName2(@Nullable String str) {
        this.name2 = str;
    }

    public final void setValue1(@Nullable String str) {
        this.value1 = str;
    }

    public final void setValue2(@Nullable String str) {
        this.value2 = str;
    }

    @NotNull
    public String toString() {
        return "AddToCompareData(name1=" + this.name1 + ", image1=" + this.image1 + ", value1=" + this.value1 + ", isHighlight1=" + this.isHighlight1 + ", name2=" + this.name2 + ", image2=" + this.image2 + ", value2=" + this.value2 + ", isHighlight2=" + this.isHighlight2 + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // android.os.Parcelable
    @RequiresApi(29)
    public void writeToParcel(@NotNull Parcel dest, int i) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.name1);
        Integer num = this.image1;
        Intrinsics.checkNotNull(num);
        dest.writeInt(num.intValue());
        dest.writeString(this.value1);
        Boolean bool = this.isHighlight1;
        Intrinsics.checkNotNull(bool);
        dest.writeBoolean(bool.booleanValue());
        dest.writeString(this.name2);
        Integer num2 = this.image2;
        Intrinsics.checkNotNull(num2);
        dest.writeInt(num2.intValue());
        dest.writeString(this.value2);
        Boolean bool2 = this.isHighlight2;
        Intrinsics.checkNotNull(bool2);
        dest.writeBoolean(bool2.booleanValue());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AddToCompareData(@NotNull Parcel parcel) {
        this(parcel.readString(), Integer.valueOf(parcel.readInt()), parcel.readString(), Boolean.valueOf(parcel.readByte() != 0), parcel.readString(), Integer.valueOf(parcel.readInt()), parcel.readString(), Boolean.valueOf(parcel.readByte() != 0));
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }
}
