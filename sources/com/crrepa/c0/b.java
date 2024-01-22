package com.crrepa.c0;

import com.crrepa.i0.f;
import java.io.File;
/* loaded from: classes9.dex */
public interface b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7695a;
    public static final String b;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(f.a().getFilesDir().getAbsolutePath());
        String str = File.separator;
        sb.append(str);
        sb.append("crp");
        sb.append(str);
        sb.append("firmware");
        f7695a = sb.toString();
        b = f.a().getFilesDir().getAbsolutePath() + str + "crp" + str + "tp";
    }
}
