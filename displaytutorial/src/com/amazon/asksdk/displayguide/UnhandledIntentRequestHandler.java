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
 * The UnhandledRequestHandler class handles all unhandled intent request, by prompting the user to say something else
 */
public class UnhandledIntentRequestHandler extends RequestHandler {
    @Override
    boolean canHandle(final SkillRequestInformation skillRequestInformation) {
        return SkillRequestInformation.RequestType.INTENT_REQUEST == skillRequestInformation.getRequestType();
    }

    @Override
    SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        return new ResponseBuilder()
                .setSimpleCard("Unhandled", "Sorry, I couldn't understand you.")
                .setSpeech("Sorry, I couldn't understand you. what did you want to learn about?")
                .setReprompt("Try asking for a topic, or say help for more options. What do you want to learn?")
                .setShouldEndSession(false)
                .build();
    }
}
