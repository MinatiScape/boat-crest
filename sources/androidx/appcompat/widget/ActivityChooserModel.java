package androidx.appcompat.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import androidx.core.app.NotificationCompat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public class ActivityChooserModel extends DataSetObservable {
    public static final String n = ActivityChooserModel.class.getSimpleName();
    public static final Object o = new Object();
    public static final Map<String, ActivityChooserModel> p = new HashMap();
    public final Context d;
    public final String e;
    public Intent f;
    public OnChooseActivityListener m;

    /* renamed from: a  reason: collision with root package name */
    public final Object f435a = new Object();
    public final List<ActivityResolveInfo> b = new ArrayList();
    public final List<HistoricalRecord> c = new ArrayList();
    public ActivitySorter g = new a();
    public int h = 50;
    public boolean i = true;
    public boolean j = false;
    public boolean k = true;
    public boolean l = false;

    /* loaded from: classes.dex */
    public interface ActivityChooserModelClient {
        void setActivityChooserModel(ActivityChooserModel activityChooserModel);
    }

    /* loaded from: classes.dex */
    public static final class ActivityResolveInfo implements Comparable<ActivityResolveInfo> {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo resolveInfo) {
            this.resolveInfo = resolveInfo;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && ActivityResolveInfo.class == obj.getClass() && Float.floatToIntBits(this.weight) == Float.floatToIntBits(((ActivityResolveInfo) obj).weight);
        }

        public int hashCode() {
            return Float.floatToIntBits(this.weight) + 31;
        }

        public String toString() {
            return "[resolveInfo:" + this.resolveInfo.toString() + "; weight:" + new BigDecimal(this.weight) + "]";
        }

        @Override // java.lang.Comparable
        public int compareTo(ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits(activityResolveInfo.weight) - Float.floatToIntBits(this.weight);
        }
    }

    /* loaded from: classes.dex */
    public interface ActivitySorter {
        void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2);
    }

    /* loaded from: classes.dex */
    public static final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && HistoricalRecord.class == obj.getClass()) {
                HistoricalRecord historicalRecord = (HistoricalRecord) obj;
                ComponentName componentName = this.activity;
                if (componentName == null) {
                    if (historicalRecord.activity != null) {
                        return false;
                    }
                } else if (!componentName.equals(historicalRecord.activity)) {
                    return false;
                }
                return this.time == historicalRecord.time && Float.floatToIntBits(this.weight) == Float.floatToIntBits(historicalRecord.weight);
            }
            return false;
        }

        public int hashCode() {
            ComponentName componentName = this.activity;
            int hashCode = componentName == null ? 0 : componentName.hashCode();
            long j = this.time;
            return ((((hashCode + 31) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Float.floatToIntBits(this.weight);
        }

        public String toString() {
            return "[; activity:" + this.activity + "; time:" + this.time + "; weight:" + new BigDecimal(this.weight) + "]";
        }

        public HistoricalRecord(ComponentName componentName, long j, float f) {
            this.activity = componentName;
            this.time = j;
            this.weight = f;
        }
    }

    /* loaded from: classes.dex */
    public interface OnChooseActivityListener {
        boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent);
    }

    /* loaded from: classes.dex */
    public static final class a implements ActivitySorter {

        /* renamed from: a  reason: collision with root package name */
        public final Map<ComponentName, ActivityResolveInfo> f436a = new HashMap();

        @Override // androidx.appcompat.widget.ActivityChooserModel.ActivitySorter
        public void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2) {
            Map<ComponentName, ActivityResolveInfo> map = this.f436a;
            map.clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ActivityResolveInfo activityResolveInfo = list.get(i);
                activityResolveInfo.weight = 0.0f;
                ActivityInfo activityInfo = activityResolveInfo.resolveInfo.activityInfo;
                map.put(new ComponentName(activityInfo.packageName, activityInfo.name), activityResolveInfo);
            }
            float f = 1.0f;
            for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
                HistoricalRecord historicalRecord = list2.get(size2);
                ActivityResolveInfo activityResolveInfo2 = map.get(historicalRecord.activity);
                if (activityResolveInfo2 != null) {
                    activityResolveInfo2.weight += historicalRecord.weight * f;
                    f *= 0.95f;
                }
            }
            Collections.sort(list);
        }
    }

    /* loaded from: classes.dex */
    public final class b extends AsyncTask<Object, Void, Void> {
        public b() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x006b, code lost:
            if (r15 != null) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x006d, code lost:
            r15.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0090, code lost:
            if (r15 == null) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00b0, code lost:
            if (r15 == null) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x00d0, code lost:
            if (r15 == null) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x00d3, code lost:
            return null;
         */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Void doInBackground(java.lang.Object... r15) {
            /*
                Method dump skipped, instructions count: 244
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActivityChooserModel.b.doInBackground(java.lang.Object[]):java.lang.Void");
        }
    }

    public ActivityChooserModel(Context context, String str) {
        this.d = context.getApplicationContext();
        if (!TextUtils.isEmpty(str) && !str.endsWith(".xml")) {
            this.e = str + ".xml";
            return;
        }
        this.e = str;
    }

    public static ActivityChooserModel d(Context context, String str) {
        ActivityChooserModel activityChooserModel;
        synchronized (o) {
            Map<String, ActivityChooserModel> map = p;
            activityChooserModel = map.get(str);
            if (activityChooserModel == null) {
                activityChooserModel = new ActivityChooserModel(context, str);
                map.put(str, activityChooserModel);
            }
        }
        return activityChooserModel;
    }

    public final boolean a(HistoricalRecord historicalRecord) {
        boolean add = this.c.add(historicalRecord);
        if (add) {
            this.k = true;
            l();
            k();
            r();
            notifyChanged();
        }
        return add;
    }

    public Intent b(int i) {
        synchronized (this.f435a) {
            if (this.f == null) {
                return null;
            }
            c();
            ActivityInfo activityInfo = this.b.get(i).resolveInfo.activityInfo;
            ComponentName componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
            Intent intent = new Intent(this.f);
            intent.setComponent(componentName);
            if (this.m != null) {
                if (this.m.onChooseActivity(this, new Intent(intent))) {
                    return null;
                }
            }
            a(new HistoricalRecord(componentName, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }

    public final void c() {
        boolean j = j() | m();
        l();
        if (j) {
            r();
            notifyChanged();
        }
    }

    public ResolveInfo e(int i) {
        ResolveInfo resolveInfo;
        synchronized (this.f435a) {
            c();
            resolveInfo = this.b.get(i).resolveInfo;
        }
        return resolveInfo;
    }

    public int f() {
        int size;
        synchronized (this.f435a) {
            c();
            size = this.b.size();
        }
        return size;
    }

    public int g(ResolveInfo resolveInfo) {
        synchronized (this.f435a) {
            c();
            List<ActivityResolveInfo> list = this.b;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).resolveInfo == resolveInfo) {
                    return i;
                }
            }
            return -1;
        }
    }

    public ResolveInfo h() {
        synchronized (this.f435a) {
            c();
            if (this.b.isEmpty()) {
                return null;
            }
            return this.b.get(0).resolveInfo;
        }
    }

    public int i() {
        int size;
        synchronized (this.f435a) {
            c();
            size = this.c.size();
        }
        return size;
    }

    public final boolean j() {
        if (!this.l || this.f == null) {
            return false;
        }
        this.l = false;
        this.b.clear();
        List<ResolveInfo> queryIntentActivities = this.d.getPackageManager().queryIntentActivities(this.f, 0);
        int size = queryIntentActivities.size();
        for (int i = 0; i < size; i++) {
            this.b.add(new ActivityResolveInfo(queryIntentActivities.get(i)));
        }
        return true;
    }

    public final void k() {
        if (this.j) {
            if (this.k) {
                this.k = false;
                if (TextUtils.isEmpty(this.e)) {
                    return;
                }
                new b().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ArrayList(this.c), this.e);
                return;
            }
            return;
        }
        throw new IllegalStateException("No preceding call to #readHistoricalData");
    }

    public final void l() {
        int size = this.c.size() - this.h;
        if (size <= 0) {
            return;
        }
        this.k = true;
        for (int i = 0; i < size; i++) {
            this.c.remove(0);
        }
    }

    public final boolean m() {
        if (this.i && this.k && !TextUtils.isEmpty(this.e)) {
            this.i = false;
            this.j = true;
            n();
            return true;
        }
        return false;
    }

    public final void n() {
        XmlPullParser newPullParser;
        try {
            FileInputStream openFileInput = this.d.openFileInput(this.e);
            try {
                try {
                    try {
                        newPullParser = Xml.newPullParser();
                        newPullParser.setInput(openFileInput, "UTF-8");
                        for (int i = 0; i != 1 && i != 2; i = newPullParser.next()) {
                        }
                    } catch (IOException e) {
                        String str = n;
                        Log.e(str, "Error reading historical recrod file: " + this.e, e);
                        if (openFileInput == null) {
                            return;
                        }
                    }
                } catch (XmlPullParserException e2) {
                    String str2 = n;
                    Log.e(str2, "Error reading historical recrod file: " + this.e, e2);
                    if (openFileInput == null) {
                        return;
                    }
                }
                if ("historical-records".equals(newPullParser.getName())) {
                    List<HistoricalRecord> list = this.c;
                    list.clear();
                    while (true) {
                        int next = newPullParser.next();
                        if (next == 1) {
                            if (openFileInput == null) {
                                return;
                            }
                        } else if (next != 3 && next != 4) {
                            if ("historical-record".equals(newPullParser.getName())) {
                                list.add(new HistoricalRecord(newPullParser.getAttributeValue(null, "activity"), Long.parseLong(newPullParser.getAttributeValue(null, NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)), Float.parseFloat(newPullParser.getAttributeValue(null, "weight"))));
                            } else {
                                throw new XmlPullParserException("Share records file not well-formed.");
                            }
                        }
                    }
                    try {
                        openFileInput.close();
                    } catch (IOException unused) {
                    }
                } else {
                    throw new XmlPullParserException("Share records file does not start with historical-records tag.");
                }
            } catch (Throwable th) {
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused3) {
        }
    }

    public void o(int i) {
        synchronized (this.f435a) {
            c();
            ActivityResolveInfo activityResolveInfo = this.b.get(i);
            ActivityResolveInfo activityResolveInfo2 = this.b.get(0);
            float f = activityResolveInfo2 != null ? (activityResolveInfo2.weight - activityResolveInfo.weight) + 5.0f : 1.0f;
            ActivityInfo activityInfo = activityResolveInfo.resolveInfo.activityInfo;
            a(new HistoricalRecord(new ComponentName(activityInfo.packageName, activityInfo.name), System.currentTimeMillis(), f));
        }
    }

    public void p(Intent intent) {
        synchronized (this.f435a) {
            if (this.f == intent) {
                return;
            }
            this.f = intent;
            this.l = true;
            c();
        }
    }

    public void q(OnChooseActivityListener onChooseActivityListener) {
        synchronized (this.f435a) {
            this.m = onChooseActivityListener;
        }
    }

    public final boolean r() {
        if (this.g == null || this.f == null || this.b.isEmpty() || this.c.isEmpty()) {
            return false;
        }
        this.g.sort(this.f, this.b, Collections.unmodifiableList(this.c));
        return true;
    }
}
