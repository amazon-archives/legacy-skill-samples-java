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

import com.amazon.speech.json.SpeechletRequestEnvelope;

/**
 * The LaunchRequestParser class is responsible for parsing information from the SpeechletRequestEnvelope for a
 * Launch Request
 */
public class LaunchRequestParser implements RequestParser {
    @Override
    public SkillRequestInformation parseRequestEnvelope(SpeechletRequestEnvelope envelope) {
        SkillRequestInformation skillRequestInformation = new SkillRequestInformation();
        skillRequestInformation.setToken(Tokens.LAUNCH_REQUEST_TOKEN);
        skillRequestInformation.setRequestType(SkillRequestInformation.RequestType.LAUNCH_REQUEST);

        return skillRequestInformation;
    }
}
