package com.mappls.sdk.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.data.LocationPoint;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes11.dex */
public final class x {
    public t c;
    public com.mappls.sdk.navigation.routing.d d;
    public NavigationApplication f;

    /* renamed from: a  reason: collision with root package name */
    public ArrayList f13050a = new ArrayList();
    public a b = null;
    public ArrayList e = new ArrayList();

    /* loaded from: classes11.dex */
    public static class a implements LocationPoint {

        /* renamed from: a  reason: collision with root package name */
        public final LatLng f13051a;
        public final String b;
        public int c;
        public boolean d;
        public boolean e;
        public com.mappls.sdk.navigation.data.a f;

        public a(LatLng latLng, com.mappls.sdk.navigation.data.a aVar) {
            this.f13051a = latLng;
            this.f = aVar;
            this.b = null;
        }

        public a(String str, com.mappls.sdk.navigation.data.a aVar) {
            this.f = aVar;
            if (str.contains(Constants.SEPARATOR_COMMA)) {
                this.f13051a = new LatLng(Double.parseDouble(str.split(Constants.SEPARATOR_COMMA)[1]), Double.parseDouble(str.split(Constants.SEPARATOR_COMMA)[0]));
                this.b = null;
                return;
            }
            this.b = str;
            this.f13051a = null;
        }

        public a(String str, com.mappls.sdk.navigation.data.a aVar, int i) {
            this.f = aVar;
            this.c = i;
            this.d = true;
            if (str.contains(Constants.SEPARATOR_COMMA)) {
                this.f13051a = new LatLng(Double.parseDouble(str.split(Constants.SEPARATOR_COMMA)[1]), Double.parseDouble(str.split(Constants.SEPARATOR_COMMA)[0]));
                this.b = null;
                return;
            }
            this.b = str;
            this.f13051a = null;
        }

        public final String a() {
            com.mappls.sdk.navigation.data.a aVar = this.f;
            return aVar == null ? "" : aVar.e();
        }

        public final double b() {
            com.mappls.sdk.navigation.data.a aVar = this.f;
            return aVar == null ? this.f13051a.getLatitude() : aVar.c();
        }

        public final double c() {
            com.mappls.sdk.navigation.data.a aVar = this.f;
            return aVar == null ? this.f13051a.getLongitude() : aVar.d();
        }

        public final String d() {
            com.mappls.sdk.navigation.data.a aVar = this.f;
            return aVar == null ? "" : aVar.f();
        }

        public final boolean equals(Object obj) {
            String str;
            LatLng latLng;
            if (this == obj) {
                return true;
            }
            if (obj != null && a.class == obj.getClass()) {
                a aVar = (a) obj;
                if (this.e != aVar.e || this.d != aVar.d || this.c != aVar.c) {
                    return false;
                }
                LatLng latLng2 = this.f13051a;
                if (latLng2 != null && (latLng = aVar.f13051a) != null) {
                    return latLng2.equals(latLng);
                }
                String str2 = this.b;
                if (str2 != null && (str = aVar.b) != null) {
                    return str2.equalsIgnoreCase(str);
                }
            }
            return false;
        }

        @Override // com.mappls.sdk.navigation.data.LocationPoint
        public final double getLatitude() {
            return this.f13051a.getLatitude();
        }

        @Override // com.mappls.sdk.navigation.data.LocationPoint
        public final double getLongitude() {
            return this.f13051a.getLongitude();
        }

        @Override // com.mappls.sdk.navigation.data.LocationPoint
        @SuppressLint({"StringFormatInvalid"})
        public final com.mappls.sdk.navigation.data.a getPointDescription(Context context) {
            if (this.d) {
                return new com.mappls.sdk.navigation.data.a(FirebaseAnalytics.Param.DESTINATION, (this.c + 1) + ". " + context.getString(R.string.mappls_intermediate_point, ""), a());
            }
            return new com.mappls.sdk.navigation.data.a(FirebaseAnalytics.Param.DESTINATION, context.getString(R.string.mappls_destination_point, ""), a());
        }

        public final int hashCode() {
            LatLng latLng = this.f13051a;
            return ((((((latLng != null ? latLng.hashCode() : this.b.hashCode()) * 31) + this.c) * 31) + (this.e ? 10 : 20)) * 31) + (this.d ? 100 : 200);
        }
    }

    public x(NavigationApplication navigationApplication) {
        new ArrayList();
        this.f = navigationApplication;
        this.c = navigationApplication.k();
        this.d = navigationApplication.h();
        g();
    }

    public final void a() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f13050a.iterator();
        while (it.hasNext()) {
            arrayList.add(((a) it.next()).f13051a);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            LatLng latLng = (LatLng) it2.next();
        }
    }

    public final void a(int i, boolean z) {
        int i2 = 0;
        if (i < 0) {
            this.c.c();
            this.b = null;
            int size = this.f13050a.size();
            if (size > 0) {
                int i3 = size - 1;
                this.c.a(i3);
                a aVar = (a) this.f13050a.remove(i3);
                this.b = aVar;
                aVar.d = false;
                this.c.a(aVar.getLatitude(), this.b.getLongitude(), this.b.f);
            }
        } else {
            this.c.a(i);
            LatLng latLng = ((a) this.f13050a.remove(i)).f13051a;
            Iterator it = this.f13050a.iterator();
            while (it.hasNext()) {
                ((a) it.next()).c = i2;
                i2++;
            }
        }
        b(z);
    }

    public final void a(LatLng latLng, int i, com.mappls.sdk.navigation.data.a aVar) {
        if (aVar.g() && com.mappls.sdk.navigation.util.a.a(aVar.e())) {
            aVar.a(com.mappls.sdk.navigation.data.a.a(this.f));
        }
        this.c.b(latLng.getLatitude(), latLng.getLongitude(), aVar, i);
        g();
        b(true);
    }

    public final void a(ArrayList arrayList) {
        this.c.b();
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                com.mappls.sdk.navigation.data.a aVar = new com.mappls.sdk.navigation.data.a(FirebaseAnalytics.Param.DESTINATION, ((com.mappls.sdk.navigation.data.a) arrayList.get(i)).f());
                aVar.b(((com.mappls.sdk.navigation.data.a) arrayList.get(i)).f());
                this.c.a(((com.mappls.sdk.navigation.data.a) arrayList.get(i)).a(), ((com.mappls.sdk.navigation.data.a) arrayList.get(i)).b(), aVar, i);
            }
            g();
            b(true);
        }
    }

    public final void a(ArrayList arrayList, int i) {
        if (arrayList.size() > 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                com.mappls.sdk.navigation.data.a aVar = new com.mappls.sdk.navigation.data.a(FirebaseAnalytics.Param.DESTINATION, ((com.mappls.sdk.navigation.data.a) arrayList.get(i2)).f());
                aVar.b(((com.mappls.sdk.navigation.data.a) arrayList.get(i2)).f());
                this.c.a(((com.mappls.sdk.navigation.data.a) arrayList.get(i2)).a(), ((com.mappls.sdk.navigation.data.a) arrayList.get(i2)).b(), aVar, i + i2);
            }
            g();
            b(true);
        }
    }

    public final void a(boolean z) {
        a();
        this.c.b();
        this.c.c();
        this.c.d();
        if (z) {
            this.c.a();
        }
        this.b = null;
        this.f13050a.clear();
        g();
        b(false);
    }

    public final void b() {
        a();
        this.c.c();
        this.c.b();
        this.f13050a.clear();
        g();
        b(false);
    }

    public final void b(boolean z) {
        if (z && (this.d.s() || this.d.t() || this.d.q() || this.d.u())) {
            LatLng m = this.c.m();
            NavLocation lastKnownLocation = this.f.getLocationProvider().getLastKnownLocation();
            this.f13050a.clear();
            ArrayList i = this.c.i();
            ArrayList b = this.c.b(i.size());
            for (int i2 = 0; i2 < i.size(); i2++) {
                this.f13050a.add(new a((String) i.get(i2), com.mappls.sdk.navigation.data.a.a((String) b.get(i2), (String) i.get(i2)), i2));
            }
            ArrayList arrayList = new ArrayList();
            if (this.c.k0.get().booleanValue()) {
                Iterator it = this.f13050a.iterator();
                while (it.hasNext()) {
                    arrayList.add(((a) it.next()).f13051a);
                }
            }
            if ((!this.d.q() || lastKnownLocation == null) && m != null) {
                NavLocation navLocation = new NavLocation("map");
                navLocation.setLatitude(m.getLatitude());
                navLocation.setLongitude(m.getLongitude());
                this.d.a(this.c.l(), arrayList, navLocation);
            } else {
                this.d.a(this.c.l(), arrayList, lastKnownLocation);
            }
        }
        Iterator it2 = this.e.iterator();
        while (it2.hasNext()) {
            ((w) it2.next()).a(null);
        }
    }

    public final ArrayList c() {
        return this.f13050a;
    }

    public final ArrayList d() {
        ArrayList arrayList = new ArrayList();
        if (this.c.k0.get().booleanValue()) {
            Iterator it = this.f13050a.iterator();
            while (it.hasNext()) {
                arrayList.add((a) it.next());
            }
        }
        return arrayList;
    }

    public final ArrayList e() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f13050a);
        a aVar = this.b;
        if (aVar != null) {
            arrayList.add(aVar);
        }
        return arrayList;
    }

    public final a f() {
        return this.b;
    }

    public final void g() {
        String l = this.c.l();
        this.b = l != null ? new a(l, this.c.k()) : null;
        LatLng m = this.c.m();
        com.mappls.sdk.navigation.data.a n = this.c.n();
        if (m != null) {
            new a(m, n).e = true;
        }
        this.f13050a.clear();
        ArrayList i = this.c.i();
        ArrayList b = this.c.b(i.size());
        for (int i2 = 0; i2 < i.size(); i2++) {
            this.f13050a.add(new a((String) i.get(i2), com.mappls.sdk.navigation.data.a.a((String) b.get(i2), (String) i.get(i2)), i2));
        }
        this.f.h.getClass();
    }

    public final void a(String str, boolean z, com.mappls.sdk.navigation.data.a aVar) {
        a aVar2;
        if (str != null) {
            if (aVar.g() && com.mappls.sdk.navigation.util.a.a(aVar.e())) {
                aVar.a(com.mappls.sdk.navigation.data.a.a(this.f));
            }
            if (-1 > this.f13050a.size() && (aVar2 = this.b) != null) {
                String str2 = aVar2.b;
                t tVar = this.c;
                if (str2 == null) {
                    tVar.a(aVar2.getLatitude(), aVar2.getLongitude(), aVar2.f, this.f13050a.size());
                } else {
                    tVar.a(str2, aVar2.f, this.f13050a.size());
                }
            }
            this.c.a(str, aVar);
        } else {
            a();
            this.c.c();
            this.c.b();
        }
        g();
        b(z);
    }

    public final void a(LatLng latLng, boolean z, int i, com.mappls.sdk.navigation.data.a aVar) {
        a aVar2;
        if (aVar.g() && com.mappls.sdk.navigation.util.a.a(aVar.e())) {
            aVar.a(com.mappls.sdk.navigation.data.a.a(this.f));
        }
        if (i < 0 || i > this.f13050a.size()) {
            if (i > this.f13050a.size() && (aVar2 = this.b) != null) {
                this.c.a(aVar2.getLatitude(), aVar2.getLongitude(), aVar2.f, this.f13050a.size());
            }
            this.c.a(latLng.getLatitude(), latLng.getLongitude(), aVar);
        } else {
            this.c.a(latLng.getLatitude(), latLng.getLongitude(), aVar, i);
        }
        g();
        b(z);
    }
}
