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
 * The TopicListScreen class handles all requests for the Topic List Screen, including both
 * touch and intent requests for this screen. It displays a list of possible topics that the user
 * can interact with.
 */
public class TopicListScreen extends RequestHandler {
    @Override
    public boolean canHandle(final SkillRequestInformation skillRequestInformation) {

        String intentName = skillRequestInformation.getIntentName();
        String token = skillRequestInformation.getToken();

        return IntentNames.TOPIC_LIST_INTENT.equals(intentName)
               || (IntentNames.AMAZON_PREVIOUS_INTENT.equals(intentName) && tokenMatchesASubTopicScreenFromThisList(token))
               || (IntentNames.AMAZON_YES_INTENT.equals(intentName) && Tokens.WELCOME_SCREEN_TOKEN.equals(token));
    }

    private boolean tokenMatchesASubTopicScreenFromThisList(String token) {
        return Tokens.TEMPLATE_OVERVIEW_SCREEN_TOKEN.equals(token)
                || Tokens.ACTION_TAG_SCREEN_TOKEN.equals(token)
                || Tokens.ITEM_SELECTION_SCREEN_TOKEN.equals(token)
                || Tokens.INLINE_IMAGE_SCREEN_TOKEN.equals(token)
                || Tokens.HINTS_SCREEN_TOKEN.equals(token);
    }

    @Override
    public SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        return getTopicListScreenResponse();
    }

    private SpeechletResponse getTopicListScreenResponse() {
        String speechText = "Here is a list of topics you can learn about. Select one to learn more.";

        ItemListForListTemplate1 itemList = new ItemListForListTemplate1();
        ListContentGenerator.generateTopicsList(itemList);

        return new ResponseBuilder()
                .setShouldEndSession(null)
                .setSpeech(speechText)
                .setSimpleCard(null, speechText)
                .addTemplateDirective(
                        new ListTemplate1Builder()
                                .setListItems(itemList.getItems())
                                .setToken(Tokens.TOPICS_LIST_SCREEN_TOKEN)
                                .build())
                .build();
    }
}
