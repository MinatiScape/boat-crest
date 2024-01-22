package com.google.android.libraries.places.api.model;

import android.net.Uri;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.Place;
import java.util.List;
/* loaded from: classes10.dex */
abstract class zzp extends Place {
    private final String zza;
    private final AddressComponents zzb;
    private final Place.BusinessStatus zzc;
    private final List<String> zzd;
    private final String zze;
    private final LatLng zzf;
    private final String zzg;
    private final OpeningHours zzh;
    private final String zzi;
    private final List<PhotoMetadata> zzj;
    private final PlusCode zzk;
    private final Integer zzl;
    private final Double zzm;
    private final List<Place.Type> zzn;
    private final Integer zzo;
    private final Integer zzp;
    private final LatLngBounds zzq;
    private final Uri zzr;

    public zzp(@Nullable String str, @Nullable AddressComponents addressComponents, @Nullable Place.BusinessStatus businessStatus, @Nullable List<String> list, @Nullable String str2, @Nullable LatLng latLng, @Nullable String str3, @Nullable OpeningHours openingHours, @Nullable String str4, @Nullable List<PhotoMetadata> list2, @Nullable PlusCode plusCode, @Nullable Integer num, @Nullable Double d, @Nullable List<Place.Type> list3, @Nullable Integer num2, @Nullable Integer num3, @Nullable LatLngBounds latLngBounds, @Nullable Uri uri) {
        this.zza = str;
        this.zzb = addressComponents;
        this.zzc = businessStatus;
        this.zzd = list;
        this.zze = str2;
        this.zzf = latLng;
        this.zzg = str3;
        this.zzh = openingHours;
        this.zzi = str4;
        this.zzj = list2;
        this.zzk = plusCode;
        this.zzl = num;
        this.zzm = d;
        this.zzn = list3;
        this.zzo = num2;
        this.zzp = num3;
        this.zzq = latLngBounds;
        this.zzr = uri;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Place) {
            Place place = (Place) obj;
            String str = this.zza;
            if (str != null ? str.equals(place.getAddress()) : place.getAddress() == null) {
                AddressComponents addressComponents = this.zzb;
                if (addressComponents != null ? addressComponents.equals(place.getAddressComponents()) : place.getAddressComponents() == null) {
                    Place.BusinessStatus businessStatus = this.zzc;
                    if (businessStatus != null ? businessStatus.equals(place.getBusinessStatus()) : place.getBusinessStatus() == null) {
                        List<String> list = this.zzd;
                        if (list != null ? list.equals(place.getAttributions()) : place.getAttributions() == null) {
                            String str2 = this.zze;
                            if (str2 != null ? str2.equals(place.getId()) : place.getId() == null) {
                                LatLng latLng = this.zzf;
                                if (latLng != null ? latLng.equals(place.getLatLng()) : place.getLatLng() == null) {
                                    String str3 = this.zzg;
                                    if (str3 != null ? str3.equals(place.getName()) : place.getName() == null) {
                                        OpeningHours openingHours = this.zzh;
                                        if (openingHours != null ? openingHours.equals(place.getOpeningHours()) : place.getOpeningHours() == null) {
                                            String str4 = this.zzi;
                                            if (str4 != null ? str4.equals(place.getPhoneNumber()) : place.getPhoneNumber() == null) {
                                                List<PhotoMetadata> list2 = this.zzj;
                                                if (list2 != null ? list2.equals(place.getPhotoMetadatas()) : place.getPhotoMetadatas() == null) {
                                                    PlusCode plusCode = this.zzk;
                                                    if (plusCode != null ? plusCode.equals(place.getPlusCode()) : place.getPlusCode() == null) {
                                                        Integer num = this.zzl;
                                                        if (num != null ? num.equals(place.getPriceLevel()) : place.getPriceLevel() == null) {
                                                            Double d = this.zzm;
                                                            if (d != null ? d.equals(place.getRating()) : place.getRating() == null) {
                                                                List<Place.Type> list3 = this.zzn;
                                                                if (list3 != null ? list3.equals(place.getTypes()) : place.getTypes() == null) {
                                                                    Integer num2 = this.zzo;
                                                                    if (num2 != null ? num2.equals(place.getUserRatingsTotal()) : place.getUserRatingsTotal() == null) {
                                                                        Integer num3 = this.zzp;
                                                                        if (num3 != null ? num3.equals(place.getUtcOffsetMinutes()) : place.getUtcOffsetMinutes() == null) {
                                                                            LatLngBounds latLngBounds = this.zzq;
                                                                            if (latLngBounds != null ? latLngBounds.equals(place.getViewport()) : place.getViewport() == null) {
                                                                                Uri uri = this.zzr;
                                                                                if (uri != null ? uri.equals(place.getWebsiteUri()) : place.getWebsiteUri() == null) {
                                                                                    return true;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public String getAddress() {
        return this.zza;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public AddressComponents getAddressComponents() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public List<String> getAttributions() {
        return this.zzd;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public Place.BusinessStatus getBusinessStatus() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public String getId() {
        return this.zze;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public LatLng getLatLng() {
        return this.zzf;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public String getName() {
        return this.zzg;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public OpeningHours getOpeningHours() {
        return this.zzh;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public String getPhoneNumber() {
        return this.zzi;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public List<PhotoMetadata> getPhotoMetadatas() {
        return this.zzj;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public PlusCode getPlusCode() {
        return this.zzk;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @IntRange(from = 0, to = 4)
    @Nullable
    public Integer getPriceLevel() {
        return this.zzl;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    @FloatRange(from = 1.0d, to = Place.RATING_MAX_VALUE)
    public Double getRating() {
        return this.zzm;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public List<Place.Type> getTypes() {
        return this.zzn;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @IntRange(from = 0)
    @Nullable
    public Integer getUserRatingsTotal() {
        return this.zzo;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public Integer getUtcOffsetMinutes() {
        return this.zzp;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public LatLngBounds getViewport() {
        return this.zzq;
    }

    @Override // com.google.android.libraries.places.api.model.Place
    @Nullable
    public Uri getWebsiteUri() {
        return this.zzr;
    }

    public int hashCode() {
        String str = this.zza;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        AddressComponents addressComponents = this.zzb;
        int hashCode2 = (hashCode ^ (addressComponents == null ? 0 : addressComponents.hashCode())) * 1000003;
        Place.BusinessStatus businessStatus = this.zzc;
        int hashCode3 = (hashCode2 ^ (businessStatus == null ? 0 : businessStatus.hashCode())) * 1000003;
        List<String> list = this.zzd;
        int hashCode4 = (hashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        String str2 = this.zze;
        int hashCode5 = (hashCode4 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        LatLng latLng = this.zzf;
        int hashCode6 = (hashCode5 ^ (latLng == null ? 0 : latLng.hashCode())) * 1000003;
        String str3 = this.zzg;
        int hashCode7 = (hashCode6 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        OpeningHours openingHours = this.zzh;
        int hashCode8 = (hashCode7 ^ (openingHours == null ? 0 : openingHours.hashCode())) * 1000003;
        String str4 = this.zzi;
        int hashCode9 = (hashCode8 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        List<PhotoMetadata> list2 = this.zzj;
        int hashCode10 = (hashCode9 ^ (list2 == null ? 0 : list2.hashCode())) * 1000003;
        PlusCode plusCode = this.zzk;
        int hashCode11 = (hashCode10 ^ (plusCode == null ? 0 : plusCode.hashCode())) * 1000003;
        Integer num = this.zzl;
        int hashCode12 = (hashCode11 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Double d = this.zzm;
        int hashCode13 = (hashCode12 ^ (d == null ? 0 : d.hashCode())) * 1000003;
        List<Place.Type> list3 = this.zzn;
        int hashCode14 = (hashCode13 ^ (list3 == null ? 0 : list3.hashCode())) * 1000003;
        Integer num2 = this.zzo;
        int hashCode15 = (hashCode14 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        Integer num3 = this.zzp;
        int hashCode16 = (hashCode15 ^ (num3 == null ? 0 : num3.hashCode())) * 1000003;
        LatLngBounds latLngBounds = this.zzq;
        int hashCode17 = (hashCode16 ^ (latLngBounds == null ? 0 : latLngBounds.hashCode())) * 1000003;
        Uri uri = this.zzr;
        return hashCode17 ^ (uri != null ? uri.hashCode() : 0);
    }

    public String toString() {
        String str = this.zza;
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(this.zzc);
        String valueOf3 = String.valueOf(this.zzd);
        String str2 = this.zze;
        String valueOf4 = String.valueOf(this.zzf);
        String str3 = this.zzg;
        String valueOf5 = String.valueOf(this.zzh);
        String str4 = this.zzi;
        String valueOf6 = String.valueOf(this.zzj);
        String valueOf7 = String.valueOf(this.zzk);
        String valueOf8 = String.valueOf(this.zzl);
        String valueOf9 = String.valueOf(this.zzm);
        String valueOf10 = String.valueOf(this.zzn);
        String valueOf11 = String.valueOf(this.zzo);
        String valueOf12 = String.valueOf(this.zzp);
        String valueOf13 = String.valueOf(this.zzq);
        String valueOf14 = String.valueOf(this.zzr);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 237 + valueOf.length() + valueOf2.length() + valueOf3.length() + String.valueOf(str2).length() + valueOf4.length() + String.valueOf(str3).length() + valueOf5.length() + String.valueOf(str4).length() + valueOf6.length() + valueOf7.length() + valueOf8.length() + valueOf9.length() + valueOf10.length() + valueOf11.length() + valueOf12.length() + valueOf13.length() + valueOf14.length());
        sb.append("Place{address=");
        sb.append(str);
        sb.append(", addressComponents=");
        sb.append(valueOf);
        sb.append(", businessStatus=");
        sb.append(valueOf2);
        sb.append(", attributions=");
        sb.append(valueOf3);
        sb.append(", id=");
        sb.append(str2);
        sb.append(", latLng=");
        sb.append(valueOf4);
        sb.append(", name=");
        sb.append(str3);
        sb.append(", openingHours=");
        sb.append(valueOf5);
        sb.append(", phoneNumber=");
        sb.append(str4);
        sb.append(", photoMetadatas=");
        sb.append(valueOf6);
        sb.append(", plusCode=");
        sb.append(valueOf7);
        sb.append(", priceLevel=");
        sb.append(valueOf8);
        sb.append(", rating=");
        sb.append(valueOf9);
        sb.append(", types=");
        sb.append(valueOf10);
        sb.append(", userRatingsTotal=");
        sb.append(valueOf11);
        sb.append(", utcOffsetMinutes=");
        sb.append(valueOf12);
        sb.append(", viewport=");
        sb.append(valueOf13);
        sb.append(", websiteUri=");
        sb.append(valueOf14);
        sb.append("}");
        return sb.toString();
    }
}
