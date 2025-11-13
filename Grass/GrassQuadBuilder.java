// @Author Matheus N.

public class GrassQuadBuilder
{

    public void add_tuft(MeshData mesh, Vector3 coord, float half, float height, float rotation)
    {
        add_quad(mesh, coord, half, height, rotation);
        add_quad(mesh, coord, half, height, rotation + (float)Math.PI * 30f);
    }

    private void add_quad(MeshData mesh, Vector3 coord, float half, float height, float rotation)
    {
        float sin = (float)Math.sin(rotation);
        float cos = (float)Math.cos(rotation);

        add_vertice(mesh, new Vector3(-half * cos, 0, -half * sin), coord);
        add_vertice(mesh, new Vector3(half * cos, 0, half * sin), coord);
        add_vertice(mesh, new Vector3(half * cos, height, half * sin), coord);
        add_vertice(mesh, new Vector3(-half * cos, height, -half * sin), coord);

        int base_index = mesh.vertex_count() - 4;

        for (int i = 0; i < 4; i++)
            mesh.normals.add(new Vector3(0, 1, 0));

        mesh.uvs.add(new Vector2(0, 0));
        mesh.uvs.add(new Vector2(1, 0));
        mesh.uvs.add(new Vector2(1, 1));
        mesh.uvs.add(new Vector2(0, 1));

        mesh.triangles.add(new Vector3(base_index, base_index + 1, base_index + 2));
        mesh.triangles.add(new Vector3(base_index + 2, base_index + 3, base_index));
    }
    
    void add_vertice(MeshData mesh, Vector3 vertice, Vector3 coord)
    {
        VectorUtils.add_local(vertice, coord);
        mesh.vertices.add(vertice);
    }
}
