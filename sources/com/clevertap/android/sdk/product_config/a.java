package com.clevertap.android.sdk.product_config;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;
import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes2.dex */
public class a {
    public HashMap<String, String> a(Context context, int i) {
        HashMap<String, String> hashMap = new HashMap<>();
        b(context.getResources(), i, hashMap);
        return hashMap;
    }

    public void b(Resources resources, int i, HashMap<String, String> hashMap) {
        if (resources == null) {
            Log.e("ProductConfig", "Could not find the resources of the current context while trying to set defaults from an XML.");
            return;
        }
        try {
            c(resources.getXml(i), hashMap);
        } catch (Exception e) {
            Log.e("ProductConfig", "Encountered an error while parsing the defaults XML file.", e);
        }
    }

    public void c(XmlResourceParser xmlResourceParser, HashMap<String, String> hashMap) throws XmlPullParserException, IOException {
        int eventType = xmlResourceParser.getEventType();
        String str = null;
        String str2 = null;
        String str3 = null;
        while (eventType != 1) {
            if (eventType == 2) {
                str2 = xmlResourceParser.getName();
            } else if (eventType == 3) {
                if (xmlResourceParser.getName().equals("entry")) {
                    if (str != null && str3 != null) {
                        hashMap.put(str, str3);
                    } else {
                        Log.w(Constants.LOG_TAG_PRODUCT_CONFIG, "An entry in the defaults XML has an invalid key and/or value tag.");
                    }
                    str = null;
                    str3 = null;
                }
                str2 = null;
            } else if (eventType == 4 && str2 != null) {
                char c = 65535;
                if (str2.equals(Constants.KEY_KEY)) {
                    c = 0;
                } else if (str2.equals("value")) {
                    c = 1;
                }
                if (c == 0) {
                    str = xmlResourceParser.getText();
                } else if (c != 1) {
                    Log.w(Constants.LOG_TAG_PRODUCT_CONFIG, "Encountered an unexpected tag while parsing the defaults XML.");
                } else {
                    str3 = xmlResourceParser.getText();
                }
            }
            eventType = xmlResourceParser.next();
        }
    }
}
