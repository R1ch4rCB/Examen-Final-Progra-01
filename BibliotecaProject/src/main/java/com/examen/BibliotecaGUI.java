package com.examen;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BibliotecaGUI extends JFrame {
    private BibliotecaDAO dao;
    private JTable tablaLibros;
    
    public BibliotecaGUI() {
        dao = new BibliotecaDAO();
        configurarVentana();
        initComponentes();
        cargarDatos();
    }
    
    private void configurarVentana() {
        setTitle("Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void initComponentes() {
        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(4, 2));
        
        JTextField txtTitulo = new JTextField();
        JTextField txtAnio = new JTextField();
        JTextField txtAutor = new JTextField();
        JButton btnGuardar = new JButton("Guardar");
        
        panelForm.add(new JLabel("Título:"));
        panelForm.add(txtTitulo);
        panelForm.add(new JLabel("Año:"));
        panelForm.add(txtAnio);
        panelForm.add(new JLabel("Autor:"));
        panelForm.add(txtAutor);
        panelForm.add(new JLabel(""));
        panelForm.add(btnGuardar);
        
        // Panel de tabla
        tablaLibros = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        
        // Agregar todo al JFrame
        add(panelForm, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Acción del botón
        btnGuardar.addActionListener(e -> {
            Autor autor = new Autor();
            autor.setNombre(txtAutor.getText());
            autor.setNacionalidad("Desconocida");
            
            Libro libro = new Libro();
            libro.setTitulo(txtTitulo.getText());
            libro.setAño(Integer.parseInt(txtAnio.getText()));
            libro.setAutor(autor);
            
            dao.guardarAutor(autor);
            dao.guardarLibro(libro);
            cargarDatos();
            
            JOptionPane.showMessageDialog(this, "Libro guardado!");
        });
    }
    
    private void cargarDatos() {
        List<Libro> libros = dao.getLibros();
        String[] columnas = {"ID", "Título", "Año", "Autor"};
        Object[][] datos = new Object[libros.size()][4];
        
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            datos[i][0] = libro.getId();
            datos[i][1] = libro.getTitulo();
            datos[i][2] = libro.getAño();
            datos[i][3] = libro.getAutor().getNombre();
        }
        
        tablaLibros.setModel(new javax.swing.table.DefaultTableModel(
            datos, columnas
        ));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BibliotecaGUI().setVisible(true);
        });
    }
}