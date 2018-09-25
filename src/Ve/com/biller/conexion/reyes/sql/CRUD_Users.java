package Ve.com.biller.conexion.reyes.sql;

import Ve.com.biller.estructuras.reyes.User;
import Ve.com.biller.vistas.reyes.DialogLoginChangePassword;
import Ve.com.biller.vistas.reyes.DialogLoginCreateUser;
import Ve.com.biller.vistas.reyes.LoginPanel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Reyes
 */
public class CRUD_Users {
    
    public static User loginUser(String name,char[] password){   
        User user=null;
        PreparedStatement preparedStatement;    
        ResultSet resultSet;
        String sPassword="";                
        for (int i = 0; i < password.length; i++) {
            sPassword+=password[i];
        }              
        try {
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.SELECT_LOGIN);
//se verifica la contraseña y usuario
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, sPassword);            
            resultSet = preparedStatement.executeQuery();            
            if (resultSet.next()) {
                user= new User( name,
                                sPassword, 
                                resultSet.getInt(DatosSQL.CAMPO_USER_ID),
                                resultSet.getInt(DatosSQL.CAMPO_USER_ADMIN)==1);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Users.class.getName()).log(Level.SEVERE, null, ex);
        }                
        return user;
    }
    
    public static ArrayList<User> selectAllUsers(){
        ArrayList<User> arrayUser= new ArrayList<>(5);
        PreparedStatement preparedStatement;  
        ResultSet resultSet;
        User userAux;        
        try {
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.SELECT_ALL_USERS);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                userAux= new User(  resultSet.getString(DatosSQL.CAMPO_NOMBRE_USUARIO),
                                    resultSet.getString(DatosSQL.CAMPO_PASSWORD),
                                    resultSet.getInt(DatosSQL.CAMPO_USER_ID),
                                    resultSet.getInt(DatosSQL.CAMPO_USER_ADMIN)==1);
                                    //si es igual a 1 es admin
                arrayUser.add(userAux);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Users.class.getName()).log(Level.SEVERE, null, ex);
        }                
        return arrayUser;
    }
    
   
    public static void changeUserPassword(DialogLoginChangePassword dialog,User user,char[] passWord){
        PreparedStatement preparedStatement;  
        
        String stringPass="";
        for (int i = 0; i < passWord.length; i++) {
            stringPass+=passWord[i];
        }            
        try {
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.UPDATE_USER_PASSWORD);
            preparedStatement.setString(1, stringPass);
            preparedStatement.setInt(2, user.getId());
            
            preparedStatement.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Contraseña cambiada con exito.","Información", JOptionPane.INFORMATION_MESSAGE);
            dialog.close();
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Users.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static boolean deleteUser(User user){
        
        if (user.getId()==LoginPanel.getLoggedUser().getId()) {
            JOptionPane.showMessageDialog(null, "No puedes borrar tu propio usuario","Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            PreparedStatement preparedStatement;  
            
            try {
                preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.DELETE_USER);
                preparedStatement.setInt(1, user.getId());
                
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(CRUD_Users.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            
        }
    }
    
    //create a new user
    public static boolean insertNewUser(String name,String password,boolean isAdmin
            ,DialogLoginCreateUser dialog ){
        PreparedStatement preparedStatement;  
        ResultSet resultSet;
        
        try {
            //se checkea primero si ya existe el usuario en la base de datos
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.SELECT_USER_BY_NAME);
            preparedStatement.setString(1, name);
            resultSet= preparedStatement.executeQuery();
            
            if (!resultSet.next()) {   //si el usuario no existe
                preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.INSERT_NEW_USER);
                preparedStatement.setString(1, name);//nombre de ususario 
                preparedStatement.setString(2, password);//contraseña usuario
                
                if (isAdmin) {preparedStatement.setInt(3, 1);  //es admin                
                }else{preparedStatement.setInt(3, 0);}//no es admin
                
                preparedStatement.executeUpdate();
                
                dialog.close();
                
                return true;
            }else{// el usuario ya existe
                JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre", "Error", 
                                JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Users.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
}
