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
package uk.co.dandyer.watchmaker.framework.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import uk.co.dandyer.maths.ConstantSequence;
import uk.co.dandyer.maths.NumberSequence;
import uk.co.dandyer.watchmaker.framework.EvolutionaryOperator;

/**
 * Generic base class for cross-over implementations.  Supports all
 * cross-over processes that operate on a pair of parent candidates.
 * @author Daniel Dyer
 */
public abstract class AbstractCrossover<T> implements EvolutionaryOperator<T>
{
    private final NumberSequence<Integer> crossoverPointsVariable;

    /**
     * @param crossoverPoints The constant number of cross-over points
     * to use for all cross-over operations.
     */
    protected AbstractCrossover(int crossoverPoints)
    {
        this(new ConstantSequence<Integer>(crossoverPoints));
    }


    /**
     * @param crossoverPointsVariable A random variable that provides a number
     * of cross-over points for each cross-over operation.
     */
    protected AbstractCrossover(NumberSequence<Integer> crossoverPointsVariable)
    {
        this.crossoverPointsVariable = crossoverPointsVariable;
    }

    /**
     * @param <S> A more specific type restriction than that associated
     * with this class (T).  Ensures that the returned list is of the appropriate
     * type even when dealing with sub-classes of T.
     */
    @SuppressWarnings("unchecked")
    public <S extends T> List<S> apply(List<S> population, Random rng)
    {
        List<S> result = new ArrayList<S>(population.size());
        Iterator<S> iterator = population.iterator();
        while (iterator.hasNext())
        {
            S parent1 = iterator.next();
            S parent2 = iterator.next();
            int crossoverPoints = crossoverPointsVariable.nextValue();
            result.addAll((Collection<? extends S>) reproduce(parent1, parent2, crossoverPoints, rng));
        }
        return result;
    }

    /**
     * Implementing classes should return the list elements of the most specific
     * type possible (derived from the actual types of the arguments).  In other
     * words, if <code>parent1</code> and <code>parent2</code> are instances of
     * a sub-class of T, then the elements returned returned in the list must
     * also be instances of the same sub-class.  This is to ensure that the
     * cross-over implementation can correctly deal with populations of
     * sub-classes of T.
     */
    protected abstract List<? extends T> reproduce(T parent1,
                                                   T parent2,
                                                   int numberOfCrossoverPoints,
                                                   Random rng);
}