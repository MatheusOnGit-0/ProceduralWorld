// @Author Matheus Nunes

public class Noise
{
    
    public static float[][] generate_noise_map(int map_width, int map_height, float scale)
    {
        float[][] noise_map = new float[map_width][map_height];
        PerlinNoise noise = new PerlinNoise();
        
        for(int y = 0; y < map_height; y++)
        {
            for(int x = 0; x < map_width; x++)
            {
                float sample_x = x / scale;
                float sample_y = y / scale;
                
                float perlin_value = noise.noise(sample_x,  sample_y);
                noise_map[x][y] = (perlin_value + 1f) / 2f;
            }
        }
        
        return noise_map;
    }
    
}
