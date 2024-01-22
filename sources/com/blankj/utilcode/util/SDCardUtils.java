package com.blankj.utilcode.util;

import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.format.Formatter;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class SDCardUtils {

    /* loaded from: classes.dex */
    public static class SDCardInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f2283a;
        public String b;
        public boolean c;
        public long d;
        public long e;

        public SDCardInfo(String str, String str2, boolean z) {
            this.f2283a = str;
            this.b = str2;
            this.c = z;
            this.d = b.S(str);
            this.e = b.R(str);
        }

        public long getAvailableSize() {
            return this.e;
        }

        public String getPath() {
            return this.f2283a;
        }

        public String getState() {
            return this.b;
        }

        public long getTotalSize() {
            return this.d;
        }

        public boolean isRemovable() {
            return this.c;
        }

        public String toString() {
            return "SDCardInfo {path = " + this.f2283a + ", state = " + this.b + ", isRemovable = " + this.c + ", totalSize = " + Formatter.formatFileSize(Utils.getApp(), this.d) + ", availableSize = " + Formatter.formatFileSize(Utils.getApp(), this.e) + '}';
        }
    }

    public SDCardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static long getExternalAvailableSize() {
        return b.R(getSDCardPathByEnvironment());
    }

    public static long getExternalTotalSize() {
        return b.S(getSDCardPathByEnvironment());
    }

    public static long getInternalAvailableSize() {
        return b.R(Environment.getDataDirectory().getAbsolutePath());
    }

    public static long getInternalTotalSize() {
        return b.S(Environment.getDataDirectory().getAbsolutePath());
    }

    public static List<String> getMountedSDCardPath() {
        ArrayList arrayList = new ArrayList();
        List<SDCardInfo> sDCardInfo = getSDCardInfo();
        if (sDCardInfo != null && !sDCardInfo.isEmpty()) {
            for (SDCardInfo sDCardInfo2 : sDCardInfo) {
                String str = sDCardInfo2.b;
                if (str != null && "mounted".equals(str.toLowerCase())) {
                    arrayList.add(sDCardInfo2.f2283a);
                }
            }
        }
        return arrayList;
    }

    public static List<SDCardInfo> getSDCardInfo() {
        ArrayList arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) Utils.getApp().getSystemService("storage");
        if (storageManager == null) {
            return arrayList;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
            try {
                Method method = StorageVolume.class.getMethod("getPath", new Class[0]);
                for (StorageVolume storageVolume : storageVolumes) {
                    arrayList.add(new SDCardInfo((String) method.invoke(storageVolume, new Object[0]), storageVolume.getState(), storageVolume.isRemovable()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        } else {
            try {
                Class<?> cls = Class.forName("android.os.storage.StorageVolume");
                Method method2 = cls.getMethod("getPath", new Class[0]);
                Method method3 = cls.getMethod("isRemovable", new Class[0]);
                Method method4 = StorageManager.class.getMethod("getVolumeState", String.class);
                Object invoke = StorageManager.class.getMethod("getVolumeList", new Class[0]).invoke(storageManager, new Object[0]);
                int length = Array.getLength(invoke);
                for (int i = 0; i < length; i++) {
                    Object obj = Array.get(invoke, i);
                    String str = (String) method2.invoke(obj, new Object[0]);
                    arrayList.add(new SDCardInfo(str, (String) method4.invoke(storageManager, str), ((Boolean) method3.invoke(obj, new Object[0])).booleanValue()));
                }
            } catch (ClassNotFoundException e4) {
                e4.printStackTrace();
            } catch (IllegalAccessException e5) {
                e5.printStackTrace();
            } catch (NoSuchMethodException e6) {
                e6.printStackTrace();
            } catch (InvocationTargetException e7) {
                e7.printStackTrace();
            }
        }
        return arrayList;
    }

    public static String getSDCardPathByEnvironment() {
        return isSDCardEnableByEnvironment() ? Environment.getExternalStorageDirectory().getAbsolutePath() : "";
    }

    public static boolean isSDCardEnableByEnvironment() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
