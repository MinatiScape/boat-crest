package com.mappls.sdk.nearby.plugin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.nearby.plugin.MapplsNearbyWidget;
import com.mappls.sdk.nearby.plugin.model.NearbyUIOption;
import com.mappls.sdk.nearby.plugin.util.d;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.Place;
import com.mappls.sdk.services.api.PlaceResponse;
import com.mappls.sdk.services.api.nearby.MapplsNearby;
import com.mappls.sdk.services.api.nearby.MapplsNearbyManager;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCode;
import com.mappls.sdk.services.api.reversegeocode.MapplsReverseGeoCodeManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SourceDebugExtension({"SMAP\nNearbyViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NearbyViewModel.kt\ncom/mappls/sdk/nearby/plugin/viewmodel/NearbyViewModel\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,133:1\n1855#2,2:134\n37#3,2:136\n*S KotlinDebug\n*F\n+ 1 NearbyViewModel.kt\ncom/mappls/sdk/nearby/plugin/viewmodel/NearbyViewModel\n*L\n128#1:134,2\n131#1:136,2\n*E\n"})
/* loaded from: classes10.dex */
public final class d extends ViewModel {
    @Nullable
    public NearbyUIOption c;
    @Nullable
    public LatLng e;
    @Nullable
    public String f;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<List<Place>>> f13079a = new MutableLiveData<>();
    @NotNull
    public MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<NearbyAtlasResponse>> b = new MutableLiveData<>();
    @NotNull
    public List<? extends CategoryCode> d = MapplsNearbyWidget.INSTANCE.getCategoryList();
    public boolean g = true;

    /* loaded from: classes10.dex */
    public static final class a implements OnResponseCallback<PlaceResponse> {
        public final /* synthetic */ LatLng b;

        public a(LatLng latLng) {
            this.b = latLng;
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onError(int i, @Nullable String str) {
            MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<List<Place>>> f;
            ArrayList arrayListOf;
            String str2;
            if (i == 0) {
                return;
            }
            if (i == 1) {
                Place place = new Place();
                place.setLat(Double.valueOf(this.b.getLatitude()));
                place.setLng(Double.valueOf(this.b.getLongitude()));
                place.setFormattedAddress('[' + this.b.getLatitude() + ", " + this.b.getLongitude() + ']');
                f = d.this.f();
                arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(place);
                str2 = "No Internet Connection";
            } else if (i != 204) {
                Place place2 = new Place();
                place2.setLat(Double.valueOf(this.b.getLatitude()));
                place2.setLng(Double.valueOf(this.b.getLongitude()));
                place2.setFormattedAddress('[' + this.b.getLatitude() + ", " + this.b.getLongitude() + ']');
                f = d.this.f();
                arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(place2);
                str2 = "Something went wrong";
            } else {
                Place place3 = new Place();
                place3.setLat(Double.valueOf(this.b.getLatitude()));
                place3.setLng(Double.valueOf(this.b.getLongitude()));
                place3.setFormattedAddress('[' + this.b.getLatitude() + ", " + this.b.getLongitude() + ']');
                f = d.this.f();
                arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(place3);
                str2 = "No address Found";
            }
            f.setValue(d.a.a(str2, arrayListOf));
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onSuccess(PlaceResponse placeResponse) {
            MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<List<Place>>> f;
            com.mappls.sdk.nearby.plugin.util.d<List<Place>> a2;
            PlaceResponse placeResponse2 = placeResponse;
            if ((placeResponse2 != null ? placeResponse2.getPlaces() : null) == null || placeResponse2.getPlaces().size() <= 0) {
                Place place = new Place();
                place.setLat(Double.valueOf(this.b.getLatitude()));
                place.setLng(Double.valueOf(this.b.getLongitude()));
                place.setFormattedAddress('[' + this.b.getLatitude() + ", " + this.b.getLongitude() + ']');
                f = d.this.f();
                a2 = d.a.a("No address Found", CollectionsKt__CollectionsKt.arrayListOf(place));
            } else {
                f = d.this.f();
                a2 = d.a.a(placeResponse2.getPlaces());
            }
            f.setValue(a2);
        }
    }

    @NotNull
    public final List<CategoryCode> a() {
        return this.d;
    }

    public final void a(@NotNull MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<NearbyAtlasResponse>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void a(@NotNull LatLng latLng) {
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        this.f13079a.setValue(d.a.a());
        MapplsReverseGeoCodeManager.newInstance(MapplsReverseGeoCode.builder().setLocation(latLng.getLatitude(), latLng.getLongitude()).build()).call(new a(latLng));
    }

    public final void a(@Nullable NearbyUIOption nearbyUIOption) {
        this.c = nearbyUIOption;
    }

    public final void a(@Nullable String str) {
        this.f = str;
    }

    public final void a(@NotNull ArrayList selectedCategory) {
        Intrinsics.checkNotNullParameter(selectedCategory, "selectedCategory");
        this.b.setValue(d.a.a());
        MapplsNearby.Builder builder = MapplsNearby.builder();
        ArrayList arrayList = new ArrayList();
        Iterator it = selectedCategory.iterator();
        while (it.hasNext()) {
            List<String> categoryCode = ((CategoryCode) it.next()).getCategoryCode();
            Intrinsics.checkNotNullExpressionValue(categoryCode, "it.categoryCode");
            arrayList.addAll(categoryCode);
        }
        MapplsNearby.Builder keyword = builder.keyword(com.mappls.sdk.nearby.plugin.util.c.a(arrayList.toArray(new Object[0])));
        LatLng latLng = this.e;
        if (latLng != null) {
            Double valueOf = Double.valueOf(latLng.getLatitude());
            LatLng latLng2 = this.e;
            keyword.setLocation(valueOf, latLng2 != null ? Double.valueOf(latLng2.getLongitude()) : null);
        } else {
            keyword.setLocation(this.f);
        }
        MapplsNearbyWidget mapplsNearbyWidget = MapplsNearbyWidget.INSTANCE;
        keyword.radius(mapplsNearbyWidget.getNearbyOption().radius());
        keyword.bounds(mapplsNearbyWidget.getNearbyOption().bounds());
        keyword.explain(mapplsNearbyWidget.getNearbyOption().explain());
        keyword.filter(mapplsNearbyWidget.getNearbyOption().filter());
        keyword.pod(mapplsNearbyWidget.getNearbyOption().pod());
        keyword.richData(mapplsNearbyWidget.getNearbyOption().richData());
        keyword.searchBy(mapplsNearbyWidget.getNearbyOption().searchBy());
        keyword.sortBy(mapplsNearbyWidget.getNearbyOption().sortBy());
        keyword.userName(mapplsNearbyWidget.getNearbyOption().userName());
        MapplsNearbyManager.newInstance(keyword.build()).call(new c(this));
    }

    public final void a(@NotNull List<? extends CategoryCode> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.d = list;
    }

    public final void a(boolean z) {
        this.g = z;
    }

    @Nullable
    public final LatLng b() {
        return this.e;
    }

    public final void b(@Nullable LatLng latLng) {
        this.e = latLng;
    }

    @Nullable
    public final String c() {
        return this.f;
    }

    @NotNull
    public final MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<NearbyAtlasResponse>> d() {
        return this.b;
    }

    @Nullable
    public final NearbyUIOption e() {
        return this.c;
    }

    @NotNull
    public final MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<List<Place>>> f() {
        return this.f13079a;
    }

    public final boolean g() {
        return this.g;
    }
}
