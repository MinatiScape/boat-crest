package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
/* loaded from: classes.dex */
public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    public ParserConfig config;
    public ParseContext contex;
    public List<ExtraProcessor> extraProcessors;
    public List<ExtraTypeProvider> extraTypeProviders;
    public FieldTypeResolver fieldTypeResolver;
    public String h;
    public DateFormat i;
    public ParseContext[] j;
    public int k;
    public List<ResolveTask> l;
    public final JSONLexer lexer;
    public int resolveStatus;
    public final SymbolTable symbolTable;

    /* loaded from: classes.dex */
    public static class ResolveTask {

        /* renamed from: a  reason: collision with root package name */
        public final ParseContext f2115a;
        public final String b;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;

        public ResolveTask(ParseContext parseContext, String str) {
            this.f2115a = parseContext;
            this.b = str;
        }
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.global, JSON.DEFAULT_PARSER_FEATURE);
    }

    public final void accept(int i) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token == i) {
            jSONLexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(this.lexer.token));
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.l == null) {
            this.l = new ArrayList(2);
        }
        this.l.add(resolveTask);
    }

    public void checkListResolve(Collection collection) {
        if (collection instanceof List) {
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = new e(this, (List) collection, collection.size() - 1);
            lastResolveTask.ownerContext = this.contex;
            this.resolveStatus = 0;
            return;
        }
        ResolveTask lastResolveTask2 = getLastResolveTask();
        lastResolveTask2.fieldDeserializer = new e(collection);
        lastResolveTask2.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    public void checkMapResolve(Map map, Object obj) {
        e eVar = new e(map, obj);
        ResolveTask lastResolveTask = getLastResolveTask();
        lastResolveTask.fieldDeserializer = eVar;
        lastResolveTask.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            JSONLexer jSONLexer = this.lexer;
            if (jSONLexer.token == 20) {
                jSONLexer.close();
                return;
            }
            throw new JSONException("not close json text, token : " + JSONToken.name(this.lexer.token));
        } catch (Throwable th) {
            this.lexer.close();
            throw th;
        }
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public String getDateFomartPattern() {
        return this.h;
    }

    public DateFormat getDateFormat() {
        if (this.i == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.h, this.lexer.locale);
            this.i = simpleDateFormat;
            simpleDateFormat.setTimeZone(this.lexer.timeZone);
        }
        return this.i;
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public ResolveTask getLastResolveTask() {
        List<ResolveTask> list = this.l;
        return list.get(list.size() - 1);
    }

    public void handleResovleTask(Object obj) {
        List<ResolveTask> list = this.l;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ResolveTask resolveTask = this.l.get(i);
            FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
            if (fieldDeserializer != null) {
                ParseContext parseContext = resolveTask.ownerContext;
                Object obj2 = null;
                Object obj3 = parseContext != null ? parseContext.object : null;
                String str = resolveTask.b;
                if (!str.startsWith("$")) {
                    obj2 = resolveTask.f2115a.object;
                } else {
                    for (int i2 = 0; i2 < this.k; i2++) {
                        if (str.equals(this.j[i2].toString())) {
                            obj2 = this.j[i2].object;
                        }
                    }
                }
                fieldDeserializer.setValue(obj3, obj2);
            }
        }
    }

    public Object parse() {
        return parse(null);
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public Object parseArrayWithType(Type type) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token == 8) {
            jSONLexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length == 1) {
            Type type2 = actualTypeArguments[0];
            if (type2 instanceof Class) {
                ArrayList arrayList = new ArrayList();
                parseArray((Class) type2, (Collection) arrayList);
                return arrayList;
            } else if (type2 instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type2;
                Type type3 = wildcardType.getUpperBounds()[0];
                if (Object.class.equals(type3)) {
                    if (wildcardType.getLowerBounds().length == 0) {
                        return parse();
                    }
                    throw new JSONException("not support type : " + type);
                }
                ArrayList arrayList2 = new ArrayList();
                parseArray((Class) type3, (Collection) arrayList2);
                return arrayList2;
            } else {
                if (type2 instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) type2;
                    Type[] bounds = typeVariable.getBounds();
                    if (bounds.length == 1) {
                        Type type4 = bounds[0];
                        if (type4 instanceof Class) {
                            ArrayList arrayList3 = new ArrayList();
                            parseArray((Class) type4, (Collection) arrayList3);
                            return arrayList3;
                        }
                    } else {
                        throw new JSONException("not support : " + typeVariable);
                    }
                }
                if (type2 instanceof ParameterizedType) {
                    ArrayList arrayList4 = new ArrayList();
                    parseArray((ParameterizedType) type2, arrayList4);
                    return arrayList4;
                }
                throw new JSONException("TODO : " + type);
            }
        }
        throw new JSONException("not support type " + type);
    }

    /* JADX WARN: Code restructure failed: missing block: B:140:0x024b, code lost:
        r3.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0252, code lost:
        if (r3.token != 13) goto L355;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0254, code lost:
        r3.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0257, code lost:
        r2 = r18.config.getDeserializer(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x025f, code lost:
        if ((r2 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer) == false) goto L351;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0261, code lost:
        r2 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r2;
        r3 = r2.createInstance(r18, r7);
        r0 = r19.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0273, code lost:
        if (r0.hasNext() == false) goto L339;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0275, code lost:
        r4 = (java.util.Map.Entry) r0.next();
        r5 = r4.getKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0281, code lost:
        if ((r5 instanceof java.lang.String) == false) goto L338;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0283, code lost:
        r5 = r2.getFieldDeserializer((java.lang.String) r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0289, code lost:
        if (r5 == null) goto L337;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x028b, code lost:
        r5.setValue(r3, r4.getValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0293, code lost:
        r3 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0294, code lost:
        if (r3 != null) goto L348;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0298, code lost:
        if (r7 != java.lang.Cloneable.class) goto L344;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x029a, code lost:
        r3 = new java.util.HashMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x02a6, code lost:
        if ("java.util.Collections$EmptyMap".equals(r6) == false) goto L347;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x02a8, code lost:
        r3 = java.util.Collections.emptyMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x02ad, code lost:
        r3 = r7.newInstance();
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x02b1, code lost:
        if (r13 != false) goto L350;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x02b3, code lost:
        r18.contex = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x02b5, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x02b6, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x02be, code lost:
        throw new com.alibaba.fastjson.JSONException("create instance error", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x02bf, code lost:
        r18.resolveStatus = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x02c4, code lost:
        if (r18.contex == null) goto L360;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x02c8, code lost:
        if ((r20 instanceof java.lang.Integer) != false) goto L360;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x02ca, code lost:
        popContext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x02d1, code lost:
        if (r19.size() <= 0) goto L366;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x02d3, code lost:
        r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r19, (java.lang.Class<java.lang.Object>) r7, r18.config);
        parseObject(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x02dc, code lost:
        if (r13 != false) goto L365;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x02de, code lost:
        r18.contex = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x02e0, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x02e1, code lost:
        r0 = r18.config.getDeserializer(r7);
        r2 = r0.deserialze(r18, r7, r20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x02ed, code lost:
        if ((r0 instanceof com.alibaba.fastjson.parser.d) == false) goto L369;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x02ef, code lost:
        r18.resolveStatus = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x02f2, code lost:
        if (r13 != false) goto L371;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x02f4, code lost:
        r18.contex = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x02f6, code lost:
        return r2;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01fb A[Catch: all -> 0x06d1, TryCatch #0 {all -> 0x06d1, blocks: (B:21:0x0063, B:24:0x006d, B:27:0x0076, B:31:0x0089, B:33:0x0093, B:36:0x009b, B:37:0x00b9, B:96:0x01c1, B:100:0x01d3, B:116:0x01f2, B:119:0x01ff, B:122:0x0206, B:124:0x020e, B:125:0x0217, B:127:0x021d, B:132:0x022a, B:136:0x0232, B:139:0x0240, B:140:0x024b, B:142:0x0254, B:143:0x0257, B:145:0x0261, B:146:0x026f, B:148:0x0275, B:150:0x0283, B:152:0x028b, B:157:0x029a, B:158:0x02a0, B:160:0x02a8, B:161:0x02ad, B:166:0x02b7, B:167:0x02be, B:168:0x02bf, B:170:0x02c6, B:172:0x02ca, B:173:0x02cd, B:175:0x02d3, B:179:0x02e1, B:181:0x02ef, B:189:0x02ff, B:191:0x0307, B:193:0x030e, B:195:0x031d, B:197:0x0325, B:200:0x032a, B:202:0x032e, B:221:0x0378, B:223:0x037c, B:227:0x0386, B:228:0x039e, B:203:0x0331, B:205:0x0339, B:208:0x033f, B:209:0x034b, B:212:0x0354, B:215:0x035a, B:218:0x0360, B:219:0x036c, B:229:0x039f, B:230:0x03bb, B:233:0x03c0, B:239:0x03d1, B:241:0x03d7, B:243:0x03e3, B:244:0x03e9, B:246:0x03ee, B:346:0x0586, B:350:0x0590, B:353:0x0599, B:357:0x05ac, B:356:0x05a6, B:360:0x05b8, B:364:0x05cb, B:366:0x05d4, B:370:0x05e7, B:387:0x062f, B:369:0x05e1, B:373:0x05f2, B:377:0x0605, B:376:0x05ff, B:380:0x0610, B:384:0x0623, B:383:0x061d, B:385:0x062a, B:363:0x05c5, B:391:0x0639, B:392:0x0651, B:247:0x03f2, B:254:0x0402, B:258:0x0411, B:262:0x0428, B:264:0x0431, B:269:0x043e, B:270:0x0441, B:272:0x044b, B:274:0x0452, B:276:0x0456, B:284:0x046b, B:285:0x0483, B:273:0x044f, B:261:0x0422, B:288:0x0488, B:292:0x049b, B:294:0x04ac, B:298:0x04c0, B:300:0x04c6, B:303:0x04cc, B:305:0x04d6, B:307:0x04de, B:311:0x04f0, B:314:0x04f8, B:315:0x04fa, B:317:0x04ff, B:319:0x0508, B:322:0x0511, B:323:0x0514, B:325:0x051a, B:327:0x0521, B:334:0x052f, B:335:0x0547, B:320:0x050c, B:295:0x04b7, B:291:0x0495, B:338:0x054e, B:340:0x055a, B:343:0x056d, B:345:0x0579, B:393:0x0652, B:395:0x0663, B:396:0x0667, B:398:0x0670, B:405:0x0686, B:406:0x069e, B:99:0x01cd, B:118:0x01fb, B:41:0x00c1, B:45:0x00d2, B:44:0x00cc, B:51:0x00e5, B:53:0x00ef, B:54:0x00f2, B:57:0x00f7, B:58:0x010d, B:68:0x0120, B:69:0x0126, B:71:0x012b, B:74:0x0138, B:75:0x013c, B:78:0x0142, B:79:0x015c, B:72:0x0130, B:80:0x015d, B:81:0x0177, B:87:0x0181, B:90:0x0190, B:91:0x0196, B:92:0x01b4, B:93:0x01b5, B:407:0x069f, B:408:0x06b7, B:409:0x06b8, B:410:0x06d0), top: B:415:0x0063, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:239:0x03d1 A[Catch: all -> 0x06d1, TryCatch #0 {all -> 0x06d1, blocks: (B:21:0x0063, B:24:0x006d, B:27:0x0076, B:31:0x0089, B:33:0x0093, B:36:0x009b, B:37:0x00b9, B:96:0x01c1, B:100:0x01d3, B:116:0x01f2, B:119:0x01ff, B:122:0x0206, B:124:0x020e, B:125:0x0217, B:127:0x021d, B:132:0x022a, B:136:0x0232, B:139:0x0240, B:140:0x024b, B:142:0x0254, B:143:0x0257, B:145:0x0261, B:146:0x026f, B:148:0x0275, B:150:0x0283, B:152:0x028b, B:157:0x029a, B:158:0x02a0, B:160:0x02a8, B:161:0x02ad, B:166:0x02b7, B:167:0x02be, B:168:0x02bf, B:170:0x02c6, B:172:0x02ca, B:173:0x02cd, B:175:0x02d3, B:179:0x02e1, B:181:0x02ef, B:189:0x02ff, B:191:0x0307, B:193:0x030e, B:195:0x031d, B:197:0x0325, B:200:0x032a, B:202:0x032e, B:221:0x0378, B:223:0x037c, B:227:0x0386, B:228:0x039e, B:203:0x0331, B:205:0x0339, B:208:0x033f, B:209:0x034b, B:212:0x0354, B:215:0x035a, B:218:0x0360, B:219:0x036c, B:229:0x039f, B:230:0x03bb, B:233:0x03c0, B:239:0x03d1, B:241:0x03d7, B:243:0x03e3, B:244:0x03e9, B:246:0x03ee, B:346:0x0586, B:350:0x0590, B:353:0x0599, B:357:0x05ac, B:356:0x05a6, B:360:0x05b8, B:364:0x05cb, B:366:0x05d4, B:370:0x05e7, B:387:0x062f, B:369:0x05e1, B:373:0x05f2, B:377:0x0605, B:376:0x05ff, B:380:0x0610, B:384:0x0623, B:383:0x061d, B:385:0x062a, B:363:0x05c5, B:391:0x0639, B:392:0x0651, B:247:0x03f2, B:254:0x0402, B:258:0x0411, B:262:0x0428, B:264:0x0431, B:269:0x043e, B:270:0x0441, B:272:0x044b, B:274:0x0452, B:276:0x0456, B:284:0x046b, B:285:0x0483, B:273:0x044f, B:261:0x0422, B:288:0x0488, B:292:0x049b, B:294:0x04ac, B:298:0x04c0, B:300:0x04c6, B:303:0x04cc, B:305:0x04d6, B:307:0x04de, B:311:0x04f0, B:314:0x04f8, B:315:0x04fa, B:317:0x04ff, B:319:0x0508, B:322:0x0511, B:323:0x0514, B:325:0x051a, B:327:0x0521, B:334:0x052f, B:335:0x0547, B:320:0x050c, B:295:0x04b7, B:291:0x0495, B:338:0x054e, B:340:0x055a, B:343:0x056d, B:345:0x0579, B:393:0x0652, B:395:0x0663, B:396:0x0667, B:398:0x0670, B:405:0x0686, B:406:0x069e, B:99:0x01cd, B:118:0x01fb, B:41:0x00c1, B:45:0x00d2, B:44:0x00cc, B:51:0x00e5, B:53:0x00ef, B:54:0x00f2, B:57:0x00f7, B:58:0x010d, B:68:0x0120, B:69:0x0126, B:71:0x012b, B:74:0x0138, B:75:0x013c, B:78:0x0142, B:79:0x015c, B:72:0x0130, B:80:0x015d, B:81:0x0177, B:87:0x0181, B:90:0x0190, B:91:0x0196, B:92:0x01b4, B:93:0x01b5, B:407:0x069f, B:408:0x06b7, B:409:0x06b8, B:410:0x06d0), top: B:415:0x0063, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x04f0 A[Catch: all -> 0x06d1, TryCatch #0 {all -> 0x06d1, blocks: (B:21:0x0063, B:24:0x006d, B:27:0x0076, B:31:0x0089, B:33:0x0093, B:36:0x009b, B:37:0x00b9, B:96:0x01c1, B:100:0x01d3, B:116:0x01f2, B:119:0x01ff, B:122:0x0206, B:124:0x020e, B:125:0x0217, B:127:0x021d, B:132:0x022a, B:136:0x0232, B:139:0x0240, B:140:0x024b, B:142:0x0254, B:143:0x0257, B:145:0x0261, B:146:0x026f, B:148:0x0275, B:150:0x0283, B:152:0x028b, B:157:0x029a, B:158:0x02a0, B:160:0x02a8, B:161:0x02ad, B:166:0x02b7, B:167:0x02be, B:168:0x02bf, B:170:0x02c6, B:172:0x02ca, B:173:0x02cd, B:175:0x02d3, B:179:0x02e1, B:181:0x02ef, B:189:0x02ff, B:191:0x0307, B:193:0x030e, B:195:0x031d, B:197:0x0325, B:200:0x032a, B:202:0x032e, B:221:0x0378, B:223:0x037c, B:227:0x0386, B:228:0x039e, B:203:0x0331, B:205:0x0339, B:208:0x033f, B:209:0x034b, B:212:0x0354, B:215:0x035a, B:218:0x0360, B:219:0x036c, B:229:0x039f, B:230:0x03bb, B:233:0x03c0, B:239:0x03d1, B:241:0x03d7, B:243:0x03e3, B:244:0x03e9, B:246:0x03ee, B:346:0x0586, B:350:0x0590, B:353:0x0599, B:357:0x05ac, B:356:0x05a6, B:360:0x05b8, B:364:0x05cb, B:366:0x05d4, B:370:0x05e7, B:387:0x062f, B:369:0x05e1, B:373:0x05f2, B:377:0x0605, B:376:0x05ff, B:380:0x0610, B:384:0x0623, B:383:0x061d, B:385:0x062a, B:363:0x05c5, B:391:0x0639, B:392:0x0651, B:247:0x03f2, B:254:0x0402, B:258:0x0411, B:262:0x0428, B:264:0x0431, B:269:0x043e, B:270:0x0441, B:272:0x044b, B:274:0x0452, B:276:0x0456, B:284:0x046b, B:285:0x0483, B:273:0x044f, B:261:0x0422, B:288:0x0488, B:292:0x049b, B:294:0x04ac, B:298:0x04c0, B:300:0x04c6, B:303:0x04cc, B:305:0x04d6, B:307:0x04de, B:311:0x04f0, B:314:0x04f8, B:315:0x04fa, B:317:0x04ff, B:319:0x0508, B:322:0x0511, B:323:0x0514, B:325:0x051a, B:327:0x0521, B:334:0x052f, B:335:0x0547, B:320:0x050c, B:295:0x04b7, B:291:0x0495, B:338:0x054e, B:340:0x055a, B:343:0x056d, B:345:0x0579, B:393:0x0652, B:395:0x0663, B:396:0x0667, B:398:0x0670, B:405:0x0686, B:406:0x069e, B:99:0x01cd, B:118:0x01fb, B:41:0x00c1, B:45:0x00d2, B:44:0x00cc, B:51:0x00e5, B:53:0x00ef, B:54:0x00f2, B:57:0x00f7, B:58:0x010d, B:68:0x0120, B:69:0x0126, B:71:0x012b, B:74:0x0138, B:75:0x013c, B:78:0x0142, B:79:0x015c, B:72:0x0130, B:80:0x015d, B:81:0x0177, B:87:0x0181, B:90:0x0190, B:91:0x0196, B:92:0x01b4, B:93:0x01b5, B:407:0x069f, B:408:0x06b7, B:409:0x06b8, B:410:0x06d0), top: B:415:0x0063, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:317:0x04ff A[Catch: all -> 0x06d1, TryCatch #0 {all -> 0x06d1, blocks: (B:21:0x0063, B:24:0x006d, B:27:0x0076, B:31:0x0089, B:33:0x0093, B:36:0x009b, B:37:0x00b9, B:96:0x01c1, B:100:0x01d3, B:116:0x01f2, B:119:0x01ff, B:122:0x0206, B:124:0x020e, B:125:0x0217, B:127:0x021d, B:132:0x022a, B:136:0x0232, B:139:0x0240, B:140:0x024b, B:142:0x0254, B:143:0x0257, B:145:0x0261, B:146:0x026f, B:148:0x0275, B:150:0x0283, B:152:0x028b, B:157:0x029a, B:158:0x02a0, B:160:0x02a8, B:161:0x02ad, B:166:0x02b7, B:167:0x02be, B:168:0x02bf, B:170:0x02c6, B:172:0x02ca, B:173:0x02cd, B:175:0x02d3, B:179:0x02e1, B:181:0x02ef, B:189:0x02ff, B:191:0x0307, B:193:0x030e, B:195:0x031d, B:197:0x0325, B:200:0x032a, B:202:0x032e, B:221:0x0378, B:223:0x037c, B:227:0x0386, B:228:0x039e, B:203:0x0331, B:205:0x0339, B:208:0x033f, B:209:0x034b, B:212:0x0354, B:215:0x035a, B:218:0x0360, B:219:0x036c, B:229:0x039f, B:230:0x03bb, B:233:0x03c0, B:239:0x03d1, B:241:0x03d7, B:243:0x03e3, B:244:0x03e9, B:246:0x03ee, B:346:0x0586, B:350:0x0590, B:353:0x0599, B:357:0x05ac, B:356:0x05a6, B:360:0x05b8, B:364:0x05cb, B:366:0x05d4, B:370:0x05e7, B:387:0x062f, B:369:0x05e1, B:373:0x05f2, B:377:0x0605, B:376:0x05ff, B:380:0x0610, B:384:0x0623, B:383:0x061d, B:385:0x062a, B:363:0x05c5, B:391:0x0639, B:392:0x0651, B:247:0x03f2, B:254:0x0402, B:258:0x0411, B:262:0x0428, B:264:0x0431, B:269:0x043e, B:270:0x0441, B:272:0x044b, B:274:0x0452, B:276:0x0456, B:284:0x046b, B:285:0x0483, B:273:0x044f, B:261:0x0422, B:288:0x0488, B:292:0x049b, B:294:0x04ac, B:298:0x04c0, B:300:0x04c6, B:303:0x04cc, B:305:0x04d6, B:307:0x04de, B:311:0x04f0, B:314:0x04f8, B:315:0x04fa, B:317:0x04ff, B:319:0x0508, B:322:0x0511, B:323:0x0514, B:325:0x051a, B:327:0x0521, B:334:0x052f, B:335:0x0547, B:320:0x050c, B:295:0x04b7, B:291:0x0495, B:338:0x054e, B:340:0x055a, B:343:0x056d, B:345:0x0579, B:393:0x0652, B:395:0x0663, B:396:0x0667, B:398:0x0670, B:405:0x0686, B:406:0x069e, B:99:0x01cd, B:118:0x01fb, B:41:0x00c1, B:45:0x00d2, B:44:0x00cc, B:51:0x00e5, B:53:0x00ef, B:54:0x00f2, B:57:0x00f7, B:58:0x010d, B:68:0x0120, B:69:0x0126, B:71:0x012b, B:74:0x0138, B:75:0x013c, B:78:0x0142, B:79:0x015c, B:72:0x0130, B:80:0x015d, B:81:0x0177, B:87:0x0181, B:90:0x0190, B:91:0x0196, B:92:0x01b4, B:93:0x01b5, B:407:0x069f, B:408:0x06b7, B:409:0x06b8, B:410:0x06d0), top: B:415:0x0063, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0508 A[Catch: all -> 0x06d1, TryCatch #0 {all -> 0x06d1, blocks: (B:21:0x0063, B:24:0x006d, B:27:0x0076, B:31:0x0089, B:33:0x0093, B:36:0x009b, B:37:0x00b9, B:96:0x01c1, B:100:0x01d3, B:116:0x01f2, B:119:0x01ff, B:122:0x0206, B:124:0x020e, B:125:0x0217, B:127:0x021d, B:132:0x022a, B:136:0x0232, B:139:0x0240, B:140:0x024b, B:142:0x0254, B:143:0x0257, B:145:0x0261, B:146:0x026f, B:148:0x0275, B:150:0x0283, B:152:0x028b, B:157:0x029a, B:158:0x02a0, B:160:0x02a8, B:161:0x02ad, B:166:0x02b7, B:167:0x02be, B:168:0x02bf, B:170:0x02c6, B:172:0x02ca, B:173:0x02cd, B:175:0x02d3, B:179:0x02e1, B:181:0x02ef, B:189:0x02ff, B:191:0x0307, B:193:0x030e, B:195:0x031d, B:197:0x0325, B:200:0x032a, B:202:0x032e, B:221:0x0378, B:223:0x037c, B:227:0x0386, B:228:0x039e, B:203:0x0331, B:205:0x0339, B:208:0x033f, B:209:0x034b, B:212:0x0354, B:215:0x035a, B:218:0x0360, B:219:0x036c, B:229:0x039f, B:230:0x03bb, B:233:0x03c0, B:239:0x03d1, B:241:0x03d7, B:243:0x03e3, B:244:0x03e9, B:246:0x03ee, B:346:0x0586, B:350:0x0590, B:353:0x0599, B:357:0x05ac, B:356:0x05a6, B:360:0x05b8, B:364:0x05cb, B:366:0x05d4, B:370:0x05e7, B:387:0x062f, B:369:0x05e1, B:373:0x05f2, B:377:0x0605, B:376:0x05ff, B:380:0x0610, B:384:0x0623, B:383:0x061d, B:385:0x062a, B:363:0x05c5, B:391:0x0639, B:392:0x0651, B:247:0x03f2, B:254:0x0402, B:258:0x0411, B:262:0x0428, B:264:0x0431, B:269:0x043e, B:270:0x0441, B:272:0x044b, B:274:0x0452, B:276:0x0456, B:284:0x046b, B:285:0x0483, B:273:0x044f, B:261:0x0422, B:288:0x0488, B:292:0x049b, B:294:0x04ac, B:298:0x04c0, B:300:0x04c6, B:303:0x04cc, B:305:0x04d6, B:307:0x04de, B:311:0x04f0, B:314:0x04f8, B:315:0x04fa, B:317:0x04ff, B:319:0x0508, B:322:0x0511, B:323:0x0514, B:325:0x051a, B:327:0x0521, B:334:0x052f, B:335:0x0547, B:320:0x050c, B:295:0x04b7, B:291:0x0495, B:338:0x054e, B:340:0x055a, B:343:0x056d, B:345:0x0579, B:393:0x0652, B:395:0x0663, B:396:0x0667, B:398:0x0670, B:405:0x0686, B:406:0x069e, B:99:0x01cd, B:118:0x01fb, B:41:0x00c1, B:45:0x00d2, B:44:0x00cc, B:51:0x00e5, B:53:0x00ef, B:54:0x00f2, B:57:0x00f7, B:58:0x010d, B:68:0x0120, B:69:0x0126, B:71:0x012b, B:74:0x0138, B:75:0x013c, B:78:0x0142, B:79:0x015c, B:72:0x0130, B:80:0x015d, B:81:0x0177, B:87:0x0181, B:90:0x0190, B:91:0x0196, B:92:0x01b4, B:93:0x01b5, B:407:0x069f, B:408:0x06b7, B:409:0x06b8, B:410:0x06d0), top: B:415:0x0063, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:320:0x050c A[Catch: all -> 0x06d1, TryCatch #0 {all -> 0x06d1, blocks: (B:21:0x0063, B:24:0x006d, B:27:0x0076, B:31:0x0089, B:33:0x0093, B:36:0x009b, B:37:0x00b9, B:96:0x01c1, B:100:0x01d3, B:116:0x01f2, B:119:0x01ff, B:122:0x0206, B:124:0x020e, B:125:0x0217, B:127:0x021d, B:132:0x022a, B:136:0x0232, B:139:0x0240, B:140:0x024b, B:142:0x0254, B:143:0x0257, B:145:0x0261, B:146:0x026f, B:148:0x0275, B:150:0x0283, B:152:0x028b, B:157:0x029a, B:158:0x02a0, B:160:0x02a8, B:161:0x02ad, B:166:0x02b7, B:167:0x02be, B:168:0x02bf, B:170:0x02c6, B:172:0x02ca, B:173:0x02cd, B:175:0x02d3, B:179:0x02e1, B:181:0x02ef, B:189:0x02ff, B:191:0x0307, B:193:0x030e, B:195:0x031d, B:197:0x0325, B:200:0x032a, B:202:0x032e, B:221:0x0378, B:223:0x037c, B:227:0x0386, B:228:0x039e, B:203:0x0331, B:205:0x0339, B:208:0x033f, B:209:0x034b, B:212:0x0354, B:215:0x035a, B:218:0x0360, B:219:0x036c, B:229:0x039f, B:230:0x03bb, B:233:0x03c0, B:239:0x03d1, B:241:0x03d7, B:243:0x03e3, B:244:0x03e9, B:246:0x03ee, B:346:0x0586, B:350:0x0590, B:353:0x0599, B:357:0x05ac, B:356:0x05a6, B:360:0x05b8, B:364:0x05cb, B:366:0x05d4, B:370:0x05e7, B:387:0x062f, B:369:0x05e1, B:373:0x05f2, B:377:0x0605, B:376:0x05ff, B:380:0x0610, B:384:0x0623, B:383:0x061d, B:385:0x062a, B:363:0x05c5, B:391:0x0639, B:392:0x0651, B:247:0x03f2, B:254:0x0402, B:258:0x0411, B:262:0x0428, B:264:0x0431, B:269:0x043e, B:270:0x0441, B:272:0x044b, B:274:0x0452, B:276:0x0456, B:284:0x046b, B:285:0x0483, B:273:0x044f, B:261:0x0422, B:288:0x0488, B:292:0x049b, B:294:0x04ac, B:298:0x04c0, B:300:0x04c6, B:303:0x04cc, B:305:0x04d6, B:307:0x04de, B:311:0x04f0, B:314:0x04f8, B:315:0x04fa, B:317:0x04ff, B:319:0x0508, B:322:0x0511, B:323:0x0514, B:325:0x051a, B:327:0x0521, B:334:0x052f, B:335:0x0547, B:320:0x050c, B:295:0x04b7, B:291:0x0495, B:338:0x054e, B:340:0x055a, B:343:0x056d, B:345:0x0579, B:393:0x0652, B:395:0x0663, B:396:0x0667, B:398:0x0670, B:405:0x0686, B:406:0x069e, B:99:0x01cd, B:118:0x01fb, B:41:0x00c1, B:45:0x00d2, B:44:0x00cc, B:51:0x00e5, B:53:0x00ef, B:54:0x00f2, B:57:0x00f7, B:58:0x010d, B:68:0x0120, B:69:0x0126, B:71:0x012b, B:74:0x0138, B:75:0x013c, B:78:0x0142, B:79:0x015c, B:72:0x0130, B:80:0x015d, B:81:0x0177, B:87:0x0181, B:90:0x0190, B:91:0x0196, B:92:0x01b4, B:93:0x01b5, B:407:0x069f, B:408:0x06b7, B:409:0x06b8, B:410:0x06d0), top: B:415:0x0063, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0511 A[Catch: all -> 0x06d1, TryCatch #0 {all -> 0x06d1, blocks: (B:21:0x0063, B:24:0x006d, B:27:0x0076, B:31:0x0089, B:33:0x0093, B:36:0x009b, B:37:0x00b9, B:96:0x01c1, B:100:0x01d3, B:116:0x01f2, B:119:0x01ff, B:122:0x0206, B:124:0x020e, B:125:0x0217, B:127:0x021d, B:132:0x022a, B:136:0x0232, B:139:0x0240, B:140:0x024b, B:142:0x0254, B:143:0x0257, B:145:0x0261, B:146:0x026f, B:148:0x0275, B:150:0x0283, B:152:0x028b, B:157:0x029a, B:158:0x02a0, B:160:0x02a8, B:161:0x02ad, B:166:0x02b7, B:167:0x02be, B:168:0x02bf, B:170:0x02c6, B:172:0x02ca, B:173:0x02cd, B:175:0x02d3, B:179:0x02e1, B:181:0x02ef, B:189:0x02ff, B:191:0x0307, B:193:0x030e, B:195:0x031d, B:197:0x0325, B:200:0x032a, B:202:0x032e, B:221:0x0378, B:223:0x037c, B:227:0x0386, B:228:0x039e, B:203:0x0331, B:205:0x0339, B:208:0x033f, B:209:0x034b, B:212:0x0354, B:215:0x035a, B:218:0x0360, B:219:0x036c, B:229:0x039f, B:230:0x03bb, B:233:0x03c0, B:239:0x03d1, B:241:0x03d7, B:243:0x03e3, B:244:0x03e9, B:246:0x03ee, B:346:0x0586, B:350:0x0590, B:353:0x0599, B:357:0x05ac, B:356:0x05a6, B:360:0x05b8, B:364:0x05cb, B:366:0x05d4, B:370:0x05e7, B:387:0x062f, B:369:0x05e1, B:373:0x05f2, B:377:0x0605, B:376:0x05ff, B:380:0x0610, B:384:0x0623, B:383:0x061d, B:385:0x062a, B:363:0x05c5, B:391:0x0639, B:392:0x0651, B:247:0x03f2, B:254:0x0402, B:258:0x0411, B:262:0x0428, B:264:0x0431, B:269:0x043e, B:270:0x0441, B:272:0x044b, B:274:0x0452, B:276:0x0456, B:284:0x046b, B:285:0x0483, B:273:0x044f, B:261:0x0422, B:288:0x0488, B:292:0x049b, B:294:0x04ac, B:298:0x04c0, B:300:0x04c6, B:303:0x04cc, B:305:0x04d6, B:307:0x04de, B:311:0x04f0, B:314:0x04f8, B:315:0x04fa, B:317:0x04ff, B:319:0x0508, B:322:0x0511, B:323:0x0514, B:325:0x051a, B:327:0x0521, B:334:0x052f, B:335:0x0547, B:320:0x050c, B:295:0x04b7, B:291:0x0495, B:338:0x054e, B:340:0x055a, B:343:0x056d, B:345:0x0579, B:393:0x0652, B:395:0x0663, B:396:0x0667, B:398:0x0670, B:405:0x0686, B:406:0x069e, B:99:0x01cd, B:118:0x01fb, B:41:0x00c1, B:45:0x00d2, B:44:0x00cc, B:51:0x00e5, B:53:0x00ef, B:54:0x00f2, B:57:0x00f7, B:58:0x010d, B:68:0x0120, B:69:0x0126, B:71:0x012b, B:74:0x0138, B:75:0x013c, B:78:0x0142, B:79:0x015c, B:72:0x0130, B:80:0x015d, B:81:0x0177, B:87:0x0181, B:90:0x0190, B:91:0x0196, B:92:0x01b4, B:93:0x01b5, B:407:0x069f, B:408:0x06b7, B:409:0x06b8, B:410:0x06d0), top: B:415:0x0063, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x058c  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x0599 A[Catch: all -> 0x06d1, TryCatch #0 {all -> 0x06d1, blocks: (B:21:0x0063, B:24:0x006d, B:27:0x0076, B:31:0x0089, B:33:0x0093, B:36:0x009b, B:37:0x00b9, B:96:0x01c1, B:100:0x01d3, B:116:0x01f2, B:119:0x01ff, B:122:0x0206, B:124:0x020e, B:125:0x0217, B:127:0x021d, B:132:0x022a, B:136:0x0232, B:139:0x0240, B:140:0x024b, B:142:0x0254, B:143:0x0257, B:145:0x0261, B:146:0x026f, B:148:0x0275, B:150:0x0283, B:152:0x028b, B:157:0x029a, B:158:0x02a0, B:160:0x02a8, B:161:0x02ad, B:166:0x02b7, B:167:0x02be, B:168:0x02bf, B:170:0x02c6, B:172:0x02ca, B:173:0x02cd, B:175:0x02d3, B:179:0x02e1, B:181:0x02ef, B:189:0x02ff, B:191:0x0307, B:193:0x030e, B:195:0x031d, B:197:0x0325, B:200:0x032a, B:202:0x032e, B:221:0x0378, B:223:0x037c, B:227:0x0386, B:228:0x039e, B:203:0x0331, B:205:0x0339, B:208:0x033f, B:209:0x034b, B:212:0x0354, B:215:0x035a, B:218:0x0360, B:219:0x036c, B:229:0x039f, B:230:0x03bb, B:233:0x03c0, B:239:0x03d1, B:241:0x03d7, B:243:0x03e3, B:244:0x03e9, B:246:0x03ee, B:346:0x0586, B:350:0x0590, B:353:0x0599, B:357:0x05ac, B:356:0x05a6, B:360:0x05b8, B:364:0x05cb, B:366:0x05d4, B:370:0x05e7, B:387:0x062f, B:369:0x05e1, B:373:0x05f2, B:377:0x0605, B:376:0x05ff, B:380:0x0610, B:384:0x0623, B:383:0x061d, B:385:0x062a, B:363:0x05c5, B:391:0x0639, B:392:0x0651, B:247:0x03f2, B:254:0x0402, B:258:0x0411, B:262:0x0428, B:264:0x0431, B:269:0x043e, B:270:0x0441, B:272:0x044b, B:274:0x0452, B:276:0x0456, B:284:0x046b, B:285:0x0483, B:273:0x044f, B:261:0x0422, B:288:0x0488, B:292:0x049b, B:294:0x04ac, B:298:0x04c0, B:300:0x04c6, B:303:0x04cc, B:305:0x04d6, B:307:0x04de, B:311:0x04f0, B:314:0x04f8, B:315:0x04fa, B:317:0x04ff, B:319:0x0508, B:322:0x0511, B:323:0x0514, B:325:0x051a, B:327:0x0521, B:334:0x052f, B:335:0x0547, B:320:0x050c, B:295:0x04b7, B:291:0x0495, B:338:0x054e, B:340:0x055a, B:343:0x056d, B:345:0x0579, B:393:0x0652, B:395:0x0663, B:396:0x0667, B:398:0x0670, B:405:0x0686, B:406:0x069e, B:99:0x01cd, B:118:0x01fb, B:41:0x00c1, B:45:0x00d2, B:44:0x00cc, B:51:0x00e5, B:53:0x00ef, B:54:0x00f2, B:57:0x00f7, B:58:0x010d, B:68:0x0120, B:69:0x0126, B:71:0x012b, B:74:0x0138, B:75:0x013c, B:78:0x0142, B:79:0x015c, B:72:0x0130, B:80:0x015d, B:81:0x0177, B:87:0x0181, B:90:0x0190, B:91:0x0196, B:92:0x01b4, B:93:0x01b5, B:407:0x069f, B:408:0x06b7, B:409:0x06b8, B:410:0x06d0), top: B:415:0x0063, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:435:0x051a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:437:0x05b4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01c1 A[Catch: all -> 0x06d1, TryCatch #0 {all -> 0x06d1, blocks: (B:21:0x0063, B:24:0x006d, B:27:0x0076, B:31:0x0089, B:33:0x0093, B:36:0x009b, B:37:0x00b9, B:96:0x01c1, B:100:0x01d3, B:116:0x01f2, B:119:0x01ff, B:122:0x0206, B:124:0x020e, B:125:0x0217, B:127:0x021d, B:132:0x022a, B:136:0x0232, B:139:0x0240, B:140:0x024b, B:142:0x0254, B:143:0x0257, B:145:0x0261, B:146:0x026f, B:148:0x0275, B:150:0x0283, B:152:0x028b, B:157:0x029a, B:158:0x02a0, B:160:0x02a8, B:161:0x02ad, B:166:0x02b7, B:167:0x02be, B:168:0x02bf, B:170:0x02c6, B:172:0x02ca, B:173:0x02cd, B:175:0x02d3, B:179:0x02e1, B:181:0x02ef, B:189:0x02ff, B:191:0x0307, B:193:0x030e, B:195:0x031d, B:197:0x0325, B:200:0x032a, B:202:0x032e, B:221:0x0378, B:223:0x037c, B:227:0x0386, B:228:0x039e, B:203:0x0331, B:205:0x0339, B:208:0x033f, B:209:0x034b, B:212:0x0354, B:215:0x035a, B:218:0x0360, B:219:0x036c, B:229:0x039f, B:230:0x03bb, B:233:0x03c0, B:239:0x03d1, B:241:0x03d7, B:243:0x03e3, B:244:0x03e9, B:246:0x03ee, B:346:0x0586, B:350:0x0590, B:353:0x0599, B:357:0x05ac, B:356:0x05a6, B:360:0x05b8, B:364:0x05cb, B:366:0x05d4, B:370:0x05e7, B:387:0x062f, B:369:0x05e1, B:373:0x05f2, B:377:0x0605, B:376:0x05ff, B:380:0x0610, B:384:0x0623, B:383:0x061d, B:385:0x062a, B:363:0x05c5, B:391:0x0639, B:392:0x0651, B:247:0x03f2, B:254:0x0402, B:258:0x0411, B:262:0x0428, B:264:0x0431, B:269:0x043e, B:270:0x0441, B:272:0x044b, B:274:0x0452, B:276:0x0456, B:284:0x046b, B:285:0x0483, B:273:0x044f, B:261:0x0422, B:288:0x0488, B:292:0x049b, B:294:0x04ac, B:298:0x04c0, B:300:0x04c6, B:303:0x04cc, B:305:0x04d6, B:307:0x04de, B:311:0x04f0, B:314:0x04f8, B:315:0x04fa, B:317:0x04ff, B:319:0x0508, B:322:0x0511, B:323:0x0514, B:325:0x051a, B:327:0x0521, B:334:0x052f, B:335:0x0547, B:320:0x050c, B:295:0x04b7, B:291:0x0495, B:338:0x054e, B:340:0x055a, B:343:0x056d, B:345:0x0579, B:393:0x0652, B:395:0x0663, B:396:0x0667, B:398:0x0670, B:405:0x0686, B:406:0x069e, B:99:0x01cd, B:118:0x01fb, B:41:0x00c1, B:45:0x00d2, B:44:0x00cc, B:51:0x00e5, B:53:0x00ef, B:54:0x00f2, B:57:0x00f7, B:58:0x010d, B:68:0x0120, B:69:0x0126, B:71:0x012b, B:74:0x0138, B:75:0x013c, B:78:0x0142, B:79:0x015c, B:72:0x0130, B:80:0x015d, B:81:0x0177, B:87:0x0181, B:90:0x0190, B:91:0x0196, B:92:0x01b4, B:93:0x01b5, B:407:0x069f, B:408:0x06b7, B:409:0x06b8, B:410:0x06d0), top: B:415:0x0063, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object parseObject(java.util.Map r19, java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 1751
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public String parseString() {
        JSONLexer jSONLexer = this.lexer;
        int i = jSONLexer.token;
        if (i != 4) {
            if (i == 2) {
                String numberString = jSONLexer.numberString();
                this.lexer.nextToken(16);
                return numberString;
            }
            Object parse = parse();
            if (parse == null) {
                return null;
            }
            return parse.toString();
        }
        String stringVal = jSONLexer.stringVal();
        JSONLexer jSONLexer2 = this.lexer;
        char c = jSONLexer2.ch;
        char c2 = JSONLexer.EOI;
        if (c == ',') {
            int i2 = jSONLexer2.bp + 1;
            jSONLexer2.bp = i2;
            if (i2 < jSONLexer2.len) {
                c2 = jSONLexer2.text.charAt(i2);
            }
            jSONLexer2.ch = c2;
            this.lexer.token = 16;
        } else if (c == ']') {
            int i3 = jSONLexer2.bp + 1;
            jSONLexer2.bp = i3;
            if (i3 < jSONLexer2.len) {
                c2 = jSONLexer2.text.charAt(i3);
            }
            jSONLexer2.ch = c2;
            this.lexer.token = 15;
        } else if (c == '}') {
            int i4 = jSONLexer2.bp + 1;
            jSONLexer2.bp = i4;
            if (i4 < jSONLexer2.len) {
                c2 = jSONLexer2.text.charAt(i4);
            }
            jSONLexer2.ch = c2;
            this.lexer.token = 13;
        } else {
            jSONLexer2.nextToken();
        }
        return stringVal;
    }

    public void popContext() {
        this.contex = this.contex.parent;
        ParseContext[] parseContextArr = this.j;
        int i = this.k;
        parseContextArr[i - 1] = null;
        this.k = i - 1;
    }

    public void setContext(ParseContext parseContext) {
        if (this.lexer.disableCircularReferenceDetect) {
            return;
        }
        this.contex = parseContext;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.i = dateFormat;
    }

    public void setDateFormat(String str) {
        this.h = str;
        this.i = null;
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(new JSONLexer(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Object parse(Object obj) {
        JSONLexer jSONLexer = this.lexer;
        int i = jSONLexer.token;
        if (i == 2) {
            Number integerValue = jSONLexer.integerValue();
            this.lexer.nextToken();
            return integerValue;
        }
        if (i == 3) {
            Number decimalValue = jSONLexer.decimalValue((jSONLexer.features & Feature.UseBigDecimal.mask) != 0);
            this.lexer.nextToken();
            return decimalValue;
        } else if (i == 4) {
            String stringVal = jSONLexer.stringVal();
            this.lexer.nextToken(16);
            if ((this.lexer.features & Feature.AllowISO8601DateFormat.mask) != 0) {
                JSONLexer jSONLexer2 = new JSONLexer(stringVal);
                try {
                    if (jSONLexer2.scanISO8601DateIfMatch(true)) {
                        return jSONLexer2.calendar.getTime();
                    }
                } finally {
                    jSONLexer2.close();
                }
            }
            return stringVal;
        } else if (i == 12) {
            return parseObject((jSONLexer.features & Feature.OrderedField.mask) != 0 ? new JSONObject(new LinkedHashMap()) : new JSONObject(), obj);
        } else if (i != 14) {
            switch (i) {
                case 6:
                    jSONLexer.nextToken(16);
                    return Boolean.TRUE;
                case 7:
                    jSONLexer.nextToken(16);
                    return Boolean.FALSE;
                case 8:
                    break;
                case 9:
                    jSONLexer.nextToken(18);
                    JSONLexer jSONLexer3 = this.lexer;
                    if (jSONLexer3.token == 18) {
                        jSONLexer3.nextToken(10);
                        accept(10);
                        long longValue = this.lexer.integerValue().longValue();
                        accept(2);
                        accept(11);
                        return new Date(longValue);
                    }
                    throw new JSONException("syntax error, " + this.lexer.info());
                default:
                    switch (i) {
                        case 20:
                            if (jSONLexer.isBlankInput()) {
                                return null;
                            }
                            throw new JSONException("syntax error, " + this.lexer.info());
                        case 21:
                            jSONLexer.nextToken();
                            HashSet hashSet = new HashSet();
                            parseArray(hashSet, obj);
                            return hashSet;
                        case 22:
                            jSONLexer.nextToken();
                            TreeSet treeSet = new TreeSet();
                            parseArray(treeSet, obj);
                            return treeSet;
                        case 23:
                            break;
                        default:
                            throw new JSONException("syntax error, " + this.lexer.info());
                    }
            }
            jSONLexer.nextToken();
            return null;
        } else {
            JSONArray jSONArray = new JSONArray();
            parseArray(jSONArray, obj);
            return jSONArray;
        }
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this(new JSONLexer(str, i), parserConfig);
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.disableCircularReferenceDetect) {
            return null;
        }
        this.contex = new ParseContext(parseContext, obj, obj2);
        int i = this.k;
        this.k = i + 1;
        ParseContext[] parseContextArr = this.j;
        if (parseContextArr == null) {
            this.j = new ParseContext[8];
        } else if (i >= parseContextArr.length) {
            ParseContext[] parseContextArr2 = new ParseContext[(parseContextArr.length * 3) / 2];
            System.arraycopy(parseContextArr, 0, parseContextArr2, 0, parseContextArr.length);
            this.j = parseContextArr2;
        }
        ParseContext[] parseContextArr3 = this.j;
        ParseContext parseContext2 = this.contex;
        parseContextArr3[i] = parseContext2;
        return parseContext2;
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this(new JSONLexer(cArr, i, i2), parserConfig);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.global);
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer deserializer;
        String str;
        JSONLexer jSONLexer = this.lexer;
        int i = jSONLexer.token;
        if (i == 21 || i == 22) {
            jSONLexer.nextToken();
        }
        JSONLexer jSONLexer2 = this.lexer;
        if (jSONLexer2.token == 14) {
            if (Integer.TYPE != type) {
                if (String.class == type) {
                    deserializer = StringCodec.instance;
                    jSONLexer2.nextToken(4);
                } else {
                    deserializer = this.config.getDeserializer(type);
                    this.lexer.nextToken(12);
                }
            } else {
                deserializer = IntegerCodec.instance;
                jSONLexer2.nextToken(2);
            }
            ParseContext parseContext = this.contex;
            if (!this.lexer.disableCircularReferenceDetect) {
                setContext(parseContext, collection, obj);
            }
            int i2 = 0;
            while (true) {
                try {
                    JSONLexer jSONLexer3 = this.lexer;
                    int i3 = jSONLexer3.token;
                    if (i3 == 16) {
                        jSONLexer3.nextToken();
                    } else if (i3 == 15) {
                        this.contex = parseContext;
                        jSONLexer3.nextToken(16);
                        return;
                    } else {
                        Object obj2 = null;
                        String obj3 = null;
                        if (Integer.TYPE != type) {
                            if (String.class == type) {
                                if (i3 == 4) {
                                    str = jSONLexer3.stringVal();
                                    this.lexer.nextToken(16);
                                } else {
                                    Object parse = parse();
                                    if (parse != null) {
                                        obj3 = parse.toString();
                                    }
                                    str = obj3;
                                }
                                collection.add(str);
                            } else {
                                if (i3 == 8) {
                                    jSONLexer3.nextToken();
                                } else {
                                    obj2 = deserializer.deserialze(this, type, Integer.valueOf(i2));
                                }
                                collection.add(obj2);
                                if (this.resolveStatus == 1) {
                                    checkListResolve(collection);
                                }
                            }
                        } else {
                            collection.add(IntegerCodec.instance.deserialze(this, null, null));
                        }
                        JSONLexer jSONLexer4 = this.lexer;
                        if (jSONLexer4.token == 16) {
                            jSONLexer4.nextToken();
                        }
                        i2++;
                    }
                } catch (Throwable th) {
                    this.contex = parseContext;
                    throw th;
                }
            }
        } else {
            throw new JSONException("exepct '[', but " + JSONToken.name(this.lexer.token) + ", " + this.lexer.info());
        }
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.h = JSON.DEFFAULT_DATE_FORMAT;
        this.k = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.lexer = jSONLexer;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char c = jSONLexer.ch;
        char c2 = JSONLexer.EOI;
        if (c == '{') {
            int i = jSONLexer.bp + 1;
            jSONLexer.bp = i;
            jSONLexer.ch = i < jSONLexer.len ? jSONLexer.text.charAt(i) : c2;
            jSONLexer.token = 12;
        } else if (c == '[') {
            int i2 = jSONLexer.bp + 1;
            jSONLexer.bp = i2;
            jSONLexer.ch = i2 < jSONLexer.len ? jSONLexer.text.charAt(i2) : c2;
            jSONLexer.token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    public Object[] parseArray(Type[] typeArr) {
        Object cast;
        Class<?> cls;
        boolean z;
        int i;
        JSONLexer jSONLexer = this.lexer;
        int i2 = jSONLexer.token;
        int i3 = 8;
        if (i2 == 8) {
            jSONLexer.nextToken(16);
            return null;
        } else if (i2 == 14) {
            Object[] objArr = new Object[typeArr.length];
            if (typeArr.length == 0) {
                jSONLexer.nextToken(15);
                JSONLexer jSONLexer2 = this.lexer;
                if (jSONLexer2.token == 15) {
                    jSONLexer2.nextToken(16);
                    return new Object[0];
                }
                throw new JSONException("syntax error, " + this.lexer.info());
            }
            jSONLexer.nextToken(2);
            int i4 = 0;
            while (i4 < typeArr.length) {
                JSONLexer jSONLexer3 = this.lexer;
                int i5 = jSONLexer3.token;
                if (i5 == i3) {
                    jSONLexer3.nextToken(16);
                    cast = null;
                } else {
                    Type type = typeArr[i4];
                    if (type == Integer.TYPE || type == Integer.class) {
                        if (i5 == 2) {
                            cast = Integer.valueOf(jSONLexer3.intValue());
                            this.lexer.nextToken(16);
                        } else {
                            cast = TypeUtils.cast(parse(), type, this.config);
                        }
                    } else if (type != String.class) {
                        if (i4 == typeArr.length - 1 && (type instanceof Class)) {
                            Class cls2 = (Class) type;
                            z = cls2.isArray();
                            cls = cls2.getComponentType();
                        } else {
                            cls = null;
                            z = false;
                        }
                        if (z && this.lexer.token != 14) {
                            ArrayList arrayList = new ArrayList();
                            ObjectDeserializer deserializer = this.config.getDeserializer(cls);
                            if (this.lexer.token != 15) {
                                while (true) {
                                    arrayList.add(deserializer.deserialze(this, type, null));
                                    JSONLexer jSONLexer4 = this.lexer;
                                    i = jSONLexer4.token;
                                    if (i != 16) {
                                        break;
                                    }
                                    jSONLexer4.nextToken(12);
                                }
                                if (i != 15) {
                                    throw new JSONException("syntax error, " + this.lexer.info());
                                }
                            }
                            cast = TypeUtils.cast(arrayList, type, this.config);
                        } else {
                            cast = this.config.getDeserializer(type).deserialze(this, type, null);
                        }
                    } else if (i5 == 4) {
                        cast = jSONLexer3.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        cast = TypeUtils.cast(parse(), type, this.config);
                    }
                }
                objArr[i4] = cast;
                JSONLexer jSONLexer5 = this.lexer;
                int i6 = jSONLexer5.token;
                if (i6 == 15) {
                    break;
                } else if (i6 == 16) {
                    if (i4 == typeArr.length - 1) {
                        jSONLexer5.nextToken(15);
                    } else {
                        jSONLexer5.nextToken(2);
                    }
                    i4++;
                    i3 = 8;
                } else {
                    throw new JSONException("syntax error, " + this.lexer.info());
                }
            }
            JSONLexer jSONLexer6 = this.lexer;
            if (jSONLexer6.token == 15) {
                jSONLexer6.nextToken(16);
                return objArr;
            }
            throw new JSONException("syntax error, " + this.lexer.info());
        } else {
            throw new JSONException("syntax error, " + this.lexer.info());
        }
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x01d6 A[Catch: all -> 0x0236, TryCatch #0 {all -> 0x0236, blocks: (B:12:0x0028, B:15:0x003c, B:20:0x004b, B:24:0x005d, B:33:0x007b, B:35:0x0081, B:37:0x008d, B:41:0x009f, B:43:0x00a8, B:46:0x00b0, B:40:0x0099, B:48:0x00b9, B:52:0x00cb, B:54:0x00d4, B:55:0x00d7, B:51:0x00c5, B:59:0x00e1, B:60:0x00e4, B:62:0x00ea, B:83:0x0119, B:113:0x01e4, B:115:0x01eb, B:116:0x01ee, B:118:0x01f4, B:120:0x01f8, B:125:0x0208, B:128:0x0214, B:132:0x0228, B:131:0x0222, B:133:0x022b, B:85:0x0121, B:89:0x012b, B:90:0x0138, B:91:0x0140, B:92:0x0147, B:93:0x0148, B:95:0x0155, B:97:0x0165, B:96:0x0160, B:98:0x016e, B:99:0x0176, B:100:0x0180, B:101:0x018a, B:103:0x01a2, B:105:0x01ad, B:106:0x01b3, B:107:0x01b8, B:109:0x01c5, B:111:0x01d0, B:110:0x01cb, B:112:0x01d6, B:23:0x0057, B:25:0x0064, B:27:0x0069, B:30:0x0074), top: B:141:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01eb A[Catch: all -> 0x0236, TryCatch #0 {all -> 0x0236, blocks: (B:12:0x0028, B:15:0x003c, B:20:0x004b, B:24:0x005d, B:33:0x007b, B:35:0x0081, B:37:0x008d, B:41:0x009f, B:43:0x00a8, B:46:0x00b0, B:40:0x0099, B:48:0x00b9, B:52:0x00cb, B:54:0x00d4, B:55:0x00d7, B:51:0x00c5, B:59:0x00e1, B:60:0x00e4, B:62:0x00ea, B:83:0x0119, B:113:0x01e4, B:115:0x01eb, B:116:0x01ee, B:118:0x01f4, B:120:0x01f8, B:125:0x0208, B:128:0x0214, B:132:0x0228, B:131:0x0222, B:133:0x022b, B:85:0x0121, B:89:0x012b, B:90:0x0138, B:91:0x0140, B:92:0x0147, B:93:0x0148, B:95:0x0155, B:97:0x0165, B:96:0x0160, B:98:0x016e, B:99:0x0176, B:100:0x0180, B:101:0x018a, B:103:0x01a2, B:105:0x01ad, B:106:0x01b3, B:107:0x01b8, B:109:0x01c5, B:111:0x01d0, B:110:0x01cb, B:112:0x01d6, B:23:0x0057, B:25:0x0064, B:27:0x0069, B:30:0x0074), top: B:141:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01f4 A[Catch: all -> 0x0236, TryCatch #0 {all -> 0x0236, blocks: (B:12:0x0028, B:15:0x003c, B:20:0x004b, B:24:0x005d, B:33:0x007b, B:35:0x0081, B:37:0x008d, B:41:0x009f, B:43:0x00a8, B:46:0x00b0, B:40:0x0099, B:48:0x00b9, B:52:0x00cb, B:54:0x00d4, B:55:0x00d7, B:51:0x00c5, B:59:0x00e1, B:60:0x00e4, B:62:0x00ea, B:83:0x0119, B:113:0x01e4, B:115:0x01eb, B:116:0x01ee, B:118:0x01f4, B:120:0x01f8, B:125:0x0208, B:128:0x0214, B:132:0x0228, B:131:0x0222, B:133:0x022b, B:85:0x0121, B:89:0x012b, B:90:0x0138, B:91:0x0140, B:92:0x0147, B:93:0x0148, B:95:0x0155, B:97:0x0165, B:96:0x0160, B:98:0x016e, B:99:0x0176, B:100:0x0180, B:101:0x018a, B:103:0x01a2, B:105:0x01ad, B:106:0x01b3, B:107:0x01b8, B:109:0x01c5, B:111:0x01d0, B:110:0x01cb, B:112:0x01d6, B:23:0x0057, B:25:0x0064, B:27:0x0069, B:30:0x0074), top: B:141:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x022e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007b A[Catch: all -> 0x0236, TryCatch #0 {all -> 0x0236, blocks: (B:12:0x0028, B:15:0x003c, B:20:0x004b, B:24:0x005d, B:33:0x007b, B:35:0x0081, B:37:0x008d, B:41:0x009f, B:43:0x00a8, B:46:0x00b0, B:40:0x0099, B:48:0x00b9, B:52:0x00cb, B:54:0x00d4, B:55:0x00d7, B:51:0x00c5, B:59:0x00e1, B:60:0x00e4, B:62:0x00ea, B:83:0x0119, B:113:0x01e4, B:115:0x01eb, B:116:0x01ee, B:118:0x01f4, B:120:0x01f8, B:125:0x0208, B:128:0x0214, B:132:0x0228, B:131:0x0222, B:133:0x022b, B:85:0x0121, B:89:0x012b, B:90:0x0138, B:91:0x0140, B:92:0x0147, B:93:0x0148, B:95:0x0155, B:97:0x0165, B:96:0x0160, B:98:0x016e, B:99:0x0176, B:100:0x0180, B:101:0x018a, B:103:0x01a2, B:105:0x01ad, B:106:0x01b3, B:107:0x01b8, B:109:0x01c5, B:111:0x01d0, B:110:0x01cb, B:112:0x01d6, B:23:0x0057, B:25:0x0064, B:27:0x0069, B:30:0x0074), top: B:141:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ea A[Catch: all -> 0x0236, LOOP:1: B:61:0x00e8->B:62:0x00ea, LOOP_END, TryCatch #0 {all -> 0x0236, blocks: (B:12:0x0028, B:15:0x003c, B:20:0x004b, B:24:0x005d, B:33:0x007b, B:35:0x0081, B:37:0x008d, B:41:0x009f, B:43:0x00a8, B:46:0x00b0, B:40:0x0099, B:48:0x00b9, B:52:0x00cb, B:54:0x00d4, B:55:0x00d7, B:51:0x00c5, B:59:0x00e1, B:60:0x00e4, B:62:0x00ea, B:83:0x0119, B:113:0x01e4, B:115:0x01eb, B:116:0x01ee, B:118:0x01f4, B:120:0x01f8, B:125:0x0208, B:128:0x0214, B:132:0x0228, B:131:0x0222, B:133:0x022b, B:85:0x0121, B:89:0x012b, B:90:0x0138, B:91:0x0140, B:92:0x0147, B:93:0x0148, B:95:0x0155, B:97:0x0165, B:96:0x0160, B:98:0x016e, B:99:0x0176, B:100:0x0180, B:101:0x018a, B:103:0x01a2, B:105:0x01ad, B:106:0x01b3, B:107:0x01b8, B:109:0x01c5, B:111:0x01d0, B:110:0x01cb, B:112:0x01d6, B:23:0x0057, B:25:0x0064, B:27:0x0069, B:30:0x0074), top: B:141:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void parseArray(java.util.Collection r17, java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 611
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseArray(java.util.Collection, java.lang.Object):void");
    }

    public <T> T parseObject(Class<T> cls) {
        return (T) parseObject(cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return (T) parseObject(type, (Object) null);
    }

    public <T> T parseObject(Type type, Object obj) {
        JSONLexer jSONLexer = this.lexer;
        int i = jSONLexer.token;
        if (i == 8) {
            jSONLexer.nextToken();
            return null;
        }
        if (i == 4) {
            if (type == byte[].class) {
                T t = (T) jSONLexer.bytesValue();
                this.lexer.nextToken();
                return t;
            } else if (type == char[].class) {
                String stringVal = jSONLexer.stringVal();
                this.lexer.nextToken();
                return (T) stringVal.toCharArray();
            }
        }
        try {
            return (T) this.config.getDeserializer(type).deserialze(this, type, obj);
        } catch (JSONException e) {
            throw e;
        } catch (Exception e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public void parseObject(Object obj) {
        Object deserialze;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        int i = this.lexer.token;
        if (i != 12 && i != 16) {
            throw new JSONException("syntax error, expect {, actual " + JSONToken.name(i));
        }
        while (true) {
            String scanSymbol = this.lexer.scanSymbol(this.symbolTable);
            if (scanSymbol == null) {
                JSONLexer jSONLexer = this.lexer;
                int i2 = jSONLexer.token;
                if (i2 == 13) {
                    jSONLexer.nextToken(16);
                    return;
                } else if (i2 == 16) {
                    continue;
                }
            }
            FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(scanSymbol) : null;
            if (fieldDeserializer == null) {
                JSONLexer jSONLexer2 = this.lexer;
                if ((jSONLexer2.features & Feature.IgnoreNotMatch.mask) != 0) {
                    jSONLexer2.nextTokenWithChar(':');
                    parse();
                    JSONLexer jSONLexer3 = this.lexer;
                    if (jSONLexer3.token == 13) {
                        jSONLexer3.nextToken();
                        return;
                    }
                } else {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + scanSymbol);
                }
            } else {
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                Class<?> cls2 = fieldInfo.fieldClass;
                Type type = fieldInfo.fieldType;
                if (cls2 == Integer.TYPE) {
                    this.lexer.nextTokenWithChar(':');
                    deserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else if (cls2 == String.class) {
                    this.lexer.nextTokenWithChar(':');
                    deserialze = parseString();
                } else if (cls2 == Long.TYPE) {
                    this.lexer.nextTokenWithChar(':');
                    deserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else {
                    ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                    this.lexer.nextTokenWithChar(':');
                    deserialze = deserializer2.deserialze(this, type, null);
                }
                fieldDeserializer.setValue(obj, deserialze);
                JSONLexer jSONLexer4 = this.lexer;
                int i3 = jSONLexer4.token;
                if (i3 != 16 && i3 == 13) {
                    jSONLexer4.nextToken(16);
                    return;
                }
            }
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((this.lexer.features & Feature.OrderedField.mask) != 0 ? new JSONObject(new LinkedHashMap()) : new JSONObject(), (Object) null);
    }
}
