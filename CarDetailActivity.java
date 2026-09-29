package com.example.carrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CarDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RESERVATION_BUNDLE = "reservation_bundle";
    public static final String KEY_RES_CAR_NAME = "res_car_name";
    public static final String KEY_RES_CAR_PRICE = "res_car_price";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        TextView tvDetailName = findViewById(R.id.tvDetailName);
        TextView tvDetailBrand = findViewById(R.id.tvDetailBrand);
        TextView tvDetailPrice = findViewById(R.id.tvDetailPrice);
        TextView tvDetailDesc = findViewById(R.id.tvDetailDesc);
        Button btnReserveNow = findViewById(R.id.btnReserveNow);

        // 1) Receive the Bundle sent from MainActivity
        Bundle carBundle = getIntent().getBundleExtra(MainActivity.EXTRA_CAR_BUNDLE);

        String carName = "";
        double carPrice = 0.0;

        if (carBundle != null) {
            carName = carBundle.getString(MainActivity.KEY_CAR_NAME, "Unknown");
            String carBrand = carBundle.getString(MainActivity.KEY_CAR_BRAND, "");
            carPrice = carBundle.getDouble(MainActivity.KEY_CAR_PRICE, 0.0);
            String carDesc = carBundle.getString(MainActivity.KEY_CAR_DESC, "");

            tvDetailName.setText(carName);
            tvDetailBrand.setText(carBrand);
            tvDetailPrice.setText(carPrice + " $/day");
            tvDetailDesc.setText(carDesc);
        }

        final String finalCarName = carName;
        final double finalCarPrice = carPrice;

        // 2) Build a NEW Bundle to pass along to the next screen
        btnReserveNow.setOnClickListener(v -> {
            Bundle reservationBundle = new Bundle();
            reservationBundle.putString(KEY_RES_CAR_NAME, finalCarName);
            reservationBundle.putDouble(KEY_RES_CAR_PRICE, finalCarPrice);

            Intent intent = new Intent(CarDetailActivity.this, ReservationSummaryActivity.class);
            intent.putExtra(EXTRA_RESERVATION_BUNDLE, reservationBundle);
            startActivity(intent);
        });
    }
}