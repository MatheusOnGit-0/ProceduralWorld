import de.javagl.obj.Obj;
import de.javagl.obj.Objs;
import de.javagl.obj.ObjWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;

public class ObjectVertexWriter
{
    private Vertex vertex;
    private File file;
    
    /**
     * Class constructor.
     *
     * @param file_name  nome do arquivo a ser salvo.
     * @param vertex     malha do objeto a ser salvo.
     */
    public ObjectVertexWriter(File file, Vertex vertex)
    {
        this.file = file;
        this.vertex = vertex;
    }
    
    /**
     * Salva a malha do objeto no arquivo passado no construtor.
     *
     * @see Obj.Objs
     */
    public void write()
    {
        IntBuffer triangles = BuffersUtils.to_buffer(vertex.getTrianglesArray());
        FloatBuffer vertices = BuffersUtils.to_buffer(vertex.getVerticesArray());
        FloatBuffer uvs = BuffersUtils.to_buffer(vertex.getUvsArray());
        
        Obj obj = Objs.createFromIndexedTriangleData(triangles, vertices, uvs, null);
        
        try
        {
            FileWriter writer = new FileWriter(file.getPath());
            ObjWriter.write(obj, writer);
            Toast.showText(file.getPath() + " save successfully", 0);
        }
        
        catch(IOException e)
        {
            Console.log(e);
        }
    }
}
