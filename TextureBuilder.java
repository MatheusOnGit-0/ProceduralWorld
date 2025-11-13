// @Author Matheus Silva 

public class TextureBuilder
{
    public static Texture draw_noise_map(float[][] noise_map, int width)
    { 
        Texture texture = new Texture(width, width);
        Color pixel_color;
        
        for(int y = 0; y < width; y++)
        {
            for(int x = 0; x < width; x++)
            {
                float height = noise_map[y][x];
                
                int value = (int) height * 255;
                pixel_color = new Color(255, value, value, value);
                
                texture.setPixel(y, x, pixel_color);
            }
        }
        
        texture.apply();
        
        return texture;   
    }
    
    public static Texture build_tiled_texture(float[][] height_map, int width, int tile_size)
    {
        String path = Directories.getProjectFolder() + "Files/WorldGenerator/Textures/";
        
        Texture grass = Texture.loadFile(new File(path + "grass.png"));
        Texture dirt  = Texture.loadFile(new File(path + "dirt.png"));
        Texture snow = Texture.loadFile(new File(path + "snow.png"));
        Texture stone  = Texture.loadFile(new File(path + "stone.png"));
    
        int final_size = tile_size * width;
        
        Texture final_texture = new Texture(final_size, final_size);
        
        for (int y = 0; y < width; y++)
        {
            for (int x = 0; x < width; x++)
            {
                float height = height_map[y][x];
                float blend = 0f;
                
                Texture bottom_texture;
                Texture top_texture;
                
                if(height < 0.3f)
                {
                    bottom_texture = dirt;
                    top_texture = grass;
                    blend = smoothstep(0f, 0.30f, height);
                }
                
                else if(height < 0.5f)
                {
                    bottom_texture = grass;
                    top_texture = grass;
                    blend = smoothstep(0.30f, 0.50f, height);
                }
                
                else if(height < 0.7f)
                {
                    bottom_texture = grass;
                    top_texture = stone;
                    blend = smoothstep(0.50f, 0.70f, height);
                }
                
                else
                {
                    bottom_texture = stone;
                    top_texture = snow; 
                    blend = smoothstep(0.70f, 0.9f, height);
                }
                
                add_blended_tile(blend, 16, final_texture, bottom_texture, top_texture, y, x);
           }
        }

        final_texture.apply();
        return final_texture;
    }
    
    public static void add_blended_tile(float blend, int tile_size, Texture final_texture, Texture base_texture, Texture blend_texture, int map_y, int map_x)
    {
        for(int ty = 0; ty < tile_size; ty++)
        {
            for(int tx = 0; tx < tile_size; tx++)
            {
                int sx = tx % base_texture.getWidth();
                int sy = ty % base_texture.getHeight();

                Color dirt_px  = base_texture.getPixel(sx, sy);
                Color grass_px = blend_texture.getPixel(sx, sy);

                Color final_px = lerp_color(dirt_px, grass_px, blend);

                int final_x = map_x * tile_size + tx;
                int final_y = map_y * tile_size + ty;

                final_texture.setPixel(final_x, final_y, final_px);
            }
        }
    }
    
    public static void add_tile(int tile_size, Texture final_texture, Texture base_texture, int map_y, int map_x)
    {
        for(int y = 0; y < tile_size; y++)
        {
            for(int x = 0; x < tile_size; x++)
            {
                Color color = base_texture.getPixel(x, y);
                final_texture.setPixel((int) (map_x * 16) + x, (int) (map_y * 16) + y, color);
            }
        }
    }
    
    private static float smoothstep(float edge0, float edge1, float x)
    {
        float t = clamp((x - edge0) / (edge1 - edge0), 0f, 1f);
        return t * t * (3f - 2f * t);
    }
    
    private static float clamp(float v, float min, float max)
    {
        return Math.max(min, Math.min(max, v));
    }
    
    private static Color lerp_color(Color a, Color b, float t)
    {
        Color result = new Color();
        result.setFloatRed(a.getFloatRed() + (b.getFloatRed() - a.getFloatRed()) * t);
        result.setFloatGreen(a.getFloatGreen() + (b.getFloatGreen() - a.getFloatGreen()) * t);
        result.setFloatBlue(a.getFloatBlue() + (b.getFloatBlue() - a.getFloatBlue()) * t);
        result.setFloatAlpha(1f);
        
        return result;
    }

    public static Texture draw_colour_map(float[][] noise_map, int width, int height)
    { 
        Texture texture = new Texture(width, width);
        Color pixel_color;
        
        for(int y = 0; y < width; y++)
        {
            for(int x = 0; x < width; x++)
            {
                float map_height = noise_map[y][x];
                
                pixel_color = new Color(0f,0f,0.1f);
                pixel_color.lerp(1f, 0f, 0f, 0.5f, map_height);
                
                if(map_height > 0.4f)
                {
                    pixel_color = new Color(0f,0.1f,0f);
                    pixel_color.lerp(1f, 0f, 1f, 0f, map_height);
                }
                
                if(map_height > 0.4f && map_height < 0.45f)
                {
                    pixel_color = new Color(0.1f,0.1f,0f);
                    pixel_color.lerp(1f, 1f, 1f, 0f, map_height);
                }
                
                if(map_height > 0.8f)
                {
                    pixel_color = new Color(0.1f,0.1f,0.1f);
                    pixel_color.lerp(1f, 1f, 1f, 1f, map_height);
                }
                
                texture.setPixel(y, x, pixel_color);
            }
        }
        
        texture.apply();
        
        return texture;   
    }
    
}
