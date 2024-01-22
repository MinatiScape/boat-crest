package com.mappls.sdk.maps.widgets.indoor;

import android.animation.LayoutTransition;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.maps.BuildConfig;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.FillExtrusionLayer;
import com.mappls.sdk.maps.style.layers.FillLayer;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.LineLayer;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.maps.style.layers.PropertyValue;
import com.mappls.sdk.maps.style.layers.SymbolLayer;
import com.mappls.sdk.maps.widgets.indoor.iface.IndoorListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class FloorControllerView extends ScrollView implements MapplsMap.OnCameraMoveStartedListener, MapplsMap.OnCameraIdleListener, MapView.OnDidFinishRenderingMapListener {
    public final List<IndoorListener> h;
    public LinearLayout i;
    public int j;
    public MapplsMap k;
    public String l;
    public int m;
    public boolean n;
    public boolean o;
    public boolean p;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FloorView floorView = (FloorView) view;
            Integer number = floorView.getFloor().getNumber();
            FloorControllerView.this.setFloor(number.intValue());
            FloorControllerView.this.onFloorWillChange(floorView.getFloor());
            FloorControllerView.this.onFloorChange(floorView.getFloor());
            if (MapplsLMSManager.isInitialised()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("event_view", "indoor_floor");
                    jSONObject.put("selected_floor", number);
                    jSONObject.put("building_id", FloorControllerView.this.l);
                    MapplsLMSManager.getInstance().add("click", BuildConfig.MAPPLS_SDK_NAME, BuildConfig.MAPPLS_SDK_VERSION, jSONObject);
                } catch (JSONException unused) {
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f12862a;

        public b(int i) {
            this.f12862a = i;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public void onStyleLoaded(@NonNull Style style) {
            FloorControllerView.this.m = this.f12862a;
            for (String str : IndoorConstants.f12863a) {
                Layer layer = style.getLayer(str);
                if (layer != null) {
                    if (layer instanceof FillLayer) {
                        FillLayer fillLayer = (FillLayer) layer;
                        if (fillLayer.getFilter() != null) {
                            fillLayer.setFilter(Expression.Converter.convert(FloorControllerView.this.d(this.f12862a, fillLayer.getFilter().toString())));
                        }
                    } else if (layer instanceof SymbolLayer) {
                        SymbolLayer symbolLayer = (SymbolLayer) layer;
                        if (symbolLayer.getFilter() != null) {
                            symbolLayer.setFilter(Expression.Converter.convert(FloorControllerView.this.d(this.f12862a, symbolLayer.getFilter().toString())));
                        }
                    } else if (layer instanceof FillExtrusionLayer) {
                        FillExtrusionLayer fillExtrusionLayer = (FillExtrusionLayer) layer;
                        if (fillExtrusionLayer.getFilter() != null) {
                            fillExtrusionLayer.setFilter(Expression.Converter.convert(FloorControllerView.this.d(this.f12862a, fillExtrusionLayer.getFilter().toString())));
                        }
                    } else if (layer instanceof LineLayer) {
                        LineLayer lineLayer = (LineLayer) layer;
                        if (lineLayer.getFilter() != null) {
                            lineLayer.setFilter(Expression.Converter.convert(FloorControllerView.this.d(this.f12862a, lineLayer.getFilter().toString())));
                        }
                    }
                }
            }
            Layer layer2 = style.getLayer("footprints_indoor_2_3floors");
            if (layer2 != null) {
                PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                propertyValueArr[0] = PropertyFactory.visibility(this.f12862a > 0 ? Property.VISIBLE : "none");
                layer2.setProperties(propertyValueArr);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FloorControllerView floorControllerView = FloorControllerView.this;
            FloorControllerView.this.smoothScrollBy(0, (floorControllerView.getChildAt(floorControllerView.getChildCount() - 1).getBottom() + FloorControllerView.this.getPaddingBottom()) - (FloorControllerView.this.getScrollY() + FloorControllerView.this.getHeight()));
        }
    }

    public FloorControllerView(@NonNull Context context) {
        super(context);
        this.h = new ArrayList();
        this.j = 0;
        this.l = null;
        this.m = 0;
        this.n = true;
        this.o = false;
        this.p = false;
        f();
    }

    public void addOnIndoorListener(@NonNull IndoorListener indoorListener) {
        this.h.add(indoorListener);
    }

    public final String d(int i, String str) {
        List<String> possibleFloors = IndoorConstants.getPossibleFloors();
        String internalFloorName = IndoorConstants.getInternalFloorName(i);
        String str2 = str;
        for (String str3 : possibleFloors) {
            if (str.contains(str3)) {
                str2 = str.replaceAll(str3, internalFloorName);
            }
        }
        return str2;
    }

    public final void e() {
        this.n = true;
        if (this.h.size() > 0) {
            for (IndoorListener indoorListener : this.h) {
                indoorListener.hideControl();
            }
        }
    }

    public final void f() {
        setVerticalScrollBarEnabled(true);
        this.j = (int) getContext().getResources().getDimension(R.dimen.mappls_maps_ui_floor_button_size);
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.i = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(this.j + 10, -2));
        this.i.setOrientation(1);
        this.i.setBackgroundColor(0);
        this.i.setVerticalGravity(80);
        this.i.setLayoutTransition(new LayoutTransition());
        setLayoutTransition(new LayoutTransition());
        if (Build.VERSION.SDK_INT >= 16) {
            getLayoutTransition().enableTransitionType(4);
            this.i.getLayoutTransition().enableTransitionType(4);
        }
        addView(this.i);
    }

    public void g() {
        postDelayed(new c(), 300L);
    }

    public MapplsMap getMap() {
        return this.k;
    }

    public int getSelectedFloor() {
        return this.m;
    }

    public final void h(int i, int i2, int i3) {
        this.n = false;
        this.m = i2;
        if (this.h.size() > 0) {
            for (IndoorListener indoorListener : this.h) {
                indoorListener.showControl(i, i2, i3);
            }
        }
    }

    public boolean isFollowMe() {
        return this.p;
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnCameraIdleListener
    public void onCameraIdle() {
        if (this.o) {
            this.o = false;
            onCameraMove();
        }
    }

    public void onCameraMove() {
        if (this.p) {
            return;
        }
        MapplsMap mapplsMap = this.k;
        List<Feature> queryRenderedFeatures = mapplsMap.queryRenderedFeatures(mapplsMap.getProjection().toScreenLocation(this.k.getCameraPosition().target), "footprints_indoor_3d_1_floor");
        if (this.k.getCameraPosition().zoom > 15.9d && queryRenderedFeatures.size() > 0) {
            Feature feature = queryRenderedFeatures.get(0);
            int intValue = feature.hasProperty("FLOOR") ? feature.getNumberProperty("FLOOR").intValue() : 0;
            int intValue2 = feature.hasProperty("INI_FLOOR") ? feature.getNumberProperty("INI_FLOOR").intValue() : 0;
            if (intValue > 1) {
                if (feature.hasProperty("BLDG_ID")) {
                    String stringProperty = feature.getStringProperty("BLDG_ID");
                    String str = this.l;
                    if (str != null && !str.equalsIgnoreCase(stringProperty)) {
                        h(intValue2, 0, intValue);
                    } else if (this.n) {
                        h(intValue2, 0, intValue);
                    }
                    this.l = stringProperty;
                    return;
                }
                return;
            }
            e();
            return;
        }
        e();
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnCameraMoveStartedListener
    public void onCameraMoveStarted(int i) {
        if (1 == i || 3 == i) {
            this.o = true;
        }
    }

    public void onDestroy() {
        this.k.removeOnCameraIdleListener(this);
        this.k.removeOnCameraMoveStartedListener(this);
    }

    @Override // com.mappls.sdk.maps.MapView.OnDidFinishRenderingMapListener
    public void onDidFinishRenderingMap(boolean z) {
        if (z) {
            onCameraMove();
        }
    }

    public void onFloorChange(@Nullable Floor floor) {
        for (int i = 0; i < this.i.getChildCount(); i++) {
            FloorView floorView = (FloorView) this.i.getChildAt(i);
            Integer number = floorView.getFloor().getNumber();
            if (floor != null && floor.getNumber().equals(number)) {
                floorView.setSelected(true);
            } else {
                floorView.setSelected(false);
            }
        }
    }

    public void onFloorWillChange(@Nullable Floor floor) {
        for (int i = 0; i < this.i.getChildCount(); i++) {
            FloorView floorView = (FloorView) this.i.getChildAt(i);
            Integer number = floorView.getFloor().getNumber();
            if (floor != null && floor.getNumber().equals(number)) {
                floorView.setLoading();
            } else {
                floorView.setSelected(false);
            }
        }
    }

    public void onFloorsChange(@NonNull List<Floor> list) {
        this.i.removeAllViews();
        if (this.k.getUiSettings().isLayerControlEnabled()) {
            ArrayList<Floor> arrayList = new ArrayList(list);
            Collections.reverse(arrayList);
            for (Floor floor : arrayList) {
                FloorView floorView = new FloorView(getContext(), floor);
                int i = this.j;
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
                layoutParams.setMargins(5, 5, 5, 5);
                floorView.setLayoutParams(layoutParams);
                floorView.setOnClickListener(new a());
                this.i.addView(floorView);
            }
            g();
            onFloorChange(new Floor(0, IndoorConstants.getFloorName(0), IndoorConstants.getInternalFloorName(0)));
        }
    }

    public boolean removeIndoorListener(IndoorListener indoorListener) {
        Iterator<IndoorListener> it = this.h.iterator();
        while (it.hasNext()) {
            IndoorListener next = it.next();
            if (next != null) {
                if (indoorListener == next) {
                }
            }
            it.remove();
            return true;
        }
        return false;
    }

    public void setFloor(int i) {
        this.k.getStyle(new b(i));
    }

    public void setFollowMe(boolean z) {
        this.p = z;
    }

    public void setMap(@NonNull MapplsMap mapplsMap) {
        this.k = mapplsMap;
        mapplsMap.addOnCameraIdleListener(this);
        mapplsMap.addOnCameraMoveStartedListener(this);
    }

    public FloorControllerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new ArrayList();
        this.j = 0;
        this.l = null;
        this.m = 0;
        this.n = true;
        this.o = false;
        this.p = false;
        f();
    }

    public FloorControllerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new ArrayList();
        this.j = 0;
        this.l = null;
        this.m = 0;
        this.n = true;
        this.o = false;
        this.p = false;
        f();
    }
}
