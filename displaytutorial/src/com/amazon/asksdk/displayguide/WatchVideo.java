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
 * The WatchVideo class handles touch and intent requests to watch an example video inside the skill
 */
public class WatchVideo extends RequestHandler {
    @Override
    public boolean canHandle(final SkillRequestInformation skillRequestInformation) {
        return IntentNames.WATCH_VIDEO_INTENT.equals(skillRequestInformation.getIntentName());
    }

    @Override
    public SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {
        String videoURL = "https://s3.amazonaws.com/mkusters-images/guitar_video.mp4";
        String videoTitle = "Sample Video Title";
        String videosubTitle = "Sample Video SubTitle";

        return new ResponseBuilder()
                .addVideoDirective(videoURL, videoTitle, videosubTitle)
                .build();
    }
}
