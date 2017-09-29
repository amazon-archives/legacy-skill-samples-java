Alexa Skill Kit - Display Interface Guide
===================


Welcome to Display Interface Guide. The Display interface guide is a tutorial-style Alexa Skill that showcases the features of the Amazon Echo Show. It is written in Java using the Alexa Skills Kit Java SDK.

----------


Contents
-------------
This skill shows how to create a Lambda function for handling Alexa Skill requests and sending responses in Echo Show with:

- Render templates: 4 body templates and 2 list templates.
- Inline images: each template allows different sizes of the image.
- Videos: display and watch the video.
- Selection by voice and touch: select an item in the list either by voice or by touch.
- Action tag: create a clickable text.
- Hints: include a hint text.





Setup
-------------
To run this example skill you need to do two things. The first is to deploy the example code in lambda, and the second is to configure the Alexa skill to use Lambda.

AWS Lambda Setup
----------------------------
1. Go to the [AWS Console](https://aws.amazon.com/) and click on the Lambda link. Note: ensure you are in us-east or you wont be able to use Alexa with Lambda.
2. Click on the Create Function button.
3. Click Author from scratch.
4. In Configure triggers, add Alexa Skill kit as trigger.
5. Name the Lambda Function "Display-Interface-Guide-Skill".
6. Select the runtime as Java 8.
7. Build a jar file to upload it into the lambda function. There are two ways:
- Using maven: go to the directory containing pom.xml, and run 'mvn assembly:assembly -DdescriptorId=jar-with-dependencies package'. This will generate a zip file named "displayguide-1.0-jar-with-dependencies.jar" in the target directory. 
- Using gradle: go to the directory containing build.gradle,  and run 'gradle fatJar'. This will generate a zip file named "displaytutorial-fat-1.0.jar" in the build/libs directory.
8. Select Code entry type as "Upload a .ZIP file" and then upload the jar file created in step 7 from the build directory to Lambda.
9. Set the Handler as com.amazon.asksdk.displayguide.DisplayGuideRequestStreamHandler (this refers to the Lambda RequestStreamHandler file in the zip).
10. Choose an existing role - lambda_basic_execution.
11. Leave the Advanced settings as the defaults.
12. Click "Next" and review the settings then click "Create Function".
13. Copy the ARN from the top right to be used later in the Alexa Skill Setup.

Alexa Skill Setup
------------------------
1. Go to the [Alexa Console](https://developer.amazon.com) and click Add a New Skill in Alexa link.
2. Set "Display interface Guide" as the skill name and "Display Guide" as the invocation name, this is what is used to activate your skill. For example you would say: "Alexa, Ask Display Guide to show me how to play a video."
3. Select the Lambda ARN for the skill Endpoint and paste the ARN copied from above. Click Next.
4. Copy the custom slot types from the customSlotTypes folder. Each file in the folder represents a new custom slot type. The name of the file is the name of the custom slot type, and the values in the file are the values for the custom slot.
5. Copy the Intent Schema from the included IntentSchema.json.
6. Copy the Sample Utterances from the included SampleUtterances.txt. Click Next.
7. Go back to the skill Information tab and copy the appId. Paste the appId into the DisplayGuideRequestStreamHandler.java file for the variable supportedApplicationIds, then update the lambda source zip file with this change and upload to lambda again, this step makes sure the lambda function only serves request from authorized source.
8. You are now able to start using your sample skill! You should be able to go to the [Echo webpage](https://alexa.amazon.com/) and see your skill enabled.
9. In order to test it, try to say some of the Sample Utterances from the Examples section below.
10. Your skill is now saved and once you are finished testing you can continue to publish your skill.

Examples
--------------
User: "Alexa, ask display guide to show me topics I can learn about"

User: "how do I use a template"

User: "how do I play a video"

User: "how do I show an image inline with text"

User: "how do I include an action tag"

User: "how do I handle selection of list items"

User: "how do I use a hint"




