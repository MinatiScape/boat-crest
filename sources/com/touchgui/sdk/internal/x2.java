package com.touchgui.sdk.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
/* loaded from: classes12.dex */
public final class x2 extends m2 {
    public final void b(String str) {
        Matcher matcher = Pattern.compile("\\{\"HEAD\":\\{(.+?)\\},\"WATCH\":\\{(.+?)\\},\"PREVIEW\":\\{(.+?)\\}\\}").matcher(str.replaceAll("\\s+", ""));
        if (matcher.matches()) {
            try {
                this.b.put("HEAD", a(matcher.group(1)));
                this.b.put("WATCH", a(matcher.group(2)));
                this.b.put("PREVIEW", a(matcher.group(3)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
