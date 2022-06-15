package hcmute.edu.vn.reader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import hcmute.edu.vn.reader.fragment.BookingFragment;
import hcmute.edu.vn.reader.fragment.EditProfileFragment;
import hcmute.edu.vn.reader.fragment.HomeFragment;
import hcmute.edu.vn.reader.fragment.MenuFragment;
import hcmute.edu.vn.reader.fragment.ProfileFragment;
import hcmute.edu.vn.reader.fragment.SettingsFragment;
import hcmute.edu.vn.reader.model.Dish;
import hcmute.edu.vn.reader.model.Store;
import hcmute.edu.vn.reader.model.User;

public class MainActivity extends AppCompatActivity implements Goto{
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, new HomeFragment()).commit();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                int id = item.getItemId();

                if(id == R.id.homeItem){
                    fragment = new HomeFragment();
                }else if(id == R.id.profileItem){
                    fragment = new ProfileFragment();
                }else if(id == R.id.settingItem){
                    fragment = new SettingsFragment();
                }
                gotoFragment(fragment);
                return true;
            }
        });
    }

    private void gotoFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).commit();
    }

    public void GotoMenu(Store store) {
        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setStore(store);
        gotoFragment(menuFragment);
    }

    @Override
    public void GotoBooking(Dish dish) {
        BookingFragment bookingFragment = new BookingFragment();
        bookingFragment.setDish(dish);
        gotoFragment(bookingFragment);
    }

    @Override
    public void GotoEditProfile(User user) {
        EditProfileFragment editProfileFragment = new EditProfileFragment();
        editProfileFragment.setUser(user);
        gotoFragment(editProfileFragment);
    }

    @Override
    public void GotoProfile() {
        ProfileFragment profileFragment = new ProfileFragment();
        gotoFragment(profileFragment);
    }
}