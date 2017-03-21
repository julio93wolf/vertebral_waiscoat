package dthwolf.vertebral_waistcoat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class principal extends AppCompatActivity {

    private String a_IP="0.0.0.0";
    private int a_Port=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Bundle v_Parametros = this.getIntent().getExtras();
        try{
            a_IP=v_Parametros.getString("login_ip");
            a_Port=Integer.parseInt(v_Parametros.getString("login_port"));
            m_Conexion();
        }catch(Exception e){

        }
    }

    private void m_Conexion(){

    }
}
