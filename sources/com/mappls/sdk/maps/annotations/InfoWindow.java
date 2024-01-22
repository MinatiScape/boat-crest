package com.mappls.sdk.maps.annotations;

import android.content.res.Resources;
import android.graphics.PointF;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.BaseMapplsHelper;
import com.mappls.sdk.maps.CoordinateCallback;
import com.mappls.sdk.maps.CoordinateResult;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.geometry.LatLng;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
@Deprecated
/* loaded from: classes11.dex */
public class InfoWindow {

    /* renamed from: a  reason: collision with root package name */
    public WeakReference<Marker> f12668a;
    public WeakReference<MapplsMap> b;
    public float c;
    public float d;
    public float e;
    public float f;
    public PointF g;
    public boolean h;
    @LayoutRes
    public int i;
    @Nullable
    public final ViewTreeObserver.OnGlobalLayoutListener j = new d();
    public WeakReference<View> view;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MapplsMap mapplsMap = (MapplsMap) InfoWindow.this.b.get();
            if (mapplsMap != null) {
                MapplsMap.OnInfoWindowClickListener onInfoWindowClickListener = mapplsMap.getOnInfoWindowClickListener();
                if (onInfoWindowClickListener != null ? onInfoWindowClickListener.onInfoWindowClick(InfoWindow.this.m()) : false) {
                    return;
                }
                InfoWindow.this.l();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements View.OnLongClickListener {
        public b() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            MapplsMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener;
            MapplsMap mapplsMap = (MapplsMap) InfoWindow.this.b.get();
            if (mapplsMap == null || (onInfoWindowLongClickListener = mapplsMap.getOnInfoWindowLongClickListener()) == null) {
                return true;
            }
            onInfoWindowLongClickListener.onInfoWindowLongClick(InfoWindow.this.m());
            return true;
        }
    }

    /* loaded from: classes11.dex */
    public class c implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ MapplsMap f12669a;
        public final /* synthetic */ View b;

        public c(MapplsMap mapplsMap, View view) {
            this.f12669a = mapplsMap;
            this.b = view;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            if (list.size() > 0) {
                LatLng latLng = new LatLng(list.get(0).getLatitude().doubleValue(), list.get(0).getLongitude().doubleValue());
                InfoWindow.this.g = this.f12669a.getProjection().toScreenLocation(latLng);
                View view = this.b;
                if (view instanceof BubbleLayout) {
                    view.setX((InfoWindow.this.g.x + InfoWindow.this.e) - InfoWindow.this.d);
                } else {
                    view.setX((InfoWindow.this.g.x - (this.b.getMeasuredWidth() / 2)) - InfoWindow.this.d);
                }
                this.b.setY(InfoWindow.this.g.y + InfoWindow.this.f);
            }
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
        }
    }

    /* loaded from: classes11.dex */
    public class d implements ViewTreeObserver.OnGlobalLayoutListener {
        public d() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            View view = InfoWindow.this.view.get();
            if (view != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                InfoWindow.this.f = (-view.getMeasuredHeight()) + InfoWindow.this.c;
                InfoWindow.this.update();
            }
        }
    }

    public InfoWindow(MapView mapView, int i, MapplsMap mapplsMap) {
        this.i = i;
        n(LayoutInflater.from(mapView.getContext()).inflate(i, (ViewGroup) mapView, false), mapplsMap);
    }

    @Nullable
    public View getView() {
        WeakReference<View> weakReference = this.view;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public void j(@NonNull Marker marker, MapplsMap mapplsMap, @NonNull MapView mapView) {
        View view = this.view.get();
        if (view == null) {
            view = LayoutInflater.from(mapView.getContext()).inflate(this.i, (ViewGroup) mapView, false);
            n(view, mapplsMap);
        }
        this.b = new WeakReference<>(mapplsMap);
        String title = marker.getTitle();
        TextView textView = (TextView) view.findViewById(R.id.infowindow_title);
        if (!TextUtils.isEmpty(title)) {
            textView.setText(title);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        String snippet = marker.getSnippet();
        TextView textView2 = (TextView) view.findViewById(R.id.infowindow_description);
        if (!TextUtils.isEmpty(snippet)) {
            textView2.setText(snippet);
            textView2.setVisibility(0);
            return;
        }
        textView2.setVisibility(8);
    }

    @NonNull
    public InfoWindow k() {
        MapplsMap mapplsMap = this.b.get();
        if (this.h && mapplsMap != null) {
            this.h = false;
            View view = this.view.get();
            if (view != null && view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            Marker m = m();
            MapplsMap.OnInfoWindowCloseListener onInfoWindowCloseListener = mapplsMap.getOnInfoWindowCloseListener();
            if (onInfoWindowCloseListener != null) {
                onInfoWindowCloseListener.onInfoWindowClose(m);
            }
            q(null);
        }
        return this;
    }

    public final void l() {
        MapplsMap mapplsMap = this.b.get();
        Marker marker = this.f12668a.get();
        if (marker != null && mapplsMap != null) {
            mapplsMap.deselectMarker(marker);
        }
        k();
    }

    @Nullable
    public Marker m() {
        WeakReference<Marker> weakReference = this.f12668a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public final void n(@NonNull View view, MapplsMap mapplsMap) {
        this.b = new WeakReference<>(mapplsMap);
        this.h = false;
        this.view = new WeakReference<>(view);
        view.setOnClickListener(new a());
        view.setOnLongClickListener(new b());
    }

    public void o() {
        View view = this.view.get();
        if (view != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(this.j);
            }
        }
    }

    @NonNull
    public InfoWindow p(@NonNull MapView mapView, Marker marker, @NonNull LatLng latLng, int i, int i2) {
        float f;
        boolean z;
        float f2;
        boolean z2;
        q(marker);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        MapplsMap mapplsMap = this.b.get();
        View view = this.view.get();
        if (view != null && mapplsMap != null) {
            view.measure(0, 0);
            float f3 = i2;
            this.c = f3;
            this.d = -i;
            PointF screenLocation = mapplsMap.getProjection().toScreenLocation(latLng);
            this.g = screenLocation;
            float f4 = i;
            float measuredWidth = (screenLocation.x - (view.getMeasuredWidth() / 2)) + f4;
            float measuredHeight = (this.g.y - view.getMeasuredHeight()) + f3;
            if (view instanceof BubbleLayout) {
                Resources resources = mapView.getContext().getResources();
                float measuredWidth2 = view.getMeasuredWidth() + measuredWidth;
                float right = mapView.getRight();
                float left = mapView.getLeft();
                float dimension = resources.getDimension(R.dimen.mappls_maps_infowindow_margin);
                float dimension2 = resources.getDimension(R.dimen.mappls_maps_infowindow_tipview_width) / 2.0f;
                float measuredWidth3 = (view.getMeasuredWidth() / 2) - dimension2;
                float f5 = this.g.x;
                if (f5 >= 0.0f && f5 <= mapView.getWidth()) {
                    float f6 = this.g.y;
                    if (f6 >= 0.0f && f6 <= mapView.getHeight()) {
                        if (measuredWidth2 > right) {
                            float f7 = measuredWidth2 - right;
                            f = measuredWidth - f7;
                            measuredWidth3 += f7 + dimension2;
                            measuredWidth2 = view.getMeasuredWidth() + f;
                            z = true;
                        } else {
                            f = measuredWidth;
                            z = false;
                        }
                        if (measuredWidth < left) {
                            float f8 = left - measuredWidth;
                            f += f8;
                            float f9 = measuredWidth3 - (f8 + dimension2);
                            measuredWidth = f;
                            f2 = f9;
                            z2 = true;
                        } else {
                            f2 = measuredWidth3;
                            z2 = false;
                        }
                        if (z) {
                            float f10 = right - measuredWidth2;
                            if (f10 < dimension) {
                                float f11 = dimension - f10;
                                f -= f11;
                                f2 += f11 - dimension2;
                                measuredWidth = f;
                            }
                        }
                        if (z2) {
                            float f12 = measuredWidth - left;
                            if (f12 < dimension) {
                                float f13 = dimension - f12;
                                measuredWidth3 = f2 - (f13 - dimension2);
                                measuredWidth = f + f13;
                            }
                        }
                        measuredWidth = f;
                        measuredWidth3 = f2;
                    }
                }
                ((BubbleLayout) view).setArrowPosition(measuredWidth3);
            }
            view.setX(measuredWidth);
            view.setY(measuredHeight);
            this.e = (measuredWidth - this.g.x) - f4;
            this.f = (-view.getMeasuredHeight()) + i2;
            k();
            mapView.addView(view, layoutParams);
            this.h = true;
        }
        return this;
    }

    @NonNull
    public InfoWindow q(Marker marker) {
        this.f12668a = new WeakReference<>(marker);
        return this;
    }

    public void update() {
        MapplsMap mapplsMap = this.b.get();
        Marker marker = this.f12668a.get();
        View view = this.view.get();
        if (mapplsMap == null || marker == null || view == null) {
            return;
        }
        if (marker.getMapplsPin() != null && marker.getPosition() == null) {
            try {
                Object newInstance = BaseMapplsHelper.class.newInstance();
                Method declaredMethod = BaseMapplsHelper.class.getDeclaredMethod("getAnnotation", String.class, CoordinateCallback.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(newInstance, marker.getMapplsPin(), new c(mapplsMap, view));
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return;
            } catch (InstantiationException e3) {
                e3.printStackTrace();
                return;
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
                return;
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
                return;
            }
        }
        PointF screenLocation = mapplsMap.getProjection().toScreenLocation(marker.getPosition());
        this.g = screenLocation;
        if (view instanceof BubbleLayout) {
            view.setX((screenLocation.x + this.e) - this.d);
        } else {
            view.setX((screenLocation.x - (view.getMeasuredWidth() / 2)) - this.d);
        }
        view.setY(this.g.y + this.f);
    }

    public InfoWindow(@NonNull View view, MapplsMap mapplsMap) {
        n(view, mapplsMap);
    }
}
