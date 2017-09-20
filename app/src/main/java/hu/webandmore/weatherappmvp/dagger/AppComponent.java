package hu.webandmore.weatherappmvp.dagger;

import javax.inject.Singleton;

import dagger.Component;
import hu.webandmore.weatherappmvp.ui.main.MainActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity target);
}
