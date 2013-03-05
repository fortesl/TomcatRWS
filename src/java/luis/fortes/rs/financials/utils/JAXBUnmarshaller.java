/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package luis.fortes.rs.financials.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 *
 * @author lfortes
 */
@Provider
@Consumes("application/xml")
public class JAXBUnmarshaller implements MessageBodyReader {

    @Override
    public boolean isReadable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type.isAnnotationPresent(type);
    }

    @Override
    public Object readFrom(Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        try {
            JAXBContext ctx = JAXBContext.newInstance(type);
            return ctx.createUnmarshaller().unmarshal(entityStream);
        } catch(JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
