package com.abupdate.http_libs.request.multi;

import java.nio.charset.Charset;
import java.util.Random;
/* loaded from: classes.dex */
public class BoundaryCreater {
    public static final Charset charset = Charset.forName("UTF-8");
    public static final char[] d = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* renamed from: a  reason: collision with root package name */
    public String f1874a;
    public byte[] b;
    public byte[] c;

    public BoundaryCreater() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            char[] cArr = d;
            sb.append(cArr[random.nextInt(cArr.length)]);
        }
        this.f1874a = sb.toString();
        Charset charset2 = charset;
        this.b = ("--" + this.f1874a + "\r\n").getBytes(charset2);
        this.c = ("--" + this.f1874a + "--\r\n").getBytes(charset2);
    }

    public String getBoundary() {
        return this.f1874a;
    }

    public byte[] getBoundaryEnd() {
        return this.c;
    }

    public byte[] getBoundaryLine() {
        return this.b;
    }
}
