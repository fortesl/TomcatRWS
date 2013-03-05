/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package luis.fortes.rs.financials;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author fortesl
 */
public interface Computations {

    /**
     * Retrieves representation of an instance of luis.fortes.rs.financials.ComputationsService
     * @param hh
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{computation}")
    String doGet();

    /**
     * Retrieves representation of an instance of luis.fortes.rs.financials.ComputationsService
     * @param formParams
     * @return an instance of java.lang.String
     */
    @POST
    @Path("{computation}")
    @Consumes({"application/x-www-form-urlencoded,multipart/form-data"})
//    @Consumes("{application/x-www-form-urlencoded,multipart/form-data}")
    String doPost(MultivaluedMap <String, String> formParams);    
}
