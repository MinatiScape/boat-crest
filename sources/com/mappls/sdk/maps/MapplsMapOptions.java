package com.mappls.sdk.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.ResourcesCompat;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.constants.MapplsConstants;
import com.mappls.sdk.maps.utils.BitmapUtils;
import com.mappls.sdk.maps.utils.FontUtils;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class MapplsMapOptions implements Parcelable {
    public static final Parcelable.Creator<MapplsMapOptions> CREATOR = new a();
    public boolean A;
    public int B;
    public int[] C;
    public double D;
    public double E;
    public double F;
    public double G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public int P;
    public boolean Q;
    public boolean R;
    public String S;
    public String[] T;
    public String U;
    public boolean V;
    public boolean W;
    @ColorInt
    public int X;
    public float Y;
    public boolean Z;
    public CameraPosition h;
    public boolean i;
    public boolean j;
    public boolean k;
    public int l;
    public int[] m;
    public Drawable n;
    public boolean o;
    public int p;
    public int[] q;
    public int r;
    public int s;
    public int[] t;
    public boolean u;
    public int v;
    public int[] w;
    public boolean x;
    public int y;
    public int[] z;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<MapplsMapOptions> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public MapplsMapOptions createFromParcel(@NonNull Parcel parcel) {
            return new MapplsMapOptions(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public MapplsMapOptions[] newArray(int i) {
            return new MapplsMapOptions[i];
        }
    }

    public /* synthetic */ MapplsMapOptions(Parcel parcel, a aVar) {
        this(parcel);
    }

    @VisibleForTesting
    public static MapplsMapOptions a(@NonNull MapplsMapOptions mapplsMapOptions, @NonNull Context context, @Nullable TypedArray typedArray) {
        float f = context.getResources().getDisplayMetrics().density;
        try {
            mapplsMapOptions.camera(new CameraPosition.Builder(typedArray).build());
            mapplsMapOptions.apiBaseUrl(typedArray.getString(R.styleable.mappls_maps_MapView_mappls_maps_apiBaseUrl));
            String string = typedArray.getString(R.styleable.mappls_maps_MapView_mappls_maps_apiBaseUri);
            if (!TextUtils.isEmpty(string)) {
                mapplsMapOptions.apiBaseUri(string);
            }
            mapplsMapOptions.zoomGesturesEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiZoomGestures, true));
            mapplsMapOptions.scrollGesturesEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiScrollGestures, true));
            mapplsMapOptions.horizontalScrollGesturesEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiHorizontalScrollGestures, true));
            mapplsMapOptions.rotateGesturesEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiRotateGestures, true));
            mapplsMapOptions.tiltGesturesEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiTiltGestures, true));
            mapplsMapOptions.doubleTapGesturesEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiDoubleTapGestures, true));
            mapplsMapOptions.quickZoomGesturesEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiQuickZoomGestures, true));
            mapplsMapOptions.maxZoomPreference(typedArray.getFloat(R.styleable.mappls_maps_MapView_mappls_maps_cameraZoomMax, 22.0f));
            mapplsMapOptions.minZoomPreference(typedArray.getFloat(R.styleable.mappls_maps_MapView_mappls_maps_cameraZoomMin, 1.0f));
            mapplsMapOptions.maxPitchPreference(typedArray.getFloat(R.styleable.mappls_maps_MapView_mappls_maps_cameraPitchMax, 60.0f));
            mapplsMapOptions.minPitchPreference(typedArray.getFloat(R.styleable.mappls_maps_MapView_mappls_maps_cameraPitchMin, 0.0f));
            mapplsMapOptions.compassEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiCompass, true));
            mapplsMapOptions.compassGravity(typedArray.getInt(R.styleable.mappls_maps_MapView_mappls_maps_uiCompassGravity, 8388661));
            float f2 = 4.0f * f;
            mapplsMapOptions.compassMargins(new int[]{(int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiCompassMarginLeft, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiCompassMarginTop, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiCompassMarginRight, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiCompassMarginBottom, f2)});
            mapplsMapOptions.compassFadesWhenFacingNorth(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiCompassFadeFacingNorth, true));
            Drawable drawable = typedArray.getDrawable(R.styleable.mappls_maps_MapView_mappls_maps_uiCompassDrawable);
            if (drawable == null) {
                drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.mappls_maps_compass_icon, null);
            }
            mapplsMapOptions.compassImage(drawable);
            mapplsMapOptions.logoEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiLogo, true));
            mapplsMapOptions.logoGravity(typedArray.getInt(R.styleable.mappls_maps_MapView_mappls_maps_uiLogoGravity, 8388691));
            mapplsMapOptions.logoMargins(new int[]{(int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiLogoMarginLeft, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiLogoMarginTop, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiLogoMarginRight, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiLogoMarginBottom, f2)});
            mapplsMapOptions.logoSize(typedArray.getInteger(R.styleable.mappls_maps_MapView_mappls_maps_uiLogoSize, 0));
            mapplsMapOptions.eventGravity(typedArray.getInt(R.styleable.mappls_maps_MapView_mappls_maps_uiEventGravity, 8388659));
            mapplsMapOptions.eventMargins(new int[]{(int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiEventMarginLeft, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiEventMarginTop, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiEventMarginRight, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiEventMarginBottom, f2)});
            mapplsMapOptions.layerControlEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiLayerControl, true));
            mapplsMapOptions.layerControlGravity(typedArray.getInt(R.styleable.mappls_maps_MapView_mappls_maps_uiLayerControlGravity, 8388691));
            mapplsMapOptions.layerControlMargins(new int[]{(int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiLayerControlMarginLeft, 92.0f * f), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiLayerControlMarginTop, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiLayerControlMarginRight, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiLayerControlMarginBottom, f2)});
            mapplsMapOptions.attributionEnabled(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_uiAttribution, true));
            mapplsMapOptions.attributionGravity(typedArray.getInt(R.styleable.mappls_maps_MapView_mappls_maps_uiAttributionGravity, 8388691));
            mapplsMapOptions.attributionMargins(new int[]{(int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiAttributionMarginLeft, 112.0f * f), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiAttributionMarginTop, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiAttributionMarginRight, f2), (int) typedArray.getDimension(R.styleable.mappls_maps_MapView_mappls_maps_uiAttributionMarginBottom, f * 8.0f)});
            mapplsMapOptions.safetyStripGravity(typedArray.getInt(R.styleable.mappls_maps_MapView_mappls_maps_uiSafetyStripGravity, 48));
            mapplsMapOptions.textureMode(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_renderTextureMode, false));
            mapplsMapOptions.translucentTextureSurface(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_renderTextureTranslucentSurface, false));
            mapplsMapOptions.setPrefetchesTiles(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_enableTilePrefetch, true));
            mapplsMapOptions.setPrefetchZoomDelta(typedArray.getInt(R.styleable.mappls_maps_MapView_mappls_maps_prefetchZoomDelta, 4));
            mapplsMapOptions.renderSurfaceOnTop(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_enableZMediaOverlay, false));
            mapplsMapOptions.R = typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_localIdeographEnabled, true);
            int resourceId = typedArray.getResourceId(R.styleable.mappls_maps_MapView_mappls_maps_localIdeographFontFamilies, 0);
            if (resourceId != 0) {
                mapplsMapOptions.localIdeographFontFamily(context.getResources().getStringArray(resourceId));
            } else {
                String string2 = typedArray.getString(R.styleable.mappls_maps_MapView_mappls_maps_localIdeographFontFamily);
                if (string2 == null) {
                    string2 = MapplsConstants.DEFAULT_FONT;
                }
                mapplsMapOptions.localIdeographFontFamily(string2);
            }
            mapplsMapOptions.pixelRatio(typedArray.getFloat(R.styleable.mappls_maps_MapView_mappls_maps_pixelRatio, 0.0f));
            mapplsMapOptions.foregroundLoadColor(typedArray.getInt(R.styleable.mappls_maps_MapView_mappls_maps_foregroundLoadColor, -988703));
            mapplsMapOptions.crossSourceCollisions(typedArray.getBoolean(R.styleable.mappls_maps_MapView_mappls_maps_cross_source_collisions, true));
            return mapplsMapOptions;
        } finally {
            typedArray.recycle();
        }
    }

    @NonNull
    public static MapplsMapOptions createFromAttributes(@NonNull Context context) {
        return createFromAttributes(context, null);
    }

    @NonNull
    public MapplsMapOptions apiBaseUri(String str) {
        this.U = str;
        return this;
    }

    @NonNull
    @Deprecated
    public MapplsMapOptions apiBaseUrl(String str) {
        this.U = str;
        return this;
    }

    @NonNull
    public MapplsMapOptions attributionEnabled(boolean z) {
        this.x = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions attributionGravity(int i) {
        this.y = i;
        return this;
    }

    @NonNull
    public MapplsMapOptions attributionMargins(int[] iArr) {
        this.z = iArr;
        return this;
    }

    @NonNull
    public MapplsMapOptions camera(CameraPosition cameraPosition) {
        this.h = cameraPosition;
        return this;
    }

    @NonNull
    public MapplsMapOptions compassEnabled(boolean z) {
        this.j = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions compassFadesWhenFacingNorth(boolean z) {
        this.k = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions compassGravity(int i) {
        this.l = i;
        return this;
    }

    @NonNull
    public MapplsMapOptions compassImage(Drawable drawable) {
        this.n = drawable;
        return this;
    }

    @NonNull
    public MapplsMapOptions compassMargins(int[] iArr) {
        this.m = iArr;
        return this;
    }

    @NonNull
    public MapplsMapOptions crossSourceCollisions(boolean z) {
        this.Z = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions debugActive(boolean z) {
        this.i = z;
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @NonNull
    public MapplsMapOptions doubleTapGesturesEnabled(boolean z) {
        this.M = z;
        return this;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            MapplsMapOptions mapplsMapOptions = (MapplsMapOptions) obj;
            if (this.i != mapplsMapOptions.i || this.j != mapplsMapOptions.j || this.k != mapplsMapOptions.k) {
                return false;
            }
            Drawable drawable = this.n;
            if (drawable == null ? mapplsMapOptions.n != null : !drawable.equals(mapplsMapOptions.n)) {
                return false;
            }
            if (this.l != mapplsMapOptions.l || this.o != mapplsMapOptions.o || this.p != mapplsMapOptions.p || this.r != mapplsMapOptions.r || this.u != mapplsMapOptions.u || this.v != mapplsMapOptions.v || this.x != mapplsMapOptions.x || this.y != mapplsMapOptions.y || this.A != mapplsMapOptions.A || this.B != mapplsMapOptions.B || Double.compare(mapplsMapOptions.D, this.D) != 0 || Double.compare(mapplsMapOptions.E, this.E) != 0 || Double.compare(mapplsMapOptions.F, this.F) != 0 || Double.compare(mapplsMapOptions.G, this.G) != 0 || this.H != mapplsMapOptions.H || this.I != mapplsMapOptions.I || this.J != mapplsMapOptions.J || this.K != mapplsMapOptions.K || this.L != mapplsMapOptions.L || this.M != mapplsMapOptions.M || this.N != mapplsMapOptions.N) {
                return false;
            }
            CameraPosition cameraPosition = this.h;
            if (cameraPosition == null ? mapplsMapOptions.h != null : !cameraPosition.equals(mapplsMapOptions.h)) {
                return false;
            }
            if (!Arrays.equals(this.m, mapplsMapOptions.m) || !Arrays.equals(this.q, mapplsMapOptions.q) || !Arrays.equals(this.w, mapplsMapOptions.w) || !Arrays.equals(this.z, mapplsMapOptions.z)) {
                return false;
            }
            String str = this.U;
            if (str == null ? mapplsMapOptions.U != null : !str.equals(mapplsMapOptions.U)) {
                return false;
            }
            if (this.O != mapplsMapOptions.O || this.P != mapplsMapOptions.P || this.Q != mapplsMapOptions.Q || this.R != mapplsMapOptions.R || !this.S.equals(mapplsMapOptions.S)) {
                return false;
            }
            Arrays.equals(this.T, mapplsMapOptions.T);
        }
        return false;
    }

    @NonNull
    public MapplsMapOptions eventGravity(int i) {
        this.s = i;
        return this;
    }

    @NonNull
    public MapplsMapOptions eventMargins(int[] iArr) {
        this.t = iArr;
        return this;
    }

    @NonNull
    public MapplsMapOptions foregroundLoadColor(@ColorInt int i) {
        this.X = i;
        return this;
    }

    public String getApiBaseUri() {
        return this.U;
    }

    @Deprecated
    public String getApiBaseUrl() {
        return this.U;
    }

    public boolean getAttributionEnabled() {
        return this.x;
    }

    public int getAttributionGravity() {
        return this.y;
    }

    public int[] getAttributionMargins() {
        return this.z;
    }

    public CameraPosition getCamera() {
        return this.h;
    }

    public boolean getCompassEnabled() {
        return this.j;
    }

    public boolean getCompassFadeFacingNorth() {
        return this.k;
    }

    public int getCompassGravity() {
        return this.l;
    }

    public Drawable getCompassImage() {
        return this.n;
    }

    public int[] getCompassMargins() {
        return this.m;
    }

    public boolean getCrossSourceCollisions() {
        return this.Z;
    }

    public boolean getDebugActive() {
        return this.i;
    }

    public boolean getDoubleTapGesturesEnabled() {
        return this.M;
    }

    public int getEventGravity() {
        return this.s;
    }

    public int[] getEventMargins() {
        return this.t;
    }

    @ColorInt
    public int getForegroundLoadColor() {
        return this.X;
    }

    public boolean getHorizontalScrollGesturesEnabled() {
        return this.J;
    }

    public boolean getLayerControlEnabled() {
        return this.u;
    }

    public int getLayerControlGravity() {
        return this.v;
    }

    public int[] getLayerControlMargins() {
        return this.w;
    }

    @Nullable
    public String getLocalIdeographFontFamily() {
        if (this.R) {
            return this.S;
        }
        return null;
    }

    public boolean getLogoEnabled() {
        return this.o;
    }

    public int getLogoGravity() {
        return this.p;
    }

    public int[] getLogoMargins() {
        return this.q;
    }

    public int getLogoSize() {
        return this.r;
    }

    public double getMaxPitchPreference() {
        return this.G;
    }

    public double getMaxZoomPreference() {
        return this.E;
    }

    public double getMinPitchPreference() {
        return this.F;
    }

    public double getMinZoomPreference() {
        return this.D;
    }

    public float getPixelRatio() {
        return this.Y;
    }

    @IntRange(from = 0)
    public int getPrefetchZoomDelta() {
        return this.P;
    }

    @Deprecated
    public boolean getPrefetchesTiles() {
        return this.O;
    }

    public boolean getQuickZoomGesturesEnabled() {
        return this.N;
    }

    public boolean getRenderSurfaceOnTop() {
        return this.Q;
    }

    public boolean getRotateGesturesEnabled() {
        return this.H;
    }

    public boolean getSafetyStripEnabled() {
        return this.A;
    }

    public int getSafetyStripGravity() {
        return this.B;
    }

    public int[] getSafetyStripMargins() {
        return this.C;
    }

    public boolean getScrollGesturesEnabled() {
        return this.I;
    }

    public boolean getTextureMode() {
        return this.V;
    }

    public boolean getTiltGesturesEnabled() {
        return this.K;
    }

    public boolean getTranslucentTextureSurface() {
        return this.W;
    }

    public boolean getZoomGesturesEnabled() {
        return this.L;
    }

    public int hashCode() {
        CameraPosition cameraPosition = this.h;
        int hashCode = (((((((((cameraPosition != null ? cameraPosition.hashCode() : 0) * 31) + (this.i ? 1 : 0)) * 31) + (this.j ? 1 : 0)) * 31) + (this.k ? 1 : 0)) * 31) + this.l) * 31;
        Drawable drawable = this.n;
        int hashCode2 = ((((((((((((((((((((((((((((hashCode + (drawable != null ? drawable.hashCode() : 0)) * 31) + Arrays.hashCode(this.m)) * 31) + (this.o ? 1 : 0)) * 31) + this.p) * 31) + Arrays.hashCode(this.q)) * 31) + this.r) * 31) + (this.u ? 1 : 0)) * 31) + this.v) * 31) + Arrays.hashCode(this.w)) * 31) + (this.x ? 1 : 0)) * 31) + this.y) * 31) + Arrays.hashCode(this.z)) * 31) + (this.A ? 1 : 0)) * 31) + this.B) * 31) + Arrays.hashCode(this.C);
        long doubleToLongBits = Double.doubleToLongBits(this.D);
        int i = (hashCode2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        long doubleToLongBits2 = Double.doubleToLongBits(this.E);
        int i2 = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        long doubleToLongBits3 = Double.doubleToLongBits(this.F);
        int i3 = (i2 * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)));
        long doubleToLongBits4 = Double.doubleToLongBits(this.G);
        int i4 = ((((((((((((((((i3 * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31) + (this.H ? 1 : 0)) * 31) + (this.I ? 1 : 0)) * 31) + (this.J ? 1 : 0)) * 31) + (this.K ? 1 : 0)) * 31) + (this.L ? 1 : 0)) * 31) + (this.M ? 1 : 0)) * 31) + (this.N ? 1 : 0)) * 31;
        String str = this.U;
        int hashCode3 = (((((((((((((i4 + (str != null ? str.hashCode() : 0)) * 31) + (this.V ? 1 : 0)) * 31) + (this.W ? 1 : 0)) * 31) + (this.O ? 1 : 0)) * 31) + this.P) * 31) + (this.Q ? 1 : 0)) * 31) + (this.R ? 1 : 0)) * 31;
        String str2 = this.S;
        return ((((((hashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31) + Arrays.hashCode(this.T)) * 31) + ((int) this.Y)) * 31) + (this.Z ? 1 : 0);
    }

    @NonNull
    public MapplsMapOptions horizontalScrollGesturesEnabled(boolean z) {
        this.J = z;
        return this;
    }

    public boolean isLocalIdeographFontFamilyEnabled() {
        return this.R;
    }

    @NonNull
    public MapplsMapOptions layerControlEnabled(boolean z) {
        this.u = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions layerControlGravity(int i) {
        this.v = i;
        return this;
    }

    @NonNull
    public MapplsMapOptions layerControlMargins(int[] iArr) {
        this.w = iArr;
        return this;
    }

    @NonNull
    public MapplsMapOptions localIdeographFontFamily(String str) {
        this.S = FontUtils.extractValidFont(str);
        return this;
    }

    @NonNull
    public MapplsMapOptions localIdeographFontFamilyEnabled(boolean z) {
        this.R = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions logoEnabled(boolean z) {
        this.o = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions logoGravity(int i) {
        this.p = i;
        return this;
    }

    @NonNull
    public MapplsMapOptions logoMargins(int[] iArr) {
        this.q = iArr;
        return this;
    }

    @NonNull
    public MapplsMapOptions logoSize(@IntRange(from = 0, to = 1) int i) {
        this.r = i;
        return this;
    }

    @NonNull
    public MapplsMapOptions maxPitchPreference(double d) {
        this.G = d;
        return this;
    }

    @NonNull
    public MapplsMapOptions maxZoomPreference(double d) {
        this.E = d;
        return this;
    }

    @NonNull
    public MapplsMapOptions minPitchPreference(double d) {
        this.F = d;
        return this;
    }

    @NonNull
    public MapplsMapOptions minZoomPreference(double d) {
        this.D = d;
        return this;
    }

    @NonNull
    public MapplsMapOptions pixelRatio(float f) {
        this.Y = f;
        return this;
    }

    @NonNull
    public MapplsMapOptions quickZoomGesturesEnabled(boolean z) {
        this.N = z;
        return this;
    }

    public void renderSurfaceOnTop(boolean z) {
        this.Q = z;
    }

    @NonNull
    public MapplsMapOptions rotateGesturesEnabled(boolean z) {
        this.H = z;
        return this;
    }

    public MapplsMapOptions safetyStripGravity(int i) {
        this.B = i;
        return this;
    }

    public MapplsMapOptions safetyStripMargins(int[] iArr) {
        this.C = iArr;
        return this;
    }

    @NonNull
    public MapplsMapOptions scrollGesturesEnabled(boolean z) {
        this.I = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions setPrefetchZoomDelta(@IntRange(from = 0) int i) {
        this.P = i;
        return this;
    }

    @NonNull
    @Deprecated
    public MapplsMapOptions setPrefetchesTiles(boolean z) {
        this.O = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions textureMode(boolean z) {
        this.V = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions tiltGesturesEnabled(boolean z) {
        this.K = z;
        return this;
    }

    @NonNull
    public MapplsMapOptions translucentTextureSurface(boolean z) {
        this.W = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(this.h, i);
        parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.j ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.l);
        parcel.writeIntArray(this.m);
        parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
        Drawable drawable = this.n;
        parcel.writeParcelable(drawable != null ? BitmapUtils.getBitmapFromDrawable(drawable) : null, i);
        parcel.writeByte(this.o ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.p);
        parcel.writeIntArray(this.q);
        parcel.writeInt(this.r);
        parcel.writeInt(this.s);
        parcel.writeIntArray(this.t);
        parcel.writeByte(this.u ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.v);
        parcel.writeIntArray(this.w);
        parcel.writeByte(this.x ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.y);
        parcel.writeIntArray(this.z);
        parcel.writeByte(this.A ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.B);
        parcel.writeIntArray(this.C);
        parcel.writeDouble(this.D);
        parcel.writeDouble(this.E);
        parcel.writeDouble(this.F);
        parcel.writeDouble(this.G);
        parcel.writeByte(this.H ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.I ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.J ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.K ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.L ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.M ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.N ? (byte) 1 : (byte) 0);
        parcel.writeString(this.U);
        parcel.writeByte(this.V ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.W ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.O ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.P);
        parcel.writeByte(this.Q ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.R ? (byte) 1 : (byte) 0);
        parcel.writeString(this.S);
        parcel.writeStringArray(this.T);
        parcel.writeFloat(this.Y);
        parcel.writeInt(this.X);
        parcel.writeByte(this.Z ? (byte) 1 : (byte) 0);
    }

    @NonNull
    public MapplsMapOptions zoomGesturesEnabled(boolean z) {
        this.L = z;
        return this;
    }

    @Deprecated
    public MapplsMapOptions() {
        this.j = true;
        this.k = true;
        this.l = 8388661;
        this.o = true;
        this.p = 8388691;
        this.r = 0;
        this.s = 8388659;
        this.u = true;
        this.v = 8388691;
        this.x = true;
        this.y = 8388691;
        this.A = true;
        this.B = 48;
        this.D = 1.0d;
        this.E = 22.0d;
        this.F = 0.0d;
        this.G = 60.0d;
        this.H = true;
        this.I = true;
        this.J = true;
        this.K = true;
        this.L = true;
        this.M = true;
        this.N = true;
        this.O = true;
        this.P = 4;
        this.Q = false;
        this.R = true;
        this.Z = true;
    }

    @NonNull
    public static MapplsMapOptions createFromAttributes(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        return a(new MapplsMapOptions(), context, context.obtainStyledAttributes(attributeSet, R.styleable.mappls_maps_MapView, 0, 0));
    }

    @NonNull
    public MapplsMapOptions localIdeographFontFamily(String... strArr) {
        this.S = FontUtils.extractValidFont(strArr);
        return this;
    }

    public MapplsMapOptions(Parcel parcel) {
        this.j = true;
        this.k = true;
        this.l = 8388661;
        this.o = true;
        this.p = 8388691;
        this.r = 0;
        this.s = 8388659;
        this.u = true;
        this.v = 8388691;
        this.x = true;
        this.y = 8388691;
        this.A = true;
        this.B = 48;
        this.D = 1.0d;
        this.E = 22.0d;
        this.F = 0.0d;
        this.G = 60.0d;
        this.H = true;
        this.I = true;
        this.J = true;
        this.K = true;
        this.L = true;
        this.M = true;
        this.N = true;
        this.O = true;
        this.P = 4;
        this.Q = false;
        this.R = true;
        this.Z = true;
        this.h = (CameraPosition) parcel.readParcelable(CameraPosition.class.getClassLoader());
        this.i = parcel.readByte() != 0;
        this.j = parcel.readByte() != 0;
        this.l = parcel.readInt();
        this.m = parcel.createIntArray();
        this.k = parcel.readByte() != 0;
        Bitmap bitmap = (Bitmap) parcel.readParcelable(getClass().getClassLoader());
        if (bitmap != null) {
            this.n = new BitmapDrawable(bitmap);
        }
        this.o = parcel.readByte() != 0;
        this.p = parcel.readInt();
        this.q = parcel.createIntArray();
        this.r = parcel.readInt();
        this.s = parcel.readInt();
        this.t = parcel.createIntArray();
        this.u = parcel.readByte() != 0;
        this.v = parcel.readInt();
        this.w = parcel.createIntArray();
        this.x = parcel.readByte() != 0;
        this.y = parcel.readInt();
        this.z = parcel.createIntArray();
        this.A = parcel.readByte() != 0;
        this.B = parcel.readInt();
        this.C = parcel.createIntArray();
        this.D = parcel.readDouble();
        this.E = parcel.readDouble();
        this.F = parcel.readDouble();
        this.G = parcel.readDouble();
        this.H = parcel.readByte() != 0;
        this.I = parcel.readByte() != 0;
        this.J = parcel.readByte() != 0;
        this.K = parcel.readByte() != 0;
        this.L = parcel.readByte() != 0;
        this.M = parcel.readByte() != 0;
        this.N = parcel.readByte() != 0;
        this.U = parcel.readString();
        this.V = parcel.readByte() != 0;
        this.W = parcel.readByte() != 0;
        this.O = parcel.readByte() != 0;
        this.P = parcel.readInt();
        this.Q = parcel.readByte() != 0;
        this.R = parcel.readByte() != 0;
        this.S = parcel.readString();
        this.T = parcel.createStringArray();
        this.Y = parcel.readFloat();
        this.X = parcel.readInt();
        this.Z = parcel.readByte() != 0;
    }
}
