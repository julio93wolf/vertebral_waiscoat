package dthwolf.vertebral_waistcoat;

import java.io.Serializable;

public class datos implements Serializable {
    private static final long serialVersionUID = 8799656478674716638L;
    private float a_X;
    private float a_Y;
    private float a_Z;

    public void m_setX(float p_X){
        a_X=p_X;
    }

    public void m_setY(float p_Y){
        a_Y=p_Y;
    }

    public void m_setZ(float p_Z){
        a_Z=p_Z;
    }

    public float m_getX(){
        return a_X;
    }

    public float m_getY(){
        return a_Y;
    }

    public float m_getZ(){
        return a_Z;
    }
}
