# Alexa Skills Kit SDK Sample - Savvy Consumer
A simple [AWS Lambda](http://aws.amazon.com/lambda) function that demonstrates how to write a skill for the Amazon Echo using the Alexa SDK.

## Concepts
This sample shows how to create a Lambda function for handling Alexa Skill requests that:

- Web service: Communicate with an the Amazon associates API to get best seller information using [aws-lib](https://github.com/livelycode/aws-lib)
- Dialog and Session State: Handles two models, both a one-shot ask and tell model, and a multi-turn dialog model.
  If the user provides an incorrect slot in a one-shot model, it will direct to the dialog model
- Pagination: Handles paginating a list of responses to avoid overwhelming the customer.
- SSML: Using SSML tags to control how Alexa renders the text-to-speech.

## Setup
To run this example skill you need to do two things. The first is to deploy the example code in lambda, and the second is to configure the Alexa skill to use Lambda.

### AWS Lambda Setup
1. Go to the AWS Console and click on the Lambda link. Note: ensure you are in us-east or you wont be able to use Alexa with Lambda.
2. Click on the Create Function button.
3. Click Author from scratch.
4. In Configure triggers, add Alexa Skill kit as trigger.
5. Name the Lambda Function "Savvy-Consumer-Example-Skill".
6. Select the runtime as Java 8.
7. Build a jar file to upload it into the lambda function. There are two ways:
- Using maven: go to the directory containing pom.xml, and run 'mvn assembly:assembly -DdescriptorId=jar-with-dependencies package'. This will generate a zip file named "savvyconsumer-1.0-jar-with-dependencies.jar" in the target directory.
- Using gradle: go to the directory containing build.gradle,  and run 'gradle fatJar'. This will generate a zip file named "savvyconsumer-fat-1.0.jar" in the build/libs directory.
9. Set the Handler as com.amazon.asksdk.savvyconsumer.SavvyConsumerSpeechletRequestStreamHandler (this refers to the Lambda RequestStreamHandler file in the zip).
10. Choose an existing role - lambda_basic_execution.
11. Increase the Timeout to 30 seconds under Basic Settings.
12. Leave the Advanced settings as the defaults.
13. Click "Next" and review the settings then click "Create Function".
14. Copy the ARN from the top right to be used later in the Alexa Skill Setup.

### Alexa Skill Setup
1. Go to the [Alexa Console](https://developer.amazon.com/edw/home.html) and click Add a New Skill.
2. Set "Savvy Consumer" as the skill name and "savvy consumer" as the invocation name, this is what is used to activate your skill. For example you would say: "Alexa, Ask Savvy Consumer for top books."
3. Select the Lambda ARN for the skill Endpoint and paste the ARN copied from above. Click Next.
4. Copy the contents of SpeechAssets.
    - If you are using the new Skill Builder, copy the Skill Builder from included SkillBuilder.json.
    - Otherwise, copy the Intent Schema from the included IntentSchema.json. Copy the Sample Utterances from the included SampleUtterances.txt. Copy the custom slot types from the customSlotTypes folder. Each file in the folder represents a new custom slot type. The name of the file is the name of the custom slot type, and the values in the file are the values for the custom slot. Click Next.
5. Go back to the skill Information tab and copy the appId. Paste the appId into the SavvyConsumerSpeechletRequestStreamHandler.java file for the variable supportedApplicationIds,
   then update the lambda source zip file with this change and upload to lambda again, this step makes sure the lambda function only serves request from authorized source.
6. You are now able to start testing your sample skill! You should be able to go to the [Echo webpage](http://echo.amazon.com/#skills) and see your skill enabled.
7. In order to test it, try to say some of the Sample Utterances from the Examples section below.
8. Your skill is now saved and once you are finished testing you can continue to publish your skill.

### Savvy Consumer Setup
In order to get the savvy consumer sample skill working, you will need to enter your access and secret key for AWS into SavvyConsumerSpeechlet.java:

1. Go to the [AWS console](https://console.aws.amazon.com/)
2. Click on your name/login in the top bar and select security credentials
3. Copy the Access Key and Secret Access Key.
4. Paste them into the AWS_ACCESS_KEY_ID and AWS_SECRET_KEY variables in SavvyConsumerSpeechlet.java.
5. Zip the src directory again, and upload it to Lambda.

In addition, you will need to create an Amazon Product API account with the same AWS account.

1. Go to the [Product Advertising API](https://affiliate-program.amazon.com/gp/advertising/api/detail/main.html) website.
2. Click on sign up now.
3. Follow the prompts and sign up with your aws account.

## Examples
### One-shot model
     User:  "Alexa, ask Savvy Consumer for top books"
     Alexa: "Here are the top sellers for books. The top seller is .... Would you like to hear more?"
     User:  "No"

### Dialog model:
     User:  "Alexa, open Savvy Consumer"
     Alexa: "Welcome to the Savvy Consumer. For which category do you want to hear the best sellers?"
     User:  "books"
     Alexa: "Here are the top sellers for books. The top seller is .... Would you like to hear more?"
     User:  "yes"
     Alexa: "Second ... Third... Fourth... Would you like to hear more?"
     User : "no"
