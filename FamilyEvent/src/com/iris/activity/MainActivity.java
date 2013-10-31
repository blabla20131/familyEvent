package com.iris.activity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.MenuDrawer.Type;
import net.simonvt.menudrawer.Position;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.iris.constants.Constants;
import com.iris.familyevent.R;
import com.iris.kakao.KakaoLink;
import com.iris.utils.EntryAdapter;
import com.iris.utils.EntryItem;
import com.iris.utils.Item;
import com.iris.utils.SectionItem;

public class MainActivity extends Activity {

    MenuDrawer menuDrawerLeftMenu;
    ArrayList<Item> items = new ArrayList<Item>();
    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuListMake();

        menuDrawerLeftMenu = MenuDrawer.attach
                (this, Type.BEHIND, Position.LEFT, MenuDrawer.MENU_DRAG_WINDOW);
        menuDrawerLeftMenu.setContentView(R.layout.activity_main);
        menuDrawerLeftMenu.setMenuView(R.layout.activity_left_menu);

        EntryAdapter adapter = new EntryAdapter(this, items);
        mList = (ListView) findViewById(R.id.slideMenuList);
        mList.setAdapter(adapter);

        mList.setOnItemClickListener(mOnItemClickListener);

        findViewById(R.id.calendarBtn).setOnClickListener(mClickListener);
        findViewById(R.id.writeBtn).setOnClickListener(mClickListener);
        findViewById(R.id.leftMenuBtn).setOnClickListener(mClickListener);
    }

    public void name(View v) throws NameNotFoundException {
        finish();
    }

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.leftMenuBtn:
                menuDrawerLeftMenu.openMenu();
                break;
            case R.id.writeBtn:
                Intent writeActivityIntent = new Intent(MainActivity.this, WriteActivity.class);
                startActivity(writeActivityIntent);
                break;
            case R.id.calendarBtn:
                Intent calendarActivityIntent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(calendarActivityIntent);
                break;
            }
        }
    };

    private final OnItemClickListener mOnItemClickListener = new OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
        {

            String toastMessage = ((EntryItem) items.get(position)).title;

            if (toastMessage.equals("공지사항")) {
                Intent noticeActivityIntent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(noticeActivityIntent);
            } else if (toastMessage.equals("리뷰 쓰기")) {
                Intent writeReviewActivityIntent = new Intent(MainActivity.this, WriteReviewActivity.class);
                startActivity(writeReviewActivityIntent);
            } else if (toastMessage.equals("카카오톡 추천")) {

                try {
                    sendAppData(view);
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (toastMessage.equals("문제 신고")) {
                Intent problemReportActivityIntent = new Intent(MainActivity.this, ProblemReportActivity.class);
                startActivity(problemReportActivityIntent);
            } else {
                Intent configActivityIntent = new Intent(MainActivity.this, ConfigActivity.class);
                startActivity(configActivityIntent);
            }
        }
    };

    private void menuListMake() {

        items.add(new SectionItem("카테고리"));
        items.add(new EntryItem("공지사항", R.drawable.slide_menu_icon_search_off));
        items.add(new EntryItem("리뷰 쓰기", R.drawable.slide_menu_icon_search_off));
        items.add(new EntryItem("카카오톡 추천", R.drawable.slide_menu_icon_search_off));
        items.add(new EntryItem("문제 신고", R.drawable.slide_menu_icon_search_off));
        items.add(new EntryItem("설정", R.drawable.slide_menu_icon_search_off));
    }

    public void sendUrlLink(View v) throws NameNotFoundException {
        // Recommended: Use application context for parameter.
        KakaoLink kakaoLink = KakaoLink.getLink(getApplicationContext());

        // check, intent is available.
        if (!kakaoLink.isAvailableIntent()) {
            alert("Not installed KakaoTalk.");
            return;
        }

        /**
         * @param activity
         * @param url: 유저에게 전달될 메세지에 포함되는 링크 url(모바일웹)
         * @param message: 유저에게 전달될 메세지 내용(UTF-8)
         * @param appId: App bundle id 또는 package id (예: com.company.app)정확히 입력하지않을 경우 이용이 제한될 수 있습니다.
         * @param appVer: 3rd app의 버전
         * @param appName: 3rd app의 정확한 이름
         * @param encoding
         */
        kakaoLink.openKakaoLink(this,
                "http://www.naver.com", // url 들어갈 자리(유저에게 전달될 메세지에 포함되는 링크 url(모바일웹))
                "내용들어갈 자리", // 유저에게 전달될 메세지 내용(UTF-8)
                getPackageName(),
                getPackageManager().getPackageInfo(getPackageName(), 0).versionName,
                "제목들어갈 자리",
                Constants.kakao_encoding);
    }

    public void sendAppData(View v) throws NameNotFoundException {
        ArrayList<Map<String, String>> metaInfoArray = new ArrayList<Map<String, String>>();

        // If application is support Android platform.
        Map<String, String> metaInfoAndroid = new Hashtable<String, String>(1);
        metaInfoAndroid.put("os", "android");//(3rd app이 지원하는 OS Platform)
        metaInfoAndroid.put("devicetype", "phone");
        metaInfoAndroid.put("installurl", "market://details?id=com.kakao.talk"); //설치 Url(3rd app의 Goole Play나 iTunes의 설치 url)
        metaInfoAndroid.put("executeurl", "iris://iris.com"); // 실행 url(app을 구동시키기 위한 url)

        // add to array
        metaInfoArray.add(metaInfoAndroid);

        // Recommended: Use application context for parameter. 
        KakaoLink kakaoLink = KakaoLink.getLink(getApplicationContext());

        // check, intent is available.
        if (!kakaoLink.isAvailableIntent()) {
            alert("Not installed KakaoTalk.");
            return;
        }

        /**
         * @param activity
         * @param url: 유저에게 전달될 메세지에 포함되는 링크 url(모바일웹)
         * @param message: 유저에게 전달될 메세지 내용(UTF-8)
         * @param appId: App bundle id 또는 package id (예: com.company.app)정확히 입력하지않을 경우 이용이 제한될 수 있습니다.
         * @param appVer: 3rd app의 버전
         * @param appName: 3rd app의 정확한 이름
         * @param encoding
         * @param metaInfoArray
         */
        kakaoLink.openKakaoAppLink(
                this,
                "http://link.kakao.com/?test-android-app", // 카카오톡 앱 연동 해주는 url
                "내용이 들어갈 자리",
                getPackageName(),
                getPackageManager().getPackageInfo(getPackageName(), 0).versionName,
                "제목이 들어갈 자리",
                Constants.kakao_encoding,
                metaInfoArray);
    }

    private void alert(String message) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .create().show();
    }
}
