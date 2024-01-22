package com.mappls.sdk.nearby.plugin;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.Keep;
import com.google.gson.Gson;
import com.mappls.sdk.nearby.plugin.model.NearbyOption;
import com.mappls.sdk.nearby.plugin.model.NearbyUIOption;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
/* loaded from: classes10.dex */
public final class MapplsNearbyWidget {
    @NotNull
    public static final MapplsNearbyWidget INSTANCE = new MapplsNearbyWidget();
    @NotNull
    private static List<? extends CategoryCode> categoryList;
    @NotNull
    private static NearbyOption nearbyOption;
    @NotNull
    private static NearbyUIOption nearbyUIOption;

    @Keep
    /* loaded from: classes10.dex */
    public static final class IntentBuilder {
        @NotNull
        private final Intent intent = new Intent();

        public IntentBuilder() {
            MapplsNearbyWidget.INSTANCE.clear$mappls_nearby_widget_othersRelease();
        }

        @NotNull
        public final Intent build(@NotNull Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.intent.setClass(activity, MapplsNearbyActivity.class);
            return this.intent;
        }

        @NotNull
        public final IntentBuilder nearbyOptions(@NotNull NearbyOption nearbyOption) {
            Intrinsics.checkNotNullParameter(nearbyOption, "nearbyOption");
            MapplsNearbyWidget.INSTANCE.setNearbyOption$mappls_nearby_widget_othersRelease(nearbyOption);
            return this;
        }

        @NotNull
        public final IntentBuilder nearbyUIOptions(@NotNull NearbyUIOption nearbyUIOption) {
            Intrinsics.checkNotNullParameter(nearbyUIOption, "nearbyUIOption");
            MapplsNearbyWidget.INSTANCE.setNearbyUIOption$mappls_nearby_widget_othersRelease(nearbyUIOption);
            return this;
        }

        @NotNull
        public final IntentBuilder setCategoryList(@NotNull List<? extends CategoryCode> categories) {
            Intrinsics.checkNotNullParameter(categories, "categories");
            MapplsNearbyWidget.INSTANCE.setCategoryList$mappls_nearby_widget_othersRelease(categories);
            return this;
        }
    }

    static {
        NearbyOption build = NearbyOption.builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
        nearbyOption = build;
        NearbyUIOption build2 = NearbyUIOption.builder().build();
        Intrinsics.checkNotNullExpressionValue(build2, "builder().build()");
        nearbyUIOption = build2;
        categoryList = CollectionsKt__CollectionsKt.arrayListOf(new CategoryCode("Coffee", R.drawable.mappls_nearby_coffee_icon, (List<String>) CollectionsKt__CollectionsKt.arrayListOf("FODCOF"), R.drawable.mappls_nearby_coffee_marker, true), new CategoryCode("Restaurants", R.drawable.mappls_nearby_restaurant_icon, CollectionsKt__CollectionsKt.arrayListOf("FODOTH", "FODOTL", "FODIND", "FODCON", "FODFFD", "FODBAK"), R.drawable.mappls_nearby_restaurant_marker), new CategoryCode("Pubs & Bars", R.drawable.mappls_nearby_bar_icon, CollectionsKt__CollectionsKt.arrayListOf("FODPUB"), R.drawable.mappls_nearby_pub_marker), new CategoryCode("Parking", R.drawable.mappls_nearby_parking_icon, CollectionsKt__CollectionsKt.arrayListOf("PRKSRF", "PRKWPM", "PRKRDS", "PRKMBK", "TRNPRK", "PRKUNG", "PRKTRK", "PRKCYC", "PRKMLT"), R.drawable.mappls_nearby_parking_marker), new CategoryCode("ATMs", R.drawable.mappls_nearby_atm_icon, CollectionsKt__CollectionsKt.arrayListOf("FINATM"), R.drawable.mappls_nearby_atm_marker), new CategoryCode("Pharmacy", R.drawable.mappls_nearby_pharmacy_icon, CollectionsKt__CollectionsKt.arrayListOf("HLTMDS", "SHPCOM", "MDS24H", "MDSJAN", "COMHDO"), R.drawable.mappls_nearby_pharmacy_marker), new CategoryCode("Transport", R.drawable.mappls_nearby_transport_icon, CollectionsKt__CollectionsKt.arrayListOf("TRNBUS", "TRNOTH", "TRNTRO", "TRNTXI", "TRNRAL", "TRNCAR", "TRNBST", "TRNMET"), R.drawable.mappls_nearby_transport_marker), new CategoryCode("Hospitals", R.drawable.mappls_nearby_hospital_icon, CollectionsKt__CollectionsKt.arrayListOf("HSPHMH", "HSPVTH", "HSPEYH", "HSPDNH", "HSPCHD", "HSPMAT", "HSPORH", "HSPHMH", "HSPENT", "HSPHRH"), R.drawable.mappls_nearby_hospital_marker), new CategoryCode("Hotels", R.drawable.mappls_nearby_hotel_icon, CollectionsKt__CollectionsKt.arrayListOf("HOTALL", "HOTHST", "HOTYTH", "HOTRES", "HOTNOP", "HOTSAP", "HOTPRE", "HOTHRG"), R.drawable.mappls_nearby_hotel_marker));
    }

    private MapplsNearbyWidget() {
    }

    @JvmStatic
    @Nullable
    public static final NearbyAtlasResult getNearbyResponse(@NotNull Intent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        String stringExtra = data.getStringExtra("com.mappls.sdk.nearby.plugin.NEARBY_RESULT_KEY");
        if (stringExtra == null) {
            return null;
        }
        return (NearbyAtlasResult) new Gson().fromJson(stringExtra, (Class<Object>) NearbyAtlasResult.class);
    }

    public final void clear$mappls_nearby_widget_othersRelease() {
        NearbyOption build = NearbyOption.builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "builder().build()");
        nearbyOption = build;
        NearbyUIOption build2 = NearbyUIOption.builder().build();
        Intrinsics.checkNotNullExpressionValue(build2, "builder().build()");
        nearbyUIOption = build2;
        categoryList = CollectionsKt__CollectionsKt.arrayListOf(new CategoryCode("Coffee", R.drawable.mappls_nearby_coffee_icon, (List<String>) CollectionsKt__CollectionsKt.arrayListOf("FODCOF"), R.drawable.mappls_nearby_coffee_marker, true), new CategoryCode("Restaurants", R.drawable.mappls_nearby_restaurant_icon, CollectionsKt__CollectionsKt.arrayListOf("FODOTH", "FODOTL", "FODIND", "FODCON", "FODFFD", "FODBAK"), R.drawable.mappls_nearby_restaurant_marker), new CategoryCode("Pubs & Bars", R.drawable.mappls_nearby_bar_icon, CollectionsKt__CollectionsKt.arrayListOf("FODPUB"), R.drawable.mappls_nearby_pub_marker), new CategoryCode("Parking", R.drawable.mappls_nearby_parking_icon, CollectionsKt__CollectionsKt.arrayListOf("PRKSRF", "PRKWPM", "PRKRDS", "PRKMBK", "TRNPRK", "PRKUNG", "PRKTRK", "PRKCYC", "PRKMLT"), R.drawable.mappls_nearby_parking_marker), new CategoryCode("ATMs", R.drawable.mappls_nearby_atm_icon, CollectionsKt__CollectionsKt.arrayListOf("FINATM"), R.drawable.mappls_nearby_atm_marker), new CategoryCode("Pharmacy", R.drawable.mappls_nearby_pharmacy_icon, CollectionsKt__CollectionsKt.arrayListOf("HLTMDS", "SHPCOM", "MDS24H", "MDSJAN", "COMHDO"), R.drawable.mappls_nearby_pharmacy_marker), new CategoryCode("Transport", R.drawable.mappls_nearby_transport_icon, CollectionsKt__CollectionsKt.arrayListOf("TRNBUS", "TRNOTH", "TRNTRO", "TRNTXI", "TRNRAL", "TRNCAR", "TRNBST", "TRNMET"), R.drawable.mappls_nearby_transport_marker), new CategoryCode("Hospitals", R.drawable.mappls_nearby_hospital_icon, CollectionsKt__CollectionsKt.arrayListOf("HSPHMH", "HSPVTH", "HSPEYH", "HSPDNH", "HSPCHD", "HSPMAT", "HSPORH", "HSPHMH", "HSPENT", "HSPHRH"), R.drawable.mappls_nearby_hospital_marker), new CategoryCode("Hotels", R.drawable.mappls_nearby_hotel_icon, CollectionsKt__CollectionsKt.arrayListOf("HOTALL", "HOTHST", "HOTYTH", "HOTRES", "HOTNOP", "HOTSAP", "HOTPRE", "HOTHRG"), R.drawable.mappls_nearby_hotel_marker));
    }

    @NotNull
    public final List<CategoryCode> getCategoryList() {
        return categoryList;
    }

    @NotNull
    public final List<CategoryCode> getCategoryList$mappls_nearby_widget_othersRelease() {
        return categoryList;
    }

    @NotNull
    public final NearbyOption getNearbyOption() {
        return nearbyOption;
    }

    @NotNull
    public final NearbyOption getNearbyOption$mappls_nearby_widget_othersRelease() {
        return nearbyOption;
    }

    @NotNull
    public final NearbyUIOption getNearbyUIOption() {
        return nearbyUIOption;
    }

    @NotNull
    public final NearbyUIOption getNearbyUIOption$mappls_nearby_widget_othersRelease() {
        return nearbyUIOption;
    }

    public final void setCategoryList$mappls_nearby_widget_othersRelease(@NotNull List<? extends CategoryCode> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        categoryList = list;
    }

    public final void setNearbyOption$mappls_nearby_widget_othersRelease(@NotNull NearbyOption nearbyOption2) {
        Intrinsics.checkNotNullParameter(nearbyOption2, "<set-?>");
        nearbyOption = nearbyOption2;
    }

    public final void setNearbyUIOption$mappls_nearby_widget_othersRelease(@NotNull NearbyUIOption nearbyUIOption2) {
        Intrinsics.checkNotNullParameter(nearbyUIOption2, "<set-?>");
        nearbyUIOption = nearbyUIOption2;
    }
}
