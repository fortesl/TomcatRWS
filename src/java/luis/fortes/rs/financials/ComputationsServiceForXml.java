/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package luis.fortes.rs.financials;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import luis.fortes.rs.financials.utils.Result;
import luis.fortes.rs.financials.utils.XmlInput;

/**
 *
 * @author lfortes
 */
public class ComputationsServiceForXml {
    private MultivaluedMap<String, String> inputParams;
    private MultivaluedMap<String, String> pathParams;
    
    /**
     * Creates a new instance of ComputationsServiceForXml
     */
    public ComputationsServiceForXml(MultivaluedMap<String, String> pathParams) {
        this.pathParams = pathParams;
    }
    
        /**
     * Retrieves representation of an instance of luis.fortes.rs.financials.ComputationsService
     * @param xmlInput
     * @return an instance of java.lang.String
     */
    @POST
    @Path("{computation}")
    @Consumes(value = {"application/xml"})
    public String doPost(XmlInput xmlInput) {
        this.inputParams = this.getFromXml(xmlInput);
        return Result.getResult(pathParams.getFirst("computation"), this.inputParams);
    }
    
    
    private MultivaluedMap<String, String> getFromXml(XmlInput xmlInput) {
        MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        
        map.add("compoundingperiodsperyear", xmlInput.getCompoundingPeriodsPerYear());
        map.add("compoundingyears", xmlInput.getCompoundingYears());
        map.add("desiredannuity", xmlInput.getDesiredAnnuity());
        map.add("initialinvestment", xmlInput.getInitialInvestment());
        map.add("numberofyears", xmlInput.getNumberOfYears());
        map.add("paymentamount", xmlInput.getPaymentAmount());
        map.add("paymentsmade", xmlInput.getPaymentsMade());
        map.add("paymentsperyear", xmlInput.getPaymentsPerYear());
        map.add("principal", xmlInput.getPrincipal());
        map.add("rateofreturn", xmlInput.getRateOfReturn());
        map.add("withdrawalsperyear", xmlInput.getWithdrawalsPerYear());
        map.add("interestrate", xmlInput.getInterestRate());
        
        return map;
    }

}
