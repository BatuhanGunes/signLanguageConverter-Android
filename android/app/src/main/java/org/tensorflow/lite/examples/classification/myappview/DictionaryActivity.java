package org.tensorflow.lite.examples.classification.myappview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import org.tensorflow.lite.examples.classification.R;
import org.tensorflow.lite.examples.classification.myappview.viewpager.Tab1;
import org.tensorflow.lite.examples.classification.myappview.viewpager.Tab2;
import org.tensorflow.lite.examples.classification.myappview.viewpager.Tab3;

import java.util.ArrayList;
import java.util.List;

public class DictionaryActivity extends AppCompatActivity {

    private Button button_back;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private Tab1 tab1;
    private Tab2 tab2;
    private Tab3 tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        button_back = (Button)findViewById(R.id.button_back);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tablayout);

        tab1 = new Tab1();
        tab2 = new Tab2();
        tab3 = new Tab3();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(tab1, "A - M");
        viewPagerAdapter.addFragment(tab2, "N - Z");
        viewPagerAdapter.addFragment(tab3, "0 - 9");
        viewPager.setAdapter(viewPagerAdapter);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DictionaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title){

            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
}
