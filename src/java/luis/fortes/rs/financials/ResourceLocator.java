/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package luis.fortes.rs.financials;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author lfortes
 */
@Path("/")
@Produces("text/plain")
public class ResourceLocator {
    private static final Logger LOG = Logger.getLogger(ResourceLocator.class.getName());
    @Context
    private UriInfo context;
    @Context 
    private HttpHeaders httpHeaders;
    
    @Path("/{financial: [fF]inancial}")
    public Object assignResource() {
        MultivaluedMap<String, String> pathParams = context.getPathParameters(true);
        MultivaluedMap<String, String> inputParams = context.getQueryParameters();
        List<String> contentType = httpHeaders.getRequestHeader("content-type");
        LOG.log(Level.INFO, "pathParams: {0}\n inputParams: {1}\n httpHeaders: {2}", 
                new Object[] {pathParams, inputParams, httpHeaders.getRequestHeaders()});

        if (contentType != null && "application/xml".equals(contentType.get(0))) {
            return new ComputationsServiceForXml(pathParams);
        }
        else {
            return new ComputationsService(pathParams, inputParams);
        }
    }

}
