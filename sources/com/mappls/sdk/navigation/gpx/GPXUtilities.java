package com.mappls.sdk.navigation.gpx;

import android.content.Context;
import android.graphics.Color;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.data.LocationPoint;
import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import com.mappls.sdk.navigation.z;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;
/* loaded from: classes11.dex */
public class GPXUtilities {

    /* renamed from: a  reason: collision with root package name */
    public static final NumberFormat f12901a = new DecimalFormat("0.00#####", new DecimalFormatSymbols(new Locale("EN", "US")));
    public static Charset b = Charset.forName("UTF-8");

    /* loaded from: classes11.dex */
    public static class GPXFile extends c {
        public String author;
        public boolean showCurrentTrack;
        public List<Track> tracks = new ArrayList();
        public List<g> points = new ArrayList();
        public List<Route> routes = new ArrayList();
        public String warning = null;
        public String path = "";
        public long modifiedTime = 0;

        public g addWptPt(double d, double d2, long j, String str, String str2, String str3, int i) {
            g gVar = new g(Double.parseDouble(GPXUtilities.f12901a.format(d)), Double.parseDouble(GPXUtilities.f12901a.format(d2)), j, Double.NaN, 0.0d, Double.NaN);
            gVar.c = str2;
            gVar.e = str3;
            gVar.f = str;
            if (i != 0) {
                gVar.setColor(i);
            }
            this.points.add(gVar);
            this.modifiedTime = System.currentTimeMillis();
            return gVar;
        }

        public boolean deleteWptPt(g gVar) {
            this.modifiedTime = System.currentTimeMillis();
            return this.points.remove(gVar);
        }

        public g findPointToShow() {
            List<g> list;
            Object obj;
            Iterator<Track> it = this.tracks.iterator();
            loop0: while (true) {
                if (it.hasNext()) {
                    for (f fVar : it.next().segments) {
                        if (fVar.f12904a.size() > 0) {
                            obj = fVar.f12904a.get(0);
                            break loop0;
                        }
                    }
                } else {
                    Iterator<Route> it2 = this.routes.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Route next = it2.next();
                            if (next.points.size() > 0) {
                                list = next.points;
                                break;
                            }
                        } else if (this.points.size() <= 0) {
                            return null;
                        } else {
                            list = this.points;
                        }
                    }
                    obj = list.get(0);
                }
            }
            return (g) obj;
        }

        public GPXTrackAnalysis getAnalysis(long j) {
            GPXTrackAnalysis gPXTrackAnalysis = new GPXTrackAnalysis();
            gPXTrackAnalysis.wptPoints = this.points.size();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.tracks.size(); i++) {
                for (f fVar : this.tracks.get(i).segments) {
                    gPXTrackAnalysis.totalTracks++;
                    if (fVar.f12904a.size() > 1) {
                        arrayList.add(new e(fVar));
                    }
                }
            }
            gPXTrackAnalysis.prepareInformation(j, (e[]) arrayList.toArray(new e[arrayList.size()]));
            return gPXTrackAnalysis;
        }

        public g getLastPoint() {
            if (this.tracks.size() > 0) {
                List<Track> list = this.tracks;
                Track track = list.get(list.size() - 1);
                if (track.segments.size() > 0) {
                    List<f> list2 = track.segments;
                    f fVar = list2.get(list2.size() - 1);
                    if (fVar.f12904a.size() > 0) {
                        ArrayList arrayList = fVar.f12904a;
                        return (g) arrayList.get(arrayList.size() - 1);
                    }
                    return null;
                }
                return null;
            }
            return null;
        }

        public boolean hasRtePt() {
            for (Route route : this.routes) {
                if (route.points.size() > 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean hasTrkPt() {
            for (Track track : this.tracks) {
                for (f fVar : track.segments) {
                    if (fVar.f12904a.size() > 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean hasWptPt() {
            return this.points.size() > 0;
        }

        public boolean isCloudmadeRouteFile() {
            return "cloudmade".equalsIgnoreCase(this.author);
        }

        public boolean isEmpty() {
            for (Track track : this.tracks) {
                List<f> list = track.segments;
                if (list != null) {
                    for (f fVar : list) {
                        if (!fVar.f12904a.isEmpty()) {
                            return false;
                        }
                    }
                    continue;
                }
            }
            return this.points.isEmpty() && this.routes.isEmpty();
        }

        public List<f> proccessPoints() {
            ArrayList arrayList = new ArrayList();
            for (Track track : this.tracks) {
                int color = track.getColor(getColor(0));
                for (f fVar : track.segments) {
                    if (fVar.f12904a.size() > 0) {
                        f fVar2 = new f();
                        arrayList.add(fVar2);
                        fVar2.f12904a.addAll(fVar.f12904a);
                        fVar2.setColor(color);
                    }
                }
            }
            return arrayList;
        }

        public List<f> processRoutePoints() {
            ArrayList arrayList = new ArrayList();
            if (this.routes.size() > 0) {
                for (Route route : this.routes) {
                    int color = route.getColor(getColor(0));
                    if (route.points.size() > 0) {
                        f fVar = new f();
                        arrayList.add(fVar);
                        fVar.f12904a.addAll(route.points);
                        fVar.setColor(color);
                    }
                }
            }
            return arrayList;
        }

        public void updateWptPt(g gVar, double d, double d2, long j, String str, String str2, String str3, int i) {
            int indexOf = this.points.indexOf(gVar);
            double parseDouble = Double.parseDouble(GPXUtilities.f12901a.format(d));
            double parseDouble2 = Double.parseDouble(GPXUtilities.f12901a.format(d2));
            gVar.f12905a = parseDouble;
            gVar.b = parseDouble2;
            gVar.h = j;
            gVar.f = str;
            gVar.c = str2;
            gVar.e = str3;
            if (i != 0) {
                gVar.setColor(i);
            }
            if (indexOf != -1) {
                this.points.set(indexOf, gVar);
            }
            this.modifiedTime = System.currentTimeMillis();
        }
    }

    /* loaded from: classes11.dex */
    public static class GPXTrackAnalysis {
        public float avgSpeed;
        public g locationEnd;
        public g locationStart;
        public double metricEnd;
        public int points;
        public double secondaryMetricEnd;
        public float totalDistance = 0.0f;
        public int totalTracks = 0;
        public long startTime = Long.MAX_VALUE;
        public long endTime = Long.MIN_VALUE;
        public long timeSpan = 0;
        public long timeMoving = 0;
        public float totalDistanceMoving = 0.0f;
        public double diffElevationUp = 0.0d;
        public double diffElevationDown = 0.0d;
        public double avgElevation = 0.0d;
        public double minElevation = 99999.0d;
        public double maxElevation = -100.0d;
        public float maxSpeed = 0.0f;
        public int wptPoints = 0;

        public static GPXTrackAnalysis segment(long j, f fVar) {
            return new GPXTrackAnalysis().prepareInformation(j, new e(fVar));
        }

        public boolean isElevationSpecified() {
            return this.maxElevation != -100.0d;
        }

        public boolean isSpeedSpecified() {
            return this.avgSpeed > 0.0f;
        }

        public boolean isTimeMoving() {
            return this.timeMoving != 0;
        }

        public boolean isTimeSpecified() {
            long j = this.startTime;
            return (j == Long.MAX_VALUE || j == 0) ? false : true;
        }

        public GPXTrackAnalysis prepareInformation(long j, e... eVarArr) {
            float f;
            float[] fArr;
            double d;
            boolean z;
            int i;
            e eVar;
            int i2;
            double d2;
            e[] eVarArr2 = eVarArr;
            float[] fArr2 = new float[1];
            this.points = 0;
            int length = eVarArr2.length;
            double d3 = 0.0d;
            int i3 = 0;
            int i4 = 0;
            boolean z2 = false;
            int i5 = 0;
            float f2 = 0.0f;
            while (i5 < length) {
                e eVar2 = eVarArr2[i5];
                int i6 = (eVar2.e - eVar2.c) + 2;
                int i7 = i5;
                this.metricEnd += eVar2.f;
                this.secondaryMetricEnd += eVar2.g;
                this.points += i6;
                z2 = z2;
                double d4 = 5.0d;
                int i8 = 0;
                double d5 = 99999.0d;
                double d6 = 99999.0d;
                double d7 = 99999.0d;
                while (i8 < i6) {
                    g b = eVar2.b(i8);
                    if (i8 == 0 && this.locationStart == null) {
                        this.locationStart = b;
                    }
                    int i9 = i6 - 1;
                    if (i8 == i9) {
                        this.locationEnd = b;
                    }
                    long j2 = b.h;
                    int i10 = length;
                    if (j2 != 0) {
                        fArr = fArr2;
                        this.startTime = Math.min(this.startTime, j2);
                        this.endTime = Math.max(this.endTime, j2);
                    } else {
                        fArr = fArr2;
                    }
                    double d8 = b.i;
                    if (!Double.isNaN(d8)) {
                        f2 = (float) (f2 + d8);
                        i3++;
                        this.minElevation = Math.min(d8, this.minElevation);
                        this.maxElevation = Math.max(d8, this.maxElevation);
                    }
                    int i11 = i3;
                    float f3 = f2;
                    float f4 = (float) b.j;
                    int i12 = (f4 > 0.0f ? 1 : (f4 == 0.0f ? 0 : -1));
                    if (i12 > 0) {
                        d3 += f4;
                        this.maxSpeed = Math.max(f4, this.maxSpeed);
                        i4++;
                    }
                    double d9 = d3;
                    int i13 = i4;
                    if (Double.isNaN(b.i)) {
                        d = d9;
                        z = true;
                    } else {
                        if (d5 == 99999.0d) {
                            d5 = b.i;
                            d6 = d5;
                            d7 = d6;
                            d2 = 5.0d;
                        } else {
                            d2 = d4;
                        }
                        double d10 = b.i;
                        d = d9;
                        if (d10 > d6) {
                            if (!Double.isNaN(b.k)) {
                                d2 = Math.max(d2, b.k * 2.0d);
                            }
                            d6 = d10;
                        } else if (d10 < d7) {
                            if (!Double.isNaN(b.k)) {
                                d2 = Math.max(d2, b.k * 2.0d);
                            }
                            d7 = d10;
                        }
                        double d11 = b.i;
                        z = true;
                        if (d11 <= d6 - d2 && z2) {
                            double d12 = d6 - d5;
                            if (d12 >= d2) {
                                this.diffElevationUp += d12;
                            }
                            d7 = d11;
                            d4 = 5.0d;
                            d5 = d6;
                            z2 = false;
                        } else if (d11 < d7 + d2 || z2) {
                            d4 = d2;
                        } else {
                            double d13 = d5 - d7;
                            if (d13 >= d2) {
                                this.diffElevationDown += d13;
                            }
                            d6 = d11;
                            z2 = true;
                            d4 = 5.0d;
                            d5 = d7;
                        }
                        if (i8 == i9) {
                            double d14 = d6 - d5;
                            if (d14 >= d4) {
                                this.diffElevationUp += d14;
                            }
                            double d15 = d5 - d7;
                            if (d15 >= d4) {
                                this.diffElevationDown += d15;
                            }
                        }
                    }
                    boolean z3 = z2;
                    if (i8 > 0) {
                        g b2 = eVar2.b(i8 - 1);
                        i = i8;
                        eVar = eVar2;
                        i2 = i10;
                        NavLocation.distanceBetween(b2.f12905a, b2.b, b.f12905a, b.b, fArr);
                        float f5 = this.totalDistance;
                        float f6 = fArr[0];
                        this.totalDistance = f5 + f6;
                        if (i12 > 0) {
                            long j3 = b.h;
                            long j4 = b2.h;
                            long j5 = j3 - j4;
                            if (f6 > j5 * 1.0E-4d && j3 != 0 && j4 != 0) {
                                this.timeMoving = j5 + this.timeMoving;
                                this.totalDistanceMoving += f6;
                            }
                        }
                    } else {
                        i = i8;
                        eVar = eVar2;
                        i2 = i10;
                    }
                    z2 = z3;
                    i8 = i + 1;
                    length = i2;
                    fArr2 = fArr;
                    i3 = i11;
                    f2 = f3;
                    i4 = i13;
                    d3 = d;
                    eVar2 = eVar;
                }
                eVarArr2 = eVarArr;
                fArr2 = fArr2;
                i5 = i7 + 1;
            }
            if (!isTimeSpecified()) {
                this.startTime = j;
                this.endTime = j;
            }
            this.timeSpan = this.endTime - this.startTime;
            if (i3 > 0) {
                this.avgElevation = f2 / i3;
            }
            if (i4 > 0) {
                long j6 = this.timeMoving;
                if (j6 > 0) {
                    this.avgSpeed = (this.totalDistanceMoving / ((float) j6)) * 1000.0f;
                    return this;
                }
                f = ((float) d3) / i4;
            } else {
                f = -1.0f;
            }
            this.avgSpeed = f;
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public static class Route extends c {
        public String name = null;
        public String desc = null;
        public List<g> points = new ArrayList();
    }

    /* loaded from: classes11.dex */
    public static class Track extends c {
        public String name = null;
        public String desc = null;
        public List<f> segments = new ArrayList();
    }

    /* loaded from: classes11.dex */
    public class a extends d {

        /* renamed from: a  reason: collision with root package name */
        public float[] f12902a;

        public a() {
            super(0);
            this.f12902a = new float[1];
        }

        @Override // com.mappls.sdk.navigation.gpx.GPXUtilities.d
        public final double a(g gVar, g gVar2) {
            NavLocation.distanceBetween(gVar.f12905a, gVar.b, gVar2.f12905a, gVar2.b, this.f12902a);
            return this.f12902a[0];
        }
    }

    /* loaded from: classes11.dex */
    public class b extends d {
        public b() {
            super(0);
        }

        @Override // com.mappls.sdk.navigation.gpx.GPXUtilities.d
        public final double a(g gVar, g gVar2) {
            long j = gVar.h;
            if (j != 0) {
                long j2 = gVar2.h;
                if (j2 != 0) {
                    return (int) Math.abs((j2 - j) / 1000);
                }
                return 0.0d;
            }
            return 0.0d;
        }
    }

    /* loaded from: classes11.dex */
    public static class c {
        public Map<String, String> l = null;

        @ColorInt
        public int getColor(@ColorInt int i) {
            Map<String, String> map = this.l;
            if (map != null && map.containsKey("color")) {
                try {
                    return Color.parseColor(this.l.get("color").toUpperCase());
                } catch (IllegalArgumentException e) {
                    NavigationLogger.d(e);
                }
            }
            return i;
        }

        public Map<String, String> getExtensionsToRead() {
            Map<String, String> map = this.l;
            return map == null ? Collections.emptyMap() : map;
        }

        public Map<String, String> getExtensionsToWrite() {
            if (this.l == null) {
                this.l = new LinkedHashMap();
            }
            return this.l;
        }

        public void setColor(int i) {
            getExtensionsToWrite().put("color", com.mappls.sdk.navigation.util.a.a(i));
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class d {
        public d() {
        }

        public /* synthetic */ d(int i) {
            this();
        }

        public abstract double a(g gVar, g gVar2);
    }

    /* loaded from: classes11.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public f f12903a;
        public double b;
        public int c;
        public double d;
        public int e;
        public double f;
        public double g;

        public e(f fVar) {
            this.d = 0.0d;
            this.c = 0;
            this.b = 0.0d;
            this.e = fVar.f12904a.size() - 2;
            this.d = 1.0d;
            this.f12903a = fVar;
        }

        public e(f fVar, int i, double d) {
            this.d = 0.0d;
            this.f12903a = fVar;
            this.c = i;
            this.b = d;
        }

        public static double a(double d, double d2, double d3, double d4) {
            return (d == d3 || Double.isNaN(d)) ? d2 : (d2 == d3 || Double.isNaN(d2)) ? d : ((d2 - d) * d4) + d;
        }

        public static g c(g gVar, g gVar2, double d) {
            long j = gVar.h;
            long j2 = gVar2.h;
            if (j == 0) {
                j = j2;
            } else if (j2 != 0) {
                j += (long) ((j2 - j) * d);
            }
            double a2 = a(gVar.j, gVar2.j, 0.0d, d);
            return new g(a(gVar.f12905a, gVar2.f12905a, -360.0d, d), a(gVar.b, gVar2.b, -360.0d, d), j, a(gVar.i, gVar2.i, 0.0d, d), a2, a(gVar.k, gVar2.k, 0.0d, d));
        }

        public final g b(int i) {
            int i2 = this.c;
            int i3 = i + i2;
            if (i == 0) {
                return this.b == 0.0d ? (g) this.f12903a.f12904a.get(i3) : c((g) this.f12903a.f12904a.get(i3), (g) this.f12903a.f12904a.get(i3 + 1), this.b);
            }
            if (i == ((this.e - i2) + 2) - 1 && this.d != 1.0d) {
                return c((g) this.f12903a.f12904a.get(i3 - 1), (g) this.f12903a.f12904a.get(i3), this.d);
            }
            return (g) this.f12903a.f12904a.get(i3);
        }
    }

    /* loaded from: classes11.dex */
    public static class f extends c {

        /* renamed from: a  reason: collision with root package name */
        public ArrayList f12904a = new ArrayList();

        public final List<GPXTrackAnalysis> a(double d) {
            d a2 = GPXUtilities.a();
            d b = GPXUtilities.b();
            ArrayList arrayList = new ArrayList();
            GPXUtilities.l(a2, b, d, arrayList, this);
            return GPXUtilities.f(arrayList);
        }

        public final List<GPXTrackAnalysis> a(int i) {
            d b = GPXUtilities.b();
            d a2 = GPXUtilities.a();
            double d = i;
            ArrayList arrayList = new ArrayList();
            GPXUtilities.l(b, a2, d, arrayList, this);
            return GPXUtilities.f(arrayList);
        }
    }

    /* loaded from: classes11.dex */
    public static class g extends c implements LocationPoint {

        /* renamed from: a  reason: collision with root package name */
        public double f12905a;
        public double b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public long h;
        public double i;
        public double j;
        public double k;

        public g() {
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = 0L;
            this.i = Double.NaN;
            this.j = 0.0d;
            this.k = Double.NaN;
        }

        public g(double d, double d2, long j, double d3, double d4, double d5) {
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.f12905a = d;
            this.b = d2;
            this.h = j;
            this.i = d3;
            this.j = d4;
            this.k = d5;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || g.class != obj.getClass()) {
                return false;
            }
            g gVar = (g) obj;
            return com.mappls.sdk.navigation.util.a.a(gVar.c, this.c) && com.mappls.sdk.navigation.util.a.a(gVar.e, this.e) && com.mappls.sdk.navigation.util.a.a(Double.valueOf(gVar.f12905a), Double.valueOf(this.f12905a)) && com.mappls.sdk.navigation.util.a.a(Double.valueOf(gVar.b), Double.valueOf(this.b)) && com.mappls.sdk.navigation.util.a.a(gVar.f, this.f);
        }

        @Override // com.mappls.sdk.navigation.data.LocationPoint
        public final double getLatitude() {
            return this.f12905a;
        }

        @Override // com.mappls.sdk.navigation.data.LocationPoint
        public final double getLongitude() {
            return this.b;
        }

        @Override // com.mappls.sdk.navigation.data.LocationPoint
        public final com.mappls.sdk.navigation.data.a getPointDescription(Context context) {
            return new com.mappls.sdk.navigation.data.a("wpt", this.c);
        }

        public final int hashCode() {
            String str = this.c;
            int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            String str2 = this.e;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.f;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.g;
            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            double d = this.f12905a;
            int hashCode5 = (hashCode4 + (d == 0.0d ? 0 : Double.valueOf(d).hashCode())) * 31;
            double d2 = this.b;
            return hashCode5 + (d2 != 0.0d ? Double.valueOf(d2).hashCode() : 0);
        }
    }

    public static /* synthetic */ d a() {
        return g();
    }

    public static String asString(GPXFile gPXFile, NavigationApplication navigationApplication) {
        StringWriter stringWriter = new StringWriter();
        writeGpx(stringWriter, gPXFile, navigationApplication);
        return stringWriter.toString();
    }

    public static /* synthetic */ d b() {
        return h();
    }

    public static List<GPXTrackAnalysis> f(List<e> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<e> it = list.iterator();
        while (it.hasNext()) {
            GPXTrackAnalysis gPXTrackAnalysis = new GPXTrackAnalysis();
            gPXTrackAnalysis.prepareInformation(0L, it.next());
            arrayList.add(gPXTrackAnalysis);
        }
        return arrayList;
    }

    public static d g() {
        return new a();
    }

    public static d h() {
        return new b();
    }

    public static Reader i(InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.mark(3);
        byte[] bArr = new byte[3];
        bufferedInputStream.read(bArr);
        boolean z = false;
        if (bArr[0] != -17 || bArr[1] != -69 || bArr[2] != -65) {
            z = true;
        }
        if (z) {
            bufferedInputStream.reset();
        }
        return new InputStreamReader(bufferedInputStream, b);
    }

    public static g j(XmlPullParser xmlPullParser) {
        g gVar = new g();
        try {
            gVar.f12905a = Double.parseDouble(xmlPullParser.getAttributeValue("", "lat"));
            gVar.b = Double.parseDouble(xmlPullParser.getAttributeValue("", "lon"));
        } catch (NumberFormatException e2) {
            NavigationLogger.e(e2);
        }
        return gVar;
    }

    public static String k(XmlPullParser xmlPullParser, String str) {
        String str2 = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1 || (next == 3 && xmlPullParser.getName().equals(str))) {
                break;
            } else if (next == 4) {
                if (str2 == null) {
                    str2 = xmlPullParser.getText();
                } else {
                    StringBuilder a2 = h.a(str2);
                    a2.append(xmlPullParser.getText());
                    str2 = a2.toString();
                }
            }
        }
        return str2;
    }

    public static void l(d dVar, d dVar2, double d2, List<e> list, f fVar) {
        double d3;
        double d4 = 0.0d;
        e eVar = new e(fVar, 0, 0.0d);
        double d5 = d2;
        g gVar = null;
        double d6 = 0.0d;
        for (int i = 0; i < fVar.f12904a.size(); i++) {
            g gVar2 = (g) fVar.f12904a.get(i);
            if (i > 0) {
                double a2 = dVar.a(gVar, gVar2);
                d4 += dVar2.a(gVar, gVar2);
                while (true) {
                    d3 = d6 + a2;
                    if (d3 <= d5) {
                        break;
                    }
                    g gVar3 = gVar2;
                    double d7 = (d5 - d6) / a2;
                    int i2 = i - 1;
                    eVar.d = d7;
                    eVar.e = i2;
                    eVar.f = d5;
                    eVar.g = d4;
                    list.add(eVar);
                    eVar = new e(fVar, i2, d7);
                    d5 += d2;
                    eVar.b(0);
                    gVar2 = gVar3;
                }
                gVar = gVar2;
                d6 = d3;
            } else {
                gVar = gVar2;
            }
        }
        if (fVar.f12904a.size() > 0) {
            if (eVar.e == fVar.f12904a.size() - 1 && eVar.b == 1.0d) {
                return;
            }
            eVar.f = d6;
            eVar.g = d4;
            eVar.d = 1.0d;
            eVar.e = fVar.f12904a.size() - 2;
            list.add(eVar);
        }
    }

    public static GPXFile loadGPXFile(Context context, File file) {
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    try {
                        GPXFile loadGPXFile = loadGPXFile(context, fileInputStream2);
                        loadGPXFile.path = file.getAbsolutePath();
                        try {
                            fileInputStream2.close();
                        } catch (IOException e2) {
                            NavigationLogger.e(e2);
                        }
                        try {
                            fileInputStream2.close();
                        } catch (IOException e3) {
                            NavigationLogger.e(e3);
                        }
                        return loadGPXFile;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                NavigationLogger.e(e4);
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                    fileInputStream = fileInputStream2;
                    NavigationLogger.e(e);
                    GPXFile gPXFile = new GPXFile();
                    gPXFile.path = file.getAbsolutePath();
                    NavigationLogger.e(e, "BadRequestError reading gpx", new Object[0]);
                    gPXFile.warning = context.getString(R.string.mappls_error_reading_gpx);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            NavigationLogger.e(e6);
                        }
                    }
                    return gPXFile;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException e7) {
            e = e7;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:157:0x030f, code lost:
        if (r2.equals("trkseg") != false) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x015e, code lost:
        r0.add(r1);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.mappls.sdk.navigation.gpx.GPXUtilities.GPXFile loadGPXFile(android.content.Context r22, java.io.InputStream r23) {
        /*
            Method dump skipped, instructions count: 922
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.gpx.GPXUtilities.loadGPXFile(android.content.Context, java.io.InputStream):com.mappls.sdk.navigation.gpx.GPXUtilities$GPXFile");
    }

    public static GPXFile loadGPXFile(Context context, String str) {
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(str.getBytes(b));
            } catch (Throwable th) {
                th = th;
                byteArrayInputStream = null;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            GPXFile loadGPXFile = loadGPXFile(context, byteArrayInputStream);
            try {
                byteArrayInputStream.close();
            } catch (IOException e3) {
                NavigationLogger.e(e3);
            }
            try {
                byteArrayInputStream.close();
            } catch (IOException e4) {
                NavigationLogger.d(e4);
            }
            return loadGPXFile;
        } catch (Exception e5) {
            e = e5;
            byteArrayInputStream2 = byteArrayInputStream;
            GPXFile gPXFile = new GPXFile();
            NavigationLogger.e(e, "BadRequestError reading gpx", new Object[0]);
            gPXFile.warning = context.getString(R.string.mappls_error_reading_gpx);
            if (byteArrayInputStream2 != null) {
                try {
                    byteArrayInputStream2.close();
                } catch (IOException e6) {
                    NavigationLogger.d(e6);
                }
            }
            return gPXFile;
        } catch (Throwable th2) {
            th = th2;
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e7) {
                    NavigationLogger.d(e7);
                }
            }
            throw th;
        }
    }

    public static void m(XmlSerializer xmlSerializer, c cVar) {
        if (cVar.getExtensionsToRead().isEmpty()) {
            return;
        }
        xmlSerializer.startTag(null, "extensions");
        for (Map.Entry<String, String> entry : cVar.getExtensionsToRead().entrySet()) {
            n(xmlSerializer, entry.getKey(), entry.getValue());
        }
        xmlSerializer.endTag(null, "extensions");
    }

    public static void mergeGPXFileInto(GPXFile gPXFile, GPXFile gPXFile2) {
        if (gPXFile2 == null) {
            return;
        }
        if (gPXFile2.showCurrentTrack) {
            gPXFile.showCurrentTrack = true;
        }
        List<g> list = gPXFile2.points;
        if (list != null) {
            gPXFile.points.addAll(list);
        }
        List<Track> list2 = gPXFile2.tracks;
        if (list2 != null) {
            gPXFile.tracks.addAll(list2);
        }
        List<Route> list3 = gPXFile2.routes;
        if (list3 != null) {
            gPXFile.routes.addAll(list3);
        }
        String str = gPXFile2.warning;
        if (str != null) {
            gPXFile.warning = str;
        }
    }

    public static void n(XmlSerializer xmlSerializer, String str, String str2) {
        if (str2 != null) {
            xmlSerializer.startTag(null, str);
            xmlSerializer.text(str2);
            xmlSerializer.endTag(null, str);
        }
    }

    public static void o(SimpleDateFormat simpleDateFormat, XmlSerializer xmlSerializer, g gVar) {
        NumberFormat numberFormat = f12901a;
        xmlSerializer.attribute(null, "lat", numberFormat.format(gVar.f12905a));
        xmlSerializer.attribute(null, "lon", numberFormat.format(gVar.b));
        if (!Double.isNaN(gVar.i)) {
            n(xmlSerializer, "ele", ((float) gVar.i) + "");
        }
        if (gVar.h != 0) {
            n(xmlSerializer, NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, simpleDateFormat.format(new Date(gVar.h)));
        }
        n(xmlSerializer, AppMeasurementSdk.ConditionalUserProperty.NAME, gVar.c);
        n(xmlSerializer, "desc", gVar.f);
        if (gVar.d != null) {
            xmlSerializer.startTag(null, "link");
            xmlSerializer.attribute(null, "href", gVar.d);
            xmlSerializer.endTag(null, "link");
        }
        n(xmlSerializer, "type", gVar.e);
        String str = gVar.g;
        if (str != null) {
            n(xmlSerializer, "cmt", str);
        }
        if (!Double.isNaN(gVar.k)) {
            n(xmlSerializer, SavingTrackHelper.TRACK_COL_HDOP, gVar.k + "");
        }
        if (gVar.j > 0.0d) {
            Map<String, String> extensionsToWrite = gVar.getExtensionsToWrite();
            extensionsToWrite.put("speed", gVar.j + "");
        }
        m(xmlSerializer, gVar);
    }

    public static String writeGpx(Writer writer, GPXFile gPXFile, NavigationApplication navigationApplication) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            XmlSerializer newSerializer = Xml.newSerializer();
            newSerializer.setOutput(writer);
            newSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            newSerializer.startDocument("UTF-8", Boolean.TRUE);
            newSerializer.startTag(null, "gpx");
            newSerializer.attribute(null, "version", "1.1");
            String str = gPXFile.author;
            if (str == null) {
                str = z.a(navigationApplication);
            }
            newSerializer.attribute(null, "creator", str);
            newSerializer.attribute(null, "xmlns", "http://www.topografix.com/GPX/1/1");
            newSerializer.attribute(null, "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            newSerializer.attribute(null, "xsi:schemaLocation", "http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd");
            Iterator<Track> it = gPXFile.tracks.iterator();
            while (it.hasNext()) {
                Track next = it.next();
                Iterator<Track> it2 = it;
                newSerializer.startTag(null, "trk");
                n(newSerializer, AppMeasurementSdk.ConditionalUserProperty.NAME, next.name);
                n(newSerializer, "desc", next.desc);
                Iterator<f> it3 = next.segments.iterator();
                while (it3.hasNext()) {
                    newSerializer.startTag(null, "trkseg");
                    Iterator it4 = it3.next().f12904a.iterator();
                    while (it4.hasNext()) {
                        newSerializer.startTag(null, "trkpt");
                        o(simpleDateFormat, newSerializer, (g) it4.next());
                        newSerializer.endTag(null, "trkpt");
                        it3 = it3;
                    }
                    newSerializer.endTag(null, "trkseg");
                    it3 = it3;
                }
                m(newSerializer, next);
                newSerializer.endTag(null, "trk");
                it = it2;
            }
            for (Route route : gPXFile.routes) {
                newSerializer.startTag(null, "rte");
                n(newSerializer, AppMeasurementSdk.ConditionalUserProperty.NAME, route.name);
                n(newSerializer, "desc", route.desc);
                for (g gVar : route.points) {
                    newSerializer.startTag(null, "rtept");
                    o(simpleDateFormat, newSerializer, gVar);
                    newSerializer.endTag(null, "rtept");
                }
                m(newSerializer, route);
                newSerializer.endTag(null, "rte");
            }
            for (g gVar2 : gPXFile.points) {
                newSerializer.startTag(null, "wpt");
                o(simpleDateFormat, newSerializer, gVar2);
                newSerializer.endTag(null, "wpt");
            }
            newSerializer.endTag(null, "gpx");
            newSerializer.flush();
            newSerializer.endDocument();
            return null;
        } catch (IOException e2) {
            NavigationLogger.e(e2, "BadRequestError saving gpx", new Object[0]);
            return navigationApplication.getString(R.string.mappls_error_occurred_saving_gpx);
        } catch (RuntimeException e3) {
            NavigationLogger.e(e3, "BadRequestError saving gpx", new Object[0]);
            return navigationApplication.getString(R.string.mappls_error_occurred_saving_gpx);
        }
    }

    public static String writeGpxFile(File file, GPXFile gPXFile, NavigationApplication navigationApplication) {
        OutputStreamWriter outputStreamWriter;
        OutputStreamWriter outputStreamWriter2 = null;
        try {
            try {
                outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), b);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            outputStreamWriter = outputStreamWriter2;
        }
        try {
            String writeGpx = writeGpx(outputStreamWriter, gPXFile, navigationApplication);
            try {
                outputStreamWriter.close();
            } catch (IOException e3) {
                NavigationLogger.d(e3);
            }
            return writeGpx;
        } catch (IOException e4) {
            e = e4;
            outputStreamWriter2 = outputStreamWriter;
            NavigationLogger.e(e, "BadRequestError saving gpx", new Object[0]);
            String string = navigationApplication.getString(R.string.mappls_error_occurred_saving_gpx);
            if (outputStreamWriter2 != null) {
                try {
                    outputStreamWriter2.close();
                } catch (IOException e5) {
                    NavigationLogger.d(e5);
                }
            }
            return string;
        } catch (Throwable th2) {
            th = th2;
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e6) {
                    NavigationLogger.d(e6);
                }
            }
            throw th;
        }
    }
}
