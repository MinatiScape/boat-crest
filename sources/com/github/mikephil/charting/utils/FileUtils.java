package com.github.mikephil.charting.utils;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes9.dex */
public class FileUtils {
    /* JADX WARN: Multi-variable type inference failed */
    public static List<BarEntry> loadBarEntriesFromAssets(AssetManager assetManager, String str) {
        BufferedReader bufferedReader;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader2 = null;
        BufferedReader bufferedReader3 = null;
        bufferedReader2 = null;
        bufferedReader2 = null;
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(str), "UTF-8"));
                } catch (IOException e) {
                    Log.e("MPChart-FileUtils", e.toString());
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                String[] split = readLine.split(MqttTopic.MULTI_LEVEL_WILDCARD);
                float parseFloat = Float.parseFloat(split[1]);
                arrayList.add(new BarEntry(parseFloat, Float.parseFloat(split[0])));
                readLine = bufferedReader.readLine();
                bufferedReader3 = parseFloat;
            }
            bufferedReader.close();
            bufferedReader2 = bufferedReader3;
        } catch (IOException e3) {
            e = e3;
            bufferedReader2 = bufferedReader;
            Log.e("MPChart-FileUtils", e.toString());
            if (bufferedReader2 != null) {
                bufferedReader2.close();
                bufferedReader2 = bufferedReader2;
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                    Log.e("MPChart-FileUtils", e4.toString());
                }
            }
            throw th;
        }
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r2v11, types: [float[]] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v14, types: [float] */
    public static List<Entry> loadEntriesFromAssets(AssetManager assetManager, String str) {
        BufferedReader bufferedReader;
        ?? r2;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(str), "UTF-8"));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
            }
        } catch (IOException e2) {
            Log.e("MPChart-FileUtils", e2.toString());
        }
        try {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                String[] split = readLine.split(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (split.length <= 2) {
                    r2 = Float.parseFloat(split[1]);
                    arrayList.add(new Entry(r2, Float.parseFloat(split[0])));
                } else {
                    int length = split.length - 1;
                    r2 = new float[length];
                    for (int i = 0; i < length; i++) {
                        r2[i] = Float.parseFloat(split[i]);
                    }
                    arrayList.add(new BarEntry(Integer.parseInt(split[split.length - 1]), (float[]) r2));
                }
                readLine = bufferedReader.readLine();
                bufferedReader2 = r2;
            }
            bufferedReader.close();
        } catch (IOException e3) {
            e = e3;
            bufferedReader2 = bufferedReader;
            Log.e("MPChart-FileUtils", e.toString());
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                    Log.e("MPChart-FileUtils", e4.toString());
                }
            }
            throw th;
        }
        return arrayList;
    }

    public static List<Entry> loadEntriesFromFile(String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        ArrayList arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String[] split = readLine.split(MqttTopic.MULTI_LEVEL_WILDCARD);
                if (split.length <= 2) {
                    arrayList.add(new Entry(Float.parseFloat(split[0]), Integer.parseInt(split[1])));
                } else {
                    int length = split.length - 1;
                    float[] fArr = new float[length];
                    for (int i = 0; i < length; i++) {
                        fArr[i] = Float.parseFloat(split[i]);
                    }
                    arrayList.add(new BarEntry(Integer.parseInt(split[split.length - 1]), fArr));
                }
            }
        } catch (IOException e) {
            Log.e("MPChart-FileUtils", e.toString());
        }
        return arrayList;
    }

    public static void saveToSdCard(List<Entry> list, String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e("MPChart-FileUtils", e.toString());
            }
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            for (Entry entry : list) {
                bufferedWriter.append((CharSequence) (entry.getY() + MqttTopic.MULTI_LEVEL_WILDCARD + entry.getX()));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e2) {
            Log.e("MPChart-FileUtils", e2.toString());
        }
    }
}
