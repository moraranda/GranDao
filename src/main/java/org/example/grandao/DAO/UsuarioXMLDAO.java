package org.example.grandao.DAO;

import org.example.grandao.Entidades.UsuarioXML;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "usuarios")
public class UsuarioXMLDAO {

    private List<UsuarioXML> usuarios = new ArrayList<>();

    @XmlElement(name = "usuario")
    public List<UsuarioXML> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioXML> usuarios) {
        this.usuarios = usuarios;
    }

    // Método para garantizar que el archivo XML existe y tiene una estructura válida
    private void ensureXMLFileExists(String rutaArchivo) throws JAXBException, IOException {
        File file = new File(rutaArchivo);
        if (!file.exists() || file.length() == 0) {
            file.createNewFile();
            guardarUsuariosEnXML(new ArrayList<>(), rutaArchivo); // Crear estructura vacía
        }
    }

    // Método para leer usuarios desde el archivo XML
    public List<UsuarioXML> leerUsuariosDesdeXML(String rutaArchivo) throws JAXBException, IOException {
        ensureXMLFileExists(rutaArchivo);

        File file = new File(rutaArchivo);
        if (file.length() == 0) {
            return new ArrayList<>(); // Si está vacío, retornamos lista vacía
        }

        JAXBContext context = JAXBContext.newInstance(UsuarioXMLDAO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        UsuarioXMLDAO usuarioXMLDAO = (UsuarioXMLDAO) unmarshaller.unmarshal(file);
        return usuarioXMLDAO.getUsuarios();
    }

    // Método para guardar usuarios en el archivo XML
    private void guardarUsuariosEnXML(List<UsuarioXML> usuarios, String rutaArchivo) throws JAXBException {
        UsuarioXMLDAO usuarioXMLDAO = new UsuarioXMLDAO();
        usuarioXMLDAO.setUsuarios(usuarios);

        JAXBContext context = JAXBContext.newInstance(UsuarioXMLDAO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(usuarioXMLDAO, new File(rutaArchivo));
    }

    // Método para obtener el próximo ID disponible
    private int obtenerProximoId(List<UsuarioXML> usuarios) {
        return usuarios.stream()
                .mapToInt(UsuarioXML::getId)  // Obtener todos los IDs
                .max()  // Obtener el mayor ID
                .orElse(0) + 1;  // Si no hay IDs, empezar desde 1
    }

    // Método para insertar un usuario en el archivo XML con ID autoincremental
    public void insertarUsuarioEnXML(UsuarioXML usuario, String rutaArchivo) throws JAXBException, IOException {
        ensureXMLFileExists(rutaArchivo);

        List<UsuarioXML> usuarios = leerUsuariosDesdeXML(rutaArchivo);

        // Asignar un nuevo ID autoincremental
        usuario.setId(obtenerProximoId(usuarios));

        usuarios.add(usuario);
        guardarUsuariosEnXML(usuarios, rutaArchivo);
    }
}
