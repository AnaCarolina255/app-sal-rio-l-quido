package br.senai.sp.cotia.appcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView salarioB, planSaude, valA, valT, valR, textIrrf, textInss, salLiq;
    private double salarioBruto = 0;
    private int planoSaude = 0;
    private double valVt = 0;
    private double valVa = 0;
    private double valVr = 0;
    private double inss = 0;
    private double irrf = 0;
    private double salarioLiquido = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        salarioB = findViewById(R.id.salB);
        planSaude = findViewById(R.id.plan);
        valA = findViewById(R.id.valeA);
        valT = findViewById(R.id.valeT);
        valR = findViewById(R.id.valeR);
        textIrrf = findViewById(R.id.text_irrf);
        textInss = findViewById(R.id.text_inss);
        salLiq = findViewById(R.id.sal_liq);

            salarioBruto = getIntent().getDoubleExtra("salario_bruto", 0);

            planoSaude = getIntent().getIntExtra("plano_saude", 0);

            valVt = getIntent().getDoubleExtra("vale_transporte",0);

            valVa = getIntent().getDoubleExtra("vale_alimentacao",0);

            valVr = getIntent().getDoubleExtra("vale_refeicao",0);

            inss = getIntent().getDoubleExtra("inss", 0);

            irrf = getIntent().getDoubleExtra("irrf", 0);

            salarioLiquido = getIntent().getDoubleExtra("sal_liq", 0);


        salarioB.setText(salarioBruto+"");
        planSaude.setText(planoSaude+"");
        valA.setText(valVa+"");
        valT.setText(valVt+"");
        valR.setText(valVr+"");
        textIrrf.setText(irrf+"");
        textInss.setText(inss+"");
        salLiq.setText(salarioLiquido+"");
    }
}