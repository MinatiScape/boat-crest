package com.mappls.sdk.direction.ui.fragment;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionPreviewFragmentBinding;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.utils.PolylineUtils;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class b extends Fragment implements OnMapReadyCallback {
    public MapplsDirectionPreviewFragmentBinding h;
    public com.mappls.sdk.direction.ui.adapters.a i;
    public List<LegStep> j;
    public int k;
    public MapView l;
    public MapplsMap m;
    public com.mappls.sdk.direction.ui.plugin.a n;
    public int o;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (b.this.h.pagerDirectionPreview.getCurrentItem() + 1 < b.this.j.size()) {
                ViewPager viewPager = b.this.h.pagerDirectionPreview;
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    }

    /* renamed from: com.mappls.sdk.direction.ui.fragment.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class View$OnClickListenerC0615b implements View.OnClickListener {
        public View$OnClickListenerC0615b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (b.this.h.pagerDirectionPreview.getCurrentItem() > 0) {
                ViewPager viewPager = b.this.h.pagerDirectionPreview;
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements ViewPager.OnPageChangeListener {
        public c() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public final void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public final void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public final void onPageSelected(int i) {
            b.b(b.this, i);
        }
    }

    /* loaded from: classes11.dex */
    public class d extends OnBackPressedCallback {
        public d() {
            super(true);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public final void handleOnBackPressed() {
            b.this.getParentFragmentManager().popBackStack(b.this.getClass().getName(), 1);
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Style.OnStyleLoaded {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ MapplsMap f12588a;

        public e(MapplsMap mapplsMap) {
            this.f12588a = mapplsMap;
        }

        @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
        public final void onStyleLoaded(@NonNull Style style) {
            b bVar = b.this;
            bVar.n = new com.mappls.sdk.direction.ui.plugin.a(bVar.l, this.f12588a);
            b.this.n.a(true);
            b bVar2 = b.this;
            b.b(bVar2, bVar2.o);
        }
    }

    public b(List<LegStep> list, MapView mapView, int i, @StyleRes int i2) {
        this.j = list;
        this.l = mapView;
        this.o = i;
        this.k = i2;
    }

    public static void b(b bVar, int i) {
        List<LegStep> list;
        if (bVar.m == null || (list = bVar.j) == null || list.size() <= i) {
            return;
        }
        LegStep legStep = bVar.j.get(i);
        String geometry = legStep.geometry();
        Objects.requireNonNull(geometry);
        List<Point> decode = PolylineUtils.decode(geometry, 6);
        if (decode.size() > 0) {
            Point point = decode.get(0);
            bVar.n.a((float) (legStep.maneuver().bearingAfter() != null ? legStep.maneuver().bearingAfter().doubleValue() : 0.0d), new LatLng(point.latitude(), point.longitude()));
            bVar.m.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(point.latitude(), point.longitude()), 16.0d));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        requireContext().setTheme(this.k);
        this.h = (MapplsDirectionPreviewFragmentBinding) DataBindingUtil.inflate(layoutInflater, R.layout.mappls_direction_preview_fragment, viewGroup, false);
        Resources.Theme theme = requireContext().getTheme();
        int[] iArr = R.styleable.mappls_direction;
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(iArr);
        Objects.requireNonNull(obtainStyledAttributes);
        this.h.layoutCommonToolbar.toolbar.setTitleTextColor(obtainStyledAttributes.getColor(R.styleable.mappls_direction_direction_preview_toolbar_text_color, ContextCompat.getColor(requireContext(), R.color.mappls_direction_colorBlack)));
        TypedArray obtainStyledAttributes2 = requireContext().getTheme().obtainStyledAttributes(iArr);
        Objects.requireNonNull(obtainStyledAttributes2);
        this.h.layoutCommonToolbar.toolbar.setBackgroundColor(obtainStyledAttributes2.getColor(R.styleable.mappls_direction_directions_preview_toolbar_bg_color, ContextCompat.getColor(requireContext(), R.color.white)));
        this.h.layoutCommonToolbar.toolbar.setTitle("DirectionList");
        this.h.layoutCommonToolbar.toolbar.setNavigationOnClickListener(new o(this));
        MapView mapView = this.l;
        if (mapView != null) {
            mapView.getMapAsync(this);
        }
        com.mappls.sdk.direction.ui.adapters.a aVar = new com.mappls.sdk.direction.ui.adapters.a(this.j, this.k, getContext());
        this.i = aVar;
        this.h.pagerDirectionPreview.setAdapter(aVar);
        com.mappls.sdk.direction.ui.adapters.a aVar2 = this.i;
        if (this.j != null) {
            aVar2.notifyDataSetChanged();
        } else {
            aVar2.getClass();
        }
        this.h.pagerDirectionPreview.setCurrentItem(this.o);
        this.h.setOnNextClick(new a());
        this.h.setOnPreviousClick(new View$OnClickListenerC0615b());
        this.h.pagerDirectionPreview.addOnPageChangeListener(new c());
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new d());
        return this.h.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroyView() {
        super.onDestroyView();
        com.mappls.sdk.direction.ui.plugin.a aVar = this.n;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public final void onMapError(int i, String str) {
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public final void onMapReady(MapplsMap mapplsMap) {
        this.m = mapplsMap;
        mapplsMap.getStyle(new e(mapplsMap));
    }
}
