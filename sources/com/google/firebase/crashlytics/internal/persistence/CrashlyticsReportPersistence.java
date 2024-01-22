package com.google.firebase.crashlytics.internal.persistence;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes10.dex */
public class CrashlyticsReportPersistence {
    public static final Charset g = Charset.forName("UTF-8");
    public static final int h = 15;
    public static final CrashlyticsReportJsonTransform i = new CrashlyticsReportJsonTransform();
    public static final Comparator<? super File> j = new Comparator() { // from class: com.google.firebase.crashlytics.internal.persistence.e
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int w;
            w = CrashlyticsReportPersistence.w((File) obj, (File) obj2);
            return w;
        }
    };
    public static final FilenameFilter k = new FilenameFilter() { // from class: com.google.firebase.crashlytics.internal.persistence.d
        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            boolean x;
            x = CrashlyticsReportPersistence.x(file, str);
            return x;
        }
    };
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final AtomicInteger f11246a = new AtomicInteger(0);
    @NonNull
    public final File b;
    @NonNull
    public final File c;
    @NonNull
    public final File d;
    @NonNull
    public final File e;
    @NonNull
    public final SettingsDataProvider f;

    public CrashlyticsReportPersistence(@NonNull File file, @NonNull SettingsDataProvider settingsDataProvider) {
        File file2 = new File(file, "report-persistence");
        this.b = new File(file2, "sessions");
        this.c = new File(file2, "priority-reports");
        this.d = new File(file2, "reports");
        this.e = new File(file2, "native-reports");
        this.f = settingsDataProvider;
    }

    @NonNull
    public static File A(@NonNull File file) throws IOException {
        if (y(file)) {
            return file;
        }
        throw new IOException("Could not create directory " + file);
    }

    @NonNull
    public static String B(@NonNull File file) throws IOException {
        byte[] bArr = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            try {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    String str = new String(byteArrayOutputStream.toByteArray(), g);
                    fileInputStream.close();
                    return str;
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static void C(@Nullable File file) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                C(file2);
            }
        }
        file.delete();
    }

    @NonNull
    public static List<File> D(@NonNull List<File>... listArr) {
        for (List<File> list : listArr) {
            Collections.sort(list, j);
        }
        return j(listArr);
    }

    public static void E(@NonNull File file, @NonNull File file2, @NonNull CrashlyticsReport.FilesPayload filesPayload, @NonNull String str) {
        try {
            CrashlyticsReportJsonTransform crashlyticsReportJsonTransform = i;
            I(new File(A(file2), str), crashlyticsReportJsonTransform.reportToJson(crashlyticsReportJsonTransform.reportFromJson(B(file)).withNdkPayload(filesPayload)));
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.w("Could not synthesize final native report file for " + file, e);
        }
    }

    public static void G(@NonNull File file, @NonNull File file2, @NonNull List<CrashlyticsReport.Session.Event> list, long j2, boolean z, @Nullable String str) {
        try {
            CrashlyticsReportJsonTransform crashlyticsReportJsonTransform = i;
            CrashlyticsReport withEvents = crashlyticsReportJsonTransform.reportFromJson(B(file)).withSessionEndFields(j2, z, str).withEvents(ImmutableList.from(list));
            CrashlyticsReport.Session session = withEvents.getSession();
            if (session == null) {
                return;
            }
            I(new File(A(file2), session.getIdentifier()), crashlyticsReportJsonTransform.reportToJson(withEvents));
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.w("Could not synthesize final report file for " + file, e);
        }
    }

    public static int H(@NonNull File file, int i2) {
        List<File> q = q(file, new FilenameFilter() { // from class: com.google.firebase.crashlytics.internal.persistence.c
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str) {
                boolean t;
                t = CrashlyticsReportPersistence.t(file2, str);
                return t;
            }
        });
        Collections.sort(q, new Comparator() { // from class: com.google.firebase.crashlytics.internal.persistence.f
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int z;
                z = CrashlyticsReportPersistence.z((File) obj, (File) obj2);
                return z;
            }
        });
        return h(q, i2);
    }

    public static void I(File file, String str) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), g);
        try {
            outputStreamWriter.write(str);
            outputStreamWriter.close();
        } catch (Throwable th) {
            try {
                outputStreamWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static void J(File file, String str, long j2) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), g);
        try {
            outputStreamWriter.write(str);
            file.setLastModified(k(j2));
            outputStreamWriter.close();
        } catch (Throwable th) {
            try {
                outputStreamWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static int h(List<File> list, int i2) {
        int size = list.size();
        for (File file : list) {
            if (size <= i2) {
                return size;
            }
            C(file);
            size--;
        }
        return size;
    }

    @NonNull
    public static List<File> j(@NonNull List<File>... listArr) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (List<File> list : listArr) {
            i2 += list.size();
        }
        arrayList.ensureCapacity(i2);
        for (List<File> list2 : listArr) {
            arrayList.addAll(list2);
        }
        return arrayList;
    }

    public static long k(long j2) {
        return j2 * 1000;
    }

    @NonNull
    public static String l(int i2, boolean z) {
        String format = String.format(Locale.US, "%010d", Integer.valueOf(i2));
        String str = z ? "_" : "";
        return "event" + format + str;
    }

    @NonNull
    public static List<File> m(@NonNull File file) {
        return p(file, null);
    }

    @NonNull
    public static String o(@NonNull String str) {
        return str.substring(0, h);
    }

    @NonNull
    public static List<File> p(@NonNull File file, @Nullable FileFilter fileFilter) {
        if (!file.isDirectory()) {
            return Collections.emptyList();
        }
        File[] listFiles = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
        return listFiles != null ? Arrays.asList(listFiles) : Collections.emptyList();
    }

    @NonNull
    public static List<File> q(@NonNull File file, @Nullable FilenameFilter filenameFilter) {
        if (!file.isDirectory()) {
            return Collections.emptyList();
        }
        File[] listFiles = filenameFilter == null ? file.listFiles() : file.listFiles(filenameFilter);
        return listFiles != null ? Arrays.asList(listFiles) : Collections.emptyList();
    }

    public static boolean s(@NonNull String str) {
        return str.startsWith("event") && str.endsWith("_");
    }

    public static boolean t(@NonNull File file, @NonNull String str) {
        return str.startsWith("event") && !str.endsWith("_");
    }

    public static /* synthetic */ boolean u(String str, File file) {
        return file.isDirectory() && !file.getName().equals(str);
    }

    public static /* synthetic */ boolean v(String str, File file, String str2) {
        return str2.startsWith(str);
    }

    public static /* synthetic */ int w(File file, File file2) {
        return file2.getName().compareTo(file.getName());
    }

    public static /* synthetic */ boolean x(File file, String str) {
        return str.startsWith("event");
    }

    public static boolean y(@NonNull File file) {
        return file.exists() || file.mkdirs();
    }

    public static int z(@NonNull File file, @NonNull File file2) {
        return o(file.getName()).compareTo(o(file2.getName()));
    }

    public final void F(@NonNull File file, long j2) {
        boolean z;
        List<File> q = q(file, k);
        if (q.isEmpty()) {
            Logger.getLogger().v("Session " + file.getName() + " has no events.");
            return;
        }
        Collections.sort(q);
        ArrayList arrayList = new ArrayList();
        loop0: while (true) {
            z = false;
            for (File file2 : q) {
                try {
                    arrayList.add(i.eventFromJson(B(file2)));
                } catch (IOException e) {
                    Logger.getLogger().w("Could not add event to report for " + file2, e);
                }
                if (z || s(file2.getName())) {
                    z = true;
                }
            }
        }
        if (arrayList.isEmpty()) {
            Logger.getLogger().w("Could not parse event files for session " + file.getName());
            return;
        }
        String str = null;
        File file3 = new File(file, "user");
        if (file3.isFile()) {
            try {
                str = B(file3);
            } catch (IOException e2) {
                Logger.getLogger().w("Could not read user ID file in " + file.getName(), e2);
            }
        }
        G(new File(file, "report"), z ? this.c : this.d, arrayList, j2, z, str);
    }

    public void deleteAllReports() {
        for (File file : n()) {
            file.delete();
        }
    }

    public void deleteFinalizedReport(final String str) {
        FilenameFilter filenameFilter = new FilenameFilter() { // from class: com.google.firebase.crashlytics.internal.persistence.b
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str2) {
                boolean v;
                v = CrashlyticsReportPersistence.v(str, file, str2);
                return v;
            }
        };
        for (File file : j(q(this.c, filenameFilter), q(this.e, filenameFilter), q(this.d, filenameFilter))) {
            file.delete();
        }
    }

    public void finalizeReports(@Nullable String str, long j2) {
        for (File file : g(str)) {
            Logger logger = Logger.getLogger();
            logger.v("Finalizing report for session " + file.getName());
            F(file, j2);
            C(file);
        }
        i();
    }

    public void finalizeSessionWithNativeEvent(@NonNull String str, @NonNull CrashlyticsReport.FilesPayload filesPayload) {
        E(new File(r(str), "report"), this.e, filesPayload, str);
    }

    @NonNull
    public final List<File> g(@Nullable final String str) {
        List<File> p = p(this.b, new FileFilter() { // from class: com.google.firebase.crashlytics.internal.persistence.a
            @Override // java.io.FileFilter
            public final boolean accept(File file) {
                boolean u;
                u = CrashlyticsReportPersistence.u(str, file);
                return u;
            }
        });
        Collections.sort(p, j);
        if (p.size() <= 8) {
            return p;
        }
        for (File file : p.subList(8, p.size())) {
            C(file);
        }
        return p.subList(0, 8);
    }

    public long getStartTimestampMillis(String str) {
        return new File(r(str), "start-time").lastModified();
    }

    public boolean hasFinalizedReports() {
        return !n().isEmpty();
    }

    public final void i() {
        int i2 = this.f.getSettings().getSessionData().maxCompleteSessionsCount;
        List<File> n = n();
        int size = n.size();
        if (size <= i2) {
            return;
        }
        for (File file : n.subList(i2, size)) {
            file.delete();
        }
    }

    @NonNull
    public List<String> listSortedOpenSessionIds() {
        List<File> m = m(this.b);
        Collections.sort(m, j);
        ArrayList arrayList = new ArrayList();
        for (File file : m) {
            arrayList.add(file.getName());
        }
        return arrayList;
    }

    @NonNull
    public List<CrashlyticsReportWithSessionId> loadFinalizedReports() {
        List<File> n = n();
        ArrayList arrayList = new ArrayList();
        arrayList.ensureCapacity(n.size());
        for (File file : n()) {
            try {
                arrayList.add(CrashlyticsReportWithSessionId.create(i.reportFromJson(B(file)), file.getName()));
            } catch (IOException e) {
                Logger logger = Logger.getLogger();
                logger.w("Could not load report file " + file + "; deleting", e);
                file.delete();
            }
        }
        return arrayList;
    }

    @NonNull
    public final List<File> n() {
        return D(j(m(this.c), m(this.e)), m(this.d));
    }

    public void persistEvent(@NonNull CrashlyticsReport.Session.Event event, @NonNull String str) {
        persistEvent(event, str, false);
    }

    public void persistReport(@NonNull CrashlyticsReport crashlyticsReport) {
        CrashlyticsReport.Session session = crashlyticsReport.getSession();
        if (session == null) {
            Logger.getLogger().d("Could not get session for report");
            return;
        }
        String identifier = session.getIdentifier();
        try {
            File A = A(r(identifier));
            I(new File(A, "report"), i.reportToJson(crashlyticsReport));
            J(new File(A, "start-time"), "", session.getStartedAt());
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.d("Could not persist report for session " + identifier, e);
        }
    }

    public void persistUserIdForSession(@NonNull String str, @NonNull String str2) {
        try {
            I(new File(r(str2), "user"), str);
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.w("Could not persist user ID for session " + str2, e);
        }
    }

    @NonNull
    public final File r(@NonNull String str) {
        return new File(this.b, str);
    }

    public void persistEvent(@NonNull CrashlyticsReport.Session.Event event, @NonNull String str, boolean z) {
        int i2 = this.f.getSettings().getSessionData().maxCustomExceptionEvents;
        File r = r(str);
        try {
            I(new File(r, l(this.f11246a.getAndIncrement(), z)), i.eventToJson(event));
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.w("Could not persist event for session " + str, e);
        }
        H(r, i2);
    }
}
