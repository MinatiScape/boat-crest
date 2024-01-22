package androidx.core.content.pm;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class ShortcutXmlParser {

    /* renamed from: a  reason: collision with root package name */
    public static volatile ArrayList<String> f1017a;
    public static final Object b = new Object();

    public static String a(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str);
        return attributeValue == null ? xmlPullParser.getAttributeValue(null, str) : attributeValue;
    }

    @NonNull
    public static XmlResourceParser b(Context context, ActivityInfo activityInfo) {
        XmlResourceParser loadXmlMetaData = activityInfo.loadXmlMetaData(context.getPackageManager(), "android.app.shortcuts");
        if (loadXmlMetaData != null) {
            return loadXmlMetaData;
        }
        throw new IllegalArgumentException("Failed to open android.app.shortcuts meta-data resource of " + activityInfo.name);
    }

    @NonNull
    public static Set<String> c(@NonNull Context context) {
        HashSet hashSet = new HashSet();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 128);
        if (queryIntentActivities != null && queryIntentActivities.size() != 0) {
            try {
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    ActivityInfo activityInfo = resolveInfo.activityInfo;
                    Bundle bundle = activityInfo.metaData;
                    if (bundle != null && bundle.containsKey("android.app.shortcuts")) {
                        XmlResourceParser b2 = b(context, activityInfo);
                        hashSet.addAll(parseShortcutIds(b2));
                        if (b2 != null) {
                            b2.close();
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("ShortcutXmlParser", "Failed to parse the Xml resource: ", e);
            }
        }
        return hashSet;
    }

    @NonNull
    @WorkerThread
    public static List<String> getShortcutIds(@NonNull Context context) {
        if (f1017a == null) {
            synchronized (b) {
                if (f1017a == null) {
                    f1017a = new ArrayList<>();
                    f1017a.addAll(c(context));
                }
            }
        }
        return f1017a;
    }

    @NonNull
    @VisibleForTesting
    public static List<String> parseShortcutIds(@NonNull XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String a2;
        ArrayList arrayList = new ArrayList(1);
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1 || (next == 3 && xmlPullParser.getDepth() <= 0)) {
                break;
            }
            int depth = xmlPullParser.getDepth();
            String name = xmlPullParser.getName();
            if (next == 2 && depth == 2 && "shortcut".equals(name) && (a2 = a(xmlPullParser, "shortcutId")) != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }
}
