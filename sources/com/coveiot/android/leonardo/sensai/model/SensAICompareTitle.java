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
public final class SensAICompareTitle implements Parcelable {
    @NotNull
    public static final CREATOR CREATOR = new CREATOR(null);
    @Nullable
    private Integer image;
    @Nullable
    private String name;
    @Nullable
    private String value1;

    /* loaded from: classes5.dex */
    public static final class CREATOR implements Parcelable.Creator<SensAICompareTitle> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public SensAICompareTitle createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SensAICompareTitle(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public SensAICompareTitle[] newArray(int i) {
            return new SensAICompareTitle[i];
        }
    }

    public SensAICompareTitle(@Nullable String str, @Nullable Integer num, @Nullable String str2) {
        this.name = str;
        this.image = num;
        this.value1 = str2;
    }

    public static /* synthetic */ SensAICompareTitle copy$default(SensAICompareTitle sensAICompareTitle, String str, Integer num, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sensAICompareTitle.name;
        }
        if ((i & 2) != 0) {
            num = sensAICompareTitle.image;
        }
        if ((i & 4) != 0) {
            str2 = sensAICompareTitle.value1;
        }
        return sensAICompareTitle.copy(str, num, str2);
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
    public final String component3() {
        return this.value1;
    }

    @NotNull
    public final SensAICompareTitle copy(@Nullable String str, @Nullable Integer num, @Nullable String str2) {
        return new SensAICompareTitle(str, num, str2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SensAICompareTitle) {
            SensAICompareTitle sensAICompareTitle = (SensAICompareTitle) obj;
            return Intrinsics.areEqual(this.name, sensAICompareTitle.name) && Intrinsics.areEqual(this.image, sensAICompareTitle.image) && Intrinsics.areEqual(this.value1, sensAICompareTitle.value1);
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
    public final String getValue1() {
        return this.value1;
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.image;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.value1;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final void setImage(@Nullable Integer num) {
        this.image = num;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setValue1(@Nullable String str) {
        this.value1 = str;
    }

    @NotNull
    public String toString() {
        return "SensAICompareTitle(name=" + this.name + ", image=" + this.image + ", value1=" + this.value1 + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // android.os.Parcelable
    @RequiresApi(29)
    public void writeToParcel(@NotNull Parcel dest, int i) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.name);
        Integer num = this.image;
        Intrinsics.checkNotNull(num);
        dest.writeInt(num.intValue());
        String str = this.value1;
        Intrinsics.checkNotNull(str);
        dest.writeString(str);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SensAICompareTitle(@NotNull Parcel parcel) {
        this(parcel.readString(), Integer.valueOf(parcel.readInt()), parcel.readString());
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }
}
