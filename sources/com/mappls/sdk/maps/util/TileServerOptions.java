package com.mappls.sdk.maps.util;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.WellKnownTileServer;
import com.mappls.sdk.maps.exceptions.MapplsConfigurationException;
/* loaded from: classes11.dex */
public class TileServerOptions implements Parcelable {
    public static final Parcelable.Creator<TileServerOptions> CREATOR = new a();
    @Keep
    private String apiKeyParameterName;
    @Keep
    private boolean apiKeyRequired;
    @Keep
    private String baseURL;
    @Keep
    private String defaultStyle;
    @Keep
    private DefaultStyle[] defaultStyles;
    @Keep
    private String glyphsDomainName;
    @Keep
    private String glyphsTemplate;
    @Nullable
    @Keep
    private String glyphsVersionPrefix;
    @Keep
    private String sourceDomainName;
    @Keep
    private String sourceTemplate;
    @Nullable
    @Keep
    private String sourceVersionPrefix;
    @Keep
    private String spritesDomainName;
    @Keep
    private String spritesTemplate;
    @Nullable
    @Keep
    private String spritesVersionPrefix;
    @Keep
    private String styleDomainName;
    @Keep
    private String styleTemplate;
    @Nullable
    @Keep
    private String styleVersionPrefix;
    @Keep
    private String tileDomainName;
    @Keep
    private String tileTemplate;
    @Nullable
    @Keep
    private String tileVersionPrefix;
    @Keep
    private String uriSchemeAlias;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<TileServerOptions> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public TileServerOptions createFromParcel(@NonNull Parcel parcel) {
            return new TileServerOptions(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public TileServerOptions[] newArray(int i) {
            return new TileServerOptions[i];
        }
    }

    /* loaded from: classes11.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f12853a;

        static {
            int[] iArr = new int[WellKnownTileServer.values().length];
            f12853a = iArr;
            try {
                iArr[WellKnownTileServer.MAPPLS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f12853a[WellKnownTileServer.MapTiler.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f12853a[WellKnownTileServer.MapLibre.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Keep
    public TileServerOptions(String str, String str2, String str3, String str4, @Nullable String str5, String str6, String str7, @Nullable String str8, String str9, String str10, @Nullable String str11, String str12, String str13, @Nullable String str14, String str15, String str16, @Nullable String str17, String str18, boolean z, String str19, DefaultStyle[] defaultStyleArr) {
        setBaseURL(str);
        setUriSchemeAlias(str2);
        setSourceTemplate(str3);
        setSourceDomainName(str4);
        setSourceVersionPrefix(str5);
        setStyleTemplate(str6);
        setStyleDomainName(str7);
        setStyleVersionPrefix(str8);
        setSpritesTemplate(str9);
        setSpritesDomainName(str10);
        setSpritesVersionPrefix(str11);
        setGlyphsTemplate(str12);
        setGlyphsDomainName(str13);
        setGlyphsVersionPrefix(str14);
        setTileTemplate(str15);
        setTileDomainName(str16);
        setTileVersionPrefix(str17);
        setApiKeyParameterName(str18);
        setDefaultStyles(defaultStyleArr);
        setDefaultStyle(str19);
        setApiKeyRequired(z);
    }

    @NonNull
    @Keep
    private static native TileServerOptions defaultConfiguration();

    public static TileServerOptions get(WellKnownTileServer wellKnownTileServer) {
        int i = b.f12853a[wellKnownTileServer.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return mapLibreConfiguration();
                }
                throw new MapplsConfigurationException("Unknown tile server");
            }
            return mapTilerConfiguration();
        }
        return mapplsConfiguration();
    }

    @NonNull
    @Keep
    private static native TileServerOptions mapLibreConfiguration();

    @NonNull
    @Keep
    private static native TileServerOptions mapTilerConfiguration();

    @NonNull
    @Keep
    private static native TileServerOptions mapplsConfiguration();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getApiKeyParameterName() {
        return this.apiKeyParameterName;
    }

    public boolean getApiKeyRequired() {
        return this.apiKeyRequired;
    }

    public String getBaseURL() {
        return this.baseURL;
    }

    public String getDefaultStyle() {
        return this.defaultStyle;
    }

    public DefaultStyle[] getDefaultStyles() {
        return this.defaultStyles;
    }

    public String getGlyphsDomainName() {
        return this.glyphsDomainName;
    }

    public String getGlyphsTemplate() {
        return this.glyphsTemplate;
    }

    public String getGlyphsVersionPrefix() {
        return this.glyphsVersionPrefix;
    }

    public String getSourceDomainName() {
        return this.sourceDomainName;
    }

    public String getSourceTemplate() {
        return this.sourceTemplate;
    }

    public String getSourceVersionPrefix() {
        return this.sourceVersionPrefix;
    }

    public String getSpritesDomainName() {
        return this.spritesDomainName;
    }

    public String getSpritesTemplate() {
        return this.spritesTemplate;
    }

    public String getSpritesVersionPrefix() {
        return this.spritesVersionPrefix;
    }

    public String getStyleDomainName() {
        return this.styleDomainName;
    }

    public String getStyleTemplate() {
        return this.styleTemplate;
    }

    public String getStyleVersionPrefix() {
        return this.styleVersionPrefix;
    }

    public String getTileDomainName() {
        return this.tileDomainName;
    }

    public String getTileTemplate() {
        return this.tileTemplate;
    }

    public String getTileVersionPrefix() {
        return this.tileVersionPrefix;
    }

    public String getUriSchemeAlias() {
        return this.uriSchemeAlias;
    }

    public void setApiKeyParameterName(String str) {
        this.apiKeyParameterName = str;
    }

    public void setApiKeyRequired(boolean z) {
        this.apiKeyRequired = z;
    }

    public void setBaseURL(String str) {
        this.baseURL = str;
    }

    public void setDefaultStyle(String str) {
        this.defaultStyle = str;
    }

    public void setDefaultStyles(DefaultStyle[] defaultStyleArr) {
        this.defaultStyles = defaultStyleArr;
    }

    public void setGlyphsDomainName(String str) {
        this.glyphsDomainName = str;
    }

    public void setGlyphsTemplate(String str) {
        this.glyphsTemplate = str;
    }

    public void setGlyphsVersionPrefix(String str) {
        this.glyphsVersionPrefix = str;
    }

    public void setSourceDomainName(String str) {
        this.sourceDomainName = str;
    }

    public void setSourceTemplate(String str) {
        this.sourceTemplate = str;
    }

    public void setSourceVersionPrefix(String str) {
        this.sourceVersionPrefix = str;
    }

    public void setSpritesDomainName(String str) {
        this.spritesDomainName = str;
    }

    public void setSpritesTemplate(String str) {
        this.spritesTemplate = str;
    }

    public void setSpritesVersionPrefix(String str) {
        this.spritesVersionPrefix = str;
    }

    public void setStyleDomainName(String str) {
        this.styleDomainName = str;
    }

    public void setStyleTemplate(String str) {
        this.styleTemplate = str;
    }

    public void setStyleVersionPrefix(String str) {
        this.styleVersionPrefix = str;
    }

    public void setTileDomainName(String str) {
        this.tileDomainName = str;
    }

    public void setTileTemplate(String str) {
        this.tileTemplate = str;
    }

    public void setTileVersionPrefix(String str) {
        this.tileVersionPrefix = str;
    }

    public void setUriSchemeAlias(String str) {
        this.uriSchemeAlias = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.baseURL);
        parcel.writeString(this.uriSchemeAlias);
        parcel.writeString(this.sourceTemplate);
        parcel.writeString(this.sourceDomainName);
        parcel.writeString(this.sourceVersionPrefix);
        parcel.writeString(this.styleTemplate);
        parcel.writeString(this.styleDomainName);
        parcel.writeString(this.styleVersionPrefix);
        parcel.writeString(this.spritesTemplate);
        parcel.writeString(this.spritesDomainName);
        parcel.writeString(this.spritesVersionPrefix);
        parcel.writeString(this.glyphsTemplate);
        parcel.writeString(this.glyphsDomainName);
        parcel.writeString(this.glyphsVersionPrefix);
        parcel.writeString(this.tileTemplate);
        parcel.writeString(this.tileDomainName);
        parcel.writeString(this.tileVersionPrefix);
        parcel.writeString(this.apiKeyParameterName);
        parcel.writeByte(this.apiKeyRequired ? (byte) 1 : (byte) 0);
        parcel.writeString(this.defaultStyle);
        parcel.writeTypedArray(this.defaultStyles, 0);
    }

    public TileServerOptions(Parcel parcel) {
        setBaseURL(parcel.readString());
        setUriSchemeAlias(parcel.readString());
        setSourceTemplate(parcel.readString());
        setSourceDomainName(parcel.readString());
        setSourceVersionPrefix(parcel.readString());
        setStyleTemplate(parcel.readString());
        setStyleDomainName(parcel.readString());
        setStyleVersionPrefix(parcel.readString());
        setSpritesTemplate(parcel.readString());
        setSpritesDomainName(parcel.readString());
        setSpritesVersionPrefix(parcel.readString());
        setGlyphsTemplate(parcel.readString());
        setGlyphsDomainName(parcel.readString());
        setGlyphsVersionPrefix(parcel.readString());
        setTileTemplate(parcel.readString());
        setTileDomainName(parcel.readString());
        setTileVersionPrefix(parcel.readString());
        setApiKeyParameterName(parcel.readString());
        setApiKeyRequired(parcel.readByte() != 0);
        setDefaultStyle(parcel.readString());
        parcel.createTypedArray(DefaultStyle.CREATOR);
    }
}
