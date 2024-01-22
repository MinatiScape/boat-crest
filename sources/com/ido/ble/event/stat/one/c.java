package com.ido.ble.event.stat.one;

import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class c {
    public static void a() {
        a("connect_failed");
    }

    public static void a(long j) {
        e.a(g.a(j));
    }

    public static void a(long j, String str) {
        e.a(g.a(j, str));
    }

    public static void a(long j, String str, String str2, String str3) {
        e.a(g.a(j, str, str2, str3));
    }

    public static void a(String str) {
        e.a(g.a(str));
    }

    public static void a(String str, String str2) {
        e.a(g.a(str, str2));
    }

    public static void a(String str, String str2, IEventStatCallBack iEventStatCallBack) {
        e.b(FeedbackEvent.createFeedbackLog(str, str2), iEventStatCallBack);
    }

    public static void a(boolean z) {
        e.a(g.a(z));
    }

    public static void b() {
        a("not_find_device");
    }

    public static void b(String str) {
        e.a(g.b(str));
    }

    public static void c() {
        e.a(g.b());
    }

    public static void c(String str) {
        e.a(g.d(str));
    }

    public static void d() {
        e.a();
        com.ido.ble.g.a.a c = g.c();
        LogTool.d("Event", c.toString());
        e.a(c);
    }

    public static void d(String str) {
        e.a(g.c(str));
    }

    public static void e() {
        e.a(g.d());
    }

    public static void e(String str) {
        e.a(g.e(str));
    }

    public static void f(String str) {
        e.a(g.f(str));
    }

    public static void g(String str) {
        e.a(g.g(str));
    }

    public static void h(String str) {
        e.a(g.h(str));
    }

    public static void i(String str) {
        e.a(g.i(str));
    }

    public static void j(String str) {
        e.a(g.j(str));
    }
}
