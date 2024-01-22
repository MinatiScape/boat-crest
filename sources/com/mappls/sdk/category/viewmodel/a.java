package com.mappls.sdk.category.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mappls.sdk.category.model.SearchCategoryOption;
import com.mappls.sdk.category.model.SearchCategoryUIOption;
import com.mappls.sdk.category.utils.c;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRoute;
import com.mappls.sdk.services.api.alongroute.MapplsPOIAlongRouteManager;
import com.mappls.sdk.services.api.alongroute.models.POIAlongRouteResponse;
import com.mappls.sdk.services.api.nearby.MapplsNearby;
import com.mappls.sdk.services.api.nearby.MapplsNearbyManager;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SourceDebugExtension({"SMAP\nCategoryResultViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CategoryResultViewModel.kt\ncom/mappls/sdk/category/viewmodel/CategoryResultViewModel\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,108:1\n1855#2,2:109\n37#3,2:111\n*S KotlinDebug\n*F\n+ 1 CategoryResultViewModel.kt\ncom/mappls/sdk/category/viewmodel/CategoryResultViewModel\n*L\n103#1:109,2\n106#1:111,2\n*E\n"})
/* loaded from: classes11.dex */
public final class a extends ViewModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<? extends CategoryCode> f12550a = CollectionsKt__CollectionsKt.emptyList();
    @NotNull
    public LinkedHashMap b = new LinkedHashMap();
    @NotNull
    public SearchCategoryOption c = com.mappls.sdk.category.a.c();
    @NotNull
    public SearchCategoryUIOption d;
    @NotNull
    public MutableLiveData<c<POIAlongRouteResponse>> e;
    @NotNull
    public MutableLiveData<c<NearbyAtlasResponse>> f;

    /* renamed from: com.mappls.sdk.category.viewmodel.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class C0611a implements OnResponseCallback<NearbyAtlasResponse> {
        public C0611a() {
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onError(int i, @Nullable String str) {
            MutableLiveData<c<NearbyAtlasResponse>> c;
            c<NearbyAtlasResponse> a2;
            if (i == 204) {
                c = a.this.c();
                a2 = c.a.a((Object) null);
            } else {
                if (101 <= i && i < 401) {
                    c = a.this.c();
                    if (str == null) {
                        str = "Something Went wrong";
                    }
                    a2 = c.a.a(str);
                } else if (i == 0) {
                    return;
                } else {
                    c = a.this.c();
                    a2 = c.a.a("Something Went wrong");
                }
            }
            c.setValue(a2);
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onSuccess(NearbyAtlasResponse nearbyAtlasResponse) {
            a.this.c().setValue(c.a.a(nearbyAtlasResponse));
        }
    }

    /* loaded from: classes11.dex */
    public static final class b implements OnResponseCallback<POIAlongRouteResponse> {
        public b() {
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onError(int i, @Nullable String str) {
            MutableLiveData<c<POIAlongRouteResponse>> a2;
            c<POIAlongRouteResponse> a3;
            if (i == 204) {
                a2 = a.this.a();
                a3 = c.a.a((Object) null);
            } else {
                if (101 <= i && i < 401) {
                    a2 = a.this.a();
                    if (str == null) {
                        str = "Something Went wrong";
                    }
                    a3 = c.a.a(str);
                } else if (i == 0) {
                    return;
                } else {
                    a2 = a.this.a();
                    a3 = c.a.a("Something Went wrong");
                }
            }
            a2.setValue(a3);
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onSuccess(POIAlongRouteResponse pOIAlongRouteResponse) {
            a.this.a().setValue(c.a.a(pOIAlongRouteResponse));
        }
    }

    public a() {
        SearchCategoryUIOption build = SearchCategoryUIOption.builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
        this.d = build;
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
    }

    @NotNull
    public final MutableLiveData<c<POIAlongRouteResponse>> a() {
        return this.e;
    }

    public final void a(@NotNull SearchCategoryUIOption searchCategoryUIOption) {
        Intrinsics.checkNotNullParameter(searchCategoryUIOption, "<set-?>");
        this.d = searchCategoryUIOption;
    }

    public final void a(@Nullable ArrayList arrayList) {
        this.f12550a = arrayList;
    }

    public final void a(@NotNull List<? extends CategoryCode> categoryCodes) {
        Intrinsics.checkNotNullParameter(categoryCodes, "categoryCodes");
        if (this.c.location() == null) {
            this.f.setValue(c.a.a("Location Not found"));
            return;
        }
        this.f.setValue(c.a.a());
        MapplsNearby.Builder builder = MapplsNearby.builder();
        String location = this.c.location();
        Intrinsics.checkNotNull(location);
        MapplsNearby.Builder explain = builder.setLocation(location).bounds(this.c.bounds()).explain(this.c.explain());
        ArrayList arrayList = new ArrayList();
        for (CategoryCode categoryCode : categoryCodes) {
            List<String> categoryCode2 = categoryCode.getCategoryCode();
            Intrinsics.checkNotNullExpressionValue(categoryCode2, "it.categoryCode");
            arrayList.addAll(categoryCode2);
        }
        MapplsNearbyManager.newInstance(explain.keyword(com.mappls.sdk.category.utils.b.a(arrayList.toArray(new Object[0]))).filter(this.c.filter()).page(this.c.page()).pod(this.c.pod()).radius(this.c.radius()).richData(this.c.richData()).searchBy(this.c.searchBy()).sortBy(this.c.searchBy()).userName(this.c.userName()).build()).call(new C0611a());
    }

    @NotNull
    public final LinkedHashMap b() {
        return this.b;
    }

    public final void b(@NotNull List<? extends CategoryCode> categoryCodes) {
        Intrinsics.checkNotNullParameter(categoryCodes, "categoryCodes");
        if (this.c.path() == null) {
            this.e.setValue(c.a.a("Path Not found"));
            return;
        }
        this.e.setValue(c.a.a());
        MapplsPOIAlongRoute.Builder builder = MapplsPOIAlongRoute.builder();
        String path = this.c.path();
        Intrinsics.checkNotNull(path);
        MapplsPOIAlongRoute.Builder geometries = builder.path(path).buffer(this.c.buffer()).geometries(this.c.geometries());
        ArrayList arrayList = new ArrayList();
        for (CategoryCode categoryCode : categoryCodes) {
            List<String> categoryCode2 = categoryCode.getCategoryCode();
            Intrinsics.checkNotNullExpressionValue(categoryCode2, "it.categoryCode");
            arrayList.addAll(categoryCode2);
        }
        MapplsPOIAlongRouteManager.newInstance(geometries.category(com.mappls.sdk.category.utils.b.a(arrayList.toArray(new Object[0]))).sort(this.c.isSort()).page(this.c.page()).build()).call(new b());
    }

    @NotNull
    public final MutableLiveData<c<NearbyAtlasResponse>> c() {
        return this.f;
    }

    @NotNull
    public final SearchCategoryOption d() {
        return this.c;
    }

    @NotNull
    public final SearchCategoryUIOption e() {
        return this.d;
    }

    @Nullable
    public final List<CategoryCode> f() {
        return this.f12550a;
    }
}
