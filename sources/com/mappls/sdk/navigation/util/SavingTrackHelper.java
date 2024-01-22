package com.mappls.sdk.navigation.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.NavigationLocationProvider;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.gpx.GPXDataModel;
import com.mappls.sdk.navigation.gpx.GPXUtilities;
import com.mappls.sdk.navigation.gpx.GpxSelectionHelper;
import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.notifications.a;
import com.mappls.sdk.navigation.o;
import com.mappls.sdk.navigation.s;
import com.mappls.sdk.navigation.t;
import com.mappls.sdk.navigation.util.navigationLogs.NavigationTrace;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class SavingTrackHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tracks";
    public static final int DATABASE_VERSION = 5;
    public static final String IS_SYNCED = "is_synced";
    public static final String POINT_COL_CATEGORY = "category";
    public static final String POINT_COL_COLOR = "color";
    public static final String POINT_COL_DATE = "date";
    public static final String POINT_COL_DESCRIPTION = "description";
    public static final String POINT_COL_LAT = "lat";
    public static final String POINT_COL_LON = "lon";
    public static final String POINT_COL_NAME = "pname";
    public static final String POINT_NAME = "point";
    public static final String SQL_CREATE_TABLE_TRACKS = "CREATE TABLE IF NOT EXISTS tracks_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT, track_id INTEGER NOT NULL, track_name TEXT NOT NULL, gpx_path TEXT NOT NULL, is_synced TEXT DEFAULT '0'  );";
    public static final String TRACK_COL_ALTITUDE = "altitude";
    public static final String TRACK_COL_DATE = "date";
    public static final String TRACK_COL_HDOP = "hdop";
    public static final String TRACK_COL_LAT = "lat";
    public static final String TRACK_COL_LON = "lon";
    public static final String TRACK_COL_SPEED = "speed";
    public static final String TRACK_ID = "track_id";
    public static final String TRACK_NAME = "track";
    public static final String TRACK_NAME_FIELD = "track_name";
    public static final String TRACK_PATH = "gpx_path";
    public static final String TRACK_TABLE_NAME = "tracks_table";
    public final NavigationApplication h;
    public String i;
    public String j;
    public String k;
    public long l;
    public LatLng m;
    public float n;
    public long o;
    public GpxSelectionHelper.SelectedGpxFile p;
    public int q;

    public SavingTrackHelper(NavigationApplication navigationApplication) {
        super(navigationApplication, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 5);
        this.l = 0L;
        this.n = 0.0f;
        this.o = 0L;
        this.h = navigationApplication;
        GpxSelectionHelper.SelectedGpxFile selectedGpxFile = new GpxSelectionHelper.SelectedGpxFile();
        this.p = selectedGpxFile;
        selectedGpxFile.setShowCurrentTrack(true);
        GPXUtilities.GPXFile gPXFile = new GPXUtilities.GPXFile();
        gPXFile.showCurrentTrack = true;
        this.p.setGpxFile(gPXFile);
        i();
        this.i = "INSERT INTO track (lat, lon, altitude, speed, hdop, date) VALUES (?, ?, ?, ?, ?, ?)";
        this.j = "INSERT INTO tracks_table (track_id, track_name, gpx_path, is_synced) VALUES (?, ?, ?, ?)";
        this.k = "INSERT INTO point VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    public final void a(GPXUtilities.g gVar, boolean z, long j) {
        List<GPXUtilities.f> modifiablePointsToDisplay = this.p.getModifiablePointsToDisplay();
        GPXUtilities.Track track = this.p.getGpxFile().tracks.get(0);
        if (modifiablePointsToDisplay.size() == 0 || z) {
            modifiablePointsToDisplay.add(new GPXUtilities.f());
        }
        if (track.segments.size() == 0 || z) {
            track.segments.add(new GPXUtilities.f());
        }
        if (gVar != null) {
            modifiablePointsToDisplay.get(modifiablePointsToDisplay.size() - 1).f12904a.add(gVar);
            List<GPXUtilities.f> list = track.segments;
            list.get(list.size() - 1).f12904a.add(gVar);
        }
        this.p.getModifiableGpxFile().modifiedTime = j;
    }

    public final void b(SQLiteDatabase sQLiteDatabase, Map<String, GPXUtilities.GPXFile> map) {
        GPXUtilities.GPXFile gPXFile;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT lat,lon,date,description,pname,category,color FROM point ORDER BY date ASC", null);
        if (!rawQuery.moveToFirst()) {
            rawQuery.close();
        }
        do {
            GPXUtilities.g gVar = new GPXUtilities.g();
            gVar.f12905a = rawQuery.getDouble(0);
            gVar.b = rawQuery.getDouble(1);
            long j = rawQuery.getLong(2);
            gVar.h = j;
            gVar.f = rawQuery.getString(3);
            gVar.c = rawQuery.getString(4);
            gVar.e = rawQuery.getString(5);
            int i = rawQuery.getInt(6);
            if (i != 0) {
                gVar.setColor(i);
            }
            String str = gVar.c;
            if (str != null && str.length() > 4) {
                String str2 = gVar.c;
                if (str2.charAt(str2.length() - 4) == '.') {
                    gVar.d = gVar.c;
                }
            }
            String charSequence = DateFormat.format("yyyy-MM-dd", j).toString();
            if (map.containsKey(charSequence)) {
                gPXFile = map.get(charSequence);
            } else {
                GPXUtilities.GPXFile gPXFile2 = new GPXUtilities.GPXFile();
                map.put(charSequence, gPXFile2);
                gPXFile = gPXFile2;
            }
            gPXFile.points.add(gVar);
        } while (rawQuery.moveToNext());
        rawQuery.close();
    }

    public final void c(SQLiteDatabase sQLiteDatabase, Map<String, GPXUtilities.GPXFile> map) {
        GPXUtilities.Track track = null;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT lat,lon,altitude,speed,hdop,date FROM track ORDER BY date ASC", null);
        if (rawQuery.moveToFirst()) {
            long j = 0;
            GPXUtilities.f fVar = null;
            long j2 = 0;
            while (true) {
                GPXUtilities.g gVar = new GPXUtilities.g();
                boolean z = false;
                gVar.f12905a = rawQuery.getDouble(0);
                gVar.b = rawQuery.getDouble(1);
                gVar.i = rawQuery.getDouble(2);
                gVar.j = rawQuery.getDouble(3);
                gVar.k = rawQuery.getDouble(4);
                long j3 = rawQuery.getLong(5);
                gVar.h = j3;
                long abs = Math.abs(j3 - j2);
                if (gVar.f12905a == 0.0d && gVar.b == 0.0d) {
                    z = true;
                }
                if (track != null && !z && (abs < 360000 || abs < j * 10)) {
                    fVar.f12904a.add(gVar);
                } else if (track == null || abs >= 7200000) {
                    track = new GPXUtilities.Track();
                    fVar = new GPXUtilities.f();
                    track.segments.add(fVar);
                    if (!z) {
                        fVar.f12904a.add(gVar);
                    }
                    String charSequence = DateFormat.format("yyyy-MM-dd", j3).toString();
                    if (map.containsKey(charSequence)) {
                        map.get(charSequence).tracks.add(track);
                    } else {
                        GPXUtilities.GPXFile gPXFile = new GPXUtilities.GPXFile();
                        gPXFile.tracks.add(track);
                        map.put(charSequence, gPXFile);
                    }
                } else {
                    fVar = new GPXUtilities.f();
                    if (!z) {
                        fVar.f12904a.add(gVar);
                    }
                    track.segments.add(fVar);
                }
                if (!rawQuery.moveToNext()) {
                    break;
                }
                j = abs;
                j2 = j3;
            }
        }
        rawQuery.close();
    }

    public void clearPreviousData() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase != null && writableDatabase.isOpen()) {
            try {
                writableDatabase.execSQL("DELETE FROM track WHERE date <= ?", new Object[]{Long.valueOf(System.currentTimeMillis())});
                writableDatabase.execSQL("DELETE FROM point WHERE date <= ?", new Object[]{Long.valueOf(System.currentTimeMillis())});
            } finally {
                writableDatabase.close();
            }
        }
        this.n = 0.0f;
        this.q = 0;
        this.o = 0L;
        this.p.getModifiableGpxFile().points.clear();
        this.p.getModifiableGpxFile().tracks.clear();
        this.p.getModifiablePointsToDisplay().clear();
        this.p.getModifiableGpxFile().modifiedTime = System.currentTimeMillis();
        i();
    }

    public Map<String, GPXUtilities.GPXFile> collectRecordedData() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase != null && readableDatabase.isOpen()) {
            try {
                b(readableDatabase, linkedHashMap);
                c(readableDatabase, linkedHashMap);
            } finally {
                readableDatabase.close();
            }
        }
        return linkedHashMap;
    }

    public final ArrayList<GPXDataModel> d(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT track_id,track_name,gpx_path,is_synced FROM tracks_table", null);
        ArrayList<GPXDataModel> arrayList = new ArrayList<>();
        if (!rawQuery.moveToFirst()) {
            rawQuery.close();
            return arrayList;
        }
        do {
            GPXDataModel gPXDataModel = new GPXDataModel();
            gPXDataModel.setTrackID(rawQuery.getInt(0));
            gPXDataModel.setIsSynced(rawQuery.getString(3));
            gPXDataModel.setTrackName(rawQuery.getString(1));
            GPXUtilities.GPXFile loadGPXFile = GPXUtilities.loadGPXFile(this.h, rawQuery.getString(2));
            try {
                ArrayList<LatLng> arrayList2 = new ArrayList<>();
                for (GPXUtilities.Track track : loadGPXFile.tracks) {
                    for (GPXUtilities.f fVar : track.segments) {
                        Iterator it = fVar.f12904a.iterator();
                        while (it.hasNext()) {
                            GPXUtilities.g gVar = (GPXUtilities.g) it.next();
                            arrayList2.add(new LatLng(gVar.getLatitude(), gVar.getLongitude()));
                        }
                    }
                }
                gPXDataModel.setTrackPath(arrayList2);
            } catch (Exception e) {
                NavigationLogger.d(e);
            }
            arrayList.add(gPXDataModel);
            GPXUtilities.GPXTrackAnalysis analysis = loadGPXFile.getAnalysis(loadGPXFile.modifiedTime);
            gPXDataModel.setMaxSpeed(NavigationFormatter.getFormattedSpeed(analysis.maxSpeed, this.h));
            gPXDataModel.setAverageSpeed(NavigationFormatter.getFormattedSpeed(analysis.avgSpeed, this.h));
            if (analysis.timeSpan > 0) {
                gPXDataModel.setTimeMoving(a.a((int) (analysis.timeMoving / 1000), this.h.a()));
            }
            gPXDataModel.setDistance(NavigationFormatter.getFormattedDistance(analysis.totalDistanceMoving, this.h));
            NavigationApplication navigationApplication = this.h;
            int i = R.string.mappls_gpx_start_time;
            gPXDataModel.setStartTime(navigationApplication.getString(i, Long.valueOf(analysis.startTime)));
            gPXDataModel.setEndTime(this.h.getString(i, Long.valueOf(analysis.endTime)));
        } while (rawQuery.moveToNext());
        rawQuery.close();
        return arrayList;
    }

    public void deletePointData(GPXUtilities.g gVar) {
        this.p.getModifiableGpxFile().points.remove(gVar);
        this.p.getModifiableGpxFile().modifiedTime = System.currentTimeMillis();
        this.q--;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Double.valueOf(gVar.f12905a));
        arrayList.add(Double.valueOf(gVar.b));
        arrayList.add(Long.valueOf(gVar.h));
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM point WHERE lat=? AND lon=? AND date=?");
        String str = gVar.f;
        sb.append(" AND ");
        sb.append(POINT_COL_DESCRIPTION);
        if (str != null) {
            sb.append(org.apache.commons.codec.net.a.PREFIX);
            arrayList.add(gVar.f);
        } else {
            sb.append(" IS NULL");
        }
        String str2 = gVar.c;
        sb.append(" AND ");
        sb.append(POINT_COL_NAME);
        if (str2 != null) {
            sb.append(org.apache.commons.codec.net.a.PREFIX);
            arrayList.add(gVar.c);
        } else {
            sb.append(" IS NULL");
        }
        String str3 = gVar.e;
        sb.append(" AND ");
        sb.append(POINT_COL_CATEGORY);
        if (str3 != null) {
            sb.append(org.apache.commons.codec.net.a.PREFIX);
            arrayList.add(gVar.e);
        } else {
            sb.append(" IS NULL");
        }
        h(sb.toString(), arrayList.toArray());
    }

    public final void e(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(SQL_CREATE_TABLE_TRACKS);
    }

    public final void f(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE point (lat double, lon double, date long, description text, pname text, category text, color long)");
        } catch (RuntimeException e) {
            NavigationLogger.d(e);
        }
    }

    public final void g(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE track (lat double, lon double, altitude double, speed double, hdop double, date long )");
    }

    public GPXUtilities.GPXFile getCurrentGpx() {
        return this.p.getGpxFile();
    }

    public GpxSelectionHelper.SelectedGpxFile getCurrentTrack() {
        return this.p;
    }

    public float getDistance() {
        return this.n;
    }

    public long getDuration() {
        return this.o;
    }

    public ArrayList<GPXDataModel> getGPXTracks() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase == null || !readableDatabase.isOpen()) {
            return null;
        }
        try {
            return d(readableDatabase);
        } finally {
            readableDatabase.close();
        }
    }

    public boolean getIsRecording() {
        if (s.a() != null) {
            return this.h.k().u.get().booleanValue() || (this.h.k().x.get().booleanValue() && this.h.h().q());
        }
        return false;
    }

    public long getLastTimeUpdated() {
        return this.l;
    }

    public long getLastTrackPointTime() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                Cursor rawQuery = writableDatabase.rawQuery("SELECT date FROM track ORDER BY date DESC", null);
                r0 = rawQuery.moveToFirst() ? rawQuery.getLong(0) : 0L;
                rawQuery.close();
                writableDatabase.close();
            }
        } catch (RuntimeException e) {
            NavigationLogger.e(e);
        }
        return r0;
    }

    public int getPoints() {
        return this.q;
    }

    public final synchronized void h(String str, Object[] objArr) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase != null) {
            writableDatabase.execSQL(str, objArr);
        }
        if (writableDatabase != null) {
            writableDatabase.close();
        }
    }

    public synchronized boolean hasDataToSave() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    Cursor query = writableDatabase.query(false, TRACK_NAME, new String[0], null, null, null, null, null, null);
                    boolean moveToFirst = query.moveToFirst();
                    query.close();
                    if (moveToFirst) {
                        return true;
                    }
                    Cursor query2 = writableDatabase.query(false, "point", new String[]{"lat", "lon"}, null, null, null, null, null, null);
                    boolean moveToFirst2 = query2.moveToFirst();
                    while (true) {
                        if (!moveToFirst2 || query2.getDouble(0) != 0.0d || query2.getDouble(1) != 0.0d) {
                            break;
                        } else if (!query2.moveToNext()) {
                            moveToFirst2 = false;
                            break;
                        }
                    }
                    query2.close();
                    if (moveToFirst2) {
                        return true;
                    }
                    writableDatabase.close();
                } finally {
                    writableDatabase.close();
                }
            }
            return false;
        } catch (RuntimeException e) {
            NavigationLogger.d(e);
            return false;
        }
    }

    public final void i() {
        if (this.p.getModifiableGpxFile().tracks.size() == 0) {
            this.p.getModifiableGpxFile().tracks.add(new GPXUtilities.Track());
        }
        while (this.p.getPointsToDisplay().size() < this.p.getModifiableGpxFile().tracks.size()) {
            this.p.getModifiablePointsToDisplay().add(new GPXUtilities.f());
        }
    }

    public void insertData(double d, double d2, double d3, double d4, double d5, long j, t tVar) {
        boolean z;
        h(this.i, new Object[]{Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(d4), Double.valueOf(d5), Long.valueOf(j)});
        LatLng latLng = this.m;
        if (latLng == null || j - this.l > 180000) {
            this.m = new LatLng(d, d2);
            z = true;
        } else {
            float[] fArr = new float[1];
            NavLocation.distanceBetween(d, d2, latLng.getLatitude(), this.m.getLongitude(), fArr);
            long j2 = this.l;
            if (j2 > 0 && j > j2) {
                this.o = (j - j2) + this.o;
            }
            this.n += fArr[0];
            this.m = new LatLng(d, d2);
            z = false;
        }
        this.l = j;
        a(new GPXUtilities.g(d, d2, j, d3, d4, d5), z, j);
    }

    public GPXUtilities.g insertPointData(double d, double d2, long j, String str, String str2, String str3, int i) {
        GPXUtilities.g gVar = new GPXUtilities.g(d, d2, j, Double.NaN, 0.0d, Double.NaN);
        gVar.c = str2;
        gVar.e = str3;
        gVar.f = str;
        if (i != 0) {
            gVar.setColor(i);
        }
        this.p.getModifiableGpxFile().points.add(gVar);
        this.p.getModifiableGpxFile().modifiedTime = j;
        this.q++;
        h(this.k, new Object[]{Double.valueOf(d), Double.valueOf(d2), Long.valueOf(j), str, str2, str3, Integer.valueOf(i)});
        return gVar;
    }

    public void loadGpxFromDatabase() {
        Map<String, GPXUtilities.GPXFile> collectRecordedData = collectRecordedData();
        this.p.getModifiableGpxFile().tracks.clear();
        for (Map.Entry<String, GPXUtilities.GPXFile> entry : collectRecordedData.entrySet()) {
            this.p.getModifiableGpxFile().points.addAll(entry.getValue().points);
            this.p.getModifiableGpxFile().tracks.addAll(entry.getValue().tracks);
        }
        this.p.processPoints();
        i();
        GPXUtilities.GPXTrackAnalysis analysis = this.p.getModifiableGpxFile().getAnalysis(System.currentTimeMillis());
        this.n = analysis.totalDistance;
        this.q = analysis.wptPoints;
        this.o = analysis.timeSpan;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        g(sQLiteDatabase);
        f(sQLiteDatabase);
        e(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public synchronized List<String> saveDataToGpx(File file) {
        return saveDataToGpx(null, file);
    }

    public synchronized List<String> saveDataToGpx(String str, File file) {
        ArrayList arrayList = new ArrayList();
        file.mkdirs();
        if (file.getParentFile().canWrite() && file.exists()) {
            Map<String, GPXUtilities.GPXFile> collectRecordedData = collectRecordedData();
            for (String str2 : collectRecordedData.keySet()) {
                File file2 = new File(file, o.a(str2, ".gpx"));
                if (!collectRecordedData.get(str2).isEmpty()) {
                    GPXUtilities.g findPointToShow = collectRecordedData.get(str2).findPointToShow();
                    String str3 = str2 + "_" + new SimpleDateFormat("HH-mm_EEE", Locale.US).format(new Date(findPointToShow.h));
                    File file3 = new File(file, o.a(str3, ".gpx"));
                    int i = 1;
                    while (file3.exists()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str3);
                        sb.append("_");
                        i++;
                        sb.append(i);
                        sb.append(".gpx");
                        file3 = new File(file, sb.toString());
                    }
                    file2 = file3;
                }
                String str4 = this.j;
                Object[] objArr = new Object[4];
                objArr[0] = BleConst.GetDeviceTime;
                objArr[1] = str != null ? str : "";
                objArr[2] = GPXUtilities.asString(collectRecordedData.get(str2), this.h);
                objArr[3] = 0;
                h(str4, objArr);
                String writeGpxFile = GPXUtilities.writeGpxFile(file2, collectRecordedData.get(str2), this.h);
                if (writeGpxFile != null) {
                    arrayList.add(writeGpxFile);
                    return arrayList;
                }
            }
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase != null && arrayList.isEmpty() && writableDatabase.isOpen()) {
            writableDatabase.execSQL("DELETE FROM track WHERE date <= ?", new Object[]{Long.valueOf(System.currentTimeMillis())});
            writableDatabase.execSQL("DELETE FROM point WHERE date <= ?", new Object[]{Long.valueOf(System.currentTimeMillis())});
            writableDatabase.close();
        }
        this.n = 0.0f;
        this.q = 0;
        this.o = 0L;
        this.p.getModifiableGpxFile().points.clear();
        this.p.getModifiableGpxFile().tracks.clear();
        this.p.getModifiablePointsToDisplay().clear();
        this.p.getModifiableGpxFile().modifiedTime = System.currentTimeMillis();
        i();
        return arrayList;
    }

    public void startNewSegment() {
        this.l = 0L;
        this.m = null;
        h(this.i, new Object[]{0, 0, 0, 0, 0, Long.valueOf(System.currentTimeMillis())});
        a(null, true, System.currentTimeMillis());
    }

    public void updateLocation(NavLocation navLocation) {
        LatLng latLng;
        long currentTimeMillis = System.currentTimeMillis();
        t k = this.h.k();
        boolean z = false;
        if (NavigationLocationProvider.isPointAccurateForRouting(navLocation) && NavigationLocationProvider.isNotSimulatedLocation(navLocation) && s.a() != null) {
            boolean z2 = (k.x.get().booleanValue() && currentTimeMillis - this.l > ((long) k.a0.get().intValue()) && this.h.h().q()) || (k.u.get().booleanValue() && currentTimeMillis - this.l > ((long) k.v.get().intValue()));
            float floatValue = k.d0.get().floatValue();
            if (floatValue > 0.0f && (latLng = this.m) != null && d.a(latLng, navLocation.getLatitude(), navLocation.getLongitude()) < floatValue) {
                NavigationTrace.writeLineNavigation("SAVE_TRACK_MIN_DISTANCE : Min Distance = " + floatValue + " | actual = " + d.a(this.m, navLocation.getLatitude(), navLocation.getLongitude()));
                z2 = false;
            }
            float floatValue2 = k.b0.get().floatValue();
            if (floatValue2 > 0.0f && (!navLocation.hasAccuracy() || navLocation.getAccuracy() < floatValue2)) {
                NavigationTrace.writeLineNavigation("SAVE_TRACK_PRECISION : Precision = " + floatValue2 + " | actual = " + navLocation.getAccuracy());
                z2 = false;
            }
            float floatValue3 = k.c0.get().floatValue();
            if (floatValue3 <= 0.0f || (navLocation.hasSpeed() && navLocation.getSpeed() >= floatValue3)) {
                z = z2;
            } else {
                NavigationTrace.writeLineNavigation("SAVE_TRACK_PRECISION : Min Speed = " + floatValue3 + " | actual Speed = " + navLocation.getSpeed());
            }
        }
        if (!z) {
            StringBuilder a2 = h.a("Not Recorded");
            a2.append(navLocation.toString());
            NavigationTrace.writeLineNavigation(a2.toString());
            return;
        }
        insertData(navLocation.getLatitude(), navLocation.getLongitude(), navLocation.getAltitude(), navLocation.getSpeed(), navLocation.getAccuracy(), currentTimeMillis, k);
        this.h.getNotificationHelper().refreshNotification(a.EnumC0640a.GPX);
        NavigationTrace.writeLineNavigation("Update location Record : latitude = " + navLocation.getLatitude() + " | longitude : " + navLocation.getLongitude() + " | Speed : " + navLocation.getSpeed());
    }

    public void updatePointData(GPXUtilities.g gVar, double d, double d2, long j, String str, String str2, String str3, int i) {
        this.p.getModifiableGpxFile().modifiedTime = j;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Double.valueOf(d));
        arrayList.add(Double.valueOf(d2));
        arrayList.add(Long.valueOf(j));
        arrayList.add(str);
        arrayList.add(str2);
        arrayList.add(str3);
        arrayList.add(Integer.valueOf(i));
        arrayList.add(Double.valueOf(gVar.f12905a));
        arrayList.add(Double.valueOf(gVar.b));
        arrayList.add(Long.valueOf(gVar.h));
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE point SET lat=?, lon=?, date=?, description=?, pname=?, category=?, color=? WHERE lat=? AND lon=? AND date=?");
        String str4 = gVar.f;
        sb.append(" AND ");
        sb.append(POINT_COL_DESCRIPTION);
        if (str4 != null) {
            sb.append(org.apache.commons.codec.net.a.PREFIX);
            arrayList.add(gVar.f);
        } else {
            sb.append(" IS NULL");
        }
        String str5 = gVar.c;
        sb.append(" AND ");
        sb.append(POINT_COL_NAME);
        if (str5 != null) {
            sb.append(org.apache.commons.codec.net.a.PREFIX);
            arrayList.add(gVar.c);
        } else {
            sb.append(" IS NULL");
        }
        String str6 = gVar.e;
        sb.append(" AND ");
        sb.append(POINT_COL_CATEGORY);
        if (str6 != null) {
            sb.append(org.apache.commons.codec.net.a.PREFIX);
            arrayList.add(gVar.e);
        } else {
            sb.append(" IS NULL");
        }
        h(sb.toString(), arrayList.toArray());
        gVar.f12905a = d;
        gVar.b = d2;
        gVar.h = j;
        gVar.f = str;
        gVar.c = str2;
        gVar.e = str3;
        if (i != 0) {
            gVar.setColor(i);
        }
    }
}
