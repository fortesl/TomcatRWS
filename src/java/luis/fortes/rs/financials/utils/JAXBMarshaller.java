/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package luis.fortes.rs.financials.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lfortes
 */
@Provider
@Produces("application/xml")
public class JAXBMarshaller implements MessageBodyWriter {

    @Override
    public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type.isAnnotationPresent(XmlRootElement.class);
    }

    @Override
    public long getSize(Object t, Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Context
    protected Providers providers;
    
    @Override
    public void writeTo(Object t, Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        try {
            JAXBContext ctx = null;
            ContextResolver<JAXBContext> resolver =
                    providers.getContextResolver(JAXBContext.class, mediaType);
            if (resolver != null) {
                ctx = resolver.getContext(type);
            }
            if (ctx == null) {
                ctx = JAXBContext.newInstance(type);
            }
            Marshaller m = ctx.createMarshaller();
            
            boolean pretty = false;
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Pretty.class)) {
                    pretty = true;
                    break;
                }
            }
            if (pretty) {
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            }
            m.marshal(t, entityStream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
