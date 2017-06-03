package com.github.leopoldodonnel.deeprunner;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;

/**
 * Created by leo_odonnell on 6/2/17.
 */
public class DeepRunner {
    private Options options;

    public static void main(String[] args) throws Exception {
        DeepRunner runner = new DeepRunner(args);
    }

    public DeepRunner(String[] args) {
        options = options();
    }

    private Options options() {
        Options options = new Options();
        Option help     = new Option("help", "print this message");
        Option quiet    = new Option("quiet", "be extra quiet");
        Option verbose  = new Option("verbose", "be extra verbose");
        Option debug    = new Option("debug", "print debugging information");

        options.addOption(help)
            .addOption(quiet)
            .addOption(verbose)
            .addOption(debug);


        return options;
    }

}