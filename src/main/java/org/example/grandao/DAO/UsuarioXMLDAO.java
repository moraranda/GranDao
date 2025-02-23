package org.example.grandao.DAO;

import org.example.grandao.Entidades.Usuario;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "usuarios")
public class UsuarioXMLDAO {

    private List<Usuario> usuarios = new ArrayList<>();

    @XmlElement(name = "usuario")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    // Metodo para leer usuarios desde el archivo XML
    public List<Usuario> leerUsuariosDesdeXML(String rutaArchivo) throws JAXBException {
        File file = new File(rutaArchivo);
        JAXBContext context = JAXBContext.newInstance(UsuarioXMLDAO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        UsuarioXMLDAO usuarioXMLDAO = (UsuarioXMLDAO) unmarshaller.unmarshal(file);
        return usuarioXMLDAO.getUsuarios();
    }

    // Metodo para insertar un usuario en el archivo XML
    public void insertarUsuarioEnXML(Usuario usuario, String rutaArchivo) throws JAXBException {

        List<Usuario> usuarios = leerUsuariosDesdeXML(rutaArchivo);

        // Agregar el nuevo usuario
        usuarios.add(usuario);


        UsuarioXMLDAO usuarioXMLDAO = new UsuarioXMLDAO();
        usuarioXMLDAO.setUsuarios(usuarios);

        JAXBContext context = JAXBContext.newInstance(UsuarioXMLDAO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(usuarioXMLDAO, new File(rutaArchivo));
    }
}