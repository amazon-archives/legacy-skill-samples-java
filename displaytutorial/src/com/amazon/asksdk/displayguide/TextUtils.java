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

import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.element.ImageInstance;
import com.amazon.speech.speechlet.interfaces.display.element.PlainText;
import com.amazon.speech.speechlet.interfaces.display.element.RichText;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;

public class TextUtils {

    static String LINE_BREAK = "<br/>";
    static String SPACE = "&#160;";

    static PlainText makePlainText(String text) {
        PlainText plainText = new PlainText();
        plainText.setText(text);
        return plainText;
    }

    static RichText makeRichText(String text) {
        RichText richText = new RichText();
        richText.setText(text);
        return richText;
    }

    static String escapeXML(String text) {
        return StringEscapeUtils.escapeXml(text);
    }
    static String wrapTextInExtraSmallFont(String text) {
        return String.format("<font size=\"2\">%s</font>", text);
    }
    static String wrapTextInSmallFont(String text) {
        return String.format("<font size=\"3\">%s</font>", text);
    }
    static String wrapTextInMediumFont(String text) {
        return String.format("<font size=\"5\">%s</font>", text);
    }
    static String wrapTextInLargeFont(String text) {
        return String.format("<font size=\"7\">%s</font>", text);
    }
    static String wrapTextInBold(String text) {
        return String.format("<b>%s</b>", text);
    }
    static String wrapTextInItalics(String text) {
        return String.format("<i>%s</i>", text);
    }
    static String wrapTextInUnderline(String text) {
        return String.format("<u>%s</u>", text);
    }
    static String wrapTextInAction(String text, String token) {
        return String.format("<action value=\"%s\">%s</action>", token, text);
    }

    static String inlineImageTag(Image image) {
        List<ImageInstance> sources = image.getSources();
        ImageInstance imageInstance;
        if(sources.size() > 0) {
            imageInstance = sources.get(0);
        } else {
            return "";
        }

        return inlineImageTag(imageInstance.getUrl(), imageInstance.getWidthPixels(), imageInstance.getHeightPixels());
    }
    static String inlineImageTag(String url, int width, int height) {
        return String.format("<img src=\"%s\" width=\"%s\" height=\"%s\"></img>", url, width, height);
    }
}
