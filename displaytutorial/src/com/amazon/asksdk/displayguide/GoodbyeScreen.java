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

import static com.amazon.asksdk.displayguide.TextUtils.makePlainText;

/**
 * The GoodbyeScreen class handles request events to exit the skill.
 */
public class GoodbyeScreen extends RequestHandler {
    @Override
    boolean canHandle(final SkillRequestInformation skillRequestInformation) {

        String intentName = skillRequestInformation.getIntentName();

        return IntentNames.AMAZON_STOP_INTENT.equals(intentName) || (IntentNames.AMAZON_NO_INTENT.equals(intentName)
                && Tokens.WELCOME_SCREEN_TOKEN.equals(skillRequestInformation.getToken()));
    }

    @Override
    SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        return new ResponseBuilder()
                .setShouldEndSession(true)
                .setSpeech("See you next time!")
                .setSimpleCard("Goodbye!", "Visit the Alexa Skills Kit website to learn more.")
                .addTemplateDirective(new BodyTemplate6Builder()
                        .setPrimaryText(makePlainText("Goodbye!"))
                        .build())
                .build();
    }
}
