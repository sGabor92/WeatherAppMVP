package hu.webandmore.weatherappmvp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hu.webandmore.weatherappmvp.R;

public class MainActivity extends AppCompatActivity implements MainScreen{

    private EditText mLocation;
    private CardView mWeatherDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocation = (EditText) findViewById(R.id.typeLocation);
        mWeatherDataView = (CardView) findViewById(R.id.weatherData);

        Button btnShowWeatherData = (Button) findViewById(R.id.showWeatherBtn);
        btnShowWeatherData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainPresenter.getInstance().showLocationWeather(mLocation.getText().toString());
            }
        });
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
    public void showWeather(String location) {
        mWeatherDataView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNetworkError(String errorMsg) {
        
    }
}
