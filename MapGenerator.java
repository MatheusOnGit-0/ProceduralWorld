// @Author Matheus Nunes

public class MapGenerator extends Component
{
    public int map_width;
    public int map_height;
    public float noise_scale;
    public boolean auto_update;
    
    void start()
    {
        generate_map();
    }
    
    void repeat()
    {
        if(auto_update)
        {
            generate_map();
        }
    }
    
    public void generate_map()
    {
        float[][] noise_map = Noise.generate_noise_map(map_width, map_height, noise_scale);
        MapDisplay map_display = (MapDisplay) myObject.findComponentInChildren(MapDisplay.class);
        
        map_display.draw_terrain_model(noise_map, map_width, map_height);
    }
}
