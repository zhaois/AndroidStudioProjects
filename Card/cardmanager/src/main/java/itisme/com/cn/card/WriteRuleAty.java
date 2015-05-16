package itisme.com.cn.card;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import itisme.com.cn.card.dao.ISQLiteOperate;
import itisme.com.cn.card.dao.SQLiteOperate;
import itisme.com.cn.card.utils.StaticMethod;


public class WriteRuleAty extends Activity implements OnClickListener{
    private Spinner spinnerRules;
    private static int MAX_ITEMS_NUMBER = 28;
    private List<String> callCardsList = new ArrayList<String>();
    private List<TextView> textViews;
    private ISQLiteOperate isqLiteOperate;
    private int flag = 0;
    private EditText etWriteRule;
    private String currentText = "Pass";
    private String[] contents = new String[40];
    private String[] numbers = new String[40];
    private int spinnerPosition = 0;
    ArrayAdapter<String> adapter;
    String[] callCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_write_rule_aty);
        // setFullScreen();
        initDatabase();
        initView();
        initTextViewsContent();

    }

    /*
    初始化规则内容
     */
    private void initTextViewsContent() {
        TextView textView = textViews.get(flag);
        // textView.setText();
    }

    /*
        初始化数据库
     */
    private void initDatabase() {
        isqLiteOperate = new SQLiteOperate(getString(R.string.db_name));
    }

    /*
        初始化视图
     */
    private void initView() {
        initTextViews();
        spinnerRules = (Spinner) findViewById(R.id.spinnerRules);
        callCards = getResources().getStringArray(R.array.call_cards);
        for (String item : callCards) {
            callCardsList.add(item);
        }
        adapter = new ArrayAdapter<String>(
                WriteRuleAty.this, android.R.layout.simple_spinner_item,
                callCardsList);
        spinnerRules.setAdapter(adapter);
        spinnerRules.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spinnerRules.getSelectedItem().toString().trim();
                textViews.get(flag).setText(text);
                numbers[flag] = text;
                spinnerPosition = position;
                etWriteRule.setText(isqLiteOperate.getContent(setWhereString(), flag + 1));

                if(spinnerRules.getSelectedItemPosition()==0 || !StaticMethod.isEmpty(etWriteRule.getText().toString())){
                    etWriteRule.setEnabled(false);
                }else {
                    etWriteRule.setEnabled(true);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textViews.get(0).setText(callCardsList.get(0));
                etWriteRule.setEnabled(false);
            }
        });
        etWriteRule = (EditText) findViewById(R.id.etWriteRule);
        etWriteRule.setOnClickListener(this);
    }

    private String setWhereString() {
        String where= "";
        for(int i = 1; i<=flag; i++){
            if(StaticMethod.isGreateThanNine(i)){
                where += getString(R.string.call_)+i+" = '"  +numbers[i-1] + "' and ";
            }
            else {
                where += getString(R.string.call_)+0 + i+" = '" + numbers[i-1] + "' and ";
            }
        }
        if(flag < 9){
            where += getString(R.string.call_)+0+(flag+1)+" = '" + numbers[flag]+ "'";
        }else {
            where += getString(R.string.call_)+0+(flag+1)+" = '" +  numbers[flag] + "' ";
        }

        return where;
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

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void lastStep(View view) {
        if (flag == 0) {
            toast("不能上一步了");
            return;
        }
        textViews.get(flag).setText("");
        flag--;
        callCardsList.clear();
        for (int i = 0; i < callCards.length; i++) {
            if (flag == 0 || callCards[i].equals(numbers[flag - 1])) {
                callCardsList.add(callCards[0]);
                for (int j = i + 1; j < callCards.length; j++) {
                    callCardsList.add(callCards[j]);
                }
                break;
            }
        }
        etWriteRule.setText(contents[flag]);
        spinnerRules.setSelection(callCardsList.indexOf(numbers[flag]));
    }

    public void nextStep(View view) {

        contents[flag] = etWriteRule.getText().toString().trim();
        flag++;
        if (flag == MAX_ITEMS_NUMBER) {
            flag--;
            toast("不能继续下一步了");
        }
        etWriteRule.setText("");
        etWriteRule.setHint("请输入...");
        if (spinnerPosition != 0) {
            for (int i = 0; i < spinnerPosition; i++) {
                callCardsList.remove(1);
            }
        }
        adapter.notifyDataSetChanged();
        spinnerPosition = 0;
        spinnerRules.setSelection(0);
        textViews.get(flag).setText(spinnerRules.getSelectedItem().toString().trim());
    }

    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void clearContent(View view) {
        etWriteRule.setText("");
    }

    public void clearCurrentProcess(View view) {
        clearAll();
    }

    private void clearAll() {
        flag = 0;
        for (int i = 0; i < contents.length; i++) {
            contents[i] = "";
            numbers[i] = "";
        }
        for (TextView textview : textViews) {
            textview.setText("");
        }

        callCardsList.clear();
        for (String string : callCards) {
            callCardsList.add(string);
        }
        adapter.notifyDataSetChanged();
        spinnerRules.setSelection(0);
        textViews.get(0).setText(spinnerRules.getSelectedItem().toString().trim());
    }

    public void writeNext(View view) {
        if (!saveItems()) {
            toast("保存失败");
            return;
        }
        clearAll();
    }

    public void saveBack(View view) {
        if (!saveItems()) {
            toast("保存失败");
            return;
        }
        Intent intent = new Intent(WriteRuleAty.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean saveItems() {
        numbers[flag] = spinnerRules.getSelectedItem().toString().trim();
        contents[flag] = etWriteRule.getText().toString().trim();
        if (StaticMethod.isEmpty(configureSql())) {
            return false;
        }
        isqLiteOperate.insertItems(getString(R.string.table_name), configureSql());
        return true;
    }

    private String configureSql() {
        String sql = "";
        for (int i = 0; i < numbers.length; i++) {
            sql += ",";
            sql = sql + "'" + numbers[i] + "'";
        }
        for (int i = 0; i < contents.length; i++) {
            sql += ",";
            sql = sql + "'" + contents[i] + "'";
        }
        return sql;

    }

    public void backMain(View view) {
        Intent intent = new Intent(WriteRuleAty.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.etWriteRule:
                if(!etWriteRule.isEnabled()){
                    toast("当前内容不可修改,请转查看列表");
                }
                break;
        }

    }
}
