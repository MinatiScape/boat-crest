package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.clevertap.android.sdk.Constants;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kotlin.text.Typography;
/* loaded from: classes8.dex */
public final class x0 {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f9621a;

    static {
        char[] cArr = new char[80];
        f9621a = cArr;
        Arrays.fill(cArr, ' ');
    }

    public static String a(zzfo zzfoVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        d(zzfoVar, sb, 0);
        return sb.toString();
    }

    public static void b(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                b(sb, i, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                b(sb, i, str, entry);
            }
        } else {
            sb.append('\n');
            c(i, sb);
            if (!str.isEmpty()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Character.toLowerCase(str.charAt(0)));
                for (int i2 = 1; i2 < str.length(); i2++) {
                    char charAt = str.charAt(i2);
                    if (Character.isUpperCase(charAt)) {
                        sb2.append("_");
                    }
                    sb2.append(Character.toLowerCase(charAt));
                }
                str = sb2.toString();
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(x1.a(new u(((String) obj).getBytes(zzem.f9632a))));
                sb.append(Typography.quote);
            } else if (obj instanceof zzdb) {
                sb.append(": \"");
                sb.append(x1.a((zzdb) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzed) {
                sb.append(" {");
                d((zzed) obj, sb, i + 2);
                sb.append("\n");
                c(i, sb);
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int i3 = i + 2;
                b(sb, i3, Constants.KEY_KEY, entry2.getKey());
                b(sb, i3, "value", entry2.getValue());
                sb.append("\n");
                c(i, sb);
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj);
            }
        }
    }

    public static void c(int i, StringBuilder sb) {
        while (i > 0) {
            int i2 = 80;
            if (i <= 80) {
                i2 = i;
            }
            sb.append(f9621a, 0, i2);
            i -= i2;
        }
    }

    public static void d(zzfo zzfoVar, StringBuilder sb, int i) {
        int i2;
        boolean equals;
        Method method;
        Method method2;
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zzfoVar.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i3 = 0;
        while (true) {
            i2 = 3;
            if (i3 >= length) {
                break;
            }
            Method method3 = declaredMethods[i3];
            if (!Modifier.isStatic(method3.getModifiers()) && method3.getName().length() >= 3) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && method3.getParameterTypes().length == 0) {
                    if (method3.getName().startsWith("has")) {
                        hashMap.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith("get")) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i3++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String substring = ((String) entry.getKey()).substring(i2);
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List") && (method2 = (Method) entry.getValue()) != null && method2.getReturnType().equals(List.class)) {
                b(sb, i, substring.substring(0, substring.length() - 4), zzed.e(method2, zzfoVar, new Object[0]));
            } else if (substring.endsWith("Map") && !substring.equals("Map") && (method = (Method) entry.getValue()) != null && method.getReturnType().equals(Map.class) && !method.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method.getModifiers())) {
                b(sb, i, substring.substring(0, substring.length() - 3), zzed.e(method, zzfoVar, new Object[0]));
            } else if (hashSet.contains("set".concat(substring)) && (!substring.endsWith("Bytes") || !treeMap.containsKey("get".concat(String.valueOf(substring.substring(0, substring.length() - 5)))))) {
                Method method4 = (Method) entry.getValue();
                Method method5 = (Method) hashMap.get("has".concat(substring));
                if (method4 != null) {
                    Object e = zzed.e(method4, zzfoVar, new Object[0]);
                    if (method5 == null) {
                        if (e instanceof Boolean) {
                            if (!((Boolean) e).booleanValue()) {
                            }
                            b(sb, i, substring, e);
                        } else if (e instanceof Integer) {
                            if (((Integer) e).intValue() == 0) {
                            }
                            b(sb, i, substring, e);
                        } else if (e instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) e).floatValue()) == 0) {
                            }
                            b(sb, i, substring, e);
                        } else if (e instanceof Double) {
                            if (Double.doubleToRawLongBits(((Double) e).doubleValue()) == 0) {
                            }
                            b(sb, i, substring, e);
                        } else {
                            if (e instanceof String) {
                                equals = e.equals("");
                            } else if (e instanceof zzdb) {
                                equals = e.equals(zzdb.zzb);
                            } else if (e instanceof zzfo) {
                                if (e == ((zzfo) e).zzab()) {
                                }
                                b(sb, i, substring, e);
                            } else {
                                if ((e instanceof Enum) && ((Enum) e).ordinal() == 0) {
                                }
                                b(sb, i, substring, e);
                            }
                            if (equals) {
                            }
                            b(sb, i, substring, e);
                        }
                    } else {
                        if (!((Boolean) zzed.e(method5, zzfoVar, new Object[0])).booleanValue()) {
                        }
                        b(sb, i, substring, e);
                    }
                }
            }
            i2 = 3;
        }
        if (zzfoVar instanceof zzdz) {
            Iterator f = ((zzdz) zzfoVar).zza.f();
            while (f.hasNext()) {
                Map.Entry entry2 = (Map.Entry) f.next();
                b(sb, i, "[" + ((i0) entry2.getKey()).h + "]", entry2.getValue());
            }
        }
        zzgz zzgzVar = ((zzed) zzfoVar).zzc;
        if (zzgzVar != null) {
            zzgzVar.e(sb, i);
        }
    }
}
