package com.github.leopoldodonnel.deeprunner;

import org.apache.commons.cli.*;
import org.deeplearning4j.util.ModelSerializer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.*;
import java.sql.BatchUpdateException;

/**
 * Created by leo_odonnell on 6/2/17.
 */
public class DeepRunner {
    static private boolean isVerbose = false;
    static private boolean isDebug = false;

    private BufferedReader network;
    private BufferedReader data;
    private BufferedWriter out;

    public static void main(String[] args) throws Exception {
        CommandLine cmdLine = null;
        CommandLineParser cmdParser = new DefaultParser();

        BufferedReader data;
        BufferedWriter out;
        BufferedReader network;

        try {
            cmdLine = cmdParser.parse(options(), args);
        }
        catch( ParseException exp ) {
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            System.exit(-1);
        }

        if (cmdLine.hasOption("verbose")) isVerbose = true;
        if (cmdLine.hasOption("debug")) isDebug = true;


        // Setup the input data reader
        if (cmdLine.hasOption("data")) {
            File datafile = new File(cmdLine.getOptionValue("data"));
            if (datafile.canRead() == false) {
                System.err.printf("Can't read from data file %s\n", datafile.getPath());
                System.exit(-1);
            }
            data = new BufferedReader(new FileReader(datafile));
        }
        else {
            data = new BufferedReader(new InputStreamReader(System.in));
        }

        // Setup the output writer
        if (cmdLine.hasOption("out")) {
            File outfile = new File(cmdLine.getOptionValue("out"));
            outfile.createNewFile();
            if (outfile.canWrite() == false) {
                System.err.printf("Can't write to output file %s\n", outfile.getPath());
                System.exit(-1);
            }
            out = new BufferedWriter(new FileWriter(outfile));
        }
        else {
            out = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        // TODO: Open the model

        // Get the yaml network file
        if (cmdLine.getArgs().length != 1) {
            System.err.printf("A network YAML file must be provided");
            System.exit(-1);
        }

        File networkfile = new File(cmdLine.getArgs()[0]);
        if (networkfile.canRead() == false) {
            System.err.printf("Can't read from data file %s\n", networkfile.getPath());
            System.exit(-1);
        }
        network = new BufferedReader(new FileReader(networkfile));


        DeepRunner runner = new DeepRunner(network);

        // Train the model and return
        if (cmdLine.hasOption("train")) {
            runner.train(data, out);
            return;
        }

        // Run the model that should have been generated from a previous training

        if (cmdLine.hasOption("model") == false) {
            System.err.println("When running a network, a trained model must be supplied");
            System.exit(-1);
        }

        File modelfile = new File(cmdLine.getOptionValue("model"));
        if (modelfile.canRead() == false) {
            System.err.printf("Can't read from model file %s\n", modelfile.getPath());
            System.exit(-1);
        }

        MultiLayerNetwork model = ModelSerializer.restoreMultiLayerNetwork(modelfile);

        // get the model
        runner.run(model, data, out);
    }

    public DeepRunner(BufferedReader network) {
        this.network = network;

        // Initialize the network
    }

    public void run(MultiLayerNetwork model, BufferedReader data, BufferedWriter out) {

    }

    public void train(BufferedReader data, BufferedWriter out) {

    }

    private static Options options() {
        Options options = new Options();
        Option help     = new Option("help", "Display help and usage information");
        Option verbose  = new Option("verbose", "be extra verbose");
        Option debug    = new Option("debug", "print debugging information");
        Option train    = new Option("train", "Input data is used to generate an output model");
        Option data     = Option.builder("d")
                .longOpt("data")
                .required(false)
                .hasArg(true)
                .desc("file path to the input data")
                .build();
        Option out      = Option.builder("o")
                .longOpt("out")
                .required(false)
                .hasArg(true)
                .desc("file path to the output data")
                .build();
        Option model    = Option.builder()
                .longOpt("model")
                .required(false)
                .hasArg(true)
                .desc("file path the network model that was generated from a training run")
                .build();


        options.addOption(help)
            .addOption(verbose)
            .addOption(debug)
            .addOption(train)
            .addOption(data)
            .addOption(out)
            .addOption(model);


        return options;
    }

    private static String help() {
        return "Placeholder for help";
    }

}