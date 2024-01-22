package com.coveiot.utils.utility;

import android.content.Context;
import androidx.annotation.NonNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class FileUtil {

    /* loaded from: classes9.dex */
    public interface Format {
        public static final String JPEG = "jpeg";
        public static final String PNG = "png";
    }

    public static File createFile(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        File file;
        Exception e;
        IOException e2;
        try {
            File file2 = new File(str);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            file = new File(str + str2);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e3) {
                e2 = e3;
                e2.printStackTrace();
                return file;
            } catch (Exception e4) {
                e = e4;
                e.printStackTrace();
                return file;
            }
        } catch (IOException e5) {
            file = null;
            e2 = e5;
        } catch (Exception e6) {
            file = null;
            e = e6;
        }
        return file;
    }

    public static List<Integer> readFromFile(@NonNull Context context, @NonNull File file) {
        ArrayList arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null || readLine.isEmpty()) {
                        break;
                    }
                    try {
                        arrayList.add(Integer.valueOf(Integer.parseInt(readLine)));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        return arrayList;
    }

    public static Boolean writeToFile(@NonNull Context context, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<Integer> list) {
        Boolean bool = Boolean.FALSE;
        try {
            File createFile = createFile(context, str2, str3);
            if (createFile != null) {
                FileOutputStream fileOutputStream = new FileOutputStream(createFile);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    stringBuffer.append(list.get(i).toString() + "\n");
                }
                outputStreamWriter.write(stringBuffer.toString());
                outputStreamWriter.flush();
                fileOutputStream.close();
                outputStreamWriter.close();
                return Boolean.TRUE;
            }
            return bool;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return bool;
        } catch (IOException e2) {
            e2.printStackTrace();
            return bool;
        } catch (Exception e3) {
            e3.printStackTrace();
            return bool;
        }
    }
}
