package com.example.coordinateviewer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private TextView locationTextView;
    private TextView flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI要素の取得
        locationTextView = findViewById(R.id.locationTextView);
        flag = findViewById(R.id.flag);
        Button getLocationButton = findViewById(R.id.getLocationButton);

        // LocationManagerの初期化
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // LocationListenerの初期化
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // 位置情報が更新されたときの処理
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                locationTextView.setText("緯度: " + latitude + "\n経度: " + longitude);

                Flag.printFlag(flag, latitude, longitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        // ボタンがクリックされたとき
        getLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // パーミッションの確認
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {
                    // 位置情報の更新をリクエスト
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            0, 0, locationListener);

                }
            }
        });


    }
}
