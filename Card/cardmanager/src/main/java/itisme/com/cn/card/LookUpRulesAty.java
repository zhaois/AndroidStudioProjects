package itisme.com.cn.card;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import itisme.com.cn.card.dao.ISQLiteOperate;
import itisme.com.cn.card.dao.SQLiteOperate;
import itisme.com.cn.card.utils.StaticMethod;


public class LookUpRulesAty extends Activity implements View.OnClickListener, OnItemClickListener {
    private static final String TAG = "LookUp";
    private ISQLiteOperate isqLiteOperate;
    private EditText etRulesContent;
    private ListView listView;
    private List<String> rulesList;
    private List<List<String>> rulesLists;
    private List<List<String>> rulesContentList;
    private ArrayList<TextView> textViews;
    private List<String> rules;
    private int postionRules = -1;//当前所点击的规则位置
    private int postionContentRules = -1;// 当前所点击的规则内容位置


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_up_rules);
        initSql();
        initRulesList();
        initRulesContentList();
        initView();
        initListView();
    }

    private void initListView() {
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(LookUpRulesAty.this, android.R.layout.simple_spinner_item, rulesList);
        if (listView != null) {
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(this);
    }

    private void setTextViews() {
        int i = 0;
        int onClick = -1;
        if (rules != null && rules.size() != 0) {
            for (String str : rules) {
                if(i>=28){
                    break;
                }
                if (StaticMethod.isEmpty(str)) {
                    textViews.get(i).setText("");
                } else {
                    onClick++;
                    textViews.get(i).setText(str);
                }
                i++;
            }
            setTextViewsOnClik(onClick);
        } else {
            return;
        }
    }

    private void initRulesContentList() {
        if (isqLiteOperate != null) {
            rulesContentList = isqLiteOperate.getAllContent(getString(R.string.table_name));
        }

    }

    private void initRulesList() {
        rulesList = new ArrayList<String>();
        rulesList.add(getString(R.string.no_data_currently));
        if (isqLiteOperate == null) {
            return;
        }
        rulesLists = isqLiteOperate.getAllRules(getString(R.string.table_name));
        Log.i(TAG, rulesLists.get(0).toString());
        if (rulesLists == null || rulesLists.size() == 0) {
            return;
        }
        rulesList.clear();
        for (List<String> list : rulesLists) {
            String str = "";
            for (String string : list) {
                if (StaticMethod.isEmpty(string)) {
                    break;
                }
                str += string + ",";
            }
            str = str.substring(0, str.length() - 1);
            rulesList.add(str);
        }
    }

    private void initView() {
        etRulesContent = (EditText) findViewById(R.id.etRulesContent);
        initTextViews();
        listView = (ListView) findViewById(R.id.listView);
    }

    private void initSql() {
        isqLiteOperate = new SQLiteOperate(getString(R.string.db_name));
    }


    public void changeContent(View view) {

    }

    public void backMain(View view) {
        Intent intent = new Intent(LookUpRulesAty.this, MainActivity.class);
        startActivity(intent);
    }

    /*
     * 初始化显示的TextView
     */
    private void initTextViews() {
        textViews = new ArrayList<TextView>();
        textViews.add((TextView) findViewById(R.id.tvWest1));
        textViews.add((TextView) findViewById(R.id.tvNorth1));
        textViews.add((TextView) findViewById(R.id.tvEast1));
        textViews.add((TextView) findViewById(R.id.tvSouth1));
        textViews.add((TextView) findViewById(R.id.tvWest2));
        textViews.add((TextView) findViewById(R.id.tvNorth2));
        textViews.add((TextView) findViewById(R.id.tvEast2));
        textViews.add((TextView) findViewById(R.id.tvSouth2));
        textViews.add((TextView) findViewById(R.id.tvWest3));
        textViews.add((TextView) findViewById(R.id.tvNorth3));
        textViews.add((TextView) findViewById(R.id.tvEast3));
        textViews.add((TextView) findViewById(R.id.tvSouth3));
        textViews.add((TextView) findViewById(R.id.tvWest4));
        textViews.add((TextView) findViewById(R.id.tvNorth4));
        textViews.add((TextView) findViewById(R.id.tvEast4));
        textViews.add((TextView) findViewById(R.id.tvSouth4));
        textViews.add((TextView) findViewById(R.id.tvWest5));
        textViews.add((TextView) findViewById(R.id.tvNorth5));
        textViews.add((TextView) findViewById(R.id.tvEast5));
        textViews.add((TextView) findViewById(R.id.tvSouth5));
        textViews.add((TextView) findViewById(R.id.tvWest6));
        textViews.add((TextView) findViewById(R.id.tvNorth6));
        textViews.add((TextView) findViewById(R.id.tvEast6));
        textViews.add((TextView) findViewById(R.id.tvSouth6));
        textViews.add((TextView) findViewById(R.id.tvWest7));
        textViews.add((TextView) findViewById(R.id.tvNorth7));
        textViews.add((TextView) findViewById(R.id.tvEast7));
        textViews.add((TextView) findViewById(R.id.tvSouth7));

    }

    public void setTextViewsOnClik(int postion){
        for (TextView textView : textViews) {
            textView.setOnClickListener(null);
        }
        for (int i = 0; i <= postion; i++) {
            textViews.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG,"hello");
        for (TextView view: textViews){
            if(view.getId() == v.getId()){
                postionContentRules = textViews.indexOf(view);
                etRulesContent.setText(rulesContentList.get(postionRules).get(postionContentRules).toString().trim());

            }
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "Hello");
        switch (parent.getId()) {
            case R.id.listView:
                if (rulesLists != null && rulesLists.size() != 0) {
                    rules = rulesLists.get(position);
                    Log.i("listView", rules.toString());
                    Log.i(TAG, rules.toString() + " onItem");
                    postionRules = position;
                    setTextViews();
                } else {
                    Log.i("listView", "hello");
                    return;
                }
                break;
        }
    }


}
