package dthwolf.vertebral_waistcoat;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class principal extends AppCompatActivity implements SensorEventListener {

    private String a_IP="0.0.0.0";
    private int a_Port=0;
    private Socket a_Conexion;
    private ObjectOutputStream a_Output;
    private SensorManager a_SensorManager;
    private Sensor a_SensorAcc;
    private TextView a_lblSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        a_lblSensor = (TextView)findViewById(R.id.lbl_Sensores);
        a_SensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        a_SensorAcc = a_SensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Bundle v_Parametros = this.getIntent().getExtras();
        try{
            a_IP=v_Parametros.getString("login_ip");
            a_Port=Integer.parseInt(v_Parametros.getString("login_port"));
            m_Conexion();
        }catch(Exception e){

        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float v_X,v_Y,v_Z;
        v_X = event.values[0];
        v_Y = event.values[1];
        v_Z = event.values[2];
        a_lblSensor.setText("\n\nX="+v_X+"\nY="+v_Y+"\nZ="+v_Z);
        m_Output(v_X,v_Y,v_Z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        a_SensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        a_SensorManager.registerListener(this,a_SensorAcc,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void m_Conexion(){
        Toast ts_Mensaje;
        try{
            System.out.println("IP: "+a_IP);
            System.out.println("Puerto: "+a_Port);
            a_Conexion=new Socket(a_IP,a_Port);
            System.out.println("Conexion Realizada");
            a_Output=new ObjectOutputStream(a_Conexion.getOutputStream());
            System.out.println("Stream Realizado");
            if(a_Conexion.isConnected()){
                ts_Mensaje=Toast.makeText(getApplicationContext(), "Servidor Conectado: "+a_IP, Toast.LENGTH_SHORT);
                ts_Mensaje.show();
            }else{
                ts_Mensaje=Toast.makeText(getApplicationContext(), "Servidor no encontrado", Toast.LENGTH_SHORT);
                ts_Mensaje.show();
            }
        }catch(Exception e){
            System.out.println(e.toString());
            ts_Mensaje=Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
            ts_Mensaje.show();
        }
    }

    private void m_Output(float p_X,float p_Y,float p_Z){
        try{
            if(a_Conexion.isConnected()){
                datos v_Output=new datos();
                v_Output.m_setX(p_X);
                v_Output.m_setY(p_Y);
                v_Output.m_setZ(p_Z);
                a_Output.writeObject(v_Output);
                a_Output.flush();
            }
        }catch(Exception e){

        }

    }
}
