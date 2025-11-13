// @Author Matheus N.

public class TreePrefab extends PrefabController
{
    public float height_multiplier = 20f;   
    public float[][] height_map;
    public int width;
    
    public TreePrefab(float[][] height_map, int width, ObjectFile prefab_object, SpatialObject group)
    {
        this.height_map = height_map;
        this.width = width;
        super.prefab_object = prefab_object;
        super.main_object = group;
    }
    
    void generate()
    {
        for(int x = 0; x < width; x++)
        {
            for(int z = 0; z < width; z++)
            {
                float y = height_map[z][x];
                
                if(can_spawn(y))
                {
                    add_tolist(x, y, z);
                }
            }
        }
    }
    
    boolean can_spawn(float y)
    {
        if(y < 0.3f)
            return false;
        
        return Random.range(0, 100) == 10;
    }
    
    void add_tolist(int x, float y, int z)
    {
        PrefabData data = new PrefabData();
        
        data.position = new Vector3(x, y * height_multiplier - 0.3f, z);
        data.name = "tree";
        
        super.prefab_data.add(data);
    }
}
