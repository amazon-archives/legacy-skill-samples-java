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
 * The VideoInstructionalScreen class handles touch and intent request for the Video Instructional Screen which
 * describes how to include videos in a skill.
 */
public class VideoInstructionalScreen extends RequestHandler {
    @Override
    public boolean canHandle(final SkillRequestInformation skillRequestInformation) {
        return isIntentOrItemFromTopicList(skillRequestInformation, IntentNames.VIDEO_INSTRUCTIONAL_INTENT, "2");
    }

    @Override
    public SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        String title = "How to include Videos";
        String shortDescription = "Including videos in your skill is easy thanks to the Video App Interface.  " +
                "After sending a Launch Directive in your skill's response, the Video App takes over and handles " +
                "all video playback controls such as pause, play, and fast forward. ";
        String speech = shortDescription + " Look to your Echo Show screen to read more.";

        String description = wrapTextInAction("Watch Video", Tokens.WATCH_VIDEO_ACTION_TOKEN) + LINE_BREAK +
                wrapTextInItalics("Try clicking the blue action above to see the Video App in action.") + LINE_BREAK +
                LINE_BREAK + shortDescription +
                "When the user exits your video, your skill session resumes at the point where the video app was " +
                "launched.  To send a VideoApp.Launch directive, include the URL of your video and an optional " +
                "title and subtitle. Your video must be hosted at a publicly-accessible HTTPS endpoint. See the " +
                "Video App Interface documentation on the Alexa Skills Kit website for more details.";

        return new ResponseBuilder()
                .setShouldEndSession(null)
                .setSpeech(speech)
                .addTemplateDirective(new BodyTemplate2Builder()
                        .setTitle(title)
                        .setPrimaryText(makeRichText(description))
                        .setFgImage(ImageResources.VIDEO_EXAMPLE.getImage())
                        .build())
                .setSimpleCard("Launch Directive", "Video example")
                .build();
    }
}
