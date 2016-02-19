package com.qf.danan.daydayup.other.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.qf.danan.daydayup.R;
import com.qf.danan.daydayup.channel.ui.ChannelFragment;
import com.qf.danan.daydayup.discover.ui.DiscoverFragment;
import com.qf.danan.daydayup.home.ui.HomeFragment;
import com.qf.danan.daydayup.mine.ui.MineFragment;

public class MainActivity extends FragmentActivity {
    private Fragment[] fragments;
    private FrameLayout flcontent;
    private RadioGroup radioGroup;
    private View checkedButon;
    private int checkIndex;

    private RadioGroup.OnCheckedChangeListener changeListener =
            new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    View radioButton = findViewById(checkedId);

                    checkedButon = radioButton;

                    int index = 0;
                    switch(checkedId){
                        case R.id.main_home_rb:
                            index = 0;
                            break;
                        case R.id.main_channel_rb:
                            index = 1;
                            break;
                        case R.id.main_discover_rb:
                            index = 2;
                            break;
                        case R.id.main_mine_rb:
                            index = 3;
                            break;
                        default:
                            index = 0;
                            break;
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();

                    transaction.show(fragments[index]);
                    transaction.hide(fragments[checkIndex]);
                    transaction.commit();
                    checkIndex = index;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup)findViewById(R.id.main_rg);
        radioGroup.setOnCheckedChangeListener(changeListener);

        View firstChild = radioGroup.getChildAt(0);

        fragments = new Fragment[]{new HomeFragment(),new ChannelFragment(),
        new DiscoverFragment(),new MineFragment()};

        flcontent = (FrameLayout) findViewById(R.id.main_content_fl);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //默认选中第一个
        checkedButon = firstChild;
        firstChild.performClick();

        for (int i = 0; i < fragments.length; i++){
            Fragment fragment = fragments[i];
            transaction.add(R.id.main_content_fl,fragment);
            transaction.hide(fragment);
        }

        transaction.show(fragments[0]);
        transaction.commit();
    }

}
