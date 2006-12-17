// ============================================================================
//   Copyright 2006 Daniel W. Dyer
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
// ============================================================================
package org.uncommons.watchmaker.framework.factories;

import java.util.Random;

/**
 * General-purpose candidate factory for EAs that use a fixed-length String encoding.
 * Generates random strings of a fixed length from a given alphabet.
 * @author Daniel Dyer
 */
public class StringFactory extends AbstractCandidateFactory<String>
{
    private final char[] alphabet;
    private final int stringLength;

    /**
     * @param alphabet The set of characters that can legally occur within a
     * string generated by this factory.
     * @param stringLength The fixed length of all strings generated by this
     * factory.
     */
    public StringFactory(char[] alphabet,
                         int stringLength)
    {
        this.alphabet = alphabet.clone();
        this.stringLength = stringLength;
    }


    /**
     * Generates a random string of a pre-configured length.  Each character
     * is randomly selected from the pre-configured alphabet.  The same
     * character may appear multiple times and some characters may not appear
     * at all.
     * @param rng A source of randomness used to select characters to make up
     * the string.
     * @return A randomly generated string.
     */
    protected String generateRandomCandidate(Random rng)
    {
        char[] chars = new char[stringLength];
        for (int i = 0; i < stringLength; i++)
        {
            chars[i] = alphabet[rng.nextInt(alphabet.length)];
        }
        return new String(chars);
    }
}
