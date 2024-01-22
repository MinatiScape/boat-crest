package com.mappls.sdk.navigation.gpx;

import androidx.annotation.NonNull;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.c;
import com.mappls.sdk.navigation.g;
import com.mappls.sdk.navigation.gpx.GPXUtilities;
import com.mappls.sdk.navigation.t;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class GpxSelectionHelper {

    /* renamed from: a  reason: collision with root package name */
    public NavigationApplication f12906a;
    @NonNull
    public List<SelectedGpxFile> b = new ArrayList();
    public SavingTrackHelper c;

    /* loaded from: classes11.dex */
    public static class SelectedGpxFile {

        /* renamed from: a  reason: collision with root package name */
        public boolean f12907a;
        public GPXUtilities.GPXFile b;
        public int c;
        public GPXUtilities.GPXTrackAnalysis d;
        public boolean g;
        public List<a> h;
        public boolean notShowNavigationDialog = false;
        public long e = -1;
        public List<GPXUtilities.f> f = new ArrayList();

        public final void b() {
            GPXUtilities.GPXFile gPXFile = this.b;
            this.e = gPXFile.modifiedTime;
            this.d = gPXFile.getAnalysis(com.mappls.sdk.navigation.util.a.a(gPXFile.path) ? System.currentTimeMillis() : new File(this.b.path).lastModified());
            this.h = null;
        }

        public int getColor() {
            return this.c;
        }

        public List<a> getDisplayGroups() {
            if (this.e != this.b.modifiedTime) {
                b();
            }
            return this.h;
        }

        public GPXUtilities.GPXFile getGpxFile() {
            return this.b;
        }

        public GPXUtilities.GPXFile getModifiableGpxFile() {
            return this.b;
        }

        public List<GPXUtilities.f> getModifiablePointsToDisplay() {
            return this.f;
        }

        public List<GPXUtilities.f> getPointsToDisplay() {
            return this.f;
        }

        public GPXUtilities.GPXTrackAnalysis getTrackAnalysis() {
            if (this.e != this.b.modifiedTime) {
                b();
            }
            return this.d;
        }

        public boolean isRoutePoints() {
            return this.g;
        }

        public boolean isShowCurrentTrack() {
            return this.f12907a;
        }

        public void processPoints() {
            b();
            List<GPXUtilities.f> proccessPoints = this.b.proccessPoints();
            this.f = proccessPoints;
            if (proccessPoints.isEmpty()) {
                List<GPXUtilities.f> processRoutePoints = this.b.processRoutePoints();
                this.f = processRoutePoints;
                this.g = !processRoutePoints.isEmpty();
            }
        }

        public void setDisplayGroups(List<a> list) {
            if (this.e != this.b.modifiedTime) {
                b();
            }
            this.h = list;
        }

        public void setGpxFile(GPXUtilities.GPXFile gPXFile) {
            this.b = gPXFile;
            if (gPXFile.tracks.size() > 0) {
                this.c = gPXFile.tracks.get(0).getColor(0);
            }
            processPoints();
        }

        public void setShowCurrentTrack(boolean z) {
            this.f12907a = z;
        }
    }

    /* loaded from: classes11.dex */
    public static class a {
        public GPXUtilities.Track b;

        /* renamed from: a  reason: collision with root package name */
        public ArrayList f12908a = new ArrayList();
        public double c = -1.0d;
        public int d = -1;

        public final ArrayList a() {
            return this.f12908a;
        }

        public final void a(GPXUtilities.Track track) {
            this.b = track;
        }

        public final double b() {
            return this.c;
        }

        public final boolean c() {
            return this.c > 0.0d;
        }

        public final boolean d() {
            return this.d > 0;
        }
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public String f12909a;
    }

    public GpxSelectionHelper(NavigationApplication navigationApplication, SavingTrackHelper savingTrackHelper) {
        this.f12906a = navigationApplication;
        this.c = savingTrackHelper;
    }

    public static String a(double d, a aVar, NavigationApplication navigationApplication) {
        return aVar.c() ? com.mappls.sdk.navigation.util.a.a((int) d, navigationApplication.a()) : NavigationFormatter.getFormattedDistance((float) d, navigationApplication);
    }

    public static String b(double d, a aVar, NavigationApplication navigationApplication) {
        if (aVar.c()) {
            if (((t.s) navigationApplication.k().y0.get()) == t.s.KILOMETERS_AND_METERS) {
                double b2 = aVar.b();
                int i = 2;
                int i2 = b2 < 100.0d ? 2 : b2 < 1000.0d ? 1 : 0;
                int i3 = (int) (0.5d + d);
                if (i3 % 1000 > 1 && i2 < 1) {
                    i2 = 1;
                }
                if (i3 % 100 <= 1 || i2 >= 2) {
                    i = i2;
                }
                return NavigationFormatter.getFormattedRoundDistanceKm((float) d, i, navigationApplication);
            }
            return NavigationFormatter.getFormattedDistance((float) d, navigationApplication);
        }
        return com.mappls.sdk.navigation.util.a.a((int) d, navigationApplication.a());
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0110, code lost:
        if (r13.timeMoving > 0) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void d(com.mappls.sdk.navigation.NavigationApplication r23, com.mappls.sdk.navigation.gpx.GpxSelectionHelper.a r24) {
        /*
            Method dump skipped, instructions count: 538
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.gpx.GpxSelectionHelper.d(com.mappls.sdk.navigation.NavigationApplication, com.mappls.sdk.navigation.gpx.GpxSelectionHelper$a):void");
    }

    public final String c(int i, Object... objArr) {
        return this.f12906a.getString(i, objArr);
    }

    public void clearAllGpxFileToShow() {
        this.b.clear();
        e();
    }

    public List<a> collectDisplayGroups(GPXUtilities.GPXFile gPXFile) {
        String replace;
        ArrayList arrayList = new ArrayList();
        String str = gPXFile.path;
        if (gPXFile.showCurrentTrack) {
            replace = c(R.string.mappls_shared_string_currently_recording_track, new Object[0]);
        } else {
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf >= 0) {
                str = str.substring(lastIndexOf + 1);
            }
            int lastIndexOf2 = str.lastIndexOf(92);
            if (lastIndexOf2 >= 0) {
                str = str.substring(lastIndexOf2 + 1);
            }
            if (str.toLowerCase().endsWith(".gpx")) {
                str = str.substring(0, str.length() - 4);
            }
            replace = str.replace('_', ' ');
        }
        int i = 2;
        if (gPXFile.tracks.size() > 0) {
            int i2 = 1;
            for (GPXUtilities.Track track : gPXFile.tracks) {
                a aVar = new a();
                track.getColor(gPXFile.getColor(0));
                aVar.a(track);
                StringBuilder sb = new StringBuilder();
                int i3 = i2 + 1;
                sb.append(i2);
                sb.append("");
                String sb2 = sb.toString();
                int i4 = R.string.mappls_gpx_selection_track;
                Object[] objArr = new Object[2];
                objArr[0] = replace;
                if (gPXFile.tracks.size() == 1) {
                    sb2 = "";
                }
                objArr[1] = sb2;
                c(i4, objArr);
                arrayList.add(aVar);
                d(this.f12906a, aVar);
                i2 = i3;
            }
        }
        if (gPXFile.routes.size() > 0) {
            int i5 = 0;
            for (GPXUtilities.Route route : gPXFile.routes) {
                a aVar2 = new a();
                int i6 = R.string.mappls_gpx_selection_number_of_points;
                Object[] objArr2 = new Object[i];
                objArr2[0] = replace;
                objArr2[1] = Integer.valueOf(route.points.size());
                c(i6, objArr2);
                StringBuilder sb3 = new StringBuilder();
                int i7 = i5 + 1;
                sb3.append(i5);
                sb3.append("");
                String sb4 = sb3.toString();
                int i8 = R.string.mappls_gpx_selection_route_points;
                Object[] objArr3 = new Object[i];
                objArr3[0] = replace;
                if (gPXFile.routes.size() == 1) {
                    sb4 = "";
                }
                objArr3[1] = sb4;
                c(i8, objArr3);
                arrayList.add(aVar2);
                ArrayList a2 = aVar2.a();
                int i9 = 0;
                for (GPXUtilities.g gVar : route.points) {
                    b bVar = new b();
                    String str2 = gVar.f;
                    i9++;
                    if (com.mappls.sdk.navigation.util.a.a(gVar.c)) {
                        c(R.string.mappls_gpx_selection_point, i9 + "");
                    }
                    a2.add(bVar);
                    i = 2;
                }
                i5 = i7;
            }
        }
        if (gPXFile.points.size() > 0) {
            a aVar3 = new a();
            c(R.string.mappls_gpx_selection_number_of_points, Integer.valueOf(gPXFile.points.size()));
            c(R.string.mappls_gpx_selection_points, replace);
            arrayList.add(aVar3);
            ArrayList a3 = aVar3.a();
            int i10 = 0;
            for (GPXUtilities.g gVar2 : gPXFile.points) {
                b bVar2 = new b();
                String str3 = gVar2.f;
                i10++;
                if (com.mappls.sdk.navigation.util.a.a(gVar2.c)) {
                    c(R.string.mappls_gpx_selection_point, i10 + "");
                }
                a3.add(bVar2);
            }
        }
        return arrayList;
    }

    public final void e() {
        JSONArray jSONArray = new JSONArray();
        for (SelectedGpxFile selectedGpxFile : this.b) {
            if (selectedGpxFile.b != null && !selectedGpxFile.notShowNavigationDialog) {
                JSONObject jSONObject = new JSONObject();
                try {
                    if (selectedGpxFile.isShowCurrentTrack()) {
                        jSONObject.put("currentTrack", true);
                    } else if (!com.mappls.sdk.navigation.util.a.a(selectedGpxFile.b.path)) {
                        jSONObject.put("file", selectedGpxFile.b.path);
                        if (selectedGpxFile.b.getColor(0) != 0) {
                            jSONObject.put("color", com.mappls.sdk.navigation.util.a.a(selectedGpxFile.b.getColor(0)));
                        }
                    }
                } catch (JSONException e) {
                    NavigationLogger.d(e);
                }
                jSONArray.put(jSONObject);
            }
        }
        this.f12906a.k().e0.set(jSONArray.toString());
    }

    public final SelectedGpxFile f(GPXUtilities.GPXFile gPXFile, boolean z, boolean z2) {
        SelectedGpxFile selectedGpxFile;
        boolean z3;
        if (gPXFile == null || !gPXFile.showCurrentTrack) {
            SelectedGpxFile selectedFileByPath = getSelectedFileByPath(gPXFile.path);
            boolean z4 = selectedFileByPath != null;
            if (z && selectedFileByPath == null) {
                selectedFileByPath = new SelectedGpxFile();
                selectedFileByPath.setGpxFile(gPXFile);
                selectedFileByPath.notShowNavigationDialog = z2;
            }
            selectedGpxFile = selectedFileByPath;
            z3 = z4;
        } else {
            selectedGpxFile = this.c.getCurrentTrack();
            selectedGpxFile.notShowNavigationDialog = z2;
            z3 = this.b.contains(selectedGpxFile);
        }
        if (z3 != z) {
            if (z) {
                this.b.add(selectedGpxFile);
            } else {
                this.b.remove(selectedGpxFile);
            }
        }
        return selectedGpxFile;
    }

    public String getGpxDescription() {
        if (this.b.size() != 1) {
            if (this.b.size() == 0) {
                return null;
            }
            return this.f12906a.getResources().getString(R.string.mappls_number_of_gpx_files_selected_pattern, Integer.valueOf(this.b.size()));
        } else if (this.b.get(0).getGpxFile() == this.f12906a.j().getCurrentGpx()) {
            return this.f12906a.getResources().getString(R.string.mappls_current_track);
        } else {
            String name = new File(this.b.get(0).getGpxFile().path).getName();
            int indexOf = name.indexOf(46);
            return indexOf >= 0 ? name.substring(0, indexOf) : name;
        }
    }

    public SelectedGpxFile getSelectedCurrentRecordingTrack() {
        for (SelectedGpxFile selectedGpxFile : this.b) {
            if (selectedGpxFile.isShowCurrentTrack()) {
                return selectedGpxFile;
            }
        }
        return null;
    }

    public SelectedGpxFile getSelectedFileByName(String str) {
        for (SelectedGpxFile selectedGpxFile : this.b) {
            String str2 = selectedGpxFile.getGpxFile().path;
            if (str2.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR + str)) {
                return selectedGpxFile;
            }
        }
        return null;
    }

    public SelectedGpxFile getSelectedFileByPath(String str) {
        for (SelectedGpxFile selectedGpxFile : this.b) {
            if (selectedGpxFile.getGpxFile().path.equals(str)) {
                return selectedGpxFile;
            }
        }
        return null;
    }

    public SelectedGpxFile getSelectedGPXFile(GPXUtilities.g gVar) {
        for (SelectedGpxFile selectedGpxFile : this.b) {
            if (selectedGpxFile.getGpxFile().points.contains(gVar)) {
                return selectedGpxFile;
            }
        }
        return null;
    }

    @NonNull
    public List<SelectedGpxFile> getSelectedGPXFiles() {
        return this.b;
    }

    public boolean isShowingAnyGpxFiles() {
        return !this.b.isEmpty();
    }

    public void loadGPXTracks(g gVar) {
        String str = (String) this.f12906a.k().e0.get();
        if (com.mappls.sdk.navigation.util.a.a(str)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            boolean z = false;
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("file")) {
                    File file = new File(jSONObject.getString("file"));
                    if (gVar != null) {
                        c(R.string.mappls_loading_smth, file.getName());
                        ((c) gVar).a(c.d.f12885a);
                    }
                    GPXUtilities.GPXFile loadGPXFile = GPXUtilities.loadGPXFile(this.f12906a, file);
                    if (jSONObject.has("color")) {
                        loadGPXFile.setColor(com.mappls.sdk.navigation.util.a.b(jSONObject.getString("color")));
                    }
                    if (loadGPXFile.warning != null) {
                        z = true;
                    } else {
                        selectGpxFile(loadGPXFile, true, false);
                    }
                } else if (jSONObject.has("currentTrack")) {
                    this.b.add(this.c.getCurrentTrack());
                }
            }
            if (z) {
                e();
            }
        } catch (Exception e) {
            this.f12906a.k().e0.set("");
            NavigationLogger.d(e);
        }
    }

    public SelectedGpxFile selectGpxFile(GPXUtilities.GPXFile gPXFile, boolean z, boolean z2) {
        SelectedGpxFile f = f(gPXFile, z, z2);
        e();
        return f;
    }

    public void setGpxFileToDisplay(GPXUtilities.GPXFile... gPXFileArr) {
        for (GPXUtilities.GPXFile gPXFile : gPXFileArr) {
            f(gPXFile, true, false);
        }
        e();
    }
}
