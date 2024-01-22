package com.coveiot.android.leonardo.sensai.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SensAICompareData implements Parcelable {
    @NotNull
    public static final CREATOR CREATOR = new CREATOR(null);
    @Nullable
    private ArrayList<SensAICompareListItem> compareList;
    @Nullable
    private String headerTitle;

    /* loaded from: classes5.dex */
    public static final class CREATOR implements Parcelable.Creator<SensAICompareData> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public SensAICompareData createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SensAICompareData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public SensAICompareData[] newArray(int i) {
            return new SensAICompareData[i];
        }
    }

    public SensAICompareData(@Nullable String str, @Nullable ArrayList<SensAICompareListItem> arrayList) {
        this.headerTitle = str;
        this.compareList = arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SensAICompareData copy$default(SensAICompareData sensAICompareData, String str, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sensAICompareData.headerTitle;
        }
        if ((i & 2) != 0) {
            arrayList = sensAICompareData.compareList;
        }
        return sensAICompareData.copy(str, arrayList);
    }

    @Nullable
    public final String component1() {
        return this.headerTitle;
    }

    @Nullable
    public final ArrayList<SensAICompareListItem> component2() {
        return this.compareList;
    }

    @NotNull
    public final SensAICompareData copy(@Nullable String str, @Nullable ArrayList<SensAICompareListItem> arrayList) {
        return new SensAICompareData(str, arrayList);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SensAICompareData) {
            SensAICompareData sensAICompareData = (SensAICompareData) obj;
            return Intrinsics.areEqual(this.headerTitle, sensAICompareData.headerTitle) && Intrinsics.areEqual(this.compareList, sensAICompareData.compareList);
        }
        return false;
    }

    @Nullable
    public final ArrayList<SensAICompareListItem> getCompareList() {
        return this.compareList;
    }

    @Nullable
    public final String getHeaderTitle() {
        return this.headerTitle;
    }

    public int hashCode() {
        String str = this.headerTitle;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        ArrayList<SensAICompareListItem> arrayList = this.compareList;
        return hashCode + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public final void setCompareList(@Nullable ArrayList<SensAICompareListItem> arrayList) {
        this.compareList = arrayList;
    }

    public final void setHeaderTitle(@Nullable String str) {
        this.headerTitle = str;
    }

    @NotNull
    public String toString() {
        return "SensAICompareData(headerTitle=" + this.headerTitle + ", compareList=" + this.compareList + HexStringBuilder.COMMENT_END_CHAR;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel dest, int i) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.headerTitle);
        dest.writeTypedList(this.compareList);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SensAICompareData(@NotNull Parcel parcel) {
        this(parcel.readString(), parcel.createTypedArrayList(SensAICompareListItem.CREATOR));
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }
}
