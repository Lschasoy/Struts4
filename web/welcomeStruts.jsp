<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="welcome.title"/></title>
        <html:base/>
    </head>
    <body style="background-color: white">
        <div class="contenedor">
            <h3><bean:message key="welcome.heading"/></h3>    
            <table class="estilotabla">
            <tr> 
                <td class="estilocelda"><bean:message key="literal.seleccion"/></td>
                <td  class="estilocelda" align="center">
                    <html:form action="/Empl">
                        <html:select property="datosEmpleados" onchange="submit()"> 
                            <html:options collection="tablaOficios" 
                                 property="key" labelProperty="value"  />
                         </html:select>         
                    </html:form>        
                </td>
            </tr>
            <tr id="listaEmpleados">
              <td class="estilocelda2" colspan="2">
                  <logic:present name="mensaje">
                     <logic:iterate name="mensaje" id="correcto">
                         <li>
                             <bean:write name="correcto" />
                          </li>
                      </logic:iterate>	
                   </logic:present>
              </td>
            </tr>
           </table>       
        </div>                                 
    </body>
</html:html>
