package controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

public class EmpleadoAction extends org.apache.struts.action.Action
implements org.apache.struts.action.PlugIn {

    private static final String SUCCESS = "success";

   
    @Override
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ActionForward retValue = null;
        try{
            DynaActionForm formulario = (DynaActionForm) actionForm;
            String oficio = formulario.get("datosEmpleados").toString();
                	
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","javaoracle");
           String consulta="select apellido ||', ' || oficio ||', Total Salario:'|| salario  datos from emp where oficio = '"+oficio+"'";
//            String consulta="select apellido ||', ' || oficio ||', Total Salario:'|| salario ||'€' datos from emp ";

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            ArrayList result =  new ArrayList();

            while (rs.next()) {
                result.add( rs.getString("datos"));                  
            }            
            request.setAttribute("mensaje", result);
            retValue= actionMapping.findForward("correcto"); 
         } catch (SQLException ex) {
            // recuperamos acceso al JSP de origen
             retValue = actionMapping.getInputForward();
        }    
        return retValue; // redirigimos a la presentacion
    }
    
   @Override
    public void destroy() 
    {

    }

   @Override

   
    public void init(org.apache.struts.action.ActionServlet actionServlet, 
         org.apache.struts.config.ModuleConfig moduleConfig) throws 
           javax.servlet.ServletException 
    {  
        try{   
        Hashtable vdepart = new Hashtable();
        
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","javaoracle");
    
        String consulta;
        consulta = "select distinct(oficio) nom_oficio from emp";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        
        int i=0;
        while (rs.next())
        {                
            vdepart.put(rs.getString(1), rs.getString(1));
            i++;
        }
        
        // ponemos la tabla en el ambito de la aplicación
        actionServlet.getServletContext().setAttribute("tablaOficios",vdepart);  
      } catch (SQLException ex) {
            System.out.println("Excepcion " + ex.toString());
        }       
        
    }
}    

