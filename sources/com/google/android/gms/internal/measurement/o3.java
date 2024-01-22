package com.google.android.gms.internal.measurement;

import com.clevertap.android.sdk.Constants;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.text.Typography;
/* loaded from: classes8.dex */
public final class o3 {
    public static String a(zzlg zzlgVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        d(zzlgVar, sb, 0);
        return sb.toString();
    }

    public static final void b(StringBuilder sb, int i, String str, Object obj) {
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
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(k4.a(zziy.zzm((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zziy) {
                sb.append(": \"");
                sb.append(k4.a((zziy) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzjz) {
                sb.append(" {");
                d((zzjz) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int i4 = i + 2;
                b(sb, i4, Constants.KEY_KEY, entry2.getKey());
                b(sb, i4, "value", entry2.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj.toString());
            }
        }
    }

    public static final String c(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    public static void d(zzlg zzlgVar, StringBuilder sb, int i) {
        Method[] declaredMethods;
        boolean equals;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzlgVar.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String substring = str.startsWith("get") ? str.substring(3) : str;
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List")) {
                String valueOf = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(substring.substring(1, substring.length() - 4));
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    b(sb, i, c(concat), zzjz.c(method2, zzlgVar, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String valueOf3 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(substring.substring(1, substring.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    b(sb, i, c(concat2), zzjz.c(method3, zzlgVar, new Object[0]));
                }
            }
            if (((Method) hashMap2.get(substring.length() != 0 ? "set".concat(substring) : new String("set"))) != null) {
                if (substring.endsWith("Bytes")) {
                    String valueOf5 = String.valueOf(substring.substring(0, substring.length() - 5));
                    if (!hashMap.containsKey(valueOf5.length() != 0 ? "get".concat(valueOf5) : new String("get"))) {
                    }
                }
                String valueOf6 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf7 = String.valueOf(substring.substring(1));
                String concat3 = valueOf7.length() != 0 ? valueOf6.concat(valueOf7) : new String(valueOf6);
                Method method4 = (Method) hashMap.get(substring.length() != 0 ? "get".concat(substring) : new String("get"));
                Method method5 = (Method) hashMap.get(substring.length() != 0 ? "has".concat(substring) : new String("has"));
                if (method4 != null) {
                    Object c = zzjz.c(method4, zzlgVar, new Object[0]);
                    if (method5 == null) {
                        if (c instanceof Boolean) {
                            if (((Boolean) c).booleanValue()) {
                                b(sb, i, c(concat3), c);
                            }
                        } else if (c instanceof Integer) {
                            if (((Integer) c).intValue() != 0) {
                                b(sb, i, c(concat3), c);
                            }
                        } else if (c instanceof Float) {
                            if (((Float) c).floatValue() != 0.0f) {
                                b(sb, i, c(concat3), c);
                            }
                        } else if (c instanceof Double) {
                            if (((Double) c).doubleValue() != 0.0d) {
                                b(sb, i, c(concat3), c);
                            }
                        } else {
                            if (c instanceof String) {
                                equals = c.equals("");
                            } else if (c instanceof zziy) {
                                equals = c.equals(zziy.zzb);
                            } else if (c instanceof zzlg) {
                                if (c != ((zzlg) c).zzbL()) {
                                    b(sb, i, c(concat3), c);
                                }
                            } else {
                                if ((c instanceof Enum) && ((Enum) c).ordinal() == 0) {
                                }
                                b(sb, i, c(concat3), c);
                            }
                            if (!equals) {
                                b(sb, i, c(concat3), c);
                            }
                        }
                    } else if (((Boolean) zzjz.c(method5, zzlgVar, new Object[0])).booleanValue()) {
                        b(sb, i, c(concat3), c);
                    }
                }
            }
        }
        if (!(zzlgVar instanceof zzjw)) {
            zzmj zzmjVar = ((zzjz) zzlgVar).zzc;
            if (zzmjVar != null) {
                zzmjVar.c(sb, i);
                return;
            }
            return;
        }
        zzjw zzjwVar = (zzjw) zzlgVar;
        throw null;
    }
}
