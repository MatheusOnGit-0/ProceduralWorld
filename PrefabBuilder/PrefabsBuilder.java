// @Author Matheus N.

public class PrefabsBuilder extends Component
{
    public ObjectFile tree, rock;
    public SpatialObject prefabs_group;
    private AList<PrefabController> prefabs_controller = new AList();
    
    void generate_prefabs(float[][] height_map,  int width)
    {
        TreePrefab tree_prefab = new TreePrefab(height_map, width, tree, prefabs_group);
        tree_prefab.generate();
       
        RockPrefab rock_prefab = new RockPrefab(height_map, width, rock, prefabs_group);
        rock_prefab.generate();
        
        prefabs_controller.add(tree_prefab);
        prefabs_controller.add(rock_prefab);
    }
    
    void update_all(Vector3 player_position)
    {
        for(PrefabController prefab : prefabs_controller)
        {
            prefab.update(player_position);
        }
    }
}
