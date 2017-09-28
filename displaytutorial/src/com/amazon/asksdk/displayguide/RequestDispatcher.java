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
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.display.DisplayInterface;
import com.amazon.speech.speechlet.interfaces.system.SystemInterface;
import com.amazon.speech.speechlet.interfaces.system.SystemState;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The RequestDispatcher class is responsible for finding a handler to handle the request, and dispatching the
 * request to that handler.
 */
public class RequestDispatcher {
    private static final Logger log = LoggerFactory.getLogger(RequestDispatcher.class);

    private RequestParser requestParser;

    private static List<RequestHandler> handlers;
    static {
        handlers = new ArrayList<>();
        handlers.add(new TemplateOverview());
        handlers.add(new HelpScreen());
        handlers.add(new TopicListScreen());
        handlers.add(new IndividualTemplateScreen());
        handlers.add(new VideoInstructionalScreen());
        handlers.add(new WatchVideo());
        handlers.add(new WelcomeScreen());
        handlers.add(new InlineImageScreen());
        handlers.add(new ActionTagScreen());
        handlers.add(new HandlingListItemSelectionScreen());
        handlers.add(new GoodbyeScreen());
        handlers.add(new HintTextsScreen());
        handlers.add(new UnhandledIntentRequestHandler());
    }

    private RequestDispatcher() {}

    public RequestDispatcher(RequestParser requestParser) {
        this.requestParser = requestParser;
    }

    public SpeechletResponse dispatchRequest(SpeechletRequestEnvelope envelope) {

        SystemState system = envelope.getContext().getState(SystemInterface.class, SystemState.class);
        boolean hasDisplay = system.getDevice().getSupportedInterfaces().isInterfaceSupported(DisplayInterface.class);

        if (!hasDisplay) {
            String msg = "The Display Interface Guide requires an Echo device with a display, such as Echo Show. You " +
                    "cannot use this skill on a headless Echo device.";
            return new ResponseBuilder()
                    .setSpeech(msg)
                    .setSimpleCard("Echo device with display required", msg)
                    .setShouldEndSession(true)
                    .build();
        }

        SkillRequestInformation skillRequestInformation = requestParser.parseRequestEnvelope(envelope);

        for ( RequestHandler handler : handlers) {
            try {
                if (handler.canHandle(skillRequestInformation)) {
                    SpeechletResponse response = handler.handle(skillRequestInformation);

                    log.debug(new ObjectMapper()
                            .writerWithDefaultPrettyPrinter()
                            .writeValueAsString(response));

                    return response;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        throw new UnhandledRequestException(String.format("intentName: %s token: %s slots: %s",
                skillRequestInformation.getIntentName(),
                skillRequestInformation.getToken(),
                skillRequestInformation.getSlots()));
    }
}
