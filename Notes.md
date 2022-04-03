## Link
https://aws.plainenglish.io/hands-on-ci-cd-for-spring-boot-applications-using-github-actions-and-aws-1cbc1e2c9d54

## GitHub Actions
Workflow (build.yml) can be executed
    - manually
    - automatically on push to master

## How To

### Elastic Beanstalk
- Create Application
  - Java Corretto 11 V3.1.8
  - Code: sample application
- Environment is created automatically

### AWS Credentials
1. Create user with access to Elastic Beanstalk
   ![alt](img/create-aws-user.png)


2. Create Access keys for this user
   ![alt](img/create-access-keys.png)
   - generates AWS_ACCESS_KEY_ID & AWS_SECRET_ACCESS_KEY


3. Create AWS_SESSION_TOKEN

### GitHub secrets
- AWS_ACCESS_KEY_ID
- AWS_SECRET_ACCESS_KEY
- AWS_SESSION_TOKEN (optional)

![alt](img/github-secrets.png)

### GitHub Actions Workflow

1. Create File .github/workflows/build.yml
   - Jobs: test, build, deploy

2. server.port=5000

3. Git push
   - Workflow will be executed automatically

## Result

![alt](img/results.png)