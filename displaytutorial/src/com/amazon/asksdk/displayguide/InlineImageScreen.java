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
 * The InlineImageScreen class handles all requests for the Inline Image Screen, which describes to the user how to include
 * inline images in a Rich Text element, and shows examples of one image being displayed with different pixels heights and
 * widths.
 */
public class InlineImageScreen extends RequestHandler {
    @Override
    public boolean canHandle(final SkillRequestInformation skillRequestInformation) {
        return isIntentOrItemFromTopicList(skillRequestInformation, IntentNames.INLINE_IMAGE_TAG_INTENT, "3");
    }

    @Override
    public SpeechletResponse handle(final SkillRequestInformation skillRequestInformation) {

        String title = "Including images in rich text elements";
        String shortDescription = "You can include images in a rich text element using the image tag. ";

        String speech = shortDescription + " Check out your Echo Show screen to see examples of inline images.";

        String bodyText = shortDescription + "All templates support rich text elements in their corresponding text fields.  " +
                "Here is an example of an image included inline in a rich text element: " +
                inlineImageTag(ImageResources.FOUR_COLORS.getImage()) + LINE_BREAK + LINE_BREAK +
                "The format for including an image is: "+ LINE_BREAK + LINE_BREAK +
                wrapTextInBold(escapeXML("<img src=\"image source url\" width=\"# of pixels\" height=\"# of pixels\"></img>")) +
                LINE_BREAK + LINE_BREAK +
                "If the width and height provided are smaller than the actual dimensions of the image, then the image " +
                "will be cropped to that size from the top left corner." +
                LINE_BREAK +
                "If the dimensions provided are larger than the actual dimensions, then the image will be stretched to " +
                "fill the space." +
                LINE_BREAK +
                "The original image size is 200x200. Here the image is cropped to 150x150: " +
                inlineImageTag(ImageResources.FOUR_COLORS.getImage().getSources().get(0).getUrl(), 150, 150) +
                "Here is the image cropped to 100x100: " +
                inlineImageTag(ImageResources.FOUR_COLORS.getImage().getSources().get(0).getUrl(), 100, 100) +
                "Here is the image cropped to 150x200:" +
                inlineImageTag(ImageResources.FOUR_COLORS.getImage().getSources().get(0).getUrl(), 150, 200) +
                "Here is the image stretched to 300x500:" +
                inlineImageTag(ImageResources.FOUR_COLORS.getImage().getSources().get(0).getUrl(), 300, 500);

        return new ResponseBuilder()
                .setSpeech(speech)
                .addTemplateDirective(new BodyTemplate1Builder()
                        .setPrimaryText(makeRichText(bodyText))
                        .setTitle(title)
                        .setToken(Tokens.INLINE_IMAGE_SCREEN_TOKEN)
                        .build())
                .build();
    }
}
