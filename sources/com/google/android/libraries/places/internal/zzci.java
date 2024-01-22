package com.google.android.libraries.places.internal;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.AddressComponent;
import com.google.android.libraries.places.api.model.AddressComponents;
import com.google.android.libraries.places.api.model.DayOfWeek;
import com.google.android.libraries.places.api.model.LocalTime;
import com.google.android.libraries.places.api.model.OpeningHours;
import com.google.android.libraries.places.api.model.Period;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlusCode;
import com.google.android.libraries.places.api.model.TimeOfWeek;
import com.google.android.libraries.places.internal.zzcj;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes10.dex */
final class zzci {
    private static final zzgn<String, Place.BusinessStatus> zza = new zzgm().zza("OPERATIONAL", Place.BusinessStatus.OPERATIONAL).zza("CLOSED_TEMPORARILY", Place.BusinessStatus.CLOSED_TEMPORARILY).zza("CLOSED_PERMANENTLY", Place.BusinessStatus.CLOSED_PERMANENTLY).zza();
    private static final zzgn<String, Place.Type> zzb = new zzgm().zza("accounting", Place.Type.ACCOUNTING).zza("administrative_area_level_1", Place.Type.ADMINISTRATIVE_AREA_LEVEL_1).zza("administrative_area_level_2", Place.Type.ADMINISTRATIVE_AREA_LEVEL_2).zza("administrative_area_level_3", Place.Type.ADMINISTRATIVE_AREA_LEVEL_3).zza("administrative_area_level_4", Place.Type.ADMINISTRATIVE_AREA_LEVEL_4).zza("administrative_area_level_5", Place.Type.ADMINISTRATIVE_AREA_LEVEL_5).zza("airport", Place.Type.AIRPORT).zza("amusement_park", Place.Type.AMUSEMENT_PARK).zza("aquarium", Place.Type.AQUARIUM).zza("archipelago", Place.Type.ARCHIPELAGO).zza("art_gallery", Place.Type.ART_GALLERY).zza("atm", Place.Type.ATM).zza("bakery", Place.Type.BAKERY).zza("bank", Place.Type.BANK).zza("bar", Place.Type.BAR).zza("beauty_salon", Place.Type.BEAUTY_SALON).zza("bicycle_store", Place.Type.BICYCLE_STORE).zza("book_store", Place.Type.BOOK_STORE).zza("bowling_alley", Place.Type.BOWLING_ALLEY).zza("bus_station", Place.Type.BUS_STATION).zza("cafe", Place.Type.CAFE).zza("campground", Place.Type.CAMPGROUND).zza("car_dealer", Place.Type.CAR_DEALER).zza("car_rental", Place.Type.CAR_RENTAL).zza("car_repair", Place.Type.CAR_REPAIR).zza("car_wash", Place.Type.CAR_WASH).zza("casino", Place.Type.CASINO).zza("cemetery", Place.Type.CEMETERY).zza("church", Place.Type.CHURCH).zza("city_hall", Place.Type.CITY_HALL).zza("clothing_store", Place.Type.CLOTHING_STORE).zza("colloquial_area", Place.Type.COLLOQUIAL_AREA).zza("continent", Place.Type.CONTINENT).zza("convenience_store", Place.Type.CONVENIENCE_STORE).zza("country", Place.Type.COUNTRY).zza("courthouse", Place.Type.COURTHOUSE).zza("dentist", Place.Type.DENTIST).zza("department_store", Place.Type.DEPARTMENT_STORE).zza("doctor", Place.Type.DOCTOR).zza("drugstore", Place.Type.DRUGSTORE).zza("electrician", Place.Type.ELECTRICIAN).zza("electronics_store", Place.Type.ELECTRONICS_STORE).zza("embassy", Place.Type.EMBASSY).zza("establishment", Place.Type.ESTABLISHMENT).zza("finance", Place.Type.FINANCE).zza("fire_station", Place.Type.FIRE_STATION).zza("floor", Place.Type.FLOOR).zza("florist", Place.Type.FLORIST).zza("food", Place.Type.FOOD).zza("funeral_home", Place.Type.FUNERAL_HOME).zza("furniture_store", Place.Type.FURNITURE_STORE).zza("gas_station", Place.Type.GAS_STATION).zza("general_contractor", Place.Type.GENERAL_CONTRACTOR).zza("geocode", Place.Type.GEOCODE).zza("grocery_or_supermarket", Place.Type.GROCERY_OR_SUPERMARKET).zza("gym", Place.Type.GYM).zza("hair_care", Place.Type.HAIR_CARE).zza("hardware_store", Place.Type.HARDWARE_STORE).zza("health", Place.Type.HEALTH).zza("hindu_temple", Place.Type.HINDU_TEMPLE).zza("home_goods_store", Place.Type.HOME_GOODS_STORE).zza("hospital", Place.Type.HOSPITAL).zza("insurance_agency", Place.Type.INSURANCE_AGENCY).zza("intersection", Place.Type.INTERSECTION).zza("jewelry_store", Place.Type.JEWELRY_STORE).zza("laundry", Place.Type.LAUNDRY).zza("lawyer", Place.Type.LAWYER).zza("library", Place.Type.LIBRARY).zza("light_rail_station", Place.Type.LIGHT_RAIL_STATION).zza("liquor_store", Place.Type.LIQUOR_STORE).zza("local_government_office", Place.Type.LOCAL_GOVERNMENT_OFFICE).zza("locality", Place.Type.LOCALITY).zza("locksmith", Place.Type.LOCKSMITH).zza("lodging", Place.Type.LODGING).zza("meal_delivery", Place.Type.MEAL_DELIVERY).zza("meal_takeaway", Place.Type.MEAL_TAKEAWAY).zza("mosque", Place.Type.MOSQUE).zza("movie_rental", Place.Type.MOVIE_RENTAL).zza("movie_theater", Place.Type.MOVIE_THEATER).zza("moving_company", Place.Type.MOVING_COMPANY).zza("museum", Place.Type.MUSEUM).zza("natural_feature", Place.Type.NATURAL_FEATURE).zza("neighborhood", Place.Type.NEIGHBORHOOD).zza("night_club", Place.Type.NIGHT_CLUB).zza("painter", Place.Type.PAINTER).zza("park", Place.Type.PARK).zza("parking", Place.Type.PARKING).zza("pet_store", Place.Type.PET_STORE).zza("pharmacy", Place.Type.PHARMACY).zza("physiotherapist", Place.Type.PHYSIOTHERAPIST).zza("place_of_worship", Place.Type.PLACE_OF_WORSHIP).zza("plumber", Place.Type.PLUMBER).zza("plus_code", Place.Type.PLUS_CODE).zza("point_of_interest", Place.Type.POINT_OF_INTEREST).zza("police", Place.Type.POLICE).zza("political", Place.Type.POLITICAL).zza("post_box", Place.Type.POST_BOX).zza("post_office", Place.Type.POST_OFFICE).zza("postal_code_prefix", Place.Type.POSTAL_CODE_PREFIX).zza("postal_code_suffix", Place.Type.POSTAL_CODE_SUFFIX).zza("postal_code", Place.Type.POSTAL_CODE).zza("postal_town", Place.Type.POSTAL_TOWN).zza("premise", Place.Type.PREMISE).zza("primary_school", Place.Type.PRIMARY_SCHOOL).zza("real_estate_agency", Place.Type.REAL_ESTATE_AGENCY).zza("restaurant", Place.Type.RESTAURANT).zza("roofing_contractor", Place.Type.ROOFING_CONTRACTOR).zza("room", Place.Type.ROOM).zza("route", Place.Type.ROUTE).zza("rv_park", Place.Type.RV_PARK).zza("school", Place.Type.SCHOOL).zza("secondary_school", Place.Type.SECONDARY_SCHOOL).zza("shoe_store", Place.Type.SHOE_STORE).zza("shopping_mall", Place.Type.SHOPPING_MALL).zza("spa", Place.Type.SPA).zza("stadium", Place.Type.STADIUM).zza("storage", Place.Type.STORAGE).zza("store", Place.Type.STORE).zza("street_address", Place.Type.STREET_ADDRESS).zza("street_number", Place.Type.STREET_NUMBER).zza("sublocality_level_1", Place.Type.SUBLOCALITY_LEVEL_1).zza("sublocality_level_2", Place.Type.SUBLOCALITY_LEVEL_2).zza("sublocality_level_3", Place.Type.SUBLOCALITY_LEVEL_3).zza("sublocality_level_4", Place.Type.SUBLOCALITY_LEVEL_4).zza("sublocality_level_5", Place.Type.SUBLOCALITY_LEVEL_5).zza("sublocality", Place.Type.SUBLOCALITY).zza("subpremise", Place.Type.SUBPREMISE).zza("subway_station", Place.Type.SUBWAY_STATION).zza("supermarket", Place.Type.SUPERMARKET).zza("synagogue", Place.Type.SYNAGOGUE).zza("taxi_stand", Place.Type.TAXI_STAND).zza("tourist_attraction", Place.Type.TOURIST_ATTRACTION).zza("town_square", Place.Type.TOWN_SQUARE).zza("train_station", Place.Type.TRAIN_STATION).zza("transit_station", Place.Type.TRANSIT_STATION).zza("travel_agency", Place.Type.TRAVEL_AGENCY).zza("university", Place.Type.UNIVERSITY).zza("veterinary_care", Place.Type.VETERINARY_CARE).zza("zoo", Place.Type.ZOO).zza();

    public static Place zza(@Nullable zzcj zzcjVar, @Nullable List<String> list) throws ApiException {
        AddressComponents newInstance;
        LatLng latLng;
        LatLngBounds latLngBounds;
        ArrayList arrayList;
        OpeningHours openingHours;
        ArrayList arrayList2;
        PhotoMetadata build;
        Place.Builder builder = Place.builder();
        builder.setAttributions(list);
        if (zzcjVar != null) {
            zzgi<zzcj.zzb> zza2 = zzcjVar.zza();
            int i = 0;
            if (zza2 == null) {
                newInstance = null;
            } else {
                ArrayList arrayList3 = new ArrayList();
                int size = zza2.size();
                int i2 = 0;
                while (i2 < size) {
                    zzcj.zzb zzbVar = zza2.get(i2);
                    i2++;
                    zza(arrayList3, zza(zzbVar));
                }
                newInstance = AddressComponents.newInstance(arrayList3);
            }
            zzcj.zza zzd = zzcjVar.zzd();
            if (zzd != null) {
                latLng = zza(zzd.zza());
                zzcj.zza.C0395zza zzb2 = zzd.zzb();
                if (zzb2 != null) {
                    LatLng zza3 = zza(zzb2.zzb());
                    LatLng zza4 = zza(zzb2.zza());
                    if (zza3 != null && zza4 != null) {
                        latLngBounds = new LatLngBounds(zza3, zza4);
                    }
                }
                latLngBounds = null;
            } else {
                latLng = null;
                latLngBounds = null;
            }
            String zzp = zzcjVar.zzp();
            Uri parse = zzp != null ? Uri.parse(zzp) : null;
            Place.Builder phoneNumber = builder.setAddress(zzcjVar.zzc()).setAddressComponents(newInstance).setBusinessStatus(zza.getOrDefault(zzcjVar.zzb(), null)).setId(zzcjVar.zzi()).setLatLng(latLng).setName(zzcjVar.zzf()).setPhoneNumber(zzcjVar.zze());
            zzgi<zzcj.zzd> zzh = zzcjVar.zzh();
            if (zzh != null) {
                arrayList = new ArrayList();
                int size2 = zzh.size();
                int i3 = 0;
                while (i3 < size2) {
                    zzcj.zzd zzdVar = zzh.get(i3);
                    i3++;
                    zzcj.zzd zzdVar2 = zzdVar;
                    if (zzdVar2 == null) {
                        build = null;
                    } else if (!TextUtils.isEmpty(zzdVar2.zzc())) {
                        Integer zza5 = zzdVar2.zza();
                        Integer zzb3 = zzdVar2.zzb();
                        PhotoMetadata.Builder builder2 = PhotoMetadata.builder(zzdVar2.zzc());
                        zzgi<String> zzd2 = zzdVar2.zzd();
                        build = builder2.setAttributions((zzd2 == null || zzd2.isEmpty()) ? "" : zzfj.zza(", ").zza().zza(new StringBuilder(), (Iterator<?>) zzd2.iterator()).toString()).setHeight(zza5 == null ? 0 : zza5.intValue()).setWidth(zzb3 == null ? 0 : zzb3.intValue()).build();
                    } else {
                        throw zzb("Photo reference not provided for a PhotoMetadata result.");
                    }
                    zza(arrayList, build);
                }
            } else {
                arrayList = null;
            }
            Place.Builder photoMetadatas = phoneNumber.setPhotoMetadatas(arrayList);
            zzcj.zzc zzg = zzcjVar.zzg();
            if (zzg != null) {
                OpeningHours.Builder builder3 = OpeningHours.builder();
                zzgi<zzcj.zzc.zza> zza6 = zzg.zza();
                if (zza6 != null) {
                    arrayList2 = new ArrayList();
                    int size3 = zza6.size();
                    while (i < size3) {
                        zzcj.zzc.zza zzaVar = zza6.get(i);
                        i++;
                        zzcj.zzc.zza zzaVar2 = zzaVar;
                        zza(arrayList2, zzaVar2 != null ? Period.builder().setOpen(zza(zzaVar2.zzb())).setClose(zza(zzaVar2.zza())).build() : null);
                    }
                } else {
                    arrayList2 = null;
                }
                openingHours = builder3.setPeriods(zzb(arrayList2)).setWeekdayText(zzb(zzg.zzb())).build();
            } else {
                openingHours = null;
            }
            Place.Builder openingHours2 = photoMetadatas.setOpeningHours(openingHours);
            zzcj.zze zzj = zzcjVar.zzj();
            openingHours2.setPlusCode(zzj != null ? PlusCode.builder().setCompoundCode(zzj.zza()).setGlobalCode(zzj.zzb()).build() : null).setPriceLevel(zzcjVar.zzk()).setRating(zzcjVar.zzl()).setTypes(zza(zzcjVar.zzm())).setUserRatingsTotal(zzcjVar.zzn()).setUtcOffsetMinutes(zzcjVar.zzo()).setViewport(latLngBounds).setWebsiteUri(parse);
        }
        return builder.build();
    }

    private static ApiException zzb(String str) {
        String valueOf = String.valueOf(str);
        return new ApiException(new Status(8, valueOf.length() != 0 ? "Unexpected server error: ".concat(valueOf) : new String("Unexpected server error: ")));
    }

    public static <T> List<T> zzb(@Nullable List<T> list) {
        return list != null ? list : new ArrayList();
    }

    @Nullable
    private static AddressComponent zza(@Nullable zzcj.zzb zzbVar) throws ApiException {
        if (zzbVar == null) {
            return null;
        }
        try {
            return AddressComponent.builder(zzbVar.zza(), zzbVar.zzc()).setShortName(zzbVar.zzb()).build();
        } catch (IllegalStateException | NullPointerException e) {
            throw zzb(String.format("AddressComponent not properly defined (%s).", e.getMessage()));
        }
    }

    @Nullable
    private static TimeOfWeek zza(@Nullable zzcj.zzc.zzb zzbVar) {
        DayOfWeek dayOfWeek;
        if (zzbVar != null) {
            zzft.zza(zzbVar.zza() != null, "Unable to convert Pablo response to TimeOfWeek: The \"day\" field is missing.");
            zzft.zza(zzbVar.zzb() != null, "Unable to convert Pablo response to TimeOfWeek: The \"time\" field is missing.");
            switch (zzbVar.zza().intValue()) {
                case 0:
                    dayOfWeek = DayOfWeek.SUNDAY;
                    break;
                case 1:
                    dayOfWeek = DayOfWeek.MONDAY;
                    break;
                case 2:
                    dayOfWeek = DayOfWeek.TUESDAY;
                    break;
                case 3:
                    dayOfWeek = DayOfWeek.WEDNESDAY;
                    break;
                case 4:
                    dayOfWeek = DayOfWeek.THURSDAY;
                    break;
                case 5:
                    dayOfWeek = DayOfWeek.FRIDAY;
                    break;
                case 6:
                    dayOfWeek = DayOfWeek.SATURDAY;
                    break;
                default:
                    throw new IllegalArgumentException("pabloDayOfWeek can only be an integer between 0 and 6");
            }
            return TimeOfWeek.newInstance(dayOfWeek, zza(zzbVar.zzb()));
        }
        return null;
    }

    @Nullable
    @VisibleForTesting
    private static LocalTime zza(@Nullable String str) {
        if (str != null) {
            String format = String.format("Unable to convert %s to LocalTime, must be of format \"hhmm\".", str);
            zzft.zza(str.length() == 4, format);
            try {
                return LocalTime.newInstance(Integer.parseInt(str.substring(0, 2)), Integer.parseInt(str.substring(2, 4)));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(format, e);
            }
        }
        return null;
    }

    @Nullable
    public static List<Place.Type> zza(@Nullable List<String> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (String str : list) {
            zzgn<String, Place.Type> zzgnVar = zzb;
            if (zzgnVar.containsKey(str)) {
                arrayList.add(zzgnVar.get(str));
            } else {
                z = true;
            }
        }
        if (z) {
            arrayList.add(Place.Type.OTHER);
        }
        return arrayList;
    }

    @Nullable
    private static LatLng zza(@Nullable zzcj.zza.zzb zzbVar) {
        if (zzbVar == null || zzbVar.zza() == null || zzbVar.zzb() == null) {
            return null;
        }
        return new LatLng(zzbVar.zza().doubleValue(), zzbVar.zzb().doubleValue());
    }

    private static <T> boolean zza(Collection<T> collection, @Nullable T t) {
        if (t != null) {
            return collection.add(t);
        }
        return false;
    }
}
