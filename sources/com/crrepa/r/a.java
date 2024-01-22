package com.crrepa.r;

import java.io.File;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class a {
    public static final String c = "file/*";
    public static final String d = "image/*";
    public static final String e = "audio/*";
    public static final String f = "video/*";

    public static void a(String str, com.crrepa.s.a aVar) {
        a(str, (Map<String, String>) null, aVar);
    }

    public static void a(String str, com.crrepa.s.b bVar) {
        a(str, (Map<String, String>) null, bVar);
    }

    public static void a(String str, File file, String str2, String str3, com.crrepa.s.a aVar) {
        a(str, file, str2, str3, (Map<String, String>) null, aVar);
    }

    public static void a(String str, File file, String str2, String str3, Map<String, String> map, com.crrepa.s.a aVar) {
        a(str, file, str2, str3, map, (Map<String, String>) null, aVar);
    }

    public static void a(String str, File file, String str2, String str3, Map<String, String> map, Map<String, String> map2, com.crrepa.s.a aVar) {
        new d(str, file, null, null, str2, str3, map, map2, aVar).f();
    }

    public static void a(String str, String str2, com.crrepa.s.a aVar) {
        a(str, str2, (Map<String, String>) null, aVar);
    }

    public static void a(String str, String str2, Map<String, String> map, com.crrepa.s.a aVar) {
        new d(str, str2, map, aVar).f();
    }

    public static void a(String str, List<File> list, String str2, String str3, com.crrepa.s.a aVar) {
        a(str, list, str2, str3, (Map<String, String>) null, aVar);
    }

    public static void a(String str, List<File> list, String str2, String str3, Map<String, String> map, com.crrepa.s.a aVar) {
        a(str, list, str2, str3, map, (Map<String, String>) null, aVar);
    }

    public static void a(String str, List<File> list, String str2, String str3, Map<String, String> map, Map<String, String> map2, com.crrepa.s.a aVar) {
        new d(str, null, list, null, str2, str3, map, map2, aVar).f();
    }

    public static void a(String str, Map<String, String> map, com.crrepa.s.a aVar) {
        a(str, map, (Map<String, String>) null, aVar);
    }

    public static void a(String str, Map<String, String> map, com.crrepa.s.b bVar) {
        b(str, map, null, bVar);
    }

    public static void a(String str, Map<String, File> map, String str2, com.crrepa.s.a aVar) {
        a(str, map, str2, (Map<String, String>) null, aVar);
    }

    public static void a(String str, Map<String, File> map, String str2, Map<String, String> map2, com.crrepa.s.a aVar) {
        a(str, map, str2, map2, (Map<String, String>) null, aVar);
    }

    public static void a(String str, Map<String, File> map, String str2, Map<String, String> map2, Map<String, String> map3, com.crrepa.s.a aVar) {
        new d(str, null, null, map, null, str2, map2, map3, aVar).f();
    }

    public static void a(String str, Map<String, String> map, Map<String, String> map2, com.crrepa.s.a aVar) {
        b(str, map, map2, aVar);
    }

    public static void b(String str, com.crrepa.s.a aVar) {
        b(str, null, null, aVar);
    }

    public static void b(String str, Map<String, String> map, com.crrepa.s.a aVar) {
        b(str, map, null, aVar);
    }

    public static void b(String str, Map<String, String> map, Map<String, String> map2, com.crrepa.s.a aVar) {
        new d("GET", str, map, map2, aVar).f();
    }

    public static void c(String str, com.crrepa.s.a aVar) {
        c(str, null, aVar);
    }

    public static void c(String str, Map<String, String> map, com.crrepa.s.a aVar) {
        c(str, map, null, aVar);
    }

    public static void c(String str, Map<String, String> map, Map<String, String> map2, com.crrepa.s.a aVar) {
        new d("POST", str, map, map2, aVar).f();
    }
}
