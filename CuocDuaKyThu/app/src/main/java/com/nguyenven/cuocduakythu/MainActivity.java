package com.nguyenven.cuocduakythu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtDiem;
    CheckBox cb1,cb2,cb3,cb4;
    SeekBar sb1, sb2, sb3, sb4;
    ImageButton ibtnPlay;
    int soDiem = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        sb1.setEnabled(false);
        sb2.setEnabled(false);
        sb3.setEnabled(false);
        sb4.setEnabled(false);

        txtDiem.setText(soDiem + "");
        final CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long l) {
                int number       =5;
                Random random   = new Random();
                int one         = random.nextInt(number);
                int two         = random.nextInt(number);
                int three        = random.nextInt(number);
                int four        = random.nextInt(number);


                //kiem tra win tung cai
                if (sb1.getProgress() >= sb1.getMax())
                {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"One Win" , Toast.LENGTH_SHORT).show();
                    // kiem tra dat cuoc
                    if (cb1.isChecked())
                    {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this , "Bạn Đã Đoán Đúng", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        soDiem -=5;
                        Toast.makeText(MainActivity.this , "Bạn Đã Đoán Sai", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem +"");
                    EnableCheckBox();

                }
                if (sb2.getProgress()>= sb2.getMax())
                {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Two Win" , Toast.LENGTH_SHORT).show();
                    if (cb2.isChecked())
                    {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this , "Bạn Đã Đoán Đúng", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        soDiem -=5;
                        Toast.makeText(MainActivity.this , "Bạn Đã Đoán Sai", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem +"");
                    EnableCheckBox();

                }
                if (sb3.getProgress()>= sb3.getMax())
                {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Three Win" , Toast.LENGTH_SHORT).show();
                    if (cb3.isChecked())
                    {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this , "Bạn Đã Đoán Đúng", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        soDiem -=5;
                        Toast.makeText(MainActivity.this , "Bạn Đã Đoán Sai", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem +"");
                    EnableCheckBox();
                }
                if (sb4.getProgress()>= sb4.getMax())
                {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Four Win" , Toast.LENGTH_SHORT).show();
                    if (cb4.isChecked())
                    {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this , "Bạn Đã Đoán Đúng", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        soDiem -=5;
                        Toast.makeText(MainActivity.this , "Bạn Đã Đoán Sai", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem +"");
                    EnableCheckBox();
                }

                sb1.setProgress(sb1.getProgress()+one);
                sb2.setProgress(sb2.getProgress()+two);
                sb3.setProgress(sb3.getProgress()+three);
                sb4.setProgress(sb4.getProgress()+four);
            }

            @Override
            public void onFinish() {

            }
        };
        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb1.isChecked() || cb2.isChecked()|| cb3.isChecked()|| cb4.isChecked())
                {
                    sb1.setProgress(0); // sau khi nhan nut play thi quay ve 0
                    sb2.setProgress(0);
                    sb3.setProgress(0);
                    sb4.setProgress(0);
                    ibtnPlay.setVisibility(View.INVISIBLE); // an button khi clik invisible
                    countDownTimer.start();

                    DisableCheckBox();
                }
               else
                {
                    Toast.makeText(MainActivity.this, " Vui  Lòng Đặt Cược Trước Khi Chơi", Toast.LENGTH_LONG).show();
                }
            }
        });
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b)
                {
                    //bo check 2,3,4
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                }

            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b)
                {
                    //bo check 2
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    // bo check 3
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb4.setChecked(false);
                }
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    // bo check 4
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });
    }

    private  void EnableCheckBox ()
    {
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
        cb4.setEnabled(true);
    }
    private void DisableCheckBox()
    {
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
        cb4.setEnabled(false);
    }

    private void AnhXa()
    {
        txtDiem = (TextView) findViewById(R.id.textviewDiem);

        cb1      = (CheckBox) findViewById(R.id.checkbox1);
        cb2      = (CheckBox) findViewById(R.id.checkbox2);
        cb3      = (CheckBox) findViewById(R.id.checkbox3);
        cb4      = (CheckBox) findViewById(R.id.checkbox4);

        sb1      = (SeekBar) findViewById(R.id.seekbar1);
        sb2      = (SeekBar) findViewById(R.id.seekbar2);
        sb3      = (SeekBar) findViewById(R.id.seekbar3);
        sb4      = (SeekBar) findViewById(R.id.seekbar4);
        ibtnPlay = (ImageButton) findViewById(R.id.Buttonlay);
    }
}
