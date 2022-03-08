package br.senai.sp.cotia.appcalc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private EditText editSalBruto, editNumDep, editPlanoSaude;
    private RadioButton simVt, simVa, simVr;
    private RadioGroup groupPlan, radVt, radVa, radVr;
    private Button calc;
    private double inss, valVa, valVt, valVr, irrf, salLiq, base, pcDesconto;
    int planoSaude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupPlan = findViewById(R.id.group_plan);
        radVt = findViewById(R.id.radioVt);
        radVa = findViewById(R.id.radioVa);
        radVr = findViewById(R.id.radioVr);
        editSalBruto = findViewById(R.id.edit_sal_bruto);
        editNumDep = findViewById(R.id.edit_num_dep);
        simVt = findViewById(R.id.sim_vt);
        simVr = findViewById(R.id.sim_vr);
        simVa = findViewById(R.id.sim_va);
        calc = findViewById(R.id.calc);

        Button calc = (Button) findViewById(R.id.calc);
        calc.setOnClickListener(view -> {
            // identifica se o campo está vazio ou não
            if (editSalBruto.getText().toString().isEmpty()) {
                editSalBruto.setError(getString(R.string.valida_salB));
                Toast.makeText(getBaseContext(), R.string.valida_salB, Toast.LENGTH_SHORT).show();
            } else if (editNumDep.getText().toString().isEmpty()) {
                editNumDep.setError(getString(R.string.valida_numDep));
                Snackbar.make(editNumDep, R.string.valida_numDep, Snackbar.LENGTH_SHORT).show();
            } else {


                double salarioBruto, novo_sal = 0;
                EditText edit_sal_bruto = (EditText) findViewById(R.id.edit_sal_bruto);
                salarioBruto = Double.parseDouble(edit_sal_bruto.getText().toString());
                RadioGroup rg = (RadioGroup) findViewById(R.id.group_plan);

                switch (groupPlan.getCheckedRadioButtonId()) {
                    case R.id.p1:
                        if (salarioBruto <= 3000)
                            planoSaude = (60);
                        else
                            planoSaude = (80);
                        break;
                    case R.id.p2:
                        if (salarioBruto <= 3000)
                            planoSaude = (80);
                        else
                            planoSaude = (110);
                        break;
                    case R.id.p3:
                        if (salarioBruto <= 3000)
                            planoSaude = (93);
                        else
                            planoSaude = (135);
                        break;
                    case R.id.p4:
                        if (salarioBruto <= 3000)
                            planoSaude = (130);
                        else
                            planoSaude = (180);
                        break;
                }

                double dependentes = Double.parseDouble(editNumDep.getText().toString());
               // double salarioBruto = Double.parseDouble(editSalBruto.getText().toString());

                // cálculo vale Transporte

                if (radVt.getCheckedRadioButtonId() == R.id.sim_vt)
                    valVt = salarioBruto * 0.06;
                else
                    valVt = 0;


                // cálculo vale Alimentação

                if (radVa.getCheckedRadioButtonId() == R.id.sim_va)
                    if (salarioBruto <= 3000)
                        valVa = 15.00;
                    else if (salarioBruto >= 3000.01 && salarioBruto <= 5000.00)
                        valVa = 25.00;
                    else
                        valVa = 35.00;

                else
                    valVa = 0;

                // cálculo vale Refeição

                if (radVr.getCheckedRadioButtonId() == R.id.sim_vr)
                    if (salarioBruto <= 3000)
                        valVr = 22 * 2.60;
                    else if (salarioBruto >= 3000.01 && salarioBruto <= 5000.00)
                        valVr = 22 * 3.65;
                    else
                        valVr = 22 * 6.50;

                // cálculo do INSS

                if (salarioBruto <= 1045)
                    inss = 0.075 * salarioBruto;
                else if (salarioBruto < 2089.6)
                    inss = (0.09 * salarioBruto) - 15.68;
                else if (salarioBruto < 3134.40)
                    inss = (0.12 * salarioBruto) - 78.38;
                else if (salarioBruto < 6101.06)
                    inss = (0.14 * salarioBruto) - 141.07;
                else
                    inss = 713.08;

                // calculo Imposto de Renda (IRRF)

                base = salarioBruto - inss - (189.59 * dependentes);
                if (irrf <= 1903.98)

                    irrf = base * 0.00 - 0.00;

                else if (base < 2826.65)

                    irrf = base * 0.075 - 142.80;

                else if (base < 3751.05)

                    irrf = base * 0.15 - 354.80;

                else if (base < 4664.68)

                    irrf = base * 0.225 - 636.13;

                else

                    irrf = base * 0.275 - 869.36;


                // cálculo do Salário Líquido

                salLiq = salarioBruto - inss - valVt - valVr - valVa - irrf - planoSaude;
                pcDesconto = (1 - salLiq / salarioBruto) * 100;

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("salario_bruto", salarioBruto);
                intent.putExtra("plano_saude", planoSaude);
                intent.putExtra("vale_transporte", valVt);
                intent.putExtra("vale_alimentacao", valVa);
                intent.putExtra("vale_refeicao", valVr);
                intent.putExtra("inss", inss);
                intent.putExtra("irrf", irrf);
                intent.putExtra("sal_liq", salLiq);
                startActivity(intent);
                finish();
            }
        });


    }


}
