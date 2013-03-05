/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package luis.fortes.rs.financials.utils;

import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import static luis.fortes.financials.FinancialComputations.*;

/**
 *
 * @author lfortes
 */
public class Result {
    
    private enum financialResources {
    loanpayment, loanbalance, futurevalue, annuityinvestment, annuitywithdrawal}
    private static final Logger LOG = Logger.getLogger(Result.class.getName());
    private static final NumberFormat numberFormat;
    
    static {
        numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
    }


    static public String getResult(String function, MultivaluedMap<String, String> params) {
        financialResources computation;
        try {
            computation = financialResources.valueOf(function.toLowerCase());
        }catch (IllegalArgumentException e) {
            throw new WebApplicationException(new Throwable("Unknown resource: " + function), Response.Status.NOT_FOUND);
        }
        validateParameters(computation, params);
        double result = 0;
        try {
            switch(computation) {
                case futurevalue:
                    result = computeFutureValueOfInvestment(
                            Double.parseDouble(params.getFirst("principal")),
                            Double.parseDouble(params.getFirst("rateofreturn")),
                            Integer.parseInt(params.getFirst("compoundingyears")),
                            params.containsKey("compoundingperiodsperyear") ?
                            Integer.parseInt(params.getFirst("compoundingperiodsperyear")) : 1);
                    break;
                case loanbalance:
                    result = computeRemainingBalanceOnLoan(
                            Double.parseDouble(params.getFirst("principal")),
                            Double.parseDouble(params.getFirst("interestrate")),
                            Double.parseDouble(params.getFirst("paymentAmount")),
                            Integer.parseInt(params.getFirst("paymentsMade")),
                            params.containsKey("paymentsperyear") ?
                            Integer.parseInt(params.getFirst("paymentsperyear")) : 12);
                    break;
                case loanpayment:
                    result = computePaymentOnLoan(
                            Double.parseDouble(params.getFirst("principal")),
                            Double.parseDouble(params.getFirst("interestrate")),
                            Integer.parseInt(params.getFirst("numberofyears")),
                            params.containsKey("paymentsperyear") ?
                            Integer.parseInt(params.getFirst("paymentsperyear")) : 12);
                    break;
                case annuityinvestment:
                    result = computeInitialInvestmentForAnnuity(
                            Double.parseDouble(params.getFirst("desiredAnnuity")),
                            Double.parseDouble(params.getFirst("rateofreturn")),
                            Integer.parseInt(params.getFirst("numberofyears")),
                            params.containsKey("withdrawalsperyear") ?
                            Integer.parseInt(params.getFirst("withdrawalsperyear")) : 12);
                    break;
                case annuitywithdrawal:
                    result = computeAnnuityWithdrawalFromInvestment(
                            Double.parseDouble(params.getFirst("initialInvestment")),
                            Double.parseDouble(params.getFirst("rateofreturn")),
                            Integer.parseInt(params.getFirst("numberofyears")),
                            params.containsKey("withdrawalsperyear") ?
                            Integer.parseInt(params.getFirst("withdrawalsperyear")) : 12);
                    break;
            }        
            return numberFormat.format(result);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid parameters specified: " + params.keySet());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input values specified: " + params);
        }
        
    }
    
    private static void validateParameters(financialResources computation, MultivaluedMap<String, String> params) {
        switch (computation) {
            case futurevalue:
                if (!params.containsKey("principal") ||
                        !params.containsKey("rateofreturn") ||
                        !params.containsKey("compoundingYears")) {
                    LOG.log(Level.WARNING, 
                            "{0} Valid parameters are: principal, rateofreturn, compoundingYears and " + 
                            "compoundingPeriodsperyear. \nReceived: {1}", 
                            new Object[] {financialResources.futurevalue, params});
                    throw new WebApplicationException(Response.Status.BAD_REQUEST);                 
                }
                break;
            case loanbalance:
                if (!params.containsKey("principal") ||
                        !params.containsKey("interestrate") ||
                        !params.containsKey("paymentAmount") ||
                        !params.containsKey("paymentsMade")) {
                    LOG.log(Level.WARNING, "{0} " +
                            "Valid parameters are: principal, interestrate, paymentAmount, " + 
                            "paymentsMade and paymentsperyear. \nReceived: {1}", 
                            new Object[]{financialResources.loanbalance, params});
                    throw new WebApplicationException(Response.Status.BAD_REQUEST);
                }
                break;
            case loanpayment:
                if (!params.containsKey("principal") ||
                        !params.containsKey("interestrate") ||
                        !params.containsKey("numberofyears")) {
                    LOG.log(Level.WARNING, "{0} " +
                            "Valid parameters are: principal, interestrate, numberofyears " +
                            "and paymentsperyear. \nReceived: {1}",
                            new Object[]{financialResources.loanpayment, params});
                    throw new WebApplicationException(Response.Status.BAD_REQUEST);                 
                }
                break;
            case annuityinvestment:
                if (!params.containsKey("desiredAnnuity") ||
                        !params.containsKey("rateofreturn") ||
                        !params.containsKey("numberofyears")) {
                    LOG.log(Level.WARNING, "{0} " +
                            "Valid parameters are: desiredAnnuity, rateofreturn, numberofyears and " +
                            "withdrawalsperyear. \nReceived: {1}",
                            new Object[]{financialResources.annuityinvestment, params});
                    throw new WebApplicationException(Response.Status.BAD_REQUEST); 
                }
                break;
            case annuitywithdrawal:
                if (!params.containsKey("initialInvestment") ||
                        !params.containsKey("rateofreturn") ||
                        !params.containsKey("numberofyears")) {
                    LOG.log(Level.WARNING, "{0} " +
                            "Valid parameters are: initialInvestment, rateofreturn, numberofyears and " +
                            "withdrawalsperyear. \nReceived: {1}",
                            new Object[]{financialResources.annuitywithdrawal, params});
                    throw new WebApplicationException(Response.Status.BAD_REQUEST);                 
                }
                break;            
        }
    }
}
