// @Author Matheus N.

public class PrefabController
{
    public float view_distance = 50f;
    public AList<PrefabData> prefab_data = new AList();
    public SpatialObject main_object;
    public ObjectFile prefab_object;
    
    public void update(Vector3 player_position)
    {
        for(PrefabData data : prefab_data)
        {
            float distance = player_position.distance(data.position);
            
            if(distance < view_distance && !data.is_spawned)
            {
                spawn_prefab(data);
                
                continue;
            }
            
            else if(distance > view_distance)
            {
                destroy_prefab(data);
            }
        }
    }
    
    void spawn_prefab(PrefabData data)
    {
        data.is_spawned = true;
        
        data.object = main_object.instantiateHasChild(prefab_object);
        data.object.setPosition(data.position);
    }
    
    void destroy_prefab(PrefabData data)
    {
        if(!data.is_spawned)
            return;
            
        if(data.object == null)
            return;
        
        data.is_spawned = false;
        
        if(data.object.exists())
            data.object.destroy();
        
        data.object = null;
    }
}
