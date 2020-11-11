package org.humanoid.mapmyweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jugal Mistry on 10/8/2019.
 */
public class WeatherBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private ConstraintLayout mRootLayout;
    private TextView mAddressTV, mDateTV, mTimeTV, mTempMinTV, mTempMaxTV, mHumidityTV,
            mShortDescTV, mLongDescTV;
    private ImageView mIcon;
    private ProgressBar mProgressBar;
    private String mAddress;
    private LatLng mLatLng;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressBar = view.findViewById(R.id.loading);
        mRootLayout = view.findViewById(R.id.root_bottom_sheet);
        mAddressTV = view.findViewById(R.id.address_tv);
        mAddressTV.setText(mAddress);
        mDateTV = view.findViewById(R.id.datetv);
        mTimeTV = view.findViewById(R.id.timetv);
        mTempMinTV = view.findViewById(R.id.temMin);
        mTempMaxTV = view.findViewById(R.id.temMax);
        mHumidityTV = view.findViewById(R.id.humiditytv);
        mShortDescTV = view.findViewById(R.id.shortDesctv);
        mLongDescTV = view.findViewById(R.id.longdesctv);
        mIcon = view.findViewById(R.id.iconNewActivty);

        loadData();
    }

    public void getData(String address, LatLng latLng) {
        mAddress = address;
        mLatLng = latLng;
    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        String lat = String.valueOf(mLatLng.latitude), lon = String.valueOf(mLatLng.longitude);
        Call<WeatherResponse> call = service.getCurrentWeatherData(lat, lon,
                "Ya aint gettin any key BUOAY");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;
                    mRootLayout.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.GONE);
                    setData(weatherResponse);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });
    }

    private void setData(WeatherResponse response) {
        String tempMax = String.valueOf(kToC(response.getMain().getTemp_max())),
                tempMin = String.valueOf(kToC(response.getMain().getTemp_min())),
                humidity = String.valueOf(response.getMain().getHumidity());
        mTempMaxTV.setText(tempMax.substring(0,5) + " °C");
        mTempMinTV.setText(tempMin.substring(0,5) + " °C");
        mHumidityTV.setText("Humidity: " + humidity + "%");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        Date date = new Date();
        mDateTV.setText(simpleDateFormat.format(date));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        mTimeTV.setText(simpleDateFormat1.format(date));

        mShortDescTV.setText(response.getWeather().get(0).getMain());
        mLongDescTV.setText(response.getWeather().get(0).getDescription());

        setIcon(response.getWeather().get(0).getIcon());
    }

    private void setIcon(String iconID) {
        if(iconID.equals("01d")){
            mIcon.setImageResource(R.drawable.ico01d);
        } else if(iconID.equals("01n")){
            mIcon.setImageResource(R.drawable.ico01n);
        } else if(iconID.equals("02d")){
            mIcon.setImageResource(R.drawable.ico02d);
        } else if(iconID.equals("02n")){
            mIcon.setImageResource(R.drawable.ico02n);
        } else if(iconID.equals("03d") || iconID.equals("03n")){
            mIcon.setImageResource(R.drawable.ico03d);
        } else if (iconID.equals("04d") || iconID.equals("04n")){
            mIcon.setImageResource(R.drawable.ico04d);
        } else if (iconID.equals("09d") || iconID.equals("09n")){
            mIcon.setImageResource(R.drawable.ico09d);
        } else if(iconID.equals("10d")){
            mIcon.setImageResource(R.drawable.ico10d);
        } else if(iconID.equals("10n")){
            mIcon.setImageResource(R.drawable.ico10n);
        } else if(iconID.equals("11d")){
            mIcon.setImageResource(R.drawable.ico11d);
        } else if(iconID.equals("11n")){
            mIcon.setImageResource(R.drawable.ico11n);
        } else if(iconID.equals("13d")){
            mIcon.setImageResource(R.drawable.ico13d);
        } else if(iconID.equals("13n")){
            mIcon.setImageResource(R.drawable.ico13n);
        } else if(iconID.equals("50d")){
            mIcon.setImageResource(R.drawable.ico50d);
        } else if(iconID.equals("50n")){
            mIcon.setImageResource(R.drawable.ico50n);
        }
    }

    private double fToC(double f) {
        return (f - 32.0) * 5.0/9.0;
    }

    private double kToC(double k) {
        return k - 273.15;
    }
}
