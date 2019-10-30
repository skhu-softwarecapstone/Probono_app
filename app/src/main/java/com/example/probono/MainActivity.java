package com.example.probono;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.probono.DailylogPageFragment.HttpClient;
import com.example.probono.FragmentPages.DailylogPageFragment;
import com.example.probono.FragmentPages.HomePageFragment;
import com.example.probono.FragmentPages.MySponlistPageFragment;
import com.example.probono.FragmentPages.NotilistPageFragment;
import com.example.probono.FragmentPages.SponPageFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //DrawerLayout 받아올 객체 선언
    private DrawerLayout drawerLayout;
    //NavigationView를 받아올 객체 선언
    private NavigationView navigationView;
    //메인페이지에서 프래그먼트로 전활된 페이지 객체를 선언
    private LinearLayout transitionPage;
    private String userid;
    private String role;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.intent = getIntent();

        userid = intent.getStringExtra("userId");
        role = intent.getStringExtra("userRole");

        //ToolBar 초기화
        initializeAppBar();
        //NavigationDrawer 초기화
        initializeDraewr();
        //NavigationEvent 초기화
        setNavigationEventHandler();
        HttpClient.initClient();
       //화면 전환 프래그먼트 선언 및 초기 화면 설정
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.transition_view_page, new HomePageFragment()).commit();
    }

    //ToolBar 초기화
    public void initializeAppBar() {
        //Toolbar를 통해 Appbar 생성
        Toolbar toolbar = findViewById(R.id.toolbar);
        //default appbar를 actionbar가 아닌 toolbar로 설정
        setSupportActionBar(toolbar);
    }

    //NavigtionDrawer 초기화
    public void initializeDraewr() {
        //App bar 좌측 영역에 Drawer를 Open하기 위한 Icon추가
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //icon 설정
        //getSupportActionBar().setHomeAsUpIndicator()
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        transitionPage = (LinearLayout) findViewById(R.id.transition_view_page);
    }

    //Navigation 이벤트처리
    public void setNavigationEventHandler() {
        if(navigationView!=null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    //메뉴아이템이 선택되었을시에 drawerLayout을 닫는다.
                    menuItem.setChecked(true);
                    if(drawerLayout!=null) drawerLayout.closeDrawers();

                    switch (menuItem.getItemId())
                    {
                        case R.id.home_menu:
                            replaceTransitionPageFragment(new HomePageFragment());
                            return true;
                        case R.id.dailylog_menu:
                            replaceTransitionPageFragment(new DailylogPageFragment(userid));
                            return true;
                        case R.id.my_sponlist_menu:
                            replaceTransitionPageFragment(new MySponlistPageFragment(userid));
                            return true;
                        case R.id.notilist_menu:
                            replaceTransitionPageFragment(new NotilistPageFragment());
                            return true;
                        case R.id.spon_page_menu:
                            replaceTransitionPageFragment(new SponPageFragment());
                            return true;
                    }

                    return true;
                }
            });
        }
    }

    //화면 페이지 전환
    private void replaceTransitionPageFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.transition_view_page, fragment).commit();
    }

    //Navigation 메뉴를 뷰에 초기화
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_item, menu);

        //메뉴 페이지가 동적으로 늘어났을시에  이벤트 처리 구문
        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return false;
            }
        };

        return  true;
    }

    //appBar 아이템 셀렉트시 이벤트 처리
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //AppBar에서 navigationDrawer 아이콘 클릭시 네비게이션 드로워 뷰 펼쳐짐
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.home_menu:
                replaceTransitionPageFragment(new HomePageFragment());
                return true;
            case R.id.dailylog_menu:
                replaceTransitionPageFragment(new DailylogPageFragment(userid));
                return true;
            case R.id.my_sponlist_menu:
                replaceTransitionPageFragment(new MySponlistPageFragment(userid));
                return true;
            case R.id.notilist_menu:
                replaceTransitionPageFragment(new NotilistPageFragment());
                return true;
            case R.id.spon_page_menu:
                replaceTransitionPageFragment(new SponPageFragment());
                return true;
            }
        return super.onOptionsItemSelected(item);
    }
}
