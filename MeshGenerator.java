// @Author Matheus Nunes

public class MeshGenerator
{
    public AList<Vector3> vertices = new AList();
    public AList<Vector3> triangles = new AList();
    public AList<Vector2> uvs = new AList();
    public AList<Vector3> normals = new AList();

    public void generate(float[][] height_map, int width)
    {
        int vertex_index = 0;

        for(int y = 0; y < width; y++)
        {
            for(int x = 0; x < width; x++)
            {
                float final_height = height_map[y][x];

                //if(final_height < 0.2f) final_height = 0.2f;

                vertices.add(new Vector3(x, final_height * 20f, y));
                float uv_scale = width; // controla quantas vezes a textura se repete
                uvs.add(new Vector2((float)x / uv_scale, (float)y / uv_scale));
               // uvs.add(new Vector2((float)x / (width - 1), 1f - (float)y / (width - 1)));
                normals.add(new Vector3(0, 1, 0)); // inicializa normal
            }
        }

        for (int x = 0; x < width - 1; x++)
        {
            for (int z = 0; z < width - 1; z++)
            {
                int t_left = x * width + z;
                int b_left = (x + 1) * width + z;
                int t_right = x * width + (z + 1);
                int b_right = (x + 1) * width + (z + 1);

                triangles.add(new Vector3(t_left, b_left, t_right));  
                triangles.add(new Vector3(t_right, b_left, b_right));  
            }
        }

        // calcula normais por face e soma nos vértices
        for (int i = 0; i < triangles.size(); i++)
        {
            Vector3 tri = triangles.get(i);

            Vector3 v0 = vertices.get((int)tri.x);
            Vector3 v1 = vertices.get((int)tri.y);
            Vector3 v2 = vertices.get((int)tri.z);

            Vector3 edge1 = v1.copy().sub(v0);
            Vector3 edge2 = v2.copy().sub(v0);
            Vector3 normal = edge1.cross(edge2).normalize();

            normals.get((int)tri.x).add(normal);
            normals.get((int)tri.y).add(normal);
            normals.get((int)tri.z).add(normal);
        }

        // normaliza as normais médias
        for (int i = 0; i < normals.size(); i++)
        {
            normals.get(i).normalizeLocal();
        }
    }
}
