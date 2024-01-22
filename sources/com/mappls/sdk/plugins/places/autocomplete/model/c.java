package com.mappls.sdk.plugins.places.autocomplete.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.mappls.sdk.geojson.Point;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public final class c extends b {
    public static final Parcelable.Creator<c> CREATOR = new a();

    /* loaded from: classes10.dex */
    public class a implements Parcelable.Creator<c> {
        @Override // android.os.Parcelable.Creator
        public final c createFromParcel(Parcel parcel) {
            Boolean bool;
            Boolean bool2;
            Boolean bool3;
            Point point = parcel.readInt() == 0 ? (Point) parcel.readSerializable() : null;
            Boolean valueOf = Boolean.valueOf(parcel.readInt() == 1);
            String readString = parcel.readInt() == 0 ? parcel.readString() : null;
            int readInt = parcel.readInt();
            Integer valueOf2 = parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null;
            Integer valueOf3 = parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null;
            Double valueOf4 = parcel.readInt() == 0 ? Double.valueOf(parcel.readDouble()) : null;
            Boolean valueOf5 = Boolean.valueOf(parcel.readInt() == 1);
            Boolean valueOf6 = Boolean.valueOf(parcel.readInt() == 1);
            String readString2 = parcel.readInt() == 0 ? parcel.readString() : null;
            if (parcel.readInt() == 0) {
                bool = Boolean.valueOf(parcel.readInt() == 1);
            } else {
                bool = null;
            }
            ArrayList readArrayList = parcel.readArrayList(PlaceOptions.class.getClassLoader());
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            int readInt4 = parcel.readInt();
            Integer valueOf7 = parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null;
            Boolean valueOf8 = Boolean.valueOf(parcel.readInt() == 1);
            int readInt5 = parcel.readInt();
            int readInt6 = parcel.readInt();
            int readInt7 = parcel.readInt();
            int readInt8 = parcel.readInt();
            Integer valueOf9 = Integer.valueOf(parcel.readInt());
            String readString3 = parcel.readInt() == 0 ? parcel.readString() : null;
            if (parcel.readInt() == 0) {
                bool2 = Boolean.valueOf(parcel.readInt() == 1);
            } else {
                bool2 = null;
            }
            if (parcel.readInt() == 0) {
                bool3 = Boolean.valueOf(parcel.readInt() == 1);
            } else {
                bool3 = null;
            }
            return new c(point, valueOf, readString, readInt, valueOf2, valueOf3, valueOf4, valueOf5, valueOf6, readString2, bool, readArrayList, readInt2, readInt3, readInt4, valueOf7, valueOf8, readInt5, readInt6, readInt7, readInt8, valueOf9, readString3, bool2, bool3, Boolean.valueOf(parcel.readInt() == 1), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? parcel.readString() : null);
        }

        @Override // android.os.Parcelable.Creator
        public final c[] newArray(int i) {
            return new c[i];
        }
    }

    public c(@Nullable Point point, Boolean bool, @Nullable String str, int i, @Nullable Integer num, @Nullable Integer num2, @Nullable Double d, Boolean bool2, Boolean bool3, @Nullable String str2, @Nullable Boolean bool4, @Nullable List<String> list, int i2, int i3, int i4, @Nullable Integer num3, Boolean bool5, int i5, int i6, int i7, int i8, Integer num4, @Nullable String str3, @Nullable Boolean bool6, @Nullable Boolean bool7, Boolean bool8, Integer num5, Integer num6, Integer num7, @Nullable String str4) {
        super(point, bool, str, i, num, num2, d, bool2, bool3, str2, bool4, list, i2, i3, i4, num3, bool5, i5, i6, i7, i8, num4, str3, bool6, bool7, bool8, num5, num6, num7, str4);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        if (location() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeSerializable(location());
        }
        parcel.writeInt(userAddedLocationEnable().booleanValue() ? 1 : 0);
        if (filter() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(filter());
        }
        parcel.writeInt(limit());
        if (historyCount() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(historyCount().intValue());
        }
        if (favoriteCount() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(favoriteCount().intValue());
        }
        if (zoom() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeDouble(zoom().doubleValue());
        }
        parcel.writeInt(saveHistory().booleanValue() ? 1 : 0);
        parcel.writeInt(enableTextSearch().booleanValue() ? 1 : 0);
        if (pod() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(pod());
        }
        if (tokenizeAddress() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(tokenizeAddress().booleanValue() ? 1 : 0);
        }
        parcel.writeList(injectedPlaces());
        parcel.writeInt(viewMode());
        parcel.writeInt(backgroundColor());
        parcel.writeInt(toolbarColor());
        if (statusBarColor() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(statusBarColor().intValue());
        }
        parcel.writeInt(showPoweredByText().booleanValue() ? 1 : 0);
        parcel.writeInt(toolbarTintColor());
        parcel.writeInt(attributionVerticalAlignment());
        parcel.writeInt(attributionHorizontalAlignment());
        parcel.writeInt(logoSize());
        parcel.writeInt(internalMinCharactersForSearch().intValue());
        if (hint() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeString(hint());
        }
        if (hyperLocal() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(hyperLocal().booleanValue() ? 1 : 0);
        }
        if (bridge() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(bridge().booleanValue() ? 1 : 0);
        }
        parcel.writeInt(isShowCurrentLocation().booleanValue() ? 1 : 0);
        parcel.writeInt(currentLocationTextColor().intValue());
        parcel.writeInt(internalDebounce().intValue());
        parcel.writeInt(currentLocationIcon().intValue());
        if (responseLang() == null) {
            parcel.writeInt(1);
            return;
        }
        parcel.writeInt(0);
        parcel.writeString(responseLang());
    }
}
