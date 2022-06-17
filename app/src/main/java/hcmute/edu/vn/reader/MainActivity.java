package hcmute.edu.vn.reader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import hcmute.edu.vn.reader.fragment.BillFragment;
import hcmute.edu.vn.reader.fragment.BookingFragment;
import hcmute.edu.vn.reader.fragment.CartFragment;
import hcmute.edu.vn.reader.fragment.EditProfileFragment;
import hcmute.edu.vn.reader.fragment.HomeFragment;
import hcmute.edu.vn.reader.fragment.LoginFragment;
import hcmute.edu.vn.reader.fragment.BookFragment;
import hcmute.edu.vn.reader.fragment.ProfileFragment;
import hcmute.edu.vn.reader.fragment.RegisterFragment;
import hcmute.edu.vn.reader.fragment.SigninFragment;
import hcmute.edu.vn.reader.model.BookTitle;
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
                }else if(id == R.id.borrowRegisterItem){
                    fragment = new RegisterFragment();
                }else if(id == R.id.borrowBillItem){
                    fragment = new BillFragment();
                }else if(id == R.id.cartItem){
                    fragment = new CartFragment();
                }
                gotoFragment(fragment);
                return true;
            }
        });

        // load current user form sqlite,
        MySingleton.getInstance(this.getApplicationContext()).getCurrentUser();
    }

    private void gotoFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).commit();
    }

    @Override
    public void GotoHome() {
        HomeFragment homeFragment = new HomeFragment();
        gotoFragment(homeFragment);
    }

    public void GotoDetail(BookTitle bookTitle) {
        BookFragment bookFragment = new BookFragment();
        bookFragment.setBookTitle(bookTitle);
        gotoFragment(bookFragment);
    }

    @Override
    public void GotoBooking(List<BookTitle> bookTitles) {
        BookingFragment bookingFragment = new BookingFragment();
        bookingFragment.setBookTitles(bookTitles);
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

    @Override
    public void GotoLogin() {
        LoginFragment loginFragment = new LoginFragment();
        gotoFragment(loginFragment);
    }

    @Override
    public void GotoSignin() {
        SigninFragment signinFragment = new SigninFragment();
        gotoFragment(signinFragment);
    }
}