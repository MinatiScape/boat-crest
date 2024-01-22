package com.touchgui.sdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.actions.actres.ResBuilder;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import com.jieli.watchtesttool.util.WatchTestConstant;
import com.realsil.sdk.dfu.DfuException;
import com.touchgui.sdk.TGLogger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public final class c3 implements t2 {
    public static final String[] o = {"Picture01", "Picture02"};

    /* renamed from: a  reason: collision with root package name */
    public final Context f13750a;
    public b3 b;
    public String c;
    public JSONObject d;
    public String e;
    public String f;
    public int g = 0;
    public int h = 368;
    public int i = 448;
    public int j = 220;
    public int k = DfuException.ERROR_SEND_COMMAND_REACH_MAX_RETRY_TIMES;
    public int l = com.veryfit.multi.nativeprotocol.b.E1;
    public final ArrayList m = new ArrayList();
    public final ResBuilder n = new ResBuilder();

    public c3(Context context) {
        this.f13750a = context;
    }

    public static /* synthetic */ boolean c(File file) {
        return true;
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void a(Bitmap bitmap) {
        Bitmap a2 = a(bitmap, this.h, this.i);
        int i = this.h;
        if (i == this.i && this.g == 1) {
            a2 = v3.a(a2, i / 2.0f);
        }
        String[] strArr = o;
        String b = b(strArr[0]);
        v3.a(a2, new File(b), Bitmap.CompressFormat.JPEG, false);
        String b2 = b(strArr[1]);
        v3.a(a(a2, this.j, this.k), new File(b2), Bitmap.CompressFormat.JPEG, true);
        if (a2.isRecycled()) {
            return;
        }
        a2.recycle();
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void b(int i) {
        JSONObject optJSONObject;
        JSONArray optJSONArray;
        if (this.d == null) {
            return;
        }
        String[] strArr = {"LargeWatch", "SmallWatch"};
        for (int i2 = 0; i2 < 2; i2++) {
            try {
                String str = strArr[i2];
                if (this.d.has(str) && (optJSONObject = this.d.optJSONObject(str)) != null && optJSONObject.has(Constants.ScionAnalytics.PARAM_LABEL) && (optJSONArray = optJSONObject.optJSONArray(Constants.ScionAnalytics.PARAM_LABEL)) != null) {
                    for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                        if (optJSONObject2 != null && optJSONObject2.has("color")) {
                            JSONArray jSONArray = new JSONArray();
                            jSONArray.put((16711680 & i) >> 16);
                            jSONArray.put((65280 & i) >> 8);
                            jSONArray.put(i & 255);
                            optJSONObject2.put("color", jSONArray);
                        }
                    }
                }
            } catch (JSONException e) {
                TGLogger.e(e.getMessage());
                return;
            }
        }
    }

    public final b3 c(String str) {
        JSONArray optJSONArray;
        JSONArray optJSONArray2;
        b3 b3Var = new b3();
        JSONObject jSONObject = new JSONObject(str);
        b3Var.f13745a = jSONObject.optString("scene_name");
        b3Var.b = jSONObject.optInt("photo");
        this.l = jSONObject.optInt("max_size", com.veryfit.multi.nativeprotocol.b.E1);
        if (jSONObject.has("pic01_size") && (optJSONArray2 = jSONObject.optJSONArray("pic01_size")) != null && optJSONArray2.length() == 2) {
            this.h = optJSONArray2.optInt(0, this.h);
            this.i = optJSONArray2.optInt(1, this.i);
        }
        if (jSONObject.has("pic02_size") && (optJSONArray = jSONObject.optJSONArray("pic02_size")) != null && optJSONArray.length() == 2) {
            this.j = optJSONArray.optInt(0, this.j);
            this.k = optJSONArray.optInt(1, this.k);
        }
        JSONArray optJSONArray3 = jSONObject.optJSONArray("file");
        if (optJSONArray3 != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray3.length(); i++) {
                a3 a3Var = new a3();
                JSONObject optJSONObject = optJSONArray3.optJSONObject(i);
                a3Var.f13740a = optJSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                a3Var.b = optJSONObject.optInt("size");
                a3Var.c = optJSONObject.optInt(TypedValues.CycleType.S_WAVE_OFFSET);
                arrayList.add(a3Var);
            }
            b3Var.c = arrayList;
        }
        return b3Var;
    }

    public final void d(String str) {
        String b = b(str);
        if (!new File(b).exists()) {
            TGLogger.w(String.format("%s not exists!!!", str));
            return;
        }
        String str2 = this.f + "_out";
        String str3 = this.e + "_out";
        int replacePicture = this.n.replacePicture(this.e, this.f, this.b.f13745a, str, b, str2, str3);
        if (replacePicture != 1) {
            TGLogger.d(String.format("replacePicture, %d", Integer.valueOf(replacePicture)));
            return;
        }
        File file = new File(this.e);
        if (!file.delete()) {
            TGLogger.e("Failed to delete file: " + file.getAbsolutePath());
        }
        if (!new File(str3).renameTo(file)) {
            TGLogger.e("Failed to rename file: " + file.getAbsolutePath());
        }
        File file2 = new File(this.f);
        if (!file2.delete()) {
            TGLogger.e("Failed to delete file: " + file2.getAbsolutePath());
        }
        if (new File(str2).renameTo(file2)) {
            return;
        }
        TGLogger.e("Failed to rename file: " + file2.getAbsolutePath());
    }

    public final void c() {
        File file = new File(this.f13750a.getCacheDir(), "TGTemp");
        v3.a(file, new FileFilter() { // from class: com.touchgui.sdk.internal.yb
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return c3.c(file2);
            }
        });
        if (file.exists()) {
            file.delete();
        }
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void b() {
        c();
    }

    public final String b(String str) {
        File file = new File(this.f13750a.getCacheDir(), "TGTemp");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + File.separator + str;
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void a(ArrayList arrayList) {
        if (arrayList == null || arrayList.size() < 1) {
            return;
        }
        if (arrayList.size() <= 1) {
            a((Bitmap) arrayList.get(0));
            return;
        }
        a((Bitmap) arrayList.get(0));
        this.m.clear();
        for (int i = 1; i < arrayList.size(); i++) {
            Bitmap a2 = a((Bitmap) arrayList.get(i), this.h, this.i);
            int i2 = this.h;
            if (i2 == this.i && this.g == 1) {
                Bitmap a3 = v3.a(a2, i2 / 2.0f);
                if (!a2.isRecycled()) {
                    a2.recycle();
                }
                a2 = a3;
            }
            String format = String.format(Locale.getDefault(), "Picture%02d", Integer.valueOf(i + 2));
            this.m.add(format);
            v3.a(a(a2, this.h, this.i), new File(b(format)), Bitmap.CompressFormat.JPEG, true);
        }
    }

    @Override // com.touchgui.sdk.internal.t2
    public final boolean b(File file) {
        int length;
        File file2;
        String[] strArr = o;
        d(strArr[0]);
        d(strArr[1]);
        Iterator it = this.m.iterator();
        while (it.hasNext()) {
            d((String) it.next());
        }
        try {
            this.c = this.d.toString(4);
            Iterator it2 = this.b.c.iterator();
            int i = 0;
            while (it2.hasNext()) {
                a3 a3Var = (a3) it2.next();
                if (a3Var.f13740a.contains(".res")) {
                    file2 = new File(b(a3Var.f13740a));
                } else if (a3Var.f13740a.contains(".sty")) {
                    file2 = new File(b(a3Var.f13740a));
                } else if (a3Var.f13740a.equalsIgnoreCase(com.clevertap.android.sdk.Constants.KEY_CONFIG)) {
                    length = ((this.c.length() + 3) / 4) * 4;
                    a3Var.b = length;
                    a3Var.c = i;
                    i += a3Var.b;
                } else {
                    a3Var.c = i;
                    i += a3Var.b;
                }
                length = (int) file2.length();
                a3Var.b = length;
                a3Var.c = i;
                i += a3Var.b;
            }
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("scene_name", this.b.f13745a);
                    jSONObject.put("photo", this.b.b);
                    JSONArray jSONArray = new JSONArray();
                    Iterator it3 = this.b.c.iterator();
                    while (it3.hasNext()) {
                        a3 a3Var2 = (a3) it3.next();
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put(AppMeasurementSdk.ConditionalUserProperty.NAME, a3Var2.f13740a);
                        jSONObject2.put("size", a3Var2.b);
                        jSONObject2.put(TypedValues.CycleType.S_WAVE_OFFSET, a3Var2.c);
                        jSONArray.put(jSONObject2);
                    }
                    jSONObject.put("file", jSONArray);
                    String jSONObject3 = jSONObject.toString(4);
                    int length2 = ((jSONObject3.length() + 3) / 4) * 4;
                    dataOutputStream.write(String.valueOf(length2).getBytes());
                    for (int length3 = String.valueOf(length2).length(); length3 < 4; length3++) {
                        dataOutputStream.write(0);
                    }
                    dataOutputStream.write(jSONObject3.getBytes());
                    for (int length4 = jSONObject3.length(); length4 < length2; length4++) {
                        dataOutputStream.writeByte(0);
                    }
                    Iterator it4 = this.b.c.iterator();
                    while (it4.hasNext()) {
                        a3 a3Var3 = (a3) it4.next();
                        if (!a3Var3.f13740a.contains(".res") && !a3Var3.f13740a.contains(".sty")) {
                            if (a3Var3.f13740a.equalsIgnoreCase(com.clevertap.android.sdk.Constants.KEY_CONFIG)) {
                                dataOutputStream.write(this.c.getBytes());
                                for (int length5 = this.c.length(); length5 < a3Var3.b; length5++) {
                                    dataOutputStream.writeByte(0);
                                }
                            } else {
                                dataOutputStream.write(a3Var3.d);
                            }
                        }
                        a(dataOutputStream, b(a3Var3.f13740a));
                    }
                    dataOutputStream.close();
                    return true;
                } catch (Throwable th) {
                    try {
                        dataOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return false;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null || jSONArray2 == null) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            String optString = optJSONObject.optString("type");
            if (!TextUtils.isEmpty(optString)) {
                int i2 = 0;
                while (true) {
                    if (i2 < jSONArray2.length()) {
                        JSONObject optJSONObject2 = jSONArray2.optJSONObject(i2);
                        if (optString.equalsIgnoreCase(optJSONObject2.optString("type"))) {
                            optJSONObject2.put("xy", optJSONObject.optJSONArray("xy"));
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void b(int i, int i2) {
        this.h = i;
        this.i = i2;
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                String optString = optJSONObject.optString(WatchTestConstant.DIR_WATCH);
                JSONArray optJSONArray = optJSONObject.optJSONArray("img");
                JSONObject optJSONObject2 = this.d.optJSONObject(optString);
                a(optJSONArray, optJSONObject2 != null ? optJSONObject2.optJSONArray("img") : null);
                JSONArray optJSONArray2 = optJSONObject.optJSONArray(Constants.ScionAnalytics.PARAM_LABEL);
                JSONObject optJSONObject3 = this.d.optJSONObject(optString);
                a(optJSONArray2, optJSONObject3 != null ? optJSONObject3.optJSONArray(Constants.ScionAnalytics.PARAM_LABEL) : null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(createBitmap);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, i, i2), paint);
        canvas.setBitmap(null);
        return createBitmap;
    }

    @Override // com.touchgui.sdk.internal.t2
    public final int a() {
        return this.l * 1024;
    }

    @Override // com.touchgui.sdk.internal.t2
    public final boolean a(File file) {
        String b;
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            byte[] bArr = new byte[4];
            dataInputStream.read(bArr);
            int i = 0;
            for (int i2 = 0; i2 < 4 && (bArr[i2] & 255) != 0; i2++) {
                i++;
            }
            int parseInt = Integer.parseInt(new String(bArr, 0, i));
            byte[] bArr2 = new byte[parseInt];
            if (dataInputStream.read(bArr2) != parseInt) {
                TGLogger.w("load error");
            }
            b3 c = c(new String(bArr2, 0, parseInt));
            this.b = c;
            Collections.sort(c.c);
            Iterator it = this.b.c.iterator();
            while (it.hasNext()) {
                a3 a3Var = (a3) it.next();
                if (a3Var.f13740a.contains(".res")) {
                    b = b(a3Var.f13740a);
                    this.f = b;
                } else if (a3Var.f13740a.contains(".sty")) {
                    b = b(a3Var.f13740a);
                    this.e = b;
                } else if (a3Var.f13740a.equalsIgnoreCase(com.clevertap.android.sdk.Constants.KEY_CONFIG)) {
                    int i3 = a3Var.b;
                    byte[] bArr3 = new byte[i3];
                    if (dataInputStream.read(bArr3) != i3) {
                        TGLogger.w("load error");
                    } else {
                        short s = 0;
                        for (int i4 = 0; i4 < i3 && bArr3[i4] != 0; i4++) {
                            s = (short) (s + 1);
                        }
                        this.c = new String(bArr3, 0, (int) s);
                        try {
                            this.d = new JSONObject(this.c);
                        } catch (JSONException e) {
                            TGLogger.e(e.getMessage());
                        }
                    }
                } else {
                    byte[] bArr4 = new byte[a3Var.b];
                    if (dataInputStream.read(bArr4) != a3Var.b) {
                        TGLogger.w("load error");
                    } else {
                        a3Var.d = bArr4;
                    }
                }
                a(dataInputStream, b, a3Var.b);
            }
            dataInputStream.close();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void a(DataInputStream dataInputStream, String str, int i) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        FileOutputStream fileOutputStream3 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(str);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                }
            } catch (Exception e) {
                e = e;
            }
            try {
                byte[] bArr = new byte[512];
                int i2 = 0;
                while (true) {
                    int read = dataInputStream.read(bArr, 0, Math.min(i - i2, 512));
                    if (read <= 0) {
                        break;
                    }
                    i2 += read;
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.close();
                fileOutputStream2 = bArr;
            } catch (Exception e2) {
                e = e2;
                fileOutputStream3 = fileOutputStream;
                e.printStackTrace();
                if (fileOutputStream3 != null) {
                    fileOutputStream3.close();
                    fileOutputStream2 = fileOutputStream3;
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void a(int i) {
        this.g = i;
    }

    @Override // com.touchgui.sdk.internal.t2
    public final void a(int i, int i2) {
        this.j = i;
        this.k = i2;
    }

    public static void a(DataOutputStream dataOutputStream, String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(str);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
            }
            try {
                byte[] bArr = new byte[512];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    dataOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
            } catch (Exception e2) {
                e = e2;
                fileInputStream2 = fileInputStream;
                e.printStackTrace();
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }
}
