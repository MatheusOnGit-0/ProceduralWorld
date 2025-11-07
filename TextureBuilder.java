// @Author Matheus Silva 

public class TextureBuilder
{
    public static Texture draw_noise_map(float[][] noise_map, int width, int height)
    { 
        Texture texture = new Texture(width, width);
        Color pixel_color;
        
        for(int y = 0; y < width; y++)
        {
            for(int x = 0; x < width; x++)
            {
                
                pixel_color = Color.BLACK();
                pixel_color.lerp(1f, noise_map[x][y]);
                
                texture.setPixel(y, x, pixel_color);
            }
        }
        
        texture.apply();
        
        return texture;   
    }
    
    public static Texture draw_colour_map(float[][] noise_map, int width, int height)
    { 
        Texture texture = new Texture(width, width);
        Color pixel_color;
        
        for(int y = 0; y < width; y++)
        {
            for(int x = 0; x < width; x++)
            {
                float map_height = noise_map[x][y];
                
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
