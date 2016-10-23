package cn.edu.gdmec.a07150817.m;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //计算按钮
    private Button calculattorButton;
    //体重输入框
    private EditText weightEdittext;
    //男性选择框
    private RadioButton manRadioButton;
    //女性选择框
    private RadioButton womanRadioButton;
    //显示结果
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取UI控件的实例？
        calculattorButton = (Button) findViewById(R.id.calculate);
        weightEdittext = (EditText) findViewById(R.id.weight);
        manRadioButton = (RadioButton) findViewById(R.id.man);
        womanRadioButton = (RadioButton) findViewById(R.id.woman);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }
    private void registerEvent(){
        //注册按钮事件
        calculattorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //内部类方法
                //判断是否输入体重
                //这个判断方式略有趣 获取值 转换为String去除头尾空格 如果等于“”则判断为空 感觉不是那么牢靠啊亲
                if(!weightEdittext.getText().toString().trim().equals("")){
                    //判断是否选择性别
                    if (manRadioButton.isChecked()||womanRadioButton.isChecked()){
                        Double weight = Double.parseDouble(weightEdittext.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("----------评估结果----------------\n" );
                        if(manRadioButton.isChecked()){
                            sb.append("男性标准身高:");
                            //计算
                            double result = evaluateHeight(weight,"男");
                            sb.append((int)result+"厘米");
                        }else {
                            sb.append("女性标准身高:");
                            double result = evaluateHeight(weight,"女");
                        }
                        //显示计算结果
                        resultTextView.setText(sb.toString());
                    }else {
                        showMessage("请选择性别");
                    }
                }else {
                    showMessage("请输入体重");
                }
            }
        });
    }
    private void showMessage(String message){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("温馨提示");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_NEGATIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
    private  double evaluateHeight(double weight,String sex){
    double height;
        if (sex == "男"){
            height = 170-(62-weight)/0.6;
        }else {
            height = 158-(52-weight)/0.5;
        }
        return height;
    }
    //创建选项菜单？


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }
    //菜单事件处理

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ((item.getItemId())){
            case 1 :
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
