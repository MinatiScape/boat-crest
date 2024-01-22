package com.mappls.sdk.maps.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.mappls.sdk.maps.LogoImageLoadCallback;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.Mappls;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
/* loaded from: classes11.dex */
public class LogoView extends ImageView implements MapplsMap.OnCameraIdleListener, MapView.OnDidFinishLoadingStyleListener {
    public static final LatLngBounds k = new LatLngBounds.Builder().include(new LatLng(37.238124d, 64.805223d)).include(new LatLng(5.100697d, 98.574512d)).build();
    public MapplsMap h;
    public MapView i;
    public IRefreshListener j;

    /* loaded from: classes11.dex */
    public interface IRefreshListener {
        void refresh();
    }

    /* loaded from: classes11.dex */
    public class a implements LogoImageLoadCallback {

        /* renamed from: com.mappls.sdk.maps.widgets.LogoView$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class RunnableC0637a implements Runnable {
            public final /* synthetic */ Bitmap h;

            public RunnableC0637a(Bitmap bitmap) {
                this.h = bitmap;
            }

            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmap = this.h;
                if (bitmap == null) {
                    if (LogoView.k.contains(LogoView.this.h.getCameraPosition().target)) {
                        LogoView.this.setImageDrawable(ContextCompat.getDrawable(Mappls.getApplicationContext(), R.drawable.mappls_maps_logo_icon));
                    } else {
                        LogoView.this.setImageDrawable(ContextCompat.getDrawable(Mappls.getApplicationContext(), R.drawable.mappls_maps_logo_icon_global));
                    }
                } else {
                    LogoView.this.setImageBitmap(bitmap);
                }
                if (LogoView.this.j != null) {
                    LogoView.this.j.refresh();
                }
            }
        }

        /* loaded from: classes11.dex */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LogoView.k.contains(LogoView.this.h.getCameraPosition().target)) {
                    LogoView.this.setImageDrawable(ContextCompat.getDrawable(Mappls.getApplicationContext(), R.drawable.mappls_maps_logo_icon));
                } else {
                    LogoView.this.setImageDrawable(ContextCompat.getDrawable(Mappls.getApplicationContext(), R.drawable.mappls_maps_logo_icon_global));
                }
                LogoView.this.i.measure(View.MeasureSpec.makeMeasureSpec(LogoView.this.i.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(LogoView.this.i.getMeasuredHeight(), 1073741824));
                LogoView.this.i.layout(LogoView.this.i.getLeft(), LogoView.this.i.getTop(), LogoView.this.i.getRight(), LogoView.this.i.getBottom());
            }
        }

        public a() {
        }

        @Override // com.mappls.sdk.maps.LogoImageLoadCallback
        public void onFailure() {
            LogoView.this.post(new b());
        }

        @Override // com.mappls.sdk.maps.LogoImageLoadCallback
        public void onSuccess(Bitmap bitmap) {
            LogoView.this.post(new RunnableC0637a(bitmap));
        }
    }

    public LogoView(Context context) {
        super(context);
    }

    public final void d() {
        a aVar = new a();
        if (this.h.getStyle() != null) {
            if (k.contains(this.h.getCameraPosition().target)) {
                Mappls.getStyleHelper().getBitmapLogo(this.h.getStyle().getMapplsStyle(), aVar);
                return;
            } else {
                Mappls.getStyleHelper().getGlobalBitmapLogo(this.h.getStyle().getMapplsStyle(), aVar);
                return;
            }
        }
        if (k.contains(this.h.getCameraPosition().target)) {
            setImageDrawable(ContextCompat.getDrawable(Mappls.getApplicationContext(), R.drawable.mappls_maps_logo_icon));
        } else {
            setImageDrawable(ContextCompat.getDrawable(Mappls.getApplicationContext(), R.drawable.mappls_maps_logo_icon_global));
        }
        MapView mapView = this.i;
        mapView.measure(View.MeasureSpec.makeMeasureSpec(mapView.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.i.getMeasuredHeight(), 1073741824));
        MapView mapView2 = this.i;
        mapView2.layout(mapView2.getLeft(), this.i.getTop(), this.i.getRight(), this.i.getBottom());
    }

    public void injectLogoView(MapView mapView, MapplsMap mapplsMap) {
        this.h = mapplsMap;
        this.i = mapView;
        mapplsMap.addOnCameraIdleListener(this);
        mapView.addOnDidFinishLoadingStyleListener(this);
        d();
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnCameraIdleListener
    public void onCameraIdle() {
        d();
    }

    @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingStyleListener
    public void onDidFinishLoadingStyle() {
        d();
    }

    public void setRefreshListener(IRefreshListener iRefreshListener) {
        this.j = iRefreshListener;
    }

    public LogoView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LogoView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
