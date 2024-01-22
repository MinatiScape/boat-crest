package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.ArrayCodec;
import com.alibaba.fastjson.serializer.BigDecimalCodec;
import com.alibaba.fastjson.serializer.BooleanCodec;
import com.alibaba.fastjson.serializer.CollectionCodec;
import com.alibaba.fastjson.serializer.DateCodec;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.MiscCodec;
import com.alibaba.fastjson.serializer.NumberCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class ParserConfig {
    public static long[] b = {-9164606388214699518L, -8720046426850100497L, -8649961213709896794L, -8165637398350707645L, -8109300701639721088L, -7966123100503199569L, -7921218830998286408L, -7775351613326101303L, -7768608037458185275L, -7766605818834748097L, -6835437086156813536L, -6316154655839304624L, -6179589609550493385L, -6025144546313590215L, -5939269048541779808L, -5885964883385605994L, -5764804792063216819L, -5472097725414717105L, -5194641081268104286L, -5076846148177416215L, -4837536971810737970L, -4703320437989596122L, -4608341446948126581L, -4537258998789938600L, -4438775680185074100L, -4314457471973557243L, -4150995715611818742L, -4082057040235125754L, -3975378478825053783L, -3935185854875733362L, -3319207949486691020L, -3077205613010077203L, -3053747177772160511L, -2995060141064716555L, -2825378362173150292L, -2533039401923731906L, -2439930098895578154L, -2378990704010641148L, -2364987994247679115L, -2262244760619952081L, -2192804397019347313L, -2095516571388852610L, -1872417015366588117L, -1650485814983027158L, -1589194880214235129L, -965955008570215305L, -905177026366752536L, -831789045734283466L, -582813228520337988L, -254670111376247151L, -219577392946377768L, -190281065685395680L, -26639035867733124L, -9822483067882491L, 4750336058574309L, 33238344207745342L, 156405680656087946L, 218512992947536312L, 313864100207897507L, 386461436234701831L, 823641066473609950L, 1073634739308289776L, 1153291637701043748L, 1203232727967308606L, 1214780596910349029L, 1459860845934817624L, 1502845958873959152L, 1534439610567445754L, 1698504441317515818L, 1818089308493370394L, 2078113382421334967L, 2164696723069287854L, 2622551729063269307L, 2653453629929770569L, 2660670623866180977L, 2731823439467737506L, 2836431254737891113L, 2930861374593775110L, 3085473968517218653L, 3089451460101527857L, 3114862868117605599L, 3129395579983849527L, 3256258368248066264L, 3452379460455804429L, 3547627781654598988L, 3637939656440441093L, 3688179072722109200L, 3718352661124136681L, 3730752432285826863L, 3794316665763266033L, 4000049462512838776L, 4046190361520671643L, 4147696707147271408L, 4193204392725694463L, 4241163808635564644L, 4254584350247334433L, 4814658433570175913L, 4841947709850912914L, 4904007817188630457L, 5100336081510080343L, 5274044858141538265L, 5347909877633654828L, 5450448828334921485L, 5474268165959054640L, 5545425291794704408L, 5596129856135573697L, 5688200883751798389L, 5751393439502795295L, 5944107969236155580L, 6007332606592876737L, 6280357960959217660L, 6456855723474196908L, 6511035576063254270L, 6534946468240507089L, 6584624952928234050L, 6734240326434096246L, 6742705432718011780L, 6854854816081053523L, 7045245923763966215L, 7123326897294507060L, 7179336928365889465L, 7240293012336844478L, 7347653049056829645L, 7375862386996623731L, 7442624256860549330L, 7617522210483516279L, 7658177784286215602L, 8055461369741094911L, 8389032537095247355L, 8409640769019589119L, 8488266005336625107L, 8537233257283452655L, 8838294710098435315L, 9140390920032557669L, 9140416208800006522L};
    public static ParserConfig global = new ParserConfig();

    /* renamed from: a  reason: collision with root package name */
    public final IdentityHashMap<ObjectDeserializer> f2120a;
    public boolean autoTypeSupport;
    public ClassLoader defaultClassLoader;
    public PropertyNamingStrategy propertyNamingStrategy;
    public final SymbolTable symbolTable;

    public ParserConfig() {
        IdentityHashMap<ObjectDeserializer> identityHashMap = new IdentityHashMap<>(1024);
        this.f2120a = identityHashMap;
        this.symbolTable = new SymbolTable(16384);
        MiscCodec miscCodec = MiscCodec.instance;
        identityHashMap.put(SimpleDateFormat.class, miscCodec);
        DateCodec dateCodec = DateCodec.instance;
        identityHashMap.put(Date.class, dateCodec);
        identityHashMap.put(Calendar.class, dateCodec);
        identityHashMap.put(Map.class, d.f2126a);
        identityHashMap.put(HashMap.class, d.f2126a);
        identityHashMap.put(LinkedHashMap.class, d.f2126a);
        identityHashMap.put(TreeMap.class, d.f2126a);
        identityHashMap.put(ConcurrentMap.class, d.f2126a);
        identityHashMap.put(ConcurrentHashMap.class, d.f2126a);
        CollectionCodec collectionCodec = CollectionCodec.instance;
        identityHashMap.put(Collection.class, collectionCodec);
        identityHashMap.put(List.class, collectionCodec);
        identityHashMap.put(ArrayList.class, collectionCodec);
        b bVar = b.f2124a;
        identityHashMap.put(Object.class, bVar);
        identityHashMap.put(String.class, StringCodec.instance);
        identityHashMap.put(Character.TYPE, miscCodec);
        identityHashMap.put(Character.class, miscCodec);
        Class cls = Byte.TYPE;
        NumberCodec numberCodec = NumberCodec.instance;
        identityHashMap.put(cls, numberCodec);
        identityHashMap.put(Byte.class, numberCodec);
        identityHashMap.put(Short.TYPE, numberCodec);
        identityHashMap.put(Short.class, numberCodec);
        identityHashMap.put(Integer.TYPE, IntegerCodec.instance);
        identityHashMap.put(Integer.class, IntegerCodec.instance);
        identityHashMap.put(Long.TYPE, IntegerCodec.instance);
        identityHashMap.put(Long.class, IntegerCodec.instance);
        BigDecimalCodec bigDecimalCodec = BigDecimalCodec.instance;
        identityHashMap.put(BigInteger.class, bigDecimalCodec);
        identityHashMap.put(BigDecimal.class, bigDecimalCodec);
        identityHashMap.put(Float.TYPE, numberCodec);
        identityHashMap.put(Float.class, numberCodec);
        identityHashMap.put(Double.TYPE, numberCodec);
        identityHashMap.put(Double.class, numberCodec);
        Class cls2 = Boolean.TYPE;
        BooleanCodec booleanCodec = BooleanCodec.instance;
        identityHashMap.put(cls2, booleanCodec);
        identityHashMap.put(Boolean.class, booleanCodec);
        identityHashMap.put(Class.class, miscCodec);
        ArrayCodec arrayCodec = ArrayCodec.instance;
        identityHashMap.put(char[].class, arrayCodec);
        identityHashMap.put(Object[].class, arrayCodec);
        identityHashMap.put(UUID.class, miscCodec);
        identityHashMap.put(TimeZone.class, miscCodec);
        identityHashMap.put(Locale.class, miscCodec);
        identityHashMap.put(Currency.class, miscCodec);
        identityHashMap.put(URI.class, miscCodec);
        identityHashMap.put(URL.class, miscCodec);
        identityHashMap.put(Pattern.class, miscCodec);
        identityHashMap.put(Charset.class, miscCodec);
        identityHashMap.put(Number.class, numberCodec);
        identityHashMap.put(StackTraceElement.class, miscCodec);
        identityHashMap.put(Serializable.class, bVar);
        identityHashMap.put(Cloneable.class, bVar);
        identityHashMap.put(Comparable.class, bVar);
        identityHashMap.put(Closeable.class, bVar);
    }

    public static ParserConfig getGlobalInstance() {
        return global;
    }

    public static boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() || cls == Boolean.class || cls == Character.class || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == BigInteger.class || cls == BigDecimal.class || cls == String.class || cls == Date.class || cls == java.sql.Date.class || cls == Time.class || cls == Timestamp.class;
    }

    public Class<?> checkAutoType(String str, Class<?> cls, int i) {
        if (str == null) {
            return null;
        }
        if (str.length() < 128) {
            if (str.length() >= 3) {
                long charAt = (str.charAt(0) ^ (-3750763034362895579L)) * 1099511628211L;
                if (charAt != -5808493101479473382L) {
                    if ((charAt ^ str.charAt(str.length() - 1)) * 1099511628211L != 655701488918567152L) {
                        long charAt2 = (((((str.charAt(0) ^ (-3750763034362895579L)) * 1099511628211L) ^ str.charAt(1)) * 1099511628211L) ^ str.charAt(2)) * 1099511628211L;
                        for (int i2 = 3; i2 < str.length(); i2++) {
                            charAt2 = (charAt2 ^ str.charAt(i2)) * 1099511628211L;
                            if (Arrays.binarySearch(b, charAt2) >= 0 && TypeUtils.getClassFromMapping(str) == null) {
                                throw new JSONException("autoType is not support. " + str);
                            }
                        }
                        Class<?> classFromMapping = TypeUtils.getClassFromMapping(str);
                        if (classFromMapping != null) {
                            return classFromMapping;
                        }
                        Class<?> findClass = this.f2120a.findClass(str);
                        if (findClass != null) {
                            return findClass;
                        }
                        Class<?> loadClass = TypeUtils.loadClass(str, this.defaultClassLoader, false);
                        if (loadClass != null && cls != null && loadClass != HashMap.class) {
                            if (cls.isAssignableFrom(loadClass)) {
                                TypeUtils.addMapping(str, loadClass);
                                return loadClass;
                            }
                            throw new JSONException("type not match. " + str + " -> " + cls.getName());
                        } else if (loadClass.isAnnotationPresent(JSONType.class)) {
                            TypeUtils.addMapping(str, loadClass);
                            return loadClass;
                        } else {
                            int i3 = Feature.SupportAutoType.mask;
                            if ((i & i3) == 0 && (i3 & JSON.DEFAULT_PARSER_FEATURE) == 0 && !this.autoTypeSupport) {
                                throw new JSONException("autoType is not support : " + str);
                            }
                            TypeUtils.addMapping(str, loadClass);
                            return loadClass;
                        }
                    }
                    throw new JSONException("autoType is not support. " + str);
                }
                throw new JSONException("autoType is not support. " + str);
            }
        }
        throw new JSONException("autoType is not support. " + str);
    }

    public boolean containsKey(Class cls) {
        return this.f2120a.get(cls) != null;
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        Class<?> cls2 = fieldInfo.fieldClass;
        if (cls2 != List.class && cls2 != ArrayList.class && (!cls2.isArray() || cls2.getComponentType().isPrimitive())) {
            return new DefaultFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        return new c(parserConfig, cls, fieldInfo);
    }

    public ObjectDeserializer getDeserializer(Type type) {
        ObjectDeserializer objectDeserializer = this.f2120a.get(type);
        if (objectDeserializer != null) {
            return objectDeserializer;
        }
        if (type instanceof Class) {
            return getDeserializer((Class) type, type);
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (rawType instanceof Class) {
                return getDeserializer((Class) rawType, type);
            }
            return getDeserializer(rawType);
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getDeserializer(upperBounds[0]);
            }
        }
        return b.f2124a;
    }

    public void putDeserializer(Type type, ObjectDeserializer objectDeserializer) {
        this.f2120a.put(type, objectDeserializer);
    }

    public ObjectDeserializer registerIfNotExists(Class<?> cls) {
        return registerIfNotExists(cls, cls.getModifiers(), false, true, true, true);
    }

    public ObjectDeserializer registerIfNotExists(Class<?> cls, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        ObjectDeserializer objectDeserializer = this.f2120a.get(cls);
        if (objectDeserializer != null) {
            return objectDeserializer;
        }
        JavaBeanDeserializer javaBeanDeserializer = new JavaBeanDeserializer(this, cls, cls, a.b(cls, i, cls, z, z2, z3, z4, this.propertyNamingStrategy));
        putDeserializer(cls, javaBeanDeserializer);
        return javaBeanDeserializer;
    }

    public ObjectDeserializer getDeserializer(Class<?> cls, Type type) {
        ObjectDeserializer objectDeserializer;
        JSONType jSONType;
        Class<?> mappingTo;
        ObjectDeserializer objectDeserializer2 = this.f2120a.get(type);
        if (objectDeserializer2 != null) {
            return objectDeserializer2;
        }
        if (type == null) {
            type = cls;
        }
        ObjectDeserializer objectDeserializer3 = this.f2120a.get(type);
        if (objectDeserializer3 != null) {
            return objectDeserializer3;
        }
        if (!isPrimitive(cls) && (jSONType = (JSONType) cls.getAnnotation(JSONType.class)) != null && (mappingTo = jSONType.mappingTo()) != Void.class) {
            return getDeserializer(mappingTo, mappingTo);
        }
        if ((type instanceof WildcardType) || (type instanceof TypeVariable) || (type instanceof ParameterizedType)) {
            objectDeserializer3 = this.f2120a.get(cls);
        }
        if (objectDeserializer3 != null) {
            return objectDeserializer3;
        }
        ObjectDeserializer objectDeserializer4 = this.f2120a.get(type);
        if (objectDeserializer4 != null) {
            return objectDeserializer4;
        }
        if (cls.isEnum()) {
            objectDeserializer = new EnumDeserializer(cls);
        } else if (cls.isArray()) {
            objectDeserializer = ArrayCodec.instance;
        } else if (cls != Set.class && cls != HashSet.class && cls != Collection.class && cls != List.class && cls != ArrayList.class) {
            if (Collection.class.isAssignableFrom(cls)) {
                objectDeserializer = CollectionCodec.instance;
            } else if (Map.class.isAssignableFrom(cls)) {
                objectDeserializer = d.f2126a;
            } else if (Throwable.class.isAssignableFrom(cls)) {
                objectDeserializer = new ThrowableDeserializer(this, cls);
            } else if (cls.getName().equals("android.net.Uri")) {
                objectDeserializer = MiscCodec.instance;
            } else {
                objectDeserializer = new JavaBeanDeserializer(this, cls, type);
            }
        } else {
            objectDeserializer = CollectionCodec.instance;
        }
        putDeserializer(type, objectDeserializer);
        return objectDeserializer;
    }
}
