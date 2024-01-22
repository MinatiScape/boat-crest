package com.mappls.sdk.direction.ui.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class c extends b {
    public static final Parcelable.Creator<c> CREATOR = new a();

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<c> {
        @Override // android.os.Parcelable.Creator
        public final c createFromParcel(Parcel parcel) {
            Boolean bool;
            Boolean bool2;
            Boolean bool3;
            Boolean valueOf = Boolean.valueOf(parcel.readInt() == 1);
            Boolean valueOf2 = Boolean.valueOf(parcel.readInt() == 1);
            Integer valueOf3 = Integer.valueOf(parcel.readInt());
            Integer valueOf4 = Integer.valueOf(parcel.readInt());
            Integer valueOf5 = Integer.valueOf(parcel.readInt());
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            ArrayList readArrayList = parcel.readArrayList(DirectionOptions.class.getClassLoader());
            Boolean valueOf6 = Boolean.valueOf(parcel.readInt() == 1);
            if (parcel.readInt() == 0) {
                bool = Boolean.valueOf(parcel.readInt() == 1);
            } else {
                bool = null;
            }
            ArrayList readArrayList2 = parcel.readArrayList(DirectionOptions.class.getClassLoader());
            Integer valueOf7 = parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null;
            if (parcel.readInt() == 0) {
                bool2 = Boolean.valueOf(parcel.readInt() == 1);
            } else {
                bool2 = null;
            }
            String readString4 = parcel.readString();
            if (parcel.readInt() == 0) {
                bool3 = Boolean.valueOf(parcel.readInt() == 1);
            } else {
                bool3 = null;
            }
            return new c(valueOf, valueOf2, valueOf3, valueOf4, valueOf5, readString, readString2, readString3, readArrayList, valueOf6, bool, readArrayList2, valueOf7, bool2, readString4, bool3, Boolean.valueOf(parcel.readInt() == 1), (PlaceOptions) parcel.readParcelable(DirectionOptions.class.getClassLoader()), (DirectionPoint) parcel.readParcelable(DirectionOptions.class.getClassLoader()), (DirectionPoint) parcel.readParcelable(DirectionOptions.class.getClassLoader()), Boolean.valueOf(parcel.readInt() == 1), Boolean.valueOf(parcel.readInt() == 1), parcel.readInt() == 0 ? Integer.valueOf(parcel.readInt()) : null, Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Integer.valueOf(parcel.readInt()), Boolean.valueOf(parcel.readInt() == 1), Boolean.valueOf(parcel.readInt() == 1), Boolean.valueOf(parcel.readInt() == 1), Boolean.valueOf(parcel.readInt() == 1));
        }

        @Override // android.os.Parcelable.Creator
        public final c[] newArray(int i) {
            return new c[i];
        }
    }

    public c(Boolean bool, Boolean bool2, Integer num, Integer num2, Integer num3, String str, String str2, String str3, @Nullable List<String> list, Boolean bool3, @Nullable Boolean bool4, @Nullable List<String> list2, @Nullable Integer num4, @Nullable Boolean bool5, String str4, @Nullable Boolean bool6, Boolean bool7, PlaceOptions placeOptions, @Nullable DirectionPoint directionPoint, @Nullable DirectionPoint directionPoint2, Boolean bool8, Boolean bool9, @Nullable Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, Integer num14, Integer num15, Integer num16, Boolean bool10, Boolean bool11, Boolean bool12, Boolean bool13) {
        super(bool, bool2, num, num2, num3, str, str2, str3, list, bool3, bool4, list2, num4, bool5, str4, bool6, bool7, placeOptions, directionPoint, directionPoint2, bool8, bool9, num5, num6, num7, num8, num9, num10, num11, num12, num13, num14, num15, num16, bool10, bool11, bool12, bool13);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(showProfileOption().booleanValue() ? 1 : 0);
        parcel.writeInt(showAlternative().booleanValue() ? 1 : 0);
        parcel.writeInt(theme().intValue());
        parcel.writeInt(directionDayTheme().intValue());
        parcel.writeInt(directionDarkTheme().intValue());
        parcel.writeString(resource());
        parcel.writeString(profile());
        parcel.writeString(overview());
        parcel.writeList(annotation());
        parcel.writeInt(steps().booleanValue() ? 1 : 0);
        if (isSort() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(isSort().booleanValue() ? 1 : 0);
        }
        parcel.writeList(excludes());
        if (routeType() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(routeType().intValue());
        }
        if (lessVerbose() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(lessVerbose().booleanValue() ? 1 : 0);
        }
        parcel.writeString(geometries());
        if (instructions() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(instructions().booleanValue() ? 1 : 0);
        }
        parcel.writeInt(showStartNavigation().booleanValue() ? 1 : 0);
        parcel.writeParcelable(searchPlaceOption(), i);
        parcel.writeParcelable(destination(), i);
        parcel.writeParcelable(origin(), i);
        parcel.writeInt(showDefaultMap().booleanValue() ? 1 : 0);
        parcel.writeInt(searchAlongRoute().booleanValue() ? 1 : 0);
        if (alongRouteBuffer() == null) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
            parcel.writeInt(alongRouteBuffer().intValue());
        }
        parcel.writeInt(alongRouteDayTheme().intValue());
        parcel.writeInt(alongRouteDarkTheme().intValue());
        parcel.writeInt(selectedRouteColor().intValue());
        parcel.writeInt(selectedCasingRouteColor().intValue());
        parcel.writeInt(alternateRouteColor().intValue());
        parcel.writeInt(alternateCasingRouteColor().intValue());
        parcel.writeInt(destinationMarker().intValue());
        parcel.writeInt(sourceMarker().intValue());
        parcel.writeInt(firstWayPointMarker().intValue());
        parcel.writeInt(secondWayPointMarker().intValue());
        parcel.writeInt(thirdWayPointMarker().intValue());
        parcel.writeInt(showAddWaypointOption().booleanValue() ? 1 : 0);
        parcel.writeInt(showRouteReportSummary().booleanValue() ? 1 : 0);
        parcel.writeInt(showRouteReportSummaryOnMap().booleanValue() ? 1 : 0);
        parcel.writeInt(showTripCostSummary().booleanValue() ? 1 : 0);
    }
}
