package com.google.android.gms.internal.gtm;

import com.clevertap.android.sdk.Constants;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.text.Typography;
/* loaded from: classes8.dex */
public final class zzwm {
    public static String zza(zzwk zzwkVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzwkVar, sb, 0);
        return sb.toString();
    }

    public static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                zzb(sb, i, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                zzb(sb, i, str, entry);
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
                sb.append(zzxm.zza(zztd.zzo((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zztd) {
                sb.append(": \"");
                sb.append(zzxm.zza((zztd) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzuz) {
                sb.append(" {");
                zzd((zzuz) obj, sb, i + 2);
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
                zzb(sb, i4, Constants.KEY_KEY, entry2.getKey());
                zzb(sb, i4, "value", entry2.getValue());
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

    public static final String zzc(String str) {
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

    public static void zzd(zzwk zzwkVar, StringBuilder sb, int i) {
        Method[] declaredMethods;
        boolean equals;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzwkVar.getClass().getDeclaredMethods()) {
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
                    zzb(sb, i, zzc(concat), zzuz.zzai(method2, zzwkVar, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String valueOf3 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(substring.substring(1, substring.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i, zzc(concat2), zzuz.zzai(method3, zzwkVar, new Object[0]));
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
                    Object zzai = zzuz.zzai(method4, zzwkVar, new Object[0]);
                    if (method5 == null) {
                        if (zzai instanceof Boolean) {
                            if (((Boolean) zzai).booleanValue()) {
                                zzb(sb, i, zzc(concat3), zzai);
                            }
                        } else if (zzai instanceof Integer) {
                            if (((Integer) zzai).intValue() != 0) {
                                zzb(sb, i, zzc(concat3), zzai);
                            }
                        } else if (zzai instanceof Float) {
                            if (((Float) zzai).floatValue() != 0.0f) {
                                zzb(sb, i, zzc(concat3), zzai);
                            }
                        } else if (zzai instanceof Double) {
                            if (((Double) zzai).doubleValue() != 0.0d) {
                                zzb(sb, i, zzc(concat3), zzai);
                            }
                        } else {
                            if (zzai instanceof String) {
                                equals = zzai.equals("");
                            } else if (zzai instanceof zztd) {
                                equals = zzai.equals(zztd.zzb);
                            } else if (zzai instanceof zzwk) {
                                if (zzai != ((zzwk) zzai).zzar()) {
                                    zzb(sb, i, zzc(concat3), zzai);
                                }
                            } else {
                                if ((zzai instanceof Enum) && ((Enum) zzai).ordinal() == 0) {
                                }
                                zzb(sb, i, zzc(concat3), zzai);
                            }
                            if (!equals) {
                                zzb(sb, i, zzc(concat3), zzai);
                            }
                        }
                    } else if (((Boolean) zzuz.zzai(method5, zzwkVar, new Object[0])).booleanValue()) {
                        zzb(sb, i, zzc(concat3), zzai);
                    }
                }
            }
        }
        if (zzwkVar instanceof zzuv) {
            Iterator<Map.Entry<zzuw, Object>> zzf = ((zzuv) zzwkVar).zza.zzf();
            while (zzf.hasNext()) {
                Map.Entry<zzuw, Object> next = zzf.next();
                int i2 = next.getKey().zzb;
                StringBuilder sb2 = new StringBuilder(13);
                sb2.append("[");
                sb2.append(i2);
                sb2.append("]");
                zzb(sb, i, sb2.toString(), next.getValue());
            }
        }
        zzxp zzxpVar = ((zzuz) zzwkVar).zzc;
        if (zzxpVar != null) {
            zzxpVar.zzg(sb, i);
        }
    }
}
