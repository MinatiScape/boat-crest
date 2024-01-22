package com.google.android.gms.internal.auth;

import com.clevertap.android.sdk.Constants;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.text.Typography;
/* loaded from: classes6.dex */
public final class q1 {
    public static String a(zzfw zzfwVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        d(zzfwVar, sb, 0);
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
                sb.append(l2.a(zzee.zzl((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zzee) {
                sb.append(": \"");
                sb.append(l2.a((zzee) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzeu) {
                sb.append(" {");
                d((zzeu) obj, sb, i + 2);
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
                sb.append(obj);
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

    public static void d(zzfw zzfwVar, StringBuilder sb, int i) {
        Method[] declaredMethods;
        boolean equals;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzfwVar.getClass().getDeclaredMethods()) {
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
                String concat = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1, substring.length() - 4)));
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    b(sb, i, c(concat), zzeu.b(method2, zzfwVar, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String concat2 = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1, substring.length() - 3)));
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    b(sb, i, c(concat2), zzeu.b(method3, zzfwVar, new Object[0]));
                }
            }
            if (((Method) hashMap2.get("set".concat(substring))) != null && (!substring.endsWith("Bytes") || !hashMap.containsKey("get".concat(String.valueOf(substring.substring(0, substring.length() - 5)))))) {
                String concat3 = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1)));
                Method method4 = (Method) hashMap.get("get".concat(substring));
                Method method5 = (Method) hashMap.get("has".concat(substring));
                if (method4 != null) {
                    Object b = zzeu.b(method4, zzfwVar, new Object[0]);
                    if (method5 == null) {
                        if (b instanceof Boolean) {
                            if (((Boolean) b).booleanValue()) {
                                b(sb, i, c(concat3), b);
                            }
                        } else if (b instanceof Integer) {
                            if (((Integer) b).intValue() != 0) {
                                b(sb, i, c(concat3), b);
                            }
                        } else if (b instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) b).floatValue()) != 0) {
                                b(sb, i, c(concat3), b);
                            }
                        } else if (b instanceof Double) {
                            if (Double.doubleToRawLongBits(((Double) b).doubleValue()) != 0) {
                                b(sb, i, c(concat3), b);
                            }
                        } else {
                            if (b instanceof String) {
                                equals = b.equals("");
                            } else if (b instanceof zzee) {
                                equals = b.equals(zzee.zzb);
                            } else if (b instanceof zzfw) {
                                if (b != ((zzfw) b).zzh()) {
                                    b(sb, i, c(concat3), b);
                                }
                            } else {
                                if ((b instanceof Enum) && ((Enum) b).ordinal() == 0) {
                                }
                                b(sb, i, c(concat3), b);
                            }
                            if (!equals) {
                                b(sb, i, c(concat3), b);
                            }
                        }
                    } else if (((Boolean) zzeu.b(method5, zzfwVar, new Object[0])).booleanValue()) {
                        b(sb, i, c(concat3), b);
                    }
                }
            }
        }
        if (!(zzfwVar instanceof zzet)) {
            zzgz zzgzVar = ((zzeu) zzfwVar).zzc;
            if (zzgzVar != null) {
                zzgzVar.c(sb, i);
                return;
            }
            return;
        }
        zzet zzetVar = (zzet) zzfwVar;
        throw null;
    }
}
