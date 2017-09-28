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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The TemplateOverview class handles touch and intent request events for the Template Overview page which presents the
 * user with a list of all available templates, and their names, pictures, and descriptions.
 */
public class TemplateOverview extends RequestHandler {
    private static final Logger log = LoggerFactory.getLogger(TemplateOverview.class);
    @Override
    public boolean canHandle(final SkillRequestInformation skillRequestInformation) {
        String intentName = skillRequestInformation.getIntentName();
        String token = skillRequestInformation.getToken();

        log.debug("intentName: {} token: {}", intentName, token);

        return isIntentOrItemFromTopicList(skillRequestInformation, IntentNames.TEMPLATE_OVERVIEW_INTENT, "1")
                || (IntentNames.AMAZON_PREVIOUS_INTENT.equals(intentName)
                        && tokenMatchesAnIndividualTemplateScreen(token));
    }

    private boolean tokenMatchesAnIndividualTemplateScreen(String token) {
        return Tokens.BODY_TEMPLATE_1_SCREEN_TOKEN.equals(token)
                || Tokens.BODY_TEMPLATE_2_SCREEN_TOKEN.equals(token)
                || Tokens.BODY_TEMPLATE_3_SCREEN_TOKEN.equals(token)
                || Tokens.BODY_TEMPLATE_6_SCREEN_TOKEN.equals(token)
                || Tokens.LIST_TEMPLATE_1_SCREEN_TOKEN.equals(token)
                || Tokens.LIST_TEMPLATE_2_SCREEN_TOKEN.equals(token);
    }

    @Override
    public SpeechletResponse handle(final SkillRequestInformation skillRequestInformation){
        return getTemplateOverviewScreenResponse();
    }

    private SpeechletResponse getTemplateOverviewScreenResponse() {
        String title = "Render Template Directive";
        String description = "There are 2 List templates and 4 Body templates. " +
                "All templates have a customizable background image and display your skill's icon in the " +
                "top right corner. " +
                "You can display a template by sending a Render Template Directive in your skill's response.";
        String speechText = description + "  Select one to see an example.";
        String cardText = description + "  Here is a link to more information on the Render Template directive: " +
                "https://developer.amazon.com/public/solutions/alexa/alexa-skills-kit/docs/display-interface-reference";
        String hintText = "select number 1";

        ItemListForListTemplate2 itemList = new ItemListForListTemplate2();
        ListContentGenerator.generateTemplateOverviewList(itemList);

        return new ResponseBuilder()
                .setShouldEndSession(null)
                .setSpeech(speechText)
                .setSimpleCard(title, cardText)
                .addHintDirective(hintText)
                .addTemplateDirective(
                        new ListTemplate2Builder()
                                .setListItems(itemList.getItems())
                                .setTitle(title)
                                .setToken(Tokens.TEMPLATE_OVERVIEW_SCREEN_TOKEN)
                                .build()
                )
                .build();
    }
}
