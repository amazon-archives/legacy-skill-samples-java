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
import com.amazon.speech.speechlet.interfaces.display.element.TextField;
import com.amazon.speech.speechlet.interfaces.display.element.TripleTextContent;

/**
 * ItemList provides an interface for all operations used when constructing List Items for List Template 1 or 2
 */
public interface ItemList {

    void addItem(Image image, String token, TripleTextContent textContent);

    TripleTextContent makeTextContent(TextField primary);

    TripleTextContent makeTextContent(TextField primary, TextField secondary);

    TripleTextContent makeTextContent(TextField primary, TextField secondary, TextField tertiary);
}
