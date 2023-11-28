# Genetic Algorithm

This code replicates the experiment of evolving bitstrings using a genetic algorithm found in Melanie Mitchell's "The Royal Road for Genetic Algorithms: Fitness Landscapes and GA Performance". 

## Explaination Of Code

#### Fitness and HIFF
These two functions take a bitstring and return a fitness value. HIFF recursively checks the similarity between the values in the bitstring and returns a fitness value based on that. Alternatively, the Fitness function sums together the values of the bitstring and uses this value as the fitness. 

#### InitPop
This function initializes a population of bitstrings with a specific size (size parameter), number of bits (numBits), and total population size (pop_size)

#### Hill Climber
The hill_climber function generates a random neighbor by switching a bit in a given bitstring, checks if this new bitstring has a higher fitness value than the previous one, and moves to it if the new bitstring is better.

#### Crossover
The crossover funtions take two bitstrings in the current population and create a new bitstring called child. 

#### Mutate
The mutate function randomly changes the newly created child bitstring(output of the used crossover function). 

#### Genetic Algorithm
The genetic_algorithm function takes the following parameters:

(generations, population_size, mutation_rate, population, crossover, script,fit)


The population parameter contains the list of bitstrings representing the population of the bitstrings being evolved. The size of this population is the population_size parameter. The number of generations being ran for one pass of the genetic algorithm is determined by the parameter generations. The mutation_rate parameter determines the liklihood of mutation in the mutation function. The script parameter allows printing to show statistics about each generation. The fit parameter determines which fitness function is being used. 

#### Roulette Wheel Selection

This function randomly determines a selected population to continue with in the genetic algorithm. For parameters, this takes a population and fitness values. 

## Experimentation

#### runExperiment
This function creates an initial population and runs the genetic algorithm once. 

#### ThirtyRuns
This function runs the runExperiment function 30 times. 

## Crossover Differences

Performance wise, the one and two point crossover functions performed relatively similar, while the uniform crossover performed slightly better at maximizing the fitness of the final generation. 

## HIFF vs Fitness

The HIFF function outperformed the standard fitness function by a small amount. I would imagine a hybrid would work the best as HIFF is limited by the binary representation of 00000000... having the same fitness as 11111111...
