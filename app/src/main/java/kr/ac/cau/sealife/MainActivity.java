package kr.ac.cau.sealife;

import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    private int lastExpandedPosition = -1;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {


        MenuModel menuModel = new MenuModel("바다생태", true, true); //Menu of Java Tutorials
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("해양생태계란?", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("해양생태계의 구조", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("해양생태계의 중요성", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("해양생태도란?", false, false);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("해양보호구역", true, true); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("해양보호구역이란?", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("해양보호구역현황", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("해양보호구역 지정 및 관리", false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("갯벌정보", true, true); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("갯벌의 정의", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("갯벌의 종류", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("갯벌의 중요성", false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("갯벌 현황", true, true); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("한국의 갯벌", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("세계의 갯벌", false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        Toast.makeText(getApplicationContext(), "Group clicked!", Toast.LENGTH_LONG).show();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    Toast.makeText(getApplicationContext(), "Child clicked!", Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });
    }
    public void SeaEcoListener(View target) {
        Intent intent = new Intent(getApplicationContext(), SeaEco.class);
        startActivity(intent);
    }

    public void RegionListener(View target) {
        Intent intent = new Intent(getApplicationContext(), Region.class);
        startActivity(intent);
    }

    public void MudflatInfoListener(View target) {
        Intent intent = new Intent(getApplicationContext(), MudflatInfo.class);
        startActivity(intent);
    }

    public void MudflatNowListener(View target) {
        Intent intent = new Intent(getApplicationContext(), MudflatNow.class);
        startActivity(intent);
    }

    public void CalActivityListener(View target) {
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);
    }

    public void SListViewListener(View target) {
        Intent intent = new Intent(getApplicationContext(), SortedListView.class);
        startActivity(intent);
    }






}

