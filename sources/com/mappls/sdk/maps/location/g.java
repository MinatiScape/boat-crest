package com.mappls.sdk.maps.location;

import android.location.Location;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.VisibleForTesting;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.Projection;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.location.o;
import com.mappls.sdk.maps.log.Logger;
import java.util.ArrayList;
import java.util.Set;
/* loaded from: classes11.dex */
public final class g {
    public final Projection b;
    public Location c;
    public float g;
    public final p h;
    public final q i;
    public boolean j;
    public boolean k;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final SparseArray<o> f12762a = new SparseArray<>();
    public float d = -1.0f;
    public float e = -1.0f;
    public long f = -1;
    @VisibleForTesting
    public int l = Integer.MAX_VALUE;
    @VisibleForTesting
    public final SparseArray<o.b> m = new SparseArray<>();

    public g(@NonNull Projection projection, @NonNull q qVar, @NonNull p pVar) {
        this.b = projection;
        this.h = pVar;
        this.i = qVar;
    }

    public void A() {
        t tVar = (t) this.f12762a.get(0);
        s sVar = (s) this.f12762a.get(2);
        s sVar2 = (s) this.f12762a.get(3);
        s sVar3 = (s) this.f12762a.get(6);
        if (tVar != null && sVar != null) {
            j(0, (LatLng) tVar.getAnimatedValue(), tVar.b());
            h(2, ((Float) sVar.getAnimatedValue()).floatValue(), sVar.b().floatValue());
            x(tVar.getDuration() - tVar.getCurrentPlayTime(), 0, 2);
        }
        if (sVar2 != null) {
            h(3, u(), sVar2.b().floatValue());
            x(this.j ? 500L : 0L, 3);
        }
        if (sVar3 != null) {
            l(this.d, false);
        }
    }

    public final void B(@NonNull CameraPosition cameraPosition) {
        s sVar = (s) this.f12762a.get(5);
        if (sVar == null) {
            return;
        }
        float floatValue = sVar.b().floatValue();
        float f = (float) cameraPosition.bearing;
        h(5, f, Utils.shortestRotation(floatValue, f));
    }

    public final void C(@NonNull CameraPosition cameraPosition, boolean z) {
        s sVar = (s) this.f12762a.get(4);
        if (sVar == null) {
            return;
        }
        float f = f(z, sVar.b().floatValue());
        float f2 = (float) cameraPosition.bearing;
        h(4, f2, Utils.shortestRotation(f, f2));
    }

    public final boolean D(@NonNull CameraPosition cameraPosition) {
        t tVar = (t) this.f12762a.get(1);
        if (tVar == null) {
            return false;
        }
        LatLng b = tVar.b();
        LatLng latLng = cameraPosition.target;
        j(1, latLng, b);
        return Utils.d(this.b, latLng, b);
    }

    public final boolean E(@NonNull CameraPosition cameraPosition, boolean z) {
        C(cameraPosition, z);
        return D(cameraPosition);
    }

    public void F(boolean z) {
        this.k = z;
    }

    public void G(boolean z) {
        this.j = z;
    }

    public void H(int i) {
        if (i <= 0) {
            Logger.e("Mbgl-LocationAnimatorCoordinator", "Max animation FPS cannot be less or equal to 0.");
        } else {
            this.l = i;
        }
    }

    public void I(float f) {
        this.g = f;
    }

    public void J(LocationComponentOptions locationComponentOptions) {
        c(9);
        o.b bVar = this.m.get(9);
        if (bVar != null) {
            this.f12762a.put(9, this.h.e(bVar, this.l, locationComponentOptions.pulseSingleDuration(), locationComponentOptions.pulseMaxRadius(), locationComponentOptions.pulseInterpolator() == null ? new DecelerateInterpolator() : locationComponentOptions.pulseInterpolator()));
            y();
        }
    }

    public void K() {
        c(9);
    }

    public final void L(float f, float f2) {
        h(6, f2, f);
    }

    public void M(@NonNull Set<a> set) {
        o oVar;
        this.m.clear();
        for (a aVar : set) {
            this.m.append(aVar.a(), aVar.b());
        }
        for (int i = 0; i < this.f12762a.size(); i++) {
            int keyAt = this.f12762a.keyAt(i);
            if (this.m.get(keyAt) == null && (oVar = this.f12762a.get(keyAt)) != null) {
                oVar.makeInvalid();
            }
        }
    }

    public final void N(LatLng[] latLngArr, Float[] fArr) {
        k(1, latLngArr);
        i(4, fArr);
    }

    public final void O(float f, float f2, float f3) {
        h(3, f2, Utils.shortestRotation(f, f2));
        h(5, f3, Utils.shortestRotation(f, f3));
    }

    public final void P(LatLng[] latLngArr, Float[] fArr) {
        k(0, latLngArr);
        i(2, fArr);
    }

    public final void Q(float f, float f2, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        g(8, new Float[]{Float.valueOf(f2), Float.valueOf(f)}, cancelableCallback);
    }

    public final void R(float f, float f2, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        g(7, new Float[]{Float.valueOf(f2), Float.valueOf(f)}, cancelableCallback);
    }

    public void a() {
        for (int i = 0; i < this.f12762a.size(); i++) {
            c(this.f12762a.keyAt(i));
        }
    }

    public void b() {
        c(2);
        this.f12762a.remove(2);
    }

    public final void c(int i) {
        o oVar = this.f12762a.get(i);
        if (oVar != null) {
            oVar.cancel();
            oVar.removeAllUpdateListeners();
            oVar.removeAllListeners();
        }
    }

    public void d() {
        c(8);
    }

    public void e() {
        c(7);
    }

    public final float f(boolean z, float f) {
        if (z) {
            return 0.0f;
        }
        return f;
    }

    public final void g(int i, @NonNull @Size(min = 2) Float[] fArr, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        c(i);
        o.b bVar = this.m.get(i);
        if (bVar != null) {
            this.f12762a.put(i, this.h.a(fArr, bVar, cancelableCallback));
        }
    }

    public final void h(int i, float f, float f2) {
        i(i, new Float[]{Float.valueOf(f), Float.valueOf(f2)});
    }

    public final void i(int i, @NonNull @Size(min = 2) Float[] fArr) {
        c(i);
        o.b bVar = this.m.get(i);
        if (bVar != null) {
            this.f12762a.put(i, this.h.b(fArr, bVar, this.l));
        }
    }

    public final void j(int i, LatLng latLng, LatLng latLng2) {
        k(i, new LatLng[]{latLng, latLng2});
    }

    public final void k(int i, LatLng[] latLngArr) {
        c(i);
        o.b bVar = this.m.get(i);
        if (bVar != null) {
            this.f12762a.put(i, this.h.d(latLngArr, bVar, this.l));
        }
    }

    public void l(float f, boolean z) {
        if (this.d < 0.0f) {
            this.d = f;
        }
        L(f, t());
        x((z || !this.k) ? 0L : 250L, 6);
        this.d = f;
    }

    public void m(float f, @NonNull CameraPosition cameraPosition) {
        if (this.e < 0.0f) {
            this.e = f;
        }
        O(f, u(), (float) cameraPosition.bearing);
        x(this.j ? 500L : 0L, 3, 5);
        this.e = f;
    }

    public void n(@NonNull Location location, @NonNull CameraPosition cameraPosition, boolean z) {
        o(new Location[]{location}, cameraPosition, z, false);
    }

    public void o(@NonNull @Size(min = 1) Location[] locationArr, @NonNull CameraPosition cameraPosition, boolean z, boolean z2) {
        boolean z3 = true;
        Location location = locationArr[locationArr.length - 1];
        if (this.c == null) {
            this.c = location;
            this.f = SystemClock.elapsedRealtime() - 750;
        }
        LatLng w = w();
        float v = v();
        LatLng latLng = cameraPosition.target;
        float normalize = Utils.normalize((float) cameraPosition.bearing);
        LatLng[] s = s(w, locationArr);
        P(s, r(Float.valueOf(v), locationArr));
        s[0] = latLng;
        N(s, z ? new Float[]{Float.valueOf(normalize), Float.valueOf(Utils.shortestRotation(0.0f, normalize))} : r(Float.valueOf(normalize), locationArr));
        LatLng latLng2 = new LatLng(location);
        if (!Utils.d(this.b, latLng, latLng2) && !Utils.d(this.b, w, latLng2)) {
            z3 = false;
        }
        long j = 0;
        if (!z3) {
            long j2 = this.f;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.f = elapsedRealtime;
            if (j2 != 0) {
                if (z2) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis > location.getTime()) {
                        Logger.e("LocationAnimatorCoordinator", "Lookahead enabled, but the target location's timestamp is smaller than current timestamp");
                    } else {
                        j = location.getTime() - currentTimeMillis;
                    }
                } else {
                    j = ((float) (elapsedRealtime - j2)) * this.g;
                }
            }
            j = Math.min(j, (long) Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
        }
        x(j, 0, 2, 1, 4);
        this.c = location;
    }

    public void p(double d, @NonNull CameraPosition cameraPosition, long j, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        Q((float) d, (float) cameraPosition.tilt, cancelableCallback);
        x(j, 8);
    }

    public void q(double d, @NonNull CameraPosition cameraPosition, long j, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        R((float) d, (float) cameraPosition.zoom, cancelableCallback);
        x(j, 7);
    }

    public final Float[] r(Float f, Location[] locationArr) {
        int length = locationArr.length + 1;
        Float[] fArr = new Float[length];
        fArr[0] = Float.valueOf(Utils.normalize(f.floatValue()));
        for (int i = 1; i < length; i++) {
            int i2 = i - 1;
            fArr[i] = Float.valueOf(Utils.shortestRotation(locationArr[i2].getBearing(), fArr[i2].floatValue()));
        }
        return fArr;
    }

    public final LatLng[] s(LatLng latLng, Location[] locationArr) {
        int length = locationArr.length + 1;
        LatLng[] latLngArr = new LatLng[length];
        latLngArr[0] = latLng;
        for (int i = 1; i < length; i++) {
            latLngArr[i] = new LatLng(locationArr[i - 1]);
        }
        return latLngArr;
    }

    public final float t() {
        o oVar = this.f12762a.get(6);
        if (oVar != null) {
            return ((Float) oVar.getAnimatedValue()).floatValue();
        }
        return this.d;
    }

    public final float u() {
        s sVar = (s) this.f12762a.get(3);
        if (sVar != null) {
            return ((Float) sVar.getAnimatedValue()).floatValue();
        }
        return this.e;
    }

    public final float v() {
        s sVar = (s) this.f12762a.get(2);
        if (sVar != null) {
            return ((Float) sVar.getAnimatedValue()).floatValue();
        }
        return this.c.getBearing();
    }

    public final LatLng w() {
        o oVar = this.f12762a.get(0);
        if (oVar != null) {
            return (LatLng) oVar.getAnimatedValue();
        }
        return new LatLng(this.c);
    }

    public final void x(long j, int... iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            o oVar = this.f12762a.get(i);
            if (oVar != null) {
                arrayList.add(oVar);
            }
        }
        this.i.b(arrayList, new LinearInterpolator(), j);
    }

    public final void y() {
        o oVar = this.f12762a.get(9);
        if (oVar != null) {
            oVar.start();
        }
    }

    public void z(@NonNull CameraPosition cameraPosition, boolean z) {
        B(cameraPosition);
        x(E(cameraPosition, z) ? 0L : 750L, 1, 4);
    }
}
