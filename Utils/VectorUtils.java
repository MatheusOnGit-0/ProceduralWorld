// @Author Matheus N.

public class VectorUtils
{
    public static void add_local(Vector3 vector, float x, float y, float z)
    {
        vector.setX(vector.getX() + x);
        vector.setY(vector.getY() + y);
        vector.setZ(vector.getZ() + z);
    }
    
    public static void add_local(Vector3 vector, Vector3 other)
    {
        vector.setX(vector.getX() + other.x);
        vector.setY(vector.getY() + other.y);
        vector.setZ(vector.getZ() + other.z);
    }
}
