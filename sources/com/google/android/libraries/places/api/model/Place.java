package com.google.android.libraries.places.api.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.internal.zzft;
import com.google.android.libraries.places.internal.zzfz;
import com.google.android.libraries.places.internal.zzgi;
import com.google.android.libraries.places.internal.zzgr;
import com.google.auto.value.AutoValue;
import java.util.Collection;
import java.util.List;
@AutoValue
/* loaded from: classes10.dex */
public abstract class Place implements Parcelable {
    public static final int PRICE_LEVEL_MAX_VALUE = 4;
    public static final int PRICE_LEVEL_MIN_VALUE = 0;
    public static final double RATING_MAX_VALUE = 5.0d;
    public static final double RATING_MIN_VALUE = 1.0d;

    @AutoValue.Builder
    /* loaded from: classes10.dex */
    public static abstract class Builder {
        @NonNull
        public Place build() {
            Place zza = zza();
            List<String> attributions = zza.getAttributions();
            if (attributions != null) {
                for (String str : attributions) {
                    zzft.zzb(!TextUtils.isEmpty(str), "Attributions must not contain null or empty values.");
                }
            }
            Integer priceLevel = zza.getPriceLevel();
            if (priceLevel != null) {
                zzft.zza(zzgr.zza(0, 4).zzb(priceLevel), "Price Level must not be out-of-range: %s to %s, but was: %s.", 0, 4, priceLevel);
            }
            Double rating = zza.getRating();
            if (rating != null) {
                zzft.zza(zzgr.zza(Double.valueOf(1.0d), Double.valueOf(5.0d)).zzb(rating), "Rating must not be out-of-range: %s to %s, but was: %s.", Double.valueOf(1.0d), Double.valueOf(5.0d), rating);
            }
            Integer userRatingsTotal = zza.getUserRatingsTotal();
            if (userRatingsTotal != null && !zzgr.zza(0).zzb(userRatingsTotal)) {
                throw new IllegalStateException(zzfz.zza("User Ratings Total must not be < 0, but was: %s.", userRatingsTotal));
            }
            if (attributions != null) {
                setAttributions(zzgi.zza((Collection) attributions));
            }
            List<PhotoMetadata> photoMetadatas = zza.getPhotoMetadatas();
            if (photoMetadatas != null) {
                setPhotoMetadatas(zzgi.zza((Collection) photoMetadatas));
            }
            List<Type> types = zza.getTypes();
            if (types != null) {
                setTypes(zzgi.zza((Collection) types));
            }
            return zza();
        }

        @Nullable
        public abstract String getAddress();

        @Nullable
        public abstract AddressComponents getAddressComponents();

        @Nullable
        public abstract List<String> getAttributions();

        @Nullable
        public abstract BusinessStatus getBusinessStatus();

        @Nullable
        public abstract String getId();

        @Nullable
        public abstract LatLng getLatLng();

        @Nullable
        public abstract String getName();

        @Nullable
        public abstract OpeningHours getOpeningHours();

        @Nullable
        public abstract String getPhoneNumber();

        @Nullable
        public abstract List<PhotoMetadata> getPhotoMetadatas();

        @Nullable
        public abstract PlusCode getPlusCode();

        @IntRange(from = 0, to = 4)
        @Nullable
        public abstract Integer getPriceLevel();

        @Nullable
        @FloatRange(from = 1.0d, to = Place.RATING_MAX_VALUE)
        public abstract Double getRating();

        @Nullable
        public abstract List<Type> getTypes();

        @IntRange(from = 0)
        @Nullable
        public abstract Integer getUserRatingsTotal();

        @Nullable
        public abstract Integer getUtcOffsetMinutes();

        @Nullable
        public abstract LatLngBounds getViewport();

        @Nullable
        public abstract Uri getWebsiteUri();

        @NonNull
        public abstract Builder setAddress(@Nullable String str);

        @NonNull
        public abstract Builder setAddressComponents(@Nullable AddressComponents addressComponents);

        @NonNull
        public abstract Builder setAttributions(@Nullable List<String> list);

        @NonNull
        public abstract Builder setBusinessStatus(@Nullable BusinessStatus businessStatus);

        @NonNull
        public abstract Builder setId(@Nullable String str);

        @NonNull
        public abstract Builder setLatLng(@Nullable LatLng latLng);

        @NonNull
        public abstract Builder setName(@Nullable String str);

        @NonNull
        public abstract Builder setOpeningHours(@Nullable OpeningHours openingHours);

        @NonNull
        public abstract Builder setPhoneNumber(@Nullable String str);

        @NonNull
        public abstract Builder setPhotoMetadatas(@Nullable List<PhotoMetadata> list);

        @NonNull
        public abstract Builder setPlusCode(@Nullable PlusCode plusCode);

        @NonNull
        public abstract Builder setPriceLevel(@IntRange(from = 0, to = 4) @Nullable Integer num);

        @NonNull
        public abstract Builder setRating(@Nullable @FloatRange(from = 1.0d, to = 5.0d) Double d);

        @NonNull
        public abstract Builder setTypes(@Nullable List<Type> list);

        @NonNull
        public abstract Builder setUserRatingsTotal(@IntRange(from = 0) @Nullable Integer num);

        @NonNull
        public abstract Builder setUtcOffsetMinutes(@Nullable Integer num);

        @NonNull
        public abstract Builder setViewport(@Nullable LatLngBounds latLngBounds);

        @NonNull
        public abstract Builder setWebsiteUri(@Nullable Uri uri);

        @NonNull
        public abstract Place zza();
    }

    /* loaded from: classes10.dex */
    public enum BusinessStatus implements Parcelable {
        OPERATIONAL,
        CLOSED_TEMPORARILY,
        CLOSED_PERMANENTLY;
        
        @NonNull
        public static final Parcelable.Creator<BusinessStatus> CREATOR = new zzbd();

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name());
        }
    }

    /* loaded from: classes10.dex */
    public enum Field implements Parcelable {
        ADDRESS,
        ADDRESS_COMPONENTS,
        BUSINESS_STATUS,
        ID,
        LAT_LNG,
        NAME,
        OPENING_HOURS,
        PHONE_NUMBER,
        PHOTO_METADATAS,
        PLUS_CODE,
        PRICE_LEVEL,
        RATING,
        TYPES,
        USER_RATINGS_TOTAL,
        UTC_OFFSET,
        VIEWPORT,
        WEBSITE_URI;
        
        @NonNull
        public static final Parcelable.Creator<Field> CREATOR = new zzbe();

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name());
        }
    }

    /* loaded from: classes10.dex */
    public enum Type implements Parcelable {
        OTHER,
        ACCOUNTING,
        ADMINISTRATIVE_AREA_LEVEL_1,
        ADMINISTRATIVE_AREA_LEVEL_2,
        ADMINISTRATIVE_AREA_LEVEL_3,
        ADMINISTRATIVE_AREA_LEVEL_4,
        ADMINISTRATIVE_AREA_LEVEL_5,
        AIRPORT,
        AMUSEMENT_PARK,
        AQUARIUM,
        ARCHIPELAGO,
        ART_GALLERY,
        ATM,
        BAKERY,
        BANK,
        BAR,
        BEAUTY_SALON,
        BICYCLE_STORE,
        BOOK_STORE,
        BOWLING_ALLEY,
        BUS_STATION,
        CAFE,
        CAMPGROUND,
        CAR_DEALER,
        CAR_RENTAL,
        CAR_REPAIR,
        CAR_WASH,
        CASINO,
        CEMETERY,
        CHURCH,
        CITY_HALL,
        CLOTHING_STORE,
        COLLOQUIAL_AREA,
        CONTINENT,
        CONVENIENCE_STORE,
        COUNTRY,
        COURTHOUSE,
        DENTIST,
        DEPARTMENT_STORE,
        DOCTOR,
        DRUGSTORE,
        ELECTRICIAN,
        ELECTRONICS_STORE,
        EMBASSY,
        ESTABLISHMENT,
        FINANCE,
        FIRE_STATION,
        FLOOR,
        FLORIST,
        FOOD,
        FUNERAL_HOME,
        FURNITURE_STORE,
        GAS_STATION,
        GENERAL_CONTRACTOR,
        GEOCODE,
        GROCERY_OR_SUPERMARKET,
        GYM,
        HAIR_CARE,
        HARDWARE_STORE,
        HEALTH,
        HINDU_TEMPLE,
        HOME_GOODS_STORE,
        HOSPITAL,
        INSURANCE_AGENCY,
        INTERSECTION,
        JEWELRY_STORE,
        LAUNDRY,
        LAWYER,
        LIBRARY,
        LIGHT_RAIL_STATION,
        LIQUOR_STORE,
        LOCAL_GOVERNMENT_OFFICE,
        LOCALITY,
        LOCKSMITH,
        LODGING,
        MEAL_DELIVERY,
        MEAL_TAKEAWAY,
        MOSQUE,
        MOVIE_RENTAL,
        MOVIE_THEATER,
        MOVING_COMPANY,
        MUSEUM,
        NATURAL_FEATURE,
        NEIGHBORHOOD,
        NIGHT_CLUB,
        PAINTER,
        PARK,
        PARKING,
        PET_STORE,
        PHARMACY,
        PHYSIOTHERAPIST,
        PLACE_OF_WORSHIP,
        PLUMBER,
        PLUS_CODE,
        POINT_OF_INTEREST,
        POLICE,
        POLITICAL,
        POST_BOX,
        POST_OFFICE,
        POSTAL_CODE_PREFIX,
        POSTAL_CODE_SUFFIX,
        POSTAL_CODE,
        POSTAL_TOWN,
        PREMISE,
        PRIMARY_SCHOOL,
        REAL_ESTATE_AGENCY,
        RESTAURANT,
        ROOFING_CONTRACTOR,
        ROOM,
        ROUTE,
        RV_PARK,
        SCHOOL,
        SECONDARY_SCHOOL,
        SHOE_STORE,
        SHOPPING_MALL,
        SPA,
        STADIUM,
        STORAGE,
        STORE,
        STREET_ADDRESS,
        STREET_NUMBER,
        SUBLOCALITY_LEVEL_1,
        SUBLOCALITY_LEVEL_2,
        SUBLOCALITY_LEVEL_3,
        SUBLOCALITY_LEVEL_4,
        SUBLOCALITY_LEVEL_5,
        SUBLOCALITY,
        SUBPREMISE,
        SUBWAY_STATION,
        SUPERMARKET,
        SYNAGOGUE,
        TAXI_STAND,
        TOURIST_ATTRACTION,
        TOWN_SQUARE,
        TRAIN_STATION,
        TRANSIT_STATION,
        TRAVEL_AGENCY,
        UNIVERSITY,
        VETERINARY_CARE,
        ZOO;
        
        @NonNull
        public static final Parcelable.Creator<Type> CREATOR = new zzbf();

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name());
        }
    }

    @NonNull
    public static Builder builder() {
        return new zzs();
    }

    @Nullable
    public abstract String getAddress();

    @Nullable
    public abstract AddressComponents getAddressComponents();

    @Nullable
    public abstract List<String> getAttributions();

    @Nullable
    public abstract BusinessStatus getBusinessStatus();

    @Nullable
    public abstract String getId();

    @Nullable
    public abstract LatLng getLatLng();

    @Nullable
    public abstract String getName();

    @Nullable
    public abstract OpeningHours getOpeningHours();

    @Nullable
    public abstract String getPhoneNumber();

    @Nullable
    public abstract List<PhotoMetadata> getPhotoMetadatas();

    @Nullable
    public abstract PlusCode getPlusCode();

    @IntRange(from = 0, to = 4)
    @Nullable
    public abstract Integer getPriceLevel();

    @Nullable
    @FloatRange(from = 1.0d, to = RATING_MAX_VALUE)
    public abstract Double getRating();

    @Nullable
    public abstract List<Type> getTypes();

    @IntRange(from = 0)
    @Nullable
    public abstract Integer getUserRatingsTotal();

    @Nullable
    public abstract Integer getUtcOffsetMinutes();

    @Nullable
    public abstract LatLngBounds getViewport();

    @Nullable
    public abstract Uri getWebsiteUri();

    @Nullable
    public Boolean isOpen() {
        return isOpen(System.currentTimeMillis());
    }

    @Nullable
    public Boolean isOpen(long j) {
        return zzbc.zza(this, j);
    }
}
