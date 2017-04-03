package no_clay.blogclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no_clay.blogclient.Fragments.MyBlogFragment;
import no_clay.blogclient.Fragments.TopTenFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contentPanel)
    FrameLayout mContentPanel;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;

    TopTenFragment mTopTenFragment;
    MyBlogFragment mMyBlogFragment;
    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ActionBarDrawerToggle toggle =
                    new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, 0, 0);
            toggle.syncState();
            mDrawerLayout.addDrawerListener(toggle);
            actionBar.setTitle("");
            mTitle.setText("博客天下");
        }
        initFragment();

    }

    private void initFragment() {
        mTopTenFragment = new TopTenFragment();
        mNavigationView.setNavigationItemSelectedListener(this);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.contentPanel, mTopTenFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.header_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.searchButton: {
                //搜索
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.topPanel: {
                if (mTopTenFragment == null){
                    mTopTenFragment = new TopTenFragment();
                }
                mFragmentManager.beginTransaction().replace(R.id.contentPanel, mTopTenFragment).commit();
                break;
            }
            case R.id.blogPanel: {
                if (mMyBlogFragment == null){
                    mMyBlogFragment = new MyBlogFragment();
                }
                mFragmentManager.beginTransaction().replace(R.id.contentPanel, mMyBlogFragment).commit();
                break;
            }
            case R.id.questionPanel: {
                break;
            }
            case R.id.suggest: {
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:noclay@vip.qq.com"));
                startActivity(intent);
                break;
            }
            case R.id.about: {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.xiyoumobile.com/"));
                startActivity(intent);
                break;
            }
        }
        mDrawerLayout.closeDrawers();
        return false;
    }
}
