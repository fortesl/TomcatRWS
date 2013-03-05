/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package luis.fortes.rs.financials.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fortesl
 */
@XmlRootElement(name="FinancialValues")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlInput {
    
    @XmlElement(required=false)
    protected String principal;
    
    @XmlElement(required=false)
    protected String initialInvestment;
    
    @XmlElement(required=false)
    protected String numberOfYears;
    
    @XmlElement(required=false)
    protected String paymentAmount;
    
    @XmlElement(required=false)
    protected String rateOfReturn;
    
    @XmlElement(required=false)
    protected String desiredAnnuity;
    
    @XmlElement(required=false)
    protected String withdrawalsPerYear;
            
    @XmlElement(required=false)
    protected String paymentsPerYear;

    @XmlElement(required=false)
    protected String paymentsMade;
    
    @XmlElement(required=false)
    protected String compoundingYears;

    @XmlElement(required=false)
    protected String compoundingPeriodsPerYear;
    
    @XmlElement(required=false)
    protected String interestRate;
    
    
    
    
    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getInitialInvestment() {
        return initialInvestment;
    }

    public void setInitialInvestment(String initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    public String getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(String numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getRateOfReturn() {
        return rateOfReturn;
    }

    public void setRateOfReturn(String rateOfReturn) {
        this.rateOfReturn = rateOfReturn;
    }

    public String getDesiredAnnuity() {
        return desiredAnnuity;
    }

    public void setDesiredAnnuity(String desiredAnnuity) {
        this.desiredAnnuity = desiredAnnuity;
    }

    public String getWithdrawalsPerYear() {
        return withdrawalsPerYear;
    }

    public void setWithdrawalsPerYear(String withdrawalsPerYear) {
        this.withdrawalsPerYear = withdrawalsPerYear;
    }

    public String getPaymentsPerYear() {
        return paymentsPerYear;
    }

    public void setPaymentsPerYear(String paymentsPerYear) {
        this.paymentsPerYear = paymentsPerYear;
    }

    public String getPaymentsMade() {
        return paymentsMade;
    }

    public void setPaymentsMade(String paymentsMade) {
        this.paymentsMade = paymentsMade;
    }

    public String getCompoundingYears() {
        return compoundingYears;
    }

    public void setCompoundingYears(String compoundingYears) {
        this.compoundingYears = compoundingYears;
    }

    public String getCompoundingPeriodsPerYear() {
        return compoundingPeriodsPerYear;
    }

    public void setCompoundingPeriodsPerYear(String compoundingPeriodsPerYear) {
        this.compoundingPeriodsPerYear = compoundingPeriodsPerYear;
    }
    
    
}
