// @Author Matheus Nunes

public class MapGenerator extends Component
{
    public int map_width;
    public float noise_scale;
    public int octaves = 4;
    public float persistence = 0.5f;
    public float lacunarity = 2f;
    public float island_scale = 0.8f;
    public SpatialObject grass_mesh;
    private MapDisplay map_display;
    public PrefabsBuilder prefabs;
    public boolean spawn_grass = false;
    public SpatialObject player;
    
    void start()
    {
        generate_map();
    }
    
    void repeat()
    {
        prefabs.update_all(player.getGlobalPosition());
    }
    
    public void generate_map()
    {      
        float[][] noise_map = Noise.generate_noise_map(island_scale, map_width, noise_scale, octaves, persistence, lacunarity);
        map_display = (MapDisplay) myObject.findComponentInChildren(MapDisplay.class);
        Vertex terrain_vertex = map_display.draw_terrain_model(noise_map, map_width);
        
        prefabs.generate_prefabs(noise_map, map_width);
        
        if(spawn_grass)
            map_display.draw_grass(noise_map, map_width, grass_mesh);
            
        Collider collider = myObject.findComponent(Collider.class);
        collider.setVertex(terrain_vertex);
    }
}
