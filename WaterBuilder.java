// @Author Matheus Nunes

public class WaterBuilder extends Component
{
    private AList<Vector3> vertices = new AList();
    private AList<Vector3> normals = new AList();
    private AList<Vector2> uvs = new AList();
    private AList<Vector3> triangles = new AList();
    private Vertex vertex = new Vertex();
      
    public int width;
    public int height;
    public float scale = 1f;
    public float amplitude = 1.2f;
    public float frequency = 1.2f;
    public float speed = 4.0f;
    public float elapsed_time = 0f;
    
    void start()
    {
        calculate_scale();
        build(width, height, scale);
        
        MeshUtils.set_mesh_inobject(myObject, vertex, vertices, triangles, normals, uvs);
    }
    
    void calculate_scale()
    {
        width = (int) Math.round(width / scale);
        height = (int) Math.round(height / scale);
    }
    
    void repeat()
    {
        elapsed_time += Time.deltaTime();
        update(elapsed_time);
    }

    public void build(int width, int height, float scale)
    {
        this.width = width;
        this.height = height;
        this.scale = scale;

        int index = 0;

        for (int y = 0; y < height - 1; y++)
        {
            for (int x = 0; x < width - 1; x++)
            {
                float px = x * scale;
                float py = 0;
                float pz = y * scale;

                vertices.add(new Vector3(px, py, pz));
                vertices.add(new Vector3(px + scale, py, pz));
                vertices.add(new Vector3(px + scale, py, pz + scale));
                vertices.add(new Vector3(px, py, pz + scale));

                normals.add(new Vector3(0, 1, 0));
                normals.add(new Vector3(0, 1, 0));
                normals.add(new Vector3(0, 1, 0));
                normals.add(new Vector3(0, 1, 0));

                uvs.add(new Vector2(0, 0));
                uvs.add(new Vector2(1, 0));
                uvs.add(new Vector2(1, 1));
                uvs.add(new Vector2(0, 1));

                triangles.add(new Vector3(index, index + 1, index + 2));
                triangles.add(new Vector3(index + 2, index + 3, index));
                index += 4;
            }
        }
    }

    public void update(float time)
    {
        for (int i = 0; i < vertices.size(); i++)
        {
            Vector3 v = vertices.get(i);
            float wave = (float) Math.sin((v.x * frequency) + (time * speed))
                       + (float) Math.cos((v.z * frequency * 0.7f) + (time * speed * 0.9f));

            v.setY(wave * amplitude);
        }
        
        vertex.setVertices(vertices);
        vertex.apply();
    }
}
