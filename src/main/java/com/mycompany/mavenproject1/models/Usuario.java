package com.mycompany.mavenproject1.models;

import com.mycompany.mavenproject1.config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Usuario {
    private String email;
    private String nombre;
    private String apellido;
    private String contrasena;
    private Date fecha_nacimiento;
    private String telefono;
    private String dni;
    private String foto;
    private Boolean socio;
    
    //Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getSocio() {
        return socio;
    }

    public void setSocio(Boolean socio) {
        this.socio = socio;
    }
    
    //Metododos

    public static List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL
            String sql = "SELECT * FROM usuarios WHERE activado = 1";

            // Crear el statement
            statement = connection.createStatement();

            // Ejecutar la consulta
            resultSet = statement.executeQuery(sql);

            // Iterar sobre los resultados y crear objetos Usuario
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setContrasena(resultSet.getString("contraseña"));
                usuario.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                usuario.setTelefono(resultSet.getString("telefono"));
                usuario.setDni(resultSet.getString("dni"));
                usuario.setSocio(resultSet.getBoolean("socio"));
                usuario.setFoto(resultSet.getString("foto"));
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores: imprime el error en la consola
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de errores: imprime el error en la consola
            }
        }

        return usuarios;
    }
    
    public void insertarUsuario(){
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener usuarios
            String sql = "INSERT INTO usuarios (email, nombre, apellido, dni, socio, telefono, foto, contraseña, fecha_nacimiento) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // Establecer los valores para los parámetros
            preparedStatement.setString(1, getEmail());
            preparedStatement.setString(2, getNombre());
            preparedStatement.setString(3, getApellido());
            preparedStatement.setString(4, getDni());
            preparedStatement.setBoolean(5, getSocio());
            preparedStatement.setString(6, getTelefono());
            preparedStatement.setString(7, getFoto());
            preparedStatement.setString(8, getContrasena());
            preparedStatement.setDate(9,  getFecha_nacimiento());
            
            // Ejecutar la consulta de inserción
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null,"Usuario insertado correctamente!");
            
            
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores: imprime el error en la consola
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de errores: imprime el error en la consola
            }
        }
    }
    
    public Boolean existeUsuario(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener usuarios
            String sql = "SELECT COUNT(*) AS count FROM usuarios WHERE email = ? OR dni = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Establecer los valores para los parámetros
            preparedStatement.setString(1, getEmail());
            preparedStatement.setString(2, getDni());

            // Ejecutar la consulta de inserción
            resultSet = preparedStatement.executeQuery();
            
            // Verificar si hay al menos un registro
            if (resultSet != null && resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean esUsuario(String mail, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establece la conexión
            Conexion conexion = new Conexion();
            connection = conexion.establecerConexion();

            // Consulta SQL para verificar las credenciales
            String consultaSQL = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ? AND activado = 1";
            preparedStatement = connection.prepareStatement(consultaSQL);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, password);
            
            resultSet = preparedStatement.executeQuery();
            
            return resultSet.next();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Usuario datosUsuarioConDni(String dni) {
        Usuario user = new Usuario();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL con un PreparedStatement y parámetros
            String sql = "SELECT * FROM usuarios WHERE dni = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dni);

            // Ejecutar la consulta
            resultSet = preparedStatement.executeQuery();

            // Iterar sobre los resultados y crear objetos Usuario
            while (resultSet.next()) {
                user.setNombre(resultSet.getString("nombre"));
                user.setApellido(resultSet.getString("apellido"));
                user.setEmail(resultSet.getString("email"));
                user.setContrasena(resultSet.getString("contraseña"));
                user.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                user.setTelefono(resultSet.getString("telefono"));
                user.setDni(resultSet.getString("dni"));
                user.setSocio(resultSet.getBoolean("socio"));

                
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores: imprime el error en la consola
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de errores: imprime el error en la consola
            }
        }
        return user;
    }
    
    public void editarUsuario(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener usuarios
            String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, fecha_nacimiento = ?, telefono = ?, socio = ?, contraseña=? WHERE dni = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Establecer los valores para los parámetros
            preparedStatement.setString(1, getNombre());
            preparedStatement.setString(2, getApellido());
            preparedStatement.setString(3, getEmail());
            preparedStatement.setDate(4,  getFecha_nacimiento());
            preparedStatement.setString(5, getTelefono());
            preparedStatement.setBoolean(6, getSocio());
            preparedStatement.setString(7, getContrasena());
            preparedStatement.setString(8, getDni());
            
            // Ejecutar la consulta de inserción
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores: imprime el error en la consola
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de errores: imprime el error en la consola
            }
        }
    }
    
    public void desactivar(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL con un PreparedStatement y parámetros
            String sql = "UPDATE usuarios SET activado = 0 WHERE dni = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, getDni());

            // Ejecutar la consulta
            int resultSet = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static List<Usuario> obtenerUsuariosDesactivados() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL
            String sql = "SELECT * FROM usuarios WHERE activado = 0";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            // Iterar sobre los resultados y crear objetos Usuario
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setContrasena(resultSet.getString("contraseña"));
                usuario.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                usuario.setTelefono(resultSet.getString("telefono"));
                usuario.setDni(resultSet.getString("dni"));
                usuario.setSocio(resultSet.getBoolean("socio"));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores: imprime el error en la consola
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de errores: imprime el error en la consola
            }
        }

        return usuarios;
    }
    
    public void activarUsuario(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL con un PreparedStatement y parámetros
            String sql = "UPDATE usuarios SET activado = 1 WHERE dni = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, getDni());

            // Ejecutar la consulta
            int resultSet = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean comprobarDatosUsuario(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establece la conexión
            Conexion conexion = new Conexion();
            connection = conexion.establecerConexion();

            // Consulta SQL para verificar las credenciales
            String consultaSQL = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ? AND activado = 1";
            preparedStatement = connection.prepareStatement(consultaSQL);
            preparedStatement.setString(1, getEmail());
            preparedStatement.setString(2, getContrasena());

            resultSet = preparedStatement.executeQuery();

            // Si hay al menos una fila, las credenciales son válidas
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String obtenerNombre() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String nombre = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL con un PreparedStatement
            String sql = "SELECT nombre FROM usuarios WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, getEmail());

            // Ejecutar la consulta
            resultSet = preparedStatement.executeQuery();

            // Obtener el nombre (si existe)
            if (resultSet.next()) {
                nombre = resultSet.getString("nombre");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores: imprime el error en la consola
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de errores: imprime el error en la consola
            }
        }

        return nombre;
    }
    
    public List<Usuario> datosUsuarioLogeado() {
        List<Usuario> infoUser = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Crear la consulta SQL con un PreparedStatement y parámetros
            String sql = "SELECT * FROM usuarios WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, getEmail());
            // Ejecutar la consulta
            resultSet = preparedStatement.executeQuery();

            // Iterar sobre los resultados y crear objetos Usuario
            while (resultSet.next()) {
                this.setNombre(resultSet.getString("nombre"));
                this.setApellido(resultSet.getString("apellido"));
                this.setEmail(resultSet.getString("email"));
                this.setContrasena(resultSet.getString("contraseña"));
                this.setFecha_nacimiento(resultSet.getDate("fecha_nacimiento"));
                this.setTelefono(resultSet.getString("telefono"));
                this.setDni(resultSet.getString("dni"));
                this.setSocio(resultSet.getBoolean("socio"));
                this.setFoto(resultSet.getString("foto"));
                infoUser.add(this);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores: imprime el error en la consola
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de errores: imprime el error en la consola
            }
        }
        return infoUser;
    }
    
    public void guardarImagen() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener usuarios
            String sql = "UPDATE usuarios SET foto = ? WHERE dni = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Establecer los valores para los parámetros
            preparedStatement.setString(1, getFoto());
            preparedStatement.setString(2, getDni());

            // Ejecutar la consulta de inserción
            preparedStatement.executeUpdate();
            //JOptionPane.showMessageDialog(null,"Usuario insertado correctamente!");
            
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores: imprime el error en la consola
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de errores: imprime el error en la consola
            }
        }
    }
    
    public static Boolean existeUsuarioParaReservas(String email){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {            
            // Establecer conexión a la base de datos
            Conexion conn = new Conexion();
            connection = conn.establecerConexion();

            // Consulta SQL para obtener usuarios
            String sql = "SELECT COUNT(*) AS count FROM usuarios WHERE email = ? AND activado = 1;";
            preparedStatement = connection.prepareStatement(sql);

            // Establecer los valores para los parámetros
            preparedStatement.setString(1, email);

            // Ejecutar la consulta de inserción
            resultSet = preparedStatement.executeQuery();
            
            // Verificar si hay al menos un registro
            if (resultSet != null && resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}