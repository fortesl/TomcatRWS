Financial Computation WebServices

These web services provide answers for various well known financial computations. They are mainly intended for use by application developers for integration with any application or platform which support online web services.

Author: fortesl

Base URI: https://jas-fortes.rhcloud.com/ws/financial/

HTTP methods: GET and POST.

Content-Type consumed: application/x-www-form-urlencoded, multipart/form-data, or application/xml (POST method); query strings (Get method).

Content-Type produced: All services return a text/plain currency value. example: 123.15

Hosted by: RedHat OpenShift

Available Web Services
Service	Path URI	Input parameters	Description	Sample Clients
Annuity Investment	annuityinvestment	DesiredAnnuity, RateOfReturn, NumberOfYears*, and WithdrawalsPerYear	Computes the investment required for a desired annuity payment, given the desired annuity payment, the rate of return, the number of years, and the number of withdrawals per year. Number of withdrawals is optional, if not given, 12 (monthly) withdrawals per year are assumed.	GET
POST

Annuity Payment	annuitywithdrawal	InitialInvestment, RateOfReturn, NumberOfYears*, and WithdrawalsPerYear	Computes the annuity payment (withdrawal) amount given an initial investment, the rate of return, the number of years, and the number of withdrawals per year. Number of withdrawals is optional, if not given, 12 (monthly) withdrawals per year are assumed.	GET
POST

Compound Interest	compoundinterest	Principal, RateOfReturn, NumberOfYears*, and CompoundingPeriodsPerYear	Computes the amount of compound interest earned on an account or investment, given the original principal, the number of years, the annual rate of return, and the number of compounding periods per year. The number of compounding periods is optional, if not given, 12 (monthly compounding) is assumed.	GET
POST

Future Value	futurevalue	Principal, RateOfReturn, NumberOfYears*, and CompoundingPeriodsPerYear	Computes the future value of an investment given the principal (initial investments), rate of return, number of years, and the number of compounding periods per year. Compounding periods is optional, if not given, 1 compounding period per year is assumed.	GET
POST

Loan Balance	loanbalance	Principal, InterestRate, PaymentAmount, PaymentsMade, and PaymentsPerYear	Computes the remaining balance on a loan given the original principal, interest rate, payment amount, number of payments already made, and the number of payments per year. Number of payments is optional, if not given, 12 payments (monthly payments) are assumed.	GET
POST

Loan Payment	loanpayment	Principal, InterestRate, NumberOfYears*, and PaymentsPerYear	Computes the payment amount on a loan given the principal, interest rate, number of years, and the number of payments per year. Number of payments is optional, if not given, 12 payments (monthly payments) are assumed.	GET
POST

Present Value	presentvalue	FutureValue, RateOfReturn, NumberOfYears*, and CompoundingPeriodsPerYear	Computes the present day value (PV) of an amount expected at a future date, given the future value, the rate of return, the number of years, and the number of compounding periods per year. Compounding periods is optional, if not given, 1 compounding period per year is assumed.	GET
POST

Simple Interest	simpleinterest	Principal, RateOfReturn, and NumberOfYears*	Computes the amount of simple (non compounding) interest accrued on a loan or savings account, given the original principal, the rate of return and the number of years.	GET
POST

* The year is often used in financial transactions as the relevant time period, therefore we use the term "NumberOfYears", but as far as these services are concerned, it just represents a number of periods, not necessarily tied to the calendar year.
Posting XML content: All Services also consume data of application/xml mime type. Below is a sample xml file content which can be posted to the Loan Payment service:

                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <FinancialValues>
                        <!-- Compute loan payment -->
                        <principal>20000</principal>
                        <numberOfYears>5</numberOfYears>
                        <interestRate>9</interestRate>
                        <paymentsPerYear>12</paymentsPerYear>
                </FinancialValues>
                
The xml content for all services have the same format. Just specify the input parameter elements required by the service nested in the <FinancialValues> element. 
Here is an example that submits the xml content above in a file named loan_payment.xml with the cURL command line utility:
curl -X POST -d@loan_payment.xml --header "Content-Type:application/xml" {BaseURI}/loanpayment

If you wish, leave feedback and let me know how you are consuming the services. Are there any computations you would like me to add?

Thank you,
Luis Fortes.
