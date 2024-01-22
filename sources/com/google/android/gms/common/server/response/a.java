package com.google.android.gms.common.server.response;

import androidx.annotation.Nullable;
import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class a implements i {
    @Override // com.google.android.gms.common.server.response.i
    @Nullable
    public final /* synthetic */ Object a(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        int m;
        m = fastParser.m(bufferedReader);
        return Integer.valueOf(m);
    }
}
