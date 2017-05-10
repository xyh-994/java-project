package com.v.administrator.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.math.*;
import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //定义按钮,让UI的按钮绑定到后台的按钮,实现单击事件
    private TextView tv_show;
    private StringBuffer sum=new StringBuffer();
    private StringBuffer showContent=new StringBuffer();
    private LinkedList<String> ls=new LinkedList<>();
    private boolean flagPoint=false;
    private boolean flagSymbol=false;
    private boolean flagequal=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn_1;
        Button btn_2;
        Button btn_3;
        Button btn_4;
        Button btn_5;
        Button btn_6;
        Button btn_7;
        Button btn_8;
        Button btn_9;
        Button btn_0;
        Button btn_equal;
        Button btn_clear;
        Button btn_del;
        Button btn_except;
        Button btn_ride;
        Button btn_reduce;
        Button btn_add;
        Button btn_sign;
        Button btn_point;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化按钮
        btn_0=(Button) findViewById(R.id.btn_0);
        btn_1=(Button) findViewById(R.id.btn_1);
        btn_2=(Button) findViewById(R.id.btn_2);
        btn_3=(Button) findViewById(R.id.btn_3);
        btn_4=(Button) findViewById(R.id.btn_4);
        btn_5=(Button) findViewById(R.id.btn_5);
        btn_6=(Button) findViewById(R.id.btn_6);
        btn_7=(Button) findViewById(R.id.btn_7);
        btn_8=(Button) findViewById(R.id.btn_8);
        btn_9=(Button) findViewById(R.id.btn_9);
        btn_clear=(Button) findViewById(R.id.btn_clear);
        btn_add=(Button) findViewById(R.id.btn_add);
        btn_reduce=(Button) findViewById(R.id.btn_reduce);
        btn_ride=(Button) findViewById(R.id.btn_ride);
        btn_except=(Button) findViewById(R.id.btn_except);
        btn_sign=(Button) findViewById(R.id.btn_sign);
        btn_equal=(Button) findViewById(R.id.btn_equal);
        btn_point=(Button) findViewById(R.id.btn_point);
        btn_del=(Button) findViewById(R.id.btn_del);
        tv_show=(TextView) findViewById(R.id.tv_show);
        //注册按钮点击事件(点击按钮之后会触发onclick事件)
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_reduce.setOnClickListener(this);
        btn_ride.setOnClickListener(this);
        btn_except.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_sign.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_del.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String temp;
        temp=((Button) view).getText().toString();
        switch (view.getId()) {
            case R.id.btn_0:
                clear();
                if(!(sum.toString().equals("0"))){
                    flagSymbol=flagPoint=false;
                    sum.append(temp);
                    showContent.append(temp);
                    tv_show.setText(showContent.toString());
                }
                break;
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                clear();
                if(sum.toString().equals("0")){
                    sum=new StringBuffer();
                    showContent.deleteCharAt(showContent.lastIndexOf("0"));
                }
                flagSymbol=flagPoint=false;
                sum.append(temp);
                showContent.append(temp);
                tv_show.setText(showContent.toString());
                break;
            case R.id.btn_add:
            case R.id.btn_reduce:
            case R.id.btn_ride:
            case R.id.btn_except:
                flagSymbol=true;
                if(!(new String(showContent).equals(""))&&showContent.lastIndexOf(".")!=showContent.length()-1){
                    if(judgeLastSymbol()){
                        if(new String(sum).equals("")){
                            showContent.deleteCharAt(showContent.length()-1);
                            ls.removeLast();
                            ls.addLast(temp);
                        }
                    }else{
                        if(flagequal){
                            flagequal=false;
                            ls.addLast(temp);
                            sum=new StringBuffer();
                        }
                        else{
                            ls.addLast(sum.toString());
                            ls.addLast(temp);
                            sum=new StringBuffer();
                        }
                    }
                    flagPoint=false;
                    showContent.append(temp);
                    tv_show.setText(showContent);
                }
                break;
            case R.id.btn_point:
                clear();
                if(!(new String(showContent).equals(""))&&!(new String(sum).equals(""))&&!flagPoint&&sum.lastIndexOf(".")<0){
                    flagPoint=true;
                    flagSymbol=false;
                    showContent.append(temp);
                    sum.append(temp);
                    tv_show.setText(showContent);
                }
                break;
            case R.id.btn_clear:
                if(!(new String(sum).equals(""))){
                    sum=new StringBuffer();
                }
                if(!(new String(showContent).equals(""))){
                    showContent=new StringBuffer();
                }
                if(ls.size()>0){
                    ls.clear();
                }
                tv_show.setText("0");
                flagSymbol=flagPoint=false;
                break;
            case R.id.btn_equal:
                if(ls.size()>0&&!flagequal) {
                    flagequal=true;
                    if (new String(sum).equals("")) {
                        ls.removeLast();
                    } else {
                        if(sum.lastIndexOf(".")==sum.length()-1){
                            sum.append("0");
                        }
                        ls.addLast(sum.toString());
                    }
                    if(getResult()){
                        if(ls.size()>0){
                            showContent=new StringBuffer(ls.getLast());
                        }else{
                            showContent.deleteCharAt(showContent.length()-1);
                        }
                        tv_show.setText(showContent);
                    }else{
                        tv_show.setText("不能除以0");
                        clear();
                    }
                }
                break;
            case R.id.btn_del:
                if(!(new String(showContent).equals(""))){
                    showContent.deleteCharAt(showContent.length()-1);
                    if(judgeLastSymbol()){
                        if(ls.size()>0) {
                            ls.removeLast();
                        }
                        sum.deleteCharAt(sum.length()-1);
                    }else{
                        if(flagSymbol){
                            ls.removeLast();
                            flagSymbol=false;
                        }
                        if((new String(sum).equals(""))){
                            sum=new StringBuffer(ls.getLast());
                            ls.removeLast();
                        }else{
                            sum.deleteCharAt(sum.length()-1);
                        }
                     }
                }
                tv_show.setText(showContent);
                break;
            case R.id.btn_sign:
                if(flagequal){
                    BigDecimal b3=new BigDecimal(showContent.toString());
                    BigDecimal b4=new BigDecimal("0.01");
                    Double temps;
                    temps=b3.multiply(b4).doubleValue();
                    sum=new StringBuffer(temps.toString());
                    showContent=new StringBuffer(temps.toString());
                    tv_show.setText(showContent);
                    flagequal=false;
                }
                else if(!(new String(sum).equals(""))&&!flagPoint&&!judgeLastSymbol()){
                    showContent.delete(showContent.lastIndexOf(sum.toString()),showContent.length());
                    BigDecimal b1=new BigDecimal(sum.toString());
                    BigDecimal b2=new BigDecimal("0.01");
                    //调用math类的bigDecimal 来计算防止某些值太大而精度不够
                    sum=new StringBuffer(String.valueOf(b1.multiply(b2).doubleValue()));
                    showContent.append(sum);
                    tv_show.setText(showContent);
                }
                break;
        }
    }
    private  boolean judgeLastSymbol(){
        return (showContent.lastIndexOf("+")==showContent.length()-1||showContent.lastIndexOf("-")==showContent.length()-1||showContent.lastIndexOf("×")==showContent.length()-1||showContent.lastIndexOf("÷")==showContent.length()-1);
    }
    private  boolean getResult_(){
        BigDecimal temp;
        int i=0,length=ls.size();
        while(i<length){
            if(ls.get(i).equals("×")){
                BigDecimal b1=new BigDecimal(ls.get(i-1));
                BigDecimal b2=new BigDecimal(ls.get(i+1));
                temp=b1.multiply(b2);
                ls.remove(i-1);
                ls.remove(i-1);
                ls.remove(i-1);
                ls.add(i-1,String.valueOf(temp));
                length-=2;
            }
            else if(ls.get(i).equals("÷")){
                if(Double.parseDouble(ls.get(i+1))==Double.parseDouble("0.00")){
                    return false;
                }else {
                    BigDecimal b1 = new BigDecimal(ls.get(i - 1));
                    BigDecimal b2 = new BigDecimal(ls.get(i + 1));
                    temp = b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP);
                    ls.remove(i - 1);
                    ls.remove(i - 1);
                    ls.remove(i - 1);
                    ls.add(i - 1, String.valueOf(temp));
                    length -= 2;
                }
            }
            else{
                i++;
            }

        }
        return true;
    }
    private  boolean getResult(){
        if(!getResult_()){
            return false;
        }else{
            BigDecimal temp;
            int i=0,length=ls.size();
            while(i<length){
                if(ls.get(i).equals("+")){
                    BigDecimal b1=new BigDecimal(ls.get(i-1));
                    BigDecimal b2=new BigDecimal(ls.get(i+1));
                    temp=b1.add(b2);
                    ls.remove(i-1);
                    ls.remove(i-1);
                    ls.remove(i-1);
                    ls.add(i-1,String.valueOf(temp));
                    length-=2;
                }
                else if(ls.get(i).equals("-")){
                    BigDecimal b1=new BigDecimal(ls.get(i-1));
                    BigDecimal b2=new BigDecimal(ls.get(i+1));
                    temp=b1.subtract(b2);
                    ls.remove(i-1);
                    ls.remove(i-1);
                    ls.remove(i-1);
                    ls.add(i-1,String.valueOf(temp));
                    length-=2;
                }
                else{
                    i++;
                }
            }
            return true;
        }
    }
    private void clear(){
        if(flagequal){
            sum=new StringBuffer();
            showContent=new StringBuffer();
            if(ls.size()>0)
                ls.clear();
            flagequal=flagSymbol=flagequal=flagPoint=false;
        }
    }
}
