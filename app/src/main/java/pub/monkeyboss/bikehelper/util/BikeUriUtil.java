package pub.monkeyboss.bikehelper.util;

import android.content.ContentUris;
import android.content.UriMatcher;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiangXu on 2017-03-23.
 */

public class BikeUriUtil {

    public static final String SCHEME_HTTP = "http";
    public static final String SCHEME_HTTPS = "https";
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int FLAG_OFO = 1;
    public static final int FLAG_MOBIKE = 2;
    public static final int FLAG_BLUEGOGO = 3;
    public static final int FLAG_ZXBIKE = 4;
    public static final int FLAG_COOLQI = 5;
    public static final int FLAG_ERROR = UriMatcher.NO_MATCH;

    public static final String KEY_BRAND = "brand";
    public static final String KEY_NO = "no";

    private static final String BIKENO_ERROR = "获取失败";

    static {
        matcher.addURI("ofo.so", "plate/#", FLAG_OFO);
        matcher.addURI("www.mobike.com", "download/*", FLAG_MOBIKE);
        matcher.addURI("www.bluegogo.com", "*", FLAG_BLUEGOGO);
        matcher.addURI("m.zxbike.net", "app/*", FLAG_ZXBIKE);
        matcher.addURI("bike.coolqi.com", "*", FLAG_COOLQI);
    }

    public static int match(Uri url) {
        return matcher.match(url);
    }

    public static Map<String, String> parse(String url) {
        Uri uri = Uri.parse(url);
        String scheme = uri.getScheme();
        if (BikeUriUtil.SCHEME_HTTP.equals(scheme) || BikeUriUtil.SCHEME_HTTPS.equals(scheme)) {
            HashMap<String, String> map = new HashMap<>();
            int match = BikeUriUtil.match(uri);
            switch (match) {
                case FLAG_OFO:
                    map.put(KEY_BRAND, "ofo");
                    map.put(KEY_NO, parseOfo(uri));
                    return map;
                case FLAG_MOBIKE:
                    map.put(KEY_BRAND, "摩拜单车");
                    map.put(KEY_NO, parseMoBike(uri));
                    return map;
                case FLAG_BLUEGOGO:
                    map.put(KEY_BRAND, "bluegogo");
                    map.put(KEY_NO, parseBluegogo(uri));
                    return map;
                case FLAG_ZXBIKE:
                    map.put(KEY_BRAND, "智享单车");
                    map.put(KEY_NO, parseZxBike(uri));
                    return map;
                case FLAG_COOLQI:
                    map.put(KEY_BRAND, "酷骑单车");
                    map.put(KEY_NO, parseCoolqi(uri));
                    return map;
                case FLAG_ERROR:
                    return null;
            }
        } else {
            return null;
        }
        return null;
    }

    private static String parseOfo(Uri uri) {
        long ofoId = ContentUris.parseId(uri);
        return String.valueOf(ofoId);
    }

    private static String parseMoBike(Uri uri) {
        String b = uri.getQueryParameter("b");
        if (!TextUtils.isEmpty(b)) {
            String[] bs = b.split("_");
            if (bs.length > 0) return bs[0];
            return BIKENO_ERROR;
        }
        return BIKENO_ERROR;
    }

    private static String parseBluegogo(Uri uri) {
        String no = uri.getQueryParameter("no");
        if (!TextUtils.isEmpty(no)) return no;
        else return BIKENO_ERROR;
    }

    private static String parseZxBike(Uri uri) {
        String zxbike = uri.getQueryParameter("zxbike");
        if (!TextUtils.isEmpty(zxbike)) return zxbike;
        else return BIKENO_ERROR;
    }

    private static String parseCoolqi(Uri uri) {
        String sn = uri.getQueryParameter("sn");
        if (!TextUtils.isEmpty(sn)) return sn;
        else return BIKENO_ERROR;
    }
}
