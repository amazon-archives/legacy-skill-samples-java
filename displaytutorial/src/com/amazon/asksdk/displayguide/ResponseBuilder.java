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

import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.interfaces.core.PlainTextHint;
import com.amazon.speech.speechlet.interfaces.core.directive.HintDirective;
import com.amazon.speech.speechlet.interfaces.display.directive.RenderTemplateDirective;
import com.amazon.speech.speechlet.interfaces.display.template.*;
import com.amazon.speech.speechlet.interfaces.videoapp.Metadata;
import com.amazon.speech.speechlet.interfaces.videoapp.VideoItem;
import com.amazon.speech.speechlet.interfaces.videoapp.directive.LaunchDirective;
import com.amazon.speech.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ResponseBuilder {

    private static final Logger log = LoggerFactory.getLogger(ResponseBuilder.class);
    private OutputSpeech speech;
    private Card card;
    private List<Directive> directiveList = new ArrayList<>();
    private Boolean shouldEndSession;
    private Reprompt reprompt;

    SpeechletResponse build() {
        SpeechletResponse response = new SpeechletResponse();
        response.setOutputSpeech(speech);
        response.setCard(card);
        response.setReprompt(reprompt);
        response.setDirectives(directiveList);
        response.setNullableShouldEndSession(shouldEndSession);
        return response;
    }

    ResponseBuilder setSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        this.speech = speech;
        return this;
    }

    ResponseBuilder setSimpleCard(String cardTitle, String cardText) {
        SimpleCard card = new SimpleCard();
        card.setContent(cardText);
        card.setTitle(cardTitle);
        this.card = card;
        return this;
    }

    ResponseBuilder setStandardCard(String cardTitle, String cardText, Image image) {
        StandardCard card = new StandardCard();
        card.setText(cardText);
        card.setImage(image);
        card.setTitle(cardTitle);
        this.card = card;
        return this;
    }

    ResponseBuilder setReprompt(String text) {
        Reprompt reprompt = new Reprompt();
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(text);
        reprompt.setOutputSpeech(speech);
        this.reprompt = reprompt;
        return this;
    }

    ResponseBuilder setShouldEndSession(Boolean shouldEndSession) {
        this.shouldEndSession = shouldEndSession;
        return this;
    }

    ResponseBuilder addHintDirective(String hintText) {
        PlainTextHint hint =  new PlainTextHint();
        hint.setText(hintText);
        HintDirective hintDirective = new HintDirective();
        hintDirective.setHint(hint);
        return addDirective(hintDirective);
    }

    ResponseBuilder addVideoDirective(String videoURL, String title, String subTitle) {
        Metadata metadata = new Metadata();
        metadata.setSubtitle(subTitle);
        metadata.setTitle(title);

        VideoItem videoItem = new VideoItem();
        videoItem.setSource(videoURL);
        videoItem.setMetadata(metadata);
        LaunchDirective videoDirective = new LaunchDirective();
        videoDirective.setVideoItem(videoItem);

        return addDirective(videoDirective);
    }

    ResponseBuilder addTemplateDirective(Template template) {
        RenderTemplateDirective templateDirective = new RenderTemplateDirective();
        templateDirective.setTemplate(template);
        return addDirective(templateDirective);
    }

    ResponseBuilder addDirective(Directive directive) {
        directiveList.add(directive);
        return this;
    }
}
