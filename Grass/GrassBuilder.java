// @Author Matheus N.

public class GrassBuilder extends MeshData
{
    public float min_height = 0.2f;
    public float max_height = 0.7f;
    public float height_multiplier = 20f;
    public float offset = 0.4f;
    
    private final GrassQuadBuilder quad_builder = new GrassQuadBuilder();

    public void build(float[][] height_map, int width, float density)
    {
        for(int y = 0; y < width; y++)
        {
            for(int x = 0; x < width; x++)
            {
                float height = height_map[y][x];
                
                if(height < min_height || height > max_height)
                    continue;

                if(Random.range(0f, 1f) < density)
                {
                    float offset_x = Random.range(-offset, offset);
                    float offset_z = Random.range(-offset, offset);

                    float world_x = x + offset_x;
                    float world_y = (height * height_multiplier) - 0.2f;
                    float world_z = y + offset_z;

                    float size = 0.6f + Random.range(0f, 0.3f);
                    float half = size / 2f;
                    float rotation = Random.range(0f, 1f) * (float)Math.PI * 2f;

                    quad_builder.add_tuft(this, new Vector3(world_x, world_y, world_z), half, size, rotation);
                }
            }
        }
    }
}
