// @Author Matheus Nunes

public class MeshGenerator
{
    
    public AList<Vector3> vertices = new AList();
    public AList<Vector3> triangles = new AList();
    public AList<Vector2> uvs = new AList();
    
    public void generate(float[][] height_map, int width)
    {
        int vertex_index = 0;
        
        for(int y = 0; y < width; y++)
        {
            for(int x = 0; x < width; x++)
            {
                float final_height = height_map[y][x];
                
                if(final_height < 0.4f) final_height = 0.4f;
                
                vertices.add(new Vector3(x, final_height * 15f, y));
                uvs.add(new Vector2((float)x / (width - 1), 1f - (float)y / (width - 1)));
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
    }
}
