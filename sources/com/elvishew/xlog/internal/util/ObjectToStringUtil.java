package com.elvishew.xlog.internal.util;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import com.szabh.smable3.entity.BleNotificationSettings;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
/* loaded from: classes9.dex */
public class ObjectToStringUtil {
    public static void a(Bundle bundle, StringBuilder sb) {
        boolean z = true;
        for (String str : bundle.keySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(str);
            sb.append('=');
            Object obj = bundle.get(str);
            if (obj instanceof int[]) {
                sb.append(Arrays.toString((int[]) obj));
            } else if (obj instanceof byte[]) {
                sb.append(Arrays.toString((byte[]) obj));
            } else if (obj instanceof boolean[]) {
                sb.append(Arrays.toString((boolean[]) obj));
            } else if (obj instanceof short[]) {
                sb.append(Arrays.toString((short[]) obj));
            } else if (obj instanceof long[]) {
                sb.append(Arrays.toString((long[]) obj));
            } else if (obj instanceof float[]) {
                sb.append(Arrays.toString((float[]) obj));
            } else if (obj instanceof double[]) {
                sb.append(Arrays.toString((double[]) obj));
            } else if (obj instanceof String[]) {
                sb.append(Arrays.toString((String[]) obj));
            } else if (obj instanceof CharSequence[]) {
                sb.append(Arrays.toString((CharSequence[]) obj));
            } else if (obj instanceof Parcelable[]) {
                sb.append(Arrays.toString((Parcelable[]) obj));
            } else if (obj instanceof Bundle) {
                sb.append(bundleToString((Bundle) obj));
            } else {
                sb.append(obj);
            }
            z = false;
        }
    }

    public static void b(Intent intent, StringBuilder sb) {
        boolean z;
        Intent selector;
        String action = intent.getAction();
        boolean z2 = true;
        boolean z3 = false;
        if (action != null) {
            sb.append("act=");
            sb.append(action);
            z = false;
        } else {
            z = true;
        }
        Set<String> categories = intent.getCategories();
        if (categories != null) {
            if (!z) {
                sb.append(' ');
            }
            sb.append("cat=[");
            for (String str : categories) {
                if (!z2) {
                    sb.append(',');
                }
                sb.append(str);
                z2 = false;
            }
            sb.append("]");
            z = false;
        }
        Uri data = intent.getData();
        if (data != null) {
            if (!z) {
                sb.append(' ');
            }
            sb.append("dat=");
            if (Build.VERSION.SDK_INT >= 14) {
                sb.append(c(data));
            } else {
                String scheme = data.getScheme();
                if (scheme != null) {
                    if (scheme.equalsIgnoreCase(BleNotificationSettings.CALL)) {
                        sb.append("tel:xxx-xxx-xxxx");
                    } else if (scheme.equalsIgnoreCase("smsto")) {
                        sb.append("smsto:xxx-xxx-xxxx");
                    } else {
                        sb.append(data);
                    }
                } else {
                    sb.append(data);
                }
            }
            z = false;
        }
        String type = intent.getType();
        if (type != null) {
            if (!z) {
                sb.append(' ');
            }
            sb.append("typ=");
            sb.append(type);
            z = false;
        }
        int flags = intent.getFlags();
        if (flags != 0) {
            if (!z) {
                sb.append(' ');
            }
            sb.append("flg=0x");
            sb.append(Integer.toHexString(flags));
            z = false;
        }
        String str2 = intent.getPackage();
        if (str2 != null) {
            if (!z) {
                sb.append(' ');
            }
            sb.append("pkg=");
            sb.append(str2);
            z = false;
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            if (!z) {
                sb.append(' ');
            }
            sb.append("cmp=");
            sb.append(component.flattenToShortString());
            z = false;
        }
        Rect sourceBounds = intent.getSourceBounds();
        if (sourceBounds != null) {
            if (!z) {
                sb.append(' ');
            }
            sb.append("bnds=");
            sb.append(sourceBounds.toShortString());
            z = false;
        }
        int i = Build.VERSION.SDK_INT;
        if (i < 16 || intent.getClipData() == null) {
            z3 = z;
        } else {
            if (!z) {
                sb.append(' ');
            }
            sb.append("(has clip)");
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (!z3) {
                sb.append(' ');
            }
            sb.append("extras={");
            a(extras, sb);
            sb.append('}');
        }
        if (i < 15 || (selector = intent.getSelector()) == null) {
            return;
        }
        sb.append(" sel=");
        b(selector, sb);
        sb.append("}");
    }

    public static String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("Bundle[{");
        a(bundle, sb);
        sb.append("}]");
        return sb.toString();
    }

    public static String c(Uri uri) {
        if (Build.VERSION.SDK_INT >= 14) {
            try {
                Method declaredMethod = Uri.class.getDeclaredMethod("toSafeString", new Class[0]);
                declaredMethod.setAccessible(true);
                return (String) declaredMethod.invoke(uri, new Object[0]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return uri.toString();
    }

    public static String intentToString(Intent intent) {
        if (intent == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("Intent { ");
        b(intent, sb);
        sb.append(" }");
        return sb.toString();
    }
}