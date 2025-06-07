package com.examen;

import javax.persistence.*;
import java.util.List;

public class BibliotecaDAO {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public BibliotecaDAO() {
        emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        em = emf.createEntityManager();
    }
    
    // Guardar autor
    public void guardarAutor(Autor autor) {
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
    }
    
    // Guardar libro
    public void guardarLibro(Libro libro) {
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }
    
    // Listar libros
    public List<Libro> getLibros() {
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }
    
    // Cerrar conexiones
    public void close() {
        em.close();
        emf.close();
    }
}