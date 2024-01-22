package com.github.siyamed.shapeimageview.path.parser;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes9.dex */
public class a {
    public static final String c = SvgToPath.n;

    /* renamed from: a  reason: collision with root package name */
    public final InputStream f7970a;
    public ByteArrayOutputStream b;

    public a(InputStream inputStream) {
        this.f7970a = inputStream;
        try {
            a();
        } catch (IOException e) {
            String str = c;
            Log.w(str, "IOException in CopyInputStream " + e.toString());
        }
    }

    public final void a() throws IOException {
        this.b = new ByteArrayOutputStream();
        byte[] bArr = new byte[256];
        while (true) {
            int read = this.f7970a.read(bArr);
            if (-1 != read) {
                this.b.write(bArr, 0, read);
            } else {
                this.b.flush();
                return;
            }
        }
    }

    public ByteArrayInputStream b() {
        return new ByteArrayInputStream(this.b.toByteArray());
    }
}
