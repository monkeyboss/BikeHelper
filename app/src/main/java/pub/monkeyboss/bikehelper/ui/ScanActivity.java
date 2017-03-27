package pub.monkeyboss.bikehelper.ui;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.monkeyboss.bikehelper.R;

public class ScanActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener {

    @BindView(R.id.btn_flash)
    View swichFlash;
    @BindView(R.id.icon_light)
    SimpleDraweeView iconLight;
    @BindView(R.id.btn_flash_text)
    TextView btnFlashText;
    @BindView(R.id.dbv_custom)
    DecoratedBarcodeView mDBV;

    @BindColor(R.color.colorAccent)
    int colorLightOn;
    @BindColor(R.color.colorGrayTint)
    int colorLightOff;

    private CaptureManager captureManager;
    private boolean isLightOn = false;

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);

        mDBV.setTorchListener(this);

        // 如果没有闪光灯功能，就去掉相关按钮
        if (!hasFlash()) {
            swichFlash.setVisibility(View.GONE);
        }

        //重要代码，初始化捕获
        captureManager = new CaptureManager(this, mDBV);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
    }

    // torch 手电筒
    @Override
    public void onTorchOn() {
        isLightOn = true;
//        swichFlash.setBackgroundResource(R.drawable.bg_btn_flash_on);
        iconLight.setImageURI("res:///" + R.drawable.icon_light_on);
        btnFlashText.setText("关闭手电筒");
        btnFlashText.setTextColor(colorLightOn);
    }

    @Override
    public void onTorchOff() {
        isLightOn = false;
//        swichFlash.setBackgroundResource(R.drawable.bg_btn_flash_off);
        iconLight.setImageURI("res:///" + R.drawable.icon_light_off);
        btnFlashText.setText("打开手电筒");
        btnFlashText.setTextColor(colorLightOff);
    }

    // 判断是否有闪光灯功能
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    // 点击切换闪光灯
    @OnClick(R.id.btn_flash)
    public void swichLight() {
        if (isLightOn) {
            mDBV.setTorchOff();
        } else {
            mDBV.setTorchOn();
        }
    }

    @OnClick({R.id.toolbar_icon_left, R.id.toolbar_icon_right})
    void onToolbarClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_icon_left:
                finish();
                break;
            case R.id.toolbar_icon_right:
                Toast.makeText(this, "跳转到指南", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
