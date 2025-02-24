package org.example.grandao.DAO;

import org.example.grandao.Entidades.Libro;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "libros")
public class LibroXMLDAO {

    private List<Libro> books = new ArrayList<>();

    @XmlElement(name = "libro")
    public List<Libro> getBooks() {
        return books;
    }

    public void setBooks(List<Libro> books) {
        this.books = books;
    }

    // Metodo para leer libros desde el archivo XML
    public List<Libro> readBooksFromXML(String filePath) throws JAXBException {
        File file = new File(filePath);
        JAXBContext context = JAXBContext.newInstance(LibroXMLDAO.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        LibroXMLDAO libroXMLDAO = (LibroXMLDAO) unmarshaller.unmarshal(file);

        return libroXMLDAO.getBooks();
    }

    // Metodo para insertar un libro en el archivo XML
    public void insertBookInXML(Libro book, String filePath) throws JAXBException {
        List<Libro> books = readBooksFromXML(filePath);

        // Agregamos el nuevo libro
        books.add(book);

        LibroXMLDAO libroXMLDAO = new LibroXMLDAO();
        libroXMLDAO.setBooks(books);

        JAXBContext context = JAXBContext.newInstance(LibroXMLDAO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(libroXMLDAO, new File(filePath));
    }
}