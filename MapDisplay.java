// @Author Matheus Silva

public class MapDisplay extends Component
{
    public Material material;
    
    public void draw(float[][] noise_map, int width, int height)
    {
        ModelRenderer model_renderer = myObject.findComponent(ModelRenderer.class);
        material = model_renderer.getMaterial();
        
        Texture texture = TextureBuilder.draw_colour_map(noise_map, width, height);
        
        material.setAlbedo(texture);
        myObject.setScale(width, 1f, height);
    }
    
    public void draw_terrain_model(float[][] noise_map, int width, int height)
    {
        ModelRenderer model_renderer = myObject.findComponent(ModelRenderer.class);
        material = model_renderer.getMaterial();
        Vertex vertex = model_renderer.getVertex();
        
        Texture texture = TextureBuilder.draw_colour_map(noise_map, width, height);
        MeshGenerator mesh = new MeshGenerator();
        mesh.generate(noise_map, width);
        vertex.setVertices(mesh.vertices);
        vertex.setTriangles(mesh.triangles);
        vertex.setUVs(mesh.uvs);
        
        material.setAlbedo(texture);
    }
}
