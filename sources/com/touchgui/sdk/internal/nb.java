package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.TGWorkoutDataCallback;
import com.touchgui.sdk.bean.TGWorkoutRecord;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class nb {

    /* renamed from: a  reason: collision with root package name */
    public final int f13803a;
    public int c;
    public final /* synthetic */ ob e;
    public final ArrayList b = new ArrayList();
    public qb d = null;

    public nb(ob obVar, int i) {
        this.e = obVar;
        this.f13803a = i;
    }

    public static void a(nb nbVar, com.touchgui.sdk.bean.a aVar) {
        if (nbVar.d != null) {
            TGWorkoutRecord tGWorkoutRecord = new TGWorkoutRecord();
            tGWorkoutRecord.setSummary(nbVar.d);
            if (aVar != null) {
                tGWorkoutRecord.setEvents(aVar.f13732a);
                tGWorkoutRecord.setHearts(aVar.b);
                tGWorkoutRecord.setPaceData(aVar.d);
                tGWorkoutRecord.setRowingData(aVar.e);
                ArrayList arrayList = aVar.c;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    qb qbVar = nbVar.d;
                    int i = qbVar.f13820a;
                    int i2 = qbVar.b;
                    int i3 = qbVar.c;
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        pb pbVar = (pb) it.next();
                        TGWorkoutRecord.Gps gps = new TGWorkoutRecord.Gps();
                        int i4 = pbVar.b + i;
                        int i5 = pbVar.c + i2;
                        i3 += pbVar.d;
                        gps.setOffset(pbVar.f13814a);
                        double d = i4;
                        gps.setLongitude(d / 1000000.0d);
                        double d2 = i5;
                        gps.setLatitude(d2 / 1000000.0d);
                        gps.setAltitude(i3);
                        double d3 = (i2 * 3.141592653589793d) / 180.0d;
                        double d4 = (d2 * 3.141592653589793d) / 180.0d;
                        gps.setSpeed((((float) Math.round(((Math.asin(Math.sqrt((Math.pow(Math.sin((((i - d) * 3.141592653589793d) / 180.0d) / 2.0d), 2.0d) * (Math.cos(d4) * Math.cos(d3))) + Math.pow(Math.sin((d3 - d4) / 2.0d), 2.0d))) * 2.0d) * 6378137.0d) * 100.0d)) / 100.0f) / pbVar.f13814a);
                        arrayList2.add(gps);
                        it = it;
                        i = i4;
                        i2 = i5;
                    }
                    tGWorkoutRecord.setGpsData(arrayList2);
                }
            }
            TGLogger.d(tGWorkoutRecord.toString());
            nbVar.b.add(tGWorkoutRecord);
        }
    }

    public static void a(nb nbVar, boolean z) {
        new v8(nbVar.e.f13809a, new f9(nbVar.c, z)).execute(new mb(nbVar));
    }

    public final void a() {
        int i = this.f13803a;
        if (i > 0) {
            ob obVar = this.e;
            int i2 = (this.c * 100) / i;
            na naVar = obVar.c;
            if (naVar != null) {
                Iterator it = naVar.f13802a.b.iterator();
                while (it.hasNext()) {
                    ((TGWorkoutDataCallback) it.next()).onProgress(i2);
                }
            }
        }
        int i3 = this.c;
        int i4 = this.f13803a;
        if (i3 < i4) {
            int i5 = i3 + 1;
            this.c = i5;
            new v8(this.e.f13809a, new e9(i5, 1)).execute(new jb(this, 1));
            return;
        }
        ob obVar2 = this.e;
        ArrayList arrayList = this.b;
        boolean z = i4 == arrayList.size();
        TGLogger.d(obVar2.f13809a, "Synchronous motion data complete");
        new v8(obVar2.f13809a, new g9(z)).execute(new ib(obVar2, arrayList));
    }
}
