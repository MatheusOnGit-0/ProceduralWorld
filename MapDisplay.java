// @Author Matheus Silva

public class MapDisplay extends Component
{
    public Material material;
    public String world_name;
    
    public void draw(float[][] noise_map, int width, int height)
    {
        ModelRenderer model_renderer = myObject.findComponent(ModelRenderer.class);
        material = model_renderer.getMaterial();
        
        Texture texture = TextureBuilder.build_tiled_texture(noise_map, width, 16);
        
        material.setAlbedo(texture);
        myObject.setScale(width, 1f, height);
    }
    
    
    public void draw_grass(float[][] noise_map, int width, SpatialObject mesh_render)
    {
        ModelRenderer model_renderer = mesh_render.findComponent(ModelRenderer.class);
        Vertex vertex = new Vertex();
        
        GrassBuilder mesh = new GrassBuilder();
        mesh.build(noise_map, width, 0.1f);
        vertex.setVertices(mesh.vertices);
        vertex.setTriangles(mesh.triangles);
        vertex.setUVs(mesh.uvs);
        vertex.setNormals(mesh.normals);
        
        model_renderer.setVertex(vertex);
    }
    
    public Vertex draw_terrain_model(float[][] noise_map, int width)
    {
        ModelRenderer model_renderer = myObject.findComponent(ModelRenderer.class);
        material = model_renderer.getMaterial();
        Vertex vertex = new Vertex();
        
        Texture texture = TextureBuilder.build_tiled_texture(noise_map, width, 16);
        MeshGenerator mesh = new MeshGenerator();
        mesh.generate(noise_map, width);
        vertex.setVertices(mesh.vertices);
        vertex.setTriangles(mesh.triangles);
        vertex.setUVs(mesh.uvs);
        vertex.setNormals(mesh.normals);
        
        model_renderer.setVertex(vertex);
        
        return vertex;
    }
}
