package pub.monkeyboss.bikehelper.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.monkeyboss.bikehelper.util.BikeUriUtil;
import pub.monkeyboss.bikehelper.R;
import pub.monkeyboss.bikehelper.widget.ResultDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.toolbar_icon_left, R.id.toolbar_icon_right})
    void onToolbarClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_icon_left:
                Toast.makeText(this, "left", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_icon_right:
                Toast.makeText(this, "right", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnClick(R.id.btn_scan)
    void onScan() {
//        new IntentIntegrator(this).initiateScan();
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.setCaptureActivity(ScanActivity.class);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (!TextUtils.isEmpty(result.getContents())) {
                String contents = result.getContents();
                Log.e("sdfsdf", contents);
                parse(contents);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void parse(String contents) {
        Map<String, String> result = BikeUriUtil.parse(contents);
        if (result == null) ResultDialog.showDialog(this, false, "无法获取", null, null);
        else
            ResultDialog.showDialog(this, result.get(BikeUriUtil.KEY_NO), result.get(BikeUriUtil.KEY_BRAND), null);
    }

    private void parseT() {
        String str = "http://www.mobike.com/download/app.html?id=11232313_1";
        Uri uri = Uri.parse(str);
        Log.e("getAuthority", uri.getAuthority() + "----");
        Log.e("getEncodedAuthority", uri.getEncodedAuthority() + "----");
        Log.e("getEncodedFragment", uri.getEncodedFragment() + "----");
        Log.e("getEncodedPath", uri.getEncodedPath() + "----");
        Log.e("getEncodedQuery", uri.getEncodedQuery() + "----");
        Log.e("getEncodedSchemeSpe", uri.getEncodedSchemeSpecificPart() + "----");
        Log.e("getEncodedUserInfo", uri.getEncodedUserInfo() + "----");
        Log.e("getFragment", uri.getFragment() + "----");
        Log.e("getHost", uri.getHost() + "----");
        Log.e("getLastPathSegment", uri.getLastPathSegment() + "----");
        Log.e("getPath", uri.getPath() + "----");
        Log.e("getQuery", uri.getQuery() + "----");
        Log.e("getScheme", uri.getScheme() + "----");
        Log.e("getSchemeSpecificPart", uri.getSchemeSpecificPart() + "----");
        Log.e("getUserInfo", uri.getUserInfo() + "----");
        Log.e("getPort", uri.getPort() + "----");

        Log.e("-------------", "-------------------------------------------");

        String str2 = "http://www.mobike.com/download/app/123131231";
        uri = Uri.parse(str2);
        Log.e("getAuthority", uri.getAuthority() + "----");
        Log.e("getEncodedAuthority", uri.getEncodedAuthority() + "----");
        Log.e("getEncodedFragment", uri.getEncodedFragment() + "----");
        Log.e("getEncodedPath", uri.getEncodedPath() + "----");
        Log.e("getEncodedQuery", uri.getEncodedQuery() + "----");
        Log.e("getEncodedSchemeSpe", uri.getEncodedSchemeSpecificPart() + "----");
        Log.e("getEncodedUserInfo", uri.getEncodedUserInfo() + "----");
        Log.e("getFragment", uri.getFragment() + "----");
        Log.e("getHost", uri.getHost() + "----");
        Log.e("getLastPathSegment", uri.getLastPathSegment() + "----");
        Log.e("getPath", uri.getPath() + "----");
        Log.e("getQuery", uri.getQuery() + "----");
        Log.e("getScheme", uri.getScheme() + "----");
        Log.e("getSchemeSpecificPart", uri.getSchemeSpecificPart() + "----");
        Log.e("getUserInfo", uri.getUserInfo() + "----");
        Log.e("getPort", uri.getPort() + "----");

        Log.e("-------------", "-------------------------------------------");

        String str3 = "sfshkdfjwiejf123141sdf";
        uri = Uri.parse(str3);
        Log.e("getAuthority", uri.getAuthority() + "----");
        Log.e("getEncodedAuthority", uri.getEncodedAuthority() + "----");
        Log.e("getEncodedFragment", uri.getEncodedFragment() + "----");
        Log.e("getEncodedPath", uri.getEncodedPath() + "----");
        Log.e("getEncodedQuery", uri.getEncodedQuery() + "----");
        Log.e("getEncodedSchemeSpe", uri.getEncodedSchemeSpecificPart() + "----");
        Log.e("getEncodedUserInfo", uri.getEncodedUserInfo() + "----");
        Log.e("getFragment", uri.getFragment() + "----");
        Log.e("getHost", uri.getHost() + "----");
        Log.e("getLastPathSegment", uri.getLastPathSegment() + "----");
        Log.e("getPath", uri.getPath() + "----");
        Log.e("getQuery", uri.getQuery() + "----");
        Log.e("getScheme", uri.getScheme() + "----");
        Log.e("getSchemeSpecificPart", uri.getSchemeSpecificPart() + "----");
        Log.e("getUserInfo", uri.getUserInfo() + "----");
        Log.e("getPort", uri.getPort() + "----");

        Log.e("-------------", "-------------------------------------------");

        String str4 = "";
        uri = Uri.parse(str4);
        Log.e("getAuthority", uri.getAuthority() + "----");
        Log.e("getEncodedAuthority", uri.getEncodedAuthority() + "----");
        Log.e("getEncodedFragment", uri.getEncodedFragment() + "----");
        Log.e("getEncodedPath", uri.getEncodedPath() + "----");
        Log.e("getEncodedQuery", uri.getEncodedQuery() + "----");
        Log.e("getEncodedSchemeSpe", uri.getEncodedSchemeSpecificPart() + "----");
        Log.e("getEncodedUserInfo", uri.getEncodedUserInfo() + "----");
        Log.e("getFragment", uri.getFragment() + "----");
        Log.e("getHost", uri.getHost() + "----");
        Log.e("getLastPathSegment", uri.getLastPathSegment() + "----");
        Log.e("getPath", uri.getPath() + "----");
        Log.e("getQuery", uri.getQuery() + "----");
        Log.e("getScheme", uri.getScheme() + "----");
        Log.e("getSchemeSpecificPart", uri.getSchemeSpecificPart() + "----");
        Log.e("getUserInfo", uri.getUserInfo() + "----");
        Log.e("getPort", uri.getPort() + "----");
    }
}
