package io;

import config.Constants;

public class StandardOutputWriter
{
    public static void printTitle()
    {
        String title =
        "╔═╗┬ ┬┌┬┐┌─┐┬┌─┬ ┬  ╔═╗┌─┐┬ ┬  ┬┌─┐┬─┐\n" +
        "╚═╗│ │ │││ │├┴┐│ │  ╚═╗│ ││ └┐┌┘├┤ ├┬┘\n" +
        "╚═╝└─┘─┴┘└─┘┴ ┴└─┘  ╚═╝└─┘┴─┘└┘ └─┘┴└─\n";
        System.out.printf(title);
        System.out.println("    by Karun Kesavadas, Kumari Deepshika and Bhashmi Fatnani");
        System.out.println();
    }

    public static void printAppConfiguration()
    {
        System.out.println("Application Configuration:");
        System.out.println("ELITISM RATE : "+ Constants.ELITISM_RATE);
        System.out.println("MUTATION RATE : "+Constants.MUTATION_RATE);
        System.out.println("POPULATION SIZE : "+Constants.POPULATION_SIZE);
        System.out.println("POPULATIONS BEFORE RESTART : "+Constants.POPULATIONS_BEFORE_RESTART);
        System.out.println("NUMBER OF PARENTS : "+Constants.NUMBER_OF_PARENTS);
    }
}
