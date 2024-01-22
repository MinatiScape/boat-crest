package com.mappls.sdk.nearby.plugin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.nearby.plugin.MapplsNearbyWidget;
import com.mappls.sdk.nearby.plugin.model.NearbyUIOption;
import com.mappls.sdk.nearby.plugin.util.d;
import com.mappls.sdk.services.api.nearby.MapplsNearby;
import com.mappls.sdk.services.api.nearby.MapplsNearbyManager;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SourceDebugExtension({"SMAP\nNearbyResultViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NearbyResultViewModel.kt\ncom/mappls/sdk/nearby/plugin/viewmodel/NearbyResultViewModel\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,83:1\n1855#2,2:84\n37#3,2:86\n*S KotlinDebug\n*F\n+ 1 NearbyResultViewModel.kt\ncom/mappls/sdk/nearby/plugin/viewmodel/NearbyResultViewModel\n*L\n77#1:84,2\n80#1:86,2\n*E\n"})
/* loaded from: classes10.dex */
public final class b extends ViewModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<CategoryCode> f13077a;
    @NotNull
    public MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<NearbyAtlasResponse>> b = new MutableLiveData<>();
    public int c = 1;
    @Nullable
    public NearbyAtlasResponse d;
    @Nullable
    public LatLng e;
    @Nullable
    public String f;
    @Nullable
    public NearbyUIOption g;

    public final int a() {
        return this.c;
    }

    public final void a(int i) {
        this.c = i;
    }

    public final void a(@NotNull MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<NearbyAtlasResponse>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void a(@Nullable LatLng latLng) {
        this.e = latLng;
    }

    public final void a(@Nullable NearbyUIOption nearbyUIOption) {
        this.g = nearbyUIOption;
    }

    public final void a(@Nullable NearbyAtlasResponse nearbyAtlasResponse) {
        this.d = nearbyAtlasResponse;
    }

    public final void a(@Nullable String str) {
        this.f = str;
    }

    public final void a(@Nullable ArrayList<CategoryCode> arrayList) {
        this.f13077a = arrayList;
    }

    public final void a(@NotNull ArrayList selectedCategory, int i) {
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
        keyword.page(Integer.valueOf(i));
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
        MapplsNearbyManager.newInstance(keyword.build()).call(new a(this));
    }

    @Nullable
    public final NearbyAtlasResponse b() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<com.mappls.sdk.nearby.plugin.util.d<NearbyAtlasResponse>> c() {
        return this.b;
    }

    @Nullable
    public final NearbyUIOption d() {
        return this.g;
    }

    @Nullable
    public final LatLng e() {
        return this.e;
    }

    @Nullable
    public final String f() {
        return this.f;
    }

    @Nullable
    public final ArrayList<CategoryCode> g() {
        return this.f13077a;
    }
}
