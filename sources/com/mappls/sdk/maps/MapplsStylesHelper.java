package com.mappls.sdk.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.mappls.sdk.maps.MapplsGetStyle;
import com.mappls.sdk.maps.style.IStyleListener;
import com.mappls.sdk.maps.style.model.MapplsStyle;
import com.mappls.sdk.maps.utils.BitmapUtils;
import com.mappls.sdk.services.utils.MapplsUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Keep
/* loaded from: classes11.dex */
public class MapplsStylesHelper {
    private static final String STYLE_ERROR_CODE = "Something went wrong - 101";
    private String baseUrl;
    private MapplsStyle defaultStyle;
    private String logoBaseUrl;
    private final Context mContext;
    private final z preferenceHelper;
    private final List<MapplsStyle> styleList = new ArrayList();
    private final HashMap<String, StyleData> styleHashMap = new HashMap<>();
    private final HashMap<String, Bitmap> logoHashMap = new HashMap<>();
    private final HashMap<String, LogoData> logoDataHashMap = new HashMap<>();

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ String h;
        public final /* synthetic */ t i;

        public a(MapplsStylesHelper mapplsStylesHelper, String str, t tVar) {
            this.h = str;
            this.i = tVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            URL url;
            Bitmap bitmap = null;
            try {
                url = new URL(this.h);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                url = null;
            }
            try {
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.i.a(bitmap);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements t {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ LogoData f12650a;
        public final /* synthetic */ File b;
        public final /* synthetic */ LogoImageLoadCallback c;

        public b(LogoData logoData, File file, LogoImageLoadCallback logoImageLoadCallback) {
            this.f12650a = logoData;
            this.b = file;
            this.c = logoImageLoadCallback;
        }

        @Override // com.mappls.sdk.maps.t
        public void a(Bitmap bitmap) {
            MapplsStylesHelper.this.logoHashMap.put(this.f12650a.getLogoId(), bitmap);
            MapplsStylesHelper.this.saveImageToFile(bitmap, this.b.getAbsolutePath());
            LogoImageLoadCallback logoImageLoadCallback = this.c;
            if (logoImageLoadCallback != null) {
                logoImageLoadCallback.onSuccess(bitmap);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Callback<GetStylesResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ IStyleListener f12651a;

        public c(IStyleListener iStyleListener) {
            this.f12651a = iStyleListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(@NonNull Call<GetStylesResponse> call, @NonNull Throwable th) {
            th.printStackTrace();
            if (MapplsStylesHelper.this.preferenceHelper.b() != null) {
                MapplsStylesHelper.this.initStyles(MapplsStylesHelper.this.preferenceHelper.b());
                IStyleListener iStyleListener = this.f12651a;
                if (iStyleListener != null) {
                    iStyleListener.onSuccess();
                    return;
                }
                return;
            }
            IStyleListener iStyleListener2 = this.f12651a;
            if (iStyleListener2 != null) {
                iStyleListener2.onFailure(5, MapplsStylesHelper.STYLE_ERROR_CODE);
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(@NonNull Call<GetStylesResponse> call, @NonNull Response<GetStylesResponse> response) {
            if (response.code() == 200) {
                GetStylesResponse body = response.body();
                MapplsStylesHelper.this.preferenceHelper.d(body);
                MapplsStylesHelper.this.initStyles(body);
                IStyleListener iStyleListener = this.f12651a;
                if (iStyleListener != null) {
                    iStyleListener.onSuccess();
                }
            } else if (response.code() != 7 && response.code() != 8 && response.code() != 9 && response.code() != 101 && response.code() != 102 && response.code() != 103) {
                IStyleListener iStyleListener2 = this.f12651a;
                if (iStyleListener2 != null) {
                    iStyleListener2.onFailure(response.code(), MapplsStylesHelper.STYLE_ERROR_CODE);
                }
            } else {
                IStyleListener iStyleListener3 = this.f12651a;
                if (iStyleListener3 != null) {
                    iStyleListener3.onFailure(response.code(), response.message());
                }
            }
        }
    }

    public MapplsStylesHelper(Context context) {
        this.mContext = context;
        this.preferenceHelper = new z(context);
    }

    private void downloadImage(String str, LogoData logoData, LogoImageLoadCallback logoImageLoadCallback) {
        File[] listFiles;
        File file = new File(this.mContext.getFilesDir(), logoData.getLogoId());
        file.mkdir();
        File file2 = new File(file, logoData.getModified() + ".jpg");
        if (file2.exists()) {
            Bitmap decodeFile = BitmapFactory.decodeFile(file2.getAbsolutePath());
            this.logoHashMap.put(logoData.getLogoId(), decodeFile);
            if (logoImageLoadCallback != null) {
                logoImageLoadCallback.onSuccess(decodeFile);
                return;
            }
            return;
        }
        for (File file3 : file.listFiles()) {
            if (file3 != null) {
                file3.delete();
            }
        }
        getBitmap(str + logoData.getLogoUrl(), new b(logoData, file2, logoImageLoadCallback));
    }

    private String getBaseUrl() {
        return this.baseUrl;
    }

    private void getBitmap(String str, t tVar) {
        Executors.newSingleThreadExecutor().execute(new a(this, str, tVar));
    }

    private void getLogos(String str, List<LogoData> list) {
        if (list == null) {
            return;
        }
        for (LogoData logoData : list) {
            this.logoDataHashMap.put(logoData.getLogoId(), logoData);
            downloadImage(str, logoData, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initStyles(GetStylesResponse getStylesResponse) {
        this.baseUrl = getStylesResponse.getBaseUrl();
        this.logoBaseUrl = getStylesResponse.getBaseUrlLogo();
        this.styleList.clear();
        for (StyleData styleData : getStylesResponse.getData()) {
            String name = styleData.getName();
            String displayName = styleData.getDisplayName();
            String description = styleData.getDescription();
            MapplsStyle mapplsStyle = new MapplsStyle(name, displayName, description, this.baseUrl + styleData.getImageUrl(), styleData.isDefault());
            this.styleList.add(mapplsStyle);
            this.styleHashMap.put(styleData.getName(), styleData);
            if (styleData.isDefault().intValue() == 1) {
                this.defaultStyle = mapplsStyle;
            }
        }
        getLogos(getStylesResponse.getBaseUrlLogo(), getStylesResponse.getLogoData());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveImageToFile(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(str);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                bitmap.setHasAlpha(true);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.close();
            } catch (Exception e2) {
                e = e2;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
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

    public synchronized void getBitmapLogo(String str, @NonNull LogoImageLoadCallback logoImageLoadCallback) {
        StyleData styleData = this.styleHashMap.get(str);
        if (styleData != null && styleData.getLogoIdIndia() != null) {
            if (this.logoHashMap.containsKey(styleData.getLogoIdIndia())) {
                logoImageLoadCallback.onSuccess(this.logoHashMap.get(styleData.getLogoIdIndia()));
            } else if (this.logoDataHashMap.containsKey(styleData.getLogoIdIndia())) {
                downloadImage(this.logoBaseUrl, this.logoDataHashMap.get(styleData.getLogoIdIndia()), logoImageLoadCallback);
            } else {
                logoImageLoadCallback.onFailure();
            }
        } else {
            logoImageLoadCallback.onSuccess(BitmapUtils.getBitmapFromDrawable(ContextCompat.getDrawable(Mappls.getApplicationContext(), R.drawable.mappls_maps_logo_icon)));
        }
    }

    public MapplsStyle getDefaultStyle() {
        return this.defaultStyle;
    }

    public synchronized void getGlobalBitmapLogo(String str, @NonNull LogoImageLoadCallback logoImageLoadCallback) {
        StyleData styleData = this.styleHashMap.get(str);
        if (styleData != null && styleData.getGlobalLogoId() != null) {
            if (this.logoHashMap.containsKey(styleData.getGlobalLogoId())) {
                logoImageLoadCallback.onSuccess(this.logoHashMap.get(styleData.getGlobalLogoId()));
            } else if (this.logoDataHashMap.containsKey(styleData.getGlobalLogoId())) {
                downloadImage(this.logoBaseUrl, this.logoDataHashMap.get(styleData.getGlobalLogoId()), logoImageLoadCallback);
            } else {
                logoImageLoadCallback.onFailure();
            }
        } else {
            logoImageLoadCallback.onSuccess(BitmapUtils.getBitmapFromDrawable(ContextCompat.getDrawable(Mappls.getApplicationContext(), R.drawable.mappls_maps_logo_icon)));
        }
    }

    public MapplsStyle getLastSelectedStyle() {
        return getStyle(this.preferenceHelper.a());
    }

    public MapplsStyle getStyle(String str) {
        MapplsStyle mapplsStyle = null;
        if (str != null) {
            for (MapplsStyle mapplsStyle2 : this.styleList) {
                if (mapplsStyle2.getName().equalsIgnoreCase(str)) {
                    mapplsStyle = mapplsStyle2;
                }
            }
        }
        return mapplsStyle;
    }

    public List<MapplsStyle> getStyleList() {
        return this.styleList;
    }

    public String getStyleUrl(MapplsStyle mapplsStyle) {
        if (this.styleHashMap.containsKey(mapplsStyle.getName())) {
            StyleData styleData = this.styleHashMap.get(mapplsStyle.getName());
            if (!styleData.getStyleUrl().startsWith("https://") && !styleData.getStyleUrl().startsWith("http://")) {
                return this.baseUrl + styleData.getStyleUrl();
            }
            return styleData.getStyleUrl();
        }
        return null;
    }

    public void initialiseStyles(IStyleListener iStyleListener) {
        if (this.baseUrl != null && this.styleList != null && this.defaultStyle != null) {
            if (iStyleListener != null) {
                iStyleListener.onSuccess();
                return;
            }
            return;
        }
        MapplsGetStyle.Builder a2 = MapplsGetStyle.a();
        a2.logoResolution("drawable-" + MapplsUtils.getDensityName()).build().enqueueCall(new c(iStyleListener));
    }

    public void setSelectedStyle(String str) {
        this.preferenceHelper.c(str);
    }
}
