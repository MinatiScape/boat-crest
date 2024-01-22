package com.touchgui.sdk.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
/* loaded from: classes12.dex */
public final class u2 extends m2 {
    public final void b(String str) {
        Matcher matcher = Pattern.compile("\\{\"BigWatch\":\\{(.+?)\\},\"SmallWatch\":\\{(.+?)\\}\\}").matcher(str.replaceAll("\\s+", ""));
        if (matcher.matches()) {
            try {
                this.b.put("BigWatch", a(matcher.group(1)));
                this.b.put("SmallWatch", a(matcher.group(2)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
