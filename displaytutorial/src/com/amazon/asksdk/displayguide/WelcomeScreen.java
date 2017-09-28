/*
 *Copyright 2016-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *Licensed under the Amazon Software License (the "License"). You may not use
 * this file except in compliance with the License. A copy of the License is located at
 *
 *http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amazon.asksdk.displayguide;

import com.amazon.speech.speechlet.SpeechletResponse;

import static com.amazon.asksdk.displayguide.TextUtils.*;

/**
 * The WelcomeScreen class handles launch requests, and other intent requests to go back to the welcome screen
 */
public class WelcomeScreen extends RequestHandler {
    @Override
    public boolean canHandle(final SkillRequestInformation skillRequestInformation) {

        String intentName = skillRequestInformation.getIntentName();
        String token = skillRequestInformation.getToken();

        return Tokens.LAUNCH_REQUEST_TOKEN.equals(token)
                || IntentNames.WELCOME_INTENT.equals(intentName)
                || (IntentNames.AMAZON_PREVIOUS_INTENT.equals(intentName) && Tokens.TOPICS_LIST_SCREEN_TOKEN.equals(token))
                || IntentNames.AMAZON_PREVIOUS_INTENT.equals(intentName);
    }

    @Override
    public SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        return getWelcomeScreenResponse();
    }

    SpeechletResponse getWelcomeScreenResponse() {
        String bodyText = "Welcome to the Display Interface Guide";
        String speechText = bodyText + ". Are you ready to begin?";
        String hintText = "what topics can I learn about?";

        String reprompt = "Say 'yes' to begin or say 'help' for more options. Are you ready to begin?";

        return new ResponseBuilder()
                .setShouldEndSession(false)
                .setSpeech(speechText)
                .setSimpleCard(null, speechText)
                .setReprompt(reprompt)
                .addHintDirective(hintText)
                .addTemplateDirective(
                        new BodyTemplate2Builder()
                                .setFgImage(ImageResources.ALEXA_LOGO.getImage())
                                .setPrimaryText(makeRichText(wrapTextInLargeFont(bodyText)))
                                .setToken(Tokens.WELCOME_SCREEN_TOKEN)
                                .build())
                .build();
    }
}
