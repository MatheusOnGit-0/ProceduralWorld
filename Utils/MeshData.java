// @Author Matheus N.

public class MeshData
{
    public final AList<Vector3> vertices = new AList();
    public final AList<Vector3> normals = new AList();
    public final AList<Vector2> uvs = new AList();
    public final AList<Vector3> triangles = new AList();

    public int vertex_count()
    {
        return vertices.size();
    }
}