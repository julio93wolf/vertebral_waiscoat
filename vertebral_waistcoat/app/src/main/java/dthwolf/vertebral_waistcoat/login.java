package dthwolf.vertebral_waistcoat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    EditText etxt_IP,etxt_Port;
    Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etxt_IP=(EditText) findViewById(R.id.txt_Servidor);
        etxt_Port=(EditText) findViewById(R.id.txt_Puerto);
        btn_Login=(Button) findViewById(R.id.btn_Login);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent v_Principal = new Intent(login.this,principal.class);
                Bundle v_Parametros = new Bundle();
                v_Parametros.putString("login_ip",etxt_IP.getText().toString());
                v_Parametros.putString("login_port",etxt_Port.getText().toString());
                v_Principal.putExtras(v_Parametros);
                startActivity(v_Principal);
            }
        });
    }
}
