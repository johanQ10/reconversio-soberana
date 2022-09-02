package com.jlbit.reconversionsoberana;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private ImageView image_star;
    private ImageView image_share;
    private ImageView image_arrow;
    private ImageView image_swap;

    private RelativeLayout relative_dialog_rating;

    private LinearLayout linear_soberano;
    private LinearLayout linear_fuerte;
    private LinearLayout linear_soberano_2;
    private LinearLayout linear_fuerte_2;

    private TextView txt_no_rating;
    private TextView txt_rating;

    private TextView txt_value_bolivares_soberanos;
    private TextView txt_value_bolivares_fuertes;
    private TextView txt_value_bolivares_soberanos_2;
    private TextView txt_value_bolivares_fuertes_2;

    private TextView txt_one;
    private TextView txt_two;
    private TextView txt_three;
    private TextView txt_four;
    private TextView txt_five;
    private TextView txt_six;
    private TextView txt_seven;
    private TextView txt_eight;
    private TextView txt_nine;
    private TextView txt_zero;

    private ImageView image_clean;
    private ImageView image_backspace;

    private boolean soberano;
    private String URL;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relative_dialog_rating = findViewById(R.id.relative_dialog_rating);
        linear_soberano = findViewById(R.id.linear_soberano);
        linear_fuerte = findViewById(R.id.linear_fuerte);
        linear_soberano_2 = findViewById(R.id.linear_soberano_2);
        linear_fuerte_2 = findViewById(R.id.linear_fuerte_2);
        image_star = findViewById(R.id.image_star);
        image_share = findViewById(R.id.image_share);
        image_arrow = findViewById(R.id.image_arrow);
        image_swap = findViewById(R.id.image_swap);
        txt_no_rating = findViewById(R.id.txt_no_rating);
        txt_rating = findViewById(R.id.txt_rating);
        txt_value_bolivares_soberanos = findViewById(R.id.txt_value_bolivares_soberanos);
        txt_value_bolivares_fuertes = findViewById(R.id.txt_value_bolivares_fuertes);
        txt_value_bolivares_soberanos_2 = findViewById(R.id.txt_value_bolivares_soberanos_2);
        txt_value_bolivares_fuertes_2 = findViewById(R.id.txt_value_bolivares_fuertes_2);
        txt_one = findViewById(R.id.txt_one);
        txt_two = findViewById(R.id.txt_two);
        txt_three = findViewById(R.id.txt_three);
        txt_four = findViewById(R.id.txt_four);
        txt_five = findViewById(R.id.txt_five);
        txt_six = findViewById(R.id.txt_six);
        txt_seven = findViewById(R.id.txt_seven);
        txt_eight = findViewById(R.id.txt_eight);
        txt_nine = findViewById(R.id.txt_nine);
        txt_zero = findViewById(R.id.txt_zero);
        image_clean = findViewById(R.id.image_clean);
        image_backspace = findViewById(R.id.image_backspace);

        image_star.setOnClickListener(v -> relative_dialog_rating.setVisibility(View.VISIBLE));
        image_share.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.text_share_app) + " " + URL);
            startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
        });
        txt_no_rating.setOnClickListener(v -> relative_dialog_rating.setVisibility(View.GONE));
        txt_rating.setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        });
        image_swap.setOnClickListener(v -> {
            if (soberano) {
                image_arrow.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_blue));
                soberano = false;
                linear_soberano.setVisibility(View.GONE);
                linear_fuerte.setVisibility(View.GONE);
                linear_soberano_2.setVisibility(View.VISIBLE);
                linear_fuerte_2.setVisibility(View.VISIBLE);
            } else {
                image_arrow.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_blue));
                soberano = true;
                linear_soberano.setVisibility(View.VISIBLE);
                linear_fuerte.setVisibility(View.VISIBLE);
                linear_soberano_2.setVisibility(View.GONE);
                linear_fuerte_2.setVisibility(View.GONE);
            }
        });
        image_clean.setOnClickListener(v -> {
            txt_value_bolivares_soberanos.setText("0,00 BsS.");
            txt_value_bolivares_fuertes.setText("0 BsF.");
            txt_value_bolivares_soberanos_2.setText("0,00 BsS.");
            txt_value_bolivares_fuertes_2.setText("0 BsF.");
        });
        image_backspace.setOnClickListener(v -> deleteNum());
        txt_one.setOnClickListener(v -> selectedNum("1"));
        txt_two.setOnClickListener(v -> selectedNum("2"));
        txt_three.setOnClickListener(v -> selectedNum("3"));
        txt_four.setOnClickListener(v -> selectedNum("4"));
        txt_five.setOnClickListener(v -> selectedNum("5"));
        txt_six.setOnClickListener(v -> selectedNum("6"));
        txt_seven.setOnClickListener(v -> selectedNum("7"));
        txt_eight.setOnClickListener(v -> selectedNum("8"));
        txt_nine.setOnClickListener(v -> selectedNum("9"));
        txt_zero.setOnClickListener(v -> selectedNum("0"));

        soberano = true;
        URL = "http://play.google.com/store/apps/details?id=" + getPackageName();
    }

    private void deleteNum(){
        String value_bss = txt_value_bolivares_soberanos.getText().toString();
        String value_bsf = txt_value_bolivares_fuertes.getText().toString();

        value_bss = value_bss.substring(0,value_bss.length() - 5);
        value_bsf = value_bsf.substring(0,value_bsf.length() - 5);

        value_bss = value_bss.replace(",","");
        value_bss = value_bss.replace(".","");
        value_bsf = value_bsf.replace(".","");

        String cadena_bss = "";
        String cadena_bsf = "";
        String restante = "000";

        if(soberano){
            if(value_bsf.length() >= 3) {
                restante = value_bsf.substring(value_bsf.length() - 3);
                value_bsf = value_bsf.substring(0, value_bsf.length() - 3);

                if(value_bsf.length() > 0)
                    value_bsf = value_bsf.substring(0,value_bsf.length() - 1);

                if(value_bss.length() > 3)
                    value_bss = value_bss.substring(0,value_bss.length() - 1);
                else value_bss = "0" + value_bss.substring(0, value_bss.length() - 1);

                if(value_bsf.length() > 0) value_bsf = value_bsf + restante;
                else{
                    if(restante.equals("000")) value_bsf = "0";
                    else{
                        if(restante.charAt(0) == '0' && restante.charAt(1) == '0') value_bsf = restante.substring(2);
                        else{
                            if(restante.charAt(0) == '0') value_bsf = restante.substring(1);
                            else value_bsf = restante;
                        }
                    }
                }
            }
        }else{
            if(value_bsf.length() > 6) value_bss = value_bss.substring(0,value_bss.length() - 1);
            else value_bss = "0" + value_bss.substring(0,value_bss.length() - 1);

            if(value_bsf.length() > 1) value_bsf = value_bsf.substring(0,value_bsf.length() - 1);
            else value_bsf = "0";
        }

        int n = 0;

        for (int i = value_bss.length() - 1; i >= 0; i--) {
            if (i == value_bss.length() - 3) {
                cadena_bss = "," + cadena_bss;
                n = 0;
            }
            if (n == 3) {
                cadena_bss = "." + cadena_bss;
                n = 0;
            }
            cadena_bss = value_bss.charAt(i) + cadena_bss;
            n++;
        }

        n = 0;

        for (int i = value_bsf.length() - 1; i >= 0; i--) {
            if (n == 3) {
                cadena_bsf = "." + cadena_bsf;
                n = 0;
            }
            cadena_bsf = value_bsf.charAt(i) + cadena_bsf;
            n++;
        }

        cadena_bss = cadena_bss + " BsS.";
        cadena_bsf = cadena_bsf + " BsF.";

        txt_value_bolivares_soberanos.setText(cadena_bss);
        txt_value_bolivares_fuertes.setText(cadena_bsf);
        txt_value_bolivares_soberanos_2.setText(cadena_bss);
        txt_value_bolivares_fuertes_2.setText(cadena_bsf);
    }

    private void selectedNum(String num){
        String value_bss = txt_value_bolivares_soberanos.getText().toString();
        String value_bsf = txt_value_bolivares_fuertes.getText().toString();

        String cadena_bss = "";
        String cadena_bsf = "";
        String restante = "000";

        value_bss = value_bss.substring(0,value_bss.length() - 5);
        value_bsf = value_bsf.substring(0,value_bsf.length() - 5);

        value_bss = value_bss.replace(",","");
        value_bss = value_bss.replace(".","");
        value_bsf = value_bsf.replace(".","");

        if(soberano && value_bss.equals("000")) {

            cadena_bss = "0,0" + num;

            if(value_bsf.length() == 3) restante = value_bsf;
            else{
                if(value_bsf.length() == 2) restante = "0" + value_bsf;
                else{
                    restante = "00" + value_bsf;
                }
            }
            cadena_bsf = num + "." + restante;

        }else{

            if(!soberano && value_bsf.length() < 3){

                if(value_bsf.equals("0")) cadena_bsf = num;
                else cadena_bsf = value_bsf + num;

                cadena_bss = "0,00";

            }else{

                if(soberano) {
                    restante = value_bsf.substring(value_bsf.length() - 3);
                    value_bsf = value_bsf.substring(0, value_bsf.length() - 3);

                    value_bss = value_bss + num;
                    value_bsf = value_bsf + num;
                }else{
                    value_bsf = value_bsf + num;
                    value_bss = value_bsf.substring(0,value_bsf.length() - 3);

                    if(value_bss.length() == 2) value_bss = "0" + value_bss;
                    if(value_bss.length() == 1) value_bss = "00" + value_bss;
                }

                int n = 0;

                for (int i = value_bss.length() - 1; i >= 0; i--) {
                    if (i == value_bss.length() - 3) {
                        cadena_bss = "," + cadena_bss;
                        n = 0;
                    }
                    if (n == 3) {
                        cadena_bss = "." + cadena_bss;
                        n = 0;
                    }
                    cadena_bss = value_bss.charAt(i) + cadena_bss;
                    n++;
                }

                n = 0;

                for (int i = value_bsf.length() - 1; i >= 0; i--) {
                    if (n == 3) {
                        cadena_bsf = "." + cadena_bsf;
                        n = 0;
                    }
                    cadena_bsf = value_bsf.charAt(i) + cadena_bsf;
                    n++;
                }

                if(soberano) cadena_bsf = cadena_bsf + "." + restante;
            }
        }

        if(cadena_bss.length() > 4 && cadena_bss.charAt(0) == '0')
            cadena_bss = cadena_bss.substring(1);

        cadena_bss = cadena_bss + " BsS.";
        cadena_bsf = cadena_bsf + " BsF.";

        txt_value_bolivares_soberanos.setText(cadena_bss);
        txt_value_bolivares_fuertes.setText(cadena_bsf);
        txt_value_bolivares_soberanos_2.setText(cadena_bss);
        txt_value_bolivares_fuertes_2.setText(cadena_bsf);
    }
}
