# Alexa Skills Kit SDK Sample - Wise Guy
A simple [AWS Lambda](http://aws.amazon.com/lambda) function that demonstrates how to write a skill for the Amazon Echo using the Alexa SDK.

## Concepts
This sample shows how to create a Lambda function for handling Alexa Skill requests that:

- Session State: Handles a multi-turn dialog model.
- Custom slot type: demonstrates using custom slot types to handle a finite set of known values
- SSML: Using SSML tags to control how Alexa renders the text-to-speech.

## Setup
To run this example skill you need to do two things. The first is to deploy the example code in lambda, and the second is to configure the Alexa skill to use Lambda.

### AWS Lambda Setup
1. Go to the AWS Console and click on the Lambda link. Note: ensure you are in us-east or you wont be able to use Alexa with Lambda.
2. Click on the Create Function button.
3. Click Author from scratch.
4. In Configure triggers, add Alexa Skill kit as trigger.
5. Name the Lambda Function "WiseGuy-Example-Skill".
6. Select the runtime as Java 8
7. Build a jar file to upload it into the lambda function. There are two ways:
- Using maven: go to the directory containing pom.xml, and run 'mvn assembly:assembly -DdescriptorId=jar-with-dependencies package'. This will generate a zip file named "wiseguy-1.0-jar-with-dependencies.jar" in the target directory.
- Using gradle: go to the directory containing build.gradle,  and run 'gradle fatJar'. This will generate a zip file named "wiseguy-fat-1.0.jar" in the build/libs directory.
9. Set the Handler as com.amazon.asksdk.wiseguy.WiseGuySpeechletRequestStreamHandler (this refers to the Lambda RequestStreamHandler file in the zip).
10. Choose an existing role - lambda_basic_execution.
11. Increase the Timeout to 30 seconds under Basic Settings.
12. Leave the Advanced settings as the defaults.
13. Click "Next" and review the settings then click "Create Function".
14. Copy the ARN from the top right to be used later in the Alexa Skill Setup.

### Alexa Skill Setup
1. Go to the [Alexa Console](https://developer.amazon.com/edw/home.html) and click Add a New Skill.
2. Set "WiseGuy" as the skill name and "wise guy" as the invocation name, this is what is used to activate your skill. For example you would say: "Alexa, Ask wise guy for a joke."
3. Select the Lambda ARN for the skill Endpoint and paste the ARN copied from above. Click Next.
4. Copy the contents of SpeechAssets.
    - If you are using the new Skill Builder, copy the Skill Builder from included SkillBuilder.json.
    - Otherwise, copy the Intent Schema from the included IntentSchema.json. Copy the Sample Utterances from the included SampleUtterances.txt. Copy the custom slot types from the customSlotTypes folder. Each file in the folder represents a new custom slot type. The name of the file is the name of the custom slot type, and the values in the file are the values for the custom slot. Click Next.
5. Go back to the skill Information tab and copy the appId. Paste the appId into the WiseGuySpeechletRequestStreamHandler.java file for the variable supportedApplicationIds,
   then update the lambda source zip file with this change and upload to lambda again, this step makes sure the lambda function only serves request from authorized source.
6. You are now able to start testing your sample skill! You should be able to go to the [Echo webpage](http://echo.amazon.com/#skills) and see your skill enabled.
7. In order to test it, try to say some of the Sample Utterances from the Examples section below.
8. Your skill is now saved and once you are finished testing you can continue to publish your skill.

## Examples
### Dialog model:
    User: "Alexa, ask Wise Guy to tell me a knock knock joke."
    Alexa: "Knock knock"
    User: "Who's there?"
    Alexa: "<phrase>"
    User: "<phrase> who"
    Alexa: "<punchline>"
