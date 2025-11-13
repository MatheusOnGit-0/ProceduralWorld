// @Author Matheus Nunes

public class Noise
{
    public static float[][] generate_noise_map(float island_scale, int map_width, float scale, int octaves, float persistence, float lacunarity)
    {
        float[][] noise_map = new float[map_width][map_width];
        PerlinNoise noise = new PerlinNoise();

        if(scale <= 0f) scale = 0.0001f;

        for(int y = 0; y < map_width; y++)
        {
            for(int x = 0; x < map_width; x++)
            {
                float amplitude = 1f;
                float frequency = 1f;
                float noise_height = 0f;

                for(int i = 0; i < octaves; i++)
                {
                    float sample_x = (x / scale) * frequency;
                    float sample_y = (y / scale) * frequency;

                    float perlin_value = noise.noise(sample_x, sample_y);
                    noise_height += perlin_value * amplitude;

                    amplitude *= persistence; 
                    frequency *= lacunarity;
                }
                
                float value = (noise_height + 1f) / 2f;

                float falloff = calculate_falloff(x, y, map_width, map_width, island_scale);
                value -= falloff;

                if (value < 0f) value = 0f;
                if (value > 1f) value = 1f;
                
                noise_map[x][y] = value;
            }
        }

        return noise_map;
    }
    
    private static float calculate_falloff(int x, int y, int width, int height, float island_scale)
    {
        float nx = (float)x / (float)width * 2f - 1f;
        float ny = (float)y / (float)height * 2f - 1f;

        float distance = Math.max(Math.abs(nx), Math.abs(ny));

        float falloff = smoothstep(island_scale, 1f, distance);
        return falloff;
    }

    private static float smoothstep(float edge0, float edge1, float x)
    {
        x = clamp((x - edge0) / (edge1 - edge0), 0f, 1f);
        return x * x * (3f - 2f * x);
    }

    private static float clamp(float v, float min, float max)
    {
        return Math.max(min, Math.min(max, v));
    }
    
}
