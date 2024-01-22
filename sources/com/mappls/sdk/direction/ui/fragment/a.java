package com.mappls.sdk.direction.ui.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.category.model.PoiResult;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionFragmentAddWayPointDialogBinding;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.camera.CameraMapplsPinPosition;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.plugin.annotation.SymbolManager;
import com.mappls.sdk.plugin.annotation.SymbolOptions;
import java.text.DecimalFormat;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SourceDebugExtension({"SMAP\nAddWayPointDialogFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AddWayPointDialogFragment.kt\ncom/mappls/sdk/direction/ui/fragment/AddWayPointDialogFragment\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,203:1\n1#2:204\n37#3,2:205\n*S KotlinDebug\n*F\n+ 1 AddWayPointDialogFragment.kt\ncom/mappls/sdk/direction/ui/fragment/AddWayPointDialogFragment\n*L\n132#1:205,2\n*E\n"})
/* loaded from: classes11.dex */
public final class a extends Fragment implements OnMapReadyCallback {
    public static final /* synthetic */ int g = 0;
    public MapplsDirectionFragmentAddWayPointDialogBinding h;
    @Nullable
    public PoiResult i;
    @Nullable
    public InterfaceC0614a j;
    @Nullable
    public MapView k;
    @Nullable
    public SymbolManager l;
    public boolean m;

    /* renamed from: com.mappls.sdk.direction.ui.fragment.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public interface InterfaceC0614a {
        void a(@NotNull PoiResult poiResult);

        void onCancel();
    }

    public static final void e(a this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InterfaceC0614a interfaceC0614a = this$0.j;
        if (interfaceC0614a != null) {
            interfaceC0614a.onCancel();
        }
    }

    public static final void f(a this$0, PoiResult data, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        InterfaceC0614a interfaceC0614a = this$0.j;
        if (interfaceC0614a != null) {
            interfaceC0614a.a(data);
        }
    }

    public static final void g(a this$0, MapplsMap mapplsMap, Style it) {
        SymbolManager symbolManager;
        SymbolManager symbolManager2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mapplsMap, "$mapplsMap");
        Intrinsics.checkNotNullParameter(it, "it");
        MapView mapView = this$0.k;
        Intrinsics.checkNotNull(mapView);
        this$0.l = new SymbolManager(mapView, mapplsMap, it);
        PoiResult poiResult = this$0.i;
        if ((poiResult != null ? poiResult.getLatitude() : null) != null) {
            PoiResult poiResult2 = this$0.i;
            if ((poiResult2 != null ? poiResult2.getLongitude() : null) != null && (symbolManager2 = this$0.l) != null) {
                SymbolOptions symbolOptions = new SymbolOptions();
                PoiResult poiResult3 = this$0.i;
                Double latitude = poiResult3 != null ? poiResult3.getLatitude() : null;
                Intrinsics.checkNotNull(latitude);
                double doubleValue = latitude.doubleValue();
                PoiResult poiResult4 = this$0.i;
                Double longitude = poiResult4 != null ? poiResult4.getLongitude() : null;
                Intrinsics.checkNotNull(longitude);
                symbolManager2.create((SymbolManager) symbolOptions.position(new LatLng(doubleValue, longitude.doubleValue())));
            }
        }
        PoiResult poiResult5 = this$0.i;
        if ((poiResult5 != null ? poiResult5.getMapplsPin() : null) == null || (symbolManager = this$0.l) == null) {
            return;
        }
        SymbolOptions symbolOptions2 = new SymbolOptions();
        PoiResult poiResult6 = this$0.i;
        String mapplsPin = poiResult6 != null ? poiResult6.getMapplsPin() : null;
        Intrinsics.checkNotNull(mapplsPin);
        symbolManager.create((SymbolManager) symbolOptions2.mapplsPin(mapplsPin));
    }

    public static final void h(a this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InterfaceC0614a interfaceC0614a = this$0.j;
        if (interfaceC0614a != null) {
            interfaceC0614a.onCancel();
        }
    }

    public final void a(final PoiResult poiResult) {
        TextView textView;
        String str;
        String[] strArr;
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding = this.h;
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding2 = null;
        if (mapplsDirectionFragmentAddWayPointDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsDirectionFragmentAddWayPointDialogBinding = null;
        }
        CharSequence charSequence = "";
        mapplsDirectionFragmentAddWayPointDialogBinding.resultPlaceName.setText(!TextUtils.isEmpty(poiResult.getPlaceName()) ? poiResult.getPlaceName() : "");
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding3 = this.h;
        if (mapplsDirectionFragmentAddWayPointDialogBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsDirectionFragmentAddWayPointDialogBinding3 = null;
        }
        mapplsDirectionFragmentAddWayPointDialogBinding3.itemPlaceResultPlaceAddress.setText(!TextUtils.isEmpty(poiResult.getPlaceAddress()) ? poiResult.getPlaceAddress() : "");
        Long distance = poiResult.getDistance();
        Intrinsics.checkNotNullExpressionValue(distance, "data.distance");
        if (distance.longValue() > 0) {
            MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding4 = this.h;
            if (mapplsDirectionFragmentAddWayPointDialogBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                mapplsDirectionFragmentAddWayPointDialogBinding4 = null;
            }
            textView = mapplsDirectionFragmentAddWayPointDialogBinding4.textViewDistance;
            Context context = getContext();
            if (context != null) {
                float longValue = (float) poiResult.getDistance().longValue();
                if (Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(context).getString("unit_list_preference", BleConst.GetDeviceTime)) != 0) {
                    str = Float.valueOf(new DecimalFormat("#.##").format(longValue / 1609.34f)).floatValue() + " miles";
                } else if (longValue >= 1000.0f) {
                    float f = longValue / 1000.0f;
                    str = context.getResources().getQuantityString(R.plurals.mappls_direction_distance_kilometer, (int) f, Float.valueOf(f));
                } else {
                    int i = (int) longValue;
                    str = context.getResources().getQuantityString(R.plurals.mappls_direction_distance_meter, i, Integer.valueOf(i));
                }
                String str2 = str;
                Intrinsics.checkNotNullExpressionValue(str2, "getDistanceFormat(context, distance)");
                if (((String[]) StringsKt__StringsKt.split$default((CharSequence) str2, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).toArray(new String[0])).length == 2) {
                    charSequence = Html.fromHtml(strArr[0] + "<br>" + strArr[1]);
                }
            }
            charSequence = null;
        } else {
            MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding5 = this.h;
            if (mapplsDirectionFragmentAddWayPointDialogBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                mapplsDirectionFragmentAddWayPointDialogBinding5 = null;
            }
            textView = mapplsDirectionFragmentAddWayPointDialogBinding5.textViewDistance;
        }
        textView.setText(charSequence);
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding6 = this.h;
        if (mapplsDirectionFragmentAddWayPointDialogBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsDirectionFragmentAddWayPointDialogBinding6 = null;
        }
        mapplsDirectionFragmentAddWayPointDialogBinding6.itemPlaceResultRatingBar.setVisibility(8);
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding7 = this.h;
        if (mapplsDirectionFragmentAddWayPointDialogBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsDirectionFragmentAddWayPointDialogBinding7 = null;
        }
        mapplsDirectionFragmentAddWayPointDialogBinding7.itemPlaceResultRatingBar.setRating(0.0f);
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding8 = this.h;
        if (mapplsDirectionFragmentAddWayPointDialogBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsDirectionFragmentAddWayPointDialogBinding8 = null;
        }
        mapplsDirectionFragmentAddWayPointDialogBinding8.itemPlaceResultRatingBar.setEnabled(false);
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding9 = this.h;
        if (mapplsDirectionFragmentAddWayPointDialogBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            mapplsDirectionFragmentAddWayPointDialogBinding2 = mapplsDirectionFragmentAddWayPointDialogBinding9;
        }
        mapplsDirectionFragmentAddWayPointDialogBinding2.buttonAddWaypoint.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.direction.ui.fragment.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                a.f(a.this, poiResult, view);
            }
        });
    }

    public final void a(@NotNull InterfaceC0614a listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.j = listener;
    }

    public final void a(@NotNull MapView mapView) {
        Intrinsics.checkNotNullParameter(mapView, "mapView");
        this.k = mapView;
        if (!isAdded() || this.m) {
            return;
        }
        MapView mapView2 = this.k;
        if (mapView2 != null) {
            mapView2.getMapAsync(this);
        }
        this.m = true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.i = (PoiResult) new Gson().fromJson(arguments.getString("arg_search_data"), (Class<Object>) PoiResult.class);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public final View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        MapplsDirectionFragmentAddWayPointDialogBinding inflate = MapplsDirectionFragmentAddWayPointDialogBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.h = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroyView() {
        SymbolManager symbolManager = this.l;
        if (symbolManager != null) {
            symbolManager.onDestroy();
        }
        super.onDestroyView();
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public final void onMapError(int i, @Nullable String str) {
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public final void onMapReady(@NotNull final MapplsMap mapplsMap) {
        Intrinsics.checkNotNullParameter(mapplsMap, "mapplsMap");
        PoiResult poiResult = this.i;
        if ((poiResult != null ? poiResult.getLatitude() : null) != null) {
            PoiResult poiResult2 = this.i;
            if ((poiResult2 != null ? poiResult2.getLongitude() : null) != null) {
                CameraPosition.Builder builder = new CameraPosition.Builder();
                PoiResult poiResult3 = this.i;
                Double latitude = poiResult3 != null ? poiResult3.getLatitude() : null;
                Intrinsics.checkNotNull(latitude);
                double doubleValue = latitude.doubleValue();
                PoiResult poiResult4 = this.i;
                Double longitude = poiResult4 != null ? poiResult4.getLongitude() : null;
                Intrinsics.checkNotNull(longitude);
                mapplsMap.setCameraPosition(builder.target(new LatLng(doubleValue, longitude.doubleValue())).zoom(12.0d).build());
            }
        }
        PoiResult poiResult5 = this.i;
        if ((poiResult5 != null ? poiResult5.getMapplsPin() : null) != null) {
            CameraMapplsPinPosition.Builder builder2 = new CameraMapplsPinPosition.Builder();
            PoiResult poiResult6 = this.i;
            String mapplsPin = poiResult6 != null ? poiResult6.getMapplsPin() : null;
            Intrinsics.checkNotNull(mapplsPin);
            mapplsMap.setCameraMapplsPinPosition(builder2.target(mapplsPin).zoom(12.0d).build());
        }
        mapplsMap.getStyle(new Style.OnStyleLoaded() { // from class: com.mappls.sdk.direction.ui.fragment.m
            @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
            public final void onStyleLoaded(Style style) {
                a.g(a.this, mapplsMap, style);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        TypedArray obtainStyledAttributes = view.getContext().getTheme().obtainStyledAttributes(R.styleable.mappls_poi_along_route);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "view.context.theme.obtai…e.mappls_poi_along_route)");
        int color = obtainStyledAttributes.getColor(R.styleable.mappls_poi_along_route_poi_add_way_point_button_text_color, ContextCompat.getColor(view.getContext(), R.color.search_category_text_color));
        int color2 = obtainStyledAttributes.getColor(R.styleable.mappls_poi_along_route_poi_add_way_point_button_background_color, ContextCompat.getColor(view.getContext(), R.color.search_category_next_button_back));
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding = this.h;
        if (mapplsDirectionFragmentAddWayPointDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsDirectionFragmentAddWayPointDialogBinding = null;
        }
        mapplsDirectionFragmentAddWayPointDialogBinding.buttonAddWaypoint.setTextColor(color);
        if (!this.m) {
            MapView mapView = this.k;
            if (mapView != null) {
                mapView.getMapAsync(this);
            }
            this.m = true;
        }
        Drawable drawable = ContextCompat.getDrawable(view.getContext(), R.drawable.mappls_direction_dr_button_background);
        if (drawable != null) {
            drawable.setColorFilter(new PorterDuffColorFilter(color2, PorterDuff.Mode.SRC_IN));
        }
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding2 = this.h;
        if (mapplsDirectionFragmentAddWayPointDialogBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsDirectionFragmentAddWayPointDialogBinding2 = null;
        }
        mapplsDirectionFragmentAddWayPointDialogBinding2.buttonAddWaypoint.setBackground(drawable);
        PoiResult poiResult = this.i;
        if (poiResult != null) {
            Intrinsics.checkNotNull(poiResult);
            a(poiResult);
        }
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding3 = this.h;
        if (mapplsDirectionFragmentAddWayPointDialogBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsDirectionFragmentAddWayPointDialogBinding3 = null;
        }
        mapplsDirectionFragmentAddWayPointDialogBinding3.selectedWaypointBackIcon.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.direction.ui.fragment.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                a.e(a.this, view2);
            }
        });
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding4 = this.h;
        if (mapplsDirectionFragmentAddWayPointDialogBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsDirectionFragmentAddWayPointDialogBinding4 = null;
        }
        mapplsDirectionFragmentAddWayPointDialogBinding4.selectedWaypointClearBtn.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.direction.ui.fragment.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                a.h(a.this, view2);
            }
        });
        MapplsDirectionFragmentAddWayPointDialogBinding mapplsDirectionFragmentAddWayPointDialogBinding5 = this.h;
        if (mapplsDirectionFragmentAddWayPointDialogBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            mapplsDirectionFragmentAddWayPointDialogBinding5 = null;
        }
        TextView textView = mapplsDirectionFragmentAddWayPointDialogBinding5.selectedWaypointSearchInput;
        PoiResult poiResult2 = this.i;
        textView.setText(poiResult2 != null ? poiResult2.getPoiType() : null);
    }
}
