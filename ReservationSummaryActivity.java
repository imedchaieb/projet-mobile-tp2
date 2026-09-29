package com.example.carrentalapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReservationSummaryActivity extends AppCompatActivity {

    private double pricePerDay = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_summary);

        TextView tvSummaryCarName = findViewById(R.id.tvSummaryCarName);
        TextView tvSummaryPricePerDay = findViewById(R.id.tvSummaryPricePerDay);
        EditText etNumberOfDays = findViewById(R.id.etNumberOfDays);
        Button btnCalculateTotal = findViewById(R.id.btnCalculateTotal);
        TextView tvTotalResult = findViewById(R.id.tvTotalResult);

        Bundle reservationBundle = getIntent().getBundleExtra(CarDetailActivity.EXTRA_RESERVATION_BUNDLE);

        if (reservationBundle != null) {
            String carName = reservationBundle.getString(CarDetailActivity.KEY_RES_CAR_NAME, "Unknown");
            pricePerDay = reservationBundle.getDouble(CarDetailActivity.KEY_RES_CAR_PRICE, 0.0);

            tvSummaryCarName.setText(carName);
            tvSummaryPricePerDay.setText(pricePerDay + " $/day");
        }

        btnCalculateTotal.setOnClickListener(v -> {
            String daysText = etNumberOfDays.getText().toString();

            if (daysText.isEmpty()) {
                Toast.makeText(this, "Please enter a number of days", Toast.LENGTH_SHORT).show();
                return;
            }

            int days = Integer.parseInt(daysText);
            double total = pricePerDay * days;
            tvTotalResult.setText("Total: " + total + " $");
        });
    }
}