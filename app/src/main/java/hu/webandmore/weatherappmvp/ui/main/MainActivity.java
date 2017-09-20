package hu.webandmore.weatherappmvp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.webandmore.weatherappmvp.R;
import hu.webandmore.weatherappmvp.model.Location;

public class MainActivity extends AppCompatActivity implements MainScreen{

    @BindView(R.id.typeLocation)
    EditText mLocation;

    @BindView(R.id.weatherData)
    CardView mWeatherDataView;

    @BindView(R.id.locationData)
    TextView locationData;

    @BindView(R.id.currentTempData)
    TextView currentTempData;

    @BindView(R.id.minData)
    TextView minTempData;

    @BindView(R.id.maxTempData)
    TextView maxTempData;

    @BindView(R.id.windData)
    TextView windData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.showWeatherBtn)
    public void getWeatherData(View view) {
        MainPresenter.getInstance().showLocationWeather(mLocation.getText().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        MainPresenter.getInstance().attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MainPresenter.getInstance().detachScreen();
    }

    @Override
    public void showWeather(Location location) {
        mWeatherDataView.setVisibility(View.VISIBLE);
        locationData.setText(location.getName());
        currentTempData.setText(String.valueOf(location.getMain().getTemp()));
        minTempData.setText(String.valueOf(location.getMain().getTemp_min()));
        maxTempData.setText(String.valueOf(location.getMain().getTemp_max()));
        windData.setText(String.valueOf(location.getWind().getSpeed()));
    }

    @Override
    public void showNetworkError(String errorMsg) {
        mWeatherDataView.setVisibility(View.GONE);
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
