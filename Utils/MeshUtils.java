// @Author Matheus N.

public class MeshUtils
{
    public static void set_mesh_inobject(SpatialObject object, Vertex vertex, AList<Vector3> vertices, AList<Vector3> triangles, AList<Vector3> normals, AList<Vector2> uvs)
    {
        ModelRenderer model_renderer = object.findComponent(ModelRenderer.class);
        
        vertex.setVertices(vertices);
        vertex.setTriangles(triangles);
        vertex.setUVs(uvs);
        vertex.setNormals(normals);
        
        model_renderer.setVertex(vertex);
    }
}
