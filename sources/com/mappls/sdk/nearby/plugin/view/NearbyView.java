package com.mappls.sdk.nearby.plugin.view;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.AttrRes;
import androidx.annotation.Keep;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.location.engine.LocationEngine;
import com.mappls.sdk.maps.location.engine.LocationEngineCallback;
import com.mappls.sdk.maps.location.engine.LocationEngineProvider;
import com.mappls.sdk.maps.location.engine.LocationEngineRequest;
import com.mappls.sdk.maps.location.engine.LocationEngineResult;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.nearby.plugin.R;
import com.mappls.sdk.nearby.plugin.adapter.a;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyViewBinding;
import com.mappls.sdk.nearby.plugin.model.MapplsNearbyResponse;
import com.mappls.sdk.services.api.Place;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResponse;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
@SourceDebugExtension({"SMAP\nNearbyView.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NearbyView.kt\ncom/mappls/sdk/nearby/plugin/view/NearbyView\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,353:1\n1855#2,2:354\n1855#2,2:356\n1855#2,2:358\n*S KotlinDebug\n*F\n+ 1 NearbyView.kt\ncom/mappls/sdk/nearby/plugin/view/NearbyView\n*L\n206#1:354,2\n85#1:356,2\n148#1:358,2\n*E\n"})
/* loaded from: classes10.dex */
public final class NearbyView extends RelativeLayout implements LocationEngineCallback<LocationEngineResult>, LifecycleOwner {
    private com.mappls.sdk.nearby.plugin.adapter.a adapter;
    @Nullable
    private LifecycleRegistry lifecycleRegistry;
    private LocationEngine locationEngine;
    @NotNull
    private final MapplsNearbyViewBinding mBinding;
    private com.mappls.sdk.nearby.plugin.viewmodel.d mViewModel;
    @NotNull
    private NearbyViewOption mViewOption;
    @NotNull
    private final Observer<com.mappls.sdk.nearby.plugin.util.d<NearbyAtlasResponse>> nearbyObserver;
    @Nullable
    private f nearbyViewCallback;
    @NotNull
    private final Observer<com.mappls.sdk.nearby.plugin.util.d<List<Place>>> reverseGeocodeObserver;

    /* loaded from: classes10.dex */
    public static final class a implements a.InterfaceC0652a {
        public a() {
        }

        @Override // com.mappls.sdk.nearby.plugin.adapter.a.InterfaceC0652a
        public final void a() {
            NearbyView.this.enableButton();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NearbyView(@NotNull Context context) {
        this(context, (AttributeSet) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NearbyView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NearbyView(@NotNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        MapplsNearbyViewBinding inflate = MapplsNearbyViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context), this, true)");
        this.mBinding = inflate;
        this.reverseGeocodeObserver = new Observer() { // from class: com.mappls.sdk.nearby.plugin.view.s
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NearbyView.reverseGeocodeObserver$lambda$0(NearbyView.this, (com.mappls.sdk.nearby.plugin.util.d) obj);
            }
        };
        this.nearbyObserver = new Observer() { // from class: com.mappls.sdk.nearby.plugin.view.t
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NearbyView.nearbyObserver$lambda$2(NearbyView.this, (com.mappls.sdk.nearby.plugin.util.d) obj);
            }
        };
        NearbyViewOption createFromAttributes = NearbyViewOption.createFromAttributes(context, attributeSet);
        Intrinsics.checkNotNullExpressionValue(createFromAttributes, "createFromAttributes(context, attrs)");
        this.mViewOption = createFromAttributes;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NearbyView(@NotNull Context context, @NotNull NearbyViewOption nearbyViewOption) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(nearbyViewOption, "nearbyViewOption");
        MapplsNearbyViewBinding inflate = MapplsNearbyViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context), this, true)");
        this.mBinding = inflate;
        this.reverseGeocodeObserver = new Observer() { // from class: com.mappls.sdk.nearby.plugin.view.s
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NearbyView.reverseGeocodeObserver$lambda$0(NearbyView.this, (com.mappls.sdk.nearby.plugin.util.d) obj);
            }
        };
        this.nearbyObserver = new Observer() { // from class: com.mappls.sdk.nearby.plugin.view.t
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                NearbyView.nearbyObserver$lambda$2(NearbyView.this, (com.mappls.sdk.nearby.plugin.util.d) obj);
            }
        };
        this.mViewOption = nearbyViewOption;
    }

    private final void buildLocationEngine() {
    }

    private final boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(getContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enableButton() {
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar = this.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar2 = null;
        if (dVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar = null;
        }
        if (dVar.b() == null) {
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar3 = this.mViewModel;
            if (dVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar3 = null;
            }
            if (dVar3.c() == null) {
                this.mBinding.mapplsNearbyNextBtn.setEnabled(false);
                return;
            }
        }
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar4 = this.mViewModel;
        if (dVar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            dVar2 = dVar4;
        }
        boolean z = false;
        for (CategoryCode categoryCode : dVar2.a()) {
            if (categoryCode.isSelected()) {
                z = true;
            }
        }
        TextView textView = this.mBinding.mapplsNearbyNextBtn;
        if (z) {
            textView.setEnabled(true);
        } else {
            textView.setEnabled(false);
        }
    }

    private final void enableLocation() {
        enableLocationRequest();
    }

    private final void initView() {
        ConstraintLayout constraintLayout = this.mBinding.mapplsNearbyBgLayout;
        Integer backgroundColor = this.mViewOption.backgroundColor();
        Intrinsics.checkNotNullExpressionValue(backgroundColor, "mViewOption.backgroundColor()");
        constraintLayout.setBackgroundColor(backgroundColor.intValue());
        CardView cardView = this.mBinding.mapplsNearbyBottomLayout;
        Integer backgroundColor2 = this.mViewOption.backgroundColor();
        Intrinsics.checkNotNullExpressionValue(backgroundColor2, "mViewOption.backgroundColor()");
        cardView.setCardBackgroundColor(backgroundColor2.intValue());
        LinearLayout linearLayout = this.mBinding.mapplsNearbyLayoutTitle;
        Integer num = this.mViewOption.toolbarBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(num, "mViewOption.toolbarBackgroundColor()");
        linearLayout.setBackgroundColor(num.intValue());
        if (this.mViewOption.toolbarBitmap() != null) {
            this.mBinding.mapplsNearbyToolbarIcon.setImageBitmap(this.mViewOption.toolbarBitmap());
        } else {
            ImageView imageView = this.mBinding.mapplsNearbyToolbarIcon;
            Integer num2 = this.mViewOption.toolbarIcon();
            Intrinsics.checkNotNullExpressionValue(num2, "mViewOption.toolbarIcon()");
            imageView.setImageResource(num2.intValue());
        }
        TextView textView = this.mBinding.mapplsNearbyToolbarText;
        Integer num3 = this.mViewOption.toolbarTextColor();
        Intrinsics.checkNotNullExpressionValue(num3, "mViewOption.toolbarTextColor()");
        textView.setTextColor(num3.intValue());
        this.mBinding.mapplsNearbyToolbarText.setText(this.mViewOption.toolbarText());
        CardView cardView2 = this.mBinding.mapplsNearbyLocationLayout;
        Integer addressBackgroundColor = this.mViewOption.addressBackgroundColor();
        Intrinsics.checkNotNullExpressionValue(addressBackgroundColor, "mViewOption.addressBackgroundColor()");
        cardView2.setCardBackgroundColor(addressBackgroundColor.intValue());
        TextView textView2 = this.mBinding.mapplsNearbyLocationText;
        Integer addressTooltipTextColor = this.mViewOption.addressTooltipTextColor();
        Intrinsics.checkNotNullExpressionValue(addressTooltipTextColor, "mViewOption.addressTooltipTextColor()");
        textView2.setTextColor(addressTooltipTextColor.intValue());
        this.mBinding.mapplsNearbyLocationText.setText(this.mViewOption.locationInfoLabelText());
        TextView textView3 = this.mBinding.mapplsNearbyTvAddress;
        Integer addressTextColor = this.mViewOption.addressTextColor();
        Intrinsics.checkNotNullExpressionValue(addressTextColor, "mViewOption.addressTextColor()");
        textView3.setTextColor(addressTextColor.intValue());
        TextView textView4 = this.mBinding.mapplsNearbyCurrentLocationBtn;
        Integer useCurrentLocationButtonTextColor = this.mViewOption.useCurrentLocationButtonTextColor();
        Intrinsics.checkNotNullExpressionValue(useCurrentLocationButtonTextColor, "mViewOption.useCurrentLocationButtonTextColor()");
        textView4.setTextColor(useCurrentLocationButtonTextColor.intValue());
        TextView textView5 = this.mBinding.mapplsNearbyChangeLocationBtn;
        Integer changeLocationButtonTextColor = this.mViewOption.changeLocationButtonTextColor();
        Intrinsics.checkNotNullExpressionValue(changeLocationButtonTextColor, "mViewOption.changeLocationButtonTextColor()");
        textView5.setTextColor(changeLocationButtonTextColor.intValue());
        if (this.mViewOption.submitButtonColor() != null) {
            TextView textView6 = this.mBinding.mapplsNearbyNextBtn;
            Integer submitButtonColor = this.mViewOption.submitButtonColor();
            Intrinsics.checkNotNull(submitButtonColor);
            textView6.setBackgroundColor(submitButtonColor.intValue());
        } else {
            TextView textView7 = this.mBinding.mapplsNearbyNextBtn;
            Integer submitButtonResource = this.mViewOption.submitButtonResource();
            Intrinsics.checkNotNullExpressionValue(submitButtonResource, "mViewOption.submitButtonResource()");
            textView7.setBackgroundResource(submitButtonResource.intValue());
        }
        TextView textView8 = this.mBinding.mapplsNearbyNextBtn;
        Intrinsics.checkNotNull(textView8, "null cannot be cast to non-null type android.widget.TextView");
        Integer submitButtonTextColor = this.mViewOption.submitButtonTextColor();
        Intrinsics.checkNotNullExpressionValue(submitButtonTextColor, "mViewOption.submitButtonTextColor()");
        textView8.setTextColor(submitButtonTextColor.intValue());
        TextView textView9 = this.mBinding.mapplsNearbyNextBtn;
        Intrinsics.checkNotNull(textView9, "null cannot be cast to non-null type android.widget.TextView");
        textView9.setText(this.mViewOption.submitButtonText());
    }

    private final void initialiseClick() {
        this.mBinding.mapplsNearbyChangeLocationBtn.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.view.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NearbyView.initialiseClick$lambda$3(NearbyView.this, view);
            }
        });
        this.mBinding.mapplsNearbyCurrentLocationBtn.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.view.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NearbyView.initialiseClick$lambda$4(NearbyView.this, view);
            }
        });
        this.mBinding.mapplsNearbyNextBtn.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.view.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NearbyView.initialiseClick$lambda$6(NearbyView.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialiseClick$lambda$3(NearbyView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        f fVar = this$0.nearbyViewCallback;
        if (fVar != null) {
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar = this$0.mViewModel;
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar2 = null;
            if (dVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar = null;
            }
            LatLng b = dVar.b();
            Double valueOf = b != null ? Double.valueOf(b.getLatitude()) : null;
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar3 = this$0.mViewModel;
            if (dVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar3 = null;
            }
            LatLng b2 = dVar3.b();
            Double valueOf2 = b2 != null ? Double.valueOf(b2.getLongitude()) : null;
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar4 = this$0.mViewModel;
            if (dVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                dVar2 = dVar4;
            }
            fVar.onChangeLocationRequest(valueOf, valueOf2, dVar2.c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialiseClick$lambda$4(NearbyView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mBinding.mapplsNearbyTvAddress.setText(this$0.getContext().getString(R.string.mappls_nearby_fetching_location_text));
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar = this$0.mViewModel;
        if (dVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar = null;
        }
        dVar.b(null);
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar2 = this$0.mViewModel;
        if (dVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar2 = null;
        }
        dVar2.a((String) null);
        this$0.setCurrentLocation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialiseClick$lambda$6(NearbyView this$0, View view) {
        Context context;
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar = this$0.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar2 = null;
        if (dVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar = null;
        }
        if (dVar.b() == null) {
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar3 = this$0.mViewModel;
            if (dVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar3 = null;
            }
            if (dVar3.c() == null) {
                context = this$0.getContext();
                str = "Please wait....";
                Toast.makeText(context, str, 0).show();
                return;
            }
        }
        ArrayList arrayList = new ArrayList();
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar4 = this$0.mViewModel;
        if (dVar4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar4 = null;
        }
        for (CategoryCode categoryCode : dVar4.a()) {
            if (categoryCode.isSelected()) {
                arrayList.add(categoryCode);
            }
        }
        if (arrayList.size() == 0) {
            context = this$0.getContext();
            str = "Please select atleast one category";
            Toast.makeText(context, str, 0).show();
            return;
        }
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar5 = this$0.mViewModel;
        if (dVar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            dVar2 = dVar5;
        }
        dVar2.a(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void nearbyObserver$lambda$2(NearbyView this$0, com.mappls.sdk.nearby.plugin.util.d dVar) {
        Integer totalPages;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int ordinal = dVar.c().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                this$0.mBinding.mapplsNearbyProgressBar.setVisibility(0);
                return;
            } else if (ordinal != 2) {
                return;
            } else {
                this$0.mBinding.mapplsNearbyProgressBar.setVisibility(8);
                Toast.makeText(this$0.getContext(), dVar.b(), 0).show();
                return;
            }
        }
        if (dVar.a() == null || ((totalPages = ((NearbyAtlasResponse) dVar.a()).getPageInfo().getTotalPages()) != null && totalPages.intValue() == 0)) {
            Toast.makeText(this$0.getContext(), "No result found", 0).show();
        } else {
            ArrayList arrayList = new ArrayList();
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar2 = this$0.mViewModel;
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar3 = null;
            if (dVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar2 = null;
            }
            for (CategoryCode categoryCode : dVar2.a()) {
                if (categoryCode.isSelected()) {
                    arrayList.add(categoryCode);
                }
            }
            NearbyAtlasResponse nearbyAtlasResponse = (NearbyAtlasResponse) dVar.a();
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar4 = this$0.mViewModel;
            if (dVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar4 = null;
            }
            LatLng b = dVar4.b();
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar5 = this$0.mViewModel;
            if (dVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar5 = null;
            }
            MapplsNearbyResponse mapplsNearbyResponse = new MapplsNearbyResponse(nearbyAtlasResponse, arrayList, b, dVar5.c());
            f fVar = this$0.nearbyViewCallback;
            if (fVar != null) {
                fVar.onSelectCategory(mapplsNearbyResponse);
            }
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar6 = this$0.mViewModel;
            if (dVar6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                dVar3 = dVar6;
            }
            dVar3.a(new MutableLiveData<>());
        }
        this$0.mBinding.mapplsNearbyProgressBar.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reverseGeocodeObserver$lambda$0(NearbyView this$0, com.mappls.sdk.nearby.plugin.util.d dVar) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (dVar.c() == com.mappls.sdk.nearby.plugin.util.e.f13069a) {
            this$0.mBinding.mapplsNearbyProgressBar.setVisibility(8);
            List list = (List) dVar.a();
            if ((list != null ? list.size() : 0) > 0) {
                List list2 = (List) dVar.a();
                Place place = list2 != null ? (Place) list2.get(0) : null;
                this$0.mBinding.mapplsNearbyTvAddress.setText(place != null ? place.getFormattedAddress() : null);
            }
        } else if (dVar.c() != com.mappls.sdk.nearby.plugin.util.e.c) {
            if (dVar.c() == com.mappls.sdk.nearby.plugin.util.e.b) {
                this$0.mBinding.mapplsNearbyProgressBar.setVisibility(0);
            }
        } else {
            this$0.mBinding.mapplsNearbyProgressBar.setVisibility(8);
            List list3 = (List) dVar.a();
            Place place2 = list3 != null ? (Place) list3.get(0) : null;
            this$0.mBinding.mapplsNearbyTvAddress.setText(place2 != null ? place2.getFormattedAddress() : null);
            Toast.makeText(this$0.getContext(), dVar.b(), 0).show();
        }
    }

    private final void setCurrentLocation() {
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar = this.mViewModel;
        LocationEngine locationEngine = null;
        if (dVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar = null;
        }
        dVar.a(true);
        if (checkPermission()) {
            return;
        }
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar2 = this.mViewModel;
        if (dVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar2 = null;
        }
        if (dVar2.g()) {
            LocationEngine locationEngine2 = this.locationEngine;
            if (locationEngine2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("locationEngine");
            } else {
                locationEngine = locationEngine2;
            }
            locationEngine.getLastLocation(this);
        }
    }

    private final void subscribeViewModel() {
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar = this.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar2 = null;
        if (dVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar = null;
        }
        dVar.f().observe(this, this.reverseGeocodeObserver);
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar3 = this.mViewModel;
        if (dVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            dVar2 = dVar3;
        }
        dVar2.d().observe(this, this.nearbyObserver);
    }

    public final void enableLocationRequest() {
        if (checkPermission()) {
            return;
        }
        LocationEngineRequest build = new LocationEngineRequest.Builder(1000L).setPriority(0).setFastestInterval(1000L).setDisplacement(0.0f).build();
        LocationEngine locationEngine = this.locationEngine;
        if (locationEngine == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationEngine");
            locationEngine = null;
        }
        locationEngine.requestLocationUpdates(build, this, Looper.getMainLooper());
    }

    @NotNull
    public final List<CategoryCode> getCategoryCodes() {
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar = this.mViewModel;
        if (dVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar = null;
        }
        return dVar.a();
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NotNull
    public LifecycleRegistry getLifecycle() {
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        Intrinsics.checkNotNull(lifecycleRegistry);
        return lifecycleRegistry;
    }

    @Override // android.view.View
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        RecyclerView recyclerView;
        GridLayoutManager gridLayoutManager;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == 2) {
            recyclerView = this.mBinding.mapplsNearbyRvCategory;
            gridLayoutManager = new GridLayoutManager(getContext(), 6);
        } else {
            recyclerView = this.mBinding.mapplsNearbyRvCategory;
            gridLayoutManager = new GridLayoutManager(getContext(), 3);
        }
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public final void onCreate(@Nullable Bundle bundle) {
        RecyclerView recyclerView;
        GridLayoutManager gridLayoutManager;
        if (!(getContext() instanceof FragmentActivity)) {
            throw new RuntimeException("Nearby View is not directly attached with Fragment Activity");
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        this.mViewModel = (com.mappls.sdk.nearby.plugin.viewmodel.d) new ViewModelProvider((FragmentActivity) context).get(com.mappls.sdk.nearby.plugin.viewmodel.d.class);
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry = lifecycleRegistry;
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        initView();
        LocationEngine bestLocationEngine = LocationEngineProvider.getBestLocationEngine(getContext());
        Intrinsics.checkNotNullExpressionValue(bestLocationEngine, "getBestLocationEngine(context)");
        this.locationEngine = bestLocationEngine;
        enableButton();
        if (getContext().getResources().getConfiguration().orientation == 2) {
            recyclerView = this.mBinding.mapplsNearbyRvCategory;
            gridLayoutManager = new GridLayoutManager(getContext(), 6);
        } else {
            recyclerView = this.mBinding.mapplsNearbyRvCategory;
            gridLayoutManager = new GridLayoutManager(getContext(), 3);
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        com.mappls.sdk.nearby.plugin.adapter.a aVar = new com.mappls.sdk.nearby.plugin.adapter.a(this.mViewOption);
        this.adapter = aVar;
        aVar.a(new a());
        RecyclerView recyclerView2 = this.mBinding.mapplsNearbyRvCategory;
        com.mappls.sdk.nearby.plugin.adapter.a aVar2 = this.adapter;
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar = null;
        if (aVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            aVar2 = null;
        }
        recyclerView2.setAdapter(aVar2);
        this.mBinding.mapplsNearbyTvAddress.setText(getContext().getString(R.string.mappls_nearby_fetching_location_text));
        com.mappls.sdk.nearby.plugin.adapter.a aVar3 = this.adapter;
        if (aVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            aVar3 = null;
        }
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar2 = this.mViewModel;
        if (dVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar2 = null;
        }
        aVar3.a(dVar2.a());
        initialiseClick();
        enableLocation();
        subscribeViewModel();
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar3 = this.mViewModel;
        if (dVar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar3 = null;
        }
        if (dVar3.b() != null) {
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar4 = this.mViewModel;
            if (dVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar4 = null;
            }
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar5 = this.mViewModel;
            if (dVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                dVar = dVar5;
            }
            LatLng b = dVar.b();
            Intrinsics.checkNotNull(b);
            dVar4.a(b);
        }
    }

    public final void onDestroy() {
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry != null) {
            lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
        }
        LocationEngine locationEngine = this.locationEngine;
        if (locationEngine == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationEngine");
            locationEngine = null;
        }
        locationEngine.removeLocationUpdates(this);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
    public void onFailure(@NotNull Exception p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        p0.printStackTrace();
    }

    public final void onResume() {
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry == null) {
            return;
        }
        lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);
    }

    public final void onStart() {
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry == null) {
            return;
        }
        lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
    }

    @Override // com.mappls.sdk.maps.location.engine.LocationEngineCallback
    public void onSuccess(@Nullable LocationEngineResult locationEngineResult) {
        Location lastLocation;
        if (locationEngineResult == null || (lastLocation = locationEngineResult.getLastLocation()) == null) {
            return;
        }
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar = this.mViewModel;
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar2 = null;
        if (dVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar = null;
        }
        if (dVar.g()) {
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar3 = this.mViewModel;
            if (dVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar3 = null;
            }
            dVar3.b(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()));
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar4 = this.mViewModel;
            if (dVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar4 = null;
            }
            dVar4.a((String) null);
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar5 = this.mViewModel;
            if (dVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar5 = null;
            }
            dVar5.a(new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude()));
            enableButton();
        }
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar6 = this.mViewModel;
        if (dVar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
        } else {
            dVar2 = dVar6;
        }
        dVar2.a(false);
    }

    public final void setCategoryCodes(@NotNull List<? extends CategoryCode> categoryList) {
        Intrinsics.checkNotNullParameter(categoryList, "categoryList");
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar = this.mViewModel;
        if (dVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar = null;
        }
        dVar.a(categoryList);
        com.mappls.sdk.nearby.plugin.adapter.a aVar = (com.mappls.sdk.nearby.plugin.adapter.a) this.mBinding.mapplsNearbyRvCategory.getAdapter();
        if (aVar != null) {
            aVar.a(categoryList);
        }
    }

    public final void setNearbyViewCallback(@NotNull f callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.nearbyViewCallback = callback;
    }

    public final void setSelectedLocation(@NotNull Place place) {
        Intrinsics.checkNotNullParameter(place, "place");
        com.mappls.sdk.nearby.plugin.viewmodel.d dVar = this.mViewModel;
        if (dVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            dVar = null;
        }
        dVar.a(false);
        this.mBinding.mapplsNearbyTvAddress.setText(place.getFormattedAddress());
        if (place.getLat() == null || place.getLng() == null) {
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar2 = this.mViewModel;
            if (dVar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar2 = null;
            }
            dVar2.a(place.getMapplsPin());
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar3 = this.mViewModel;
            if (dVar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar3 = null;
            }
            dVar3.b(null);
        } else {
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar4 = this.mViewModel;
            if (dVar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar4 = null;
            }
            dVar4.b(new LatLng(place.getLat().doubleValue(), place.getLng().doubleValue()));
            com.mappls.sdk.nearby.plugin.viewmodel.d dVar5 = this.mViewModel;
            if (dVar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
                dVar5 = null;
            }
            dVar5.a((String) null);
        }
        enableButton();
    }
}
