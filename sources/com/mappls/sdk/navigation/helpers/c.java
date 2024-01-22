package com.mappls.sdk.navigation.helpers;

import com.google.gson.Gson;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.d;
import com.mappls.sdk.navigation.data.LocationPoint;
import com.mappls.sdk.navigation.events.NavEvent;
import com.mappls.sdk.navigation.iface.JunctionInfoChangedListener;
import com.mappls.sdk.navigation.iface.NavigationEventListener;
import com.mappls.sdk.navigation.iface.POIAlongTheRouteChangedListener;
import com.mappls.sdk.navigation.model.Junction;
import com.mappls.sdk.navigation.routing.NavigationRoute;
import com.mappls.sdk.navigation.routing.a;
import com.mappls.sdk.navigation.routing.h;
import com.mappls.sdk.navigation.t;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes11.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public NavigationApplication f12910a;
    public NavigationRoute e;
    public d f;
    public POIAlongTheRouteChangedListener g;
    public List<com.mappls.sdk.navigation.refresh.c> h;
    public NavigationEventListener j;
    public JunctionInfoChangedListener l;
    public List<List<a>> b = new ArrayList();
    public ConcurrentHashMap<LocationPoint, Integer> c = new ConcurrentHashMap<>();
    public ArrayList<Integer> d = new ArrayList<>();
    public LinkedList i = new LinkedList();
    public LinkedList k = new LinkedList();

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public LocationPoint f12911a;
        public float b;
        public int c;

        public a() {
        }

        public a(LocationPoint locationPoint, float f, int i) {
            this.f12911a = locationPoint;
            this.b = f;
            this.c = i;
        }

        public final float a() {
            return this.b;
        }

        public final LocationPoint b() {
            return this.f12911a;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && a.class == obj.getClass()) {
                LocationPoint locationPoint = this.f12911a;
                LocationPoint locationPoint2 = ((a) obj).f12911a;
                return locationPoint == null ? locationPoint2 == null : locationPoint.equals(locationPoint2);
            }
            return false;
        }

        public final int hashCode() {
            LocationPoint locationPoint = this.f12911a;
            if (locationPoint == null) {
                return 0;
            }
            return locationPoint.hashCode();
        }
    }

    public c(NavigationApplication navigationApplication) {
        this.f12910a = navigationApplication;
        this.f = navigationApplication.k().e();
    }

    public static List b(List list, int i) {
        while (list.size() <= i) {
            list.add(new ArrayList());
        }
        ((List) list.get(i)).clear();
        return (List) list.get(i);
    }

    public static void c(NavigationRoute navigationRoute, List list, List list2) {
        List<NavLocation> immutableAllLocations = navigationRoute.getImmutableAllLocations();
        int[] iArr = new int[1];
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            LocationPoint locationPoint = (LocationPoint) it.next();
            float f = Float.POSITIVE_INFINITY;
            for (int i = 1; i < immutableAllLocations.size(); i++) {
                int i2 = i - 1;
                double a2 = com.mappls.sdk.navigation.util.d.a(locationPoint.getLatitude(), locationPoint.getLongitude(), immutableAllLocations.get(i2).getLatitude(), immutableAllLocations.get(i2).getLongitude(), immutableAllLocations.get(i).getLatitude(), immutableAllLocations.get(i).getLongitude());
                if (a2 < f) {
                    iArr[0] = i;
                    f = (float) a2;
                }
            }
            if (f < Float.POSITIVE_INFINITY) {
                int i3 = iArr[0];
                locationPoint.getLatitude();
                locationPoint.getLongitude();
                int i4 = i3 - 1;
                immutableAllLocations.get(i4).getLatitude();
                immutableAllLocations.get(i4).getLongitude();
                immutableAllLocations.get(i3).getLatitude();
                immutableAllLocations.get(i3).getLongitude();
            }
            if (f <= 500) {
                list.add(new a(locationPoint, f, iArr[0]));
            }
        }
    }

    public final h a() {
        return this.f12910a.h().o();
    }

    public final void a(int i) {
        a(this.e, i, this.b);
        this.d = new ArrayList<>(Collections.nCopies(this.b.size(), 0));
    }

    public final void a(JunctionInfoChangedListener junctionInfoChangedListener) {
        this.k.add(new WeakReference(junctionInfoChangedListener));
    }

    public final void a(NavigationEventListener navigationEventListener) {
        this.i.add(new WeakReference(navigationEventListener));
    }

    public final void a(POIAlongTheRouteChangedListener pOIAlongTheRouteChangedListener) {
        this.g = pOIAlongTheRouteChangedListener;
    }

    public final void a(NavigationRoute navigationRoute, int i, List<List<a>> list) {
        a aVar;
        t.j jVar;
        boolean z = i == -1;
        this.f = this.f12910a.k().e();
        if (navigationRoute == null || navigationRoute.isEmpty()) {
            return;
        }
        boolean booleanValue = ((Boolean) this.f12910a.k().B.get()).booleanValue();
        ((Boolean) this.f12910a.k().V.get()).booleanValue();
        if (navigationRoute.getAppMode() != null) {
            this.f = navigationRoute.getAppMode();
        }
        boolean booleanValue2 = ((Boolean) this.f12910a.k().D.a(this.f)).booleanValue();
        ((Boolean) this.f12910a.k().C.a(this.f)).booleanValue();
        ((Boolean) this.f12910a.k().W.a(this.f)).booleanValue();
        ((Boolean) this.f12910a.k().X.a(this.f)).booleanValue();
        boolean booleanValue3 = ((Boolean) this.f12910a.k().G.a(this.f)).booleanValue();
        boolean booleanValue4 = ((Boolean) this.f12910a.k().H.a(this.f)).booleanValue();
        ((Boolean) this.f12910a.k().Y.a(this.f)).booleanValue();
        ((Boolean) this.f12910a.k().Z.a(this.f)).booleanValue();
        if (i == 4 || z) {
            List b = b(list, 4);
            if (navigationRoute.getAppMode() != null) {
                d dVar = this.f;
                for (com.mappls.sdk.navigation.routing.a aVar2 : navigationRoute.getAlarmInfo()) {
                    if (aVar2.b() == a.EnumC0643a.b) {
                        if (((Boolean) this.f12910a.k().A.a(dVar)).booleanValue() || ((Boolean) this.f12910a.k().U.a(dVar)).booleanValue()) {
                            aVar = new a(aVar2, 0.0f, aVar2.a());
                            jVar = this.f12910a.k().U;
                            ((Boolean) jVar.get()).booleanValue();
                            b.add(aVar);
                        }
                    } else if (this.f12910a.k().z.a(dVar).booleanValue() || ((Boolean) this.f12910a.k().R.a(dVar)).booleanValue()) {
                        aVar = new a(aVar2, 0.0f, aVar2.a());
                        jVar = this.f12910a.k().R;
                        ((Boolean) jVar.get()).booleanValue();
                        b.add(aVar);
                    }
                }
                Collections.sort(b, new b());
            }
        }
        if (i == 1 || z) {
            List b2 = b(list, 1);
            if (booleanValue) {
                this.f12910a.c().getClass();
                c(navigationRoute, b2, Collections.emptyList());
                c(navigationRoute, b2, navigationRoute.getLocationPoints());
                Collections.sort(b2, new b());
            }
        }
        if (i == 2 || z) {
            List b3 = b(list, 2);
            if (booleanValue2) {
                for (com.mappls.sdk.navigation.refresh.c cVar : this.h) {
                    if (navigationRoute.getCurrentRoute() < cVar.a()) {
                        b3.add(new a(cVar, 0.0f, cVar.a()));
                    }
                }
                Collections.sort(b3, new b());
            }
        }
        if (i == 5 || z) {
            List b4 = b(list, 5);
            if (booleanValue3) {
                List<Junction> junctionViews = navigationRoute.getJunctionViews();
                if (junctionViews != null && junctionViews.size() > 0) {
                    for (Junction junction : junctionViews) {
                        b4.add(new a(junction, 0.0f, junction.nodeIdx));
                    }
                }
                NavigationLogger.d("calculateJunction", new Object[0]);
                Collections.sort(b4, new b());
            }
        }
        if (i == 6 || z) {
            List b5 = b(list, 6);
            if (booleanValue4) {
                List<NavEvent> internalEvents = navigationRoute.getInternalEvents();
                if (internalEvents != null && internalEvents.size() > 0) {
                    for (NavEvent navEvent : internalEvents) {
                        b5.add(new a(navEvent, 0.0f, navEvent.getIndex() != null ? navEvent.getIndex().intValue() : 0));
                    }
                }
                NavigationLogger.d("calculateNavigationEvents", new Object[0]);
                Collections.sort(b5, new b());
            }
        }
    }

    public final void a(List<com.mappls.sdk.navigation.refresh.c> list) {
        this.h = list;
        if (this.g == null || list == null || list.size() <= 0) {
            return;
        }
        this.g.onPlaceChanged(list);
    }

    public final void b() {
        boolean z;
        boolean z2;
        this.f12910a.getClass();
        NavLocation f = this.f12910a.h().f();
        if (f == null || !this.f12910a.h().q()) {
            return;
        }
        for (int i = 0; i < this.b.size(); i++) {
            int currentRoute = this.e.getCurrentRoute();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            List<a> list = this.b.get(i);
            if (list != null && this.d.size() > i) {
                int intValue = this.d.get(i).intValue();
                while (intValue < list.size() && list.get(intValue).c < currentRoute) {
                    intValue++;
                }
                this.d.set(i, Integer.valueOf(intValue));
                while (true) {
                    if (intValue >= list.size()) {
                        break;
                    }
                    a aVar = list.get(intValue);
                    if (this.e.getDistanceToPoint(aVar.c) > 1400) {
                        break;
                    }
                    LocationPoint locationPoint = aVar.f12911a;
                    double max = Math.max(0.0d, com.mappls.sdk.navigation.util.d.a(f.getLatitude(), f.getLongitude(), locationPoint.getLatitude(), locationPoint.getLongitude()) - aVar.a());
                    Integer num = this.c.get(locationPoint);
                    if (num == null || i == 6 || i == 5 || num.intValue() != 1 || !a().a(f.getSpeed(), max, 150.0d, 0.0f)) {
                        int i2 = 4;
                        if (i != 4) {
                            if ((num == null || num.intValue() == 0) && a().a(f.getSpeed(), max, 700.0d, 0.0f)) {
                                this.c.put(locationPoint, 1);
                                arrayList.add(aVar);
                            } else {
                                i2 = 4;
                            }
                        }
                        if (i == i2) {
                            if ((num == null || num.intValue() == 0) && a().a(f.getSpeed(), max, 150.0d, 0.0f)) {
                                this.c.put(locationPoint, 1);
                                arrayList.add(aVar);
                            }
                        } else if (i == 5) {
                            if ((num == null || num.intValue() == 0) && a().a(f.getSpeed(), max, 150.0d, 0.0f)) {
                                this.c.put(locationPoint, 1);
                                arrayList.add(aVar);
                            }
                            if (max < ((Integer) this.f12910a.k().g.get()).intValue()) {
                                Junction junction = (Junction) locationPoint;
                                junction.setLeftDistance(max);
                                JunctionInfoChangedListener junctionInfoChangedListener = this.l;
                                if (junctionInfoChangedListener != null) {
                                    junctionInfoChangedListener.junctionInfoChanged(junction);
                                }
                                LinkedList linkedList = this.k;
                                if (linkedList != null) {
                                    Iterator it = linkedList.iterator();
                                    while (it.hasNext()) {
                                        JunctionInfoChangedListener junctionInfoChangedListener2 = (JunctionInfoChangedListener) ((WeakReference) it.next()).get();
                                        if (junctionInfoChangedListener2 == null) {
                                            it.remove();
                                        } else {
                                            junctionInfoChangedListener2.junctionInfoChanged(junction);
                                        }
                                    }
                                }
                                z = false;
                                z2 = true;
                            }
                        } else if (i == 6) {
                            NavEvent navEvent = (NavEvent) aVar.f12911a;
                            navEvent.setDistanceLeft(max);
                            if ((num == null || num.intValue() == 0 || num.intValue() == 1) && a().a(f.getSpeed(), max, ((Integer) this.f12910a.k().e.get()).intValue(), 0.0f)) {
                                this.c.put(locationPoint, 2);
                                arrayList2.add(aVar);
                            }
                            if (max < ((Integer) this.f12910a.k().d.get()).intValue()) {
                                if (navEvent.isVisible()) {
                                    NavigationEventListener navigationEventListener = this.j;
                                    if (navigationEventListener != null) {
                                        navigationEventListener.onNavigationEvent(navEvent);
                                    }
                                    LinkedList linkedList2 = this.i;
                                    if (linkedList2 != null) {
                                        Iterator it2 = linkedList2.iterator();
                                        while (it2.hasNext()) {
                                            NavigationEventListener navigationEventListener2 = (NavigationEventListener) ((WeakReference) it2.next()).get();
                                            if (navigationEventListener2 == null) {
                                                it2.remove();
                                            } else {
                                                navigationEventListener2.onNavigationEvent(navEvent);
                                            }
                                        }
                                    }
                                }
                                z = true;
                            }
                        }
                    } else {
                        this.c.put(locationPoint, 2);
                        arrayList2.add(aVar);
                    }
                    intValue++;
                }
                z = false;
                z2 = false;
                if (!z && i == 6) {
                    NavigationEventListener navigationEventListener3 = this.j;
                    if (navigationEventListener3 != null) {
                        navigationEventListener3.onNavigationEvent(null);
                    }
                    LinkedList linkedList3 = this.i;
                    if (linkedList3 != null) {
                        Iterator it3 = linkedList3.iterator();
                        while (it3.hasNext()) {
                            NavigationEventListener navigationEventListener4 = (NavigationEventListener) ((WeakReference) it3.next()).get();
                            if (navigationEventListener4 == null) {
                                it3.remove();
                            } else {
                                navigationEventListener4.onNavigationEvent(null);
                            }
                        }
                    }
                }
                if (!z2 && i == 5) {
                    JunctionInfoChangedListener junctionInfoChangedListener3 = this.l;
                    if (junctionInfoChangedListener3 != null) {
                        junctionInfoChangedListener3.junctionInfoChanged(null);
                    }
                    LinkedList linkedList4 = this.k;
                    if (linkedList4 != null) {
                        Iterator it4 = linkedList4.iterator();
                        while (it4.hasNext()) {
                            JunctionInfoChangedListener junctionInfoChangedListener4 = (JunctionInfoChangedListener) ((WeakReference) it4.next()).get();
                            if (junctionInfoChangedListener4 == null) {
                                it4.remove();
                            } else {
                                junctionInfoChangedListener4.junctionInfoChanged(null);
                            }
                        }
                    }
                }
                if (i == 2) {
                    ArrayList arrayList3 = new ArrayList();
                    List<a> list2 = this.b.get(2);
                    NavigationLogger.d("total pois = %d", Integer.valueOf(list2.size()));
                    for (a aVar2 : list2) {
                        int i3 = aVar2.c;
                        if (currentRoute < i3) {
                            this.e.getDistanceToPoint(i3);
                            ((com.mappls.sdk.navigation.refresh.c) aVar2.f12911a).getClass();
                            arrayList3.add((com.mappls.sdk.navigation.refresh.c) aVar2.f12911a);
                            NavigationLogger.d("POIs = %s", new Gson().toJson(aVar2.f12911a));
                        }
                    }
                    POIAlongTheRouteChangedListener pOIAlongTheRouteChangedListener = this.g;
                    if (pOIAlongTheRouteChangedListener != null) {
                        pOIAlongTheRouteChangedListener.onPlaceChanged(arrayList3);
                    }
                }
                if (!arrayList2.isEmpty()) {
                    int size = arrayList2.size();
                    ArrayList arrayList4 = arrayList2;
                    if (size > 3) {
                        arrayList4 = arrayList2.subList(0, 3);
                    }
                    if (i == 1) {
                        a().c(arrayList4);
                    } else if (i == 2) {
                        a().b(arrayList4);
                    } else if (i != 4) {
                        if (i == 3) {
                            a().a(arrayList4);
                        } else if (i == 6 && ((Boolean) this.f12910a.k().I.get()).booleanValue() && !((Boolean) this.f12910a.k().l0.get()).booleanValue()) {
                            NavEvent navEvent2 = (NavEvent) arrayList4.get(0).f12911a;
                            if (navEvent2.isShouldSpeak()) {
                                a().a(String.format(Locale.getDefault(), "%s in %s ahead", navEvent2.getName(), NavigationFormatter.getFormattedDistanceNavigation((float) NavigationFormatter.calculateRoundedDist(navEvent2.getDistanceLeft(), this.f12910a), this.f12910a)));
                            }
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    ArrayList subList = arrayList.size() > 1 ? arrayList.subList(0, 1) : arrayList;
                    if (i == 1) {
                        a().c(f, subList);
                    } else if (i == 2) {
                        a().b(f, subList);
                    } else if (i == 4) {
                        EnumSet noneOf = EnumSet.noneOf(a.EnumC0643a.class);
                        for (a aVar3 : subList) {
                            noneOf.add(((com.mappls.sdk.navigation.routing.a) aVar3.f12911a).b());
                        }
                        Iterator it5 = noneOf.iterator();
                        while (it5.hasNext()) {
                            this.f12910a.h().o().a(new com.mappls.sdk.navigation.routing.a((a.EnumC0643a) it5.next()), f.getSpeed());
                        }
                    } else if (i == 3) {
                        a().a(f, subList);
                    }
                }
            }
        }
    }

    public final void b(JunctionInfoChangedListener junctionInfoChangedListener) {
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            JunctionInfoChangedListener junctionInfoChangedListener2 = (JunctionInfoChangedListener) ((WeakReference) it.next()).get();
            if (junctionInfoChangedListener2 != null) {
                if (junctionInfoChangedListener == junctionInfoChangedListener2) {
                }
            }
            it.remove();
            return;
        }
    }

    public final void b(NavigationEventListener navigationEventListener) {
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            NavigationEventListener navigationEventListener2 = (NavigationEventListener) ((WeakReference) it.next()).get();
            if (navigationEventListener2 != null) {
                if (navigationEventListener == navigationEventListener2) {
                }
            }
            it.remove();
            return;
        }
    }

    @Deprecated
    public final void c(JunctionInfoChangedListener junctionInfoChangedListener) {
        this.l = junctionInfoChangedListener;
    }

    @Deprecated
    public final void c(NavigationEventListener navigationEventListener) {
        this.j = navigationEventListener;
    }

    public final void a(NavigationRoute navigationRoute) {
        ArrayList arrayList = new ArrayList();
        a(navigationRoute, 1, arrayList);
        if (navigationRoute.getEvents().size() > 0) {
            a(navigationRoute, 6, arrayList);
        }
        if (navigationRoute.getJunctionViews().size() > 0) {
            a(navigationRoute, 5, arrayList);
        }
        synchronized (this) {
            this.b = arrayList;
            this.c.clear();
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList2.add(0);
            }
            this.d = new ArrayList<>(Collections.nCopies(arrayList.size(), 0));
            this.e = navigationRoute;
        }
    }
}
