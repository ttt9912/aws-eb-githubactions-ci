### Options
- Management Console
- Elastic Beanstalk CLI

## Create ACM Certificate
- in the same Region

Request it in ACM:
![alt](img/cert-req.png)
- *. enables subdomains (www., api., ...)

Validate
- DNS validation (add a given CNAME record)
- Email validation

## Add HTTPS Listener in Elastic Beanstalk
- only works in load balanced environments

![alt](img/https-listener.png)
- SSL Policy Best Practice = use latest

## Create Route 53 record

![alt](img/create-alias-record.png)
