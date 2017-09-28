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

/**
 * The HelpScreen class handles request for help at anytime during the skill experience, and presents a list of sample
 * utterances to the user, using List Template 1
 */
public class HelpScreen extends RequestHandler {
    @Override
    boolean canHandle(final SkillRequestInformation skillRequestInformation) {
        return IntentNames.AMAZON_HELP_INTENT.equals(skillRequestInformation.getIntentName());
    }

    @Override
    SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        return getHelpScreenResponse();
    }

    private SpeechletResponse getHelpScreenResponse() {
        String title = "Help | Alexa...";
        String description = "The Display Interface Guide can help you learn how to build Alexa Skills for Echo show "
                + "and Alexa-enabled devices with displays.";
        String speechText = description + "  Here are a few things you can ask me.";
        String cardText = description + "  You should support a user request for help in your skill. "
                + "It is common to use List Template 1 to display a list of example phrases users can say "
                + "to interact with your skill.";

        ItemListForListTemplate1 itemList = new ItemListForListTemplate1();
        ListContentGenerator.generateHelpPhrasesList(itemList);

        return new ResponseBuilder()
                .setSpeech(speechText)
                .setSimpleCard(title, cardText)
                .setShouldEndSession(null)
                .addTemplateDirective(
                        new ListTemplate1Builder()
                                .setListItems(itemList.getItems())
                                .setToken(Tokens.HELP_SCREEN_TOKEN)
                                .setTitle(title)
                                .build())
                .build();
    }
}
