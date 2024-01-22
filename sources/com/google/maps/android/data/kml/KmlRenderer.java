package com.google.maps.android.data.kml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.Marker;
import com.google.common.net.HttpHeaders;
import com.google.maps.android.collections.GroundOverlayManager;
import com.google.maps.android.collections.MarkerManager;
import com.google.maps.android.collections.PolygonManager;
import com.google.maps.android.collections.PolylineManager;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.MultiGeometry;
import com.google.maps.android.data.Renderer;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public class KmlRenderer extends Renderer {
    public final Set<String> D;
    public boolean E;
    public boolean F;
    public ArrayList<KmlContainer> G;

    /* loaded from: classes10.dex */
    public class a extends AsyncTask<String, Void, Bitmap> {

        /* renamed from: a  reason: collision with root package name */
        public final String f11556a;

        public a(String str) {
            this.f11556a = str;
            KmlRenderer.this.downloadStarted();
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Bitmap doInBackground(String... strArr) {
            try {
                return KmlRenderer.this.c0(this.f11556a);
            } catch (MalformedURLException unused) {
                return BitmapFactory.decodeFile(this.f11556a);
            } catch (IOException e) {
                Log.e("KmlRenderer", "Image [" + this.f11556a + "] download issue", e);
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                Log.e("KmlRenderer", "Image at this URL could not be found " + this.f11556a);
            } else {
                KmlRenderer.this.cacheBitmap(this.f11556a, bitmap);
                if (KmlRenderer.this.isLayerOnMap()) {
                    KmlRenderer kmlRenderer = KmlRenderer.this;
                    kmlRenderer.S(this.f11556a, kmlRenderer.getGroundOverlayMap(), true);
                    KmlRenderer kmlRenderer2 = KmlRenderer.this;
                    kmlRenderer2.R(this.f11556a, kmlRenderer2.G, true);
                }
            }
            KmlRenderer.this.downloadFinished();
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AsyncTask<String, Void, Bitmap> {

        /* renamed from: a  reason: collision with root package name */
        public final String f11557a;

        public b(String str) {
            this.f11557a = str;
            KmlRenderer.this.downloadStarted();
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Bitmap doInBackground(String... strArr) {
            try {
                return KmlRenderer.this.c0(this.f11557a);
            } catch (MalformedURLException unused) {
                return BitmapFactory.decodeFile(this.f11557a);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                Log.e("KmlRenderer", "Image at this URL could not be found " + this.f11557a);
            } else {
                KmlRenderer.this.cacheBitmap(this.f11557a, bitmap);
                if (KmlRenderer.this.isLayerOnMap()) {
                    KmlRenderer kmlRenderer = KmlRenderer.this;
                    kmlRenderer.X(this.f11557a, kmlRenderer.getAllFeatures());
                    KmlRenderer kmlRenderer2 = KmlRenderer.this;
                    kmlRenderer2.O(this.f11557a, kmlRenderer2.G);
                }
            }
            KmlRenderer.this.downloadFinished();
        }
    }

    public KmlRenderer(GoogleMap googleMap, Context context, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager, @Nullable Renderer.ImagesCache imagesCache) {
        super(googleMap, context, markerManager, polygonManager, polylineManager, groundOverlayManager, imagesCache);
        this.D = new HashSet();
        this.E = false;
        this.F = false;
    }

    public static boolean d0(KmlContainer kmlContainer, boolean z) {
        return z && (!kmlContainer.hasProperty("visibility") || Integer.parseInt(kmlContainer.getProperty("visibility")) != 0);
    }

    public final void O(String str, Iterable<KmlContainer> iterable) {
        for (KmlContainer kmlContainer : iterable) {
            X(str, kmlContainer.b());
            if (kmlContainer.hasContainers()) {
                O(str, kmlContainer.getContainers());
            }
        }
    }

    public final void P(Iterable<KmlContainer> iterable, boolean z) {
        for (KmlContainer kmlContainer : iterable) {
            boolean d0 = d0(kmlContainer, z);
            if (kmlContainer.d() != null) {
                putStyles(kmlContainer.d());
            }
            if (kmlContainer.c() != null) {
                super.assignStyleMap(kmlContainer.c(), getStylesRenderer());
            }
            Q(kmlContainer, d0);
            if (kmlContainer.hasContainers()) {
                P(kmlContainer.getContainers(), d0);
            }
        }
    }

    public final void Q(KmlContainer kmlContainer, boolean z) {
        for (KmlPlacemark kmlPlacemark : kmlContainer.getPlacemarks()) {
            boolean z2 = z && Renderer.getPlacemarkVisibility(kmlPlacemark);
            if (kmlPlacemark.getGeometry() != null) {
                String id = kmlPlacemark.getId();
                Geometry geometry = kmlPlacemark.getGeometry();
                KmlStyle placemarkStyle = getPlacemarkStyle(id);
                KmlPlacemark kmlPlacemark2 = kmlPlacemark;
                Object addKmlPlacemarkToMap = addKmlPlacemarkToMap(kmlPlacemark2, geometry, placemarkStyle, kmlPlacemark2.getInlineStyle(), z2);
                kmlContainer.e(kmlPlacemark2, addKmlPlacemarkToMap);
                putContainerFeature(addKmlPlacemarkToMap, kmlPlacemark);
            }
        }
    }

    public final void R(String str, Iterable<KmlContainer> iterable, boolean z) {
        for (KmlContainer kmlContainer : iterable) {
            boolean d0 = d0(kmlContainer, z);
            S(str, kmlContainer.a(), d0);
            if (kmlContainer.hasContainers()) {
                R(str, kmlContainer.getContainers(), d0);
            }
        }
    }

    public final void S(String str, HashMap<KmlGroundOverlay, GroundOverlay> hashMap, boolean z) {
        BitmapDescriptor cachedGroundOverlayImage = getCachedGroundOverlayImage(str);
        for (KmlGroundOverlay kmlGroundOverlay : hashMap.keySet()) {
            if (kmlGroundOverlay.getImageUrl().equals(str)) {
                GroundOverlay attachGroundOverlay = attachGroundOverlay(kmlGroundOverlay.a().image(cachedGroundOverlayImage));
                if (!z) {
                    attachGroundOverlay.setVisible(false);
                }
                hashMap.put(kmlGroundOverlay, attachGroundOverlay);
            }
        }
    }

    public final void T(HashMap<KmlGroundOverlay, GroundOverlay> hashMap) {
        for (KmlGroundOverlay kmlGroundOverlay : hashMap.keySet()) {
            String imageUrl = kmlGroundOverlay.getImageUrl();
            if (imageUrl != null && kmlGroundOverlay.getLatLngBox() != null) {
                if (getCachedGroundOverlayImage(imageUrl) != null) {
                    S(imageUrl, getGroundOverlayMap(), true);
                } else {
                    this.D.add(imageUrl);
                }
            }
        }
    }

    public final void U(HashMap<KmlGroundOverlay, GroundOverlay> hashMap, Iterable<KmlContainer> iterable) {
        T(hashMap);
        for (KmlContainer kmlContainer : iterable) {
            U(kmlContainer.a(), kmlContainer.getContainers());
        }
    }

    public final void V(String str, KmlStyle kmlStyle, KmlStyle kmlStyle2, Geometry geometry, Object obj) {
        if (geometry == null) {
            return;
        }
        if ("Point".equals(geometry.getGeometryType())) {
            W(str, kmlStyle, kmlStyle2, (Marker) obj);
        } else if ("MultiGeometry".equals(geometry.getGeometryType())) {
            Y(str, kmlStyle, kmlStyle2, (MultiGeometry) geometry, (List) obj);
        }
    }

    public final void W(String str, KmlStyle kmlStyle, KmlStyle kmlStyle2, Marker marker) {
        boolean z = true;
        boolean z2 = kmlStyle2 != null && str.equals(kmlStyle2.getIconUrl());
        if (kmlStyle == null || !str.equals(kmlStyle.getIconUrl())) {
            z = false;
        }
        if (z2) {
            h0(kmlStyle2, marker);
        } else if (z) {
            h0(kmlStyle, marker);
        }
    }

    public final void X(String str, HashMap<KmlPlacemark, Object> hashMap) {
        for (KmlPlacemark kmlPlacemark : hashMap.keySet()) {
            V(str, getStylesRenderer().get(kmlPlacemark.getId()), kmlPlacemark.getInlineStyle(), kmlPlacemark.getGeometry(), hashMap.get(kmlPlacemark));
        }
    }

    public final void Y(String str, KmlStyle kmlStyle, KmlStyle kmlStyle2, MultiGeometry multiGeometry, List<Object> list) {
        Iterator<Geometry> it = multiGeometry.getGeometryObject().iterator();
        Iterator<Object> it2 = list.iterator();
        while (it.hasNext() && it2.hasNext()) {
            V(str, kmlStyle, kmlStyle2, it.next(), it2.next());
        }
    }

    public final void Z(HashMap<? extends Feature, Object> hashMap) {
        for (Feature feature : hashMap.keySet()) {
            addFeature(feature);
        }
    }

    public final void a0() {
        this.F = true;
        Iterator<String> it = this.D.iterator();
        while (it.hasNext()) {
            new a(it.next()).execute(new String[0]);
            it.remove();
        }
    }

    public void addLayerToMap() {
        setLayerVisibility(true);
        this.G = getContainerList();
        putStyles();
        assignStyleMap(getStyleMaps(), getStylesRenderer());
        U(getGroundOverlayMap(), this.G);
        P(this.G, true);
        Z(getAllFeatures());
        if (!this.F) {
            a0();
        }
        if (!this.E) {
            b0();
        }
        checkClearBitmapCache();
    }

    public final void b0() {
        this.E = true;
        Iterator<String> it = getMarkerIconUrls().iterator();
        while (it.hasNext()) {
            new b(it.next()).execute(new String[0]);
            it.remove();
        }
    }

    public final Bitmap c0(String str) throws IOException {
        return BitmapFactory.decodeStream(e0(new URL(str).openConnection()));
    }

    public final InputStream e0(URLConnection uRLConnection) throws IOException {
        InputStream inputStream;
        boolean z;
        HttpURLConnection httpURLConnection;
        int responseCode;
        int i = 0;
        do {
            if (uRLConnection instanceof HttpURLConnection) {
                ((HttpURLConnection) uRLConnection).setInstanceFollowRedirects(false);
            }
            inputStream = uRLConnection.getInputStream();
            if (!(uRLConnection instanceof HttpURLConnection) || (responseCode = (httpURLConnection = (HttpURLConnection) uRLConnection).getResponseCode()) < 300 || responseCode > 307 || responseCode == 306 || responseCode == 304) {
                z = false;
                continue;
            } else {
                URL url = httpURLConnection.getURL();
                String headerField = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
                URL url2 = headerField != null ? new URL(url, headerField) : null;
                httpURLConnection.disconnect();
                if (url2 != null && ((url2.getProtocol().equals("http") || url2.getProtocol().equals("https")) && i < 5)) {
                    uRLConnection = url2.openConnection();
                    i++;
                    z = true;
                    continue;
                } else {
                    throw new SecurityException("illegal URL redirect");
                }
            }
        } while (z);
        return inputStream;
    }

    public final void f0(Iterable<KmlContainer> iterable) {
        for (KmlContainer kmlContainer : iterable) {
            g0(kmlContainer.b());
            removeGroundOverlays(kmlContainer.a());
            f0(kmlContainer.getContainers());
        }
    }

    public final void g0(HashMap<? extends Feature, Object> hashMap) {
        removeFeatures(hashMap);
    }

    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        return getGroundOverlayMap().keySet();
    }

    public Iterable<KmlContainer> getNestedContainers() {
        return this.G;
    }

    public final void h0(KmlStyle kmlStyle, Marker marker) {
        marker.setIcon(getCachedMarkerImage(kmlStyle.getIconUrl(), kmlStyle.getIconScale()));
    }

    public boolean hasNestedContainers() {
        return this.G.size() > 0;
    }

    public void i0(HashMap<String, KmlStyle> hashMap, HashMap<String, String> hashMap2, HashMap<KmlPlacemark, Object> hashMap3, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> hashMap4) {
        storeData(hashMap, hashMap2, hashMap3, arrayList, hashMap4);
    }

    public void j0(HashMap<String, KmlStyle> hashMap, HashMap<String, String> hashMap2, HashMap<KmlPlacemark, Object> hashMap3, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> hashMap4, HashMap<String, Bitmap> hashMap5) {
        storeData(hashMap, hashMap2, hashMap3, arrayList, hashMap4);
        for (Map.Entry<String, Bitmap> entry : hashMap5.entrySet()) {
            cacheBitmap(entry.getKey(), entry.getValue());
        }
    }

    public void removeLayerFromMap() {
        g0(getAllFeatures());
        removeGroundOverlays(getGroundOverlayMap());
        if (hasNestedContainers()) {
            f0(getNestedContainers());
        }
        setLayerVisibility(false);
        clearStylesRenderer();
    }

    @Override // com.google.maps.android.data.Renderer
    public void setMap(GoogleMap googleMap) {
        removeLayerFromMap();
        super.setMap(googleMap);
        addLayerToMap();
    }
}
