package hu.webandmore.weatherappmvp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.webandmore.weatherappmvp.R;
import hu.webandmore.weatherappmvp.app.WeatherApplication;
import hu.webandmore.weatherappmvp.model.Location;

public class MainActivity extends AppCompatActivity implements MainScreen {

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

    @BindView(R.id.descData)
    TextView descData;

    private static String TAG = "MainActivity";
    private String location = "";

    private PlaceAutocompleteFragment autocompleteFragment;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherApplication.injector.inject(this);

        ButterKnife.bind(this);

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "Place: " + place.getName());
                location = place.getName().toString();
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        assert autocompleteFragment.getView() != null;
        ((EditText)autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setText(location);
    }

    @OnClick(R.id.showWeatherBtn)
    public void getWeatherData(View view) {
        presenter.showLocationWeather(location);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachScreen();
    }

    @Override
    public void showWeather(Location location) {
        mWeatherDataView.setVisibility(View.VISIBLE);
        locationData.setText(location.getName());

        currentTempData.setText(String.format(getResources().getString(R.string.temp_with_param),
                location.getMain().getTemp()));
        minTempData.setText(String.format(getResources().getString(R.string.temp_with_param),
                location.getMain().getTemp_min()));
        maxTempData.setText(String.format(getResources().getString(R.string.temp_with_param),
                location.getMain().getTemp_max()));
        windData.setText(String.format(getResources().getString(R.string.wind_speed_with_param),
                location.getWind().getSpeedInKm()));
        descData.setText(location.getWeather()[0].getDescription());
    }

    @Override
    public void showNetworkError(String errorMsg) {
        mWeatherDataView.setVisibility(View.GONE);
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
