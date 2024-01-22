package androidx.camera.camera2.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.internal.compat.workaround.ExcludedSupportedSizesContainer;
import androidx.camera.camera2.internal.compat.workaround.TargetAspectRatio;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.SurfaceCombination;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.SurfaceSizeDefinition;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class p1 {
    public static final Size n = new Size(1920, 1080);
    public static final Size o = new Size(640, 480);
    public static final Size p = new Size(0, 0);
    public static final Size q = new Size(3840, 2160);
    public static final Size r = new Size(1920, 1080);
    public static final Size s = new Size(1280, 720);
    public static final Size t = new Size(720, 480);
    public static final Rational u = new Rational(4, 3);
    public static final Rational v = new Rational(3, 4);
    public static final Rational w = new Rational(16, 9);
    public static final Rational x = new Rational(9, 16);
    public final String c;
    public final androidx.camera.camera2.internal.b d;
    public final CameraCharacteristicsCompat e;
    public final ExcludedSupportedSizesContainer f;
    public final int g;
    public final boolean h;
    public SurfaceSizeDefinition l;

    /* renamed from: a  reason: collision with root package name */
    public final List<SurfaceCombination> f581a = new ArrayList();
    public final Map<Integer, Size> b = new HashMap();
    public final Map<Integer, List<Size>> i = new HashMap();
    public boolean j = false;
    public boolean k = false;
    public Map<Integer, Size[]> m = new HashMap();

    /* loaded from: classes.dex */
    public static final class a implements Comparator<Rational> {
        public Rational h;

        public a(Rational rational) {
            this.h = rational;
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Rational rational, Rational rational2) {
            if (rational.equals(rational2)) {
                return 0;
            }
            return (int) Math.signum(Float.valueOf(Math.abs(rational.floatValue() - this.h.floatValue())).floatValue() - Float.valueOf(Math.abs(rational2.floatValue() - this.h.floatValue())).floatValue());
        }
    }

    public p1(@NonNull Context context, @NonNull String str, @NonNull CameraManagerCompat cameraManagerCompat, @NonNull androidx.camera.camera2.internal.b bVar) throws CameraUnavailableException {
        String str2 = (String) Preconditions.checkNotNull(str);
        this.c = str2;
        this.d = (androidx.camera.camera2.internal.b) Preconditions.checkNotNull(bVar);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this.f = new ExcludedSupportedSizesContainer(str);
        try {
            CameraCharacteristicsCompat cameraCharacteristicsCompat = cameraManagerCompat.getCameraCharacteristicsCompat(str2);
            this.e = cameraCharacteristicsCompat;
            Integer num = (Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            this.g = num != null ? num.intValue() : 2;
            this.h = G();
            h();
            i(windowManager);
            a();
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    public static boolean D(Size size, Rational rational) {
        if (rational == null) {
            return false;
        }
        if (rational.equals(new Rational(size.getWidth(), size.getHeight()))) {
            return true;
        }
        if (l(size) >= l(o)) {
            return E(size, rational);
        }
        return false;
    }

    public static boolean E(Size size, Rational rational) {
        int width = size.getWidth();
        int height = size.getHeight();
        Rational rational2 = new Rational(rational.getDenominator(), rational.getNumerator());
        int i = width % 16;
        if (i == 0 && height % 16 == 0) {
            return H(Math.max(0, height + (-16)), width, rational) || H(Math.max(0, width + (-16)), height, rational2);
        } else if (i == 0) {
            return H(height, width, rational);
        } else {
            if (height % 16 == 0) {
                return H(width, height, rational2);
            }
            return false;
        }
    }

    public static boolean H(int i, int i2, Rational rational) {
        Preconditions.checkArgument(i2 % 16 == 0);
        double numerator = (i * rational.getNumerator()) / rational.getDenominator();
        return numerator > ((double) Math.max(0, i2 + (-16))) && numerator < ((double) (i2 + 16));
    }

    public static int l(Size size) {
        return size.getWidth() * size.getHeight();
    }

    @NonNull
    public static Size t(@NonNull WindowManager windowManager) {
        Size size;
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        if (point.x > point.y) {
            size = new Size(point.x, point.y);
        } else {
            size = new Size(point.y, point.x);
        }
        return (Size) Collections.min(Arrays.asList(new Size(size.getWidth(), size.getHeight()), n), new b());
    }

    @Nullable
    public final Size A(@NonNull ImageOutputConfig imageOutputConfig) {
        return g(imageOutputConfig.getTargetResolution(null), imageOutputConfig.getTargetRotation(0));
    }

    public final List<Integer> B(List<UseCaseConfig<?>> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList<Integer> arrayList2 = new ArrayList();
        for (UseCaseConfig<?> useCaseConfig : list) {
            int surfaceOccupancyPriority = useCaseConfig.getSurfaceOccupancyPriority(0);
            if (!arrayList2.contains(Integer.valueOf(surfaceOccupancyPriority))) {
                arrayList2.add(Integer.valueOf(surfaceOccupancyPriority));
            }
        }
        Collections.sort(arrayList2);
        Collections.reverse(arrayList2);
        for (Integer num : arrayList2) {
            int intValue = num.intValue();
            for (UseCaseConfig<?> useCaseConfig2 : list) {
                if (intValue == useCaseConfig2.getSurfaceOccupancyPriority(0)) {
                    arrayList.add(Integer.valueOf(list.indexOf(useCaseConfig2)));
                }
            }
        }
        return arrayList;
    }

    public final Map<Rational, List<Size>> C(List<Size> list) {
        HashMap hashMap = new HashMap();
        hashMap.put(u, new ArrayList());
        hashMap.put(w, new ArrayList());
        for (Size size : list) {
            Rational rational = null;
            for (Rational rational2 : hashMap.keySet()) {
                if (D(size, rational2)) {
                    List list2 = (List) hashMap.get(rational2);
                    if (!list2.contains(size)) {
                        list2.add(size);
                    }
                    rational = rational2;
                }
            }
            if (rational == null) {
                hashMap.put(new Rational(size.getWidth(), size.getHeight()), new ArrayList(Collections.singleton(size)));
            }
        }
        return hashMap;
    }

    public final boolean F(int i) {
        Integer num = (Integer) this.e.get(CameraCharacteristics.SENSOR_ORIENTATION);
        Preconditions.checkNotNull(num, "Camera HAL in bad state, unable to retrieve the SENSOR_ORIENTATION");
        int surfaceRotationToDegrees = CameraOrientationUtil.surfaceRotationToDegrees(i);
        Integer num2 = (Integer) this.e.get(CameraCharacteristics.LENS_FACING);
        Preconditions.checkNotNull(num2, "Camera HAL in bad state, unable to retrieve the LENS_FACING");
        int relativeImageRotation = CameraOrientationUtil.getRelativeImageRotation(surfaceRotationToDegrees, num.intValue(), 1 == num2.intValue());
        return relativeImageRotation == 90 || relativeImageRotation == 270;
    }

    public final boolean G() {
        Size size = (Size) this.e.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
        return size == null || size.getWidth() >= size.getHeight();
    }

    public final void I(List<Size> list, Size size) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i = -1;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            int i4 = i;
            i = i3;
            if (i >= list.size()) {
                break;
            }
            Size size2 = list.get(i);
            if (size2.getWidth() < size.getWidth() || size2.getHeight() < size.getHeight()) {
                break;
            }
            if (i4 >= 0) {
                arrayList.add(list.get(i4));
            }
            i2 = i + 1;
        }
        list.removeAll(arrayList);
    }

    public SurfaceConfig J(int i, Size size) {
        SurfaceConfig.ConfigType configType;
        SurfaceConfig.ConfigSize configSize = SurfaceConfig.ConfigSize.NOT_SUPPORT;
        if (i == 35) {
            configType = SurfaceConfig.ConfigType.YUV;
        } else if (i == 256) {
            configType = SurfaceConfig.ConfigType.JPEG;
        } else if (i == 32) {
            configType = SurfaceConfig.ConfigType.RAW;
        } else {
            configType = SurfaceConfig.ConfigType.PRIV;
        }
        Size f = f(i);
        if (size.getWidth() * size.getHeight() <= this.l.getAnalysisSize().getWidth() * this.l.getAnalysisSize().getHeight()) {
            configSize = SurfaceConfig.ConfigSize.ANALYSIS;
        } else if (size.getWidth() * size.getHeight() <= this.l.getPreviewSize().getWidth() * this.l.getPreviewSize().getHeight()) {
            configSize = SurfaceConfig.ConfigSize.PREVIEW;
        } else if (size.getWidth() * size.getHeight() <= this.l.getRecordSize().getWidth() * this.l.getRecordSize().getHeight()) {
            configSize = SurfaceConfig.ConfigSize.RECORD;
        } else if (size.getWidth() * size.getHeight() <= f.getWidth() * f.getHeight()) {
            configSize = SurfaceConfig.ConfigSize.MAXIMUM;
        }
        return SurfaceConfig.create(configType, configSize);
    }

    public final void a() {
    }

    public boolean b(List<SurfaceConfig> list) {
        Iterator<SurfaceCombination> it = this.f581a.iterator();
        boolean z = false;
        while (it.hasNext() && !(z = it.next().isSupported(list))) {
        }
        return z;
    }

    @NonNull
    public final Size[] c(int i) {
        Size[] outputSizes;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.e.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            if (Build.VERSION.SDK_INT < 23 && i == 34) {
                outputSizes = streamConfigurationMap.getOutputSizes(SurfaceTexture.class);
            } else {
                outputSizes = streamConfigurationMap.getOutputSizes(i);
            }
            if (outputSizes != null) {
                Size[] d = d(outputSizes, i);
                Arrays.sort(d, new b(true));
                return d;
            }
            throw new IllegalArgumentException("Can not get supported output size for the format: " + i);
        }
        throw new IllegalArgumentException("Can not retrieve SCALER_STREAM_CONFIGURATION_MAP");
    }

    @NonNull
    public final Size[] d(@NonNull Size[] sizeArr, int i) {
        List<Size> e = e(i);
        ArrayList arrayList = new ArrayList(Arrays.asList(sizeArr));
        arrayList.removeAll(e);
        return (Size[]) arrayList.toArray(new Size[0]);
    }

    @NonNull
    public final List<Size> e(int i) {
        List<Size> list = this.i.get(Integer.valueOf(i));
        if (list == null) {
            List<Size> list2 = this.f.get(i);
            this.i.put(Integer.valueOf(i), list2);
            return list2;
        }
        return list;
    }

    public final Size f(int i) {
        Size size = this.b.get(Integer.valueOf(i));
        if (size != null) {
            return size;
        }
        Size s2 = s(i);
        this.b.put(Integer.valueOf(i), s2);
        return s2;
    }

    @Nullable
    public final Size g(@Nullable Size size, int i) {
        return (size == null || !F(i)) ? size : new Size(size.getHeight(), size.getWidth());
    }

    public final void h() {
        this.f581a.addAll(p());
        int i = this.g;
        if (i == 0 || i == 1 || i == 3) {
            this.f581a.addAll(r());
        }
        int i2 = this.g;
        if (i2 == 1 || i2 == 3) {
            this.f581a.addAll(o());
        }
        int[] iArr = (int[]) this.e.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        if (iArr != null) {
            for (int i3 : iArr) {
                if (i3 == 3) {
                    this.j = true;
                } else if (i3 == 6) {
                    this.k = true;
                }
            }
        }
        if (this.j) {
            this.f581a.addAll(u());
        }
        if (this.k && this.g == 0) {
            this.f581a.addAll(m());
        }
        if (this.g == 3) {
            this.f581a.addAll(q());
        }
    }

    public final void i(WindowManager windowManager) {
        this.l = SurfaceSizeDefinition.create(new Size(640, 480), t(windowManager), v());
    }

    @NonNull
    public final Size[] j(int i) {
        Size[] sizeArr = this.m.get(Integer.valueOf(i));
        if (sizeArr == null) {
            Size[] c = c(i);
            this.m.put(Integer.valueOf(i), c);
            return c;
        }
        return sizeArr;
    }

    public final List<List<Size>> k(List<List<Size>> list) {
        int i = 1;
        for (List<Size> list2 : list) {
            i *= list2.size();
        }
        if (i != 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(new ArrayList());
            }
            int size = i / list.get(0).size();
            int i3 = i;
            for (int i4 = 0; i4 < list.size(); i4++) {
                List<Size> list3 = list.get(i4);
                for (int i5 = 0; i5 < i; i5++) {
                    ((List) arrayList.get(i5)).add(list3.get((i5 % i3) / size));
                }
                if (i4 < list.size() - 1) {
                    i3 = size;
                    size /= list.get(i4 + 1).size();
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("Failed to find supported resolutions.");
    }

    public List<SurfaceCombination> m() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        SurfaceConfig.ConfigType configType = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize configSize = SurfaceConfig.ConfigSize.PREVIEW;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        SurfaceConfig.ConfigSize configSize2 = SurfaceConfig.ConfigSize.MAXIMUM;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        SurfaceConfig.ConfigType configType2 = SurfaceConfig.ConfigType.YUV;
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        arrayList.add(surfaceCombination2);
        SurfaceCombination surfaceCombination3 = new SurfaceCombination();
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(configType2, configSize));
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        arrayList.add(surfaceCombination3);
        return arrayList;
    }

    @Nullable
    public final Size[] n(int i, @NonNull ImageOutputConfig imageOutputConfig) {
        Size[] sizeArr = null;
        List<Pair<Integer, Size[]>> supportedResolutions = imageOutputConfig.getSupportedResolutions(null);
        if (supportedResolutions != null) {
            Iterator<Pair<Integer, Size[]>> it = supportedResolutions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Pair<Integer, Size[]> next = it.next();
                if (((Integer) next.first).intValue() == i) {
                    sizeArr = (Size[]) next.second;
                    break;
                }
            }
        }
        if (sizeArr != null) {
            Size[] d = d(sizeArr, i);
            Arrays.sort(d, new b(true));
            return d;
        }
        return sizeArr;
    }

    public List<SurfaceCombination> o() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        SurfaceConfig.ConfigType configType = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize configSize = SurfaceConfig.ConfigSize.PREVIEW;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        SurfaceConfig.ConfigSize configSize2 = SurfaceConfig.ConfigSize.MAXIMUM;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        SurfaceConfig.ConfigType configType2 = SurfaceConfig.ConfigType.YUV;
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        arrayList.add(surfaceCombination2);
        SurfaceCombination surfaceCombination3 = new SurfaceCombination();
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(configType2, configSize));
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        arrayList.add(surfaceCombination3);
        SurfaceCombination surfaceCombination4 = new SurfaceCombination();
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, configSize2));
        arrayList.add(surfaceCombination4);
        SurfaceCombination surfaceCombination5 = new SurfaceCombination();
        SurfaceConfig.ConfigSize configSize3 = SurfaceConfig.ConfigSize.ANALYSIS;
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType2, configSize3));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        arrayList.add(surfaceCombination5);
        SurfaceCombination surfaceCombination6 = new SurfaceCombination();
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType2, configSize3));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType2, configSize));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        arrayList.add(surfaceCombination6);
        return arrayList;
    }

    public List<SurfaceCombination> p() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        SurfaceConfig.ConfigType configType = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize configSize = SurfaceConfig.ConfigSize.MAXIMUM;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        SurfaceConfig.ConfigType configType2 = SurfaceConfig.ConfigType.JPEG;
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType2, configSize));
        arrayList.add(surfaceCombination2);
        SurfaceCombination surfaceCombination3 = new SurfaceCombination();
        SurfaceConfig.ConfigType configType3 = SurfaceConfig.ConfigType.YUV;
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(configType3, configSize));
        arrayList.add(surfaceCombination3);
        SurfaceCombination surfaceCombination4 = new SurfaceCombination();
        SurfaceConfig.ConfigSize configSize2 = SurfaceConfig.ConfigSize.PREVIEW;
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(configType2, configSize));
        arrayList.add(surfaceCombination4);
        SurfaceCombination surfaceCombination5 = new SurfaceCombination();
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType3, configSize2));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType2, configSize));
        arrayList.add(surfaceCombination5);
        SurfaceCombination surfaceCombination6 = new SurfaceCombination();
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        arrayList.add(surfaceCombination6);
        SurfaceCombination surfaceCombination7 = new SurfaceCombination();
        surfaceCombination7.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        surfaceCombination7.addSurfaceConfig(SurfaceConfig.create(configType3, configSize2));
        arrayList.add(surfaceCombination7);
        SurfaceCombination surfaceCombination8 = new SurfaceCombination();
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(configType3, configSize2));
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(configType2, configSize));
        arrayList.add(surfaceCombination8);
        return arrayList;
    }

    public List<SurfaceCombination> q() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        SurfaceConfig.ConfigType configType = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize configSize = SurfaceConfig.ConfigSize.PREVIEW;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        SurfaceConfig.ConfigSize configSize2 = SurfaceConfig.ConfigSize.ANALYSIS;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        SurfaceConfig.ConfigType configType2 = SurfaceConfig.ConfigType.YUV;
        SurfaceConfig.ConfigSize configSize3 = SurfaceConfig.ConfigSize.MAXIMUM;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType2, configSize3));
        SurfaceConfig.ConfigType configType3 = SurfaceConfig.ConfigType.RAW;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType3, configSize3));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(SurfaceConfig.ConfigType.JPEG, configSize3));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType3, configSize3));
        arrayList.add(surfaceCombination2);
        return arrayList;
    }

    public List<SurfaceCombination> r() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        SurfaceConfig.ConfigType configType = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize configSize = SurfaceConfig.ConfigSize.PREVIEW;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        SurfaceConfig.ConfigSize configSize2 = SurfaceConfig.ConfigSize.RECORD;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        SurfaceConfig.ConfigType configType2 = SurfaceConfig.ConfigType.YUV;
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        arrayList.add(surfaceCombination2);
        SurfaceCombination surfaceCombination3 = new SurfaceCombination();
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(configType2, configSize));
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        arrayList.add(surfaceCombination3);
        SurfaceCombination surfaceCombination4 = new SurfaceCombination();
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(configType, configSize2));
        SurfaceConfig.ConfigType configType3 = SurfaceConfig.ConfigType.JPEG;
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(configType3, configSize2));
        arrayList.add(surfaceCombination4);
        SurfaceCombination surfaceCombination5 = new SurfaceCombination();
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType3, configSize2));
        arrayList.add(surfaceCombination5);
        SurfaceCombination surfaceCombination6 = new SurfaceCombination();
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType2, configSize));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType2, configSize));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType3, SurfaceConfig.ConfigSize.MAXIMUM));
        arrayList.add(surfaceCombination6);
        return arrayList;
    }

    public Size s(int i) {
        return (Size) Collections.max(Arrays.asList(j(i)), new b());
    }

    public List<SurfaceCombination> u() {
        ArrayList arrayList = new ArrayList();
        SurfaceCombination surfaceCombination = new SurfaceCombination();
        SurfaceConfig.ConfigType configType = SurfaceConfig.ConfigType.RAW;
        SurfaceConfig.ConfigSize configSize = SurfaceConfig.ConfigSize.MAXIMUM;
        surfaceCombination.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        arrayList.add(surfaceCombination);
        SurfaceCombination surfaceCombination2 = new SurfaceCombination();
        SurfaceConfig.ConfigType configType2 = SurfaceConfig.ConfigType.PRIV;
        SurfaceConfig.ConfigSize configSize2 = SurfaceConfig.ConfigSize.PREVIEW;
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        surfaceCombination2.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        arrayList.add(surfaceCombination2);
        SurfaceCombination surfaceCombination3 = new SurfaceCombination();
        SurfaceConfig.ConfigType configType3 = SurfaceConfig.ConfigType.YUV;
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(configType3, configSize2));
        surfaceCombination3.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        arrayList.add(surfaceCombination3);
        SurfaceCombination surfaceCombination4 = new SurfaceCombination();
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        surfaceCombination4.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        arrayList.add(surfaceCombination4);
        SurfaceCombination surfaceCombination5 = new SurfaceCombination();
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType3, configSize2));
        surfaceCombination5.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        arrayList.add(surfaceCombination5);
        SurfaceCombination surfaceCombination6 = new SurfaceCombination();
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType3, configSize2));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType3, configSize2));
        surfaceCombination6.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        arrayList.add(surfaceCombination6);
        SurfaceCombination surfaceCombination7 = new SurfaceCombination();
        surfaceCombination7.addSurfaceConfig(SurfaceConfig.create(configType2, configSize2));
        SurfaceConfig.ConfigType configType4 = SurfaceConfig.ConfigType.JPEG;
        surfaceCombination7.addSurfaceConfig(SurfaceConfig.create(configType4, configSize));
        surfaceCombination7.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        arrayList.add(surfaceCombination7);
        SurfaceCombination surfaceCombination8 = new SurfaceCombination();
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(configType3, configSize2));
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(configType4, configSize));
        surfaceCombination8.addSurfaceConfig(SurfaceConfig.create(configType, configSize));
        arrayList.add(surfaceCombination8);
        return arrayList;
    }

    @NonNull
    public final Size v() {
        Size size = t;
        try {
            int parseInt = Integer.parseInt(this.c);
            if (this.d.a(parseInt, 8)) {
                size = q;
            } else if (this.d.a(parseInt, 6)) {
                size = r;
            } else if (this.d.a(parseInt, 5)) {
                size = s;
            } else {
                this.d.a(parseInt, 4);
            }
            return size;
        } catch (NumberFormatException unused) {
            return w();
        }
    }

    @NonNull
    public final Size w() {
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.e.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(MediaRecorder.class);
            if (outputSizes == null) {
                return t;
            }
            Arrays.sort(outputSizes, new b(true));
            for (Size size : outputSizes) {
                int width = size.getWidth();
                Size size2 = r;
                if (width <= size2.getWidth() && size.getHeight() <= size2.getHeight()) {
                    return size;
                }
            }
            return t;
        }
        throw new IllegalArgumentException("Can not retrieve SCALER_STREAM_CONFIGURATION_MAP");
    }

    public Map<UseCaseConfig<?>, Size> x(List<SurfaceConfig> list, List<UseCaseConfig<?>> list2) {
        HashMap hashMap = new HashMap();
        List<Integer> B = B(list2);
        ArrayList arrayList = new ArrayList();
        for (Integer num : B) {
            arrayList.add(y(list2.get(num.intValue())));
        }
        Iterator<List<Size>> it = k(arrayList).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            List<Size> next = it.next();
            ArrayList arrayList2 = new ArrayList(list);
            for (int i = 0; i < next.size(); i++) {
                arrayList2.add(J(list2.get(B.get(i).intValue()).getInputFormat(), next.get(i)));
            }
            if (b(arrayList2)) {
                for (UseCaseConfig<?> useCaseConfig : list2) {
                    hashMap.put(useCaseConfig, next.get(B.indexOf(Integer.valueOf(list2.indexOf(useCaseConfig)))));
                }
            }
        }
        return hashMap;
    }

    @NonNull
    @VisibleForTesting
    public List<Size> y(@NonNull UseCaseConfig<?> useCaseConfig) {
        int inputFormat = useCaseConfig.getInputFormat();
        ImageOutputConfig imageOutputConfig = (ImageOutputConfig) useCaseConfig;
        Size[] n2 = n(inputFormat, imageOutputConfig);
        if (n2 == null) {
            n2 = j(inputFormat);
        }
        ArrayList arrayList = new ArrayList();
        Size maxResolution = imageOutputConfig.getMaxResolution(null);
        Size s2 = s(inputFormat);
        if (maxResolution == null || l(s2) < l(maxResolution)) {
            maxResolution = s2;
        }
        Arrays.sort(n2, new b(true));
        Size A = A(imageOutputConfig);
        Size size = o;
        int l = l(size);
        if (l(maxResolution) < l) {
            size = p;
        } else if (A != null && l(A) < l) {
            size = A;
        }
        for (Size size2 : n2) {
            if (l(size2) <= l(maxResolution) && l(size2) >= l(size) && !arrayList.contains(size2)) {
                arrayList.add(size2);
            }
        }
        if (!arrayList.isEmpty()) {
            Rational z = z(imageOutputConfig);
            if (A == null) {
                A = imageOutputConfig.getDefaultResolution(null);
            }
            List<Size> arrayList2 = new ArrayList<>();
            new HashMap();
            if (z == null) {
                arrayList2.addAll(arrayList);
                if (A != null) {
                    I(arrayList2, A);
                }
            } else {
                Map<Rational, List<Size>> C = C(arrayList);
                if (A != null) {
                    for (Rational rational : C.keySet()) {
                        I(C.get(rational), A);
                    }
                }
                ArrayList<Rational> arrayList3 = new ArrayList(C.keySet());
                Collections.sort(arrayList3, new a(z));
                for (Rational rational2 : arrayList3) {
                    for (Size size3 : C.get(rational2)) {
                        if (!arrayList2.contains(size3)) {
                            arrayList2.add(size3);
                        }
                    }
                }
            }
            return arrayList2;
        }
        throw new IllegalArgumentException("Can not get supported output size under supported maximum for the format: " + inputFormat);
    }

    public final Rational z(@NonNull ImageOutputConfig imageOutputConfig) {
        Rational rational;
        int i = new TargetAspectRatio().get(imageOutputConfig, this.c, this.e);
        if (i == 0) {
            rational = this.h ? u : v;
        } else if (i == 1) {
            rational = this.h ? w : x;
        } else if (i == 2) {
            Size f = f(256);
            return new Rational(f.getWidth(), f.getHeight());
        } else if (i != 3) {
            return null;
        } else {
            Size A = A(imageOutputConfig);
            if (!imageOutputConfig.hasTargetAspectRatio()) {
                if (A != null) {
                    return new Rational(A.getWidth(), A.getHeight());
                }
                return null;
            }
            int targetAspectRatio = imageOutputConfig.getTargetAspectRatio();
            if (targetAspectRatio == 0) {
                rational = this.h ? u : v;
            } else if (targetAspectRatio != 1) {
                Logger.e("SupportedSurfaceCombination", "Undefined target aspect ratio: " + targetAspectRatio);
                return null;
            } else {
                rational = this.h ? w : x;
            }
        }
        return rational;
    }

    /* loaded from: classes.dex */
    public static final class b implements Comparator<Size> {
        public boolean h;

        public b() {
            this.h = false;
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Size size, Size size2) {
            int signum = Long.signum((size.getWidth() * size.getHeight()) - (size2.getWidth() * size2.getHeight()));
            return this.h ? signum * (-1) : signum;
        }

        public b(boolean z) {
            this.h = false;
            this.h = z;
        }
    }
}
