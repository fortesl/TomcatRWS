/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package luis.fortes.rs.financials;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;
import luis.fortes.rs.financials.utils.Result;

/**
 * REST Web Service
 *
 * @author fortesl
 */
public class ComputationsService implements Computations {
    
    private static final Logger LOG = Logger.getLogger(ComputationsService.class.getName());
    MultivaluedMap<String, String> pathParams;
    MultivaluedMap<String, String> inputParams;

    /**
     * Creates a new instance of ComputationsService
     */
    public ComputationsService(MultivaluedMap<String, String> pathParams, MultivaluedMap<String, String> inputParams) {        
        this.pathParams = pathParams;
        this.inputParams =  inputParams;
    }

    @Override
    public String doGet() {
        this.inputParams =  convertToLowerCase(this.inputParams);
        return Result.getResult(pathParams.getFirst("computation"), this.inputParams);
    }

    @Override
    public String doPost(MultivaluedMap<String, String> formParams) {
        this.inputParams = convertToLowerCase(formParams);       
        return Result.getResult(pathParams.getFirst("computation"), this.inputParams);
    }        
    
    private MultivaluedMap<String, String>  convertToLowerCase(MultivaluedMap<String, String> inputParams) { 
        MultivaluedMap<String, String> map = new MultivaluedMapImpl();
  
        Set<String>keys = inputParams.keySet();
        for (String key : keys) {
            map.add(key.toLowerCase(), inputParams.getFirst(key));
        }

        return map; 
    }
}
